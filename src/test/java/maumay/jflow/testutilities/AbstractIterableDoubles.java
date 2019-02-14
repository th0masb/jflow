package maumay.jflow.testutilities;

import maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import maumay.jflow.iterators.iterables.DoubleIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableDoubles implements DoubleIterable
{
	@Override
	public abstract AbstractEnhancedDoubleIterator iter();
}
