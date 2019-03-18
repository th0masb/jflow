/**
 * 
 */
package com.github.maumay.jflow.impl;

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

	// Do we actually need these three methods?
	public abstract OptionalInt getExactSize();

	public abstract OptionalInt getMinimalUpperBound();

	public abstract OptionalInt getMaximalLowerBound();

	public abstract AbstractIteratorSize copy();

	public abstract boolean isSingleton();

	abstract void decrement();
}
