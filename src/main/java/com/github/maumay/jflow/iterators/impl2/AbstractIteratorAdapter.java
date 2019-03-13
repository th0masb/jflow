/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

/**
 * @author thomasb
 *
 */
public final class AbstractIteratorAdapter
{
	private AbstractIteratorAdapter()
	{
	}

	public static abstract class OfObject<E, R> extends AbstractEnhancedIterator<R>
	{
		private final AbstractEnhancedIterator<? extends E> source;

		public OfObject(AbstractIteratorSize size, AbstractEnhancedIterator<? extends E> source)
		{
			super(size);
			this.source = source;

			// The source is now considered locked and can only be consumed indirectly via
			// this iterator.
			source.relinquishOwnership();
		}

		protected final AbstractEnhancedIterator<? extends E> getSource()
		{
			return source;
		}
	}

	public static abstract class OfInt extends AbstractIntIterator
	{
		private final AbstractIntIterator source;

		public OfInt(AbstractIteratorSize size, AbstractIntIterator source)
		{
			super(size);
			this.source = source;

			// The source is now considered locked and can only be consumed indirectly via
			// this iterator.
			source.relinquishOwnership();
		}

		protected final AbstractIntIterator getSource()
		{
			return source;
		}
	}

	public static abstract class OfLong extends AbstractLongIterator
	{
		private final AbstractLongIterator source;

		public OfLong(AbstractIteratorSize size, AbstractLongIterator source)
		{
			super(size);
			this.source = source;

			// The source is now considered locked and can only be consumed indirectly via
			// this iterator.
			source.relinquishOwnership();
		}

		protected final AbstractLongIterator getSource()
		{
			return source;
		}
	}

	public static abstract class OfDouble extends AbstractDoubleIterator
	{
		private final AbstractDoubleIterator source;

		public OfDouble(AbstractIteratorSize size, AbstractDoubleIterator source)
		{
			super(size);
			this.source = source;

			// The source is now considered locked and can only be consumed indirectly via
			// this iterator.
			source.relinquishOwnership();
		}

		protected final AbstractDoubleIterator getSource()
		{
			return source;
		}
	}
}
