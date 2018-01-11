/**
 * Copyright � 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix;

import static io.xyz.common.funcutils.CollectionUtil.allEqual;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.MapUtil.doubleRange;
import static io.xyz.common.funcutils.MapUtil.intRange;
import static io.xyz.common.funcutils.PredicateUtil.all;
import static io.xyz.common.funcutils.PrimitiveUtil.isZero;
import static io.xyz.common.funcutils.PrimitiveUtil.pow;
import static io.xyz.common.funcutils.PrimitiveUtil.sqrt;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

import io.xyz.common.function.DoubleCompressor;
import io.xyz.common.geometry.PointMap;
import io.xyz.common.geometry.RealSpaceMember;
import io.xyz.common.matrix.impl.RPointImpl;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;

/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public interface RPoint extends RMatrix, DoubleRangeDescriptor, RealSpaceMember {

	@Override
	RPoint apply(DoubleUnaryOperator f);

	RPoint add(final RPoint other);

	@Override
	RPoint toDescriptorForm();

	@Override
	RPoint toCachedForm();

	// RPoint cross(RPoint other);

	default double x()
	{
		return get(0);
	}

	default double y()
	{
		return get(1);
	}

	default double z()
	{
		return get(2);
	}

	default DoubleRangeDescriptor coords()
	{
		return col(0);
	}

	default boolean isOrigin()
	{
		return all(x -> isZero(x), coords());
	}

	default double dot(final RPoint other)
	{
		assert dimensionsAgree(this, other);
		return sum(combine((a, b) -> a * b, this, other)).getAsDouble()/(magnitude()*other.magnitude());
	}

	default double magnitude()
	{
		return sqrt(sum(doubleRange(x -> x*x, this)).getAsDouble());
	}

	default double distL1(final RPoint other)
	{
		assert dimensionsAgree(this, other);
		return sum(combine((a, b) -> a - b, this, other)).getAsDouble();
	}

	default double distL2(final RPoint other)
	{
		assert dimensionsAgree(this, other);
		return sqrt(sum(combine((a, b) -> pow(a - b, 2), this, other)).getAsDouble());
	}

	/**
	 * @return a point with unit (L2) distance from the origin lying on the line
	 *         with direction OP
	 */
	default RPoint normalise()
	{
		if (isOrigin()) {
			return this;
		}
		return scale(1 / magnitude());
	}

	@Override
	default int dim()
	{
		return len(coords());
	}

	default RPoint operateL(final PointBiOperator f, final RPoint other)
	{
		return f.apply(this, other);
	}

	@Override
	default RPoint scale(final double scaleFactor)
	{
		return apply(x -> x * scaleFactor);
	}

	@Override
	default RPoint abs()
	{
		return apply(x -> Math.abs(x));
	}

	/**
	 * Could override for performance
	 *
	 * @param other
	 * @return
	 */
	default RPoint subtract(final RPoint other)
	{
		assert dimensionsAgree(this, other);
		return add(other.scale(-1));
	}

	/**
	 * Default representation of a RPoint as a PointTransform is a translation.
	 */
	@Override
	default PointMap getMapping()
	{
		return p -> add(p);
	}

	@Override
	default int domainDim()
	{
		return colDim();
	}

	@Override
	default int targetDim()
	{
		return rowDim();
	}

	static boolean dimensionsAgree(final RPoint... ps)
	{
		return allEqual(intRange(p -> p.dim(), ps));
	}

	static RPoint origin(final int dimension)
	{
		return RPointImpl.origin(dimension);
	}

	static RPoint of(final double... ds)
	{
		return new RPointImpl(ds);
	}

	static RPoint of(final DoubleRangeDescriptor ds)
	{
		return new RPointImpl(ds);
	}

	//	static RPoint copy(final RPoint src)
	//	{
	//		return new RPointImpl(src);
	//	}

	@Override
	default RPoint asDoubleRange(final DoubleUnaryOperator f)
	{
		return apply(f);
	}

	@Override
	default IntToDoubleFunction getDescriptor()
	{
		return this::flatAt;
	}

	@Override
	default int rangeBound()
	{
		return rowDim() * colDim();
	}

	@Override
	default double get(final int index)
	{
		assert flatInRange(index);
		return flatAt(index);
	}

	@Override
	default IntRangeDescriptor asIntRange(final DoubleToIntFunction f)
	{
		throw new RuntimeException("NYI");
	}

	@Override
	default <T> RangeDescriptor<T> asObjRange(final DoubleFunction<T> f)
	{
		throw new RuntimeException("NYI");
	}

	@Override
	default DoubleRangeDescriptor filter(final DoublePredicate p)
	{
		throw new RuntimeException("Can't filter a matrix!");
	}

	static RPoint compress(final DoubleCompressor f, final List<RPoint> ps)
	{
		assert len(ps) > 1 && allEqual(intRange(p -> p.dim(), ps));
		final int dimension = ps.get(0).dim();
		final IntFunction<DoubleRangeDescriptor> rowMapper = i -> doubleRange(x -> x.get(i), ps);

		return new RPointImpl(doubleRange(i -> f.compress(rowMapper.apply(i)), dimension));
	}

	public static void main(final String[] args)
	{
		//		final List<RPoint> ps = ImmutableList.of(RPoint.of)
		//
		//				final Curve c = t -> {
		//					final DoubleCompressor compressor = xs -> pow(1 - t, 2)*xs.get(0) + 2*t*(1 - t)*xs.get(1) + pow(t, 2)*xs.get(2);
		//					return RPoint.compress(compressor, getControlPoints());
		//				};
	}
}

/*
 * ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * ---------------------------------------------------------------------
 */