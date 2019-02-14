/**
 * 
 */
package maumay.jflow.vec;

import java.util.stream.DoubleStream;

import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.factories.Iter;

/**
 * @author ThomasB
 *
 */
public final class DoubleVec
{
	private static final DoubleVec EMPTY = new DoubleVec(new double[0]);

	private final double[] src;

	public DoubleVec(double[] src)
	{
		this.src = src;
	}

	public static DoubleVec of(double[] src)
	{
		return new DoubleVec(src);
	}

	public EnhancedDoubleIterator iter()
	{
		return Iter.doubles(src);
	}

	public DoubleStream stream()
	{
		return DoubleStream.of(src);
	}

	public double get(int index)
	{
		return src[index];
	}

	public int size()
	{
		return src.length;
	}

	public static DoubleVec empty()
	{
		return EMPTY;
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