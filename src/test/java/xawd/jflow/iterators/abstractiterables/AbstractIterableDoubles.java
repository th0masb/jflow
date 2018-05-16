package xawd.jflow.iterators.abstractiterables;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.iterables.DoubleFlowIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableDoubles implements DoubleFlowIterable
{
	@Override
	public abstract AbstractDoubleFlow iter();
}
