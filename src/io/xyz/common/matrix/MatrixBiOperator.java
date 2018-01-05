/**
 *
 */
package io.xyz.common.matrix;

import static io.xyz.common.funcutils.CombineUtil.dotProduct;
import static io.xyz.common.funcutils.MapUtil.mapToDouble;
import static io.xyz.common.funcutils.RangeUtil.range;

import java.util.function.BinaryOperator;

import io.xyz.common.matrix.impl.RMatrixDescriptor;
import io.xyz.common.matrix.impl.RMatrixImpl;
import io.xyz.common.matrix.impl.RPointImpl;

/**
 * @author t
 *
 */
public interface MatrixBiOperator extends BinaryOperator<RMatrix> {

	static MatrixBiOperator COMPOSE = MatrixBiOperator::composition;

	static RMatrix composition(final RMatrix A, final RMatrix B) {
		final int n = A.rowDim(), m = A.colDim(), r = B.colDim();
		assert m == B.rowDim();
		return new RMatrixImpl((i, j) -> dotProduct(A.row(i), B.col(j)), n, r);
	}

	static MatrixBiOperator DESCRIPTOR_COMPOSE = MatrixBiOperator::descriptorComposition;

	static RMatrix descriptorComposition(final RMatrix A, final RMatrix B) {
		final int n = A.rowDim(), m = A.colDim(), r = B.colDim();
		assert m == B.rowDim();
		return new RMatrixDescriptor((i, j) -> dotProduct(A.row(i), B.col(j)), n, r);
	}

	static MatrixBiOperator SUM = MatrixBiOperator::sum;

	static RMatrix sum(final RMatrix A, final RMatrix B) {
		assert A.rowDim() == B.rowDim() && A.rowDim() == B.rowDim();
		final int n = A.rowDim(), m = A.colDim();
		return new RMatrixImpl((i, j) -> A.at(i, j) + B.at(i, j), n, m);
	}

	static MatrixBiOperator DESCRIPTOR_SUM = MatrixBiOperator::descriptorSum;

	static RMatrix descriptorSum(final RMatrix A, final RMatrix B) {
		assert A.rowDim() == B.rowDim() && A.rowDim() == B.rowDim();
		final int n = A.rowDim(), m = A.colDim();
		return new RMatrixDescriptor((i, j) -> A.at(i, j) + B.at(i, j), n, m);
	}

	//	static MatrixBiOperator POINT_COMPOSITION = MatrixBiOperator::pointComposition;

	static RPointImpl pointComposition(final RMatrix A, final RPoint P) {
		assert A.colDim() == P.dim();
		return new RPointImpl(mapToDouble(i -> dotProduct(A.row(i), P.coords()), range(P.dim())));
	}
}
