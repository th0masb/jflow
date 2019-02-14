/**
 *
 */
package maumay.jflow.iterators.iterables;

import java.util.function.DoubleConsumer;

import maumay.jflow.iterators.EnhancedDoubleIterator;

/**
 * Abstraction of iterable object which can construct enhanced primitive double
 * iterators ({@link EnhancedDoubleIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface DoubleIterable
{
	EnhancedDoubleIterator iter();

	default void forEach(final DoubleConsumer action)
	{
		iter().forEach(action);
	}
}
