/**
 *
 */
package io.xyz.common.geometry;

import static io.xyz.common.funcutils.CollectionUtil.asList;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.FilterUtil.filter;
import static io.xyz.common.funcutils.FoldUtil.accumulate;
import static io.xyz.common.funcutils.MapUtil.mapIP;
import static io.xyz.common.funcutils.MapUtil.mapToDouble;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;
import static io.xyz.common.funcutils.RangeUtil.drange;
import static io.xyz.common.funcutils.RangeUtil.range;
import static io.xyz.common.geometry.Constants.EPSILON;

import java.util.Arrays;
import java.util.List;

import io.xyz.common.matrix.impl.RPointImpl;

/**
 * @author t
 *
 */
public interface Curve {
	int LENGTH_APPROX_STEPS = 10;

	RPointImpl map(double t);

	default int dim() {
		return map(0).dim();
	}

	static double length(final Curve c) {
		return length(c, LENGTH_APPROX_STEPS);
	}

	static double length(final Curve c, final int steps) {
		final double[] ts = drange(0, 1 + EPSILON, 1.0 / steps);
		return accumulate((t1, t2) -> c.map(t1).distFrom(c.map(t2)), 0, ts);
	}

	static Curve fuse(final Curve... cs) {
		return fuse(asList(cs));
	}

	static Curve fuse(final List<Curve> cs) {
		final int n = len(cs);
		final double[] ls = mapToDouble(Curve::length, cs);
		final double sumLen = sum(ls);
		mapIP(x -> x / sumLen, ls);
		Arrays.parallelPrefix(ls, (a, b) -> a + b);
		return t -> {
			final int[] notPassed = filter(m -> ls[m] <= t, range(n));
			final int first = notPassed.length == 0? n - 1 : notPassed[0];
			final double prevRatio = first == 0? 0 : ls[first - 1];
			final double t0 = (t - prevRatio) / (ls[first] - prevRatio);
			return cs.get(first).map(t0);
		};
	}

	static Curve straightLine(final RPointImpl p1, final RPointImpl p2) {
		/*
		 * We could write nicer but we make faster. E.g
		 *
		 * t -> p1.scale(1-t).add(p2.scale(t));
		 */
		assert RPointImpl.dimensionsAgree(p1, p2);
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

	static Curve quadLine(final RPointImpl p1, final RPointImpl c, final RPointImpl p2) {
		assert RPointImpl.dimensionsAgree(p1, c, p2);
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

	static Curve cubicLine(final RPointImpl p1, final RPointImpl c1, final RPointImpl c2, final RPointImpl p2) {
		assert RPointImpl.dimensionsAgree(p1, c1, c2, p2);
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
