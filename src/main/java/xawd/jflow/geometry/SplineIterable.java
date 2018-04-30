/**
 *
 */
package xawd.jflow.geometry;

import xawd.jflow.Flow;
import xawd.jflow.geometry.splines.Spline;

/**
 * @author t
 *
 */
public interface SplineIterable
{
	Flow<Spline> iterateOverCurves();
}
