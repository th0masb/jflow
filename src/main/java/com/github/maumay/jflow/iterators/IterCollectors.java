/**
 * 
 */
package com.github.maumay.jflow.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.utils.Exceptions;
import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.Vec;

/**
 * Provides access to a couple of iterator collectors.
 * 
 * @author t
 */
public class IterCollectors
{
	private IterCollectors()
	{
	}

	/**
	 * A collector which computes the average of all elements in a primitive double
	 * iterator, throws an exception if the iterator is empty.
	 * 
	 * @return a collector which computes the average of elements traversed by a
	 *         primitive double iterator.
	 */
	public static DoubleIteratorCollector<Double> average()
	{
		return AVERAGE;
	}

	private static final DoubleIteratorCollector<Double> AVERAGE = iter -> {
		iter.relinquishOwnership();
		if (iter.hasNext()) {
			double sum = 0;
			long count = 0;
			do {
				sum += iter.nextDoubleImpl();
				count++;
			} while (iter.hasNext());
			return sum / count;
		} else {
			throw new NoSuchElementException();
		}
	};

	/**
	 * Creates a collector which applies the same logic as
	 * {@link RichIterator#associate(Function)} but optimizes the result for
	 * enumerated types by returning an instance of {@link EnumMap}. Note that if
	 * this iterator is empty then a {@link HashMap} will be returned instead.
	 * 
	 * @param   <K> The element type of iterators this collector will accept.
	 * @param   <R> The type of values in the resultant map.
	 * @param f The function which determines the values of the map.
	 * @return see {@link RichIterator#associate(Function)}
	 */
	public static <K extends Enum<K>, R> RichIteratorCollector<K, Map<K, R>> toEnumMap(
			Function<? super K, ? extends R> f)
	{
		return new EnumMapCollector<K, R>(f);
	}

	static class EnumMapCollector<K extends Enum<K>, R>
			implements RichIteratorCollector<K, Map<K, R>>
	{
		private final Function<? super K, ? extends R> targetMapper;

		public EnumMapCollector(Function<? super K, ? extends R> targetMapper)
		{
			this.targetMapper = targetMapper;
		}

		@Override
		public Map<K, R> collect(AbstractRichIterator<? extends K> source)
		{
			// If there are no concrete elements we can't instantiate an enum map so
			// return
			// a HashMap
			Exceptions.require(source.hasOwnership());
			if (!source.hasNext()) {
				return new HashMap<>();
			}
			K first = source.nextImpl();
			@SuppressWarnings("unchecked")
			Map<K, R> dest = new EnumMap<>((Class<K>) first.getClass());
			dest.put(first, targetMapper.apply(first));
			while (source.hasNext()) {
				K next = source.nextImpl();
				if (dest.containsKey(next)) {
					throw new IllegalStateException();
				} else {
					dest.put(next, targetMapper.apply(next));
				}
			}
			return dest;
		}
	}

	public static <E> RichIteratorCollector<E, Vec<Vec<E>>> pack(int packSize)
	{
		return new Packer<>(packSize);
	}

	static class Packer<E> implements RichIteratorCollector<E, Vec<Vec<E>>>
	{
		private final int packSize;

		public Packer(int packSize)
		{
			Exceptions.require(packSize > 0);
			this.packSize = packSize;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Vec<Vec<E>> collect(AbstractRichIterator<? extends E> source)
		{
			source.relinquishOwnership();
			List<Vec<E>> dest = new ArrayList<>();
			Object[] curr = new Object[packSize];
			int count = 0;
			while (source.hasNext()) {
				curr[count++] = source.nextImpl();
				if (count == packSize) {
					dest.add(Vec.<E>of((E[]) curr));
					curr = new Object[packSize];
					count = 0;
				}
			}
			if (count > 0) {
				dest.add(Vec.<E>of((E[]) Arrays.copyOf(curr, count)));
			}
			return Vec.copy(dest);
		}
	}

	public static DoubleIteratorCollector<Vec<DoubleVec>> packDoubles(int packSize)
	{
		return new DoublePacker(packSize);
	}

	static class DoublePacker implements DoubleIteratorCollector<Vec<DoubleVec>>
	{
		private final int packSize;

		public DoublePacker(int packSize)
		{
			Exceptions.require(packSize > 0);
			this.packSize = packSize;
		}

		@Override
		public Vec<DoubleVec> collect(AbstractDoubleIterator source)
		{
			source.relinquishOwnership();
			List<DoubleVec> dest = new ArrayList<>();
			double[] curr = new double[packSize];
			int count = 0;
			while (source.hasNext()) {
				curr[count++] = source.nextDoubleImpl();
				if (count == packSize) {
					dest.add(DoubleVec.of(curr));
					curr = new double[packSize];
					count = 0;
				}
			}
			if (count > 0) {
				dest.add(DoubleVec.of(Arrays.copyOf(curr, count)));
			}
			return Vec.copy(dest);
		}
	}
}
