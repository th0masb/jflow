/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.NoSuchElementException;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;

/**
 * @author t
 */
public final class FlowFromValues
{
	private FlowFromValues() {}

	public static class OfObject<E> extends AbstractFlow<E>
	{
		private final Object[] cache;
		private int count = 0;

		@SafeVarargs
		public OfObject(E... es)
		{
			super(es.length);
			this.cache = es;
		}

//		public OfObject(Collection<? extends E> src)
//		{
//			super(src.size());
//			this.cache = src.toArray();
//		}

		@Override
		public boolean hasNext()
		{
			return count < size;
		}
		@SuppressWarnings("unchecked")
		@Override
		public E next()
		{
			if (hasNext()) {
				return (E) cache[count++];
			}
			else {
				throw new NoSuchElementException();
			}
		}
		@Override
		public void skip()
		{
			if (count++ >= size) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final long[] cache;
		private int count;

		public OfLong(final long...src)
		{
			super(src.length);
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < size;
		}
		@Override
		public long nextLong()
		{
			if (hasNext()) {
				return cache[count++];
			}
			else {
				throw new NoSuchElementException();
			}
		}
		@Override
		public void skip()
		{
			if (count++ >= size) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final double[] cache;
		private int count;

		public OfDouble(final double...src)
		{
			super(src.length);
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < size;
		}
		@Override
		public double nextDouble()
		{
			if (hasNext()) {
				return cache[count++];
			}
			else {
				throw new NoSuchElementException();
			}
		}
		@Override
		public void skip()
		{
			if (count++ >= size) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final int[] cache;
		private int count;

		public OfInt(final int...src)
		{
			super(src.length);
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < size;
		}
		@Override
		public int nextInt()
		{
			if (hasNext()) {
				return cache[count++];
			}
			else {
				throw new NoSuchElementException();
			}
		}
		@Override
		public void skip()
		{
			if (count++ >= size) {
				throw new NoSuchElementException();
			}
		}
	}
}
