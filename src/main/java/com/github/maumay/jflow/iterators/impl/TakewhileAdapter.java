/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @author thomasb
 *
 */
public class TakewhileAdapter
{
	private TakewhileAdapter()
	{
	}

	public static final class OfObject<E>
			extends AbstractIteratorAdapter.OfObject<AbstractEnhancedIterator<E>, E>
	{
		private final Predicate<? super E> predicate;

		private E cached;
		private boolean iteratorExhausted, nextReady;

		public OfObject(AbstractEnhancedIterator<E> source, Predicate<? super E> predicate)
		{
			super(IteratorImplUtils.dropLowerBound(source.getSize()), source);
			this.predicate = Objects.requireNonNull(predicate);
			this.cached = null;
			this.iteratorExhausted = false;
			this.nextReady = false;
		}

		@Override
		public boolean hasNext()
		{
			if (iteratorExhausted) {
				return false;
			} else if (nextReady) {
				return true;
			} else if (getSource().hasNext()) {
				E tmp = getSource().nextImpl();
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
		public E nextImpl()
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
		public void skipImpl()
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

	public static final class OfInt extends AbstractIteratorAdapter.OfInt<AbstractIntIterator>
	{
		private final IntPredicate predicate;

		private int cached;
		private boolean iteratorExhausted, nextReady;

		public OfInt(AbstractIntIterator source, IntPredicate predicate)
		{
			super(IteratorImplUtils.dropLowerBound(source.getSize()), source);
			this.predicate = Objects.requireNonNull(predicate);
			this.cached = 0;
			this.iteratorExhausted = false;
			this.nextReady = false;
		}

		@Override
		public boolean hasNext()
		{
			if (iteratorExhausted) {
				return false;
			} else if (nextReady) {
				return true;
			} else if (getSource().hasNext()) {
				int tmp = getSource().nextIntImpl();
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
		public int nextIntImpl()
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
		public void skipImpl()
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

	public static final class OfLong extends AbstractIteratorAdapter.OfLong<AbstractLongIterator>
	{
		private final LongPredicate predicate;

		private long cached;
		private boolean iteratorExhausted, nextReady;

		public OfLong(AbstractLongIterator source, LongPredicate predicate)
		{
			super(IteratorImplUtils.dropLowerBound(source.getSize()), source);
			this.predicate = Objects.requireNonNull(predicate);
			this.cached = 0;
			this.iteratorExhausted = false;
			this.nextReady = false;
		}

		@Override
		public boolean hasNext()
		{
			if (iteratorExhausted) {
				return false;
			} else if (nextReady) {
				return true;
			} else if (getSource().hasNext()) {
				long tmp = getSource().nextLongImpl();
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
		public long nextLongImpl()
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
		public void skipImpl()
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

	public static final class OfDouble
			extends AbstractIteratorAdapter.OfDouble<AbstractDoubleIterator>
	{
		private final DoublePredicate predicate;

		private double cached;
		private boolean iteratorExhausted, nextReady;

		public OfDouble(AbstractDoubleIterator source, DoublePredicate predicate)
		{
			super(IteratorImplUtils.dropLowerBound(source.getSize()), source);
			this.predicate = predicate;
			this.cached = 0;
			this.iteratorExhausted = false;
			this.nextReady = false;
		}

		@Override
		public boolean hasNext()
		{
			if (iteratorExhausted) {
				return false;
			} else if (nextReady) {
				return true;
			} else if (getSource().hasNext()) {
				double tmp = getSource().nextDoubleImpl();
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
		public double nextDoubleImpl()
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
		public void skipImpl()
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
