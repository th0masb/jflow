/**
 *
 */
package org.lhasalimited.common.math.geometry;

import static org.lhasalimited.common.chainutilities.CollectionUtil.asFunction;
import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.CollectionUtil.str;
import static org.lhasalimited.common.chainutilities.CombineUtil.combine;
import static org.lhasalimited.common.chainutilities.FilterUtil.filter;
import static org.lhasalimited.common.chainutilities.FoldUtil.accumulate;
import static org.lhasalimited.common.chainutilities.MapUtil.doubleMap;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.isZero;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.min;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.sum;
import static org.lhasalimited.common.chainutilities.RangeUtil.doubleRange;
import static org.lhasalimited.common.chainutilities.RangeUtil.range;
import static org.lhasalimited.common.math.Constants.EPSILON;

import java.util.List;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.chains.DoubleChain;
import org.lhasalimited.common.math.geometry.point.IPointND;
import org.lhasalimited.common.math.geometry.point.impl.PointNDImpl;

/**
 * @author t
 *
 */
public interface ICurve extends IRealSpaceMember
{
	double RANGE_TOLERANCE = 0.015;
	int LENGTH_APPROX_STEPS = 10;

	IPointND transform(double t);

	@Override
	default int dim()
	{
		return transform(0).dim();
	}

	static double length(final ICurve c)
	{
		return length(c, LENGTH_APPROX_STEPS);
	}

	static double length(final ICurve c, final int steps)
	{
//		throw new RuntimeException("Sort me out");
		final DoubleChain ts = doubleRange(0, 1 + EPSILON, 1.0 / steps);

		return range(len(ts) - 1).stream()
				.mapToDouble(i -> c.transform(ts.elementAt(i)).distL2(c.transform(ts.elementAt(i + 1))))
				.sum();
	}

	static ICurve fuse(final ICurve... cs)
	{
		return fuse(asFunction(cs));
	}

	static ICurve fuse(final List<ICurve> cs)
	{
		return fuse(asFunction(cs));
	}

	static ICurve fuse(final Chain<ICurve> cs)
	{
		final int n = len(cs);
		assert len(cs) > 0;

		final DoubleChain ls = doubleMap(ICurve::length, cs);
		final double sumLen = sum(ls).getAsDouble();
		final double[] lenRatios = accumulate((a, b) -> a + b, doubleMap(x -> x / sumLen, ls));
		lenRatios[len(lenRatios) - 1] = 1;
		return t -> {
			assert rangeWithinBounds(t) : "t out of bounds: " + t;
			final int[] notPassed = filter(m -> lenRatios[m] >= min(t, 1), range(n));
			assert len(notPassed) > 0 : "t: " + t + ", lenRatios: " + str(lenRatios);
			/* we look for the first cumulative ratio which has not been passed by t */
			final int first = notPassed[0];
			final double prevRatio = isZero(first) ? 0 : lenRatios[first - 1];
			final double t0 = (t - prevRatio) / (lenRatios[first] - prevRatio);
			assert rangeWithinBounds(t0) : "t0 out of bounds: " + t0 + ", t: " + t + ", lenratios: " + str(lenRatios);
			return cs.elementAt(first).transform(t0);
		};
	}

	static ICurve straightLine(final IPointND p1, final IPointND p2)
	{
		assert IRealSpaceMember.dimensionsAgree(p1, p2);
		return t -> {
			assert rangeWithinBounds(t) : "t out of bounds: " + t;
			return new PointNDImpl(combine((x, y) -> (1 - t) * x + t * y, p1, p2));
		};
	}

	static ICurve quadLine(final IPointND p1, final IPointND c, final IPointND p2)
	{
		assert IRealSpaceMember.dimensionsAgree(p1, c, p2);
		final int n = p1.dim();

		return t -> {
			assert rangeWithinBounds(t) : "t out of bounds: " + t;
			final double[] newCoords = new double[n];
			final double s = 1 - t;
			for (int i = 0; i < n; i++) {
				newCoords[i] = s * s * p1.elementAt(i) + 2 * t * s * c.elementAt(i) + t * t * p2.elementAt(i);
			}
			return new PointNDImpl(newCoords);
		};
	}

	static ICurve cubicLine(final IPointND p1, final IPointND c1, final IPointND c2, final IPointND p2)
	{
		assert IRealSpaceMember.dimensionsAgree(p1, c1, c2, p2);
		final int n = p1.dim();

		return t -> {
			assert rangeWithinBounds(t) : "t out of bounds: " + t;
			final double[] newCoords = new double[n];
			final double s = 1 - t;
			final double a = s * s * s, b = 3 * s * s * t, c = 3 * s * t * t, d = t * t * t;
			for (int i = 0; i < n; i++) {
				newCoords[i] = a * p1.elementAt(i) + b * c1.elementAt(i) + c * c2.elementAt(i) + d * p2.elementAt(i);
			}
			return new PointNDImpl(newCoords);
		};
	}

	static boolean rangeWithinBounds(final double t)
	{
		return -RANGE_TOLERANCE <= t && t <= 1 + RANGE_TOLERANCE;
	}
}
