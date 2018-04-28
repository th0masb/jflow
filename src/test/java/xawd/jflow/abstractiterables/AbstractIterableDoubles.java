package xawd.jflow.abstractiterables;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.iterables.IterableDoubles;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableDoubles implements IterableDoubles
{
	@Override
	public abstract AbstractDoubleFlow iter();
}
