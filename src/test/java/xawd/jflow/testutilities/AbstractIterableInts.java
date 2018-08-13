package xawd.jflow.testutilities;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.iterables.IntFlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableInts implements IntFlowIterable
{
	@Override
	public abstract AbstractIntFlow iterator();
}
