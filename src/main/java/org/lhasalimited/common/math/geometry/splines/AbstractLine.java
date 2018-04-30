/**
 * Copyright © 2018 Lhasa Limited
 * File created: 20 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import org.lhasalimited.common.math.geometry.ICurve;
import org.lhasalimited.common.math.geometry.canvasbinding.FXContextBinding;
import org.lhasalimited.common.math.geometry.point.IPointND;
import org.lhasalimited.common.math.geometry.point.IProjection2D;

/**
 * @author ThomasB
 * @since 20 Feb 2018
 */
public abstract class AbstractLine extends AbstractSplineSegment
{
	public AbstractLine(final IPointND from, final IPointND to)
	{
		super(from, to);
	}

	@Override
	public double length()
	{
		return to().distL2(from());
	}

	@Override
	public ICurve parameterise()
	{
		return ICurve.straightLine(from(), to());
	}

	public IPointND direction()
	{
		return to().subtract(from());
	}

	/**
	 * @param distance
	 * @return the Point lying on this line which is a distance given by the parameter away from the
	 *         start point of this line. If the distance is positive we move in the direction of the
	 *         endpoint, otherwise we go the opposite way.
	 */
	public IPointND travel(final double distance)
	{
		final IPointND travelVector = direction().normalise().scale(distance);
		return from().add(travelVector);
	}

	public boolean isParallelTo(final AbstractLine other)
	{
		final IPointND thisDir = direction().normalise(), otherDir = other.direction().normalise();
		return thisDir.equals(otherDir) || thisDir.equals(otherDir.scale(-1));
	}

	public void projectThenTrace(final FXContextBinding gc, final IProjection2D T)
	{
		gc.getBoundContext().lineTo(T.projectToX(to()), T.projectToY(to()));
	}

	public SegmentType getType()
	{
		return SegmentType.STRAIGHT;
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