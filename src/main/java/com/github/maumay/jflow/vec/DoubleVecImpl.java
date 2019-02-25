/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.stream.DoubleStream;

import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.factories.Iter;

/**
 * @author ThomasB
 *
 */
final class DoubleVecImpl implements DoubleVec
{
	private static final DoubleVecImpl EMPTY = new DoubleVecImpl(new double[0]);

	private final double[] src;

	DoubleVecImpl(double[] src)
	{
		this.src = src;
	}

	// public static DoubleVecImpl of(double[] src)
	// {
	// return new DoubleVecImpl(src);
	// }

	@Override
	public DoubleIterator iter()
	{
		return Iter.doubles(src);
	}

	@Override
	public DoubleStream stream()
	{
		return DoubleStream.of(src);
	}

	@Override
	public double get(int index)
	{
		return src[index];
	}

	@Override
	public int size()
	{
		return src.length;
	}

	public static DoubleVecImpl empty()
	{
		return EMPTY;
	}

	@Override
	public DoubleIterator revIter()
	{
		return Iter.reverseDoubles(src);
	}
}

/*
 * ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * ---------------------------------------------------------------------
 */