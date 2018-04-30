/**
 * Copyright © 2018 Lhasa Limited
 * File created: 20 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry;

import static java.lang.Math.sin;
import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.MapUtil.doubleMap;
import static org.lhasalimited.common.chainutilities.MapUtil.objMap;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.max;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.min;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.signum;
import static org.lhasalimited.common.chainutilities.RangeUtil.objRange;
import static org.lhasalimited.common.math.Constants.EPSILON;
import static org.lhasalimited.common.math.Constants.INFINITY;
import static org.lhasalimited.common.math.Constants.NEG_INFINITY;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.geometry.canvasbinding.FXContextBinding;
import org.lhasalimited.common.math.geometry.canvasbinding.IFillable2D;
import org.lhasalimited.common.math.geometry.canvasbinding.IStrokeable2D;
import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.point.IPointTransform2D;
import org.lhasalimited.common.math.geometry.point.impl.Mappings2D;
import org.lhasalimited.common.math.geometry.splines.Line2D;

import com.google.common.collect.ImmutableList;

/**
 * @author ThomasB
 * @since 20 Feb 2018
 */
public class Polygon implements Chain<IPoint2D>, IStrokeable2D, IFillable2D
{
	private final ImmutableList<IPoint2D> corners;

	private Polygon(final Chain<IPoint2D> corners)
	{
		this.corners = corners.toList();
	}

	private Polygon(final ImmutableList<IPoint2D> corners)
	{
		this.corners = corners;
	}

	public static Polygon of(final ImmutableList<IPoint2D> corners)
	{
		return new Polygon(corners);
	}

	public static Polygon of(final Stream<IPoint2D> corners)
	{
		return new Polygon(corners.collect(ImmutableList.toImmutableList()));
	}

	public static Polygon of(final IPoint2D... corners)
	{
		return new Polygon(ImmutableList.copyOf(corners));
	}

	public static Polygon of(final Chain<IPoint2D> corners)
	{
		return new Polygon(corners);
	}

	public static Polygon regularFromSideLength(final int nSides, final double sideLength)
	{
		if (nSides < 3 || sideLength < EPSILON) {
			throw new IllegalArgumentException();
		}
		// First calc radius
		final double thetaStep = 2*Math.PI/nSides;
		final double radius = sideLength/sin(thetaStep/2);

		final IPoint2D origin = IPoint2D.of(0, 0);
		final IPointTransform2D mainRot = Mappings2D.rotateAbout(origin, thetaStep);
		final IPointTransform2D altRot = Mappings2D.rotateAbout(origin, thetaStep/2);

		IPoint2D p = altRot.apply(IPoint2D.of(0, -radius));
		final ImmutableList.Builder<IPoint2D> ps = ImmutableList.builder();
		ps.add(p);
		for (int i = 0; i < nSides - 1; i++) {
			p = mainRot.apply(p);
			ps.add(p);
		}
		return new Polygon(ps.build());
	}

	public static Polygon regularFromRadius(final int nSides, final double radius)
	{
		final double thetaStep = 2*Math.PI/nSides;
		final IPoint2D origin = IPoint2D.of(0, 0);
		final IPointTransform2D mainRot = Mappings2D.rotateAbout(origin, thetaStep);
		final IPointTransform2D altRot = Mappings2D.rotateAbout(origin, thetaStep/2);

		IPoint2D p = altRot.apply(IPoint2D.of(0, -radius));
		final ImmutableList.Builder<IPoint2D> ps = ImmutableList.builder();
		ps.add(p);
		for (int i = 0; i < nSides - 1; i++) {
			p = mainRot.apply(p);
			ps.add(p);
		}
		return new Polygon(ps.build());
	}

	@Override
	public void transformThenFill(final FXContextBinding gc, final IPointTransform2D T)
	{
		final double[] xs = doubleMap(T::mapToX, corners), ys = doubleMap(T::mapToY, corners);
		gc.getBoundContext().fillPolygon(xs, ys, linkCount());
	}

	@Override
	public void transformThenStroke(final FXContextBinding gc, final IPointTransform2D T)
	{
		final double[] xs = doubleMap(T::mapToX, corners), ys = doubleMap(T::mapToY, corners);
		gc.getBoundContext().strokePolygon(xs, ys, linkCount());
	}

	public List<Line2D> getBoundaryLines()
	{
		final int n = linkCount();
		return objRange(i -> Line2D.between(corners.get(i), corners.get((i + 1) % n)), n).toList();
	}

	public IPoint2D closestBoundaryPointTo(final IPoint2D p)
	{
		final List<Line2D> blines = getBoundaryLines();
		IPoint2D best = null;
		for (final Line2D bline : blines) {
			final IPoint2D closestCandidate = bline.closestPointTo(p);
			if (best == null || p.distL2(closestCandidate) < p.distL2(best)) {
				best = closestCandidate;
			}
		}
		return best;
	}

	public boolean contains(final IPoint2D p)
	{
		return windingNumber(p) != 0;
	}

	/**
	 * Calculates the winding number for a point relative to this polygon. The winding
	 * number is == 0 iff p is outside this polygon.
	 *
	 * Algorithm adapted from http://geomalgorithms.com/a03-_inclusion.html
	 *
	 * @param p
	 * @return
	 */
	public int windingNumber(final IPoint2D p)
	{
		final int n = linkCount();
		int wn = 0;

		for (int i = 0; i < n; i++) {
			if (corners.get(i).y() <= p.y()) {
				if (corners.get((i + 1) % n).y() > p.y()) {
					if (isLeft(corners.get(i), corners.get((i + 1) % n), p) > 0) {
						wn++;
					}
				}
			}
			else {
				if (corners.get((i + 1) % n).y() <= p.y()) {
					if (isLeft(corners.get(i), corners.get((i + 1) % n), p) < 0) {
						wn--;
					}
				}
			}
		}
		return wn;
	}

	/**
	 * Helper function in windingNumber method, see http://geomalgorithms.com/a03-_inclusion.html
	 */
	private double isLeft(final IPoint2D p0, final IPoint2D p1, final IPoint2D p2)
	{
		return signum( (p1.x() - p0.x()) * (p2.y() - p0.y()) - (p2.x() -  p0.x()) * (p1.y() - p0.y()) );
	}

	@Override
	public int dim()
	{
		return 2;
	}

	@Override
	public int linkCount()
	{
		return len(corners);
	}

	@Override
	public IPoint2D elementAt(final int index)
	{
		return corners.get(index);
	}

	@Override
	public Polygon map(final UnaryOperator<IPoint2D> f)
	{
		return new Polygon(objMap(f, this));
	}

	public Bounds2D getBounds()
	{
		double minX = INFINITY, maxX = NEG_INFINITY, minY = INFINITY, maxY = NEG_INFINITY;
		for (final IPoint2D p : corners) {
			minX = min(minX, p.x());
			minY = min(minY, p.y());
			maxX = max(maxX, p.x());
			maxY = max(maxY, p.y());
		}
		return Bounds2D.of(minX, minY, maxX - minX, maxY - minY);
	}

	@Override
	public String toString()
	{
		return corners.toString();
	}

	public static void main(final String[] args)
	{
		final Polygon triangle = Polygon.of(IPoint2D.of(0, 0), IPoint2D.of(1, 0), IPoint2D.of(1, 1));
		System.out.println(triangle.contains(IPoint2D.of(1, 1)));
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