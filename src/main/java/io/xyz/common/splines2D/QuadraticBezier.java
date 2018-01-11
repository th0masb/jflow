/**
 * Copyright � 2017 Lhasa Limited
 * File created: 6 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines2D;


import static io.xyz.common.funcutils.PrimitiveUtil.pow;

import io.xyz.common.function.DoubleCompressor;
import io.xyz.common.fxutils.FXContextBinding;
import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.matrix.RPoint;

/**
 * @author ThomasB
 * @since 6 Nov 2017
 */
public final class QuadraticBezier extends AbstractSplineSegment
{
	public QuadraticBezier(final RPoint start, final RPoint ctrl, final RPoint end)
	{
		super(start, ctrl, end);
	}

	@Override
	public Curve parameterise()
	{
		return t -> {
			assert 0 <= t && t <= 1 : t;
			final DoubleCompressor compressor = xs -> pow(1 - t, 2)*xs.get(0) + 2*t*(1 - t)*xs.get(1) + pow(t, 2)*xs.get(2);
			return RPoint.compress(compressor, getControlPoints());
		};
	}

	@Override
	public RPoint from()
	{
		return getControlPoints().get(0);
	}

	public RPoint control()
	{
		return getControlPoints().get(1);
	}

	@Override
	public RPoint to()
	{
		return getControlPoints().get(2);
	}

	@Override
	public QuadraticBezier peturb(final RPoint peturbation)
	{
		return new QuadraticBezier(from().add(peturbation), control().add(peturbation), to().add(peturbation));
	}

	@Override
	public void trace2D(final FXContextBinding gc, final PointTransform clipTransform, final RPoint perturbation)
	{
		assert correctDimensionsToDraw2D(clipTransform);
		gc.quadraticTo(
				clipTransform.transform(control().add(perturbation)),
				clipTransform.transform(to().add(perturbation)));
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