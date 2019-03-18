/**
 * 
 */
package com.github.maumay.jflow.impl;

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

	public static final class OfObject<E>
			extends AbstractIteratorAdapter.OfObject<AbstractRichIterator<E>, E>
	{
		private final int takeCount;
		private int count;

		public OfObject(AbstractRichIterator<E> source, int takeCount)
		{
			super(IteratorImplUtils.min(source.getSize(), takeCount), source);
			this.takeCount = IteratorImplUtils.requireNonNegative(takeCount);
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
			super(IteratorImplUtils.min(source.getSize(), takeCount), source);
			this.takeCount = IteratorImplUtils.requireNonNegative(takeCount);
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
			super(IteratorImplUtils.min(source.getSize(), takeCount), source);
			this.takeCount = IteratorImplUtils.requireNonNegative(takeCount);
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
			super(IteratorImplUtils.min(source.getSize(), takeCount), source);
			this.takeCount = IteratorImplUtils.requireNonNegative(takeCount);
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
