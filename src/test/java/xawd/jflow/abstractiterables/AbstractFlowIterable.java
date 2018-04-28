package xawd.jflow.abstractiterables;

import xawd.jflow.AbstractFlow;
import xawd.jflow.iterables.FlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractFlowIterable<T> implements FlowIterable<T>
{
	@Override
	public abstract AbstractFlow<T> iter();
}
