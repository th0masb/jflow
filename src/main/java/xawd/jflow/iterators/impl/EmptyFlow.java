package xawd.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

public final class EmptyFlow
{
	private EmptyFlow() {}

	public static class OfObjects<T> extends AbstractFlow<T>
	{
		public OfObjects()
		{
			super(OptionalInt.of(0));
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
	
	public static final LongFlow OF_LONGS = new OfLongs();

	private static class OfLongs extends AbstractLongFlow
	{
		public OfLongs()
		{
			super(OptionalInt.of(0));
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
	
	public static final DoubleFlow OF_DOUBLES = new OfDoubles();

	private static class OfDoubles extends AbstractDoubleFlow
	{
		private OfDoubles()
		{
			super(OptionalInt.of(0));
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
	
	public static final IntFlow OF_INTS = new OfInts();

	private static class OfInts extends AbstractIntFlow
	{
		private OfInts()
		{
			super(OptionalInt.of(0));
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
