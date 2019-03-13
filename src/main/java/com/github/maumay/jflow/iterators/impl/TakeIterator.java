package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;

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
 * @since 25 Apr 2018
 */
public final class TakeIterator
{
	private TakeIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		private final EnhancedIterator<E> src;
		private final int takeCount;

		private int count = 0;

		public OfObject(final EnhancedIterator<E> src, final int takeCount)
		{
			super(Option.map(n -> Math.min(takeCount, n), src.size()));
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public E next()
		{
			if (count++ < takeCount) {
				return src.next();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count++ < takeCount) {
				src.skip();
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final LongIterator src;
		private final int takeCount;

		private int count = 0;

		public OfLong(final LongIterator src, final int takeCount)
		{
			super(Option.map(n -> Math.min(takeCount, n), src.size()));
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public long nextLong()
		{
			if (count++ < takeCount) {
				return src.nextLong();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count++ < takeCount) {
				src.skip();
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfInt extends AbstractIntIterator
	{
		private final IntIterator src;
		private final int takeCount;

		private int count = 0;

		public OfInt(final IntIterator src, final int takeCount)
		{
			super(Option.map(n -> Math.min(takeCount, n), src.size()));
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public int nextInt()
		{
			if (count++ < takeCount) {
				return src.nextInt();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count++ < takeCount) {
				src.nextInt();
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final DoubleIterator src;
		private final int takeCount;

		private int count = 0;

		public OfDouble(final DoubleIterator src, final int takeCount)
		{
			super(Option.map(n -> Math.min(takeCount, n), src.size()));
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public double nextDouble()
		{
			if (count++ < takeCount) {
				return src.nextDouble();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count++ < takeCount) {
				src.nextDouble();
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}
