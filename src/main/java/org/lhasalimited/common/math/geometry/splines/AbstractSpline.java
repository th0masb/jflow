/**
 * Copyright © 2018 Lhasa Limited
 * File created: 20 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.MapUtil.intMap;
import static org.lhasalimited.common.chainutilities.MapUtil.objMap;
import static org.lhasalimited.common.chainutilities.PredicateUtil.allEqual;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.geometry.ICurve;

import com.google.common.collect.ImmutableList;

/**
 * @author ThomasB
 * @since 20 Feb 2018
 */
public abstract class AbstractSpline
{
	/** The constituent segments of this spline. */
	protected final ImmutableList<AbstractSplineSegment> segments;

	/** Cached parameterisation of this spline */
	protected final ICurve parameterisation;

	public AbstractSpline(final Chain<? extends AbstractSplineSegment> segments)
	{
		assert len(segments) > 0 && allEqual(intMap(x -> x.dim(), segments));
		this.segments = objMap(x -> (AbstractSplineSegment) x, segments).toList();
		parameterisation = calculateParameterisation();
	}

	public double length()
	{
		final int steps = len(segments) * ICurve.LENGTH_APPROX_STEPS;
		return ICurve.length(parameterise(), steps);
	}

	public AbstractSplineSegment segment(final int index)
	{
		return segments.get(index);
	}

	public int dim()
	{
		return segments.get(0).dim();
	}

	public int linkCount()
	{
		return len(segments);
	}

	public ICurve parameterise()
	{
		return parameterisation;
	}

	protected abstract ICurve calculateParameterisation();
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