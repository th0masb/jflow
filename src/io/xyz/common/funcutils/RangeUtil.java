/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.asList;
import static io.xyz.common.funcutils.PrimitiveUtil.abs;
import static io.xyz.common.funcutils.PrimitiveUtil.max;
import static io.xyz.common.funcutils.PrimitiveUtil.signum;
import static io.xyz.common.geometry.Constants.EPSILON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

/**
 * @author t
 *
 */
public final class RangeUtil {
	private RangeUtil() {
	}

	public static <T> List<T> rangeMap(final IntFunction<T> f, final int upperBound) {
		final List<T> mapped = new ArrayList<>(upperBound);
		for (int i = 0; i < upperBound; i++) {
			mapped.add(f.apply(i));
		}
		return mapped;
	}

	public static <T> List<T> rangeMap(final IntFunction<T> f, final int start, final int end, final int step) {
		final int boundDiff = end - start, stepSign = signum(step);
		if (start == end) {
			return Collections.emptyList();
		} else if (signum(boundDiff) != stepSign) {
			return asList(f.apply(start));
		} else {
			final int n = max(boundDiff / step, 1);
			final List<T> mapped = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				mapped.add(f.apply(start + i * step));
			}
			return mapped;
		}
	}

	public static int[] rangeMapToInt(final IntUnaryOperator f, final int upperBound) {
		final int[] mapped = new int[upperBound];
		for (int i = 0; i < upperBound; i++) {
			mapped[i] = f.applyAsInt(i);
		}
		return mapped;
	}

	public static double[] rangeToDouble(final DoubleUnaryOperator f, final int upperBound) {
		final double[] mapped = new double[upperBound];
		for (int i = 0; i < upperBound; i++) {
			mapped[i] = f.applyAsDouble(i);
		}
		return mapped;
	}

	public static <T> Stream<T> rangeStream(final IntFunction<T> f, final int upperBound) {
		final List<T> mapped = new ArrayList<>(upperBound);
		for (int i = 0; i < upperBound; i++) {
			mapped.add(f.apply(i));
		}
		return mapped.stream();
	}

	public static int[] range(final int endBound) {
		return range(0, endBound);
	}

	public static int[] rangei(final int upperBound) {
		return range(upperBound + (upperBound > 0? 1 : -1));
	}

	public static int[] range(final int startBound, final int endBound) {
		return range(startBound, endBound, 1);
	}

	public static int[] rangei(final int startBound, final int endBound) {
		return range(startBound, endBound + (startBound < endBound? 1 : -1), 1);
	}

	/**
	 * Think geometrically
	 *
	 * @param startBound
	 * @param endBound
	 * @param step
	 * @return
	 */
	public static int[] range(final int startBound, final int endBound, final int step) {
		assert step != 0 : "Cannot have 0 step";
		final int boundDiff = endBound - startBound, stepSign = signum(step);

		if (startBound == endBound) {
			return new int[] {};
		} else if (signum(boundDiff) != stepSign) {
			return new int[] { startBound };
		} else {
			final int n = max(boundDiff / step, 1);
			final int[] range = new int[n];
			for (int i = 0; i < n; i++) {
				range[i] = startBound + i * step;
			}
			return range;
		}
	}

	public static double[] drange(final int upper) {
		return drange(0, upper, 1);
	}

	public static double[] drange(final double lower, final double upper, final double step) {
		assert abs(step) >= EPSILON : "Cannot have 0 step";
		final double boundDiff = upper - lower, stepSign = signum(step);

		if (abs(upper - lower) < EPSILON) {
			return new double[] {};
		} else if (signum(boundDiff) != stepSign) {
			return new double[] { lower };
		} else {
			final int n = (int) max(boundDiff / step, 1);
			final double[] range = new double[n];
			for (int i = 0; i < n; i++) {
				range[i] = lower + i * step;
			}
			return range;
		}
	}

	// public static double[] drangei(final int upper) {
	// return drange(upper + 1);
	// }
	//
	// public static double[] drangei(final int upper) {
	// return drange(0, upper, 1);
	// }
}
