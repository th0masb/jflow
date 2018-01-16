/**
 * Copyright � 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix;

import static io.xyz.common.funcutils.MapUtil.doubleRange;
import static io.xyz.common.funcutils.MapUtil.range;
import static io.xyz.common.funcutils.PrimitiveUtil.max;
import static io.xyz.common.funcutils.PrimitiveUtil.min;

import java.util.function.BiFunction;
import java.util.function.DoubleUnaryOperator;

import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.geometry.PointMap;
import io.xyz.common.geometry.PointTransform;

/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public interface RMatrix extends PointTransform { // , DoubleRangeDescriptor {
	double at(int rowIndex, int colIndex);

	DoubleGenerator row(int index);

	DoubleGenerator col(int index);

	int rowDim();

	int colDim();

	RMatrix apply(DoubleUnaryOperator f);

	/*
	 * TODO - add vcombine - i.e. vectorised combine.
	 */

	RMatrix toDescriptorForm();

	RMatrix toCachedForm();

	RMatrix composeL(RMatrix other);

	RPoint composeL(RPoint other);

	RMatrix add(RMatrix other);

	default RMatrix operateL(final MatrixBiOperator f, final RMatrix other)
	{
		return f.apply(this, other);
	}

	default RPoint operateL(final BiFunction<RMatrix, RPoint, RPoint> f, final RPoint other)
	{
		return f.apply(this, other);
	}

	default RMatrix scale(final double scaleFactor)
	{
		return apply(x -> x * scaleFactor);
	}

	/**
	 * Could override for performance
	 * 
	 * @param other
	 * @return
	 */
	default RMatrix subtract(final RMatrix other)
	{
		return add(scale(-1));
	}

	default RMatrix abs()
	{
		return apply(x -> Math.abs(x));
	}

	default double minEntry()
	{
		return min(flatContents()).getAsDouble();
	}

	default double maxEntry()
	{
		return max(flatContents()).getAsDouble();
	}

	default double flatAt(final int index)
	{
		assert flatInRange(index);
		return at(index / colDim(), index % colDim());
	}

	default boolean flatInRange(final int index)
	{
		return 0 <= index && index < rowDim() * colDim();
	}

	default boolean inRange(final int rowIndex, final int colIndex)
	{
		return flatInRange(rowIndex * colDim() + colIndex);
	}

	default DoubleGenerator flatContents()
	{
		return doubleRange(this::flatAt, range(colDim() * rowDim()));
	}

	/**
	 * Default mapping is composition/multiplication
	 */
	@Override
	default PointMap getMapping()
	{
		return p -> composeL(p);
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