package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.iterables.EnhancedIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractEnhancedIterable<T> implements EnhancedIterable<T>
{
	@Override
	public AbstractEnhancedIterator<T> iterator()
	{
		return iter();
	}

	@Override
	public abstract AbstractEnhancedIterator<T> iter();
}
