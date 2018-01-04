/**
 *
 */
package io.xyz.common.matrix;

import static io.xyz.common.funcutils.CombineUtil.dotProduct;

import java.util.function.BinaryOperator;

import io.xyz.common.matrix.impl.RMatrix;

/**
 * @author t
 *
 */
public interface MatrixBiOperator extends BinaryOperator<RMatrix> {

	static MatrixBiOperator COMPOSE = MatrixBiOperator::composition;

	static RMatrix composition(final RMatrix A, final RMatrix B) {
		final int n = A.rowDim(), m = A.colDim(), r = B.colDim();
		assert m == B.rowDim();
		return new RMatrix((i, j) -> dotProduct(A.row(i), B.col(j)), n, r);
	}

	static MatrixBiOperator SUM = MatrixBiOperator::sum;

	static RMatrix sum(final RMatrix A, final RMatrix B) {
		assert A.rowDim() == B.rowDim() && A.rowDim() == B.rowDim();
		final int n = A.rowDim(), m = A.colDim();
		return new RMatrix((i, j) -> A.at(i, j) + B.at(i, j), n, m);
	}
}
