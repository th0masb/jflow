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
	public ContextBinding(GraphicsContext gc) {
		this.gc = gc;
	}

	public void fillPointSequence(PointIterable pointProvider, Paint fill)
	{
		fillPointSequence(pointProvider.iterateOverPoints(), fill);
	}

	public void fillPointSequence(Iterable<Point> pointProvider, Paint fill)
	{
		fillPointSequence(pointProvider.iterator(), fill);
	}

	public void fillPointSequence(Iterator<Point> points, Paint fill)
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

	public void fillSplineSequence(SplineIterable curveProvider, Paint fill)
	{
		fillSplineSequence(curveProvider.iterateOverCurves(), fill);
	}

	public void fillSplineSequence(Iterable<Spline> curveProvider, Paint fill)
	{
		fillSplineSequence(curveProvider.iterator(), fill);
	}

	public void fillSplineSequence(Iterator<Spline> curves, Paint fill)
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

	public void strokeSplineSequence(Iterator<Spline> curves, Paint stroke, double lineWidth)
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

	public void strokeSplineSequence(SplineIterable curves, Paint stroke, double lineWidth)
	{
		strokeSplineSequence(curves.iterateOverCurves(), stroke, lineWidth);
	}

	public void strokeSplineSequence(Iterable<Spline> curves, Paint stroke, double lineWidth)
	{
		strokeSplineSequence(curves.iterator(), stroke, lineWidth);
	}
}
