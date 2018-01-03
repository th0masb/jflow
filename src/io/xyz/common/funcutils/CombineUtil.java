/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.mapToInt;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntToDoubleFunction;

/**
 * @author t
 */
public final class CombineUtil {
	private CombineUtil() {
	}

	public static double[] concat(final double[]... xs) {
		final int n = sum(mapToInt(x -> x.length, xs));
		final double[] combined = new double[n];
		int counter = 0;
		for (int i = 0; i < xs.length; i++) {
			final int len = xs[i].length;
			System.arraycopy(xs, 0, combined, counter, len);
			counter += len;
		}
		return combined;
	}

	// public static double[] join(final List<double[]> xs) {
	// final int n = sum(mapToInt(x -> x.length, xs));
	// final double[] combined = new double[n];
	// int counter = 0;
	// for (int i = 0; i < xs.length; i++) {
	// final int len = xs[i].length;
	// System.arraycopy(xs, 0, combined, counter, len);
	// counter += len;
	// }
	// return combined;
	// }

	// public static double[] combine(final List<double[]> xs) {
	// final int n = sum(mapToInt(x -> x.length, xs));
	// final double[] combined = new double[n];
	// int counter = 0;
	// for (int i = 0; i<xs.size(); i++) {
	// final int len = xs.get(i).length;
	// System.arraycopy(xs, 0, combined, counter, len);
	// counter += len;
	// }
	// return combined;
	// }

	public static int[] concat(final int[]... xs) {
		final int n = sum(mapToInt(x -> x.length, xs));
		final int[] joined = new int[n];
		int counter = 0;
		for (int i = 0; i < len(xs); i++) {
			final int len = xs[i].length;
			System.arraycopy(xs, 0, joined, counter, len);
			counter += len;
		}
		return joined;
	}

	/**
	 * @param ld
	 *        Left delimiter
	 * @param cd
	 *        Central delimiter
	 * @param rd
	 *        Right delimiter
	 * @param xs
	 *        Strings to combine
	 * 
	 * @return The String ld + xs[0] + cd + xs[1] + ... + rd
	 */
	public static String concat(final String ld, final String cd, final String rd, final String[] xs) {
		final StringBuilder sb = new StringBuilder(ld);
		final int length = len(xs);
		for (int i = 0; i < length; i++) {
			sb.append(xs[i]);
			if (i < length - 1) {
				sb.append(cd);
			}
		}
		sb.append(rd);
		return sb.toString();
	}

	public static String concat(final String od, final String cd, final String[] xs) {
		return concat(od, cd, od, xs);
	}

	public static String concat(final String... xs) {
		final StringBuilder sb = new StringBuilder();
		for (final String x : xs) {
			sb.append(x);
		}
		return sb.toString();
	}

	/**
	 * @param ld
	 *        Left delimiter
	 * @param cd
	 *        Central delimiter
	 * @param rd
	 *        Right delimiter
	 * @param xs
	 *        Strings to combine
	 * 
	 * @return The String ld + xs[0] + cd + xs[1] + ... + rd
	 */
	public static String concat(final String ld, final String cd, final String rd, final List<String> xs) {
		final StringBuilder sb = new StringBuilder(ld);
		final int length = len(xs);
		for (int i = 0; i < length; i++) {
			sb.append(xs.get(i));
			if (i < length - 1) {
				sb.append(cd);
			}
		}
		sb.append(rd);
		return sb.toString();
	}

	public static String concat(final String od, final String cd, final List<String> xs) {
		return concat(od, cd, od, xs);
	}

	public static String concat(final List<String> xs) {
		final StringBuilder sb = new StringBuilder();
		for (final String x : xs) {
			sb.append(x);
		}
		return sb.toString();
	}

	@SafeVarargs
	public static <T> List<T> append(final List<T> xs, final T... ys) {
		final List<T> newList = new ArrayList<>(len(xs) + len(ys));
		newList.addAll(xs);
		for (final T y : ys) {
			xs.add(y);
		}
		return newList;
	}

	public static double[] combine(final DoubleBinaryOperator f, final double[] a, final double[] b) {
		assert a.length == b.length;
		final int size = a.length;
		final double[] combined = new double[size];
		for (int i = 0; i < size; i++) {
			combined[i] = f.applyAsDouble(a[i], b[i]);
		}
		return combined;
	}

	public static double[] combine(final DoubleBinaryOperator f, final IntToDoubleFunction a,
			final IntToDoubleFunction b, final int size) {
		final double[] combined = new double[size];
		for (int i = 0; i < size; i++) {
			combined[i] = f.applyAsDouble(a.applyAsDouble(i), b.applyAsDouble(i));
		}
		return combined;
	}

	public static int[] combine(final IntBinaryOperator f, final int[] a, final int[] b) {
		assert a.length == b.length;
		final int size = a.length;
		final int[] combined = new int[size];
		for (int i = 0; i < size; i++) {
			combined[i] = f.applyAsInt(a[i], b[i]);
		}
		return combined;
	}

	public static double dotProduct(final double[] a, final double[] b) {
		assert len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b));
	}

	public static double dotProduct(final IntToDoubleFunction a, final IntToDoubleFunction b, final int n) {
		return sum(combine((x, y) -> x * y, a, b, n));
	}
}
