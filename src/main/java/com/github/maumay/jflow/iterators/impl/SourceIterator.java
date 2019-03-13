/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;

/**
 * @author t
 */
public final class SourceIterator
{
	private SourceIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
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
			} else {
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

	public static class OfLong extends AbstractLongIterator
	{
		private final long[] cache;
		private int count;

		public OfLong(final long... src)
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
			} else {
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

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final double[] cache;
		private int count;

		public OfDouble(final double... src)
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
			} else {
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

	public static class OfInt extends AbstractIntIterator
	{
		private final int[] cache;
		private int count;

		public OfInt(final int... src)
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
			} else {
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
