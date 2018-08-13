package xawd.jflow.testutilities;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.iterables.LongFlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableLongs implements LongFlowIterable
{
	@Override
	public abstract AbstractLongFlow iterator();
}
