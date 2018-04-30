/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import java.util.function.UnaryOperator;

import org.lhasalimited.common.math.geometry.point.IPointND;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 */
public class CubicBezier extends AbstractCubic implements ISplineSegment
{
	public CubicBezier(final IPointND from, final IPointND ctrl1, final IPointND ctrl2, final IPointND to)
	{
		super(from, ctrl1, ctrl2, to);
	}

	@Override
	public CubicBezier peturb(final IPointND peturbation)
	{
		return map(p -> p.add(peturbation));
	}

	@Override
	public CubicBezier map(final UnaryOperator<IPointND> f)
	{
		return new CubicBezier(f.apply(from()), f.apply(control1()), f.apply(control2()), f.apply(to()));
	}
}

/* ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * --------------------------------------------------------------------- */