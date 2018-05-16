/**
 *
 */
package xawd.jflow.iterators.examples;

import xawd.jflow.iterators.Flow;

/**
 * @author t
 *
 */
public interface LineIterable
{
	Flow<LineSegment> iterateOverLines();
}
