/**
 * Copyright © 2018 Lhasa Limited
 * File created: 11 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry;

import static org.lhasalimited.common.chainutilities.MapUtil.intMap;
import static org.lhasalimited.common.chainutilities.PredicateUtil.allEqual;

import org.lhasalimited.common.math.geometry.point.PointTransformND;

/**
 * @author ThomasB
 * @since 11 Jan 2018
 */
public interface IRealSpaceMember
{
	int dim();

	default boolean correctDimensionsToDraw2D(final PointTransformND transform)
	{
		return transform.domainDim() == dim() && transform.targetDim() == 2;
	}

	static boolean dimensionsAgree(final IRealSpaceMember... ps)
	{
		return allEqual(intMap(p -> p.dim(), ps));
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