/**
 *
 */
package io.xyz.common.funcutils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**
 * @author t
 *
 */
public final class MapUtil {
	private MapUtil() {
	}

	public static double[] mapToDouble(final IntToDoubleFunction f, final int[] xs) {
		final double[] result = new double[xs.length];
		for (int i = 0; i<xs.length; i++) {
			result[i] = f.applyAsDouble(xs[i]);
		}
		return result;
	}

	@SafeVarargs
	public static <T> double[] mapToDouble(final ToDoubleFunction<T> f, final T... xs) {
		final double[] result = new double[xs.length];
		for (int i = 0; i<xs.length; i++) {
			result[i] = f.applyAsDouble(xs[i]);
		}
		return result;
	}

	public static <T> double[] mapToDouble(final ToDoubleFunction<T> f, final List<T> xs) {
		final double[] result = new double[xs.size()];
		for (int i = 0; i<xs.size(); i++) {
			result[i] = f.applyAsDouble(xs.get(i));
		}
		return result;
	}

	public static int[] mapToInt(final DoubleToIntFunction f, final double[] xs) {
		final int[] result = new int[xs.length];
		for (int i = 0; i<xs.length; i++) {
			result[i] = f.applyAsInt(xs[i]);
		}
		return result;
	}

	@SafeVarargs
	public static <T> int[] mapToInt(final ToIntFunction<T> f, final T... xs) {
		final int[] result = new int[xs.length];
		for (int i = 0; i<xs.length; i++) {
			result[i] = f.applyAsInt(xs[i]);
		}
		return result;
	}

	public static <T> int[] mapToInt(final ToIntFunction<T> f, final List<T> xs) {
		final int[] result = new int[xs.size()];
		for (int i = 0; i<xs.size(); i++) {
			result[i] = f.applyAsInt(xs.get(i));
		}
		return result;
	}

	public static double[] map(final DoubleUnaryOperator f, final double[] xs) {
		final double[] result = new double[xs.length];
		for (int i = 0; i<xs.length; i++) {
			result[i] = f.applyAsDouble(xs[i]);
		}
		return result;
	}

	public static int[] map(final IntUnaryOperator f, final int[] xs) {
		final int[] result = new int[xs.length];
		for (int i = 0; i<xs.length; i++) {
			result[i] = f.applyAsInt(xs[i]);
		}
		return result;
	}

	public static <T, S> List<S> map(final Function<T, S> f, final T[] xs) {
		final List<S> result = new ArrayList<>(xs.length);
		for (int i = 0; i<xs.length; i++) {
			result.add(f.apply(xs[i]));
		}
		return result;
	}

	public static <T, S> List<S> map(final Function<T, S> f, final List<T> xs) {
		final List<S> result = new ArrayList<>(xs.size());
		for (int i = 0; i<xs.size(); i++) {
			result.add(f.apply(xs.get(i)));
		}
		return result;
	}

	public static <T, S> Set<S> map(final Function<T, S> f, final Set<T> xs) {
		final Set<S> result = new HashSet<>(xs.size());
		for (final T x : xs) {
			result.add(f.apply(x));
		}
		return result;
	}

	public static double[] mapIP(final DoubleUnaryOperator f, final double[] xs) {
		for (int i = 0; i<xs.length; i++) {
			xs[i] = f.applyAsDouble(xs[i]);
		}
		return xs;
	}

	public static int[] mapIP(final IntUnaryOperator f, final int[] xs) {
		for (int i = 0; i<xs.length; i++) {
			xs[i] = f.applyAsInt(xs[i]);
		}
		return xs;
	}

	public static <T> List<T> mapToObj(final IntFunction<T> f, final int[] xs) {
		final List<T> mappedList = new ArrayList<>(xs.length);
		for (final int x : xs) {
			mappedList.add(f.apply(x));
		}
		return mappedList;
	}

	public static <T> Stream<T> mapToObjStream(final IntFunction<T> f, final int[] xs) {
		return mapToObj(f, xs).stream();
	}

	public static <T> List<T> mapToObj(final DoubleFunction<T> f, final double[] xs) {
		final List<T> mappedList = new ArrayList<>(xs.length);
		for (final double x : xs) {
			mappedList.add(f.apply(x));
		}
		return mappedList;
	}

	public static <T> Stream<T> mapToObjStream(final DoubleFunction<T> f, final double[] xs) {
		return mapToObj(f, xs).stream();
	}
}
