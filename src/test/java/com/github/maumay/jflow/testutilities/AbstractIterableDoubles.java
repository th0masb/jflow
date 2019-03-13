package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.iterables.DoubleIterable;
import com.github.maumay.jflow.iterators.impl2.AbstractDoubleIterator;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractIterableDoubles implements DoubleIterable
{
	@Override
	public abstract AbstractDoubleIterator iter();
}
