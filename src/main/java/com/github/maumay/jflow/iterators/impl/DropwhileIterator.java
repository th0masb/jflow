/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import com.github.maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedLongIterator;
import com.github.maumay.jflow.iterators.EnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.EnhancedIntIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.EnhancedLongIterator;

/**
 * @author t
 *
 */
public class DropwhileIterator
{

	private DropwhileIterator()
	{
	}

	public static class OfObject<T> extends AbstractEnhancedIterator<T>
	{
		private final EnhancedIterator<T> src;
		private final Predicate<? super T> predicate;

		private boolean firstFailureConsumed = false;
		private T firstFailure = null;

		public OfObject(final EnhancedIterator<T> src,
				final Predicate<? super T> predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (firstFailure == null) {
				while (firstFailure == null && src.hasNext()) {
					final T next = src.next();
					if (!predicate.test(next)) {
						firstFailure = next;
						return true;
					}
				}
				return false;
			} else if (!firstFailureConsumed) {
				return true;
			} else {
				return src.hasNext();
			}
		}

		@Override
		public T next()
		{
			if (firstFailure == null) {
				if (hasNext()) {
					firstFailureConsumed = true;
					return firstFailure;
				} else {
					throw new NoSuchElementException();
				}
			} else if (firstFailureConsumed) {
				return src.next();
			} else {
				firstFailureConsumed = true;
				return firstFailure;
			}
		}

		@Override
		public void skip()
		{
			if (firstFailure == null) {
				if (hasNext()) {
					firstFailureConsumed = true;
				} else {
					throw new NoSuchElementException();
				}
			} else if (firstFailureConsumed) {
				src.skip();
			} else {
				firstFailureConsumed = true;
			}
		}
	}

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator src;
		private final LongPredicate predicate;

		private boolean firstFailureInitialized, firstFailureConsumed;
		private long firstFailure;

		public OfLong(final EnhancedLongIterator src, final LongPredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (!firstFailureInitialized) {
				while (!firstFailureInitialized && src.hasNext()) {
					final long next = src.nextLong();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialized = true;
						return true;
					}
				}
				return false;
			} else if (!firstFailureConsumed) {
				return true;
			} else {
				return src.hasNext();
			}
		}

		@Override
		public long nextLong()
		{
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
					return firstFailure;
				} else {
					throw new NoSuchElementException();
				}
			} else if (firstFailureConsumed) {
				return src.nextLong();
			} else {
				firstFailureConsumed = true;
				return firstFailure;
			}
		}

		@Override
		public void skip()
		{
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
				} else {
					throw new NoSuchElementException();
				}
			} else if (firstFailureConsumed) {
				src.skip();
			} else {
				firstFailureConsumed = true;
			}
		}
	}

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator src;
		private final DoublePredicate predicate;

		private boolean firstFailureInitialized, firstFailureConsumed;
		private double firstFailure;

		public OfDouble(final EnhancedDoubleIterator src, final DoublePredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (!firstFailureInitialized) {
				while (!firstFailureInitialized && src.hasNext()) {
					final double next = src.nextDouble();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialized = true;
						return true;
					}
				}
				return false;
			} else if (!firstFailureConsumed) {
				return true;
			} else {
				return src.hasNext();
			}
		}

		@Override
		public double nextDouble()
		{
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
					return firstFailure;
				} else {
					throw new NoSuchElementException();
				}
			} else if (firstFailureConsumed) {
				return src.nextDouble();
			} else {
				firstFailureConsumed = true;
				return firstFailure;
			}
		}

		@Override
		public void skip()
		{
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
				} else {
					throw new NoSuchElementException();
				}
			} else if (firstFailureConsumed) {
				src.skip();
			} else {
				firstFailureConsumed = true;
			}
		}
	}

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator src;
		private final IntPredicate predicate;

		private boolean firstFailureInitialized, firstFailureConsumed;
		private int firstFailure;

		public OfInt(final EnhancedIntIterator src, final IntPredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (!firstFailureInitialized) {
				while (!firstFailureInitialized && src.hasNext()) {
					final int next = src.nextInt();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialized = true;
						return true;
					}
				}
				return false;
			} else if (!firstFailureConsumed) {
				return true;
			} else {
				return src.hasNext();
			}
		}

		@Override
		public int nextInt()
		{
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
					return firstFailure;
				} else {
					throw new NoSuchElementException();
				}
			} else if (firstFailureConsumed) {
				return src.nextInt();
			} else {
				firstFailureConsumed = true;
				return firstFailure;
			}
		}

		@Override
		public void skip()
		{
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
				} else {
					throw new NoSuchElementException();
				}
			} else if (firstFailureConsumed) {
				src.skip();
			} else {
				firstFailureConsumed = true;
			}
		}
	}
}
