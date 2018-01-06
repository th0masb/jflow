/**
 * Copyright � 2018 Lhasa Limited
 * File created: 5 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CombineUtil.dotProduct;
import static io.xyz.common.funcutils.MapUtil.doubleRange;

import java.util.function.BiFunction;

import io.xyz.common.funcutils.MapUtil;
import io.xyz.common.matrix.MatrixBiOperator;
import io.xyz.common.matrix.MatrixConstructor;
import io.xyz.common.matrix.RMatrix;
import io.xyz.common.matrix.RPoint;

/**
 * @author ThomasB
 * @since 5 Jan 2018
 */
public final class Matrices {
	private Matrices()
	{
	}

	public static RMatrix rotate2D(final double radians)
	{
		return new RMatrixDescriptor(MatrixConstructor.rotate2D(radians), 2, 2);
	}

	public static RMatrix identity(final int dim)
	{
		assert dim > 0;
		return new RMatrixImpl(MatrixConstructor.identity(), dim, dim);
	}

	// public static RMatrix rotate2D(final double radians) {
	// return new RMatrixImpl(MatrixConstructor.rotate2D(radians), 2, 2);
	// }

	public static final MatrixBiOperator COMPOSE = Matrices::composition;

	public static RMatrix composition(final RMatrix A, final RMatrix B)
	{
		final int n = A.rowDim(), m = A.colDim(), r = B.colDim();
		assert m == B.rowDim();
		return new RMatrixImpl((i, j) -> dotProduct(A.row(i), B.col(j)), n, r);
	}

	public static final MatrixBiOperator DESCRIPTOR_COMPOSE = Matrices::descriptorComposition;

	public static RMatrix descriptorComposition(final RMatrix A, final RMatrix B)
	{
		final int n = A.rowDim(), m = A.colDim(), r = B.colDim();
		assert m == B.rowDim();
		return new RMatrixDescriptor((i, j) -> dotProduct(A.row(i), B.col(j)), n, r);
	}

	public static final MatrixBiOperator SUM = Matrices::sum;

	public static RMatrix sum(final RMatrix A, final RMatrix B)
	{
		assert A.rowDim() == B.rowDim() && A.rowDim() == B.rowDim();
		final int n = A.rowDim(), m = A.colDim();
		return new RMatrixImpl((i, j) -> A.at(i, j) + B.at(i, j), n, m);
	}

	public static final MatrixBiOperator DESCRIPTOR_SUM = Matrices::descriptorSum;

	public static RMatrix descriptorSum(final RMatrix A, final RMatrix B)
	{
		assert A.rowDim() == B.rowDim() && A.rowDim() == B.rowDim();
		final int n = A.rowDim(), m = A.colDim();
		return new RMatrixDescriptor((i, j) -> A.at(i, j) + B.at(i, j), n, m);
	}

	public static final BiFunction<RMatrix, RPoint, RPoint> POINT_COMPOSITION = Matrices::pointComposition;

	public static RPoint pointComposition(final RMatrix A, final RPoint P)
	{
		assert A.colDim() == P.dim();
		return new RPointImpl(doubleRange(i -> dotProduct(A.row(i), P), MapUtil.range(P.dim())));
	}

	public static final BiFunction<RMatrix, RPoint, RPoint> DESCRIPTOR_POINT_COMPOSITION = Matrices::descriptorPointComposition;

	public static RPoint descriptorPointComposition(final RMatrix A, final RPoint P)
	{
		assert A.colDim() == P.dim();
		return new RPointDescriptor(doubleRange(i -> dotProduct(A.row(i), P), MapUtil.range(P.dim())));
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final RMatrix M = rotate2D(Math.PI / 2).toDescriptorForm();
		System.out.println(M.row(0));
		System.out.println(M.row(1));
		//
		// System.out.println(len(M.row(0)));

		final RPoint p = RPoint.of(new double[] { 0, 1 }).toDescriptorForm();
		System.out.println(p);
		final RPoint q = M.transform(RPoint.of(0, 1));
		System.out.println(q);
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