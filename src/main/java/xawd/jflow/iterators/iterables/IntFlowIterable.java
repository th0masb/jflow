/**
 *
 */
package xawd.jflow.iterators.iterables;

import java.util.function.IntConsumer;

import xawd.jflow.iterators.IntFlow;

/**
 * Abstraction of iterable object which can construct enhanced primitive int
 * iterators ({@link IntFlow}).
 *
 * @author t
 */
@FunctionalInterface
public interface IntFlowIterable
{
	IntFlow iterator();

	default void forEach(final IntConsumer action)
	{
		iterator().forEachRemaining(action);
	}
}
