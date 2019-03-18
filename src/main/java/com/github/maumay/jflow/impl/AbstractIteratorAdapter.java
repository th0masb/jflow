/**
 * 
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public class AbstractIteratorAdapter
{
	private AbstractIteratorAdapter()
	{
	}

	public static abstract class OfObject<S extends AbstractIterator, R>
			extends AbstractRichIterator<R>
	{
		private final S source;

		public OfObject(AbstractIteratorSize size, S source)
		{
			super(size);
			assert size.getType() == SizeType.UNKNOWN
					|| size != source.getSize() : "Must not share size instance";
			this.source = source;

			// The source is now considered locked and can only be consumed indirectly via
			// this iterator.
			source.relinquishOwnership();
		}

		protected final S getSource()
		{
			return source;
		}
	}

	public static abstract class OfInt<S extends AbstractIterator> extends AbstractIntIterator
	{
		private final S source;

		public OfInt(AbstractIteratorSize size, S source)
		{
			super(size);
			assert size.getType() == SizeType.UNKNOWN
					|| size != source.getSize() : "Must not share size instance";
			this.source = source;

			// The source is now considered locked and can only be consumed indirectly via
			// this iterator.
			source.relinquishOwnership();
		}

		protected final S getSource()
		{
			return source;
		}
	}

	public static abstract class OfLong<S extends AbstractIterator> extends AbstractLongIterator
	{
		private final S source;

		public OfLong(AbstractIteratorSize size, S source)
		{
			super(size);
			assert size.getType() == SizeType.UNKNOWN
					|| size != source.getSize() : "Must not share size instance";
			this.source = source;

			// The source is now considered locked and can only be consumed indirectly via
			// this iterator.
			source.relinquishOwnership();
		}

		protected final S getSource()
		{
			return source;
		}
	}

	public static abstract class OfDouble<S extends AbstractIterator> extends AbstractDoubleIterator
	{
		private final S source;

		public OfDouble(AbstractIteratorSize size, S source)
		{
			super(size);
			assert size.getType() == SizeType.UNKNOWN
					|| size != source.getSize() : "Must not share size instance";
			this.source = source;

			// The source is now considered locked and can only be consumed indirectly via
			// this iterator.
			source.relinquishOwnership();
		}

		protected final S getSource()
		{
			return source;
		}
	}
}
