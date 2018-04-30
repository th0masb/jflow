/**
 * Copyright � 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.point;

import static org.lhasalimited.common.chainutilities.CombineUtil.combine;
import static org.lhasalimited.common.chainutilities.CombineUtil.dotProduct;
import static org.lhasalimited.common.chainutilities.PredicateUtil.all;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.isZero;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.pow;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.sqrt;
import static org.lhasalimited.common.chainutilities.PrimitiveUtil.sum;

import java.util.function.BinaryOperator;
import java.util.function.DoubleUnaryOperator;

import org.lhasalimited.common.chains.DoubleChain;
import org.lhasalimited.common.math.geometry.IRealSpaceMember;
import org.lhasalimited.common.math.geometry.point.impl.PointNDImpl;

/**
 * A point of arbitrary dimension (> 1). Dimension validity of operations is checked via
 * assertion statements since they can be switched off for performance gains.
 *
 * @author ThomasB
 * @since 4 Jan 2018
 */
public interface IPointND extends DoubleChain, IRealSpaceMember
{
	IPointND apply(DoubleUnaryOperator f);

	/* TODO - should we allow addition of points of different dimensions? */
	IPointND add(final IPointND other);

	default double x()
	{
		return elementAt(0);
	}

	default double y()
	{
		return elementAt(1);
	}

	default double z()
	{
		return elementAt(2);
	}

	default boolean isOrigin()
	{
		return all(x -> isZero(x), this);
	}

	default double magnitude()
	{
		return sqrt(dotProduct(this, this));
	}

	default double dot(final IPointND other)
	{
		assert IRealSpaceMember.dimensionsAgree(this, other);
		return dotProduct(this, other) / (magnitude() * other.magnitude());
	}

	default double distL1(final IPointND other)
	{
		assert IRealSpaceMember.dimensionsAgree(this, other);
		return sum(combine((a, b) -> a - b, this, other)).getAsDouble();
	}

	default double distL2(final IPointND other)
	{
		assert IRealSpaceMember.dimensionsAgree(this, other);
		return sqrt(sum(combine((a, b) -> pow(a - b, 2), this, other)).getAsDouble());
	}

	/**
	 * Denote this instance by P and the origin by O.
	 *
	 * @return a point with unit (L2) distance from the origin lying on the line
	 *         with direction OP if P is not the origin, otherwise return O
	 */
	default IPointND normalise()
	{
		if (isOrigin()) {
			return this;
		}
		return scale(1 / magnitude());
	}

	default IPointND operateL(final BinaryOperator<IPointND> f, final IPointND other)
	{
		return f.apply(this, other);
	}

	default IPointND scale(final double scaleFactor)
	{
		return apply(x -> x * scaleFactor);
	}

	/**
	 * Could override for performance
	 *
	 * @param other
	 * @return
	 */
	default IPointND subtract(final IPointND other)
	{
		assert IRealSpaceMember.dimensionsAgree(this, other);
		return add(other.scale(-1));
	}

//	@Override
//	default IPointND apply(final IPointND p)
//	{
//		return p.add(this);
//	}
//
//	@Override
//	default int domainDim()
//	{
//		return dim();
//	}
//
//	@Override
//	default int targetDim()
//	{
//		return dim();
//	}

	static IPointND origin(final int dimension)
	{
		return PointNDImpl.origin(dimension);
	}

	static IPointND of(final double... ds)
	{
		return new PointNDImpl(ds);
	}

	static IPointND immutableOf(final DoubleChain ds)
	{
		return new PointNDImpl(ds);
	}

	// static RPoint copy(final RPoint src)
	// {
	// return new RPointImpl(src);
	// }

	@Override
	default IPointND map(final DoubleUnaryOperator f)
	{
		return apply(f);
	}

	@Override
	default int linkCount()
	{
		return dim();
	}

	public static void main(final String[] args)
	{
		// final List<RPoint> ps = ImmutableList.of(RPoint.of)
		//
		// final Curve c = t -> {
		// final DoubleCompressor compressor = xs -> pow(1 - t, 2)*xs.get(0) + 2*t*(1 - t)*xs.get(1) + pow(t, 2)*xs.get(2);
		// return RPoint.compress(compressor, getControlPoints());
		// };
	}
}

/* ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * --------------------------------------------------------------------- */