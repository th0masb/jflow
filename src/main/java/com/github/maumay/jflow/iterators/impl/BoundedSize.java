/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

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
		lo = Math.max(0, lo - 1);
		hi--;
	}

	@Override
	public OptionalInt getUpperBound()
	{
		return Option.of(hi);
	}

	@Override
	public OptionalInt getLowerBound()
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
}
