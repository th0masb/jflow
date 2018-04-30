/**
 * Copyright © 2018 Lhasa Limited
 * File created: 19 Feb 2018 by thomasb
 * Creator : thomasb
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import java.util.function.UnaryOperator;

import org.lhasalimited.common.math.geometry.ICurve;
import org.lhasalimited.common.math.geometry.ICurve2D;
import org.lhasalimited.common.math.geometry.canvasbinding.FXContextBinding;
import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.point.IPointTransform2D;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author thomasb
 * @since 19 Feb 2018
 */
public class CubicBezier2D extends AbstractCubic implements ISplineSegment2D
{
	public CubicBezier2D(final IPoint2D from, final IPoint2D ctrl1, final IPoint2D ctrl2, final IPoint2D to)
	{
		super(from, ctrl1, ctrl2, to);
	}

	@Override
	public IPoint2D elementAt(final int index)
	{
		return (IPoint2D) super.elementAt(index);
	}

	@Override
	public ICurve2D parameterise()
	{
		return ICurve2D.cubicLine(from(), control1(), control2(), to());
	}

	@Override
	public void transformThenTrace(final FXContextBinding gc, final IPointTransform2D T)
	{
		final GraphicsContext boundGC = gc.getBoundContext();
		final IPoint2D c1 = control1(), c2 = control2(), x = to();
		boundGC.bezierCurveTo(
				T.mapToX(c1), T.mapToY(c1),
				T.mapToX(c2), T.mapToY(c2),
				T.mapToX(x), T.mapToY(x)
				);
	}

	@Override
	public IPoint2D from()
	{
		return (IPoint2D) super.from();
	}

	@Override
	public IPoint2D control1()
	{
		return (IPoint2D) super.control1();
	}

	@Override
	public IPoint2D control2()
	{
		return (IPoint2D) super.control2();
	}

	@Override
	public IPoint2D to()
	{
		return (IPoint2D) super.to();
	}

	@Override
	public double length()
	{
		return ICurve.length(parameterise());
	}

	@Override
	public CubicBezier2D map(final UnaryOperator<IPoint2D> f)
	{
		return new CubicBezier2D(f.apply(from()), f.apply(control1()), f.apply(control2()), f.apply(to()));
	}

	@Override
	public CubicBezier2D peturb(final IPoint2D peturbation)
	{
		return map(p -> p.add(peturbation));
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