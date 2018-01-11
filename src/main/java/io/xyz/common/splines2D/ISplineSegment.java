/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines2D;

import static io.xyz.common.funcutils.CollectionUtil.head;
import static io.xyz.common.funcutils.CollectionUtil.tail;

import java.util.List;

import io.xyz.common.fxutils.FXContextBinding;
import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.geometry.RealSpaceMember;
import io.xyz.common.matrix.RPoint;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 *
 *        Interface representing a portion of some line in space which is
 *        responsible for handling the implementation of drawing itself.
 */
public interface ISplineSegment extends RealSpaceMember {

	void trace2D(final FXContextBinding gc, final PointTransform clipTTransform, RPoint perturbation);

	default void trace2D(final FXContextBinding gc, final PointTransform clipTransform)
	{
		trace2D(gc, clipTransform, RPoint.origin(2));
	}

	/**
	 * TODO - what dimension is the peturbation? its peturbation before transform
	 * @param gc
	 * @param clipTTransform
	 * @param perturbation
	 */
	default void draw2D(final FXContextBinding gc, final PointTransform clipTransform, final RPoint perturbation)
	{
		assert correctDimensionsToDraw2D(clipTransform);
		gc.beginPath();
		gc.moveTo(clipTransform.transform(from().add(perturbation)));
		trace2D(gc, clipTransform, perturbation);
		gc.stroke();
		gc.closePath();
	}

	default void draw2D(final FXContextBinding gc, final PointTransform clipTransform)
	{
		draw2D(gc, clipTransform, RPoint.origin(2));
	}

	Curve parameterise();

	double length();

	ISplineSegment peturb(RPoint peturbation);

	List<RPoint> getControlPoints();

	@Override
	default int dim()
	{
		return from().dim();
	}

	default RPoint from()
	{
		return head(getControlPoints());
	}

	default RPoint to()
	{
		return tail(getControlPoints());
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