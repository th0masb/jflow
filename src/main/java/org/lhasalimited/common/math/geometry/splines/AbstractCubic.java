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

import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 20 Feb 2018
 */
public abstract class AbstractCubic extends AbstractSplineSegment
{
	public AbstractCubic(final IPointND from, final IPointND ctrl1, final IPointND ctrl2, final IPointND to)
	{
		super(from, ctrl1, ctrl2, to);
	}

	public IPointND control1()
	{
		return elementAt(1);
	}

	public IPointND control2()
	{
		return elementAt(2);
	}

	public SegmentType getType()
	{
		return SegmentType.CUBIC;
	}

	@Override
	public ICurve parameterise()
	{
		return ICurve.cubicLine(from(), control1(), control2(), to());
	}

	public void projectThenTrace(final FXContextBinding gc, final IProjection2D T)
	{
		final GraphicsContext boundGC = gc.getBoundContext();
		final IPointND c1 = control1(), c2 = control2(), x = to();
		boundGC.bezierCurveTo(
				T.projectToX(c1), T.projectToY(c1),
				T.projectToX(c2), T.projectToY(c2),
				T.projectToX(x), T.projectToY(x));
	}

	@Override
	public double length()
	{
		return ICurve.length(parameterise());
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