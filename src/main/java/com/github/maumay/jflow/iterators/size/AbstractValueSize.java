/**
 * 
 */
package com.github.maumay.jflow.iterators.size;

import com.github.maumay.jflow.utils.Exceptions;

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

	static int requireNonNegative(int input)
	{
		Exceptions.require(input >= 0);
		return input;
	}

	public int getValue()
	{
		return value;
	}

	public void decrement()
	{
		value--;
	}
}
