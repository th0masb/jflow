/**
 * 
 */
package xawd.jflow.testutilities;

import java.util.NoSuchElementException;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;

/**
 * @author t
 */
public class IteratorExampleProvider 
{
	private static final String[] OBJECT_EXAMPLE_SRC = {"0", "1", "2", "3", "4"};
	private static final int[] INT_EXAMPLE_SRC = {0, 1, 2, 3, 4};
	private static final double[] DOUBLE_EXAMPLE_SRC = {0, 1, 2, 3, 4};
	private static final long[] LONG_EXAMPLE_SRC = {0, 1, 2, 3, 4};


	public AbstractFlow<String> getPopulatedObjectTestIterator()
	{
		return new AbstractFlow<String>() 
		{
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < OBJECT_EXAMPLE_SRC.length;
			}
			@Override
			public String next() {
				if (hasNext()) {
					return OBJECT_EXAMPLE_SRC[count++];
				}
				else {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	public AbstractFlow<String> getEmptyObjectTestIterator()
	{
		return new AbstractFlow<String>() 
		{
			@Override
			public boolean hasNext() {
				return false;
			}
			@Override
			public String next() {
				throw new NoSuchElementException();
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	public AbstractLongFlow getPopulatedLongTestIterator()
	{
		return new AbstractLongFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < LONG_EXAMPLE_SRC.length;
			}
			@Override
			public long nextLong() {
				if (hasNext()) {
					return LONG_EXAMPLE_SRC[count++];
				}
				else {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	public AbstractLongFlow getEmptyLongTestIterator()
	{
		return new AbstractLongFlow() {
			@Override
			public boolean hasNext() {
				return false;
			}
			@Override
			public long nextLong() {
				throw new NoSuchElementException();
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	public AbstractIntFlow getPopulatedIntTestIterator()
	{
		return new AbstractIntFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < INT_EXAMPLE_SRC.length;
			}
			@Override
			public int nextInt() {
				if (hasNext()) {
					return INT_EXAMPLE_SRC[count++];
				}
				else {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				next();
			}
		};
	}
	
	public AbstractIntFlow getEmptyIntTestIterator()
	{
		return new AbstractIntFlow() {
			@Override
			public boolean hasNext() {
				return false;
			}
			@Override
			public int nextInt() {
				throw new NoSuchElementException();
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	public AbstractDoubleFlow getPopulatedDoubleTestIterator()
	{
		return new AbstractDoubleFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < DOUBLE_EXAMPLE_SRC.length;
			}
			@Override
			public double nextDouble() {
				if (hasNext()) {
					return DOUBLE_EXAMPLE_SRC[count++];
				}
				else {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				next();
			}
		};
	}
	
	public AbstractDoubleFlow getEmptyDoubleTestIterator()
	{
		return new AbstractDoubleFlow() {
			@Override
			public boolean hasNext() {
				return false;
			}
			@Override
			public double nextDouble() {
				throw new NoSuchElementException();
			}
			@Override
			public void skip() {
				next();
			}
		};
	}
}
