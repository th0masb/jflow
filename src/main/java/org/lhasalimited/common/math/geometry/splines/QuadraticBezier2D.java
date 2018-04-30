/**
 * Copyright © 2018 Lhasa Limited
 * File created: 19 Feb 2018 by thomasb
 * Creator : thomasb
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import java.util.function.UnaryOperator;

import org.lhasalimited.common.math.geometry.ICurve2D;
import org.lhasalimited.common.math.geometry.canvasbinding.FXContextBinding;
import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.point.IPointTransform2D;

/**
 * @author thomasb
 * @since 19 Feb 2018
 */
public class QuadraticBezier2D extends AbstractQuadratic implements ISplineSegment2D
{
	public QuadraticBezier2D(final IPoint2D start, final IPoint2D ctrl, final IPoint2D end)
	{
		super(start, ctrl, end);
	}

	@Override
	public IPoint2D elementAt(final int index)
	{
		return (IPoint2D) super.elementAt(index);
	}

	@Override
	public IPoint2D from()
	{
		return (IPoint2D) super.from();
	}

	@Override
	public IPoint2D control()
	{
		return (IPoint2D) super.control();
	}

	@Override
	public IPoint2D to()
	{
		return (IPoint2D) super.to();
	}

	@Override
	public ICurve2D parameterise()
	{
		return ICurve2D.quadLine(from(), control(), to());
	}

	public IPoint2D approximateCurvePeak()
	{
		final int nIterations = 10;
		final ICurve2D c = parameterise();
		double leftT = 0, rightT = 1;
		double leftDist = c.transform(leftT).distL2(control()), rightDist = c.transform(rightT).distL2(control());
		for (int i = 0; i < nIterations; i++) {
			final double centreT = (leftT + rightT)/2, centreDist =  c.transform(centreT).distL2(control());
			final boolean closerLeft = leftDist <= rightDist;
			leftT = closerLeft? leftT : centreT;
			leftDist = closerLeft? leftDist : centreDist;
			rightT = closerLeft? centreT : rightT;
			rightDist = closerLeft? centreDist : rightDist;
		}
		return c.transform(leftDist < rightDist? leftT : rightT);
	}

	@Override
	public void transformThenTrace(final FXContextBinding gc, final IPointTransform2D T)
	{
		final IPoint2D c = control(), x = to();
		gc.getBoundContext().quadraticCurveTo(T.mapToX(c), T.mapToY(c), T.mapToX(x), T.mapToY(x));
	}

	@Override
	public QuadraticBezier2D peturb(final IPoint2D peturbation)
	{
		return map(p -> p.add(peturbation));
	}

	@Override
	public QuadraticBezier2D map(final UnaryOperator<IPoint2D> f)
	{
		return new QuadraticBezier2D(f.apply(from()), f.apply(control()), f.apply(to()));
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