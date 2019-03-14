/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.OptionalInt;

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

	public SizeType getType()
	{
		return type;
	}

	public abstract OptionalInt getExactSize();

	public abstract OptionalInt getUpperBound();

	public abstract OptionalInt getLowerBound();

	abstract void decrement();
}
