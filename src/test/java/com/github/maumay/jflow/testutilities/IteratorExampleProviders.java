/**
 *
 */
package com.github.maumay.jflow.testutilities;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.AbstractIteratorSize;
import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.BoundedSize;
import com.github.maumay.jflow.impl.KnownSize;
import com.github.maumay.jflow.impl.LowerBound;

/**
 * We provide iterable objects which produce identical iterators. We want
 * multiple identical iterators to test the structure rigorously.
 *
 * @author t
 */
class IteratorExampleProviders
{
	private static final int UPPER_BOUND_MULTIPLIER = 3;

	static <T> List<AbstractRichIterable<T>> buildIterables(List<T> src)
	{
		return buildIterables(src, x -> x);
	}

	static <T> List<AbstractRichIterable<T>> buildIterables(List<T> src,
			UnaryOperator<AbstractRichIterator<T>> adapter)
	{
		int size = src.size();
		List<AbstractRichIterable<T>> dest = new ArrayList<>();
		// Add exact size
		dest.add(buildIterable(src, KnownSize.of(size), adapter));

		// Add lower bound sizes.
		for (int i = 0; i <= size; i++) {
			dest.add(buildIterable(src, LowerBound.of(i), adapter));
		}

		// Add bounded sizes.
		int upper = UPPER_BOUND_MULTIPLIER * size;
		for (int hi = size; hi < upper; hi++) {
			for (int lo = 0; lo <= size; lo++) {
				dest.add(buildIterable(src, BoundedSize.of(lo, hi), adapter));
			}
		}
		return dest;
	}

	private static <T> AbstractRichIterable<T> buildIterable(List<T> src, AbstractIteratorSize size,
			UnaryOperator<AbstractRichIterator<T>> adapter)
	{
		return new AbstractRichIterable<T>() {
			@Override
			public AbstractRichIterator<T> iter()
			{
				return adapter.apply(new AbstractRichIterator<T>(size) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < src.size();
					}

					@Override
					public T nextImpl()
					{
						if (hasNext()) {
							return src.get(count++);
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
					{
						nextImpl();
					}
				});
			}
		};
	}

	static List<AbstractIterableInts> buildIntIterables(List<Integer> src)
	{
		return buildIntIterables(src, x -> x);
	}

	static List<AbstractIterableInts> buildIntIterables(List<Integer> src,
			UnaryOperator<AbstractIntIterator> adapter)
	{
		int size = src.size();
		List<AbstractIterableInts> dest = new ArrayList<>();
		dest.add(buildIntIterable(src, KnownSize.of(size), adapter));

		// Add lower bound sizes.
		for (int i = 0; i <= size; i++) {
			dest.add(buildIntIterable(src, LowerBound.of(i), adapter));
		}

		// Add bounded sizes.
		int upper = UPPER_BOUND_MULTIPLIER * size;
		for (int hi = size; hi < upper; hi++) {
			for (int lo = 0; lo <= size; lo++) {
				dest.add(buildIntIterable(src, BoundedSize.of(lo, hi), adapter));
			}
		}
		return dest;
	}

	private static AbstractIterableInts buildIntIterable(List<Integer> src,
			AbstractIteratorSize size, UnaryOperator<AbstractIntIterator> adapter)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return adapter.apply(new AbstractIntIterator(size) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < src.size();
					}

					@Override
					public int nextIntImpl()
					{
						if (hasNext()) {
							return src.get(count++);
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
					{
						nextIntImpl();
					}
				});
			}
		};
	}

	static List<AbstractIterableLongs> buildLongIterables(List<Long> src)
	{
		return buildLongIterables(src, x -> x);
	}

	static List<AbstractIterableLongs> buildLongIterables(List<Long> src,
			UnaryOperator<AbstractLongIterator> adapter)
	{
		int size = src.size();
		List<AbstractIterableLongs> dest = new ArrayList<>();
		dest.add(buildLongIterable(src, KnownSize.of(size), adapter));

		// Add lower bound sizes.
		for (int i = 0; i <= size; i++) {
			dest.add(buildLongIterable(src, LowerBound.of(i), adapter));
		}

		// Add bounded sizes.
		int upper = UPPER_BOUND_MULTIPLIER * size;
		for (int hi = size; hi < upper; hi++) {
			for (int lo = 0; lo <= size; lo++) {
				dest.add(buildLongIterable(src, BoundedSize.of(lo, hi), adapter));
			}
		}
		return dest;
	}

	private static AbstractIterableLongs buildLongIterable(List<Long> src,
			AbstractIteratorSize size, UnaryOperator<AbstractLongIterator> adapter)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return adapter.apply(new AbstractLongIterator(size) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < src.size();
					}

					@Override
					public long nextLongImpl()
					{
						if (hasNext()) {
							return src.get(count++);
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
					{
						nextLongImpl();
					}
				});
			}
		};
	}

	static List<AbstractIterableDoubles> buildDoubleIterables(List<Double> src)
	{
		return buildDoubleIterables(src, x -> x);
	}

	static List<AbstractIterableDoubles> buildDoubleIterables(List<Double> src,
			UnaryOperator<AbstractDoubleIterator> adapter)
	{
		int size = src.size();
		List<AbstractIterableDoubles> dest = new ArrayList<>();
		dest.add(buildDoubleIterable(src, KnownSize.of(size), adapter));

		// Add lower bound sizes.
		for (int i = 0; i <= size; i++) {
			dest.add(buildDoubleIterable(src, LowerBound.of(i), adapter));
		}

		// Add bounded sizes.
		int upper = UPPER_BOUND_MULTIPLIER * size;
		for (int hi = size; hi < upper; hi++) {
			for (int lo = 0; lo <= size; lo++) {
				dest.add(buildDoubleIterable(src, BoundedSize.of(lo, hi), adapter));
			}
		}
		return dest;
	}

	private static AbstractIterableDoubles buildDoubleIterable(List<Double> src,
			AbstractIteratorSize size, UnaryOperator<AbstractDoubleIterator> adapter)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return adapter.apply(new AbstractDoubleIterator(size) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < src.size();
					}

					@Override
					public double nextDoubleImpl()
					{
						if (hasNext()) {
							return src.get(count++);
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void skipImpl()
					{
						nextDoubleImpl();
					}
				});
			}
		};
	}
}
