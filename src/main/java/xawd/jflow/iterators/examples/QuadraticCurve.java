/**
 *
 */
package xawd.jflow.iterators.examples;

import static xawd.jflow.utilities.CollectionUtil.head;
import static xawd.jflow.utilities.CollectionUtil.tail;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author t
 *
 */
public final class QuadraticCurve extends AbstractSplineSegment {

	public QuadraticCurve(final Point from, final Point control, final Point to) {
		super(from, control, to);
	}

	@Override
	public ParameterisedCurve parameterise()
	{
		final Point start = head(constituentPoints);
		final Point control = constituentPoints.get(1);
		final Point end = tail(constituentPoints);
		return ParameterisedCurve.quadLine(start, control, end);
	}

	@Override
	public QuadraticCurve map(final PointMapping mapping)
	{
		final Point mappedStart = mapping.mapPoint(head(constituentPoints));
		final Point mappedControl = mapping.mapPoint(constituentPoints.get(1));
		final Point mappedEnd = mapping.mapPoint(tail(constituentPoints));
		return new QuadraticCurve(mappedStart, mappedControl, mappedEnd);
	}

	@Override
	public void fillTrace(final GraphicsContext gc)
	{
		final Point control = constituentPoints.get(1);
		final Point end = tail(constituentPoints);
		gc.quadraticCurveTo(control.x, control.y, end.x, end.y);
	}

	@Override
	public void strokeTrace(final GraphicsContext gc)
	{
		final Point start = head(constituentPoints);
		final Point control = constituentPoints.get(1);
		final Point end = tail(constituentPoints);
		gc.moveTo(start.x, start.y);
		gc.quadraticCurveTo(control.x, control.y, end.x, end.y);
	}

}
