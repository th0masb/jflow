/**
 * Copyright � 2017 Lhasa Limited
 * File created: 17 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines;

import static io.xyz.common.funcutils.Functional.collect;
import static java.util.Arrays.stream;

import java.util.List;

import io.xyz.common.geometry.Point;
import io.xyz.common.geometry.RPoint;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 17 Nov 2017
 */
public abstract class AbstractSplineSegment implements ISplineSegment
{
//	/**
//	 * Don't modify this instance obviously!
//	 */
//	private static final Point EMPTY = new Point(0, 0);

	/**
	 * The constituent points which form this spline segment. In general
	 * the curve does not travel through each of these points.
	 */
	protected final RPoint[] constituents;

	/**
	 *
	 */
	public AbstractSplineSegment(final RPoint... points)
	{
		constituents = stream(points).map(p -> new RPoint(p)).toArray(RPoint[]::new);
	}

	/**
	 *
	 */
	public AbstractSplineSegment(final List<Point> points)
	{
		constituents = points.stream().map(p -> new Point(p)).toArray(Point[]::new);
	}

	/**
	 *
	 * @param src
	 * @param peturbation
	 */
	protected AbstractSplineSegment(final AbstractSplineSegment src, final Point peturbation)
	{
		this(collect(stream(src.constituents).map(p -> p.translate(peturbation))));
	}

	@Override
	public void draw(final GraphicsContext gc, final PointTransform coordinateTransform)
	{
		draw(gc, coordinateTransform, EMPTY);
	}

	@Override
	public double approximateLength()
	{
		final int roughLen = (int) Math.ceil(getRoughLength());
		final int nSteps = (int) Math.ceil(roughLen*ISplineSegment.APRROX_STEP_RATIO);
		final double step = 1.0/nSteps;
		final Curve p = parameterise();

		//		double sum = 0;
		//		for (double t = 0; t < 1; t +=  step)
		//		{
		//			sum +=
		//		}

		return drange(0, 1, step).map(t -> p.map(t + step).subtract(p.map(t)).magnitude()).sum();
	}

	protected abstract double getRoughLength();
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