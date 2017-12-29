/**
 * Copyright © 2017 Lhasa Limited
 * File created: 7 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.graph.splinecurves;

import java.util.List;

import org.lhasalimited.common.math.Point3D;

/**
 * @author ThomasB
 * @since 7 Nov 2017
 */
public interface IKnotPoints extends Iterable<Point3D>
{
	Point3D getStartAnchor();

	void setStartAnchor(Point3D anchorPoint);

	Point3D getEndAnchor();

	void setEndAnchor(Point3D anchorPoint);

	List<Point3D> getKnotPoints();

	void setKnotPoints();

	Point3D get(int index);

	void insert(int idx, Point3D knotPoint);

	void add(Point3D knotPoint);

	boolean isReversed();

	void reverse();

	boolean isEmpty();

	int size();

	void clear();
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