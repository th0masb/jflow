/**
 *
 */
package io.xyz.common.function;

import static io.xyz.common.funcutils.CombineUtil.dotProduct;

import java.util.function.BinaryOperator;

import io.xyz.common.geometry.RMatrix;

/**
 * @author t
 *
 */
public interface MatrixBiOperator extends BinaryOperator<RMatrix> {

	static MatrixBiOperator COMPOSE = MatrixBiOperator::composition;

	static RMatrix composition(final RMatrix A, final RMatrix B) {
		final int n = A.rowDim(), m = A.colDim(), r = B.colDim();
		assert m == B.rowDim();
		return new RMatrix((i, j) -> dotProduct(A.rowF(i), B.colF(j), m), n, r);
	}
}
