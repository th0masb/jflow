/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.Arrays;
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

	private final int[] data;

	IntVecImpl(int[] src)
	{
		this.data = src;
	}

	@Override
	public IntIterator iter()
	{
		return Iter.ints(data);
	}

	@Override
	public IntStream stream()
	{
		return IntStream.of(data);
	}

	@Override
	public int get(int index)
	{
		return data[index];
	}

	@Override
	public int size()
	{
		return data.length;
	}

	public static IntVecImpl empty()
	{
		return EMPTY;
	}

	@Override
	public IntIterator revIter()
	{
		return Iter.reverseInts(data);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof IntVec) {
			IntVec other = (IntVec) obj;
			return size() == other.size()
					&& Iter.until(size()).allMatch(i -> get(i) == other.get(i));
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