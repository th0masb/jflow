package io.xyz.common.splines;

import org.lhasalimited.common.math.ICurveParameterisation;
import org.lhasalimited.common.math.ILhasaToFXPointMap;
import org.lhasalimited.common.math.Point3D;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 */
public class StraightSegment extends AbstractSplineSegment
{
	public StraightSegment(final Point3D start, final Point3D end)
	{
		super(start, end);
	}

	protected StraightSegment(final StraightSegment src, final Point3D peturbation)
	{
		super(src, peturbation);
	}

	@Override
	public void draw(final GraphicsContext gc, final ILhasaToFXPointMap coordinateTransform, final Point3D perturbation)
	{
		final Point2D start = coordinateTransform.map(getStart().translate(perturbation));
		final Point2D end = coordinateTransform.map(getEnd().translate(perturbation));
		gc.beginPath();
		gc.moveTo(start.getX(), start.getY());
		gc.lineTo(end.getX(), end.getY());
		gc.stroke();
		gc.closePath();
	}

	@Override
	public Curve parameterise()
	{
		return t -> new Point3D((1 - t)*getStart().getX() + t*getEnd().getX(), (1 - t)*getStart().getY() + t*getEnd().getY(), 0);
	}

	@Override
	public double approximateLength()
	{
		return Point3D.getDistance2D(getStart(), getEnd());
	}

	@Override
	public ISplineSegment peturb(final Point3D peturbation)
	{
		return new StraightSegment(this, peturbation);
	}

	@Override
	protected double getRoughLength()
	{
		throw new RuntimeException("Not needed here.");
	}

	/**
	 * Scale about the midpoint of the line. If source length was x then
	 * new length will be {@code scaleFactor}*x.
	 *
	 * @param scaleFactor
	 * @return
	 */
	public StraightSegment scale(final double scaleFactor)
	{
		return scale(getStart(), getEnd(), scaleFactor);
	}

	public Point3D getStart()
	{
		return constituents[0];
	}

	public Point3D getEnd()
	{
		return constituents[1];
	}

	public static StraightSegment scale(final Point3D start, final Point3D end, final double scaleFactor)
	{
		final Point3D mid = Point3D.getMidPoint(start, end);
		final Point3D dpE = end.subtract(mid), dpS = start.subtract(mid);

		final Point3D newEnd = mid.translate(dpE.scale(scaleFactor));
		final Point3D newStart = mid.translate(dpS.scale(scaleFactor));
		return new StraightSegment(newStart, newEnd);
	}

	public static void main(final String[] args)
	{
		final StraightSegment a = new StraightSegment(new Point3D(0, 0, 0), new Point3D(1, 0, 0));
		final StraightSegment b = a.scale(0.5);
		System.out.println(b.getStart());
		System.out.println(b.getEnd());
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