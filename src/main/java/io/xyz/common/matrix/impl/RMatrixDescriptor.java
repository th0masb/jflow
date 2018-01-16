/**
 * Copyright � 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.doubleRange;

import java.util.function.DoubleUnaryOperator;

import io.xyz.common.funcutils.MapUtil;
import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.matrix.MatrixConstructor;
import io.xyz.common.matrix.RMatrix;
import io.xyz.common.matrix.RPoint;

/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public class RMatrixDescriptor implements RMatrix {

	protected final DoubleGenerator contentDescriptor;
	protected final short colDim;

	public RMatrixDescriptor(final MatrixConstructor f, final int nrows, final int ncols)
	{
		this(ncols, doubleRange(i -> f.map(i / ncols, i % ncols), MapUtil.range(nrows * ncols)));
	}

	RMatrixDescriptor(final int ncols, final DoubleGenerator contentDescriptor)
	{
		/* Check row and column numbers match and that dimension is not too high */
		assert ncols > 0 && len(contentDescriptor) % ncols == 0;
		assert (short) ncols == ncols;
		this.contentDescriptor = contentDescriptor;
		this.colDim = (short) ncols;
	}

	@Override
	public double at(final int i, final int j)
	{
		assert inRange(i, j);
		return contentDescriptor.get(i * colDim() + j);
	}

	@Override
	public RMatrix apply(final DoubleUnaryOperator f)
	{
		return new RMatrixDescriptor(colDim, doubleRange(f, contentDescriptor));
	}

	@Override
	public RMatrix composeL(final RMatrix other)
	{
		return operateL(Matrices.DESCRIPTOR_COMPOSE, other);
	}

	@Override
	public RMatrix add(final RMatrix other)
	{
		return operateL(Matrices.DESCRIPTOR_SUM, other);
	}

	@Override
	public DoubleGenerator row(final int index)
	{
		return doubleRange(j -> at(index, j), MapUtil.range(colDim()));
	}

	@Override
	public DoubleGenerator col(final int index)
	{
		return doubleRange(i -> at(i, index), MapUtil.range(rowDim()));
	}

	@Override
	public int rowDim()
	{
		return len(contentDescriptor) / colDim;
	}

	@Override
	public int colDim()
	{
		return colDim;
	}

	@Override
	public RMatrix toDescriptorForm()
	{
		return this;
	}

	@Override
	public RMatrix toCachedForm()
	{
		return new RMatrixImpl(colDim, contentDescriptor);
	}

	@Override
	public RPoint composeL(final RPoint other)
	{
		return operateL(Matrices.DESCRIPTOR_POINT_COMPOSITION, other);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return toCachedForm().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		return toCachedForm().equals(obj);
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