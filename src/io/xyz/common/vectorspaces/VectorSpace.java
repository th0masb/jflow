/**
 *
 */
package io.xyz.common.vectorspaces;

import java.util.function.BinaryOperator;

/**
 * @author t
 *
 */
public interface VectorSpace<E extends Scalable<E>> {
	/**
	 * @return the addition operator attached to this space.
	 */
	BinaryOperator<E> addF();

	/**
	 * @return the dimension of this vector space.
	 */
	int dim();
}
