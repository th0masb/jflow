/**
 *
 */
package xawd.jflow.testutilities;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;

/**
 * We provide iterable objects which produce identical iterators. We want multiple identical iterators
 * to test the structure rigorously.
 *
 * @author t
 */
public class IteratorExampleProvider
{
	public static AbstractFlowIterable<String> getSmallObjectTestIteratorProvider()
	{
		return new AbstractFlowIterable<String>()
		{
			@Override
			public AbstractFlow<String> iterator()
			{
				return new AbstractFlow<String>(OptionalInt.of(Constants.SMALL_OBJECT_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < size.getAsInt();
					}
					@Override
					public String next() {
						if (hasNext()) {
							return Constants.SMALL_OBJECT_EXAMPLE_SRC[count++];
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

	public static AbstractFlowIterable<String> getObjectTestIteratorProvider()
	{
		return new AbstractFlowIterable<String>()
		{
			@Override
			public AbstractFlow<String> iterator()
			{
				return new AbstractFlow<String>(OptionalInt.of(Constants.OBJECT_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.OBJECT_EXAMPLE_SRC.length;
					}
					@Override
					public String next() {
						if (hasNext()) {
							return Constants.OBJECT_EXAMPLE_SRC[count++];
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

	public static AbstractFlowIterable<String> getLargeObjectTestIteratorProvider()
	{
		return new AbstractFlowIterable<String>()
		{
			@Override
			public AbstractFlow<String> iterator()
			{
				return new AbstractFlow<String>(OptionalInt.of(Constants.LARGE_OBJECT_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.LARGE_OBJECT_EXAMPLE_SRC.length;
					}
					@Override
					public String next() {
						if (hasNext()) {
							return Constants.LARGE_OBJECT_EXAMPLE_SRC[count++];
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

	public static AbstractFlowIterable<String> getSingletonObjectTestIteratorProvider()
	{
		return new AbstractFlowIterable<String>()
		{
			@Override
			public AbstractFlow<String> iterator()
			{
				return new AbstractFlow<String>(OptionalInt.of(1))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count == 0;
					}
					@Override
					public String next() {
						if (count++ == 0) {
							return Constants.SINGLETON_OBJECT_EXAMPLE_SRC[0];
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

	public static AbstractFlowIterable<String> getEmptyObjectTestIteratorProvider()
	{
		return new AbstractFlowIterable<String>()
		{
			@Override
			public AbstractFlow<String> iterator()
			{
				return new AbstractFlow<String>(OptionalInt.of(0))
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

	public static AbstractIterableLongs getSmallLongTestIteratorProvider()
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iterator()
			{
				return new AbstractLongFlow(OptionalInt.of(Constants.SMALL_LONG_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.SMALL_LONG_EXAMPLE_SRC.length;
					}
					@Override
					public long nextLong() {
						if (hasNext()) {
							return Constants.SMALL_LONG_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextLong();
					}
				};
			}
		};
	}

	public static AbstractIterableLongs getLongTestIteratorProvider()
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iterator()
			{
				return new AbstractLongFlow(OptionalInt.of(Constants.LONG_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.LONG_EXAMPLE_SRC.length;
					}
					@Override
					public long nextLong() {
						if (hasNext()) {
							return Constants.LONG_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextLong();
					}
				};
			}
		};
	}

	public static AbstractIterableLongs getLargeLongTestIteratorProvider()
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iterator()
			{
				return new AbstractLongFlow(OptionalInt.of(Constants.LARGE_LONG_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.LARGE_LONG_EXAMPLE_SRC.length;
					}
					@Override
					public long nextLong() {
						if (hasNext()) {
							return Constants.LARGE_LONG_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextLong();
					}
				};
			}
		};
	}

	public static AbstractIterableLongs getEmptyLongTestIteratorProvider()
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iterator()
			{
				return new AbstractLongFlow(OptionalInt.of(0)) {
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
						nextLong();
					}
				};
			}
		};
	}

	public static AbstractIterableInts getSmallIntTestIteratorProvider()
	{
		return new AbstractIterableInts()
		{
			@Override
			public AbstractIntFlow iterator()
			{
				return new AbstractIntFlow(OptionalInt.of(Constants.SMALL_INT_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.SMALL_INT_EXAMPLE_SRC.length;
					}
					@Override
					public int nextInt() {
						if (hasNext()) {
							return Constants.SMALL_INT_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextInt();
					}
				};
			}
		};
	}

	public static AbstractIterableInts getIntTestIteratorProvider()
	{
		return new AbstractIterableInts()
		{
			@Override
			public AbstractIntFlow iterator()
			{
				return new AbstractIntFlow(OptionalInt.of(Constants.INT_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.INT_EXAMPLE_SRC.length;
					}
					@Override
					public int nextInt() {
						if (hasNext()) {
							return Constants.INT_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextInt();
					}
				};
			}
		};
	}

	public static AbstractIterableInts getLargeIntTestIteratorProvider()
	{
		return new AbstractIterableInts()
		{
			@Override
			public AbstractIntFlow iterator()
			{
				return new AbstractIntFlow(OptionalInt.of(Constants.LARGE_INT_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.LARGE_INT_EXAMPLE_SRC.length;
					}
					@Override
					public int nextInt() {
						if (hasNext()) {
							return Constants.LARGE_INT_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextInt();
					}
				};
			}
		};
	}

	public static AbstractIterableInts getEmptyIntTestIteratorProvider()
	{
		return new AbstractIterableInts()
		{
			@Override
			public AbstractIntFlow iterator()
			{
				return new AbstractIntFlow(OptionalInt.of(0)) {
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
						nextInt();
					}
				};
			}
		};
	}

	public static AbstractIterableDoubles getSmallDoubleTestIteratorProvider()
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iterator()
			{
				return new AbstractDoubleFlow(OptionalInt.of(Constants.SMALL_DOUBLE_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.SMALL_DOUBLE_EXAMPLE_SRC.length;
					}
					@Override
					public double nextDouble() {
						if (hasNext()) {
							return Constants.SMALL_DOUBLE_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextDouble();
					}
				};
			}
		};
	}

	public static AbstractIterableDoubles getDoubleTestIteratorProvider()
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iterator()
			{
				return new AbstractDoubleFlow(OptionalInt.of(Constants.DOUBLE_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.DOUBLE_EXAMPLE_SRC.length;
					}
					@Override
					public double nextDouble() {
						if (hasNext()) {
							return Constants.DOUBLE_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextDouble();
					}
				};
			}
		};
	}

	public static AbstractIterableDoubles getLargeDoubleTestIteratorProvider()
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iterator()
			{
				return new AbstractDoubleFlow(OptionalInt.of(Constants.LARGE_DOUBLE_EXAMPLE_SRC.length))
				{
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < Constants.LARGE_DOUBLE_EXAMPLE_SRC.length;
					}
					@Override
					public double nextDouble() {
						if (hasNext()) {
							return Constants.LARGE_DOUBLE_EXAMPLE_SRC[count++];
						}
						else {
							throw new NoSuchElementException();
						}
					}
					@Override
					public void skip() {
						nextDouble();
					}
				};
			}
		};
	}

	public static AbstractIterableDoubles getEmptyDoubleTestIteratorProvider()
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iterator()
			{
				return new AbstractDoubleFlow(OptionalInt.of(0)) {
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
						nextDouble();
					}
				};
			}
		};
	}
}
