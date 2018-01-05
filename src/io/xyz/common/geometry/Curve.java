/**
 *
 */
package io.xyz.common.geometry;

import static io.xyz.common.funcutils.CollectionUtil.asList;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.FilterUtil.filter;
import static io.xyz.common.funcutils.FoldUtil.accumulate;
import static io.xyz.common.funcutils.MapUtil.doubleRange;
import static io.xyz.common.funcutils.RangeUtil.drange;
import static io.xyz.common.funcutils.RangeUtil.range;
import static io.xyz.common.geometry.Constants.EPSILON;

import java.util.Arrays;
import java.util.List;

import io.xyz.common.matrix.RPoint;
import io.xyz.common.matrix.impl.RPointImpl;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;

/**
 * @author t
 *
 */
public interface Curve {
	int LENGTH_APPROX_STEPS = 10;

	RPoint transform(double t);

	default int dim() {
		return transform(0).dim();
	}

	static double length(final Curve c) {
		return length(c, LENGTH_APPROX_STEPS);
	}

	static double length(final Curve c, final int steps) {
		final DoubleRangeDescriptor ts = drange(0, 1 + EPSILON, 1.0 / steps);
		return accumulate((t1, t2) -> c.transform(t1).distL2(c.transform(t2)), 0, ts);
	}

	static Curve fuse(final Curve... cs) {
		return fuse(asList(cs));
	}

	static Curve fuse(final List<Curve> cs) {
		final int n = len(cs);
		assert len(cs) > 0;

		final DoubleRangeDescriptor ls = doubleRange(Curve::length, cs);
		final double sumLen = accumulate((a, b) -> a + b, 0, ls);
		final double[] lenRatios = doubleRange(x -> x / sumLen, ls).toArray();
		Arrays.parallelPrefix(lenRatios, (a, b) -> a + b);
		return t -> {
			final int[] notPassed = filter(m -> lenRatios[m] <= t, range(n)).toArray();
			final int first = len(notPassed) == 0? n - 1 : notPassed[0];
			final double prevRatio = first == 0? 0 : lenRatios[first - 1];
			final double t0 = (t - prevRatio) / (lenRatios[first] - prevRatio);
			return cs.get(first).transform(t0);
		};
	}

	static Curve straightLine(final RPoint p1, final RPoint p2) {
		/*
		 * We could write nicer but we make faster. E.g
		 *
		 * t -> p1.scale(1-t).add(p2.scale(t));
		 */
		assert RPoint.dimensionsAgree(p1, p2);
		return t -> new RPointImpl(combine((x, y) -> (1 - t) * x + t * y, p1.coords(), p2.coords()));

		// final int n = p1.dim();
		// return t -> {
		// final double[] newCoords = new double[n];
		// for (int i = 0; i < n; i++) {
		// newCoords[i] = (1 - t) * p1.get(i) + t * p2.get(i);
		// }
		// return new RPoint(newCoords);
		// };
	}

	static Curve quadLine(final RPoint p1, final RPoint c, final RPoint p2) {
		assert RPoint.dimensionsAgree(p1, c, p2);
		final int n = p1.dim();

		return t -> {
			final double[] newCoords = new double[n];
			final double s = 1 - t;
			for (int i = 0; i < n; i++) {
				newCoords[i] = s * s * p1.get(i) + 2 * t * s * c.get(i) + t * t * p2.get(i);
			}
			return new RPointImpl(newCoords);
		};
	}

	static Curve cubicLine(final RPoint p1, final RPoint c1, final RPoint c2, final RPoint p2) {
		assert RPoint.dimensionsAgree(p1, c1, c2, p2);
		final int n = p1.dim();

		return t -> {
			final double[] newCoords = new double[n];
			final double s = 1 - t;
			final double a = s * s * s, b = 3 * s * s * t, c = 3 * s * t * t, d = t * t * t;
			for (int i = 0; i < n; i++) {
				newCoords[i] = a * p1.get(i) + b * c1.get(i) + c * c2.get(i) + d * p2.get(i);
			}
			return new RPointImpl(newCoords);
		};
	}
}
