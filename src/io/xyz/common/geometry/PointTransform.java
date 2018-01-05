/**
 *
 */
package io.xyz.common.geometry;

import io.xyz.common.matrix.RPoint;

/**
 * @author t
 *
 */
public interface PointTransform {

	PointMap getMapping();

	int domainDim();

	int targetDim();

	/**
	 * We perform runtime assertions on the dimension of input/output.
	 * @param p - the point to transform
	 * @return the result of applying the transform.
	 */
	default RPoint transform(final RPoint p) {
		assert p.dim() == domainDim();
		final RPoint result = getMapping().transform(p);
		assert result.dim() == targetDim();
		return result;
	}
}
