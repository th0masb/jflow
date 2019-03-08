/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.stream.IntStream;

import com.github.maumay.jflow.iterables.IntIterable;
import com.github.maumay.jflow.iterators.IntIterator;

/**
 * An immutable wrapper around a primitive int array which can build enhanced
 * iterators and streams.
 * 
 * @author thomasb
 */
public interface IntVec extends IntIterable
{
	/**
	 * Computes the number of elements in this vector.
	 * 
	 * @return The number of elements contained in this vector.
	 */
	int size();

	/**
	 * Returns the int value stored at the given index.
	 * 
	 * @param index a non-negative integer which is strictly smaller than the result
	 *              of calling {@link #size()}.
	 * @return the value stored at the given index.
	 */
	int get(int index);

	/**
	 * Returns an enhanced iterator traversing over the elements in this vector in
	 * reverse order.
	 * 
	 * @return an iterator traversing the elements in this vector in reverse order.
	 */
	IntIterator revIter();

	/**
	 * Constructs a sequential {@link IntStream} over the elements in this vector.
	 * 
	 * @return a sequential stream of primitive ints whose source is this vector.
	 */
	IntStream stream();

	/**
	 * Creates a vector wrapper around the given arguments, <b>no</b> defensive
	 * copying takes place.
	 * 
	 * @param xs the elements to wrap inside a vector.
	 * @return a vector wrapping the passed arguments.
	 */
	static IntVec of(int... xs)
	{
		return new IntVecImpl(xs);
	}

	/**
	 * Retrieves the empty int vector.
	 * 
	 * @return The empty int vector.
	 */
	static IntVec empty()
	{
		return IntVecImpl.empty();
	}
}
