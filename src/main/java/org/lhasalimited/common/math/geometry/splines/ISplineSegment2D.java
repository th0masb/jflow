/**
 * Copyright © 2018 Lhasa Limited
 * File created: 19 Feb 2018 by thomasb
 * Creator : thomasb
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import java.util.function.UnaryOperator;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.geometry.ICurve2D;
import org.lhasalimited.common.math.geometry.canvasbinding.IPathTraceable2D;
import org.lhasalimited.common.math.geometry.point.IPoint2D;

/**
 * @author thomasb
 * @since 19 Feb 2018
 */
public interface ISplineSegment2D extends IPathTraceable2D, Chain<IPoint2D>
{
	ICurve2D parameterise();

	double length();

	ISplineSegment2D peturb(IPoint2D peturbation);

	@Override
	ISplineSegment2D map(final UnaryOperator<IPoint2D> f);

	SegmentType getType();

	default IPoint2D from()
	{
		return elementAt(0);
	}

	default IPoint2D to()
	{
		return elementAt(linkCount() - 1);
	}

	@Override
	default IPoint2D getPathStart()
	{
		return from();
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