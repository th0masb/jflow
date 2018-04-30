/**
 * Copyright © 2018 Lhasa Limited
 * File created: 19 Feb 2018 by thomasb
 * Creator : thomasb
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry;

import static org.lhasalimited.common.chainutilities.CollectionUtil.asFunction;
import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.CollectionUtil.str;
import static org.lhasalimited.common.chainutilities.FilterUtil.filter;
import static org.lhasalimited.common.chainutilities.FoldUtil.accumulate;
import static org.lhasalimited.common.chainutilities.MapUtil.doubleMap;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.isZero;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.min;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.sum;
import static org.lhasalimited.common.chainutilities.RangeUtil.range;

import java.util.List;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.chains.DoubleChain;
import org.lhasalimited.common.math.geometry.point.IPoint2D;

/**
 * @author thomasb
 * @since 19 Feb 2018
 */
public interface ICurve2D extends ICurve
{
	@Override
	IPoint2D transform(double t);

	@Override
	default int dim()
	{
		return 2;
	}

	static ICurve2D fuse(final ICurve2D... cs)
	{
		return fuse(asFunction(cs));
	}

	static ICurve2D fuse(final List<ICurve2D> cs)
	{
		return fuse(asFunction(cs));
	}

	static ICurve2D fuse(final Chain<ICurve2D> cs)
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

	static ICurve2D straightLine(final IPoint2D p1, final IPoint2D p2)
	{
		return t -> {
			assert rangeWithinBounds(t) : "t out of bounds: " + t;
			return IPoint2D.of((1 - t) * p1.x() + t * p2.x(), (1 - t) * p1.y() + t * p2.y());
		};
	}

	static ICurve2D quadLine(final IPoint2D p1, final IPoint2D c, final IPoint2D p2)
	{
		return t -> {
			assert rangeWithinBounds(t) : "t out of bounds: " + t;
			final double s = 1 - t;
			return IPoint2D.of(
					s * s * p1.x() + 2 * t * s * c.x() + t * t * p2.x(),
					s * s * p1.y() + 2 * t * s * c.y() + t * t * p2.y());
		};
	}

	static ICurve2D cubicLine(final IPoint2D p1, final IPoint2D c1, final IPoint2D c2, final IPoint2D p2)
	{
		return t -> {
			assert rangeWithinBounds(t) : "t out of bounds: " + t;
			final double s = 1 - t;
			final double a = s * s * s, b = 3 * s * s * t, c = 3 * s * t * t, d = t * t * t;
			return IPoint2D.of(
					a*p1.x() + b*c1.x() + c*c2.x() + d*p2.x(),
					a*p1.y() + b*c1.y() + c*c2.y() + d*p2.y());
		};
	}

	static boolean rangeWithinBounds(final double t)
	{
		return -RANGE_TOLERANCE <= t && t <= 1 + RANGE_TOLERANCE;
	}
}

/* ---------------------------------------------------------------------*
 * This software is the confidential and proprietary
 * information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS
 * ---
 * No part of this confidential information shall be disclosed
 * and it shall be used only in accordance with the terms of a
 * written license agreement entered into by holder of the information
 * with LHASA Ltd.
 * --------------------------------------------------------------------- */