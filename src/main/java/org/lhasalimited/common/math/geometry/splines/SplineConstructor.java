/**
 *
 */
package org.lhasalimited.common.math.geometry.splines;

import java.util.List;

import org.lhasalimited.common.math.geometry.point.IPoint2D;


/**
 * @author t
 *
 */
public interface SplineConstructor
{
	ISpline2D constructFrom(List<? extends IPoint2D> knots);
}
