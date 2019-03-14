/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @author thomasb
 *
 */
public final class FilterAdapter
{
	private FilterAdapter()
	{
	}

	private static AbstractIteratorSize computeNewSize(AbstractIteratorSize sourceSize)
	{
		OptionalInt upperBoundOption = sourceSize.getUpperBound();
		if (upperBoundOption.isPresent()) {
			return new UpperBound(upperBoundOption.getAsInt());
		} else {
			return UnknownSize.instance();
		}
	}

	static final class OfObject<E> extends AbstractIteratorAdapter.OfObject<E, E>
	{
		private final Predicate<? super E> predicate;

		private boolean initialized;
		private E cached;

		public OfObject(AbstractEnhancedIterator<E> source, Predicate<? super E> predicate)
		{
			super(computeNewSize(source.getSize()), source);
			this.predicate = Objects.requireNonNull(predicate);
		}

		@Override
		public boolean hasNext()
		{
			while (!initialized && getSource().hasNext()) {
				E next = getSource().nextImpl();
				if (predicate.test(next)) {
					cached = next;
					initialized = true;
					break;
				}
			}
			return initialized;
		}

		@Override
		public E nextImpl()
		{
			if (hasNext()) {
				initialized = false;
				return cached;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				initialized = false;
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	static final class OfInt extends AbstractIteratorAdapter.OfInt
	{
		private final IntPredicate predicate;

		private boolean initialized;
		private int cached;

		public OfInt(AbstractIntIterator source, IntPredicate predicate)
		{
			super(computeNewSize(source.getSize()), source);
			this.predicate = Objects.requireNonNull(predicate);
		}

		@Override
		public boolean hasNext()
		{
			while (!initialized && getSource().hasNext()) {
				int next = getSource().nextIntImpl();
				if (predicate.test(next)) {
					cached = next;
					initialized = true;
					break;
				}
			}
			return initialized;
		}

		@Override
		public int nextIntImpl()
		{
			if (hasNext()) {
				initialized = false;
				return cached;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				initialized = false;
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	static final class OfLong extends AbstractIteratorAdapter.OfLong
	{
		private final LongPredicate predicate;

		private boolean initialized;
		private long cached;

		public OfLong(AbstractLongIterator source, LongPredicate predicate)
		{
			super(computeNewSize(source.getSize()), source);
			this.predicate = Objects.requireNonNull(predicate);
		}

		@Override
		public boolean hasNext()
		{
			while (!initialized && getSource().hasNext()) {
				long next = getSource().nextLongImpl();
				if (predicate.test(next)) {
					cached = next;
					initialized = true;
					break;
				}
			}
			return initialized;
		}

		@Override
		public long nextLongImpl()
		{
			if (hasNext()) {
				initialized = false;
				return cached;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				initialized = false;
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	static final class OfDouble extends AbstractIteratorAdapter.OfDouble
	{
		private final DoublePredicate predicate;

		private boolean initialized;
		private double cached;

		public OfDouble(AbstractDoubleIterator source, DoublePredicate predicate)
		{
			super(computeNewSize(source.getSize()), source);
			this.predicate = Objects.requireNonNull(predicate);
		}

		@Override
		public boolean hasNext()
		{
			while (!initialized && getSource().hasNext()) {
				double next = getSource().nextDoubleImpl();
				if (predicate.test(next)) {
					cached = next;
					initialized = true;
					break;
				}
			}
			return initialized;
		}

		@Override
		public double nextDoubleImpl()
		{
			if (hasNext()) {
				initialized = false;
				return cached;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				initialized = false;
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}
