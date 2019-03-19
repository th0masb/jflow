/**
 * 
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public class SkipAdapter
{
	private SkipAdapter()
	{
	}

	public static final class OfObject<E>
			extends AbstractIteratorAdapter.OfObject<AbstractRichIterator<E>, E>
	{
		private final int skipCount;
		private boolean skipped;

		public OfObject(AbstractRichIterator<E> source, int skipcount)
		{
			super(IteratorSizes.subtract(source.getSize(), skipcount), source);
			this.skipCount = IteratorSizes.requireNonNegative(skipcount);
			this.skipped = false;
		}

		private void performSkip()
		{
			AbstractRichIterator<E> src = getSource();
			for (int count = 0; count < skipCount && src.hasNext(); count++) {
				src.skipImpl();
			}
			skipped = true;
		}

		@Override
		public boolean hasNext()
		{
			if (!skipped) {
				performSkip();
			}
			return getSource().hasNext();
		}

		@Override
		public E nextImpl()
		{
			if (!skipped) {
				performSkip();
			}
			return getSource().nextImpl();
		}

		@Override
		public void skipImpl()
		{
			if (!skipped) {
				performSkip();
			}
			getSource().skipImpl();
		}
	}

	public static final class OfInt extends AbstractIteratorAdapter.OfInt<AbstractIntIterator>
	{
		private final int skipCount;
		private boolean skipped;

		public OfInt(AbstractIntIterator source, int skipcount)
		{
			super(IteratorSizes.subtract(source.getSize(), skipcount), source);
			this.skipCount = IteratorSizes.requireNonNegative(skipcount);
			this.skipped = false;
		}

		private void performSkip()
		{
			AbstractIntIterator src = getSource();
			for (int count = 0; count < skipCount && src.hasNext(); count++) {
				src.skipImpl();
			}
			skipped = true;
		}

		@Override
		public boolean hasNext()
		{
			if (!skipped) {
				performSkip();
			}
			return getSource().hasNext();
		}

		@Override
		public int nextIntImpl()
		{
			if (!skipped) {
				performSkip();
			}
			return getSource().nextIntImpl();
		}

		@Override
		public void skipImpl()
		{
			if (!skipped) {
				performSkip();
			}
			getSource().skipImpl();
		}
	}

	public static final class OfLong extends AbstractIteratorAdapter.OfLong<AbstractLongIterator>
	{
		private final int skipCount;
		private boolean skipped;

		public OfLong(AbstractLongIterator source, int skipcount)
		{
			super(IteratorSizes.subtract(source.getSize(), skipcount), source);
			this.skipCount = IteratorSizes.requireNonNegative(skipcount);
			this.skipped = false;
		}

		private void performSkip()
		{
			AbstractLongIterator src = getSource();
			for (int count = 0; count < skipCount && src.hasNext(); count++) {
				src.skipImpl();
			}
			skipped = true;
		}

		@Override
		public boolean hasNext()
		{
			if (!skipped) {
				performSkip();
			}
			return getSource().hasNext();
		}

		@Override
		public long nextLongImpl()
		{
			if (!skipped) {
				performSkip();
			}
			return getSource().nextLongImpl();
		}

		@Override
		public void skipImpl()
		{
			if (!skipped) {
				performSkip();
			}
			getSource().skipImpl();
		}
	}

	public static final class OfDouble
			extends AbstractIteratorAdapter.OfDouble<AbstractDoubleIterator>
	{
		private final int skipCount;
		private boolean skipped;

		public OfDouble(AbstractDoubleIterator source, int skipcount)
		{
			super(IteratorSizes.subtract(source.getSize(), skipcount), source);
			this.skipCount = IteratorSizes.requireNonNegative(skipcount);
			this.skipped = false;
		}

		private void performSkip()
		{
			AbstractDoubleIterator src = getSource();
			for (int count = 0; count < skipCount && src.hasNext(); count++) {
				src.skipImpl();
			}
			skipped = true;
		}

		@Override
		public boolean hasNext()
		{
			if (!skipped) {
				performSkip();
			}
			return getSource().hasNext();
		}

		@Override
		public double nextDoubleImpl()
		{
			if (!skipped) {
				performSkip();
			}
			return getSource().nextDoubleImpl();
		}

		@Override
		public void skipImpl()
		{
			if (!skipped) {
				performSkip();
			}
			getSource().skipImpl();
		}
	}
}
