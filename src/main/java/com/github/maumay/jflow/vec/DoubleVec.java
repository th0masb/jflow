/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.stream.DoubleStream;

import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.iterable.DoubleIterable;
import com.github.maumay.jflow.iterator.DoubleIterator;

/**
 * An immutable wrapper around a primitive double array which can build enhanced
 * iterators and streams.
 * 
 * @author thomasb
 */
public interface DoubleVec extends DoubleIterable
{
	/**
	 * Computes the number of elements in this vector.
	 * 
	 * @return The number of elements contained in this vector.
	 */
	int size();

	/**
	 * Returns the double value stored at the given index.
	 * 
	 * @param index a non-negative integer which is strictly smaller than the result
	 *              of calling {@link #size()}.
	 * @return the value stored at the given index.
	 */
	double get(int index);

	/**
	 * Returns an enhanced iterator traversing over the elements in this vector in
	 * reverse order.
	 * 
	 * @return an iterator traversing the elements in this vector in reverse order.
	 */
	DoubleIterator revIter();

	/**
	 * Constructs a sequential {@link DoubleStream} over the elements in this
	 * vector.
	 * 
	 * @return a sequential stream of primitive doubles whose source is this vector.
	 */
	DoubleStream stream();

	/**
	 * Computes a sorted copy of this vector.
	 * 
	 * @return A new vector containing the same elements of this but sorted into
	 *         ascending order.
	 */
	DoubleVec sorted();

	/**
	 * Creates a vector wrapper around the given arguments, <b>no</b> defensive
	 * copying takes place.
	 * 
	 * @param xs the elements to wrap inside a vector.
	 * @return a vector wrapping the passed arguments.
	 */
	static DoubleVec of(double... xs)
	{
		return new ArraySource.OfDouble(xs).toVec();
	}

	/**
	 * Retrieves the empty double vector.
	 * 
	 * @return The empty double vector.
	 */
	static DoubleVec empty()
	{
		return Constants.EMPTY_DOUBLE_VEC;
	}
}
