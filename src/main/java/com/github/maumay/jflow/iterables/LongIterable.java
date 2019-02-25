/**
 *
 */
package com.github.maumay.jflow.iterables;

import java.util.function.LongConsumer;

import com.github.maumay.jflow.iterators.LongIterator;

/**
 * Abstraction of iterable object which can construct enhanced primitive long
 * iterators ({@link LongIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface LongIterable
{
	LongIterator iter();

	default void forEach(final LongConsumer action)
	{
		iter().forEachRemaining(action);
	}
}
