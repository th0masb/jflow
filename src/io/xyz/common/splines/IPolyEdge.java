/**
 * Copyright © 2017 Lhasa Limited
 * File created: 15 May 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.graph.splinecurves;

import java.util.List;

import org.lhasalimited.common.graph.IEdge;
import org.lhasalimited.common.math.Point3D;

/**
 * This interface represents the type of graph edges which can be laid out using the
 * Sugiyama framework. They will not necessarily be laid out as a single straight line
 * so it needs to have some references to bend points in the edge.
 *
 * @author ThomasB
 * @since 15 May 2017
 */
public interface IPolyEdge extends IEdge
{
	/**
	 *  Returns the set of control points associated with this edge. I.e. those
	 *  points at which a bend occurs.
	 */
	EdgeLayoutPoints getLayoutPoints();

	/**
	 * Manually set the control points.
	 * @param layoutPoints
	 */
	void setLayoutPoints(EdgeLayoutPoints layoutPoints);


	default void setLayoutPoints(final List<Point3D> knots)
	{
		setLayoutPoints(new EdgeLayoutPoints(knots));
	}

	/**
	 * Retrieve the {@link ISpline} instance which handles the drawing and
	 * parameterisation policy for this edge.
	 */
	ISpline getDrawingPolicy();

	/**
	 * Set the drawing and parameterisation policy to be an {@link ISpline}
	 * instance of the specified type fitted to this edge.
	 */
	void setDrawingPolicy(SplineType policyType);

	void setDrawingPolicy(ISpline policy);

	IPolyEdge peturb(Point3D peturbation);

	void peturbInPlace(Point3D peturbation);

	//-------------------------------------------------------------
	/**
	 * Convenience methods for retrieving commonly used knotpoints.
	 */
	default Point3D getFirstKnot()
	{
		final EdgeLayoutPoints lpoints = getLayoutPoints();
		if (lpoints == null)
		{
			return null;
		}
		return lpoints.getFirstKnot();
	}

	default Point3D getSecondKnot()
	{
		final EdgeLayoutPoints lpoints = getLayoutPoints();
		if (lpoints == null)
		{
			return null;
		}
		return lpoints.getSecondKnot();
	}

	default Point3D getLastKnot()
	{
		final EdgeLayoutPoints lpoints = getLayoutPoints();
		if (lpoints == null)
		{
			return null;
		}
		return lpoints.getLastKnot();
	}

	default Point3D getPenultimateKnot()
	{
		final EdgeLayoutPoints lpoints = getLayoutPoints();
		if (lpoints == null)
		{
			return null;
		}
		return lpoints.getPenultimateKnot();
	}
	//-------------------------------------------------------------
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