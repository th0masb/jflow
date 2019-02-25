/**
 *
 */
package com.github.maumay.jflow.iterables;

import java.util.function.IntConsumer;

import com.github.maumay.jflow.iterators.IntIterator;

/**
 * Abstraction of iterable object which can construct enhanced primitive int
 * iterators ({@link IntIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface IntIterable
{
	IntIterator iter();

	default void forEach(final IntConsumer action)
	{
		iter().forEachRemaining(action);
	}
}
