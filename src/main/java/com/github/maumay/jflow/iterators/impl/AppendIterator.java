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
 *
 */
public final class AppendIterator
{
	private AppendIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		private final EnhancedIterator<E> source;
		private final Iterator<? extends E> appended;

		public OfObject(final EnhancedIterator<E> source,
				final Iterator<? extends E> appended)
		{
			super(Option.add(source.size(), getSize(appended)));
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
			return source.hasNext() ? source.next() : appended.next();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			} else {
				ImplUtils.skip(appended);
			}
		}
	}

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final DoubleIterator source;
		private final PrimitiveIterator.OfDouble appended;

		public OfDouble(final DoubleIterator source,
				final PrimitiveIterator.OfDouble appended)
		{
			super(Option.add(source.size(), getSize(appended)));
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
			return source.hasNext() ? source.nextDouble() : appended.nextDouble();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			} else {
				ImplUtils.skip(appended);
			}
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final LongIterator source;
		private final PrimitiveIterator.OfLong appended;

		public OfLong(final LongIterator source,
				final PrimitiveIterator.OfLong appended)
		{
			super(Option.add(source.size(), getSize(appended)));
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
			return source.hasNext() ? source.nextLong() : appended.nextLong();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			} else {
				ImplUtils.skip(appended);
			}
		}
	}

	public static class OfInt extends AbstractIntIterator
	{
		private final IntIterator source;
		private final PrimitiveIterator.OfInt appended;

		public OfInt(final IntIterator source,
				final PrimitiveIterator.OfInt appended)
		{
			super(Option.add(source.size(), getSize(appended)));
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
			return source.hasNext() ? source.nextInt() : appended.nextInt();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			} else {
				ImplUtils.skip(appended);
			}
		}
	}
}
