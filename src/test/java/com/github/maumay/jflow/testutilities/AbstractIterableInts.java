package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.iterables.IntIterable;
import com.github.maumay.jflow.iterators.impl2.AbstractIntIterator;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableInts implements IntIterable
{
	@Override
	public abstract AbstractIntIterator iter();
}
