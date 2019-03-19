/**
 * 
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public abstract class AbstractValueSize extends AbstractIteratorSize
{
	private int value;

	protected AbstractValueSize(SizeType type, int value)
	{
		super(type);
		this.value = value;
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
	public boolean isSingleton()
	{
		return false;
	}

	// @Override
	// public boolean equals(Object obj)
	// {
	// if (obj instanceof AbstractValueSize) {
	// AbstractValueSize other = (AbstractValueSize) obj;
	// return value == other.value && getType() == other.getType();
	// } else {
	// return false;
	// }
	// }
	//
	// @Override
	// public int hashCode()
	// {
	// return Objects.hash(value, getType());
	// }

	@Override
	public String toString()
	{
		return new StringBuilder(getType().name()).append("(").append(value).append(")").toString();
	}
}
