package xawd.jflow.utilities;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.Flow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;

public final class Iter
{
	public static <T> Flow<T> from(final IntFunction<T> f)
	{
		return new AbstractFlow<T>() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return true;
			}
			@Override
			public T next() {
				return f.apply(count++);
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	public static LongFlow longsFrom(final IntToLongFunction f)
	{
		return new AbstractLongFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return true;
			}
			@Override
			public long nextLong() {
				return f.applyAsLong(count++);
			}
			@Override
			public void skip() {
				nextLong();
			}
		};
	}

	public static IntFlow intsFrom(final IntUnaryOperator f)
	{
		return new AbstractIntFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return true;
			}
			@Override
			public int nextInt() {
				return f.applyAsInt(count++);
			}
			@Override
			public void skip() {
				nextInt();
			}
		};
	}

	public static IntFlow naturalNumbers()
	{
		return intsFrom(i -> i);
	}

	public static <T> Flow<T> of(final Iterable<T> src)
	{
		return new AbstractFlow<T>() {
			Iterator<T> srcIter = src.iterator();
			@Override
			public boolean hasNext() {
				return srcIter.hasNext();
			}
			@Override
			public T next() {
				return srcIter.next();
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	public static <T> Flow<T> of(final T t)
	{
		return of(Arrays.asList(t));
	}

	public static <T> Flow<T> of(final T t1, final T t2)
	{
		return of(Arrays.asList(t1, t2));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3)
	{
		return of(Arrays.asList(t1, t2, t3));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3, final T t4)
	{
		return of(Arrays.asList(t1, t2, t3, t4));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3, final T t4, final T t5)
	{
		return of(Arrays.asList(t1, t2, t3, t4, t5));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3, final T t4, final T t5, final T t6)
	{
		return of(Arrays.asList(t1, t2, t3, t4, t5, t6));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7)
	{
		return of(Arrays.asList(t1, t2, t3, t4, t5, t6, t7));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3, final T t4, final T t5, final T t6, final T t7, final T t8)
	{
		return of(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8));
	}

	public static IntFlow of(final int... xs)
	{
		throw new RuntimeException();
	}

	public static void main(final String[] args)
	{
//		final IntFlow x = of(new int[] {1, 2, 3});
		final List<String> strings = Arrays.asList("a", "b", "c", "d", "e", "f");
		System.out.println(Iter.of(strings).reduce("", String::concat));

		System.out.println(Iter.of(strings).accumulate("empty", String::concat).toList());

		System.out.println(Iter.of(strings).slice(i -> 2*i + 1).toList());

		System.out.println(Itertools.product(strings, strings).toList());


//		Iter.longsFrom(i -> i)
//		.mapToObject(i -> str(i))
//		.slice(i -> 2*i*i + 3*i)
//		.take(10)
//		.enumerate()
//		.toList();
	}
}
