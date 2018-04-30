/**
 * Copyright © 2018 Lhasa Limited
 * File created: 20 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import static org.lhasalimited.common.chainutilities.PrimitiveUtil.isZero;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.max;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.min;

import java.util.Optional;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

import org.lhasalimited.common.math.geometry.ICurve2D;
import org.lhasalimited.common.math.geometry.canvasbinding.FXContextBinding;
import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.point.IPointTransform2D;

/**
 * @author ThomasB
 * @since 20 Feb 2018
 */
public class Line2D extends AbstractLine implements ISplineSegment2D
{
	public Line2D(final IPoint2D from, final IPoint2D to)
	{
		super(from, to);
	}

	public static Line2D between(final IPoint2D start, final IPoint2D end)
	{
		return new Line2D(start, end);
	}

	public static Line2D between(final double startx, final double starty, final double endx, final double endy)
	{
		return between(IPoint2D.of(startx, starty), IPoint2D.of(endx, endy));
	}

	@Override
	public IPoint2D elementAt(final int index)
	{
		return (IPoint2D) super.elementAt(index);
	}

	@Override
	public IPoint2D from()
	{
		return (IPoint2D) super.from();
	}

	@Override
	public IPoint2D to()
	{
		return (IPoint2D) super.to();
	}

	@Override
	public ICurve2D parameterise()
	{
		return ICurve2D.straightLine(from(), to());
	}

	@Override
	public void transformThenTrace(final FXContextBinding gc, final IPointTransform2D T)
	{
		gc.getBoundContext().lineTo(T.mapToX(to()), T.mapToY(to()));
	}

	@Override
	public Line2D peturb(final IPoint2D peturbation)
	{
		return map(p -> p.add(peturbation));
	}

	@Override
	public Line2D map(final UnaryOperator<IPoint2D> f)
	{
		return Line2D.between(f.apply(from()), f.apply(to()));
	}

	public Line2D peturbToNewCentre(final IPoint2D newCentre)
	{
		return peturb(newCentre.subtract(midPoint()));
	}

	@Override
	public IPoint2D direction()
	{
		return to().subtract(from());
	}

	@Override
	public IPoint2D travel(final double distance)
	{
		final IPoint2D travelVector = direction().normalise().scale(distance);
		return from().add(travelVector);
	}

	public IPoint2D midPoint()
	{
		return IPoint2D.of((from().x() + to().x())/2, (from().y() + to().y())/2);
	}

	public boolean isVertical()
	{
		return isZero(to().x() - from().x());
	}

	public boolean isHorizontal()
	{
		return isZero(to().y() - from().y());
	}

	public double grad2D()
	{
		return (to().y() - from().y()) / (to().x() - from().x());
	}

	public double invGrad2D()
	{
		return (to().x() - from().x()) / (to().y() - from().y());
	}

	public Optional<DoubleUnaryOperator> asFunction()
	{
		if (isVertical()) {
			return Optional.empty();
		}
		final double m = grad2D();
		final double intercept = from().y() - m * from().x();
		return Optional.of(x -> m * x + intercept);
	}

	public Optional<DoubleUnaryOperator> asInverseFunction()
	{
		if (isHorizontal()) {
			return Optional.empty();
		}
		final double m = invGrad2D();
		final double intercept = from().x() - m * from().y();
		return Optional.of(y -> m * y + intercept);
	}

	/**
	 *
	 * Given this line L1 and another line L2...
	 *
	 * @param other
	 *        - the other line
	 * @return
	 */
	public double pathAngleWith(final Line2D other)
	{
		assert this.to().equals(other.from()) : "Lines do not form a path";

		/* First normalise the path to the unit circle, centred at the origin
		 * whilst preserving the angles */
		final IPoint2D thisNormed = from().subtract(to()).normalise(), otherNormed = other.direction().normalise();
		if (thisNormed.equals(otherNormed)) {
			return Math.PI;
		}
		else if (thisNormed.equals(otherNormed.scale(-1))) {
			return 0;
		}
		final IPoint2D p1 = IPoint2D.origin(), p2 = thisNormed, p3 = otherNormed;
		return -getSign(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y()) * (Math.PI - Math.acos(p2.dot(p3)));
	}

