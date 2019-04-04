/**
 * 
 */
package com.github.maumay.jflow.impl;

/**
 * Abstract superclass of all four implementations of possible iterator sizes.
 * 
 * @author thomasb
 */
public abstract class AbstractIteratorSize
{
	/**
	 * The type associated with this class.
	 */
	private final SizeType type;

	protected AbstractIteratorSize(SizeType type)
	{
		this.type = type;
	}

	/**
	 * Retrieve the type of this size.
	 * 
	 * @return The type of this size.
	 */
	public SizeType getType()
	{
		return type;
	}

	/**
	 * Adds a non-negative integer to this size. Throws an exception if the
	 * parameter is negative.
	 * 
	 * @param value A natural number.
	 * @return The result of adding some natural number to this size.
	 */
	public final AbstractIteratorSize add(int value)
	{
		return addImpl(IteratorSizes.requireNonNegative(value));
	}

	/**
	 * Subtracts a non-negative number from this size. Throws an exception if the
	 * parameter is negative.
	 * 
	 * @param value A natural number.
	 * @return The result of subtracting some natural number from this size.
	 */
	public final AbstractIteratorSize subtract(int value)
	{
		return subtractImpl(IteratorSizes.requireNonNegative(value));
	}

	/**
	 * Computes the minimum of this size and a natural number which is treated as an
	 * exact size. Throws an exception if the parameter is negative.
	 * 
	 * @param value A natural number.
	 * @return The minimum of this and the given number.
	 */
	public final AbstractIteratorSize min(int value)
	{
		return minImpl(IteratorSizes.requireNonNegative(value));
	}

	/**
	 * Multiplies this size by a non-negative number. Throws an exception if the
	 * parameter is negative.
	 * 
	 * @param value A natural number
	 * @return The product of this and the given number.
	 */
	public final AbstractIteratorSize times(int value)
	{
		return timesImpl(value);
	}

	/**
	 * Computes a size which represents a size ranging from 0 to this size.
	 * 
	 * @return see above.
	 */
	public abstract AbstractIteratorSize dropLowerBound();

	/**
	 * Creates a copy of this size.
	 * 
	 * @return A copy of this size.
	 */
	public abstract AbstractIteratorSize copy();

	/**
	 * Flag to determine if this is the singleton instance of the class representing
	 * infinite size.
	 * 
	 * @return Whether this size is infinite.
	 */
	public final boolean isInfinite()
	{
		return type == SizeType.INFINITE;
	}

	// Hidden methods
	abstract AbstractIteratorSize addImpl(int value);

	abstract AbstractIteratorSize subtractImpl(int value);

	abstract AbstractIteratorSize minImpl(int value);

	abstract AbstractIteratorSize timesImpl(int value);

	/**
	 * Mutate this size in place by subtracting one from it.
	 */
	abstract void decrement();
}
