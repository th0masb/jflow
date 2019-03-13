/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import static com.github.maumay.jflow.iterators.impl.ImplUtils.getSize;

import java.util.Iterator;
import java.util.PrimitiveIterator;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;
import com.github.maumay.jflow.utils.Option;

/**
 * @author ThomasB
 */
public final class InsertIterator
{
	private InsertIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		private final EnhancedIterator<E> source;
		private final Iterator<? extends E> inserted;

		public OfObject(final EnhancedIterator<E> source,
				final Iterator<? extends E> inserted)
		{
			super(Option.add(source.size(), getSize(inserted)));
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
			return inserted.hasNext() ? inserted.next() : source.next();
		}

		@Override
		public void skip()
		{
			if (inserted.hasNext()) {
				ImplUtils.skip(inserted);
			} else {
				source.skip();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final DoubleIterator source;
		private final PrimitiveIterator.OfDouble inserted;

		public OfDouble(final DoubleIterator source,
				final PrimitiveIterator.OfDouble inserted)
		{
			super(Option.add(source.size(), getSize(inserted)));
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
			return inserted.hasNext() ? inserted.nextDouble() : source.nextDouble();
		}

		@Override
		public void skip()
		{
			if (inserted.hasNext()) {
				ImplUtils.skip(inserted);
			} else {
				source.skip();
			}
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final LongIterator source;
		private final PrimitiveIterator.OfLong inserted;

		public OfLong(final LongIterator source,
				final PrimitiveIterator.OfLong inserted)
		{
			super(Option.add(source.size(), getSize(inserted)));
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
			return inserted.hasNext() ? inserted.nextLong() : source.nextLong();
		}

		@Override
		public void skip()
		{
			if (inserted.hasNext()) {
				ImplUtils.skip(inserted);
			} else {
				source.skip();
			}
		}
	}

	public static class OfInt extends AbstractIntIterator
	{
		private final IntIterator source;
		private final PrimitiveIterator.OfInt inserted;

		public OfInt(final IntIterator source,
				final PrimitiveIterator.OfInt inserted)
		{
			super(Option.add(source.size(), getSize(inserted)));
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
			return inserted.hasNext() ? inserted.nextInt() : source.nextInt();
		}

		@Override
		public void skip()
		{
			if (inserted.hasNext()) {
				ImplUtils.skip(inserted);
			} else {
				source.skip();
			}
		}
	}
}
