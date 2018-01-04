/**
 * Copyright © 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix;

import static io.xyz.common.funcutils.CombineUtil.combine;

import java.util.function.BinaryOperator;

import io.xyz.common.matrix.impl.RPointImpl;

/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public interface PointBiOperator extends BinaryOperator<RPoint>
{
	PointBiOperator SUM = PointBiOperator::sum;

	public static RPoint sum(final RPoint p1, final RPoint p2) {
		return new RPointImpl(combine((a, b) -> a + b, p1.coords(), p2.coords()));
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