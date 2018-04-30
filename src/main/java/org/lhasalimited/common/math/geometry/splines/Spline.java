/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;


import static org.lhasalimited.common.chainutilities.CollectionUtil.asFunction;
import static org.lhasalimited.common.chainutilities.MapUtil.objMap;
import static org.lhasalimited.common.chainutilities.RangeUtil.doubleRange;
import static org.lhasalimited.common.math.Constants.EPSILON;

import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.geometry.ICurve;
import org.lhasalimited.common.math.geometry.point.IPointND;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 */
public class Spline extends AbstractSpline implements ISpline
{
	public Spline(final Chain<? extends ISplineSegment> segments)
	{
		super(objMap(seg -> (AbstractSplineSegment) seg, segments));
	}

	public Spline(final List<? extends ISplineSegment> segments)
	{
		this(asFunction(segments));
	}

	@Override
	protected ICurve calculateParameterisation()
	{
		return ICurve.fuse(objMap(s -> s.parameterise(), this));
	}

	@Override
	public ISplineSegment elementAt(final int index)
	{
		return (ISplineSegment) segment(index);
	}

	@Override
	public Set<IPointND> getPointApproximation(final double maximalSpacing)
	{
		final double step = maximalSpacing / length();
		return objMap(parameterisation::transform, doubleRange(0, 1 + EPSILON, step)).toSet();
	}

	@Override
	public ISpline peturb(final IPointND peturbation)
	{
		return new Spline(objMap(x -> x.peturb(peturbation), this));
	}

	@Override
	public Spline map(final UnaryOperator<ISplineSegment> f)
	{
		return new Spline(objMap(f, this));
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