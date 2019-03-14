/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.Objects;

/**
 * @author thomasb
 *
 */
public class AbstractIteratorBiAdapters
{
	private AbstractIteratorBiAdapters()
	{
	}

	public static abstract class OfObject<E1, E2, R> extends AbstractEnhancedIterator<R>
	{
		private final AbstractEnhancedIterator<? extends E1> sourceOne;
		private final AbstractEnhancedIterator<? extends E2> sourceTwo;

		public OfObject(AbstractIteratorSize size, AbstractEnhancedIterator<? extends E1> sourceOne,
				AbstractEnhancedIterator<? extends E2> sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected AbstractEnhancedIterator<? extends E1> getSourceOne()
		{
			return sourceOne;
		}

		protected AbstractEnhancedIterator<? extends E2> getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class BinaryInt extends AbstractIntIterator
	{
		private final AbstractIntIterator sourceOne, sourceTwo;

		public BinaryInt(AbstractIteratorSize size, AbstractIntIterator sourceOne,
				AbstractIntIterator sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected AbstractIntIterator getSourceOne()
		{
			return sourceOne;
		}

		protected AbstractIntIterator getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class IntToObject<R> extends AbstractEnhancedIterator<R>
	{
		private final AbstractIntIterator sourceOne, sourceTwo;

		public IntToObject(AbstractIteratorSize size, AbstractIntIterator sourceOne,
				AbstractIntIterator sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected AbstractIntIterator getSourceOne()
		{
			return sourceOne;
		}

		protected AbstractIntIterator getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class BinaryLong extends AbstractLongIterator
	{
		private final AbstractLongIterator sourceOne, sourceTwo;

		public BinaryLong(AbstractIteratorSize size, AbstractLongIterator sourceOne,
				AbstractLongIterator sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected AbstractLongIterator getSourceOne()
		{
			return sourceOne;
		}

		protected AbstractLongIterator getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class LongToObject<R> extends AbstractEnhancedIterator<R>
	{
		private final AbstractLongIterator sourceOne, sourceTwo;

		public LongToObject(AbstractIteratorSize size, AbstractLongIterator sourceOne,
				AbstractLongIterator sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected AbstractLongIterator getSourceOne()
		{
			return sourceOne;
		}

		protected AbstractLongIterator getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class BinaryDouble extends AbstractDoubleIterator
	{
		private final AbstractDoubleIterator sourceOne, sourceTwo;

		public BinaryDouble(AbstractIteratorSize size, AbstractDoubleIterator sourceOne,
				AbstractDoubleIterator sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected AbstractDoubleIterator getSourceOne()
		{
			return sourceOne;
		}

		protected AbstractDoubleIterator getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class DoubleToObject<R> extends AbstractEnhancedIterator<R>
	{
		private final AbstractDoubleIterator sourceOne, sourceTwo;

		public DoubleToObject(AbstractIteratorSize size, AbstractDoubleIterator sourceOne,
				AbstractDoubleIterator sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected AbstractDoubleIterator getSourceOne()
		{
			return sourceOne;
		}

		protected AbstractDoubleIterator getSourceTwo()
		{
			return sourceTwo;
		}
	}
}
