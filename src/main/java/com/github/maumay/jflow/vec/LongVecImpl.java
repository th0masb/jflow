/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.stream.LongStream;

import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.factories.Iter;

/**
 * @author ThomasB
 *
 */
final class LongVecImpl implements LongVec
{
	private static final LongVecImpl EMPTY = new LongVecImpl(new long[0]);

	private final long[] src;

	LongVecImpl(long[] src)
	{
		this.src = src;
	}

	@Override
	public LongIterator iter()
	{
		return Iter.longs(src);
	}

	@Override
	public LongStream stream()
	{
		return LongStream.of(src);
	}

	@Override
	public long get(int index)
	{
		return src[index];
	}

	@Override
	public int size()
	{
		return src.length;
	}

	public static LongVecImpl empty()
	{
		return EMPTY;
	}

	@Override
	public LongIterator revIter()
	{
		return Iter.reverseLongs(src);
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