package xawd.jflow.iterators.abstractiterables;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.iterables.FlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractFlowIterable<T> implements FlowIterable<T>
{
	@Override
	public abstract AbstractFlow<T> iter();
}
