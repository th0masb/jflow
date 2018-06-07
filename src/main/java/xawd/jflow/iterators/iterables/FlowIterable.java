package xawd.jflow.iterators.iterables;

import xawd.jflow.iterators.Flow;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface FlowIterable<E> extends Iterable<E>
{
	@Override
	Flow<E> iterator();
}
