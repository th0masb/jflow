package xawd.jflow.iterators.construction;

import static xawd.jflow.utilities.CollectionUtil.string;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

/**
 * @author ThomasB
 */
public final class Numbers
{
	public static IntFlow natural()
	{
		return IterFunction.intsFrom(i -> i);
	}

	public static LongFlow fibonacci()
	{
		return fibonacci(1, 1);
	}

	public static LongFlow fibonacci(final int lowStart, final int highStart)
	{
		return new AbstractLongFlow() {
			long x1 = lowStart, x2 = highStart;
			int count = 0;
			@Override
			public void skip() {
				nextLong();
			}
			@Override
			public boolean hasNext(){
				return true;
			}
			@Override
			public long nextLong()
			{
				if (count < 2) {
					count++;
					return count == 1? lowStart : highStart;
				}
				else {
					final long nextFib = x1 + x2;
					x1 = x2;
					x2 = nextFib;
					return nextFib;
				}
			}
		};
	}

	public static void main(final String[] args)
	{
		System.out.println(string(fibonacci().take(60).toArray()));
	}
}

