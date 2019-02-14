package maumay.jflow.testutilities;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.iterators.iterables.IntIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableInts implements IntIterable
{
	@Override
	public abstract AbstractEnhancedIntIterator iter();
}
