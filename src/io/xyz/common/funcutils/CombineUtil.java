/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.MapUtil.mapToInt;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;

import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;

/**
 * @author t
 *
 */
public final class CombineUtil {
	private CombineUtil() {
	}

	public static double[] combine(final double[]... xs) {
		final int n = sum(mapToInt(x -> x.length, xs));
		final double[] combined = new double[n];
		int counter = 0;
		for (int i = 0; i<xs.length; i++) {
			final int len = xs[i].length;
			System.arraycopy(xs, 0, combined, counter, len);
			counter += len;
		}
		return combined;
	}

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

	public static int[] combine(final int[]... xs) {
		final int n = sum(mapToInt(x -> x.length, xs));
		final int[] combined = new int[n];
		int counter = 0;
		for (int i = 0; i<xs.length; i++) {
			final int len = xs[i].length;
			System.arraycopy(xs, 0, combined, counter, len);
			counter += len;
		}
		return combined;
	}

	// public static int[] combine(final List<int[]> xs) {
	// final int n = sum(mapToInt(x -> x.length, xs));
	// final int[] combined = new int[n];
	// int counter = 0;
	// for (int i = 0; i<xs.size(); i++) {
	// final int len = xs.get(i).length;
	// System.arraycopy(xs, 0, combined, counter, len);
	// counter += len;
	// }
	// return combined;
	// }

	public static double[] combine(final DoubleBinaryOperator f, final double[] a, final double[] b) {
		assert a.length==b.length;
		final int size = a.length;
		final double[] combined = new double[size];
		for (int i = 0; i<size; i++) {
			combined[i] = f.applyAsDouble(a[i], b[i]);
		}
		return combined;
	}

	public static int[] combine(final IntBinaryOperator f, final int[] a, final int[] b) {
		assert a.length==b.length;
		final int size = a.length;
		final int[] combined = new int[size];
		for (int i = 0; i<size; i++) {
			combined[i] = f.applyAsInt(a[i], b[i]);
		}
		return combined;
	}
}
