package xawd.jflow.abstractiterables;

import xawd.jflow.AbstractLongFlow;
import xawd.jflow.iterables.IterableLongs;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableLongs implements IterableLongs
{
	@Override
	public abstract AbstractLongFlow iter();
}
