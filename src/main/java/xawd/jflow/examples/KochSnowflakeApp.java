/**
 *
 */
package xawd.jflow.examples;

import static xawd.jflow.geometry.impl.PointProcessors.createFillProcessor;

import java.util.function.Function;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import xawd.jflow.Flow;
import xawd.jflow.construction.Iter;
import xawd.jflow.geometry.LineSegment;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.Polygon;
import xawd.jflow.geometry.mappings.KochSnowflake;

/**
 * @author t
 *
 */
public class KochSnowflakeApp extends Application
{
	private static final int ITERATIONS = 5;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		final Canvas canvas = new Canvas(1000, 1000);
		final GraphicsContext gc = canvas.getGraphicsContext2D();

		final Polygon start = KochSnowflake.getEquilateralTriangle(600);
		start.points().forEach(p -> p.translateInPlace(200, 350));

		final Function<Flow<LineSegment>, Flow<Point>> kochIterator = lines ->
		{
			for (int i = 0; i < ITERATIONS; i++) {
				lines = lines
						.map(KochSnowflake::recurseRight)
						.flatten(x -> x);
			}
			return lines.flatten(line -> Iter.of(line.start(), line.end()));
		};

		kochIterator
		.apply(start.lines())
//		.slice(n -> n*n*n + 3*n)
		.consumeUsing(createFillProcessor(gc, Color.GREEN));

		final Scene s = new Scene(new Group(canvas), 1000, 1000);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
