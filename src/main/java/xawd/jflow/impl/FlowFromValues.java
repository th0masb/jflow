/**
 *
 */
package xawd.jflow.impl;

import java.util.Iterator;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;

/**
 * @author t
 *
 */
public final class FlowFromValues
{
	private FlowFromValues() {}

	public static class OfObject<T> extends AbstractFlow<T>
	{
		private final Iterator<T> src;

		public OfObject(final Iterable<T> src)
		{
			this.src = src.iterator();
		}

		@Override
		public boolean hasNext()
		{
			return src.hasNext();
		}
		@Override
		public T next()
		{
			return src.next();
		}
		@Override
		public void skip()
		{
			src.next();
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final long[] cache;
		private int count;

		public OfLong(final long...src)
		{
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < cache.length;
		}
		@Override
		public long nextLong()
		{
			return cache[count++];
		}
		@Override
		public void skip()
		{
			count++;
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final double[] cache;
		private int count;

		public OfDouble(final double...src)
		{
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < cache.length;
		}
		@Override
		public double nextDouble()
		{
			return cache[count++];
		}
		@Override
		public void skip()
		{
			count++;
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final int[] cache;
		private int count;

		public OfInt(final int...src)
		{
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < cache.length;
		}
		@Override
		public int nextInt()
		{
			return cache[count++];
		}
		@Override
		public void skip()
		{
			count++;
		}
	}
}
