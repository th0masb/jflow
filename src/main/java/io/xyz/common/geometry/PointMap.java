/**
 *
 */
package io.xyz.common.geometry;

import io.xyz.common.matrix.RPoint;

/**
 * @author t
 *
 */
public interface PointMap {
	RPoint transform(RPoint p);
}
