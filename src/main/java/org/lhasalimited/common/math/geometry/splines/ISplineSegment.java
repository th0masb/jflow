/**
 * Copyright � 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import java.util.function.UnaryOperator;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.geometry.ICurve;
import org.lhasalimited.common.math.geometry.canvasbinding.IPathTraceable;
import org.lhasalimited.common.math.geometry.point.IPointND;

/**
 * Interface representing a portion of some curve in space which is
 * responsible for handling the implementation of drawing itself.
 *
 * @author ThomasB
 * @since 3 Nov 2017
 */
public interface ISplineSegment extends IPathTraceable, Chain<IPointND>
{
	ICurve parameterise();

	double length();

	ISplineSegment peturb(IPointND peturbation);

	@Override
	ISplineSegment map(final UnaryOperator<IPointND> f);

	@Override
	default int dim()
	{
		return from().dim();
	}

	default IPointND from()
	{
		return elementAt(0);
	}

	default IPointND to()
	{
		return elementAt(linkCount() - 1);
	}

	@Override
	default IPointND getPathStart()
	{
		return from();
	}
}

/* ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * --------------------------------------------------------------------- */