/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;


import static org.lhasalimited.common.chainutilities.RangeUtil.range;

import java.util.Set;
import java.util.function.UnaryOperator;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.geometry.ICurve;
import org.lhasalimited.common.math.geometry.canvasbinding.FXContextBinding;
import org.lhasalimited.common.math.geometry.canvasbinding.IPathTraceable;
import org.lhasalimited.common.math.geometry.point.IPointND;
import org.lhasalimited.common.math.geometry.point.IProjection2D;


/**
 * @author ThomasB
 * @since 3 Nov 2017
 *
 */
public interface ISpline extends IPathTraceable, Chain<ISplineSegment>
{
	ICurve parameterise();

	double length();

	Set<IPointND> getPointApproximation(double maximalSpacing);

	@Override
	ISpline map(UnaryOperator<ISplineSegment> f);

	default ISpline peturb(final IPointND peturbation)
	{
		return map(seg -> seg.peturb(peturbation));
	}

	@Override
	default IPointND getPathStart()
	{
		return elementAt(0).getPathStart();
	}

	@Override
	default void projectThenTrace(final FXContextBinding gc, final IProjection2D T)
	{
		range(linkCount()).stream().forEach(i -> elementAt(i).projectThenTrace(gc, T));
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