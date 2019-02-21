/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalInt;

import com.github.maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedLongIterator;

/**
 * @author t
 *
 */
public final class ReversedSourceIterator
{
	private ReversedSourceIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		private final Object[] source;
		private int count;

		public OfObject(final List<? extends E> source)
		{
			super(OptionalInt.of(source.size()));
			count = source.size() - 1;
			this.source = source.toArray();
		}

		@SafeVarargs
		public OfObject(E... source)
		{
			super(OptionalInt.of(source.length));
			count = source.length - 1;
			this.source = Arrays.copyOf((Object[]) source, source.length);
		}

		@Override
		public boolean hasNext()
		{
			return count >= 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next()
		{
			if (hasNext()) {
				return (E) source[count--];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count-- < 0) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final long[] source;
		private int count;

		public OfLong(final long[] source)
		{
			super(OptionalInt.of(source.length));
			this.source = source;
			count = source.length - 1;
		}

		@Override
		public boolean hasNext()
		{
			return count >= 0;
		}

		@Override
		public long nextLong()
		{
			if (hasNext()) {
				return source[count--];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count-- < 0) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final double[] source;
		private int count;

		public OfDouble(final double[] source)
		{
			super(OptionalInt.of(source.length));
			this.source = source;
			count = source.length - 1;
		}

		@Override
		public boolean hasNext()
		{
			return count >= 0;
		}

		@Override
		public double nextDouble()
		{
			if (hasNext()) {
				return source[count--];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count-- < 0) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final int[] source;
		private int count;

		public OfInt(final int[] source)
		{
			super(OptionalInt.of(source.length));
			this.source = source;
			count = source.length - 1;
		}

		@Override
		public boolean hasNext()
		{
			return count >= 0;
		}

		@Override
		public int nextInt()
		{
			if (hasNext()) {
				return source[count--];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count-- < 0) {
				throw new NoSuchElementException();
			}
		}
	}
}
