/**
 *
 */
package com.github.maumay.jflow.testutilities;

import java.util.NoSuchElementException;

import com.github.maumay.jflow.iterators.impl2.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractIntIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractLongIterator;
import com.github.maumay.jflow.iterators.impl2.KnownSize;

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
				int size = Utils.SMALL_OBJECT_EXAMPLE_SRC.length;
				return new AbstractEnhancedIterator<String>(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public String nextImpl()
					{
						if (hasNext()) {
							return Utils.SMALL_OBJECT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				int size = Utils.OBJECT_EXAMPLE_SRC.length;
				return new AbstractEnhancedIterator<String>(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public String nextImpl()
					{
						if (hasNext()) {
							return Utils.OBJECT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				int size = Utils.LARGE_OBJECT_EXAMPLE_SRC.length;
				return new AbstractEnhancedIterator<String>(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public String nextImpl()
					{
						if (hasNext()) {
							return Utils.LARGE_OBJECT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				return new AbstractEnhancedIterator<String>(new KnownSize(1)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count == 0;
					}

					@Override
					public String nextImpl()
					{
						if (count++ == 0) {
							return Utils.SINGLETON_OBJECT_EXAMPLE_SRC[0];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				return new AbstractEnhancedIterator<String>(new KnownSize(0)) {
					@Override
					public boolean hasNext()
					{
						return false;
					}

					@Override
					public String nextImpl()
					{
						throw new NoSuchElementException();
					}

					@Override
					public void skipImpl()
					{
						next();
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
				int size = Utils.SMALL_INT_EXAMPLE_SRC.length;
				return new AbstractIntIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Utils.SMALL_INT_EXAMPLE_SRC.length;
					}

					@Override
					public int nextIntImpl()
					{
						if (hasNext()) {
							return Utils.SMALL_INT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				int size = Utils.INT_EXAMPLE_SRC.length;
				return new AbstractIntIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public int nextIntImpl()
					{
						if (hasNext()) {
							return Utils.INT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				int size = Utils.LARGE_INT_EXAMPLE_SRC.length;
				return new AbstractIntIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public int nextIntImpl()
					{
						if (hasNext()) {
							return Utils.LARGE_INT_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				return new AbstractIntIterator(new KnownSize(0)) {
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
				int size = Utils.SMALL_DOUBLE_EXAMPLE_SRC.length;
				return new AbstractDoubleIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Utils.SMALL_DOUBLE_EXAMPLE_SRC.length;
					}

					@Override
					public double nextDoubleImpl()
					{
						if (hasNext()) {
							return Utils.SMALL_DOUBLE_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				int size = Utils.DOUBLE_EXAMPLE_SRC.length;
				return new AbstractDoubleIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public double nextDoubleImpl()
					{
						if (hasNext()) {
							return Utils.DOUBLE_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				int size = Utils.LARGE_DOUBLE_EXAMPLE_SRC.length;
				return new AbstractDoubleIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public double nextDoubleImpl()
					{
						if (hasNext()) {
							return Utils.LARGE_DOUBLE_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				return new AbstractDoubleIterator(new KnownSize(0)) {
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
						nextDouble();
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
				int size = Utils.SMALL_LONG_EXAMPLE_SRC.length;
				return new AbstractLongIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < Utils.SMALL_LONG_EXAMPLE_SRC.length;
					}

					@Override
					public long nextLongImpl()
					{
						if (hasNext()) {
							return Utils.SMALL_LONG_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				int size = Utils.LONG_EXAMPLE_SRC.length;
				return new AbstractLongIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public long nextLongImpl()
					{
						if (hasNext()) {
							return Utils.LONG_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				int size = Utils.LARGE_LONG_EXAMPLE_SRC.length;
				return new AbstractLongIterator(new KnownSize(size)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < size;
					}

					@Override
					public long nextLongImpl()
					{
						if (hasNext()) {
							return Utils.LARGE_LONG_EXAMPLE_SRC[count++];
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
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
				return new AbstractLongIterator(new KnownSize(0)) {
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
						nextLong();
					}
				};
			}
		};
	}

}
