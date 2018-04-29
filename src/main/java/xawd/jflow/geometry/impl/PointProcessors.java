/**
 *
 */
package xawd.jflow.geometry.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import xawd.jflow.geometry.Point;

/**
 * @author t
 *
 */
public final class PointProcessors
{
	private PointProcessors() {}

	public static PointProcessor createClosedStrokeOf(GraphicsContext gc, Paint stroke, double width)
	{
		return points -> {
			final Paint oldStroke = gc.getStroke();
			final double oldWidth = gc.getLineWidth();
			gc.setStroke(stroke);
			gc.setLineWidth(width);
			gc.beginPath();
			final Point start = points.next();
			gc.moveTo(start.x(), start.y());
			while (points.hasNext()) {
				final Point next = points.next();
				gc.lineTo(next.x(), next.y());
			}
			gc.lineTo(start.x(), start.y());
			gc.stroke();
			gc.closePath();
			gc.setLineWidth(oldWidth);
			gc.setStroke(oldStroke);
		};
	}

	public static PointProcessor createFillProcessor(GraphicsContext gc, Paint fill)
	{
		return points -> {
			final Paint oldFill = gc.getFill();
			gc.setFill(fill);
			gc.beginPath();
			final Point start = points.next();
			gc.moveTo(start.x(), start.y());
			while (points.hasNext()) {
				final Point next = points.next();
				gc.lineTo(next.x(), next.y());
			}
			gc.lineTo(start.x(), start.y());
			gc.fill();
			gc.closePath();
			gc.setFill(oldFill);
		};
	}

	public static PointProcessor createFillProcessor(GraphicsContext gc)
	{
		return createFillProcessor(gc, Color.BLACK);
	}
}
