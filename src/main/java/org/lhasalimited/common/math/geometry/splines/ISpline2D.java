/**
 * Copyright © 2018 Lhasa Limited
 * File created: 19 Feb 2018 by thomasb
 * Creator : thomasb
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import static org.lhasalimited.common.chainutilities.RangeUtil.range;

import java.util.Set;
import java.util.function.UnaryOperator;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.geometry.ICurve2D;
import org.lhasalimited.common.math.geometry.canvasbinding.FXContextBinding;
import org.lhasalimited.common.math.geometry.canvasbinding.IPathTraceable2D;
import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.point.IPointTransform2D;

/**
 * @author thomasb
 * @since 19 Feb 2018
 */
public interface ISpline2D extends IPathTraceable2D, Chain<ISplineSegment2D>
{
	ICurve2D parameterise();

	double length();

	Set<IPoint2D> getPointApproximation(double maximalSpacing);

	@Override
	ISpline2D map(UnaryOperator<ISplineSegment2D> f);

	default ISpline2D pointMap(final UnaryOperator<IPoint2D> f)
	{
		return map(seg -> seg.map(f));
	}

	default ISpline2D peturb(final IPoint2D peturbation)
	{
		return map(seg -> seg.peturb(peturbation));
	}

	@Override
	default IPoint2D getPathStart()
	{
		return elementAt(0).getPathStart();
	}

	@Override
	default void transformThenTrace(final FXContextBinding gc, final IPointTransform2D T)
	{
		range(linkCount()).stream().forEach(i -> elementAt(i).transformThenTrace(gc, T));
	}
//
//	default List<IPoint2D> getBoundingPoints()
//	{
//		return join(this);
//	}

	default Spline2DTO toSerializableDTO()
	{
		return new Spline2DTO(this);
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