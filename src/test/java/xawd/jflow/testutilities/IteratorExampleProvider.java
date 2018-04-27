/**
 *
 */
package xawd.jflow.testutilities;

import java.util.NoSuchElementException;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.iterables.AbstractFlowIterable;
import xawd.jflow.iterables.AbstractIterableDoubles;
import xawd.jflow.iterables.AbstractIterableInts;
import xawd.jflow.iterables.AbstractIterableLongs;

/**
 * We provide iterable objects which produce identical iterators. We want multiple identical iterators
 * to test the structure rigorously.
 *
 * @author t
 */
public class IteratorExampleProvider
{
	private static final String[] OBJECT_EXAMPLE_SRC = {"0", "1", "2", "3", "4"};
	private static final int[] INT_EXAMPLE_SRC = {0, 1, 2, 3, 4};
	private static final double[] DOUBLE_EXAMPLE_SRC = {0, 1, 2, 3, 4};
	private static final long[] LONG_EXAMPLE_SRC = {0, 1, 2, 3, 4};


	public AbstractFlowIterable<String> getPopulatedObjectTestIterator()
	{
		return new AbstractFlowIterable<String>()
		{
			@Override
			public AbstractFlow<String> iterator()
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
		};
	}

	public AbstractFlowIterable<String> getEmptyObjectTestIterator()
	{
		return new AbstractFlowIterable<String>()
		{
			@Override
			public AbstractFlow<String> iterator()
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
		};
	}

	public AbstractIterableLongs getPopulatedLongTestIterator()
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iterator()
			{
				return new AbstractLongFlow()
				{
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
		};
	}

	public AbstractIterableLongs getEmptyLongTestIterator()
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iterator()
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
		};
	}

	public AbstractIterableInts getPopulatedIntTestIterator()
	{
		return new AbstractIterableInts()
		{
			@Override
			public AbstractIntFlow iterator()
			{
				return new AbstractIntFlow()
				{
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
		};
	}

	public AbstractIterableInts getEmptyIntTestIterator()
	{
		return new AbstractIterableInts()
		{
			@Override
			public AbstractIntFlow iterator()
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
		};
	}

	public AbstractIterableDoubles getPopulatedDoubleTestIterator()
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iterator()
			{
				return new AbstractDoubleFlow()
				{
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
		};
	}

	public AbstractIterableDoubles getEmptyDoubleTestIterator()
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iterator()
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
		};
	}
}
