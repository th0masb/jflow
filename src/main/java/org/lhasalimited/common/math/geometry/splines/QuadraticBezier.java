/**
 * Copyright � 2017 Lhasa Limited
 * File created: 6 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import java.util.function.UnaryOperator;

import org.lhasalimited.common.math.geometry.ICurve;
import org.lhasalimited.common.math.geometry.point.IPointND;

/**
 * @author ThomasB
 * @since 6 Nov 2017
 */
public class QuadraticBezier extends AbstractQuadratic implements ISplineSegment
{
	public QuadraticBezier(final IPointND start, final IPointND ctrl, final IPointND end)
	{
		super(start, ctrl, end);
	}

	@Override
	public ICurve parameterise()
	{
		return ICurve.quadLine(from(), control(), to());
	}

	@Override
	public IPointND control()
	{
		return elementAt(1);
	}

	@Override
	public QuadraticBezier map(final UnaryOperator<IPointND> f)
	{
		return new QuadraticBezier(f.apply(from()), f.apply(control()), f.apply(to()));
	}

	@Override
	public QuadraticBezier peturb(final IPointND peturbation)
	{
		return map(p -> p.add(peturbation));
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