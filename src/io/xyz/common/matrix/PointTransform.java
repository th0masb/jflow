/**
 *
 */
package io.xyz.common.matrix;

/**
 * @author t
 *
 */
public interface PointTransform {

	PointMap getMapping();

	int domainDim();

	int targetDim();
}
