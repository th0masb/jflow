/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

/**
 * @author thomasb
 *
 */
public final class ConcatenationAdapter
{
	private ConcatenationAdapter()
	{
	}

	public static final class OfObject<E> extends AbstractIteratorBiAdapters.OfObject<E, E, E>
	{
		public OfObject(AbstractEnhancedIterator<? extends E> sourceOne,
				AbstractEnhancedIterator<? extends E> sourceTwo)
		{
			super(IteratorSizeUtils.sum(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			return getSourceOne().hasNext() || getSourceTwo().hasNext();
		}

		@Override
		public E nextImpl()
		{
			return getSourceOne().hasNext() ? getSourceOne().nextImpl() : getSourceTwo().nextImpl();
		}

		@Override
		public void skipImpl()
		{
			if (getSourceOne().hasNext()) {
				getSourceOne().skipImpl();
			} else {
				getSourceTwo().skipImpl();
			}
		}
	}

	public static final class OfInt extends AbstractIteratorBiAdapters.BinaryInt
	{
		public OfInt(AbstractIntIterator sourceOne, AbstractIntIterator sourceTwo)
		{
			super(IteratorSizeUtils.sum(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			return getSourceOne().hasNext() || getSourceTwo().hasNext();
		}

		@Override
		public int nextIntImpl()
		{
			return getSourceOne().hasNext() ? getSourceOne().nextIntImpl()
					: getSourceTwo().nextIntImpl();
		}

		@Override
		public void skipImpl()
		{
			if (getSourceOne().hasNext()) {
				getSourceOne().skipImpl();
			} else {
				getSourceTwo().skipImpl();
			}
		}
	}

	public static final class OfLong extends AbstractIteratorBiAdapters.BinaryLong
	{
		public OfLong(AbstractLongIterator sourceOne, AbstractLongIterator sourceTwo)
		{
			super(IteratorSizeUtils.sum(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			return getSourceOne().hasNext() || getSourceTwo().hasNext();
		}

		@Override
		public long nextLongImpl()
		{
			return getSourceOne().hasNext() ? getSourceOne().nextLongImpl()
					: getSourceTwo().nextLongImpl();
		}

		@Override
		public void skipImpl()
		{
			if (getSourceOne().hasNext()) {
				getSourceOne().skipImpl();
			} else {
				getSourceTwo().skipImpl();
			}
		}
	}

	public static final class OfDouble extends AbstractIteratorBiAdapters.BinaryDouble
	{
		public OfDouble(AbstractDoubleIterator sourceOne, AbstractDoubleIterator sourceTwo)
		{
			super(IteratorSizeUtils.sum(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			return getSourceOne().hasNext() || getSourceTwo().hasNext();
		}

		@Override
		public double nextDoubleImpl()
		{
			return getSourceOne().hasNext() ? getSourceOne().nextDoubleImpl()
					: getSourceTwo().nextDoubleImpl();
		}

		@Override
		public void skipImpl()
		{
			if (getSourceOne().hasNext()) {
				getSourceOne().skipImpl();
			} else {
				getSourceTwo().skipImpl();
			}
		}
	}
}
