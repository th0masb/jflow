/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines2D;

import java.util.Set;

import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.geometry.RPoint;
import javafx.scene.canvas.GraphicsContext;

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
public interface ISpline {
	void draw(GraphicsContext gc, PointTransform coordinateMap, RPoint perturbation);

	void draw(final GraphicsContext gc, final PointTransform coordinateMap);

	Curve parameterise();

	Set<RPoint> getPointApproximation(double maximalSpacing);

	double getLengthApproximation();

	ISpline peturb(RPoint peturbation);

	int dim();
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