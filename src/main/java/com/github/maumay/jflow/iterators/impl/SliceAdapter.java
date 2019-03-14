/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.IntUnaryOperator;

import com.github.maumay.jflow.iterators.EnhancedIterator;

/**
 * @author t
 *
 */
public class SliceAdapter
{
	private SliceAdapter()
	{
	}

	public static class OfObject<E>
			extends AbstractIteratorAdapter.OfObject<AbstractEnhancedIterator<E>, E>
	{
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;

		private E cached = null;
		boolean srcExhausted = false;

		public OfObject(EnhancedIterator<E> src, IntUnaryOperator indexMapping)
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

		private void updateCheckpoint(int newCheckpoint)
		{
			if (newCheckpoint < checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			assert !srcExhausted && cached == null;
			AbstractEnhancedIterator<E> src = getSource();
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skipImpl();
					iteratorCount++;
				} else {
					srcExhausted = true;
					return false;
				}
			}

			if (src.hasNext()) {
				cached = src.nextImpl();
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
		public E nextImpl()
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
		public void skipImpl()
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
}
