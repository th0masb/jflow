package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.RangeUtil.rangeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 *
 * @author t
 *
 */
public final class CollectionUtil {
	private CollectionUtil() {
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
}
