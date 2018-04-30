/**
 * Copyright © 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry;


import static org.lhasalimited.common.chainutilities.CombineUtil.combine;

import java.util.function.BinaryOperator;

import org.lhasalimited.common.math.geometry.point.IPointND;
import org.lhasalimited.common.math.geometry.point.impl.PointNDImpl;


/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public interface PointBiOperator extends BinaryOperator<IPointND>
{
	PointBiOperator SUM = PointBiOperator::sum;

	public static IPointND sum(final IPointND p1, final IPointND p2) {
		return new PointNDImpl(combine((a, b) -> a + b, p1, p2));
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