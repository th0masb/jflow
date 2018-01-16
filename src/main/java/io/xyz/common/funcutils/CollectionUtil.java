package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CompositionUtil.compose;
import static io.xyz.common.funcutils.PrimitiveUtil.isZero;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import io.xyz.common.generators.BaseGenerator;
import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.generators.IntGenerator;
import io.xyz.common.generators.impl.ImmutableDoubleGenerator;
import io.xyz.common.generators.impl.ImmutableIntGenerator;
import io.xyz.common.generators.impl.ImmutableGenerator;
import io.xyz.common.generators.Generator;

/**
 *
 * @author t
 *
 */
public final class CollectionUtil {
	private CollectionUtil()
	{
	}

	/*
	 * Need to add sort function
	 */

	public static int len(final String s)
	{
		return s.length();
	}

	public static <T> int len(final Collection<T> xs)
	{
		return xs.size();
	}

	public static <T> int len(final T[] xs)
	{
		return xs.length;
	}

	public static int len(final int[] xs)
	{
		return xs.length;
	}

	public static int len(final double[] xs)
	{
		return xs.length;
	}

	public static int len(final int[][] xs)
	{
		return xs.length;
	}

	public static int len(final double[][] xs)
	{
		return xs.length;
	}

	public static int len(final BitSet xs)
	{
		return xs.length();
	}

	public static int len(final BaseGenerator xs)
	{
		return xs.rangeBound();
	}

	// ---------------------------------------------------------------

	public static String asString()
	{
		return "";
	}

	public static <T> String asString(final T t)
	{
		return t.toString();
	}

	public static String asString(final int x)
	{
		return Integer.toString(x);
	}

	public static String asString(final int[] xs)
	{
		return Arrays.toString(xs);
	}

	public static String asString(final double[] xs)
	{
		return Arrays.toString(xs);
	}

	public static String asString(final long[] xs)
	{
		return Arrays.toString(xs);
	}

	// ---------------------------------------------------------------

	public static <T> ImmutableList<T> asList(final Collection<? extends T> c)
	{
		return ImmutableList.copyOf(c);
	}

	@SafeVarargs
	public static <T, E extends T> ImmutableList<T> asList(final E... xs)
	{
		return ImmutableList.copyOf(xs);
	}

	// ---------------------------------------------------------------

	public static <T> ImmutableSet<T> asSet(final Collection<? extends T> c)
	{
		return ImmutableSet.copyOf(c);
	}

	@SafeVarargs
	public static <T, E extends T> ImmutableSet<T> asSet(final E... xs)
	{
		return ImmutableSet.copyOf(xs);
	}

	// ---------------------------------------------------------------

	public static IntGenerator asDescriptor(final int... xs)
	{
		return ImmutableIntGenerator.from(xs);
	}

	public static DoubleGenerator asDescriptor(final double... xs)
	{
		return ImmutableDoubleGenerator.from(xs);
	}

	public static <T> Generator<T> asDescriptor(final List<? extends T> xs)
	{
		return ImmutableGenerator.from(xs);
	}

	@SafeVarargs
	public static <T, E extends T> Generator<T> asDescriptor(final E... xs)
	{
		return ImmutableGenerator.from(xs);
	}

	// ---------------------------------------------------------------

	public static IntGenerator take(final int n, final int[] xs)
	{
		return take(n, asDescriptor(xs));
	}

	public static IntGenerator take(final int n, final IntGenerator xs)
	{
		assert 0 <= n && n <= len(xs);
		return ImmutableIntGenerator.of(n, xs.getDescriptor());
	}

	public static DoubleGenerator take(final int n, final double[] xs)
	{
		return take(n, asDescriptor(xs));
	}

	public static DoubleGenerator take(final int n, final DoubleGenerator xs)
	{
		assert 0 <= n && n <= len(xs);
		return ImmutableDoubleGenerator.of(n, xs.getDescriptor());
	}

	// public static <T> RangeDescriptor<T> take(final int n, final T[] xs)
	// {
	// assert 0 <= n && n <= len(xs);
	// return take(n, asDescriptor(xs));
	// }

	public static <T> Generator<T> take(final int n, final List<T> xs)
	{
		assert 0 <= n && n <= len(xs);
		return take(n, asDescriptor(xs));
	}

	public static <T> Generator<T> take(final int n, final Generator<T> xs)
	{
		assert 0 <= n && n <= len(xs);
		return new ImmutableGenerator<>(n, xs.getDescriptor());
	}

	// ---------------------------------------------------------------

	public static IntGenerator drop(final int n, final IntGenerator xs)
	{
		assert 0 <= n && n <= len(xs);
		return ImmutableIntGenerator.of(len(xs) - n, xs.getDescriptor().compose(i -> i + n));
	}

