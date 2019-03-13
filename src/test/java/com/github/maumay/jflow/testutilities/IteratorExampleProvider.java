/**
 *
 */
package com.github.maumay.jflow.testutilities;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;

/**
 * We provide iterable objects which produce identical iterators. We want
 * multiple identical iterators to test the structure rigorously.
 *
 * @author t
 */
public class IteratorExampleProvider
{
	public static AbstractEnhancedIterable<String> getSmallObjectTestIteratorProvider()
	{
		return new AbstractEnhancedIterable<String>() {
			@Override
			public AbstractEnhancedIterator<String> iter()
			{
				return new AbstractEnhancedIterator<String>(
						OptionalInt.of(Constants.SMALL_OBJECT_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size.getAsInt();
					}

					@Override
					public String next()
					{
						if (hasNext()) {
							return Constants.SMALL_OBJECT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						next();
					}
				};
			}
		};
	}

	public static AbstractEnhancedIterable<String> getObjectTestIteratorProvider()
	{
		return new AbstractEnhancedIterable<String>() {
			@Override
			public AbstractEnhancedIterator<String> iter()
			{
				return new AbstractEnhancedIterator<String>(
						OptionalInt.of(Constants.OBJECT_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.OBJECT_EXAMPLE_SRC.length;
					}

					@Override
					public String next()
					{
						if (hasNext()) {
							return Constants.OBJECT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						next();
					}
				};
			}
		};
	}

	public static AbstractEnhancedIterable<String> getLargeObjectTestIteratorProvider()
	{
		return new AbstractEnhancedIterable<String>() {
			@Override
			public AbstractEnhancedIterator<String> iter()
			{
				return new AbstractEnhancedIterator<String>(
						OptionalInt.of(Constants.LARGE_OBJECT_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.LARGE_OBJECT_EXAMPLE_SRC.length;
					}

					@Override
					public String next()
					{
						if (hasNext()) {
							return Constants.LARGE_OBJECT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						next();
					}
				};
			}
		};
	}

	public static AbstractEnhancedIterable<String> getSingletonObjectTestIteratorProvider()
	{
		return new AbstractEnhancedIterable<String>() {
			@Override
			public AbstractEnhancedIterator<String> iter()
			{
				return new AbstractEnhancedIterator<String>(OptionalInt.of(1)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count == 0;
					}

					@Override
					public String next()
					{
						if (count++ == 0) {
							return Constants.SINGLETON_OBJECT_EXAMPLE_SRC[0];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						next();
					}
				};
			}
		};
	}

	public static AbstractEnhancedIterable<String> getEmptyObjectTestIteratorProvider()
	{
		return new AbstractEnhancedIterable<String>() {
			@Override
			public AbstractEnhancedIterator<String> iter()
			{
				return new AbstractEnhancedIterator<String>(OptionalInt.of(0)) {
					@Override
					public boolean hasNext()
					{
						return false;
					}

					@Override
					public String next()
					{
						throw new NoSuchElementException();
					}

					@Override
					public void skip()
					{
						next();
					}
				};
			}
		};
	}

	public static AbstractIterableLongs getSmallLongTestIteratorProvider()
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return new AbstractLongIterator(
						OptionalInt.of(Constants.SMALL_LONG_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.SMALL_LONG_EXAMPLE_SRC.length;
					}

					@Override
					public long nextLong()
					{
						if (hasNext()) {
							return Constants.SMALL_LONG_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextLong();
					}
				};
			}
		};
	}

	public static AbstractIterableLongs getLongTestIteratorProvider()
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return new AbstractLongIterator(
						OptionalInt.of(Constants.LONG_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.LONG_EXAMPLE_SRC.length;
					}

					@Override
					public long nextLong()
					{
						if (hasNext()) {
							return Constants.LONG_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextLong();
					}
				};
			}
		};
	}

	public static AbstractIterableLongs getLargeLongTestIteratorProvider()
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return new AbstractLongIterator(
						OptionalInt.of(Constants.LARGE_LONG_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.LARGE_LONG_EXAMPLE_SRC.length;
					}

					@Override
					public long nextLong()
					{
						if (hasNext()) {
							return Constants.LARGE_LONG_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextLong();
					}
				};
			}
		};
	}

	public static AbstractIterableLongs getEmptyLongTestIteratorProvider()
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return new AbstractLongIterator(OptionalInt.of(0)) {
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
						nextLong();
					}
				};
			}
		};
	}

	public static AbstractIterableInts getSmallIntTestIteratorProvider()
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return new AbstractIntIterator(
						OptionalInt.of(Constants.SMALL_INT_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.SMALL_INT_EXAMPLE_SRC.length;
					}

					@Override
					public int nextInt()
					{
						if (hasNext()) {
							return Constants.SMALL_INT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextInt();
					}
				};
			}
		};
	}

	public static AbstractIterableInts getIntTestIteratorProvider()
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return new AbstractIntIterator(
						OptionalInt.of(Constants.INT_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.INT_EXAMPLE_SRC.length;
					}

					@Override
					public int nextInt()
					{
						if (hasNext()) {
							return Constants.INT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextInt();
					}
				};
			}
		};
	}

	public static AbstractIterableInts getLargeIntTestIteratorProvider()
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return new AbstractIntIterator(
						OptionalInt.of(Constants.LARGE_INT_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.LARGE_INT_EXAMPLE_SRC.length;
					}

					@Override
					public int nextInt()
					{
						if (hasNext()) {
							return Constants.LARGE_INT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextInt();
					}
				};
			}
		};
	}

	public static AbstractIterableInts getEmptyIntTestIteratorProvider()
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return new AbstractIntIterator(OptionalInt.of(0)) {
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
						nextInt();
					}
				};
			}
		};
	}

	public static AbstractIterableDoubles getSmallDoubleTestIteratorProvider()
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return new AbstractDoubleIterator(
						OptionalInt.of(Constants.SMALL_DOUBLE_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.SMALL_DOUBLE_EXAMPLE_SRC.length;
					}

					@Override
					public double nextDouble()
					{
						if (hasNext()) {
							return Constants.SMALL_DOUBLE_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextDouble();
					}
				};
			}
		};
	}

	public static AbstractIterableDoubles getDoubleTestIteratorProvider()
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return new AbstractDoubleIterator(
						OptionalInt.of(Constants.DOUBLE_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.DOUBLE_EXAMPLE_SRC.length;
					}

					@Override
					public double nextDouble()
					{
						if (hasNext()) {
							return Constants.DOUBLE_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextDouble();
					}
				};
			}
		};
	}

	public static AbstractIterableDoubles getLargeDoubleTestIteratorProvider()
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return new AbstractDoubleIterator(
						OptionalInt.of(Constants.LARGE_DOUBLE_EXAMPLE_SRC.length)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Constants.LARGE_DOUBLE_EXAMPLE_SRC.length;
					}

					@Override
					public double nextDouble()
					{
						if (hasNext()) {
							return Constants.LARGE_DOUBLE_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skip()
					{
						nextDouble();
					}
				};
			}
		};
	}

	public static AbstractIterableDoubles getEmptyDoubleTestIteratorProvider()
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return new AbstractDoubleIterator(OptionalInt.of(0)) {
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
						nextDouble();
					}
				};
			}
		};
	}
}
