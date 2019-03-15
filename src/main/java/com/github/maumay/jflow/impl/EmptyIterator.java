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
		public OfInt()
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
		public OfLong()
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
		public OfDouble()
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
