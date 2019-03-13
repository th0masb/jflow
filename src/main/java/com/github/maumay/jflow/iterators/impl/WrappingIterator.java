/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import static com.github.maumay.jflow.iterators.impl.ImplUtils.getSize;

import java.util.Iterator;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;

/**
 * @author t
 */
public final class WrappingIterator
{
	private WrappingIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		private final Iterator<? extends E> iterator;

		public OfObject(Iterator<? extends E> src)
		{
			super(getSize(src));
			this.iterator = src;
		}

		public OfObject(Iterator<? extends E> src, int size)
		{
			super(size < 0 ? OptionalInt.empty() : OptionalInt.of(size));
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

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final PrimitiveIterator.OfDouble source;

		public OfDouble(PrimitiveIterator.OfDouble src)
		{
			super(getSize(src));
			this.source = src;
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

	public static class OfLong extends AbstractLongIterator
	{
		private final PrimitiveIterator.OfLong source;

		public OfLong(PrimitiveIterator.OfLong src)
		{
			super(getSize(src));
			this.source = src;
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

	public static class OfInt extends AbstractIntIterator
	{
		private final PrimitiveIterator.OfInt source;

		public OfInt(PrimitiveIterator.OfInt src)
		{
			super(getSize(src));
			this.source = src;
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
