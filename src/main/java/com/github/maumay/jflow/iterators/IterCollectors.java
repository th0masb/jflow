/**
 * 
 */
package com.github.maumay.jflow.iterators;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.utils.Exceptions;

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
	public static <K extends Enum<K>, R> EnumMapCollector<K, R> toEnumMap(
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
}
