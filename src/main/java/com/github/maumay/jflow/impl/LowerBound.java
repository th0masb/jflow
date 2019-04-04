/**
 * 
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public final class LowerBound extends AbstractValueSize
{
	LowerBound(int size)
	{
		super(SizeType.LOWER_BOUND, size);
	}

	public static LowerBound of(int value)
	{
		return new LowerBound(IteratorSizes.requireNonNegative(value));
	}

	@Override
	public LowerBound copy()
	{
		return new LowerBound(getValue());
	}

	@Override
	AbstractIteratorSize addImpl(int value)
	{
		return new LowerBound(getValue() + value);
	}

	@Override
	AbstractIteratorSize subtractImpl(int value)
	{
		return new LowerBound(Math.max(0, getValue() - value));
	}

	@Override
	AbstractIteratorSize minImpl(int value)
	{
		return getValue() >= value ? new KnownSize(value)
				: new BoundedSize(getValue(), value);
	}

	@Override
	public AbstractIteratorSize dropLowerBound()
	{
		return new LowerBound(0);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof LowerBound) {
			LowerBound other = (LowerBound) obj;
			return getValue() == other.getValue();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return getValue();
	}

	@Override
	AbstractIteratorSize timesImpl(int value)
	{
		return new LowerBound(getValue() * value);
	}
}
