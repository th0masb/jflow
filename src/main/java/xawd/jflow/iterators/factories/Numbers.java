package xawd.jflow.iterators.factories;

import java.util.OptionalInt;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

/**
 * Static methods for building Flows of common number sets.
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
		return Repeatedly.indexInts(i -> i);
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
	 *
	 * @param first
	 *            The first start val
	 * @param second
	 *            The second start val
	 * @return The Fibonnaci numbers
	 */
	public static LongFlow fibonacci(final int first, final int second)
	{
		return new AbstractLongFlow(OptionalInt.empty()) {
			long x1 = first, x2 = second;
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
					return count == 1 ? first : second;
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
