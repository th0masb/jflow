package xawd.jflow.iterators.construction;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

/**
 * Factory for building Flows of common number sets.
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
	public static IntFlow natural()
	{
		return IterFunction.intsFrom(i -> i);
	}

	/**
	 * Iterates over the Fibonacci numbers.
	 *
	 * @return A sorted iteration of the Fibonacci numbers with beginning values 1,
	 *         1.
	 */
	public static LongFlow fibonacci()
	{
		return fibonacci(1, 1);
	}

	/**
	 * Iterates over the Fibonacci numbers with custom starting values.
	 * @param lowStart
	 * @param highStart
	 * @return
	 */
	public static LongFlow fibonacci(final int lowStart, final int highStart)
	{
		return new AbstractLongFlow() {
			long x1 = lowStart, x2 = highStart;
			int count = 0;

			@Override
			public void skip()
			{
				nextLong();
			}

			@Override
			public boolean hasNext()
			{
				return true;
			}

			@Override
			public long nextLong()
			{
				if (count < 2) {
					count++;
					return count == 1 ? lowStart : highStart;
				} else {
					final long nextFib = x1 + x2;
					x1 = x2;
					x2 = nextFib;
					return nextFib;
				}
			}
		};
	}
}
