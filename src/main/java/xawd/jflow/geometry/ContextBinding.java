/**
 *
 */
package xawd.jflow.geometry;

import java.util.Iterator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import xawd.jflow.geometry.splines.Spline;

/**
 * @author t
 *
 */
public final class ContextBinding
{
	private final GraphicsContext gc;
	/**
	 *
	 */
	public ContextBinding(final GraphicsContext gc) {
		this.gc = gc;
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
		gc.moveTo(start.x(), start.y());
		while (points.hasNext()) {
			final Point next = points.next();
			gc.lineTo(next.x(), next.y());
		}
		gc.fill();
		gc.closePath();
		gc.setFill(oldFill);
	}

	public void fillSplineSequence(final SplineIterable curveProvider, final Paint fill)
	{
		fillSplineSequence(curveProvider.iterateOverCurves(), fill);
	}

	public void fillSplineSequence(final LineIterable curveProvider, final Paint fill)
	{
		fillSplineSequence(curveProvider.iterateOverLines(), fill);
	}

	public void fillSplineSequence(final Iterable<? extends Spline> curveProvider, final Paint fill)
	{
		fillSplineSequence(curveProvider.iterator(), fill);
	}

	public void fillSplineSequence(final Iterator<? extends Spline> curves, final Paint fill)
	{
		final Paint oldFill = gc.getFill();
		gc.setFill(fill);
		gc.beginPath();
		final Spline first = curves.next();
		final Point start = first.start();
		gc.moveTo(start.x(), start.y());
		first.trace(gc);
		while (curves.hasNext()) {
			final Spline next = curves.next();
			next.trace(gc);
		}
		gc.fill();
		gc.closePath();
		gc.setFill(oldFill);
	}

	public void strokeSplineSequence(final Iterator<? extends Spline> curves, final Paint stroke, final double lineWidth)
	{
		final Paint oldStroke = gc.getStroke();
		final double oldLineWidth = gc.getLineWidth();
		gc.setLineWidth(lineWidth);
		gc.setStroke(stroke);
		gc.beginPath();
		final Spline first = curves.next();
		final Point start = first.start();
		gc.moveTo(start.x(), start.y());
		first.trace(gc);
		while (curves.hasNext()) {
			final Spline next = curves.next();
			next.trace(gc);
		}
		gc.stroke();
		gc.closePath();
		gc.setLineWidth(oldLineWidth);
		gc.setStroke(oldStroke);
	}

	public void strokeSplineSequence(final SplineIterable curves, final Paint stroke, final double lineWidth)
	{
		strokeSplineSequence(curves.iterateOverCurves(), stroke, lineWidth);
	}

	public void strokeSplineSequence(final LineIterable curves, final Paint stroke, final double lineWidth)
	{
		strokeSplineSequence(curves.iterateOverLines(), stroke, lineWidth);
	}

	public void strokeSplineSequence(final Iterable<? extends Spline> curves, final Paint stroke, final double lineWidth)
	{
		strokeSplineSequence(curves.iterator(), stroke, lineWidth);
	}
}
