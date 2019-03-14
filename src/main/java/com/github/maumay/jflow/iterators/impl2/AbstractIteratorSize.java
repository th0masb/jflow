/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import java.util.OptionalInt;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIteratorSize
{
	private final SizeType type;

	public AbstractIteratorSize(SizeType type)
	{
		this.type = type;
	}

	static int requireNonNegative(int input)
	{
		Exceptions.require(input >= 0);
		return input;
	}

	public SizeType getType()
	{
		return type;
	}

	public abstract OptionalInt getExactSize();

	public abstract OptionalInt getUpperBound();

	public abstract OptionalInt getLowerBound();

	abstract void decrement();

	public static AbstractIteratorSize sum(AbstractIteratorSize... sizes)
	{
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

		boolean finiteUpper = Double.isFinite(hi);
		if (lo == hi) {
			return new KnownSize(lo);
		} else if (lo == 0) {
			if (finiteUpper) {
				return new UpperBound((int) hi);
			} else {
				return UnknownSize.instance();
			}
		} else if (finiteUpper) {
			return new BoundedSize(lo, (int) hi);
		} else {
			return new LowerBound(lo);
		}
	}

	public static void main(String[] args)
	{
		double x = Double.POSITIVE_INFINITY;
		x += 5;
		System.out.println(x);
	}
}
