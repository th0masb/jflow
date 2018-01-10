/**
 * 
 */
package io.xyz.common.splines2D;

import java.util.List;

import io.xyz.common.matrix.RPoint;

/**
 * @author t
 *
 */
public interface SplineConstructor {

	ISpline constructFrom(List<RPoint> knots);
}
