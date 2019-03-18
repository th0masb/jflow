/**
 * 
 */
package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
public final class IteratorImplUtils
{
	public static AbstractIteratorSize min(AbstractIteratorSize... sizes)
	{
		Exceptions.require(sizes.length > 0);
		int lo = Integer.MAX_VALUE;
		double hi = Double.POSITIVE_INFINITY;

		for (AbstractIteratorSize size : sizes) {
			switch (size.getType()) {
			case EXACT: {
				KnownSize x = (KnownSize) size;
				lo = Math.min(lo, x.getValue());
				hi = Math.min(hi, x.getValue());
				break;
			}
			case BOUNDED: {
				BoundedSize x = (BoundedSize) size;
				lo = Math.min(lo, x.lowerBound());
				hi = Math.min(hi, x.upperBound());
				break;
			}
			case LOWER_BOUND: {
				LowerBound x = (LowerBound) size;
				lo = Math.min(lo, x.getValue());
				break;
			}
			case UNKNOWN: {
				lo = 0;
				break;
			}
			case INFINITE:
				break;
			default:
				throw new AssertionError();
			}
		}
		// If lo was not changed we have no lower bound so set to 0.
		lo = lo == Integer.MAX_VALUE ? 0 : lo;
		return createSize(lo, hi);
	}

	public static void main(String[] args)
	{
		System.out.println(Double.NaN - 5);
		System.out.println(Math.min(Double.NaN, 5));
	}

	public static AbstractIteratorSize sum2(AbstractIteratorSize... sizes)
	{
		Exceptions.require(sizes.length > 0);
		int lo = 0;
		double hi = 0;
		for (AbstractIteratorSize size : sizes) {
			switch (size.getType()) {
			case EXACT: {
				KnownSize x = (KnownSize) size;
				lo += x.getValue();
				hi += x.getValue();
				break;
			}
			case BOUNDED: {
				BoundedSize x = (BoundedSize) size;
				lo += x.lowerBound();
				hi += x.upperBound();
				break;
			}
			case LOWER_BOUND: {
				LowerBound x = (LowerBound) size;
				lo += x.getValue();
				hi = Double.POSITIVE_INFINITY;
				break;
			}
			case UNKNOWN: {
				hi = Double.POSITIVE_INFINITY;
				break;
			}
			default:
				throw new AssertionError();
			}
		}

		return createSize(lo, hi);
	}

	public static AbstractIteratorSize sum(AbstractIteratorSize... sizes)
	{
		Exceptions.require(sizes.length > 0);
		int lo = 0;
		double hi = 0;
		for (AbstractIteratorSize size : sizes) {
			switch (size.getType()) {
			case EXACT: {
				KnownSize x = (KnownSize) size;
				lo += x.getValue();
				hi += x.getValue();
				break;
			}
			case BOUNDED: {
				BoundedSize x = (BoundedSize) size;
				lo += x.lowerBound();
				hi += x.upperBound();
				break;
			}
			case LOWER_BOUND: {
				LowerBound x = (LowerBound) size;
				lo += x.getValue();
				hi = Double.POSITIVE_INFINITY;
				break;
			}
			case UNKNOWN: {
				hi = Double.POSITIVE_INFINITY;
				break;
			}
			default:
				throw new AssertionError();
			}
		}

		return createSize(lo, hi);
	}

	static AbstractIteratorSize createSize(SizeType type, int lo, double hi)
	{
		throw new RuntimeException();
	}

	static AbstractIteratorSize createSize(int lowerBound, double upperBound)
	{
		if (lowerBound == upperBound) {
			return new KnownSize(lowerBound);
		} else if (Double.isFinite(upperBound)) {
			return new BoundedSize(lowerBound, (int) upperBound);
		} else if (Double.isInfinite(upperBound)) {
			return InfiniteSize.instance();
		} else {
			return new LowerBound(lowerBound);
		}
	}

	public static int requireNonNegative(int input)
	{
		if (input < 0) {
			throw new IllegalArgumentException("Non negative number is required");
		}
		return input;
	}

	static AbstractIteratorSize add(AbstractIteratorSize size, int addition)
	{
		switch (size.getType()) {
		case EXACT: {
			KnownSize x = (KnownSize) size;
			return new KnownSize(x.getValue() + addition);
		}
		case LOWER_BOUND: {
			LowerBound x = (LowerBound) size;
			return new LowerBound(x.getValue() + addition);
		}
		case BOUNDED: {
			BoundedSize x = (BoundedSize) size;
			return new BoundedSize(x.lowerBound() + addition, x.upperBound() + addition);
		}
		case UNKNOWN:
			return UnknownSize.instance();
		default:
			throw new RuntimeException();
		}
	}

	static AbstractIteratorSize subtract(AbstractIteratorSize size, int subtraction)
	{
		switch (size.getType()) {
		case EXACT: {
			KnownSize x = (KnownSize) size;
			return new KnownSize(Math.max(0, x.getValue() - subtraction));
		}
		case LOWER_BOUND: {
			LowerBound x = (LowerBound) size;
			return x.getValue() > subtraction ? new LowerBound(x.getValue() - subtraction)
					: UnknownSize.instance();
		}
		case BOUNDED: {
			BoundedSize x = (BoundedSize) size;
			int lo = x.lowerBound(), hi = x.upperBound();
			return hi > subtraction
					? new BoundedSize(Math.max(0, lo - subtraction), hi - subtraction)
					: new KnownSize(0);
		}
		case UNKNOWN:
			return UnknownSize.instance();
		default:
			throw new RuntimeException();
		}
	}

	public static AbstractIteratorSize min(AbstractIteratorSize size, int other)
	{
		switch (size.getType()) {
		case EXACT: {
			KnownSize x = (KnownSize) size;
			return new KnownSize(Math.min(x.getValue(), other));
		}
		case LOWER_BOUND: {
			LowerBound x = (LowerBound) size;
			return x.getValue() >= other ? new KnownSize(other)
					: new BoundedSize(x.getValue(), other);
		}
		case BOUNDED: {
			BoundedSize x = (BoundedSize) size;
			return x.lowerBound() >= other ? new KnownSize(other)
					: new BoundedSize(x.lowerBound(), Math.min(other, x.upperBound()));
		}
		case UNKNOWN:
			return new BoundedSize(0, other);
		default:
			throw new RuntimeException();
		}
	}

	public static AbstractIteratorSize dropLowerBound(AbstractIteratorSize size)
	{
		switch (size.getType()) {
		case EXACT: {
			KnownSize x = (KnownSize) size;
			return new BoundedSize(0, x.getValue());
		}
		case BOUNDED: {
			BoundedSize x = (BoundedSize) size;
			return new BoundedSize(0, x.upperBound());
		}
		case LOWER_BOUND:
		case UNKNOWN:
			return UnknownSize.instance();
		default:
			throw new RuntimeException();
		}
	}
}
