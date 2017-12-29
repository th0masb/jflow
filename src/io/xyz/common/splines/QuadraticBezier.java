/**
 * Copyright © 2017 Lhasa Limited
 * File created: 6 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.graph.splinecurves;

import org.lhasalimited.common.math.ICurveParameterisation;
import org.lhasalimited.common.math.ILhasaToFXPointMap;
import org.lhasalimited.common.math.Point3D;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 6 Nov 2017
 */
public final class QuadraticBezier extends AbstractSplineSegment
{
	public QuadraticBezier(final Point3D start, final Point3D ctrl, final Point3D end)
	{
		super(start, ctrl, end);
	}

	protected QuadraticBezier(final QuadraticBezier src, final Point3D peturbation)
	{
		super(src, peturbation);
	}

	@Override
	public void draw(final GraphicsContext gc, final ILhasaToFXPointMap coordinateTransform, final Point3D perturbation)
	{
		final Point2D[] p = new Point2D[3];
		int i = 0;
		for (final Point3D c : constituents)
		{
			p[i++] = coordinateTransform.map(c.translate(perturbation));
		}
		gc.beginPath();
		gc.moveTo(p[0].getX(), p[0].getY());
		gc.quadraticCurveTo(p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
		gc.stroke();
		gc.closePath();
	}

	@Override
	public ICurveParameterisation parameterise()
	{
		final Point3D u0 = constituents[0], u1 = constituents[1], u2 = constituents[2];
		return t -> new Point3D(
				Math.pow(1 - t, 2)*u0.getX() + 2*t*(1 - t)*u1.getX() + Math.pow(t, 2)*u2.getX(),
				Math.pow(1 - t, 2)*u0.getY() + 2*t*(1 - t)*u1.getY() + Math.pow(t, 2)*u2.getY(),
				0);
	}

	/**
	 * Very rough length here, in general we will (always?) overestimate
	 * @return
	 */
	@Override
	protected double getRoughLength()
	{
		double rough = 0;
		for (int i = 0; i < 2; i++)
		{
			rough += Point3D.getDistance2D(constituents[i], constituents[i+1]);
		}
		return rough;
	}

	@Override
	public ISplineSegment peturb(final Point3D peturbation)
	{
		return new QuadraticBezier(this, peturbation);
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