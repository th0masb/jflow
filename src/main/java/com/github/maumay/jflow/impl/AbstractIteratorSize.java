/**
 * 
 */
package com.github.maumay.jflow.impl;

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

	public final AbstractIteratorSize add(int value)
	{
		return addImpl(IteratorSizes.requireNonNegative(value));
	}

	public final AbstractIteratorSize subtract(int value)
	{
		return subtractImpl(IteratorSizes.requireNonNegative(value));
	}

	public final AbstractIteratorSize min(int value)
	{
		return minImpl(IteratorSizes.requireNonNegative(value));
	}

	abstract AbstractIteratorSize addImpl(int value);

	abstract AbstractIteratorSize subtractImpl(int value);

	abstract AbstractIteratorSize minImpl(int value);

	public abstract AbstractIteratorSize filter();

	public abstract AbstractIteratorSize copy();

	public abstract boolean isSingleton();

	abstract void decrement();
}
