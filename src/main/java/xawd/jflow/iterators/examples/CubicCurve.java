/**
 *
 */
package xawd.jflow.iterators.examples;

import static xawd.jflow.iterators.utilities.CollectionUtil.head;
import static xawd.jflow.iterators.utilities.CollectionUtil.tail;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author t
 *
 */
public final class CubicCurve extends AbstractSplineSegment {

	public CubicCurve(final Point from, final Point c1, final Point c2, final Point to) {
		super(from, c1, c2, to);
	}

	@Override
	public ParameterisedCurve parameterise() {
		final Point start = head(constituentPoints);
		final Point c1 = constituentPoints.get(1);
		final Point c2 = constituentPoints.get(2);
		final Point end = tail(constituentPoints);
		return ParameterisedCurve.cubicLine(start, c1, c2, end);
	}

	@Override
	public Spline map(final PointMapping mapping) {
		final Point mappedStart = mapping.mapPoint(head(constituentPoints));
		final Point mappedControl1 = mapping.mapPoint(constituentPoints.get(1));
		final Point mappedControl2 = mapping.mapPoint(constituentPoints.get(2));
		final Point mappedEnd = mapping.mapPoint(tail(constituentPoints));
		return new CubicCurve(mappedStart, mappedControl1, mappedControl2, mappedEnd);
	}

	@Override
	public void fillTrace(final GraphicsContext gc) {
		final Point start = head(constituentPoints);
		final Point c1 = constituentPoints.get(1);
		final Point c2 = constituentPoints.get(2);
		final Point end = tail(constituentPoints);
		gc.moveTo(start.x, start.y);
		gc.bezierCurveTo(c1.x, c1.y, c2.x, c2.y, end.x, end.y);
	}

	@Override
	public void strokeTrace(final GraphicsContext gc)
	{
		final Point c1 = constituentPoints.get(1);
		final Point c2 = constituentPoints.get(2);
		final Point end = tail(constituentPoints);
		gc.bezierCurveTo(c1.x, c1.y, c2.x, c2.y, end.x, end.y);
	}
}
