/**
 *
 */
package maumay.jflow.iterators.impl;

import static maumay.jflow.iterators.impl.ImplUtils.getSize;

import java.util.Iterator;
import java.util.PrimitiveIterator;

import maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.iterators.AbstractEnhancedLongIterator;
import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIntIterator;
import maumay.jflow.iterators.EnhancedIterator;
import maumay.jflow.iterators.EnhancedLongIterator;
import maumay.jflow.iterators.misc.Optionals;

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
			super(Optionals.add(source.size(), getSize(inserted)));
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

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator source;
		private final PrimitiveIterator.OfDouble inserted;

		public OfDouble(final EnhancedDoubleIterator source,
				final PrimitiveIterator.OfDouble inserted)
		{
			super(Optionals.add(source.size(), getSize(inserted)));
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

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator source;
		private final PrimitiveIterator.OfLong inserted;

		public OfLong(final EnhancedLongIterator source,
				final PrimitiveIterator.OfLong inserted)
		{
			super(Optionals.add(source.size(), getSize(inserted)));
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

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator source;
		private final PrimitiveIterator.OfInt inserted;

		public OfInt(final EnhancedIntIterator source,
				final PrimitiveIterator.OfInt inserted)
		{
			super(Optionals.add(source.size(), getSize(inserted)));
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
