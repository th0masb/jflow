package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.iterables.LongIterable;
import com.github.maumay.jflow.iterators.AbstractLongIterator;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableLongs implements LongIterable
{
	@Override
	public abstract AbstractLongIterator iter();
}
