/**
 * Copyright � 2018 Lhasa Limited
 * File created: 5 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.function.DoubleUnaryOperator;

import io.xyz.common.matrix.RPoint;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;

/**
 * @author ThomasB
 * @since 5 Jan 2018
 */
public class RPointDescriptor extends RMatrixDescriptor implements RPoint {

	public RPointDescriptor(final double[] xs)
	{
		this(ImmutableDoubleRangeDescriptor.from(xs));
	}

	public RPointDescriptor(final DoubleRangeDescriptor xs)
	{
		super(len(xs), xs);
	}

	/*
	 * We store a point in a row vector but it is a column vector. We therefore
	 * override the following three methods to reflect this change.
	 */
	@Override
	public double at(final int i, final int j)
	{
		return super.at(j, i);
	}

	@Override
	public int colDim()
	{
		return super.rowDim();
	}

	@Override
	public int rowDim()
	{
		return super.colDim();
	}

	@Override
	public RPoint apply(final DoubleUnaryOperator f)
	{
		return new RPointDescriptor(contentDescriptor);
	}

	@Override
	public RPoint add(final RPoint other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RPoint toDescriptorForm()
	{
		return this;
	}

	@Override
	public RPoint toCachedForm()
	{
		return new RPointImpl(contentDescriptor);
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