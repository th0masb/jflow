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
public abstract class AbstractQuadratic extends AbstractSplineSegment
{
	public AbstractQuadratic(final IPointND from, final IPointND ctrl, final IPointND to)
	{
		super(from, ctrl, to);
	}

	public void projectThenTrace(final FXContextBinding gc, final IProjection2D T)
	{
		final IPointND c = control(), x = to();
		gc.getBoundContext().quadraticCurveTo(
				T.projectToX(c),
				T.projectToY(c),
				T.projectToX(x),
				T.projectToY(x));
	}

	public IPointND control()
	{
		return elementAt(1);
	}

	@Override
	public ICurve parameterise()
	{
		return ICurve.quadLine(from(), control(), to());
	}

	public SegmentType getType()
	{
		return SegmentType.QUADRATIC;
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