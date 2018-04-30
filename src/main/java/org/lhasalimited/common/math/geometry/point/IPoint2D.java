/**
 * Copyright © 2018 Lhasa Limited
 * File created: 31 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.point;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lhasalimited.common.chainutilities.CombineUtil.dotProduct;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.sqrt;

import java.util.function.DoubleUnaryOperator;

import org.lhasalimited.common.math.geometry.point.impl.Point2DImpl;

/**
 * A 2D point
 *
 *
 *
 * @author ThomasB
 * @since 31 Jan 2018
 */
public interface IPoint2D extends IPointND//, IPointTransform2D
{
	@Override
	IPoint2D apply(DoubleUnaryOperator f);

	IPoint2D add(double dx, double dy);

	default IPoint2D addPolar(final double r, final double theta)
	{
		return add(r*cos(theta), r*sin(theta));
	}

	default IPoint2D add(final IPoint2D other)
	{
		return add(other.x(), other.y());
	}

//	default IPoint2D operateL(final BinaryOperator<IPoint2D> f, final IPoint2D other)
//	{
//		return f.apply(this, other);
//	}

	@Override
	default IPoint2D scale(final double scaleFactor)
	{
		return apply(x -> x * scaleFactor);
	}

	/**
	 * Could override for performance
	 *
	 * @param other
	 * @return
	 */
	default IPoint2D subtract(final IPoint2D other)
	{
		return add(other.scale(-1));
	}

	default double dot(final IPoint2D other)
	{
		return dotProduct(this, other)/(magnitude()*other.magnitude());
	}

	default double distL1(final double otherX, final double otherY)
	{
		return (x() - otherX) + (y() - otherY);
	}

	default double distL1(final IPoint2D other)
	{
		return distL1(other.x(), other.y());
	}

	default double distL2(final double otherX, final double otherY)
	{
		final double dx = x() - otherX, dy = y() - otherY;
		return sqrt(dx*dx + dy*dy);
	}

	default double distL2(final IPoint2D other)
	{
		return distL2(other.x(), other.y());
	}

	/**
	 * Denote this instance by P and the origin by O.
	 *
	 * @return a point with unit (L2) distance from the origin lying on the line
	 *         with direction OP if P is not the origin, otherwise return O
	 */
	@Override
	default IPoint2D normalise()
	{
		if (isOrigin()) {
			return this;
		}
		return scale(1 / magnitude());
	}

	@Override
	default IPoint2D map(final DoubleUnaryOperator f)
	{
		return apply(f);
	}

	static IPoint2D of(final double x, final double y)
	{
		return new Point2DImpl(x, y);
	}

	static IPoint2D origin()
	{
		return Point2DImpl.ORIGIN;
	}

//	static IPoint2D immutableOf(final DoubleGenerator ds)
//	{
//		throw new RuntimeException();
////		return new RPointImpl(ds);
//	}
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