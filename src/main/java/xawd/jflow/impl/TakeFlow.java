package xawd.jflow.impl;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.DoubleFlow;
import xawd.jflow.Flow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;

/**
 * @author ThomasB
 * @since 25 Apr 2018
 */
public final class TakeFlow
{
	private TakeFlow() {}

	public static class OfObject<T> extends AbstractFlow<T>
	{
		private final Flow<T> src;
		private final int takeCount;

		private int count = 0;

		public OfObject(final Flow<T> src, final int takeCount)
		{
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public T next()
		{
			count++;
			return src.next();
		}

		@Override
		public void skip()
		{
			count++;
			src.skip();
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow src;
		private final int takeCount;

		private int count = 0;

		public OfLong(final LongFlow src, final int takeCount)
		{
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public long nextLong()
		{
			count++;
			return src.next();
		}

		@Override
		public void skip()
		{
			count++;
			src.skip();
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow src;
		private final int takeCount;

		private int count = 0;

		public OfInt(final IntFlow src, final int takeCount)
		{
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public int nextInt()
		{
			count++;
			return src.next();
		}

		@Override
		public void skip()
		{
			count++;
			src.skip();
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow src;
		private final int takeCount;

		private int count = 0;

		public OfDouble(final DoubleFlow src, final int takeCount)
		{
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public double nextDouble()
		{
			count++;
			return src.next();
		}

		@Override
		public void skip()
		{
			count++;
			src.skip();
		}
	}
}
