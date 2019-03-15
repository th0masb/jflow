/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.stream.LongStream;

import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.impl.EmptyIterator;
import com.github.maumay.jflow.iterables.LongIterable;
import com.github.maumay.jflow.iterators.LongIterator;

/**
 * An immutable wrapper around a primitive long array which can build enhanced
 * iterators and streams.
 * 
 * @author thomasb
 */
public interface LongVec extends LongIterable
{
	/**
	 * Computes the number of elements in this vector.
	 * 
	 * @return The number of elements contained in this vector.
	 */
	int size();

	/**
	 * Returns the long value stored at the given index.
	 * 
	 * @param index a non-negative integer which is strictly smaller than the result
	 *              of calling {@link #size()}.
	 * @return the value stored at the given index.
	 */
	long get(int index);

	/**
	 * Returns an enhanced iterator traversing over the elements in this vector in
	 * reverse order.
	 * 
	 * @return an iterator traversing the elements in this vector in reverse order.
	 */
	LongIterator revIter();

	/**
	 * Constructs a sequential {@link LongStream} over the elements in this vector.
	 * 
	 * @return a sequential stream of primitive longs whose source is this vector.
	 */
	LongStream stream();

	/**
	 * Creates a vector wrapper around the given arguments, <b>no</b> defensive
	 * copying takes place.
	 * 
	 * @param xs the elements to wrap inside a vector.
	 * @return a vector wrapping the passed arguments.
	 */
	static LongVec of(long... xs)
	{
		return new ArraySource.OfLong(xs).toVec();
	}

	/**
	 * Retrieves the empty long vector.
	 * 
	 * @return The empty long vector.
	 */
	static LongVec empty()
	{
		return new EmptyIterator.OfLong().toVec();
	}
}
