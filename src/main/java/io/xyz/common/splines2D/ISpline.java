/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines2D;

import static io.xyz.common.funcutils.CollectionUtil.forEach;
import static io.xyz.common.funcutils.CollectionUtil.head;

import java.util.List;
import java.util.Set;

import io.xyz.common.fxutils.FXContextBinding;
import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.geometry.RealSpaceMember;
import io.xyz.common.matrix.RPoint;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 *
 *        TODO - Maybe splines should be set onto edges? <--- Yes I think this
 *        is how it should be.
 *
 *        A spline wraps an instance of {@link IPolyEdge} providing drawing and
 *        parameterisation mechanisms
 */
public interface ISpline extends RealSpaceMember
{
	default void draw2D(final FXContextBinding gc, final PointTransform coordinateMap, final RPoint perturbation)
	{
		assert correctDimensionsToDraw2D(coordinateMap);
		gc.beginPath();
		gc.moveTo(coordinateMap.transform(head(getConstituentSegments()).from().add(perturbation)));
		trace2D(gc, coordinateMap, perturbation);
		gc.stroke();
		gc.closePath();
	}

	default void draw2D(final FXContextBinding gc, final PointTransform coordinateMap)
	{
		draw2D(gc, coordinateMap, RPoint.origin(2));
	}

	default void trace2D(final FXContextBinding gc, final PointTransform coordinateMap, final RPoint perturbation)
	{
		assert correctDimensionsToDraw2D(coordinateMap);
		forEach(s -> s.trace2D(gc, coordinateMap, perturbation), getConstituentSegments());
	}

	default void trace(final FXContextBinding gc, final PointTransform coordinateMap)
	{
		trace2D(gc, coordinateMap, RPoint.origin(2));
	}

	List<ISplineSegment> getConstituentSegments();

	Curve parameterise();

	Set<RPoint> getPointApproximation(double maximalSpacing);

	double getLengthApproximation();

	ISpline peturb(RPoint peturbation);

	//	int dim();
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