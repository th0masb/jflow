package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.PrimitiveUtil.abs;
import static io.xyz.common.funcutils.RangeUtil.rangeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.UnaryOperator;

/**
 *
 * @author t
 *
 */
public final class CollectionUtil {
	private CollectionUtil() {
	}

	/*
	 * Need to add sort function
	 */

	public static int len(int n) {
		short len = 1;
		n = abs(n) / 10;
		while (n > 0) {
			len++;
			n /= 10;
		}
		return len;
	}

	public static int len(final String s) {
		return s.length();
	}

	public static <T> int len(final Collection<T> xs) {
		return xs.size();
	}

	public static <T> int len(final T[] xs) {
		return xs.length;
	}

	public static int len(final int[] xs) {
		return xs.length;
	}

	public static int len(final double[] xs) {
		return xs.length;
	}

	public static int len(final int[][] xs) {
		return xs.length;
	}

	public static int len(final double[][] xs) {
		return xs.length;
	}

	public static <T> List<T> asList(final Collection<T> c) {
		return new ArrayList<>(c);
	}

	@SafeVarargs
	public static <T> List<T> asList(final T... xs) {
		return map(UnaryOperator.identity(), xs);
	}

	public static <T> Set<T> asSet(final Collection<T> c) {
		return new HashSet<>(c);
	}

	@SafeVarargs
	public static <T> Set<T> asSet(final T... xs) {
		final Set<T> newSet = new HashSet<>();
		for (final T x : xs) {
			newSet.add(x);
		}
		return newSet;
	}

	public static int[] take(final int n, final int[] xs) {
		assert 0 <= n && n <= xs.length;
		return Arrays.copyOf(xs, n);
	}

	public static double[] take(final int n, final double[] xs) {
		assert 0 <= n && n <= xs.length;
		return Arrays.copyOf(xs, n);
	}

	public static <T> List<T> take(final int n, final T[] xs) {
		assert 0 <= n && n <= xs.length;
		return rangeMap(i -> xs[i], n);
	}

	public static <T> List<T> take(final int n, final List<T> xs) {
		assert 0 <= n && n <= xs.size();
		return xs.subList(0, n);
	}

	public static int[] drop(final int n, final int[] xs) {
		assert 0 <= n && n <= xs.length;
		final int newLength = xs.length - n;
		final int[] remaining = new int[newLength];
		System.arraycopy(xs, n, remaining, 0, newLength);
		return remaining;
	}

	public static double[] drop(final int n, final double[] xs) {
		assert 0 <= n && n <= xs.length;
		final int newLength = xs.length - n;
		final double[] remaining = new double[newLength];
		System.arraycopy(xs, n, remaining, 0, newLength);
		return remaining;
	}

	public static <T> List<T> drop(final int n, final T[] xs) {
		assert 0 <= n && n <= xs.length;
		return rangeMap(i -> xs[i], n, xs.length, 1);
	}

	public static <T> List<T> drop(final int n, final List<T> xs) {
		assert 0 <= n && n <= xs.size();
		return xs.subList(0, n);
	}

	public static boolean allEqual(final int... xs) {
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (xs[i] != xs[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static boolean allEqual(final double... xs) {
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (xs[i] != xs[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean allEqual(final T[] xs) {
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (!xs[i].equals(xs[i + 1])) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean allEqual(final Collection<T> xs) {
		T last = null;
		for (final T x : xs) {
			if (last != null && !x.equals(last)) {
				return false;
			}
			last = x;
		}
		return true;
	}

	public static void forEach(final IntConsumer f, final int... xs) {
		for (final int x : xs) {
			f.accept(x);
		}
	}

	public static void forEach(final DoubleConsumer f, final double... xs) {
		for (final double x : xs) {
			f.accept(x);
		}
	}

	public static void forEach(final LongConsumer f, final long... xs) {
		for (final long x : xs) {
			f.accept(x);
		}
	}

	@SafeVarargs
	public static <T> void forEach(final Consumer<T> f, final T... xs) {
		for (final T x : xs) {
			f.accept(x);
		}
	}

	public static <T> void forEach(final Consumer<T> f, final Collection<T> xs) {
		for (final T x : xs) {
			f.accept(x);
		}
	}
}
