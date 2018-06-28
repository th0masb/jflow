/**
 *
 */
package xawd.jflow.iterators.impl;

import static xawd.jflow.iterators.impl.ImplUtils.getSize;
import static xawd.jflow.iterators.impl.ImplUtils.isSized;

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
 */
public final class InsertFlow
{
	private InsertFlow() {}

	public static class OfObject<E> extends AbstractFlow<E>
	{
		private final Flow<E> source;
		private final Iterator<? extends E> inserted;

		public OfObject(final Flow<E> source, final Iterator<? extends E> inserted)
		{
			super(isSized(source) && isSized(inserted)? source.size() + getSize(inserted) : -1);
			this.source = source;
			this.inserted = inserted;
		}

		@Override
		public boolean hasNext()
		{
			return inserted.hasNext() || source.hasNext();
		}

		@Override
		public E next()
		{
			return inserted.hasNext()? inserted.next() : source.next();
		}

		@Override
		public void skip()
		{
			if (inserted.hasNext()) {
				ImplUtils.skip(inserted);
			}
			else {
				source.skip();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow source;
		private final PrimitiveIterator.OfDouble inserted;

		public OfDouble(final DoubleFlow source, final PrimitiveIterator.OfDouble inserted)
		{
			this.source = source;
			this.inserted = inserted;
		}

		@Override
		public boolean hasNext()
		{
			return inserted.hasNext() || source.hasNext();
		}

		@Override
		public double nextDouble()
		{
			return inserted.hasNext()? inserted.nextDouble() : source.nextDouble();
		}

		@Override
		public void skip()
		{
			if (inserted.hasNext()) {
				if (inserted instanceof Skippable) {
					((Skippable) inserted).skip();
				}
				else {
					inserted.nextDouble();
				}
			}
			else {
				source.skip();
			}
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow source;
		private final PrimitiveIterator.OfLong inserted;

		public OfLong(final LongFlow source, final PrimitiveIterator.OfLong inserted)
		{
			this.source = source;
			this.inserted = inserted;
		}

		@Override
		public boolean hasNext()
		{
			return inserted.hasNext() || source.hasNext();
		}

		@Override
		public long nextLong()
		{
			return inserted.hasNext()? inserted.nextLong() : source.nextLong();
		}

		@Override
		public void skip()
		{
			if (inserted.hasNext()) {
				if (inserted instanceof Skippable) {
					((Skippable) inserted).skip();
				}
				else {
					inserted.nextLong();
				}
			}
			else {
				source.skip();
			}
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow source;
		private final PrimitiveIterator.OfInt inserted;

		public OfInt(final IntFlow source, final PrimitiveIterator.OfInt inserted)
		{
			this.source = source;
			this.inserted = inserted;
		}

		@Override
		public boolean hasNext()
		{
			return inserted.hasNext() || source.hasNext();
		}

		@Override
		public int nextInt()
		{
			return inserted.hasNext()? inserted.nextInt() : source.nextInt();
		}

		@Override
		public void skip()
		{
			if (inserted.hasNext()) {
				if (inserted instanceof Skippable) {
					((Skippable) inserted).skip();
				}
				else {
					inserted.nextInt();
				}
			}
			else {
				source.skip();
			}
		}
	}
}
