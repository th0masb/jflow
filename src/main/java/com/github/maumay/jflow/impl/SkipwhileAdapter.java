/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @author thomasb
 *
 */
public class SkipwhileAdapter
{
	private SkipwhileAdapter()
	{
	}

	public static final class OfObject<E>
			extends AbstractIteratorAdapter.OfObject<AbstractEnhancedIterator<E>, E>
	{
		private final Predicate<? super E> predicate;

		private E firstFailure;
		private boolean firstFailureInitialised, firstFailureConsumed;

		public OfObject(AbstractEnhancedIterator<E> source,
				Predicate<? super E> predicate)
		{
			super(IteratorImplUtils.dropLowerBound(source.getSize()), source);
			this.predicate = predicate;
			this.firstFailure = null;
			this.firstFailureInitialised = false;
			this.firstFailureConsumed = false;
		}

		@Override
		public boolean hasNext()
		{
			if (firstFailureInitialised) {
				return !firstFailureConsumed || getSource().hasNext();
			} else {
				AbstractEnhancedIterator<E> src = getSource();
				while (src.hasNext()) {
					E next = src.nextImpl();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialised = true;
						return true;
					}
				}
				return false;
			}
		}

		@Override
		public E nextImpl()
		{
			if (hasNext()) {
				if (firstFailureConsumed) {
					return getSource().nextImpl();
				} else {
					firstFailureConsumed = true;
					return firstFailure;
				}
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				if (firstFailureConsumed) {
					getSource().skipImpl();
				} else {
					firstFailureConsumed = true;
				}
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static final class OfInt
			extends AbstractIteratorAdapter.OfInt<AbstractIntIterator>
	{
		private final IntPredicate predicate;

		private int firstFailure;
		private boolean firstFailureInitialised, firstFailureConsumed;

		public OfInt(AbstractIntIterator source, IntPredicate predicate)
		{
			super(IteratorImplUtils.dropLowerBound(source.getSize()), source);
			this.predicate = predicate;
			this.firstFailure = 0;
			this.firstFailureInitialised = false;
			this.firstFailureConsumed = false;
		}

		@Override
		public boolean hasNext()
		{
			if (firstFailureInitialised) {
				return !firstFailureConsumed || getSource().hasNext();
			} else {
				AbstractIntIterator src = getSource();
				while (src.hasNext()) {
					int next = src.nextIntImpl();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialised = true;
						return true;
					}
				}
				return false;
			}
		}

		@Override
		public int nextIntImpl()
		{
			if (hasNext()) {
				if (firstFailureConsumed) {
					return getSource().nextIntImpl();
				} else {
					firstFailureConsumed = true;
					return firstFailure;
				}
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				if (firstFailureConsumed) {
					getSource().skipImpl();
				} else {
					firstFailureConsumed = true;
				}
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static final class OfLong
			extends AbstractIteratorAdapter.OfLong<AbstractLongIterator>
	{
		private final LongPredicate predicate;

		private long firstFailure;
		private boolean firstFailureInitialised, firstFailureConsumed;

		public OfLong(AbstractLongIterator source, LongPredicate predicate)
		{
			super(IteratorImplUtils.dropLowerBound(source.getSize()), source);
			this.predicate = predicate;
			this.firstFailure = 0;
			this.firstFailureInitialised = false;
			this.firstFailureConsumed = false;
		}

		@Override
		public boolean hasNext()
		{
			if (firstFailureInitialised) {
				return !firstFailureConsumed || getSource().hasNext();
			} else {
				AbstractLongIterator src = getSource();
				while (src.hasNext()) {
					long next = src.nextLongImpl();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialised = true;
						return true;
					}
				}
				return false;
			}
		}

		@Override
		public long nextLongImpl()
		{
			if (hasNext()) {
				if (firstFailureConsumed) {
					return getSource().nextLongImpl();
				} else {
					firstFailureConsumed = true;
					return firstFailure;
				}
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				if (firstFailureConsumed) {
					getSource().skipImpl();
				} else {
					firstFailureConsumed = true;
				}
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static final class OfDouble
			extends AbstractIteratorAdapter.OfDouble<AbstractDoubleIterator>
	{
		private final DoublePredicate predicate;

		private double firstFailure;
		private boolean firstFailureInitialised, firstFailureConsumed;

		public OfDouble(AbstractDoubleIterator source, DoublePredicate predicate)
		{
			super(IteratorImplUtils.dropLowerBound(source.getSize()), source);
			this.predicate = predicate;
			this.firstFailure = 0;
			this.firstFailureInitialised = false;
			this.firstFailureConsumed = false;
		}

		@Override
		public boolean hasNext()
		{
			if (firstFailureInitialised) {
				return !firstFailureConsumed || getSource().hasNext();
			} else {
				AbstractDoubleIterator src = getSource();
				while (src.hasNext()) {
					double next = src.nextDoubleImpl();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialised = true;
						return true;
					}
				}
				return false;
			}
		}

		@Override
		public double nextDoubleImpl()
		{
			if (hasNext()) {
				if (firstFailureConsumed) {
					return getSource().nextDoubleImpl();
				} else {
					firstFailureConsumed = true;
					return firstFailure;
				}
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				if (firstFailureConsumed) {
					getSource().skipImpl();
				} else {
					firstFailureConsumed = true;
				}
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}
