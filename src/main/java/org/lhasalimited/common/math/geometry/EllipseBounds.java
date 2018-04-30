/**
 * Copyright © 2018 Lhasa Limited
 * File created: 12 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.abs;
import static org.lhasalimited.common.math.Constants.PI;
import static org.lhasalimited.common.math.Constants.ROOT_2;

import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.splines.Line2D;

/**
 * @author ThomasB
 * @since 12 Feb 2018
 */
public class EllipseBounds
{
	private final Bounds2D bounds;

	/**
	 *
	 */
	private EllipseBounds(final Bounds2D bounds)
	{
		this.bounds = bounds;
	}

	private EllipseBounds(final double centerX, final double centerY, final double halfWidth, final double halfHeight)
	{
		this (Bounds2D.of(centerX - halfWidth, centerY - halfHeight, 2*halfWidth, 2*halfHeight));
	}

	public static EllipseBounds enclosedBy(final Bounds2D bounds)
	{
		return new EllipseBounds(bounds);
	}

	public static EllipseBounds enclosing(final Bounds2D bounds)
	{
		return new EllipseBounds(bounds.scale(ROOT_2*1.15));
	}

	/**
	 * Given some point P in the plane which is distinct from the centre point C of this ellipse this
	 * method calculates the intersection point of the ellipse with the line PC which is closest to P.
	 *
	 * @param from - some point in the plane distinct from the centre of this ellipse.
	 * @return the intersection point described above.
	 */
	public IPoint2D intersectionPoint(final IPoint2D from)
	{
		final IPoint2D centre = bounds.centre();
		assert !centre.equals(from) : "The argument cannot be the centre point of this ellipse";

		final Line2D a = Line2D.between(from, centre), b = Line2D.between(centre, IPoint2D.of(centre.x() + bounds.width()/2, centre.y()));
		final double absPathAngle = abs(a.pathAngleWith(b));
		final double theta = from.y() < centre.y()? PI + absPathAngle : PI - absPathAngle;
		return IPoint2D.of(cos(theta)*bounds.width()/2 + centre.x(), sin(theta)*bounds.height()/2 + centre.y());
	}

	public IPoint2D getMaxX()
	{
		return IPoint2D.of(bounds.maxX(), (bounds.maxY() + bounds.minY())/2);
	}

	public IPoint2D getMinX()
	{
		return IPoint2D.of(bounds.minX(), (bounds.maxY() + bounds.minY())/2);
	}

//	/**
//	 * Temporary, shouldn't be allowed to put arbitrary dim point in
//	 */
//	public IPoint2D intersectionPoint(final IPointND from)
//	{
//		assert from.dim() == 2;
//		final IPoint2D centre = bounds.centre();
//		assert !centre.equals(from) : "The argument cannot be the centre point of this ellipse";
//
//		final Line a = Line.between(from, centre), b = Line.between(centre, IPoint2D.immutableOf(centre.x() + bounds.width()/2, centre.y()));
//		final double absPathAngle = abs(a.pathAngleWith(b));
//		final double theta = from.y() < centre.y()? Math.PI + absPathAngle : Math.PI - absPathAngle;
//		return IPoint2D.immutableOf(cos(theta)*bounds.width()/2 + centre.x(), sin(theta)*bounds.height()/2 + centre.y());
//	}

	public static void main(final String[] args)
	{
		final EllipseBounds e = EllipseBounds.enclosedBy(Bounds2D.of(0, 0, 10, 10));
		final IPoint2D intersection = e.intersectionPoint(IPoint2D.of(5, 15));
		System.out.println(intersection.x() + ", " + intersection.y());
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