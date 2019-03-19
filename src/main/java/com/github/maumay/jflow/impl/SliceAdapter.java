/**
 * 
 */
package com.github.maumay.jflow.impl;

import static com.github.maumay.jflow.impl.IteratorSizes.requireNonNegative;

import java.util.NoSuchElementException;
import java.util.function.IntUnaryOperator;

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
			extends AbstractIteratorAdapter.OfObject<AbstractRichIterator<E>, E>
	{
		private final IntUnaryOperator indexMapping;

		private int indexCount, checkpoint, iteratorCount;
		private boolean elementCached;
		private E cached;

		public OfObject(AbstractRichIterator<E> src, IntUnaryOperator indexMapping)
		{
			super(src.getSize().filter(), src);
			this.indexMapping = indexMapping;
			this.checkpoint = requireNonNegative(indexMapping.applyAsInt(0));
			this.indexCount = 1;
			this.iteratorCount = 0;
			this.cached = null;
			this.elementCached = false;
		}

		private void updateCheckpoint(int newCheckpoint)
		{
			if (newCheckpoint <= checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			AbstractRichIterator<E> src = getSource();
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skipImpl();
					iteratorCount++;
				} else {
					return false;
				}
			}

			if (src.hasNext()) {
				elementCached = true;
				cached = src.nextImpl();
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(indexCount++));
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean hasNext()
		{
			return elementCached || cacheNextElement();
		}

		@Override
		public E nextImpl()
		{
			if (elementCached || cacheNextElement()) {
				return consumeCache();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (elementCached || cacheNextElement()) {
				consumeCache();
			} else {
				throw new NoSuchElementException();
			}
		}

		private E consumeCache()
		{
			elementCached = false;
			return cached;
		}
	}

	public static class OfInt extends AbstractIteratorAdapter.OfInt<AbstractIntIterator>
	{
		private final IntUnaryOperator indexMapping;

		private int indexCount, checkpoint, iteratorCount;
		private boolean elementCached;
		private int cached;

		public OfInt(AbstractIntIterator src, IntUnaryOperator indexMapping)
		{
			super(src.getSize().filter(), src);
			this.indexMapping = indexMapping;
			this.checkpoint = requireNonNegative(indexMapping.applyAsInt(0));
			this.indexCount = 1;
			this.iteratorCount = 0;
			this.cached = 0;
			this.elementCached = false;
		}

		private void updateCheckpoint(int newCheckpoint)
		{
			if (newCheckpoint <= checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			AbstractIntIterator src = getSource();
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skipImpl();
					iteratorCount++;
				} else {
					return false;
				}
			}

			if (src.hasNext()) {
				elementCached = true;
				cached = src.nextIntImpl();
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(indexCount++));
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean hasNext()
		{
			return elementCached || cacheNextElement();
		}

		@Override
		public int nextIntImpl()
		{
			if (elementCached || cacheNextElement()) {
				return consumeCache();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (elementCached || cacheNextElement()) {
				consumeCache();
			} else {
				throw new NoSuchElementException();
			}
		}

		private int consumeCache()
		{
			elementCached = false;
			return cached;
		}
	}

	public static class OfLong extends AbstractIteratorAdapter.OfLong<AbstractLongIterator>
	{
		private final IntUnaryOperator indexMapping;

		private int indexCount, checkpoint, iteratorCount;
		private boolean elementCached;
		private long cached;

		public OfLong(AbstractLongIterator src, IntUnaryOperator indexMapping)
		{
			super(src.getSize().filter(), src);
			this.indexMapping = indexMapping;
			this.checkpoint = requireNonNegative(indexMapping.applyAsInt(0));
			this.indexCount = 1;
			this.iteratorCount = 0;
			this.cached = 0;
			this.elementCached = false;
		}

		private void updateCheckpoint(int newCheckpoint)
		{
			if (newCheckpoint <= checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			AbstractLongIterator src = getSource();
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skipImpl();
					iteratorCount++;
				} else {
					return false;
				}
			}

			if (src.hasNext()) {
				elementCached = true;
				cached = src.nextLongImpl();
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(indexCount++));
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean hasNext()
		{
			return elementCached || cacheNextElement();
		}

		@Override
		public long nextLongImpl()
		{
			if (elementCached || cacheNextElement()) {
				return consumeCache();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (elementCached || cacheNextElement()) {
				consumeCache();
			} else {
				throw new NoSuchElementException();
			}
		}

		private long consumeCache()
		{
			elementCached = false;
			return cached;
		}
	}

	public static class OfDouble extends AbstractIteratorAdapter.OfDouble<AbstractDoubleIterator>
	{
		private final IntUnaryOperator indexMapping;

		private int indexCount, checkpoint, iteratorCount;
		private boolean elementCached;
		private double cached;

		public OfDouble(AbstractDoubleIterator src, IntUnaryOperator indexMapping)
		{
			super(src.getSize().filter(), src);
			this.indexMapping = indexMapping;
			this.checkpoint = requireNonNegative(indexMapping.applyAsInt(0));
			this.indexCount = 1;
			this.iteratorCount = 0;
			this.cached = 0;
			this.elementCached = false;
		}

		private void updateCheckpoint(int newCheckpoint)
		{
			if (newCheckpoint <= checkpoint) {
				throw new IllegalStateException();
			}
			checkpoint = newCheckpoint;
		}

		private boolean cacheNextElement()
		{
			AbstractDoubleIterator src = getSource();
			while (iteratorCount < checkpoint) {
				if (src.hasNext()) {
					src.skipImpl();
					iteratorCount++;
				} else {
					return false;
				}
			}

			if (src.hasNext()) {
				elementCached = true;
				cached = src.nextDoubleImpl();
				iteratorCount++;
				updateCheckpoint(indexMapping.applyAsInt(indexCount++));
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean hasNext()
		{
			return elementCached || cacheNextElement();
		}

		@Override
		public double nextDoubleImpl()
		{
			if (elementCached || cacheNextElement()) {
				return consumeCache();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (elementCached || cacheNextElement()) {
				consumeCache();
			} else {
				throw new NoSuchElementException();
			}
		}

		private double consumeCache()
		{
			elementCached = false;
			return cached;
		}
	}
}
