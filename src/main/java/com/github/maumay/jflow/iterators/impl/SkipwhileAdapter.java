/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
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

		public OfObject(AbstractEnhancedIterator<E> source, Predicate<? super E> predicate)
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

	public static final class OfDouble
			extends AbstractIteratorAdapter.OfDouble<AbstractDoubleIterator>
	{
		private final DoublePredicate predicate;

		private double firstFailure;
		private boolean firstFailureInitialised, firstFailureConsumed;

		public OfObject(AbstractDoubleIterator source, DoublePredicate predicate)
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
}
