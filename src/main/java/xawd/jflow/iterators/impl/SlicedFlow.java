/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

/**
 * @author t
 *
 */
public final class SlicedFlow
{
	private SlicedFlow() {}

	public static class OfObject<E> extends AbstractFlow<E>
	{
		private final Flow<E> src;
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;
		private E cached = null;
		boolean srcExhausted = false;

		public OfObject(final Flow<E> src, final IntUnaryOperator indexMapping)
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
				}
				else {
					srcExhausted = true;
					return false;
				}
			}

			if (src.hasNext()) {
				cached = src.next();
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(++indexCount));
				return true;
			}
			else {
				srcExhausted = true;
				return false;
			}
		}

		@Override
		public boolean hasNext() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				return false;
			}
			else if (cached == null) {
				return cacheNextElement();
			}
			else {
				return true;
			}
		}

		@Override
		public E next() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			}
			else if (cached == null) {
				if (cacheNextElement()) {
					return returnCacheAndErase();
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else {
				return returnCacheAndErase();
			}
		}

		@Override
		public void skip() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			}
			else if (cached == null) {
				if (cacheNextElement()) {
					returnCacheAndErase();
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else {
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

	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow src;
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;
		private int cached = -1;
		boolean srcExhausted = false, elementCached = false;

		public OfInt(final IntFlow src, final IntUnaryOperator indexMapping)
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
				}
				else {
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
			}
			else {
				srcExhausted = true;
				return false;
			}
		}

		@Override
		public boolean hasNext() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				return false;
			}
			else if (!elementCached) {
				return cacheNextElement();
			}
			else {
				return true;
			}
		}

		@Override
		public int nextInt() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			}
			else if (!elementCached) {
				if (cacheNextElement()) {
					return returnCacheAndErase();
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else {
				return returnCacheAndErase();
			}
		}

		@Override
		public void skip() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			}
			else if (!elementCached) {
				if (cacheNextElement()) {
					returnCacheAndErase();
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else {
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

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow src;
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;
		private double cached = -1;
		boolean srcExhausted = false, elementCached = false;

		public OfDouble(final DoubleFlow src, final IntUnaryOperator indexMapping)
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
				}
				else {
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
			}
			else {
				srcExhausted = true;
				return false;
			}
		}

		@Override
		public boolean hasNext() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				return false;
			}
			else if (!elementCached) {
				return cacheNextElement();
			}
			else {
				return true;
			}
		}

		@Override
		public double nextDouble() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			}
			else if (!elementCached) {
				if (cacheNextElement()) {
					return returnCacheAndErase();
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else {
				return returnCacheAndErase();
			}
		}

		@Override
		public void skip() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			}
			else if (!elementCached) {
				if (cacheNextElement()) {
					returnCacheAndErase();
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else {
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

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow src;
		private final IntUnaryOperator indexMapping;

		private int indexCount = 0;
		private int checkpoint = -1;
		private int iteratorCount = 0;
		private long cached = -1;
		boolean srcExhausted = false, elementCached = false;

		public OfLong(final LongFlow src, final IntUnaryOperator indexMapping)
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
				}
				else {
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
			}
			else {
				srcExhausted = true;
				return false;
			}
		}

		@Override
		public boolean hasNext() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				return false;
			}
			else if (!elementCached) {
				return cacheNextElement();
			}
			else {
				return true;
			}
		}

		@Override
		public long nextLong() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			}
			else if (!elementCached) {
				if (cacheNextElement()) {
					return returnCacheAndErase();
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else {
				return returnCacheAndErase();
			}
		}

		@Override
		public void skip() {
			if (checkpoint < 0) {
				init();
			}

			if (srcExhausted) {
				throw new NoSuchElementException();
			}
			else if (!elementCached) {
				if (cacheNextElement()) {
					returnCacheAndErase();
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else {
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
