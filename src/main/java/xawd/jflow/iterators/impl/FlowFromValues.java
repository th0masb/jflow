/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

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
			super(OptionalInt.of(es.length));
			this.cache = es;
		}

		public OfObject(Object[] src, Class<E> cls)
		{
			super(OptionalInt.of(src.length));
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < size.getAsInt();
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
			if (count++ >= size.getAsInt()) {
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
			super(OptionalInt.of(src.length));
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < size.getAsInt();
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
			if (count++ >= size.getAsInt()) {
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
			super(OptionalInt.of(src.length));
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < size.getAsInt();
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
			if (count++ >= size.getAsInt()) {
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
			super(OptionalInt.of(src.length));
			this.cache = src;
		}

		@Override
		public boolean hasNext()
		{
			return count < size.getAsInt();
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
			if (count++ >= size.getAsInt()) {
				throw new NoSuchElementException();
			}
		}
	}
}
