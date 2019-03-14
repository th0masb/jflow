/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

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
}
