/**
 * Copyright © 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.MapUtil.mapToDouble;
import static io.xyz.common.funcutils.RangeUtil.range;

import java.util.function.DoubleUnaryOperator;

import io.xyz.common.matrix.MatrixBiOperator;
import io.xyz.common.matrix.MatrixConstructor;
import io.xyz.common.matrix.RMatrix;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;

/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public final class RMatrixDescriptor implements RMatrix {

	private final DoubleRangeDescriptor contentDescriptor;
	private final short colDim;

	public RMatrixDescriptor(final MatrixConstructor f, final int nrows, final int ncols) {
		this (ncols, mapToDouble(i -> f.map(i/ncols, i%ncols), range(nrows*ncols)));
	}

	private RMatrixDescriptor(final int ncols, final DoubleRangeDescriptor contentDescriptor)
	{
		/* Check row and column numbers match and that dimension is not too high */
		assert ncols > 0 && len(contentDescriptor) % ncols == 0;
		assert (short) ncols == ncols;
		this.contentDescriptor = contentDescriptor;
		this.colDim = (short) ncols;
	}

	@Override
	public double at(final int i, final int j) {
		assert inRange(i, j);
		return contentDescriptor.get(i*colDim() + j);
	}

	@Override
	public RMatrix apply(final DoubleUnaryOperator f)
	{
		return new RMatrixDescriptor(colDim, map(f, contentDescriptor));
	}

	@Override
	public RMatrix composeL(final RMatrix other)
	{
		return operateL(MatrixBiOperator.COMPOSE, other);
	}

	@Override
	public RMatrix add(final RMatrix other)
	{
		return operateL(MatrixBiOperator.SUM, other);
	}

	@Override
	public DoubleRangeDescriptor row(final int index)
	{
		return mapToDouble(j -> at(index, j), range(colDim()));
	}

	@Override
	public DoubleRangeDescriptor col(final int index)
	{
		return mapToDouble(i -> at(i, index), range(rowDim()));
	}

	@Override
	public int rowDim()
	{
		return len(contentDescriptor)/colDim;
	}

	@Override
	public int colDim()
	{
		return colDim;
	}

	@Override
	public RMatrix toDescriptorMatrix()
	{
		return this;
	}

	@Override
	public RMatrix toCachedMatrix()
	{
		return new RMatrixImpl(colDim, contentDescriptor);
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