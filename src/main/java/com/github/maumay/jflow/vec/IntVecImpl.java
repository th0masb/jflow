/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.stream.IntStream;

import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.factories.Iter;

/**
 * @author ThomasB
 *
 */
final class IntVecImpl implements IntVec
{
	private static final IntVecImpl EMPTY = new IntVecImpl(new int[0]);

	private final int[] src;

	IntVecImpl(int[] src)
	{
		this.src = src;
	}

	@Override
	public IntIterator iter()
	{
		return Iter.ints(src);
	}

	@Override
	public IntStream stream()
	{
		return IntStream.of(src);
	}

	@Override
	public int get(int index)
	{
		return src[index];
	}

	@Override
	public int size()
	{
		return src.length;
	}

	public static IntVecImpl empty()
	{
		return EMPTY;
	}

	@Override
	public IntIterator revIter()
	{
		return Iter.reverseInts(src);
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