package xawd.jflow.construction;

import static xawd.jchain.chains.utilities.CollectionUtil.str;

import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;

public final class Numbers
{
	public static IntFlow natural()
	{
		return Iter.intsFrom(i -> i);
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
				next();
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

	public static IntFlow primes()
	{
		return new AbstractIntFlow() {

			@Override
			public void skip()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public boolean hasNext()
			{
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int nextInt()
			{
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

	public static void main(final String[] args)
	{
		System.out.println(str(fibonacci().take(60).toArray()));
	}
}

