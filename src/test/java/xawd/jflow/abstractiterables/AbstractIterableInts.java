package xawd.jflow.abstractiterables;

import xawd.jflow.AbstractIntFlow;
import xawd.jflow.iterables.IntFlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableInts implements IntFlowIterable
{
	@Override
	public abstract AbstractIntFlow iter();
}
