/**
 * Copyright © 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix;

import static io.xyz.common.funcutils.CollectionUtil.allEqual;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.MapUtil.mapCollect;
import static io.xyz.common.funcutils.PredicateUtil.all;
import static io.xyz.common.funcutils.PrimitiveUtil.isZero;
import static io.xyz.common.funcutils.PrimitiveUtil.pow;
import static io.xyz.common.funcutils.PrimitiveUtil.sqrt;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;

import java.util.function.DoubleUnaryOperator;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;

/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public interface RPoint extends RMatrix
{
	@Override
	RPoint apply(DoubleUnaryOperator f);

	RPoint add(final RPoint other);

	RPoint toDescriptorPoint();

	RPoint toCachedPoint();

	//	RPoint cross(RPoint other);

	default double get(final int index) {
		assert flatInRange(index);
		return flatAt(index);
	}

	default double x() {
		return get(0);
	}

	default double y() {
		return get(1);
	}

	default double z() {
		return get(2);
	}

	default DoubleRangeDescriptor coords() {
		return row(0);
	}

	default boolean isOrigin() {
		return all(x -> isZero(x), coords());
	}

	default double dot(final RPoint other) {
		assert dimensionsAgree(this, other);
		return sum(combine((a, b) -> a * b, coords(), other.coords())).getAsDouble();
	}

	default double magnitude() {
		return sqrt(dot(this));
	}

	default double distL1(final RPoint other) {
		assert dimensionsAgree(this, other);
		return sum(combine((a, b) -> a - b, coords(), other.coords())).getAsDouble();
	}

	default double distL2(final RPoint other) {
		assert dimensionsAgree(this, other);
		return sqrt(sum(combine((a, b) -> pow(a - b, 2), coords(), other.coords())).getAsDouble());
	}

	/**
	 * @return a point with unit (L2) distance from the origin lying
	 * on the line with direction OP
	 */
	default RPoint normalise() {
		if (isOrigin()) {
			return this;
		}
		return scale(1 / magnitude());
	}

	default int dim() {
		return len(coords());
	}

	default RPoint operateL(final PointBiOperator f, final RPoint other) {
		return f.apply(this, other);
	}

	@Override
	default RPoint scale(final double scaleFactor) {
		return apply(x -> x*scaleFactor);
	}

	@Override
	default RPoint abs() {
		return apply(x -> Math.abs(x));
	}

	/**
	 * Could override for performance
	 * @param other
	 * @return
	 */
	default RPoint subtract(final RPoint other) {
		assert dimensionsAgree(this, other);
		return add(scale(-1));
	}

	/**
	 * Default representation of a RPoint as a PointTransform
	 * is a translation.
	 */
	@Override
	default PointMap getMapping() {
		return  p -> add(p);
	}

	@Override
	default int domainDim() {
		return colDim();
	}

	@Override
	default int targetDim() {
		return rowDim();
	}

	static boolean dimensionsAgree(final RPoint... ps) {
		return allEqual(mapCollect(p -> p.dim(), ps));
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