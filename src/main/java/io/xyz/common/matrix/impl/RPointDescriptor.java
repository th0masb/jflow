/**
 * Copyright � 2018 Lhasa Limited
 * File created: 5 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.combine;

import java.util.function.DoubleUnaryOperator;

import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.generators.impl.ImmutableDoubleGenerator;
import io.xyz.common.matrix.RPoint;

/**
 * @author ThomasB
 * @since 5 Jan 2018
 */
public class RPointDescriptor extends RMatrixDescriptor implements RPoint {

	public RPointDescriptor(final double[] xs)
	{
		this(ImmutableDoubleGenerator.from(xs));
	}

	public RPointDescriptor(final DoubleGenerator xs)
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
		return new RPointDescriptor(combine((a, b) -> a + b, this, other));
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

	@Override
	public String toString()
	{
		return toCachedForm().toString();
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