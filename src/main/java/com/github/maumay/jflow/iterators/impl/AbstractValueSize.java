/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.Objects;

/**
 * @author thomasb
 *
 */
public abstract class AbstractValueSize extends AbstractIteratorSize
{
	private int value;

	public AbstractValueSize(SizeType type, int value)
	{
		super(type);
		this.value = requireNonNegative(value);
	}

	public int getValue()
	{
		return value;
	}

	@Override
	void decrement()
	{
		value = Math.max(0, value - 1);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof AbstractValueSize) {
			AbstractValueSize other = (AbstractValueSize) obj;
			return value == other.value && getType() == other.getType();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(value, getType());
	}
}
