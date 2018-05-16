/**
 *
 */
package xawd.jflow.iterators.examples;

import static java.util.stream.Collectors.toList;

import java.util.Iterator;
import java.util.stream.Stream;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * @author t
 *
 */
public final class ContextBinding
{
	private final GraphicsContext gc;

	public void fillPointStream(final Stream<Point> points, final Paint fill)
	{
		final Paint oldFill = gc.getFill();
		gc.setFill(fill);

		// We need to cache the first point to move to it.
		final Point first = points.limit(1).collect(toList()).get(0);

		gc.beginPath();
		gc.moveTo(first.x, first.y);

		points.forEach(p -> gc.lineTo(p.x, p.y));

		gc.fill();
		gc.closePath();
		gc.setFill(oldFill);
	}

	/**
	 *
	 */
	public ContextBinding(final GraphicsContext gc) {
		this.gc = gc;
	}

	public void fillCircle(final Point centre, final double radius, final Paint fill)
	{
		final Paint oldFill = gc.getFill();
		gc.setFill(fill);
		gc.fillOval(centre.x - radius, centre.y - radius, 2*radius, 2*radius);
		gc.setFill(oldFill);
	}

	public void fillPointSequence(final PointIterable pointProvider, final Paint fill)
	{
		fillPointSequence(pointProvider.iterateOverPoints(), fill);
	}

	public void fillPointSequence(final Iterable<Point> pointProvider, final Paint fill)
	{
		fillPointSequence(pointProvider.iterator(), fill);
	}

	public void fillPointSequence(final Iterator<Point> points, final Paint fill)
	{
		final Paint oldFill = gc.getFill();
		gc.setFill(fill);
		gc.beginPath();

		final Point start = points.next();
		gc.moveTo(start.x, start.y);

		while (points.hasNext())
		{
			final Point next = points.next();
			gc.lineTo(next.x, next.y);
		}

		gc.fill();
		gc.closePath();
		gc.setFill(oldFill);
	}

	public void strokeOpenPointSequence(final PointIterable pointProvider, final Paint stroke, final double lineWidth)
	{
		strokeOpenPointSequence(pointProvider.iterateOverPoints(), stroke, lineWidth);
	}

	public void strokeOpenPointSequence(final Iterable<Point> pointProvider, final Paint stroke, final double lineWidth)
	{
		strokeOpenPointSequence(pointProvider.iterator(), stroke, lineWidth);
	}

	public void strokeOpenPointSequence(final Iterator<Point> points, final Paint stroke, final double lineWidth)
	{
		final Paint oldStroke = gc.getStroke();
		final double oldLineWidth = gc.getLineWidth();
		gc.setLineWidth(lineWidth);
		gc.setStroke(stroke);
		gc.beginPath();
		final Point start = points.next();
		gc.moveTo(start.x, start.y);
		while (points.hasNext()) {
			final Point next = points.next();
			gc.lineTo(next.x, next.y);
		}
		gc.stroke();
		gc.closePath();
		gc.setLineWidth(oldLineWidth);
		gc.setStroke(oldStroke);
	}

	public void strokeClosedPointSequence(final PointIterable pointProvider, final Paint stroke, final double lineWidth)
	{
		strokeClosedPointSequence(pointProvider.iterateOverPoints(), stroke, lineWidth);
	}

	public void strokeClosedPointSequence(final Iterable<Point> pointProvider, final Paint stroke, final double lineWidth)
	{
		strokeClosedPointSequence(pointProvider.iterator(), stroke, lineWidth);
	}

	public void strokeClosedPointSequence(final Iterator<Point> points, final Paint stroke, final double lineWidth)
	{
		final Paint oldStroke = gc.getStroke();
		final double oldLineWidth = gc.getLineWidth();
		gc.setLineWidth(lineWidth);
		gc.setStroke(stroke);
		gc.beginPath();
		final Point start = points.next();
		gc.moveTo(start.x, start.y);
		while (points.hasNext()) {
			final Point next = points.next();
			gc.lineTo(next.x, next.y);
		}
		gc.lineTo(start.x, start.y);
		gc.stroke();
		gc.closePath();
		gc.setLineWidth(oldLineWidth);
		gc.setStroke(oldStroke);
	}

	public void fillSplineSequence(final SplineIterable curveProvider, final Paint fill)
	{
		fillSplineSequence(curveProvider.iterateOverSplines(), fill);
	}

	public void fillSplineSequence(final Iterable<Spline> curveProvider, final Paint fill)
	{
		fillSplineSequence(curveProvider.iterator(), fill);
	}

	public void fillSplineSequence(final Iterator<Spline> curves, final Paint fill)
	{
		final Paint oldFill = gc.getFill();
		gc.setFill(fill);
		gc.beginPath();
		final Spline first = curves.next();
		final Point start = first.start();
		gc.moveTo(start.x, start.y);
		first.fillTrace(gc);
		while (curves.hasNext()) {
			final Spline next = curves.next();
			next.fillTrace(gc);
		}
		gc.fill();
		gc.closePath();
		gc.setFill(oldFill);
	}

	public void strokeSplineSequence(final Iterator<Spline> curves, final Paint stroke, final double lineWidth)
	{
		final Paint oldStroke = gc.getStroke();
		final double oldLineWidth = gc.getLineWidth();
		gc.setLineWidth(lineWidth);
		gc.setStroke(stroke);
		gc.beginPath();
		final Spline first = curves.next();
		final Point start = first.start();
		gc.moveTo(start.x, start.y);
		first.strokeTrace(gc);
		while (curves.hasNext()) {
			final Spline next = curves.next();
			next.strokeTrace(gc);
		}
		gc.stroke();
		gc.closePath();
		gc.setLineWidth(oldLineWidth);
		gc.setStroke(oldStroke);
	}

	public void strokeSplineSequence(final SplineIterable curves, final Paint stroke, final double lineWidth)
	{
		strokeSplineSequence(curves.iterateOverSplines(), stroke, lineWidth);
	}

	public void strokeSplineSequence(final Iterable<Spline> curves, final Paint stroke, final double lineWidth)
	{
		strokeSplineSequence(curves.iterator(), stroke, lineWidth);
	}
}
