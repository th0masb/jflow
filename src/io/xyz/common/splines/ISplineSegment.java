/**
 * Copyright © 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.graph.splinecurves;

import org.lhasalimited.common.math.ICurveParameterisation;
import org.lhasalimited.common.math.ILhasaToFXPointMap;
import org.lhasalimited.common.math.Point3D;

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

	void draw(final GraphicsContext gc, final ILhasaToFXPointMap coordinateTransform);

	void draw(final GraphicsContext gc, final ILhasaToFXPointMap coordinateTransform, Point3D perturbation);

	ICurveParameterisation parameterise();

	double approximateLength();

	ISplineSegment peturb(Point3D peturbation);
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