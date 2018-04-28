package xawd.jflow.abstractiterables;

import xawd.jflow.AbstractIntFlow;
import xawd.jflow.iterables.IterableInts;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableInts implements IterableInts
{
	@Override
	public abstract AbstractIntFlow iter();
}
