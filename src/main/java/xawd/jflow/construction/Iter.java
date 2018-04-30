package xawd.jflow.construction;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.DoubleFlow;
import xawd.jflow.Flow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;
import xawd.jflow.impl.EmptyFlow;
import xawd.jflow.impl.FlowFromFunction;
import xawd.jflow.impl.FlowFromValues;

public final class Iter
{
	public static <T> Flow<T> from(final IntFunction<T> f)
	{
		return new FlowFromFunction.OfObject<>(f);
	}

	public static <T> Flow<T> from(final IntFunction<T> f, final int elementCount)
	{
		if (elementCount < 0) {
			throw new IllegalArgumentException();
		}
		return new FlowFromFunction.OfObject<>(f, elementCount);
	}

	public static LongFlow longsFrom(final IntToLongFunction f)
	{
		return new FlowFromFunction.OfLong(f);
	}

	public static LongFlow longsFrom(final IntToLongFunction f, final int elementCount)
	{
		if (elementCount < 0) {
			throw new IllegalArgumentException();
		}
		return new FlowFromFunction.OfLong(f, elementCount);
	}

	public static IntFlow intsFrom(final IntUnaryOperator f)
	{
		return new FlowFromFunction.OfInt(f);
	}

	public static IntFlow intsFrom(final IntUnaryOperator f, final int elementCount)
	{
		if (elementCount < 0) {
			throw new IllegalArgumentException();
		}
		return new FlowFromFunction.OfInt(f, elementCount);
	}

	public static DoubleFlow doublesFrom(final IntToDoubleFunction f)
	{
		return new FlowFromFunction.OfDouble(f);
	}

	public static DoubleFlow doublesFrom(final IntToDoubleFunction f, final int elementCount)
	{
		if (elementCount < 0) {
			throw new IllegalArgumentException();
		}
		return new FlowFromFunction.OfDouble(f, elementCount);
	}

	public static <T> Flow<T> of(final Iterable<T> src)
	{
		return new FlowFromValues.OfObject<>(src);
	}

	public static <T> Flow<T> of(final T t)
	{
		return new FlowFromValues.OfObject<>(Arrays.asList(t));
	}

	public static <T> Flow<T> of(final T t1, final T t2)
	{
		return new FlowFromValues.OfObject<>(Arrays.asList(t1, t2));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3)
	{
		return new FlowFromValues.OfObject<>(Arrays.asList(t1, t2, t3));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3, final T t4)
	{
		return new FlowFromValues.OfObject<>(Arrays.asList(t1, t2, t3, t4));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3, final T t4, final T t5)
	{
		return new FlowFromValues.OfObject<>(Arrays.asList(t1, t2, t3, t4, t5));
	}

	public static <T> Flow<T> of(final T t1, final T t2, final T t3, final T t4, final T t5, final T t6)
	{
		return new FlowFromValues.OfObject<>(Arrays.asList(t1, t2, t3, t4, t5, t6));
	}

	public static IntFlow of(final int[] xs)
	{
		return new FlowFromValues.OfInt(xs);
	}

	public static IntFlow of(final int x)
	{
		return new FlowFromValues.OfInt(x);
	}

	public static LongFlow of(final long[] xs)
	{
		return new FlowFromValues.OfLong(xs);
	}

	public static LongFlow of(final long x)
	{
		return new FlowFromValues.OfLong(x);
	}

	public static DoubleFlow of(final double[] xs)
	{
		return new FlowFromValues.OfDouble(xs);
	}

	public static DoubleFlow of(final double x)
	{
		return new FlowFromValues.OfDouble(x);
	}

	public static <T> Flow<T> empty()
	{
		return new EmptyFlow.OfObjects<>();
	}

	public static IntFlow emptyInts()
	{
		return new EmptyFlow.OfInts();
	}

	public static LongFlow emptyLongs()
	{
		return new EmptyFlow.OfLongs();
	}

	public static DoubleFlow emptyDoubles()
	{
		return new EmptyFlow.OfDoubles();
	}

	public static void main(final String[] args)
	{
		//		final IntFlow x = of(new int[] {1, 2, 3});
		final List<String> strings = Arrays.asList("a", "b", "c");
		System.out.println(IterCycle.of(strings).take(10).reduce("", String::concat));

		System.out.println(Arrays.toString(IterCycle.of(1, 2, 3, 4).take(20).toArray()));

		System.out.println(Iter.of(strings).accumulate("empty", String::concat).toList());

		System.out.println(Iter.of(strings).slice(i -> 2*i + 1).insert("x", "y").toList());

		System.out.println(IterProduct.of(strings, strings).toList());

		@SuppressWarnings("unused")
		final Set<String> xs = Iter.of(strings)
		.enumerate()
		.map(p -> p.getElement() + p.getInt())
		.toCollection(HashSet::new);

		IterRange.between(2, 11).mapToObject(i -> "a").toList();
		//		Iter.longsFrom(i -> i)
		//		.mapToObject(i -> str(i))
		//		.slice(i -> 2*i*i + 3*i)
		//		.take(10)
		//		.enumerate()
		//		.toList();

		Iter.of(asList("a", "b"), asList("c", "d")).flatten(Iter::of).toList();
		Iter.of(new int[] {1, 2}, new int[] {3, 4}).flattenToInts(Iter::of).toArray();

	}
}
