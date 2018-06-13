/**
 *
 */
package xawd.jflow.collections;

import java.util.Collection;
import java.util.function.Predicate;

import xawd.jflow.iterators.iterables.FlowIterable;

/**
 * @author ThomasB
 *
 */
public interface FlowCollection<E> extends Collection<E>, FlowIterable<E>
{
	default boolean allMatch(final Predicate<? super E> condition)
	{
		return flow().allMatch(condition);
	}
}
