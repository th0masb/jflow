/**
 * 
 */
package com.github.maumay.jflow.iterator.collector;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.function.Function;

import com.github.maumay.jflow.impl.EnumAssociator;
import com.github.maumay.jflow.impl.Packer;
import com.github.maumay.jflow.impl.Packer.Type;
import com.github.maumay.jflow.iterator.RichIterator;
import com.github.maumay.jflow.utils.Option;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.Vec;

/**
 * A collection of static factory methods for building iterator collectors.
 * 
 * @author t
 */
public class IterCollect
{
	private IterCollect()
	{
	}

	/**
	 * A collector which unsafely computes the average of all elements in a
	 * primitive double iterator, throws an exception if the iterator is empty.
	 * 
	 * @return a collector which computes the average of elements traversed by a
	 *         primitive double iterator.
	 */
	public static DoubleIteratorCollector<Double> average()
	{
		return iter -> {
			if (iter.hasNext()) {
				double sum = 0;
				long count = 0;
				do {
					sum += iter.nextDouble();
					count++;
				} while (iter.hasNext());
				return sum / count;
			} else {
				throw new NoSuchElementException();
			}
		};
	}

	/**
	 * A collector which safely computes the average of all elements in a primitive
	 * double iterator, returns nothing if the iterator is empty.
	 * 
	 * @return a collector which computes the average of elements traversed by a
	 *         primitive double iterator.
	 */
	public static DoubleIteratorCollector<OptionalDouble> averageOp()
	{
		return iter -> {
			if (iter.hasNext()) {
				double sum = 0;
				long count = 0;
				do {
					sum += iter.nextDouble();
					count++;
				} while (iter.hasNext());
				return Option.of(sum / count);
			} else {
				return Option.emptyDouble();
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
	public static <K extends Enum<K>, R> IteratorCollector<K, Map<K, R>> enumAssociate(
			Function<? super K, ? extends R> f)
	{
		return new EnumAssociator<K, R>(f);
	}

	/**
	 * Creates a collector which sequentially packs the elements of an iterator into
	 * fixed size vectors. For example if a pack size of 5 is specified the first 5
	 * elements are collected into a vector and the next five are collected into a
	 * different vector etc. If there are remainder elements then they are excluded,
	 * therefore it is guaranteed that all the vectors have length equal to the
	 * specified pack size.
	 * 
	 * @param          <E> The inferred element type of the compatible source
	 *                 iterators.
	 * @param packSize The size of vectors to pack elements into.
	 * @return The packing collector.
	 */
	public static <E> IteratorCollector<E, Vec<Vec<E>>> pack(int packSize)
	{
		return new Packer.OfObject<>(packSize, Type.EXCLUDE_REMAINDER);
	}

	/**
	 * Creates a collector which sequentially packs the elements of an iterator into
	 * fixed size vectors. For example if a pack size of 5 is specified the first 5
	 * elements are collected into a vector and the next five are collected into a
	 * different vector etc. If there are remainder elements then they are included
	 * in a vector of a smaller size to the specified pack size
	 * 
	 * @param          <E> The inferred element type of the compatible source
	 *                 iterators.
	 * @param packSize The size of vectors to pack elements into.
	 * @return The packing collector.
	 */
	public static <E> IteratorCollector<E, Vec<Vec<E>>> packAll(int packSize)
	{
		return new Packer.OfObject<>(packSize, Type.INCLUDE_REMAINDER);
	}

	/**
	 * Creates a collector which sequentially packs the elements of an iterator into
	 * fixed size vectors. For example if a pack size of 5 is specified the first 5
	 * elements are collected into a vector and the next five are collected into a
	 * different vector etc. If there are remainder elements then they are excluded,
	 * therefore it is guaranteed that all the vectors have length equal to the
	 * specified pack size.
	 * 
	 * @param packSize The size of vectors to pack elements into.
	 * @return The packing collector.
	 */
	public static DoubleIteratorCollector<Vec<DoubleVec>> packDoubles(int packSize)
	{
		return new Packer.OfDouble(packSize, Type.EXCLUDE_REMAINDER);
	}

	/**
	 * Creates a collector which sequentially packs the elements of an iterator into
	 * fixed size vectors. For example if a pack size of 5 is specified the first 5
	 * elements are collected into a vector and the next five are collected into a
	 * different vector etc. If there are remainder elements then they are included
	 * in a vector of a smaller size to the specified pack size
	 * 
	 * @param packSize The size of vectors to pack elements into.
	 * @return The packing collector.
	 */
	public static DoubleIteratorCollector<Vec<DoubleVec>> packAllDoubles(int packSize)
	{
		return new Packer.OfDouble(packSize, Type.INCLUDE_REMAINDER);
	}

	/**
	 * Returns a collector which splits an iterator of tuples into a tuple of equal
	 * sized vectors containing the left and right elements respectively.
	 */
	public static <L, R> IteratorCollector<Tup<L, R>, Tup<Vec<L>, Vec<R>>> split()
	{
		return source -> {
			List<L> left = new ArrayList<>();
			List<R> right = new ArrayList<>();
			while (source.hasNext()) {
				Tup<L, R> next = source.next();
				left.add(next._1);
				right.add(next._2);
			}
			return Tup.of(Vec.copy(left), Vec.copy(right));
		};
	}
}
