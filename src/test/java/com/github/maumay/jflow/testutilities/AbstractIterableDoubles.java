package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.iterables.DoubleIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableDoubles implements DoubleIterable
{
	@Override
	public abstract AbstractDoubleIterator iter();
}