	public static IntGenerator drop(final int n, final int[] xs)
	{
		return drop(n, asDescriptor(xs));
	}

	public static DoubleGenerator drop(final int n, final DoubleGenerator xs)
	{
		assert 0 <= n && n <= len(xs);
		return ImmutableDoubleGenerator.of(len(xs) - n, compose(xs.getDescriptor(), i -> i + n));
	}

	public static DoubleGenerator drop(final int n, final double[] xs)
	{
		return drop(n, asDescriptor(xs));
	}

	public static <T> Generator<T> drop(final int n, final Generator<T> xs)
	{
		assert 0 <= n && n <= len(xs);
		return new ImmutableGenerator<>(len(xs) - n, compose(xs.getDescriptor(), i -> i + n));
	}

	public static <T> Generator<T> drop(final int n, final T[] xs)
	{
		return drop(n, asDescriptor(xs));
	}

	public static <T> Generator<T> drop(final int n, final List<T> xs)
	{
		return drop(n, asDescriptor(xs));
	}

	// ---------------------------------------------------------------

	public static int head(final IntGenerator xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
	}

	public static int head(final int[] xs)
	{
		return head(asDescriptor(xs));
	}

	public static double head(final DoubleGenerator xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
	}

	public static double head(final double[] xs)
	{
		return head(asDescriptor(xs));
	}

	public static <T> T head(final Generator<T> xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
	}

	public static <T> T head(final T[] xs)
	{
		return head(asDescriptor(xs));
	}

	public static <T> T head(final List<T> xs)
	{
		return head(asDescriptor(xs));
	}

	// ---------------------------------------------------------------

	public static int tail(final IntGenerator xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	public static int tail(final int[] xs)
	{
		return tail(asDescriptor(xs));
	}

	public static double tail(final DoubleGenerator xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	public static double tail(final double[] xs)
	{
		return tail(asDescriptor(xs));
	}

	public static <T> T tail(final Generator<T> xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	public static <T> T tail(final T[] xs)
	{
		return tail(asDescriptor(xs));
	}

	public static <T> T tail(final List<T> xs)
	{
		return tail(asDescriptor(xs));
	}

	// ---------------------------------------------------------------

	public static boolean allEqual(final IntGenerator xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (xs.get(i) != xs.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

	public static boolean allEqual(final int... xs)
	{
		return allEqual(asDescriptor(xs));
	}

	public static boolean allEqual(final DoubleGenerator xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (!isZero(xs.get(i) - xs.get(i + 1))) {
				return false;
			}
		}
		return true;
	}

	public static boolean allEqual(final double... xs)
	{
		return allEqual(asDescriptor(xs));
	}

	public static <T> boolean allEqual(final Generator<T> xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (!xs.get(i).equals(xs.get(i + 1))) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean allEqual(final Collection<T> xs)
	{
		T last = null;
		for (final T x : xs) {
			if (last != null && !x.equals(last)) {
				return false;
			}
			last = x;
		}
		return true;
	}

	public static <T> boolean allEqual(final T[] xs)
	{
		return allEqual(asList(xs));
	}

	// ---------------------------------------------------------------

	public static void forEach(final IntConsumer f, final int... xs)
	{
		for (final int x : xs) {
			f.accept(x);
		}
	}

	public static void forEach(final DoubleConsumer f, final double... xs)
	{
		for (final double x : xs) {
			f.accept(x);
		}
	}

	public static void forEach(final LongConsumer f, final long... xs)
	{
		for (final long x : xs) {
			f.accept(x);
		}
	}

	@SafeVarargs
	public static <T> void forEach(final Consumer<T> f, final T... xs)
	{
		for (final T x : xs) {
			f.accept(x);
		}
	}

	public static <T> void forEach(final Consumer<T> f, final Collection<T> xs)
	{
		for (final T x : xs) {
			f.accept(x);
		}
	}

	public static void main(final String[] args)
	{
		final int[] originalInt = { 1, 2, 3, 4, 5 };
		final String[] originalT = { "1", "2", "3", "4", "5" };

		final int[] x = collect(drop(2, originalInt));
		final List<String> y = collect(drop(4, originalT));

		System.out.println(asString(x));
		System.out.println(asString(y));

		final int[] z = collect(take(2, originalInt));
		// final List<String> w = collect(take(4, originalT));

		System.out.println(asString());
		System.out.println(asString(z));
		// System.out.println(asString(w));

		// final IntUnaryOperator f = i -> x[i];
		// System.out.println(f.applyAsInt(0));
		// x[0] = 100;
		// System.out.println(f.applyAsInt(0));
	}
}
