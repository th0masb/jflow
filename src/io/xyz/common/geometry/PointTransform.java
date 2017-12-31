/**
 *
 */
package io.xyz.common.geometry;

/**
 * @author t
 *
 */
public interface PointTransform {
	PointMap getMapping();

	int domainDim();

	int targetDim();
}
