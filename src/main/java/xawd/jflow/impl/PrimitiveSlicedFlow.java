package xawd.jflow.impl;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public final class PrimitiveSlicedFlow
{
	public static class OfInt extends AbstractIntFlow
	{
		@Override
		public int nextInt()
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void skip()
		{
			// TODO Auto-generated method stub

		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		@Override
		public double nextDouble()
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void skip()
		{
			// TODO Auto-generated method stub

		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		@Override
		public void skip()
		{
			// TODO Auto-generated method stub
		}

		@Override
		public long nextLong()
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return false;
		}
	}
}
