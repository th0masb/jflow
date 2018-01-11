/**
 * Copyright © 2018 Lhasa Limited
 * File created: 11 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.geometry;

/**
 * @author ThomasB
 * @since 11 Jan 2018
 */
public interface RealSpaceMember
{
	int dim();

	default boolean correctDimensionsToDraw2D(final PointTransform transform)
	{
		return transform.domainDim() == dim() && transform.targetDim() == 2;
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