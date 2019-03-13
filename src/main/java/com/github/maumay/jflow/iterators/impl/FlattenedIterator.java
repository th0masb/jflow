package com.github.maumay.jflow.iterators.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Function;

import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractIntIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractLongIterator;

/**
 * @author t
 */
public final class FlattenedIterator
{
	private FlattenedIterator()
	{
	}

	public static class OfObject<E, R> extends AbstractEnhancedIterator<R>
	{
		private final EnhancedIterator<E> src;
		private final Function<? super E, ? extends Iterator<? extends R>> mapping;

		private Iterator<? extends R> currentSubFlow;

		public OfObject(final EnhancedIterator<E> src,
				final Function<? super E, ? extends Iterator<? extends R>> mapping)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.mapping = mapping;
		}

		private void init()
		{
			while (src.hasNext()
					&& (currentSubFlow == null || !currentSubFlow.hasNext())) {
				currentSubFlow = mapping.apply(src.next());
			}
		}

		@Override
		public boolean hasNext()
		{
			if (currentSubFlow == null) {
				init();
			}
			return currentSubFlow != null && currentSubFlow.hasNext();
		}

		@Override
		public R next()
		{
			if (currentSubFlow == null) {
				init();
			}

			if (currentSubFlow == null) {
				throw new NoSuchElementException();
			} else {
				final R next = currentSubFlow.next();
				while (!currentSubFlow.hasNext() && src.hasNext()) {
					currentSubFlow = mapping.apply(src.next());
				}
				return next;
			}
		}

		@Override
		public void skip()
		{
			next();
		}
	}

	public static class OfLong<E> extends AbstractLongIterator
	{
		private final EnhancedIterator<E> src;
		private final Function<? super E, ? extends LongIterator> mapping;

		private LongIterator currentSubFlow;

		public OfLong(final EnhancedIterator<E> src,
				final Function<? super E, ? extends LongIterator> mapping)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.mapping = mapping;
		}

		private void init()
		{
			while (src.hasNext()
					&& (currentSubFlow == null || !currentSubFlow.hasNext())) {
				currentSubFlow = mapping.apply(src.next());
			}
		}

		@Override
		public boolean hasNext()
		{
			if (currentSubFlow == null) {
				init();
			}
			return currentSubFlow != null && currentSubFlow.hasNext();
		}

		@Override
		public long nextLong()
		{
			if (currentSubFlow == null) {
				init();
			}

			if (currentSubFlow == null) {
				throw new NoSuchElementException();
			} else {
				final long next = currentSubFlow.nextLong();
				while (!currentSubFlow.hasNext() && src.hasNext()) {
					currentSubFlow = mapping.apply(src.next());
				}
				return next;
			}
		}

		@Override
		public void skip()
		{
			nextLong();
		}
	}

	public static class OfDouble<E> extends AbstractDoubleIterator
	{
		private final EnhancedIterator<E> src;
		private final Function<? super E, ? extends DoubleIterator> mapping;

		private DoubleIterator currentSubFlow;

		public OfDouble(final EnhancedIterator<E> src,
				final Function<? super E, ? extends DoubleIterator> mapping)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.mapping = mapping;
		}

		private void init()
		{
			while (src.hasNext()
					&& (currentSubFlow == null || !currentSubFlow.hasNext())) {
				currentSubFlow = mapping.apply(src.next());
			}
		}

		@Override
		public boolean hasNext()
		{
			if (currentSubFlow == null) {
				init();
			}
			return currentSubFlow != null && currentSubFlow.hasNext();
		}

		@Override
		public double nextDouble()
		{
			if (currentSubFlow == null) {
				init();
			}

			if (currentSubFlow == null) {
				throw new NoSuchElementException();
			} else {
				final double next = currentSubFlow.nextDouble();
				while (!currentSubFlow.hasNext() && src.hasNext()) {
					currentSubFlow = mapping.apply(src.next());
				}
				return next;
			}
		}

		@Override
		public void skip()
		{
			nextDouble();
		}
	}

	public static class OfInt<E> extends AbstractIntIterator
	{
		private final EnhancedIterator<E> src;
		private final Function<? super E, ? extends IntIterator> mapping;

		private IntIterator currentSubFlow;

		public OfInt(final EnhancedIterator<E> src,
				final Function<? super E, ? extends IntIterator> mapping)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.mapping = mapping;
		}

		private void init()
		{
			while (src.hasNext()
					&& (currentSubFlow == null || !currentSubFlow.hasNext())) {
				currentSubFlow = mapping.apply(src.next());
			}
		}

		@Override
		public boolean hasNext()
		{
			if (currentSubFlow == null) {
				init();
			}
			return currentSubFlow != null && currentSubFlow.hasNext();
		}

		@Override
		public int nextInt()
		{
			if (currentSubFlow == null) {
				init();
			}

			if (currentSubFlow == null) {
				throw new NoSuchElementException();
			} else {
				final int next = currentSubFlow.nextInt();
				while (!currentSubFlow.hasNext() && src.hasNext()) {
					currentSubFlow = mapping.apply(src.next());
				}
				return next;
			}
		}

		@Override
		public void skip()
		{
			nextInt();
		}
	}
}
