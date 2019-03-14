/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
final class IteratorSizeUtils
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
			case UPPER_BOUND: {
				UpperBound x = (UpperBound) size;
				hi = Math.min(hi, x.getValue());
				lo = 0;
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
			}
			case UPPER_BOUND: {
				UpperBound x = (UpperBound) size;
				hi += x.getValue();
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
		boolean finiteUpper = Double.isFinite(upperBound);
		if (lowerBound == upperBound) {
			return new KnownSize(lowerBound);
		} else if (lowerBound == 0) {
			if (finiteUpper) {
				return new UpperBound((int) upperBound);
			} else {
				return UnknownSize.instance();
			}
		} else if (finiteUpper) {
			return new BoundedSize(lowerBound, (int) upperBound);
		} else {
			return new LowerBound(lowerBound);
		}
	}
}
