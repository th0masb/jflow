/**
 *
 */
package com.github.maumay.jflow.iterators.iterables;

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
	DoubleIterator iter();

	default void forEach(final DoubleConsumer action)
	{
		iter().forEach(action);
	}
}
