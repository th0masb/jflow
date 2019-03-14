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

	public static abstract class OfObject<S1 extends AbstractIterator, S2 extends AbstractIterator, R>
			extends AbstractEnhancedIterator<R>
	{
		private final S1 sourceOne;
		private final S2 sourceTwo;

		public OfObject(AbstractIteratorSize size, S1 sourceOne, S2 sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected S1 getSourceOne()
		{
			return sourceOne;
		}

		protected S2 getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class OfInt<S1 extends AbstractIterator, S2 extends AbstractIterator>
			extends AbstractIntIterator
	{
		private final S1 sourceOne;
		private final S2 sourceTwo;

		public OfInt(AbstractIteratorSize size, S1 sourceOne, S2 sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected S1 getSourceOne()
		{
			return sourceOne;
		}

		protected S2 getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class OfLong<S1 extends AbstractIterator, S2 extends AbstractIterator>
			extends AbstractLongIterator
	{
		private final S1 sourceOne;
		private final S2 sourceTwo;

		public OfLong(AbstractIteratorSize size, S1 sourceOne, S2 sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected S1 getSourceOne()
		{
			return sourceOne;
		}

		protected S2 getSourceTwo()
		{
			return sourceTwo;
		}
	}

	public static abstract class OfDouble<S1 extends AbstractIterator, S2 extends AbstractIterator>
			extends AbstractDoubleIterator
	{
		private final S1 sourceOne;
		private final S2 sourceTwo;

		public OfDouble(AbstractIteratorSize size, S1 sourceOne, S2 sourceTwo)
		{
			super(size);
			this.sourceOne = Objects.requireNonNull(sourceOne);
			this.sourceTwo = Objects.requireNonNull(sourceTwo);
			sourceOne.relinquishOwnership();
			sourceTwo.relinquishOwnership();
		}

		protected S1 getSourceOne()
		{
			return sourceOne;
		}

		protected S2 getSourceTwo()
		{
			return sourceTwo;
		}
	}

}
