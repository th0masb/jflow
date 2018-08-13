package xawd.jflow.testutilities;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.iterables.DoubleFlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableDoubles implements DoubleFlowIterable
{
	@Override
	public abstract AbstractDoubleFlow iterator();
}
