/**
 *
 */
package io.xyz.common.vectorspaces;

/**
 * @author t
 *
 */
public interface NormedVectorSpace<E extends Scalable<E>> extends VectorSpace<E> {
	Norm<E> norm();
}
