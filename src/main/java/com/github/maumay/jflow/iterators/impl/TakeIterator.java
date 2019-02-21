package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;

import com.gihub.maumay.jflow.iterators.misc.Optionals;
import com.github.maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedLongIterator;
import com.github.maumay.jflow.iterators.EnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.EnhancedIntIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.EnhancedLongIterator;

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
			super(Optionals.map(n -> Math.min(takeCount, n), src.size()));
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

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator src;
		private final int takeCount;

		private int count = 0;

		public OfLong(final EnhancedLongIterator src, final int takeCount)
		{
			super(Optionals.map(n -> Math.min(takeCount, n), src.size()));
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

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator src;
		private final int takeCount;

		private int count = 0;

		public OfInt(final EnhancedIntIterator src, final int takeCount)
		{
			super(Optionals.map(n -> Math.min(takeCount, n), src.size()));
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

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator src;
		private final int takeCount;

		private int count = 0;

		public OfDouble(final EnhancedDoubleIterator src, final int takeCount)
		{
			super(Optionals.map(n -> Math.min(takeCount, n), src.size()));
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
