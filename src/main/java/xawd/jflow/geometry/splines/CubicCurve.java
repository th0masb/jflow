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
import xawd.jflow.geometry.mappings.PointMap;

/**
 * @author t
 *
 */
public final class CubicCurve extends AbstractSplineSegment {

	public CubicCurve(Point from, Point c1, Point c2, Point to) {
		super(Iter.of(from, c1, c2, to));
	}

	public CubicCurve(AbstractSplineSegment src) {
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
	public Spline map(PointMap mapping) {
		final Point mappedStart = mapping.apply(head(constituentPoints));
		final Point mappedControl1 = mapping.apply(constituentPoints.get(1));
		final Point mappedControl2 = mapping.apply(constituentPoints.get(2));
		final Point mappedEnd = mapping.apply(tail(constituentPoints));
		return new CubicCurve(mappedStart, mappedControl1, mappedControl2, mappedEnd);
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.splines.SplineSegment#trace(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void trace(GraphicsContext gc) {
		final Point c1 = constituentPoints.get(1);
		final Point c2 = constituentPoints.get(2);
		final Point end = tail(constituentPoints);
		gc.bezierCurveTo(c1.x(), c1.y(), c2.x(), c2.y(), end.x(), end.y());
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.splines.SplineSegment#copy()
	 */
	@Override
	public CubicCurve copy() {
		return new CubicCurve(this);
	}
}
