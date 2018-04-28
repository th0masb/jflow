package xawd.jflow.abstractiterables;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.iterables.DoubleFlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableDoubles implements DoubleFlowIterable
{
	@Override
	public abstract AbstractDoubleFlow iter();
}
