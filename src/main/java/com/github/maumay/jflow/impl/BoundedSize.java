/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Objects;
import java.util.OptionalInt;

import com.github.maumay.jflow.utils.Exceptions;
import com.github.maumay.jflow.utils.Option;

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
		this.lo = IteratorImplUtils.requireNonNegative(lo);
		this.hi = IteratorImplUtils.requireNonNegative(hi);
		Exceptions.require(lo <= hi);
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
		lo = Math.max(0, lo - 1);
		hi--;
	}

	@Override
	public OptionalInt getMinimalUpperBound()
	{
		return Option.of(hi);
	}

	@Override
	public OptionalInt getMaximalLowerBound()
	{
		return Option.of(lo);
	}

	@Override
	public OptionalInt getExactSize()
	{
		return OptionalInt.empty();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof BoundedSize) {
			BoundedSize other = (BoundedSize) obj;
			return lo == other.lo && hi == other.hi;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(lo, hi);
	}

	@Override
	public BoundedSize copy()
	{
		return new BoundedSize(lowerBound(), upperBound());
	}

	@Override
	public boolean isSingleton()
	{
		return false;
	}

}
