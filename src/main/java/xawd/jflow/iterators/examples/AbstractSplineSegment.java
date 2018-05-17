/**
 *
 */
package xawd.jflow.iterators.examples;

import static xawd.jflow.utilities.CollectionUtil.head;

import java.util.Arrays;
import java.util.List;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.construction.Iterate;

/**
 * @author t
 *
 */
public abstract class AbstractSplineSegment implements Spline {

	protected final List<Point> constituentPoints;

	public AbstractSplineSegment(final Point...points) {
		constituentPoints = Arrays.asList(points);
	}

	@Override
	public Flow<Point> iterateOverPoints() {
		return Iterate.over(constituentPoints);
	}

	@Override
	public Point start()
	{
		return head(constituentPoints);
	}

	@Override
	public int size()
	{
		return constituentPoints.size();
	}
}
