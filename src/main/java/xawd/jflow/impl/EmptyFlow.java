package xawd.jflow.impl;

import java.util.NoSuchElementException;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;

public final class EmptyFlow 
{
	private EmptyFlow() {}
	
	public static class OfObjects<T> extends AbstractFlow<T>
	{
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
