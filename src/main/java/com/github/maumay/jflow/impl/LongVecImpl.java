/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Arrays;
import java.util.stream.LongStream;

import com.github.maumay.jflow.iterators.Iter;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.vec.LongVec;

/**
 * @author ThomasB
 *
 */
final class LongVecImpl implements LongVec
{
	private static final LongVecImpl EMPTY = new LongVecImpl(new long[0]);

	private final long[] data;

	LongVecImpl(long[] src)
	{
		this.data = src;
	}

	@Override
	public LongIterator iter()
	{
		return Iter.longs(data);
	}

	@Override
	public LongStream stream()
	{
		return LongStream.of(data);
	}

	@Override
	public long get(int index)
	{
		return data[index];
	}

	@Override
	public int size()
	{
		return data.length;
	}

	public static LongVecImpl empty()
	{
		return EMPTY;
	}

	@Override
	public LongIterator revIter()
	{
		return Iter.reverseLongs(data);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof LongVec) {
			LongVec other = (LongVec) obj;
			return size() == other.size()
					&& Iter.until(size()).all(i -> get(i) == other.get(i));
		} else {
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return Arrays.hashCode(data);
	}

	@Override
	public String toString()
	{
		return Arrays.toString(data);
	}
}