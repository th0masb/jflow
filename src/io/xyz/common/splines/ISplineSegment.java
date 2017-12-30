/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines;

import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.geometry.RPoint;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 *
 * Interface representing a portion of some line in space which is responsible
 * for handling the implementation of drawing itself.
 */
public interface ISplineSegment
{
	/**
	 * During length approximation say that we take {@code APRROX_STEP_RATIO}
	 * steps in the parameterisation per 1 unit of (rough) distance calculated
	 * from knot points.
	 */
	double APRROX_STEP_RATIO = 1;

	void draw(final GraphicsContext gc, final PointTransform coordinateTransform);

	void draw(final GraphicsContext gc, final PointTransform coordinateTransform, RPoint perturbation);

	Curve parameterise();

	double approximateLength();

	ISplineSegment peturb(RPoint peturbation);
	
	int dim();
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