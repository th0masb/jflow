/**
 *
 */
package xawd.jflow.collections;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.iterables.FlowIterable;

/**
 * @author ThomasB
 *
 */
public interface FlowCollection<E> extends Collection<E>, FlowIterable<E>
{
	default <R> Flow<R> map(Function<? super E, R> mapFunction)
	{
		return flow().map(mapFunction);
	}

	default Flow<E> filter(Predicate<? super E> predicate)
	{
		return flow().filter(predicate);
	}

	default boolean allMatch(final Predicate<? super E> condition)
	{
		return flow().allMatch(condition);
	}
}
