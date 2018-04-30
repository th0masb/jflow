/**
 * Copyright © 2018 Lhasa Limited
 * File created: 7 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.point.impl;

import static org.lhasalimited.common.chainutilities.CollectionUtil.str;

import java.util.Spliterator.OfDouble;
import java.util.function.DoubleUnaryOperator;

import org.lhasalimited.common.chains.rangedfunctions.ImmutableDoubleChainSpliterator;
import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.point.IPointND;

import javafx.geometry.Point2D;

/**
 * @author ThomasB
 * @since 7 Feb 2018
 */
public class Point2DImpl implements IPoint2D
{
	public static final Point2DImpl ORIGIN = new Point2DImpl(0, 0);

	private final double x, y;

	/**
	 *
	 */
	public Point2DImpl(final double x, final double y)
	{
		assert Double.isFinite(x) && !Double.isNaN(x) : str(x);
		assert Double.isFinite(y) && !Double.isNaN(y) : str(y);
		this.x = x;
		this.y = y;
	}

	@Override
	public double elementAt(final int index)
	{
		assert index == 0 || index == 1;
		return index == 0? x : y;
	}

	@Override
	public OfDouble spliterator()
	{
		return new ImmutableDoubleChainSpliterator(this);
	}

	@Override
	public int dim()
	{
		return 2;
	}

	@Override
	public IPoint2D apply(final DoubleUnaryOperator f)
	{
		return new Point2DImpl(f.applyAsDouble(x), f.applyAsDouble(y));
	}

	@Override
	public IPoint2D add(final double dx, final double dy)
	{
		return new Point2DImpl(x + dx, y + dy);
	}

	@Override
	public IPointND add(final IPointND other)
	{
		throw new RuntimeException("nyimpl - should return an nd point of same dim os other");
//		return new Point2DImpl(x + other.x(), y + other.y());
	}

	public Point2D toFxPoint()
	{
		return new Point2D(x, y);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IPointND))
			return false;
		final IPointND other = (IPointND) obj;
		if (other.dim() != 2) {
			return false;
		}
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x()))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y()))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return toFxPoint().toString();
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