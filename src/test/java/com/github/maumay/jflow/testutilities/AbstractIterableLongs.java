package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.iterators.AbstractEnhancedLongIterator;
import com.github.maumay.jflow.iterators.iterables.LongIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableLongs implements LongIterable
{
	@Override
	public abstract AbstractEnhancedLongIterator iter();
}
