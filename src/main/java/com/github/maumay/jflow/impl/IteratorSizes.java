/**
 * 
 */
package com.github.maumay.jflow.impl;

import static java.lang.Double.isFinite;
import static java.lang.Double.isNaN;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
public final class IteratorSizes
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
				hi = isNaN(hi) ? x.getValue() : Math.min(hi, x.getValue());
				break;
			}
			case BOUNDED: {
				BoundedSize x = (BoundedSize) size;
				lo = Math.min(lo, x.lower());
				hi = isNaN(hi) ? x.upper() : Math.min(hi, x.upper());
				break;
			}
			case LOWER_BOUND: {
				LowerBound x = (LowerBound) size;
				lo = Math.min(lo, x.getValue());
				hi = isFinite(hi) ? hi : Double.NaN;
				break;
			}
			case INFINITE:
				break;
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
		SUM_LOOP: for (AbstractIteratorSize size : sizes) {
			switch (size.getType()) {
			case EXACT: {
				KnownSize x = (KnownSize) size;
				lo += x.getValue();
				hi += x.getValue();
				break;
			}
			case BOUNDED: {
				BoundedSize x = (BoundedSize) size;
				lo += x.lower();
				hi += x.upper();
				break;
			}
			case LOWER_BOUND: {
				LowerBound x = (LowerBound) size;
				lo += x.getValue();
				hi = Double.isInfinite(hi) ? hi : Double.NaN;
				break;
			}
			case INFINITE: {
				hi = Double.POSITIVE_INFINITY;
				break SUM_LOOP;
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

	// Could probably move the fuctions below to abstract methods on
	// AbstractIteratorSize

	// static AbstractIteratorSize add(AbstractIteratorSize size, int addition)
	// {
	// switch (size.getType()) {
	// case EXACT: {
	// KnownSize x = (KnownSize) size;
	// return new KnownSize(x.getValue() + addition);
	// }
	// case LOWER_BOUND: {
	// LowerBound x = (LowerBound) size;
	// return new LowerBound(x.getValue() + addition);
	// }
	// case BOUNDED: {
	// BoundedSize x = (BoundedSize) size;
	// return new BoundedSize(x.lower() + addition, x.upper() + addition);
	// }
	// case INFINITE:
	// return size;
	// default:
	// throw new RuntimeException();
	// }
	// }
	//
	// static AbstractIteratorSize subtract(AbstractIteratorSize size, int
	// subtraction)
	// {
	// switch (size.getType()) {
	// case EXACT: {
	// KnownSize x = (KnownSize) size;
	// return new KnownSize(Math.max(0, x.getValue() - subtraction));
	// }
	// case LOWER_BOUND: {
	// LowerBound x = (LowerBound) size;
	// return new LowerBound(Math.max(0, x.getValue() - subtraction));
	// }
	// case BOUNDED: {
	// BoundedSize x = (BoundedSize) size;
	// int lo = x.lower(), hi = x.upper();
	// return hi > subtraction
	// ? new BoundedSize(Math.max(0, lo - subtraction), hi - subtraction)
	// : new KnownSize(0);
	// }
	// case INFINITE:
	// return size;
	// default:
	// throw new RuntimeException();
	// }
	// }
	//
	// public static AbstractIteratorSize min(AbstractIteratorSize size, int other)
	// {
	// switch (size.getType()) {
	// case EXACT: {
	// KnownSize x = (KnownSize) size;
	// return new KnownSize(Math.min(x.getValue(), other));
	// }
	// case LOWER_BOUND: {
	// LowerBound x = (LowerBound) size;
	// return x.getValue() >= other ? new KnownSize(other)
	// : new BoundedSize(x.getValue(), other);
	// }
	// case BOUNDED: {
	// BoundedSize x = (BoundedSize) size;
	// return x.lower() >= other ? new KnownSize(other)
	// : new BoundedSize(x.lower(), Math.min(other, x.upper()));
	// }
	// case INFINITE:
	// return new KnownSize(other);
	// default:
	// throw new RuntimeException();
	// }
	// }
	//
	// public static AbstractIteratorSize dropLowerBound(AbstractIteratorSize size)
	// {
	// switch (size.getType()) {
	// case EXACT: {
	// KnownSize x = (KnownSize) size;
	// return new BoundedSize(0, x.getValue());
	// }
	// case BOUNDED: {
	// BoundedSize x = (BoundedSize) size;
	// return new BoundedSize(0, x.upper());
	// }
	// case LOWER_BOUND:
	// case INFINITE:
	// return new LowerBound(0);
	// default:
	// throw new RuntimeException();
	// }
	// }
}
