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

	public static OfInt ofInt()
	{
		return new OfInt();
	}

	public static OfLong ofLong()
	{
		return new OfLong();
	}

	public static OfDouble ofDouble()
	{
		return new OfDouble();
	}

	public static final class OfObject<E> extends AbstractRichIterator<E>
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
		public void forwardImpl()
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
		public void forwardImpl()
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
		public void forwardImpl()
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
		public void forwardImpl()
		{
			throw new NoSuchElementException();
		}
	}
}
