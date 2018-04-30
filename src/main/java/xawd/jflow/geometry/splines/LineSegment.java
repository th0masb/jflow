/**
 *
 */
package xawd.jflow.geometry.splines;

import static xawd.jflow.utilities.CollectionUtil.head;
import static xawd.jflow.utilities.CollectionUtil.tail;

import javafx.scene.canvas.GraphicsContext;
import xawd.jflow.construction.Iter;
import xawd.jflow.geometry.ParameterisedCurve;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.impl.PointImpl;
import xawd.jflow.geometry.mappings.PointMap;

/**
 * @author t
 *
 */
public final class LineSegment extends AbstractSplineSegment
{
	public LineSegment(Point start, Point end) {
		super(Iter.of(start, end));
	}

	public LineSegment(LineSegment src) {
		super(src);
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.splines.SplineSegment#parameterise()
	 */
	@Override
	public ParameterisedCurve parameterise() {
		throw new RuntimeException();
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.splines.SplineSegment#map(xawd.jflow.geometry.mappings.PointMap)
	 */
	@Override
	public LineSegment map(PointMap mapping) {
		final Point mappedStart = mapping.apply(head(constituentPoints));
		final Point mappedEnd = mapping.apply(tail(constituentPoints));
		return new LineSegment(mappedStart, mappedEnd);
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.splines.SplineSegment#trace(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void trace(GraphicsContext gc) {
		final Point end = tail(constituentPoints);
		gc.lineTo(end.x(), end.y());
	}

	@Override
	public LineSegment copy() {
		return new LineSegment(this);
	}

	@Override
	public Point start()
	{
		return head(constituentPoints);
	}

	public Point end()
	{
		return tail(constituentPoints);
	}

	public Point interpolate(double t)
	{
		return new PointImpl(
				(1 - t)*start().x() + t*end().x(),
				(1 - t)*start().y() + t*end().y());
	}
}
