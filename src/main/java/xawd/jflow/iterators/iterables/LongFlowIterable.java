/**
 *
 */
package xawd.jflow.iterators.iterables;

import java.util.function.LongConsumer;

import xawd.jflow.iterators.LongFlow;

/**
 * Abstraction of iterable object which can construct enhanced primitive long
 * iterators ({@link LongFlow}).
 *
 * @author t
 */
@FunctionalInterface
public interface LongFlowIterable
{
	LongFlow iterator();

	default void forEach(final LongConsumer action)
	{
		iterator().forEachRemaining(action);
	}
}
