package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractIntIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractLongIterator;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public class FilteredIterator
{
	private FilteredIterator()
	{
	}

	public static class OfObject<T> extends AbstractEnhancedIterator<T>
	{
		private final EnhancedIterator<T> src;
		private final Predicate<? super T> predicate;

		private T cached = null;

		public OfObject(EnhancedIterator<T> src, Predicate<? super T> predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			while (cached == null && src.hasNext()) {
				T next = src.next();
				if (predicate.test(next)) {
					cached = next;
				}
			}
			return cached != null;
		}

		@Override
		public T next()
		{
			if (hasNext()) {
				T tmp = cached;
				cached = null;
				return tmp;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (hasNext()) {
				cached = null;
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final LongIterator src;
		private final LongPredicate predicate;

		private boolean nextReady = false;
		private long cached = -1;

		public OfLong(final LongIterator src, final LongPredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			while (!nextReady && src.hasNext()) {
				final long next = src.nextLong();
				if (predicate.test(next)) {
					nextReady = true;
					cached = next;
				}
			}
			return nextReady;
		}

		@Override
		public long nextLong()
		{
			if (nextReady) {
				nextReady = false;
				return cached;
			} else {
				if (hasNext()) {
					nextReady = false;
					return cached;
				} else {
					throw new NoSuchElementException();
				}
			}
		}

		@Override
		public void skip()
		{
			if (nextReady) {
				nextReady = false;
			} else {
				if (hasNext()) {
					nextReady = false;
				} else {
					throw new NoSuchElementException();
				}
			}
		}
	}

	public static class OfInt extends AbstractIntIterator
	{
		private final IntIterator src;
		private final IntPredicate predicate;

		private boolean nextReady = false;
		private int cached = -1;

		public OfInt(final IntIterator src, final IntPredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			while (!nextReady && src.hasNext()) {
				final int next = src.nextInt();
				if (predicate.test(next)) {
					nextReady = true;
					cached = next;
				}
			}
			return nextReady;
		}

		@Override
		public int nextInt()
		{
			if (nextReady) {
				nextReady = false;
				return cached;
			} else {
				if (hasNext()) {
					nextReady = false;
					return cached;
				} else {
					throw new NoSuchElementException();
				}
			}
		}

		@Override
		public void skip()
		{
			if (nextReady) {
				nextReady = false;
			} else {
				if (hasNext()) {
					nextReady = false;
				} else {
					throw new NoSuchElementException();
				}
			}
		}
	}

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final DoubleIterator src;
		private final DoublePredicate predicate;

		private boolean nextReady = false;
		private double cached = -1;

		public OfDouble(final DoubleIterator src, final DoublePredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			while (!nextReady && src.hasNext()) {
				final double next = src.nextDouble();
				if (predicate.test(next)) {
					nextReady = true;
					cached = next;
				}
			}
			return nextReady;
		}

		@Override
		public double nextDouble()
		{
			if (nextReady) {
				nextReady = false;
				return cached;
			} else {
				if (hasNext()) {
					nextReady = false;
					return cached;
				} else {
					throw new NoSuchElementException();
				}
			}
		}

		@Override
		public void skip()
		{
			if (nextReady) {
				nextReady = false;
			} else {
				if (hasNext()) {
					nextReady = false;
				} else {
					throw new NoSuchElementException();
				}
			}
		}
	}
}
