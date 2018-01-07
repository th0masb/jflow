/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines2D;

import static io.xyz.common.funcutils.CollectionUtil.asList;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.objRange;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.List;
import java.util.Set;

import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.matrix.impl.RPointImpl;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 */
public class Spline implements ISpline {
	/** The constituent segments of this spline. */
	private final List<ISplineSegment> segments;

	/** Cached parameterisation of this spline */
	private final Curve parameterisation;

	public Spline(final List<ISplineSegment> segments)
	{
		assert len(segments) > 0;
		this.segments = asList(segments);
		parameterisation = Curve.fuse(collect(objRange(s -> s.parameterise(), segments)));
	}

	@Override
	public void draw(final GraphicsContext gc, final PointTransform clipT, final RPointImpl perturbation)
	{
		for (final ISplineSegment segment : segments) {
			segment.draw(gc, clipT, perturbation);
		}
	}

	@Override
	public void draw(final GraphicsContext gc, final PointTransform coordinateMap)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Curve parameterise()
	{
		return parameterisation;
	}

	@Override
	public Set<RPointImpl> getPointApproximation(final double maximalSpacing)
	{
		final Curve c = parameterise();
		final double clength = getLengthApproximation();

		return null;
	}

	@Override
	public double getLengthApproximation()
	{
		final int steps = len(segments) * Curve.LENGTH_APPROX_STEPS;
		return Curve.length(parameterise(), steps);
	}

	@Override
	public ISpline peturb(final RPointImpl peturbation)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int dim()
	{
		return segments.get(0).dim();
	}

}

/*
 * ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * ---------------------------------------------------------------------
 */