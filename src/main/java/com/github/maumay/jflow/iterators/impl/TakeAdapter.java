/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;

/**
 * @author thomasb
 *
 */
public class TakeAdapter
{
	private TakeAdapter()
	{
	}

	static AbstractIteratorSize computeNextSize(AbstractIteratorSize size, int takeCount)
	{
		switch (size.getType()) {
		case EXACT: {
			KnownSize x = (KnownSize) size;
			return new KnownSize(Math.max(0, x.getValue() - takeCount));
		}
		case UPPER_BOUND: {
			UpperBound x = (UpperBound) size;
			return new UpperBound(Math.min(x.getValue(), takeCount));
		}
		case LOWER_BOUND: {
			LowerBound x = (LowerBound) size;
			return x.getValue() >= takeCount ? new KnownSize(takeCount)
					: new BoundedSize(x.getValue(), takeCount);
		}
		case BOUNDED: {
			BoundedSize x = (BoundedSize) size;
			return x.lowerBound() >= takeCount ? new KnownSize(takeCount)
					: new BoundedSize(x.lowerBound(), Math.min(takeCount, x.upperBound()));
		}
		case UNKNOWN:
			return new UpperBound(takeCount);
		default:
			throw new RuntimeException();
		}
	}

	public static final class OfObject<E>
			extends AbstractIteratorAdapter.OfObject<AbstractEnhancedIterator<E>, E>
	{
		private final int takeCount;
		private int count;

		public OfObject(AbstractEnhancedIterator<E> source, int takeCount)
		{
			super(computeNextSize(source.getSize(), takeCount), source);
			this.takeCount = takeCount;
			this.count = 0;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && getSource().hasNext();
		}

		@Override
		public E nextImpl()
		{
			if (count++ < takeCount) {
				return getSource().nextImpl();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (count++ < takeCount) {
				getSource().skipImpl();
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static final class OfInt extends AbstractIteratorAdapter.OfInt<AbstractIntIterator>
	{
		private final int takeCount;
		private int count;

		public OfInt(AbstractIntIterator source, int takeCount)
		{
			super(computeNextSize(source.getSize(), takeCount), source);
			this.takeCount = takeCount;
			this.count = 0;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && getSource().hasNext();
		}

		@Override
		public int nextIntImpl()
		{
			if (count++ < takeCount) {
				return getSource().nextIntImpl();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (count++ < takeCount) {
				getSource().skipImpl();
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static final class OfLong extends AbstractIteratorAdapter.OfLong<AbstractLongIterator>
	{
		private final int takeCount;
		private int count;

		public OfLong(AbstractLongIterator source, int takeCount)
		{
			super(computeNextSize(source.getSize(), takeCount), source);
			this.takeCount = takeCount;
			this.count = 0;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && getSource().hasNext();
		}

		@Override
		public long nextLongImpl()
		{
			if (count++ < takeCount) {
				return getSource().nextLongImpl();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (count++ < takeCount) {
				getSource().skipImpl();
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static final class OfDouble
			extends AbstractIteratorAdapter.OfDouble<AbstractDoubleIterator>
	{
		private final int takeCount;
		private int count;

		public OfDouble(AbstractDoubleIterator source, int takeCount)
		{
			super(computeNextSize(source.getSize(), takeCount), source);
			this.takeCount = takeCount;
			this.count = 0;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && getSource().hasNext();
		}

		@Override
		public double nextDoubleImpl()
		{
			if (count++ < takeCount) {
				return getSource().nextDoubleImpl();
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (count++ < takeCount) {
				getSource().skipImpl();
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}
