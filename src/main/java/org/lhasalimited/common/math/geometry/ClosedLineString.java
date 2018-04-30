/**
 * Copyright © 2018 Lhasa Limited
 * File created: 30 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry;

import static org.lhasalimited.common.chainutilities.CollectionUtil.head;
import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.MapUtil.doubleMap;

import org.lhasalimited.common.chains.Chain;
import org.lhasalimited.common.math.geometry.canvasbinding.FXContextBinding;
import org.lhasalimited.common.math.geometry.canvasbinding.IFillable;
import org.lhasalimited.common.math.geometry.canvasbinding.IStrokeable;
import org.lhasalimited.common.math.geometry.point.IPointND;
import org.lhasalimited.common.math.geometry.point.IProjection2D;

import com.google.common.collect.ImmutableList;

/**
 * It is strongly advised to use immutable {@link IPointND} instances.
 *
 * @author ThomasB
 * @since 30 Jan 2018
 */
public class ClosedLineString implements Chain<IPointND>, IStrokeable, IFillable
{
	private final ImmutableList<IPointND> ps;

	/**
	 * TODO - probably shouldn't use an Iterable since we need at least three constituent points
	 */
	public ClosedLineString(final Iterable<? extends IPointND> constructor)
	{
		ps = ImmutableList.copyOf(constructor);
	}

	@Override
	public int dim()
	{
		return head(ps).dim();
	}

	@Override
	public void projectThenFill(final FXContextBinding gc, final IProjection2D T)
	{
		final double[] xs = doubleMap(T::projectToX, ps), ys = doubleMap(T::projectToY, ps);
		gc.getBoundContext().fillPolygon(xs, ys, linkCount());
	}

	@Override
	public void projectThenStroke(final FXContextBinding gc, final IProjection2D T)
	{
		final double[] xs = doubleMap(T::projectToX, ps), ys = doubleMap(T::projectToY, ps);
		gc.getBoundContext().strokePolygon(xs, ys, linkCount());
	}

	@Override
	public int linkCount()
	{
		return len(ps);
	}

	@Override
	public IPointND elementAt(final int index)
	{
		return ps.get(index);
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