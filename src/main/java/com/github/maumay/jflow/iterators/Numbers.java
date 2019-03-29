package com.github.maumay.jflow.iterators;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.impl.InfiniteSize;

/**
 * Static methods for creating some infinite number sets.
 *
 * @author ThomasB
 */
public final class Numbers
{
	/**
	 * Iterates over the natural numbers.
	 *
	 * @return A sorted iteration of the non-negative integers (natural numbers).
	 */
	public static IntIterator natural()
	{
		return Repeatedly.indexInts(i -> i);
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
	public static LongIterator fibonacci(final int first, final int second)
	{
		return new AbstractLongIterator(InfiniteSize.instance()) {
			long x1 = first, x2 = second;
			int count = 0;

			@Override
			public void skipImpl()
			{
				nextLong();
			}

			@Override
			public boolean hasNext()
			{
				return true;
			}

			@Override
			public long nextLongImpl()
			{
				if (count < 2) {
					count++;
					return count == 1 ? first : second;
				} else {
					long nextFib = Math.addExact(x1, x2);
					x1 = x2;
					x2 = nextFib;
					return nextFib;
				}
			}
		};
	}
}
