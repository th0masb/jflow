/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Objects;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
public final class BoundedSize extends AbstractIteratorSize
{
	private int lo, hi;

	BoundedSize(int lo, int hi)
	{
		super(SizeType.BOUNDED);
		this.lo = lo;
		this.hi = hi;
	}

	public static BoundedSize of(int lo, int hi)
	{
		lo = IteratorSizes.requireNonNegative(lo);
		hi = IteratorSizes.requireNonNegative(hi);
		Exceptions.require(lo <= hi);
		return new BoundedSize(lo, hi);
	}

	public int upper()
	{
		return hi;
	}

	public int lower()
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
	AbstractIteratorSize addImpl(int value)
	{
		return new BoundedSize(lo + value, hi + value);
	}

	@Override
	AbstractIteratorSize subtractImpl(int value)
	{
		return value >= hi ? new KnownSize(0)
				: new BoundedSize(Math.max(0, lo - value), hi - value);
	}

	@Override
	AbstractIteratorSize minImpl(int value)
	{
		return lo >= value ? new KnownSize(value)
				: new BoundedSize(lo, Math.min(hi, value));
	}

	@Override
	public AbstractIteratorSize filter()
	{
		return new BoundedSize(0, hi);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof BoundedSize) {
			BoundedSize other = (BoundedSize) obj;
			return lo == other.lo && hi == other.hi;
		} else if (obj instanceof KnownSize) {
			KnownSize other = (KnownSize) obj;
			return lo == other.getValue() && hi == other.getValue();
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
		return new BoundedSize(lo, hi);
	}

	@Override
	public String toString()
	{
		return new StringBuilder("BOUNDED(").append(lo).append(", ").append(hi)
				.append(")").toString();
	}
}