	/**
	 *
	 * @param p
	 * @return
	 */
	public IPoint2D perpendicularIntersection(final IPoint2D p)
	{
		if (isVertical()) {
			return IPoint2D.of(from().x(), p.y());
		}
		else if (isHorizontal()) {
			return IPoint2D.of(p.x(), from().y());
		}

		final double thisGrad = grad2D(), perpGrad = -1 / thisGrad;
		final double thisIntercept = from().y() - thisGrad * from().x(), perpIntercept = p.y() - perpGrad * p.x();
		return crossingPoint2D(thisGrad, thisIntercept, perpGrad, perpIntercept);
	}

	public IPoint2D closestPointTo(final IPoint2D p)
	{
		final IPoint2D perpX = perpendicularIntersection(p);
		if (withinLineBounds(perpX)) {
			return perpX;
		}
		return p.distL2(to()) < p.distL2(from())? to() : from();
	}

	public boolean withinLineBounds(final IPoint2D p)
	{
		final double minx = min(to().x(), from().x()), maxx = max(to().x(), from().x());
		final double miny = min(to().y(), from().y()), maxy = max(to().y(), from().y());
		return minx <= p.x() && p.x() <= maxx && miny <= p.y() && p.y() <= maxy;
	}

	public static Optional<IPoint2D> crossingPoint(final Line2D a, final Line2D b)
	{
		if (a.isParallelTo(b)) {
			return Optional.empty();
		}
		/* Assume a.from() is the origin then shift result at the end.
		 *
		 * We represent both lines in the form y = mx + c and solve simultaneously */

		final IPoint2D afrom = a.from(), bfrom = b.from();
		if (a.isVertical()) {
			return Optional.of(IPoint2D.of(afrom.x(), b.asFunction().get().applyAsDouble(afrom.x())));
		}
		else if (b.isVertical()) {
			return Optional.of(IPoint2D.of(bfrom.x(), a.asFunction().get().applyAsDouble(bfrom.x())));
		}
		else {
			final double am = a.grad2D(), bm = b.grad2D();
			final double aintercept = afrom.y() - am * afrom.x(), bintercept = bfrom.y() - bm * bfrom.x();
			return Optional.of(crossingPoint2D(am, aintercept, bm, bintercept));
		}
	}

	private static IPoint2D crossingPoint2D(final double agrad, final double aintercept, final double bgrad, final double bintercept)
	{
		final double resultx = (bintercept - aintercept) / (agrad - bgrad);
		return IPoint2D.of(resultx, agrad * resultx + aintercept);
	}

	/**
	 * Returns 0, 1 or -1 according to whether the three specified points, <i>p1</i> = (<i>x1</i>,
	 * <i>y1</i>), <i>p2</i> = (<i>x2</i>, <i>y2</i>) and <i>p3</i> = (<i>x3</i>, <i>y3</i>), are collinear
	 * or the angle of rotation from <i>p2</i> to <i>p3</i> about <i>p1</i> is positive or negative.
	 *
	 * @param x1 the <i>x</i> coordinate of point <i>p1</i>.
	 * @param y1 the <i>y</i> coordinate of point <i>p1</i>.
	 * @param x2 the <i>x</i> coordinate of point <i>p2</i>.
	 * @param y2 the <i>y</i> coordinate of point <i>p2</i>.
	 * @param x3 the <i>x</i> coordinate of point <i>p3</i>.
	 * @param y3 the <i>y</i> coordinate of point <i>p3</i>.
	 * @return
	 *         <ul>
	 *         <li>0 if the three points are collinear, or</li>
	 *         <li>1 if the angle of rotation from ({@code x2}, {@code y2}) to ({@code x3}, {@code y3})
	 *         about ({@code x1}, {@code y1}) is positive, or</li>
	 *         <li>-1 if the angle of rotation from ({@code x2}, {@code y2}) to ({@code x3}, {@code y3})
	 *         about ({@code x1}, {@code y1}) is negative.</li>
	 *         </ul>
	 */
	public static int getSign(final double x1, final double y1, final double x2, final double y2, final double x3, final double y3)
	{
		final double crossProduct = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
		return (crossProduct < 0.0)
				? -1
				: (crossProduct > 0.0)
						? 1
						: 0;
	}

	public static void main(final String[] args)
	{
		final Line2D line = Line2D.between(IPoint2D.of(1, 22), IPoint2D.of(2,  6));
		System.out.println(line.direction());
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