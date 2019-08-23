package com.github.maumay.jflow.iterators;

import com.github.maumay.jflow.impl.FibonnaciIterator;

/**
 * Static methods for creating some infinite number sets.
 *
 * @author ThomasB
 */
public final class Numbers
{
	private Numbers()
	{
	}

	/**
	 * Iterates over the natural numbers.
	 *
	 * @return A sorted iteration of the non-negative integers (natural numbers).
	 */
	public static IntIterator natural()
	{
		return Iter.indexInts(i -> i);
	}

	/**
	 * Iterates over the Fibonacci numbers.
	 *
	 * @return A sorted iteration of the Fibonacci numbers with beginning values 1,
	 *         1.
	 */
	public static LongIterator fibonacci()
	{
		return fibonacci(1, 1);
	}

	/**
	 * Iterates over the Fibonacci numbers with custom starting values.
	 *
	 * @param first  The first start val
	 * @param second The second start val
	 * @return The Fibonnaci numbers
	 */
	public static LongIterator fibonacci(int first, int second)
	{
		return new FibonnaciIterator(first, second);
	}
}
