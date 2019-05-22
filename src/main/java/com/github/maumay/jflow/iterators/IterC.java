/**
 * 
 */
package com.github.maumay.jflow.iterators;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

import com.github.maumay.jflow.impl.EnumAssociator;
import com.github.maumay.jflow.impl.Packer;
import com.github.maumay.jflow.impl.Packer.Type;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.Vec;

/**
 * Provides access to a couple of iterator collectors.
 * 
 * @author t
 */
public class IterC
{
	private IterC()
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
		return iter -> {
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
	}

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
	public static <K extends Enum<K>, R> RichIteratorCollector<K, Map<K, R>> enumAssociate(
			Function<? super K, ? extends R> f)
	{
		return new EnumAssociator<K, R>(f);
	}

	public static <E> RichIteratorCollector<E, Vec<Vec<E>>> pack(int packSize)
	{
		return new Packer.OfObject<>(packSize, Type.EXCLUDE_REMAINDER);
	}

	public static <E> RichIteratorCollector<E, Vec<Vec<E>>> packAll(int packSize)
	{
		return new Packer.OfObject<>(packSize, Type.INCLUDE_REMAINDER);
	}

	public static DoubleIteratorCollector<Vec<DoubleVec>> packDoubles(int packSize)
	{
		return new Packer.OfDouble(packSize, Type.EXCLUDE_REMAINDER);
	}

	public static DoubleIteratorCollector<Vec<DoubleVec>> packAllDoubles(int packSize)
	{
		return new Packer.OfDouble(packSize, Type.INCLUDE_REMAINDER);
	}

	/**
	 * Splits an iterator of tuples into a tuple of equal sized vectors containing
	 * the left and right elements respectively.
	 */
	public static <L, R> RichIteratorCollector<Tup<L, R>, Tup<Vec<L>, Vec<R>>> split()
	{
		return source -> {
			source.relinquishOwnership();
			List<L> left = new ArrayList<>();
			List<R> right = new ArrayList<>();
			while (source.hasNext()) {
				Tup<L, R> next = source.nextImpl();
				left.add(next._1);
				right.add(next._2);
			}
			return Tup.of(Vec.copy(left), Vec.copy(right));
		};
	}
}
