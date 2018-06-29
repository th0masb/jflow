/**
 *
 */
package xawd.jflow.iterators.iterables;

import java.util.function.DoubleConsumer;

import xawd.jflow.iterators.DoubleFlow;

/**
 * Abstraction of iterable object which can construct enhanced primitive double
 * iterators ({@link DoubleFlow}).
 *
 * @author t
 */
@FunctionalInterface
public interface DoubleFlowIterable
{
	DoubleFlow iterator();

	default void forEach(final DoubleConsumer action)
	{
		iterator().forEach(action);
	}
}
