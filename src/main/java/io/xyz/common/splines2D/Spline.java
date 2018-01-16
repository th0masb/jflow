/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines2D;

import static io.xyz.common.funcutils.CollectionUtil.allEqual;
import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.intRange;
import static io.xyz.common.funcutils.MapUtil.objRange;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

import io.xyz.common.generators.Generator;
import io.xyz.common.geometry.Curve;
import io.xyz.common.matrix.RPoint;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 */
public class Spline implements ISpline {
	/** The constituent segments of this spline. */
	private final ImmutableList<ISplineSegment> segments;

	/** Cached parameterisation of this spline */
	private final Curve parameterisation;

	public Spline(final List<? extends ISplineSegment> segments)
	{
		this(asDescriptor(segments));
	}

	public Spline(final Generator<? extends ISplineSegment> segments)
	{
		assert len(segments) > 0 && allEqual(intRange(x -> x.dim(), segments));
		this.segments = collect(objRange(x -> (ISplineSegment) x, segments));
		parameterisation = Curve.fuse(objRange(s -> s.parameterise(), this.segments));
	}

	@Override
	public Curve parameterise()
	{
		return parameterisation;
	}

	@Override
	public Set<RPoint> getPointApproximation(final double maximalSpacing)
	{
		throw new RuntimeException();
		//		final Curve c = parameterise();
		//		final double clength = getLengthApproximation();
		//
		//		return null;
	}

	@Override
	public double getLengthApproximation()
	{
		final int steps = len(segments) * Curve.LENGTH_APPROX_STEPS;
		return Curve.length(parameterise(), steps);
	}

	@Override
	public ISpline peturb(final RPoint peturbation)
	{
		return new Spline(objRange(x -> x.peturb(peturbation), segments));
	}

	@Override
	public int dim()
	{
		return segments.get(0).dim();
	}

	@Override
	public List<ISplineSegment> getConstituentSegments()
	{
		return segments;
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