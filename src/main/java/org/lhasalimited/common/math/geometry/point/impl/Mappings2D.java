/**
 * Copyright © 2018 Lhasa Limited
 * File created: 31 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.point.impl;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lhasalimited.common.math.Constants.PI;

import org.lhasalimited.common.math.geometry.Bounds2D;
import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.point.IPointTransform2D;
import org.lhasalimited.common.math.geometry.point.IScalingTransform2D;

/**
 * A utility class for generating common 2D -> 2D affine mappings
 *
 * @author ThomasB
 * @since 31 Jan 2018
 */
public final class Mappings2D
{
	private Mappings2D()
	{
	}
	
	public static IScalingTransform2D identity()
	{
		return new IScalingTransform2D() {
			@Override
			public IPoint2D apply(IPoint2D t) 
			{
				return IPoint2D.of(t.x(), t.y());
			}
			@Override
			public double getScalingFactor() 
			{
				return 1;
			}
		};
	}

	public static IScalingTransform2D shiftThenScale(final double dx, final double dy, final double scale)
	{
		return new IScalingTransform2D()
		{
			@Override
			public double mapToY(final IPoint2D p)
			{
				return (p.y() + dy)*scale;
			}

			@Override
			public double mapToX(final IPoint2D p)
			{
				return (p.x() + dx)*scale;
			}

			@Override
			public IPoint2D apply(final IPoint2D t)
			{
				return IPoint2D.of(mapToX(t), mapToY(t));
			}

			@Override
			public double getScalingFactor() {
				return scale;
			}
		};
	}

	public static IScalingTransform2D shiftThenScale(final IPoint2D dp, final double scale)
	{
		return shiftThenScale(dp.x(), dp.y(), scale);
	}

	public static IScalingTransform2D scaleThenShift(final double dx, final double dy, final double scale)
	{
		return new IScalingTransform2D()
		{
			@Override
			public double mapToY(final IPoint2D p)
			{
				return p.y()*scale + dy;
			}

			@Override
			public double mapToX(final IPoint2D p)
			{
				return p.x()*scale + dx;
			}

			@Override
			public IPoint2D apply(final IPoint2D t)
			{
				return IPoint2D.of(mapToX(t), mapToY(t));
			}

			@Override
			public double getScalingFactor() 
			{
				return scale;
			}
		};
	}

	public static IScalingTransform2D scaleThenShift(final IPoint2D dp, final double scale)
	{
		return scaleThenShift(dp.x(), dp.y(), scale);
	}

	public static IPointTransform2D rotateAbout(final IPoint2D rotCentre, final double theta)
	{
		final double cosTheta = cos(theta), sinTheta = sin(theta);

		return new IPointTransform2D() {

			@Override
			public double mapToX(final IPoint2D p)
			{
				final double transX = p.x() - rotCentre.x(), transY = p.y() - rotCentre.y();
				return (cosTheta*transX - sinTheta*transY) + rotCentre.x();
			}

			@Override
			public double mapToY(final IPoint2D p)
			{
				final double transX = p.x() - rotCentre.x(), transY = p.y() - rotCentre.y();
				return (sinTheta*transX + cosTheta*transY) + rotCentre.y();
			}

			@Override
			public IPoint2D apply(final IPoint2D t)
			{
				return IPoint2D.of(mapToX(t), mapToY(t));
			}
		};
	}

	public static IPointTransform2D rotate(final double theta)
	{
		final double cosTheta = cos(theta), sinTheta = sin(theta);

		return new IPointTransform2D() {

			@Override
			public double mapToX(final IPoint2D p)
			{
				return cosTheta*p.x() - sinTheta*p.y();
			}

			@Override
			public double mapToY(final IPoint2D p)
			{
				return sinTheta*p.x() + cosTheta*p.y();
			}

			@Override
			public IPoint2D apply(final IPoint2D t)
			{
				return IPoint2D.of(mapToX(t), mapToY(t));
			}
		};
	}

	public static IPointTransform2D scaleAbout(final IPoint2D scaleCentre, final double sf)
	{
		return new IPointTransform2D() {

			@Override
			public double mapToX(final IPoint2D p)
			{
				return scaleCentre.x() + sf*(p.x() - scaleCentre.x());
			}

			@Override
			public double mapToY(final IPoint2D p)
			{
				return scaleCentre.y() + sf*(p.y() - scaleCentre.y());
			}

			@Override
			public IPoint2D apply(final IPoint2D t)
			{
				return IPoint2D.of(mapToX(t), mapToY(t));
			}
		};
	}

	public static void main(final String[] args)
	{
		final Bounds2D b = Bounds2D.of(1, 1, 1, 1);
		System.out.println(b);
		final IPointTransform2D T = Mappings2D.rotateAbout(IPoint2D.of(0,  0), - PI/2);
		for (final IPoint2D p : b.getCorners()) {
			System.out.println("Before: " + p + " After: " + T.apply(p));
		}

		System.out.println();
		System.out.println(b.asRectangle().map(T));

		System.out.println(b.asRectangle().map(T).getBounds());

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