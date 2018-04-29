/**
 *
 */
package xawd.jflow.geometry;

import xawd.jflow.Flow;
import xawd.jflow.construction.Iter;

/**
 * @author t
 *
 */
public interface LineSegment
{
	Point start();

	Point end();

	Point interpolate(double t);

	default Flow<Point> points()
	{
		return Iter.of(start(), end());
	}
}
