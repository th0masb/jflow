/**
 *
 */
package xawd.jflow.geometry;

import xawd.jflow.Flow;
import xawd.jflow.geometry.splines.LineSegment;

/**
 * @author t
 *
 */
public interface LineIterable
{
	Flow<LineSegment> iterateOverLines();
}
