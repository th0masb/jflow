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
 * @author ThomasB
 * @since 23 Apr 2018
 */
public final class TakewhileIterator
{
	private TakewhileIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		private final EnhancedIterator<E> src;
		private final Predicate<? super E> predicate;

		private E cached = null;
		private boolean iteratorExhausted = false;

		public OfObject(final EnhancedIterator<E> src,
				final Predicate<? super E> predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (iteratorExhausted) {
				return false;
			}
			if (cached != null) {
				return true;
			} else if (src.hasNext()) {
				final E tmp = src.next();
				if (predicate.test(tmp)) {
					cached = tmp;
					return true;
				} else {
					iteratorExhausted = true;
					return false;
				}
			} else {
				iteratorExhausted = true;
				return false;
			}
		}

		@Override
		public E next()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			} else if (cached != null) {
				final E tmp = cached;
				cached = null;
				return tmp;
			} else { // hasnext wasn't called
				if (hasNext()) {
					final E tmp = cached;
					cached = null;
					return tmp;
				} else {
					throw new NoSuchElementException();
				}
			}
		}

		@Override
		public void skip()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			} else if (cached != null) {
				cached = null;
			} else { // hasnext wasn't called
				if (hasNext()) {
					cached = null;
				} else {
					throw new NoSuchElementException();
				}
			}
		}
	}

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator src;
		private final IntPredicate predicate;

		private int cached = -1;
		private boolean nextReady = false;
		private boolean iteratorExhausted = false;

		public OfInt(final EnhancedIntIterator src, final IntPredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (iteratorExhausted) {
				return false;
			}
			if (nextReady) {
				return true;
			} else if (src.hasNext()) {
				final int tmp = src.nextInt();
				if (predicate.test(tmp)) {
					cached = tmp;
					nextReady = true;
					return true;
				} else {
					iteratorExhausted = true;
					return false;
				}
			} else {
				iteratorExhausted = true;
				return false;
			}
		}

		@Override
		public int nextInt()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			} else if (nextReady) {
				nextReady = false;
				return cached;
			} else { // hasnext wasn't called
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
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			} else if (nextReady) {
				nextReady = false;
			} else { // hasnext wasn't called
				if (hasNext()) {
					nextReady = false;
				} else {
					throw new NoSuchElementException();
				}
			}
		}
	}

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator src;
		private final DoublePredicate predicate;

		private double cached = -1;
		private boolean nextReady = false;
		private boolean iteratorExhausted = false;

		public OfDouble(final EnhancedDoubleIterator src, final DoublePredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (iteratorExhausted) {
				return false;
			}
			if (nextReady) {
				return true;
			} else if (src.hasNext()) {
				final double tmp = src.nextDouble();
				if (predicate.test(tmp)) {
					cached = tmp;
					nextReady = true;
					return true;
				} else {
					iteratorExhausted = true;
					return false;
				}
			} else {
				iteratorExhausted = true;
				return false;
			}
		}

		@Override
		public double nextDouble()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			} else if (nextReady) {
				nextReady = false;
				return cached;
			} else { // hasnext wasn't called
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
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			} else if (nextReady) {
				nextReady = false;
			} else { // hasnext wasn't called
				if (hasNext()) {
					nextReady = false;
				} else {
					throw new NoSuchElementException();
				}
			}
		}
	}

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator src;
		private final LongPredicate predicate;

		private long cached = -1;
		private boolean nextReady = false;
		private boolean iteratorExhausted = false;

		public OfLong(final EnhancedLongIterator src, final LongPredicate predicate)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext()
		{
			if (iteratorExhausted) {
				return false;
			}
			if (nextReady) {
				return true;
			} else if (src.hasNext()) {
				final long tmp = src.nextLong();
				if (predicate.test(tmp)) {
					cached = tmp;
					nextReady = true;
					return true;
				} else {
					iteratorExhausted = true;
					return false;
				}
			} else {
				iteratorExhausted = true;
				return false;
			}
		}

		@Override
		public long nextLong()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			} else if (nextReady) {
				nextReady = false;
				return cached;
			} else { // hasnext wasn't called
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
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			} else if (nextReady) {
				nextReady = false;
			} else { // hasnext wasn't called
				if (hasNext()) {
					nextReady = false;
				} else {
					throw new NoSuchElementException();
				}
			}
		}
	}
}
