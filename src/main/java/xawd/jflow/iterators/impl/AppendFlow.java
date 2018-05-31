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
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.Skippable;

/**
 * @author ThomasB
 *
 */
public final class AppendFlow
{
	private AppendFlow() {}

	public static class OfObject<E> extends AbstractFlow<E>
	{
		private final Flow<E> source;
		private final Iterator<? extends E> appended;

		public OfObject(final Flow<E> source, final Iterator<? extends E> appended)
		{
			this.source = source;
			this.appended = appended;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext() || appended.hasNext();
		}

		@Override
		public E next()
		{
			return source.hasNext()? source.next() : appended.next();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			}
			else if (appended instanceof Skippable) {
				((Skippable) appended).skip();
			}
			else {
				appended.next();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow source;
		private final PrimitiveIterator.OfDouble appended;

		public OfDouble(final DoubleFlow source, final PrimitiveIterator.OfDouble appended)
		{
			this.source = source;
			this.appended = appended;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext() || appended.hasNext();
		}

		@Override
		public double nextDouble()
		{
			return source.hasNext()? source.nextDouble() : appended.nextDouble();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			}
			else if (appended instanceof Skippable) {
				((Skippable) appended).skip();
			}
			else {
				appended.nextDouble();
			}
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow source;
		private final PrimitiveIterator.OfLong appended;

		public OfLong(final LongFlow source, final PrimitiveIterator.OfLong appended)
		{
			this.source = source;
			this.appended = appended;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext() || appended.hasNext();
		}

		@Override
		public long nextLong()
		{
			return source.hasNext()? source.nextLong() : appended.nextLong();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			}
			else if (appended instanceof Skippable) {
				((Skippable) appended).skip();
			}
			else {
				appended.nextLong();
			}
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow source;
		private final PrimitiveIterator.OfInt appended;

		public OfInt(final IntFlow source, final PrimitiveIterator.OfInt appended)
		{
			this.source = source;
			this.appended = appended;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext() || appended.hasNext();
		}

		@Override
		public int nextInt()
		{
			return source.hasNext()? source.nextInt() : appended.nextInt();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			}
			else if (appended instanceof Skippable) {
				((Skippable) appended).skip();
			}
			else {
				appended.nextInt();
			}
		}
	}
}
