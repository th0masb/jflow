/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
final class IteratorImplUtils
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
			default:
				throw new AssertionError();
			}
		}
		// If lo was not changed we have no lower bound so set to 0.
		lo = lo == Integer.MAX_VALUE ? 0 : lo;
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

	static AbstractIteratorSize createSize(int lowerBound, double upperBound)
	{
		if (lowerBound == upperBound) {
			return new KnownSize(lowerBound);
		} else if (Double.isFinite(upperBound)) {
			return new BoundedSize(lowerBound, (int) upperBound);
		} else if (lowerBound == 0) {
			return UnknownSize.instance();
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

	static AbstractIteratorSize subtract(AbstractIteratorSize size, int subtraction)
	{
		switch (size.getType()) {
		case EXACT: {
			KnownSize x = (KnownSize) size;
			return new KnownSize(Math.max(0, x.getValue() - subtraction));
		}
		case LOWER_BOUND: {
			LowerBound x = (LowerBound) size;
			return x.getValue() >= subtraction ? new KnownSize(subtraction)
					: new BoundedSize(x.getValue(), subtraction);
		}
		case BOUNDED: {
			BoundedSize x = (BoundedSize) size;
			return x.lowerBound() >= subtraction ? new KnownSize(subtraction)
					: new BoundedSize(x.lowerBound(), Math.min(subtraction, x.upperBound()));
		}
		case UNKNOWN:
			return new BoundedSize(0, subtraction);
		default:
			throw new RuntimeException();
		}
	}
}
