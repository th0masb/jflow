/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import com.github.maumay.jflow.utils.DoubleTup;
import com.github.maumay.jflow.utils.IntTup;
import com.github.maumay.jflow.utils.LongTup;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author thomasb
 *
 */
public final class ZipAdapter
{
	private ZipAdapter()
	{
	}

	public static final class OfObjects<E1, E2> extends
			AbstractIteratorBiAdapters.OfObject<AbstractEnhancedIterator<? extends E1>, AbstractEnhancedIterator<? extends E2>, Tup<E1, E2>>
	{
		public OfObjects(AbstractEnhancedIterator<? extends E1> sourceOne,
				AbstractEnhancedIterator<? extends E2> sourceTwo)
		{
			super(IteratorImplUtils.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			return getSourceOne().hasNext() && getSourceTwo().hasNext();
		}

		@Override
		public Tup<E1, E2> nextImpl()
		{
			return Tup.of(getSourceOne().nextImpl(), getSourceTwo().nextImpl());
		}

		@Override
		public void skipImpl()
		{
			getSourceOne().skipImpl();
			getSourceTwo().skipImpl();
		}
	}

	public static final class OfInts extends
			AbstractIteratorBiAdapters.OfObject<AbstractIntIterator, AbstractIntIterator, IntTup>
	{
		public OfInts(AbstractIntIterator sourceOne, AbstractIntIterator sourceTwo)
		{
			super(IteratorImplUtils.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			return getSourceOne().hasNext() && getSourceTwo().hasNext();
		}

		@Override
		public IntTup nextImpl()
		{
			return IntTup.of(getSourceOne().nextIntImpl(), getSourceTwo().nextIntImpl());
		}

		@Override
		public void skipImpl()
		{
			getSourceOne().skipImpl();
			getSourceTwo().skipImpl();
		}
	}

	public static final class OfLongs extends
			AbstractIteratorBiAdapters.OfObject<AbstractLongIterator, AbstractLongIterator, LongTup>
	{
		public OfLongs(AbstractLongIterator sourceOne, AbstractLongIterator sourceTwo)
		{
			super(IteratorImplUtils.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			return getSourceOne().hasNext() && getSourceTwo().hasNext();
		}

		@Override
		public LongTup nextImpl()
		{
			return LongTup.of(getSourceOne().nextLongImpl(), getSourceTwo().nextLongImpl());
		}

		@Override
		public void skipImpl()
		{
			getSourceOne().skipImpl();
			getSourceTwo().skipImpl();
		}
	}

	public static final class OfDoubles extends
			AbstractIteratorBiAdapters.OfObject<AbstractDoubleIterator, AbstractDoubleIterator, DoubleTup>
	{
		public OfDoubles(AbstractDoubleIterator sourceOne, AbstractDoubleIterator sourceTwo)
		{
			super(IteratorImplUtils.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			return getSourceOne().hasNext() && getSourceTwo().hasNext();
		}

		@Override
		public DoubleTup nextImpl()
		{
			return DoubleTup.of(getSourceOne().nextDoubleImpl(), getSourceTwo().nextDoubleImpl());
		}

		@Override
		public void skipImpl()
		{
			getSourceOne().skipImpl();
			getSourceTwo().skipImpl();
		}
	}
}
