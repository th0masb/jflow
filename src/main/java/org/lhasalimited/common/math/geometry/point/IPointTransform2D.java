/**
 * Copyright © 2018 Lhasa Limited
 * File created: 31 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.point;

import java.util.function.UnaryOperator;

import org.lhasalimited.common.math.geometry.Bounds2D;

/**
 * @author ThomasB
 * @since 31 Jan 2018
 */
public interface IPointTransform2D extends UnaryOperator<IPoint2D>
{
	/**
	 * The function which calculates the x coordinate of the resulting output
	 *
	 * @param p - The input point
	 * @return the x coordinate of the output point
	 */
	default double mapToX(final IPoint2D p)
	{
		return apply(p).x();
	}

	/**
	 * The function which calculates the y coordinate of the resulting output
	 *
	 * @param p - The input point
	 * @return the y coordinate of the output point.
	 */
	default double mapToY(final IPoint2D p)
	{
		return apply(p).y();
	}

	/**
	 * Applies the transformation to a {@link Bounds2D} object.
	 *
	 * @param bounds - The bounds to apply the transform to
	 * @return the transformed bounds as an instance of {@linkplain Bounds2D}
	 */
	default Bounds2D apply(final Bounds2D bounds)
	{
		return bounds.asRectangle().map(this).getBounds();
	}

	/**
	 * The identity mapping. Please use immutable points, the apply method returns
	 * the parameter point not a copy.
	 */
	static IPointTransform2D ID = new IPointTransform2D()
	{
		@Override
		public double mapToX(final IPoint2D p)
		{
			return p.x();
		}

		@Override
		public double mapToY(final IPoint2D p)
		{
			return p.y();
		}

		@Override
		public IPoint2D apply(final IPoint2D p)
		{
			return p;
		}
	};

	static IPointTransform2D repeat(final int nTimes, final IPointTransform2D T)
	{
		return new IPointTransform2D() {

			@Override
			public double mapToY(final IPoint2D p)
			{
				return apply(p).x();
			}

			@Override
			public double mapToX(final IPoint2D p)
			{
				return apply(p).x();
			}

			@Override
			public IPoint2D apply(final IPoint2D t)
			{
				IPoint2D p = t;
				for (int i = 0; i < nTimes; i++) {
					p = T.apply(p);
				}
				return p;
			}
		};
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