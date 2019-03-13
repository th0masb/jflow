/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
public final class BoundedSize extends AbstractIteratorSize
{
	private int lo, hi;

	public BoundedSize(int lo, int hi)
	{
		super(SizeType.BOUNDED);
		this.lo = requireNonNegative(lo);
		this.hi = requireNonNegative(hi);
		Exceptions.require(lo < hi);
	}

	public int upperBound()
	{
		return hi;
	}

	public int lowerBound()
	{
		return lo;
	}

	@Override
	void decrement()
	{
		lo--;
		hi--;
	}
}
