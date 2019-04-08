/**
 *
 */
package com.github.maumay.jflow.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.AbstractIterator;
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
class IteratorProvider
{
	private static final int UPPER_BOUND_MULTIPLIER = 5;

	static <T> List<TestIterable<AbstractRichIterator<T>>> buildIterables(List<T> src)
	{
		return buildIterables(src, x -> x);
	}

	static <T, I extends AbstractIterator> List<TestIterable<I>> buildIterables(List<T> src,
			Function<AbstractRichIterator<T>, I> adapter)
	{
		int size = src.size();
		List<TestIterable<I>> dest = new ArrayList<>();
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

	private static <T, I extends AbstractIterator> TestIterable<I> buildIterable(List<T> src,
			AbstractIteratorSize size, Function<AbstractRichIterator<T>, I> adapter)
	{
		return () -> adapter.apply(new AbstractRichIterator<T>(size) {
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

	static List<TestIterable<AbstractIntIterator>> buildIntIterables(List<Integer> src)
	{
		return buildIntIterables(src, x -> x);
	}

	static <I extends AbstractIterator> List<TestIterable<I>> buildIntIterables(List<Integer> src,
			Function<AbstractIntIterator, I> adapter)
	{
		int size = src.size();
		List<TestIterable<I>> dest = new ArrayList<>();
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

	private static <I extends AbstractIterator> TestIterable<I> buildIntIterable(List<Integer> src,
			AbstractIteratorSize size, Function<AbstractIntIterator, I> adapter)
	{
		return () -> adapter.apply(new AbstractIntIterator(size) {
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

	static List<TestIterable<AbstractLongIterator>> buildLongIterables(List<Long> src)
	{
		return buildLongIterables(src, x -> x);
	}

	static <I extends AbstractIterator> List<TestIterable<I>> buildLongIterables(List<Long> src,
			Function<AbstractLongIterator, I> adapter)
	{
		int size = src.size();
		List<TestIterable<I>> dest = new ArrayList<>();
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

	private static <I extends AbstractIterator> TestIterable<I> buildLongIterable(List<Long> src,
			AbstractIteratorSize size, Function<AbstractLongIterator, I> adapter)
	{
		return () -> adapter.apply(new AbstractLongIterator(size) {
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

	static List<TestIterable<AbstractDoubleIterator>> buildDoubleIterables(List<Double> src)
	{
		return buildDoubleIterables(src, x -> x);
	}

	static <I extends AbstractIterator> List<TestIterable<I>> buildDoubleIterables(List<Double> src,
			Function<AbstractDoubleIterator, I> adapter)
	{
		int size = src.size();
		List<TestIterable<I>> dest = new ArrayList<>();
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

	private static <I extends AbstractIterator> TestIterable<I> buildDoubleIterable(
			List<Double> src, AbstractIteratorSize size,
			Function<AbstractDoubleIterator, I> adapter)
	{
		return () -> adapter.apply(new AbstractDoubleIterator(size) {
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

	public static <T1, T2, I extends AbstractIterator> List<TestIterable<I>> buildIterablesBi(
			List<T1> sourceOne, List<T2> sourceTwo,
			BiFunction<AbstractRichIterator<T1>, AbstractRichIterator<T2>, I> adapter)
	{
		List<TestIterable<I>> dest = new ArrayList<>();

		List<TestIterable<AbstractRichIterator<T1>>> leftArgs = buildIterables(sourceOne);
		List<TestIterable<AbstractRichIterator<T2>>> rightArgs = buildIterables(sourceTwo);

		for (TestIterable<AbstractRichIterator<T1>> left : leftArgs) {
			for (TestIterable<AbstractRichIterator<T2>> right : rightArgs) {
				dest.add(() -> adapter.apply(left.iter(), right.iter()));
			}
		}

		return Collections.unmodifiableList(dest);
	}
}
