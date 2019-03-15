/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;

/**
 * @author thomasb
 *
 */
public class EmptyIterator
{
	private EmptyIterator()
	{
	}

	private static final OfDouble EMPTY_DOUBLE = new OfDouble();
	private static final OfInt EMPTY_INT = new OfInt();
	private static final OfLong EMPTY_LONG = new OfLong();

	public static OfInt ofInt()
	{
		return EMPTY_INT;
	}

	public static OfLong ofLong()
	{
		return EMPTY_LONG;
	}

	public static OfDouble ofDouble()
	{
		return EMPTY_DOUBLE;
	}

	public static final class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		public OfObject()
		{
			super(new KnownSize(0));
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public E nextImpl()
		{
			throw new NoSuchElementException();
		}

		@Override
		public void skipImpl()
		{
			throw new NoSuchElementException();
		}
	}

	public static final class OfInt extends AbstractIntIterator
	{
		OfInt()
		{
			super(new KnownSize(0));
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public int nextIntImpl()
		{
			throw new NoSuchElementException();
		}

		@Override
		public void skipImpl()
		{
			throw new NoSuchElementException();
		}
	}

	public static final class OfLong extends AbstractLongIterator
	{
		OfLong()
		{
			super(new KnownSize(0));
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public long nextLongImpl()
		{
			throw new NoSuchElementException();
		}

		@Override
		public void skipImpl()
		{
			throw new NoSuchElementException();
		}
	}

	public static final class OfDouble extends AbstractDoubleIterator
	{
		OfDouble()
		{
			super(new KnownSize(0));
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public double nextDoubleImpl()
		{
			throw new NoSuchElementException();
		}

		@Override
		public void skipImpl()
		{
			throw new NoSuchElementException();
		}
	}
}
