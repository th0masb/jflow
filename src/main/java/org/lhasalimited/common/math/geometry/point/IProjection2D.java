/**
 * Copyright © 2018 Lhasa Limited
 * File created: 31 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.point;

import java.util.function.Function;

/**
 * An interface representing a projection from R^n where n > 1 to R^2
 * i.e. a projection into 2D space. Note that this represents all mappings
 * from R^2 to R^2 as well but we have a separate dedicated interface for that.
 *
 * @author ThomasB
 * @since 31 Jan 2018
 */
public interface IProjection2D extends Function<IPointND, IPoint2D>
{
	/**
	 * The function which calculates the x coordinate of the resulting projection output
	 *
	 * @param p - The input point
	 * @return the x coordinate of the output point
	 */
	double projectToX(IPointND p);

	/**
	 * The function which calculates the y coordinate of the resulting projection output
	 *
	 * @param p - The input point
	 * @return the y coordinate of the output point.
	 */
	double projectToY(IPointND p);

	/**
	 * The full projection.
	 *
	 * @param p - The input point
	 * @return the output point.
	 */
	@Override
	default IPoint2D apply(final IPointND p)
	{
		return IPoint2D.of(projectToX(p), projectToY(p));
	}

	default IProjection2D andThen(final IPointTransform2D T)
	{
		final IProjection2D original = this;
		return new IProjection2D() {

			@Override
			public double projectToY(final IPointND p)
			{
				return T.mapToY(original.apply(p));
			}

			@Override
			public double projectToX(final IPointND p)
			{
				return T.mapToX(original.apply(p));
			}
		};
	}

	/**
	 * The XY projection acts as the identity mapping for a 2D point, for higher
	 * dimensional points it  projects them into the X-Y plane.
	 */
	static IProjection2D XY_PROJECTION = new IProjection2D() {
		@Override
		public double projectToX(final IPointND p)
		{
			return p.x();
		}

		@Override
		public double projectToY(final IPointND p)
		{
			return p.y();
		}
	};


	public static void main(final String[] args)
	{
//		final IPointTransform2D translate = new IPointTransform2D() {
//
//			@Override
//			public double mapToY(final IPoint2D p)
//			{
//				return p.y() + 1;
//			}
//
//			@Override
//			public double mapToX(final IPoint2D p)
//			{
//				return p.x() + 1;
//			}
//		};
//
//		final IProjection2D projTranslate = XY_PROJECTION.andThen(translate);
//
//		final IPointND p = IPointND.of(1, 1, 3);
//		System.out.println(projTranslate.projectToX(p));
//		System.out.println(projTranslate.projectToY(p));
//		System.out.println(projTranslate.apply(p));
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