/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

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
		value--;
	}
}
