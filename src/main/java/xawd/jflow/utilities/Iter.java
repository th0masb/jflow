package xawd.jflow.utilities;

import static io.xyz.chains.utilities.CollectionUtil.len;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.DoubleFlow;
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
		return new AbstractIntFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < len(xs);
			}
			@Override
			public int nextInt() {
				if (count < len(xs)) {
					return xs[count++];
				}
				else {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				if (count < len(xs)) {
					count++;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		};
	}
	
	public static IntFlow of(final int x)
	{
		return of(new int[] {x});
	}
	
	public static LongFlow of(final long... xs)
	{
		return new AbstractLongFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < len(xs);
			}
			@Override
			public long nextLong() {
				if (count < len(xs)) {
					return xs[count++];
				}
				else {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				if (count < len(xs)) {
					count++;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		};
	}
	
	public static LongFlow of(final long x)
	{
		return of(new long[] {x});
	}
	
	public static DoubleFlow of(final double... xs)
	{
		return new AbstractDoubleFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < len(xs);
			}
			@Override
			public double nextDouble() {
				if (count < len(xs)) {
					return xs[count++];
				}
				else {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				if (count < len(xs)) {
					count++;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		};
	}
	
	public static DoubleFlow of(final double x)
	{
		return of(new double[] {x});
	}

	public static void main(final String[] args)
	{
//		final IntFlow x = of(new int[] {1, 2, 3});
		final List<String> strings = Arrays.asList("a", "b", "c", "d", "e", "f");
		System.out.println(Iter.of(strings).reduce("", String::concat));

		System.out.println(Iter.of(strings).accumulate("empty", String::concat).toList());

		System.out.println(Iter.of(strings).slice(i -> 2*i + 1).insert("x", "y").toList());

		System.out.println(Itertools.product(strings, strings).toList());


//		Iter.longsFrom(i -> i)
//		.mapToObject(i -> str(i))
//		.slice(i -> 2*i*i + 3*i)
//		.take(10)
//		.enumerate()
//		.toList();
	}
}
