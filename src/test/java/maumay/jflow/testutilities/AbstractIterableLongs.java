package maumay.jflow.testutilities;

import maumay.jflow.iterators.AbstractEnhancedLongIterator;
import maumay.jflow.iterators.iterables.LongIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableLongs implements LongIterable
{
	@Override
	public abstract AbstractEnhancedLongIterator iter();
}
