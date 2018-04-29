/**
 *
 */
package xawd.jflow.geometry;

import xawd.jflow.Flow;

/**
 * @author t
 *
 */
public interface Polygon
{
	Flow<Point> points();

	Flow<LineSegment> lines();
}
