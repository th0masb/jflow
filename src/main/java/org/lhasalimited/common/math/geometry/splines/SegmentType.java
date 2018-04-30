/**
 * Copyright © 2018 Lhasa Limited
 * File created: 22 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import static org.lhasalimited.common.chainutilities.CollectionUtil.head;
import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.CollectionUtil.tail;

import java.util.List;

import org.lhasalimited.common.math.geometry.point.IPoint2D;

/**
 * @author ThomasB
 * @since 22 Feb 2018
 */
public enum SegmentType
{
	STRAIGHT
	{
		@Override
		public Line2D construct(final List<IPoint2D> ps)
		{
			if (len(ps) != 2) {
				throw new IllegalArgumentException();
			}
			return Line2D.between(head(ps), tail(ps));
		}
	},
	QUADRATIC
	{
		@Override
		public QuadraticBezier2D construct(final List<IPoint2D> ps)
		{
			if (len(ps) != 3) {
				throw new IllegalArgumentException();
			}
			return new QuadraticBezier2D(head(ps), ps.get(1), tail(ps));
		}
	},
	CUBIC
	{
		@Override
		public CubicBezier2D construct(final List<IPoint2D> ps)
		{
			if (len(ps) != 4) {
				throw new IllegalArgumentException();
			}
			return new CubicBezier2D(head(ps), ps.get(1), ps.get(2), tail(ps));
		}
	};

	public abstract ISplineSegment2D construct(List<IPoint2D> ps);
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