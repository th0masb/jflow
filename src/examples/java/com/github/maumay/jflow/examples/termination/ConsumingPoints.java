/**
 * 
 */
package com.github.maumay.jflow.examples.termination;

import com.github.maumay.jflow.examples.termination.Base.Point;
import com.github.maumay.jflow.iterators.factories.Iter;
import com.github.maumay.jflow.iterators.termination.IteratorConsumer;
import com.github.maumay.jflow.vec.Vec;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author thomasb
 *
 */
public final class ConsumingPoints
{
	static void drawPolygon(GraphicsContext gc, Iterable<? extends Point> source)
	{
		Iter.wrap(source).consume(drawPolygon(gc));
	}

	static <P extends Point> IteratorConsumer<P> drawPolygon(GraphicsContext gc)
	{
		return iter -> {
			gc.beginPath();
			if (iter.hasNext()) {
				Point first = iter.next();
				gc.moveTo(first.x, first.y);
			}
			while (iter.hasNext()) {
				Point next = iter.next();
				gc.lineTo(next.x, next.y);
			}
			gc.closePath();
			gc.fill();
		};
	}

	public static void main(String[] args)
	{
		Vec<Point> points = Vec.of(new Point(0, 0), new Point(1, 0), new Point(1, 1));
		GraphicsContext gc = null; // Dummy

		points.iter().map(p -> new Point(p.x + 2, p.y - 3)).consume(drawPolygon(gc));

		drawPolygon(gc, points);
		drawPolygon(gc, points.toList());
		// etc etc
	}
}
