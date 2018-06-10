/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Iterator;
import java.util.PrimitiveIterator;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;

/**
 * @author t
 *
 */
public final class FlowFromIterator
{
	private FlowFromIterator() {}

	public static class OfObject<E> extends AbstractFlow<E>
	{
		private final Iterator<? extends E> iterator;

		public OfObject(final Iterator<? extends E> src)
		{
			this.iterator = src;
		}

		@Override
		public boolean hasNext()
		{
			return iterator.hasNext();
		}

		@Override
		public E next()
		{
			return iterator.next();
		}

		@Override
		public void skip()
		{
			next();
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final PrimitiveIterator.OfDouble source;

		public OfDouble(PrimitiveIterator.OfDouble source)
		{
			this.source = source;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext();
		}

		@Override
		public double nextDouble()
		{
			return source.nextDouble();
		}

		@Override
		public void skip()
		{
			nextDouble();
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final PrimitiveIterator.OfLong source;

		public OfLong(PrimitiveIterator.OfLong source)
		{
			this.source = source;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext();
		}

		@Override
		public long nextLong()
		{
			return source.nextLong();
		}

		@Override
		public void skip()
		{
			nextLong();
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final PrimitiveIterator.OfInt source;

		public OfInt(PrimitiveIterator.OfInt source)
		{
			this.source = source;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext();
		}

		@Override
		public int nextInt()
		{
			return source.nextInt();
		}

		@Override
		public void skip()
		{
			nextInt();
		}
	}
}
