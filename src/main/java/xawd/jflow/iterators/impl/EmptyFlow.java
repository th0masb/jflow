package xawd.jflow.iterators.impl;

import java.util.NoSuchElementException;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;

public final class EmptyFlow
{
	private EmptyFlow() {}

	public static class OfObjects<T> extends AbstractFlow<T>
	{
		public OfObjects()
		{
			super(0);
		}
		@Override
		public boolean hasNext()
		{
			return false;
		}
		@Override
		public T next()
		{
			throw new NoSuchElementException();
		}
		@Override
		public void skip()
		{
			throw new NoSuchElementException();
		}
	}

	public static class OfLongs extends AbstractLongFlow
	{
		public OfLongs()
		{
			super(0);
		}
		@Override
		public boolean hasNext()
		{
			return false;
		}
		@Override
		public long nextLong()
		{
			throw new NoSuchElementException();
		}
		@Override
		public void skip()
		{
			throw new NoSuchElementException();
		}
	}

	public static class OfDoubles extends AbstractDoubleFlow
	{
		public OfDoubles()
		{
			super(0);
		}
		@Override
		public boolean hasNext()
		{
			return false;
		}
		@Override
		public double nextDouble()
		{
			throw new NoSuchElementException();
		}
		@Override
		public void skip()
		{
			throw new NoSuchElementException();
		}
	}

	public static class OfInts extends AbstractIntFlow
	{
		public OfInts()
		{
			super(0);
		}
		@Override
		public boolean hasNext()
		{
			return false;
		}
		@Override
		public int nextInt()
		{
			throw new NoSuchElementException();
		}
		@Override
		public void skip()
		{
			throw new NoSuchElementException();
		}
	}
}
