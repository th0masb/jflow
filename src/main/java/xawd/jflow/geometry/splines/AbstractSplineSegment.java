/**
 *
 */
package xawd.jflow.geometry.splines;

import static xawd.jflow.utilities.CollectionUtil.head;

import xawd.jflow.Flow;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.mappings.PointMap;
import xawd.lists.listflow.ListFlow;

/**
 * @author t
 *
 */
public abstract class AbstractSplineSegment implements Spline {

	protected final ListFlow<Point> constituentPoints;

	public AbstractSplineSegment(Flow<Point> src) {
		constituentPoints = src.toListFlow();
	}

	public AbstractSplineSegment(AbstractSplineSegment src) {
		constituentPoints = src.iterateOverPoints().map(Point::copy).toListFlow();
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.PointIterable#iterateOverPoints()
	 */
	@Override
	public Flow<Point> iterateOverPoints() {
		return constituentPoints.iter();
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.splines.SplineSegment#mapInPlace(xawd.jflow.geometry.mappings.PointMap)
	 */
	@Override
	public void mapInPlace(PointMap mapping) {
		constituentPoints.iter().forEach(mapping::mapInPlace);
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
