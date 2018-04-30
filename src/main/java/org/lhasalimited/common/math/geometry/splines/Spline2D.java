/**
 * Copyright © 2018 Lhasa Limited
 * File created: 20 Feb 2018 by ThomasB
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
import org.lhasalimited.common.math.geometry.ICurve2D;
import org.lhasalimited.common.math.geometry.point.IPoint2D;

/**
 * @author ThomasB
 * @since 20 Feb 2018
 */
public class Spline2D extends AbstractSpline implements ISpline2D
{
	public Spline2D(final Chain<? extends ISplineSegment2D> segments)
	{
		super(objMap(seg -> (AbstractSplineSegment) seg, segments));
	}

	public Spline2D(final List<? extends ISplineSegment2D> segments)
	{
		this(asFunction(segments));
	}

	@Override
	protected ICurve2D calculateParameterisation()
	{
		return ICurve2D.fuse(objMap(s -> s.parameterise(), this));
	}

	@Override
	public ISplineSegment2D elementAt(final int index)
	{
		return (ISplineSegment2D) super.segment(index);
	}

	@Override
	public ICurve2D parameterise()
	{
		return (ICurve2D) super.parameterise();
	}

	@Override
	public Set<IPoint2D> getPointApproximation(final double maximalSpacing)
	{
		final double step = maximalSpacing / length();
		return objMap(parameterise()::transform, doubleRange(0, 1 + EPSILON, step)).toSet();
	}

	@Override
	public ISpline2D peturb(final IPoint2D peturbation)
	{
		return map(seg -> seg.peturb(peturbation));
	}

	@Override
	public Spline2D map(final UnaryOperator<ISplineSegment2D> f)
	{
		return new Spline2D(objMap(f, this));
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