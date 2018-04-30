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
public final class QuadraticCurve extends AbstractSplineSegment {

	public QuadraticCurve(Point from, Point control, Point to) {
		super(Iter.of(from, control, to));
	}

	public QuadraticCurve(QuadraticCurve src) {
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
	public QuadraticCurve map(PointMap mapping) {
		final Point mappedStart = mapping.apply(head(constituentPoints));
		final Point mappedControl = mapping.apply(constituentPoints.get(1));
		final Point mappedEnd = mapping.apply(tail(constituentPoints));
		return new QuadraticCurve(mappedStart, mappedControl, mappedEnd);
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.splines.SplineSegment#trace(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void trace(GraphicsContext gc) {
		final Point control = constituentPoints.get(1);
		final Point end = tail(constituentPoints);
		gc.quadraticCurveTo(control.x(), control.y(), end.x(), end.y());
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.geometry.splines.SplineSegment#copy()
	 */
	@Override
	public QuadraticCurve copy() {
		return new QuadraticCurve(this);
	}

}
