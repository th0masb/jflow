/**
 *
 */
package com.github.maumay.jflow.iterables;

import java.util.function.DoubleConsumer;

import com.github.maumay.jflow.iterators.DoubleIterator;

/**
 * Abstraction of iterable object which can construct enhanced primitive double
 * iterators ({@link DoubleIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface DoubleIterable
{
	/**
	 * Constructs an enhanced double iterator traversing the elements encapsulated
	 * by this object.
	 * 
	 * @return an enhanced iterator traversing the encapsulated elements.
	 */
	DoubleIterator iter();

	/**
	 * Consumes this iterator by applying some action to each element in turn.
	 * 
	 * @param action the action to apply to each element in this iterator.
	 */
	default void forEach(DoubleConsumer action)
	{
		iter().forEach(action);
	}
}
