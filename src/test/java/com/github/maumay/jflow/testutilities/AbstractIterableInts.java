package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.iterators.iterables.IntIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableInts implements IntIterable
{
	@Override
	public abstract AbstractEnhancedIntIterator iter();
}
