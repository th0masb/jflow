/**
 * 
 */
package com.github.maumay.jflow.impl;

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
			AbstractIteratorBiAdapters.OfObject<AbstractRichIterator<? extends E1>, AbstractRichIterator<? extends E2>, Tup<E1, E2>>
	{
		public OfObjects(AbstractRichIterator<? extends E1> sourceOne,
				AbstractRichIterator<? extends E2> sourceTwo)
		{
			super(IteratorSizes.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
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
		public void forwardImpl()
		{
			getSourceOne().forwardImpl();
			getSourceTwo().forwardImpl();
		}
	}

	public static final class OfInts extends
			AbstractIteratorBiAdapters.OfObject<AbstractIntIterator, AbstractIntIterator, IntTup>
	{
		public OfInts(AbstractIntIterator sourceOne, AbstractIntIterator sourceTwo)
		{
			super(IteratorSizes.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
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
		public void forwardImpl()
		{
			getSourceOne().forwardImpl();
			getSourceTwo().forwardImpl();
		}
	}

	public static final class OfLongs extends
			AbstractIteratorBiAdapters.OfObject<AbstractLongIterator, AbstractLongIterator, LongTup>
	{
		public OfLongs(AbstractLongIterator sourceOne, AbstractLongIterator sourceTwo)
		{
			super(IteratorSizes.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
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
		public void forwardImpl()
		{
			getSourceOne().forwardImpl();
			getSourceTwo().forwardImpl();
		}
	}

	public static final class OfDoubles extends
			AbstractIteratorBiAdapters.OfObject<AbstractDoubleIterator, AbstractDoubleIterator, DoubleTup>
	{
		public OfDoubles(AbstractDoubleIterator sourceOne, AbstractDoubleIterator sourceTwo)
		{
			super(IteratorSizes.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
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
		public void forwardImpl()
		{
			getSourceOne().forwardImpl();
			getSourceTwo().forwardImpl();
		}
	}
}
