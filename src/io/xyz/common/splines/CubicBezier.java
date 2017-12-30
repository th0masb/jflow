/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
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
 * @since 3 Nov 2017
 */
public final class CubicBezier extends AbstractSplineSegment
{
	public CubicBezier(final Point3D start, final Point3D ctrl1, final Point3D ctrl2, final Point3D end)
	{
		super(start, ctrl1, ctrl2, end);
	}
	protected CubicBezier(final CubicBezier src, final Point3D peturbation)
	{
		super(src, peturbation);
	}

	@Override
	public void draw(final GraphicsContext gc, final ILhasaToFXPointMap coordinateTransform, final Point3D perturbation)
	{
		final Point2D[] p = new Point2D[4];
		int i = 0;
		for (final Point3D c : constituents)
		{
			p[i++] = coordinateTransform.map(c.translate(perturbation));
		}
		gc.beginPath();
		gc.moveTo(p[0].getX(), p[0].getY());
		gc.bezierCurveTo(p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY(), p[3].getX(), p[3].getY());
		gc.stroke();
		gc.closePath();
	}

	@Override
	public Curve parameterise()
	{
		final Point3D u0 = constituents[0], u1 = constituents[1], u2 = constituents[2], u3 = constituents[3];
		return t -> new Point3D(
				Math.pow(1 - t, 3)*u0.getX() + 3*t*Math.pow(1 - t, 2)*u1.getX() + 3*Math.pow(t, 2)*(1 - t)*u2.getX() + Math.pow(t, 3)*u3.getX(),
				Math.pow(1 - t, 3)*u0.getY() + 3*t*Math.pow(1 - t, 2)*u1.getY() + 3*Math.pow(t, 2)*(1 - t)*u2.getY() + Math.pow(t, 3)*u3.getY(),
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
		for (int i = 0; i < 3; i++)
		{
			rough += Point3D.getDistance2D(constituents[i], constituents[i+1]);
		}
		return rough;
	}

	@Override
	public ISplineSegment peturb(final Point3D peturbation)
	{
		return new CubicBezier(this, peturbation);
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