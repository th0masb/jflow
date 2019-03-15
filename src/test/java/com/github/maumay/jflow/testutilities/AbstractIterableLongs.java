package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.iterables.LongIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableLongs implements LongIterable
{
	@Override
	public abstract AbstractLongIterator iter();
}
