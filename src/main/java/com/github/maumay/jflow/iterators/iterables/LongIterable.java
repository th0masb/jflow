/**
 *
 */
package com.github.maumay.jflow.iterators.iterables;

import java.util.function.LongConsumer;

import com.github.maumay.jflow.iterators.EnhancedLongIterator;

/**
 * Abstraction of iterable object which can construct enhanced primitive long
 * iterators ({@link EnhancedLongIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface LongIterable
{
	EnhancedLongIterator iter();

	default void forEach(final LongConsumer action)
	{
		iter().forEachRemaining(action);
	}
}
