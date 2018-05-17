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
public final class LineSegment extends AbstractSplineSegment
{
	public LineSegment(final Point start, final Point end) {
		super(start, end);
	}

	@Override
	public ParameterisedCurve parameterise()
	{
		final Point start = head(constituentPoints);
		final Point end = tail(constituentPoints);
		return ParameterisedCurve.straightLine(start, end);
	}

	@Override
	public LineSegment map(final PointMapping mapping)
	{
		final Point mappedStart = mapping.mapPoint(head(constituentPoints));
		final Point mappedEnd = mapping.mapPoint(tail(constituentPoints));
		return new LineSegment(mappedStart, mappedEnd);
	}

	@Override
	public void fillTrace(final GraphicsContext gc)
	{
		final Point end = tail(constituentPoints);
		gc.lineTo(end.x, end.y);
	}

	@Override
	public void strokeTrace(final GraphicsContext gc)
	{
		final Point start = head(constituentPoints);
		final Point end = tail(constituentPoints);
		gc.moveTo(start.x, start.y);
		gc.lineTo(end.x, end.y);
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

	public Point interpolate(final double t)
	{
		return new Point(
				(1 - t)*start().x + t*end().x,
				(1 - t)*start().y + t*end().y);
	}
}
