/**
 *
 */
package maumay.jflow.iterators.iterables;

import java.util.function.IntConsumer;

import maumay.jflow.iterators.EnhancedIntIterator;

/**
 * Abstraction of iterable object which can construct enhanced primitive int
 * iterators ({@link EnhancedIntIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface IntIterable
{
	EnhancedIntIterator iter();

	default void forEach(final IntConsumer action)
	{
		iter().forEachRemaining(action);
	}
}
