/**
 * Copyright © 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix;

import static io.xyz.common.funcutils.MapUtil.mapToDouble;
import static io.xyz.common.funcutils.PrimitiveUtil.max;
import static io.xyz.common.funcutils.PrimitiveUtil.min;
import static io.xyz.common.funcutils.RangeUtil.range;

import java.util.function.DoubleUnaryOperator;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;

/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public interface RMatrix extends PointTransform///, DoubleRangeDescriptor
{
	double at(int rowIndex, int colIndex);

	DoubleRangeDescriptor row(int index);

	DoubleRangeDescriptor col(int index);

	int rowDim();

	int colDim();

	RMatrix apply(DoubleUnaryOperator f);

	RMatrix toDescriptorMatrix();

	RMatrix toCachedMatrix();

	RMatrix composeL(RMatrix other);

	RMatrix add(RMatrix other);

	default RMatrix operateL(final MatrixBiOperator f, final RMatrix other) {
		return f.apply(this, other);
	}

	default RMatrix scale(final double scaleFactor) {
		return apply(x -> x*scaleFactor);
	}

	/**
	 * Could override for performance
	 * @param other
	 * @return
	 */
	default RMatrix subtract(final RMatrix other) {
		return add(scale(-1));
	}

	default RMatrix abs() {
		return apply(x -> Math.abs(x));
	}

	default double minEntry() {
		return min(mapToDouble(this::flatAt, range(colDim()*rowDim()))).getAsDouble();
	}

	default double maxEntry() {
		return max(mapToDouble(this::flatAt, range(colDim()*rowDim()))).getAsDouble();
	}

	default double flatAt(final int index) {
		assert flatInRange(index);
		return at(index/colDim(), index%colDim());
	}

	default boolean flatInRange(final int index) {
		return 0 <= index && index < rowDim()*colDim();
	}

	default boolean inRange(final int rowIndex, final int colIndex) {
		return flatInRange(rowIndex*colDim() + colIndex);
	}

	/**
	 * Default mapping is composition/multiplication
	 */
	@Override
	default PointMap getMapping() {
		return p -> MatrixBiOperator.pointComposition(this, p);
	}

	@Override
	default int domainDim() {
		return colDim();
	}

	@Override
	default int targetDim() {
		return rowDim();
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