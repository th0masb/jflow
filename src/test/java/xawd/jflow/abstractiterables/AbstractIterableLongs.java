package xawd.jflow.abstractiterables;

import xawd.jflow.AbstractLongFlow;
import xawd.jflow.iterables.LongFlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableLongs implements LongFlowIterable
{
	@Override
	public abstract AbstractLongFlow iter();
}
