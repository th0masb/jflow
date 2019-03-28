/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Objects;

/**
 * Contains a set of static classes useful for implementing iterator
 * bi-adapters. These are iterators which are constructed from two source
 * iterators.
 * 
 * @author thomasb
 */
public class AbstractIteratorBiAdapters
{
	private AbstractIteratorBiAdapters()
	{
	}

	/**
	 * An object iterator ({@link AbstractRichIterator} which adapts two provided
	 * source iterators and removes their ownership.
	 * 
	 * @author t
	 *
	 * @param <S1> The type of the first source iterator.
	 * @param <S2> The type of the second source iterator.
	 * @param <R> The element type of this iterator.
	 */
	public static abstract class OfObject<S1 extends AbstractIterator, S2 extends AbstractIterator, R>
			extends AbstractRichIterator<R>
	{
		private final S1 sourceOne;
		private final S2 sourceTwo;

		public OfObject(AbstractIteratorSize size, S1 sourceOne, S2 sourceTwo)
		{
			super(size);
			assert size.isInfinite()
					|| size != sourceOne.getSize() : "Must not share size instance";
			assert size.isInfinite()
					|| size != sourceTwo.getSize() : "Must not share size instance";
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

	/**
	 * An int iterator ({@link AbstractIntIterator} which adapts two provided source
	 * iterators and removes their ownership.
	 * 
	 * @author t
	 *
	 * @param <S1> The type of the first source iterator.
	 * @param <S2> The type of the second source iterator.
	 */
	public static abstract class OfInt<S1 extends AbstractIterator, S2 extends AbstractIterator>
			extends AbstractIntIterator
	{
		private final S1 sourceOne;
		private final S2 sourceTwo;

		public OfInt(AbstractIteratorSize size, S1 sourceOne, S2 sourceTwo)
		{
			super(size);
			assert size.isInfinite()
					|| size != sourceOne.getSize() : "Must not share size instance";
			assert size.isInfinite()
					|| size != sourceTwo.getSize() : "Must not share size instance";
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

	/**
	 * A long iterator ({@link AbstractLongIterator} which adapts two provided
	 * source iterators and removes their ownership.
	 * 
	 * @author t
	 *
	 * @param <S1> The type of the first source iterator.
	 * @param <S2> The type of the second source iterator.
	 */
	public static abstract class OfLong<S1 extends AbstractIterator, S2 extends AbstractIterator>
			extends AbstractLongIterator
	{
		private final S1 sourceOne;
		private final S2 sourceTwo;

		public OfLong(AbstractIteratorSize size, S1 sourceOne, S2 sourceTwo)
		{
			super(size);
			assert size.isInfinite()
					|| size != sourceOne.getSize() : "Must not share size instance";
			assert size.isInfinite()
					|| size != sourceTwo.getSize() : "Must not share size instance";
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

	/**
	 * A double iterator ({@link AbstractDoubleIterator} which adapts two provided
	 * source iterators and removes their ownership.
	 * 
	 * @author t
	 *
	 * @param <S1> The type of the first source iterator.
	 * @param <S2> The type of the second source iterator.
	 */
	public static abstract class OfDouble<S1 extends AbstractIterator, S2 extends AbstractIterator>
			extends AbstractDoubleIterator
	{
		private final S1 sourceOne;
		private final S2 sourceTwo;

		public OfDouble(AbstractIteratorSize size, S1 sourceOne, S2 sourceTwo)
		{
			super(size);
			assert size.isInfinite()
					|| size != sourceOne.getSize() : "Must not share size instance";
			assert size.isInfinite()
					|| size != sourceTwo.getSize() : "Must not share size instance";
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
