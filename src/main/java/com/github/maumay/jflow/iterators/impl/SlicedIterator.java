/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.IntUnaryOperator;

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
public final class SlicedIterator
{
	private SlicedIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		private final EnhancedIterator<E> src;
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;
		private E cached = null;
		boolean srcExhausted = false;

		public OfObject(final EnhancedIterator<E> src,
				final IntUnaryOperator indexMapping)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.indexMapping = indexMapping;
		}

		private void init()
		{
			updateCheckpoint(indexMapping.applyAsInt(indexCount));
			cacheNextElement();
		}

		private void updateCheckpoint(final int newCheckpoint)
		{
			if (newCheckpoint < checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			assert !srcExhausted && cached == null;
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skip();
					iteratorCount++;
				} else {
					srcExhausted = true;
					return false;
				}
			}

			if (src.hasNext()) {
				cached = src.next();
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(++indexCount));
				return true;
			} else {
				srcExhausted = true;
				return false;
			}
		}

		@Override
		public boolean hasNext()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				return false;
			} else if (cached == null) {
				return cacheNextElement();
			} else {
				return true;
			}
		}

		@Override
		public E next()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			} else if (cached == null) {
				if (cacheNextElement()) {
					return returnCacheAndErase();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				return returnCacheAndErase();
			}
		}

		@Override
		public void skip()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			} else if (cached == null) {
				if (cacheNextElement()) {
					returnCacheAndErase();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				returnCacheAndErase();
			}
		}

		private E returnCacheAndErase()
		{
			assert cached != null;
			final E tmp = cached;
			cached = null;
			return tmp;
		}
	}

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator src;
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;
		private int cached = -1;
		boolean srcExhausted = false, elementCached = false;

		public OfInt(final EnhancedIntIterator src, final IntUnaryOperator indexMapping)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.indexMapping = indexMapping;
		}

		private void init()
		{
			updateCheckpoint(indexMapping.applyAsInt(indexCount));
			cacheNextElement();
		}

		private void updateCheckpoint(final int newCheckpoint)
		{
			if (newCheckpoint <= checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			assert !srcExhausted && !elementCached;
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skip();
					iteratorCount++;
				} else {
					srcExhausted = true;
					return false;
				}
			}

			if (src.hasNext()) {
				cached = src.nextInt();
				elementCached = true;
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(++indexCount));
				return true;
			} else {
				srcExhausted = true;
				return false;
			}
		}

		@Override
		public boolean hasNext()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				return false;
			} else if (!elementCached) {
				return cacheNextElement();
			} else {
				return true;
			}
		}

		@Override
		public int nextInt()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			} else if (!elementCached) {
				if (cacheNextElement()) {
					return returnCacheAndErase();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				return returnCacheAndErase();
			}
		}

		@Override
		public void skip()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			} else if (!elementCached) {
				if (cacheNextElement()) {
					returnCacheAndErase();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				returnCacheAndErase();
			}
		}

		private int returnCacheAndErase()
		{
			assert elementCached;
			elementCached = false;
			return cached;
		}
	}

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator src;
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;
		private double cached = -1;
		boolean srcExhausted = false, elementCached = false;

		public OfDouble(final EnhancedDoubleIterator src,
				final IntUnaryOperator indexMapping)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.indexMapping = indexMapping;
		}

		private void init()
		{
			updateCheckpoint(indexMapping.applyAsInt(indexCount));
			cacheNextElement();
		}

		private void updateCheckpoint(final int newCheckpoint)
		{
			if (newCheckpoint <= checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			assert !srcExhausted && !elementCached;
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skip();
					iteratorCount++;
				} else {
					srcExhausted = true;
					return false;
				}
			}

			if (src.hasNext()) {
				cached = src.nextDouble();
				elementCached = true;
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(++indexCount));
				return true;
			} else {
				srcExhausted = true;
				return false;
			}
		}

		@Override
		public boolean hasNext()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				return false;
			} else if (!elementCached) {
				return cacheNextElement();
			} else {
				return true;
			}
		}

		@Override
		public double nextDouble()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			} else if (!elementCached) {
				if (cacheNextElement()) {
					return returnCacheAndErase();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				return returnCacheAndErase();
			}
		}

		@Override
		public void skip()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			} else if (!elementCached) {
				if (cacheNextElement()) {
					returnCacheAndErase();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				returnCacheAndErase();
			}
		}

		private double returnCacheAndErase()
		{
			assert elementCached;
			elementCached = false;
			return cached;
		}
	}

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator src;
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;
		private long cached = -1;
		boolean srcExhausted = false, elementCached = false;

		public OfLong(final EnhancedLongIterator src, final IntUnaryOperator indexMapping)
		{
			super(OptionalInt.empty());
			this.src = src;
			this.indexMapping = indexMapping;
		}

		private void init()
		{
			updateCheckpoint(indexMapping.applyAsInt(indexCount));
			cacheNextElement();
		}

		private void updateCheckpoint(final int newCheckpoint)
		{
			if (newCheckpoint <= checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			assert !srcExhausted && !elementCached;
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skip();
					iteratorCount++;
				} else {
					srcExhausted = true;
					return false;
				}
			}

			if (src.hasNext()) {
				cached = src.nextLong();
				elementCached = true;
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(++indexCount));
				return true;
			} else {
				srcExhausted = true;
				return false;
			}
		}

		@Override
		public boolean hasNext()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				return false;
			} else if (!elementCached) {
				return cacheNextElement();
			} else {
				return true;
			}
		}

		@Override
		public long nextLong()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			} else if (!elementCached) {
				if (cacheNextElement()) {
					return returnCacheAndErase();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				return returnCacheAndErase();
			}
		}

		@Override
		public void skip()
		{
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			} else if (!elementCached) {
				if (cacheNextElement()) {
					returnCacheAndErase();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				returnCacheAndErase();
			}
		}

		private long returnCacheAndErase()
		{
			assert elementCached;
			elementCached = false;
			return cached;
		}
	}
}