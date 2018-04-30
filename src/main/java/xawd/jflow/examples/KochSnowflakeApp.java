/**
 *
 */
package xawd.jflow.examples;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import xawd.jflow.Flow;
import xawd.jflow.construction.Iter;
import xawd.jflow.geometry.ContextBinding;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.impl.Polygon;
import xawd.jflow.geometry.mappings.KochSnowflake;
import xawd.jflow.geometry.mappings.PointMap;
import xawd.jflow.geometry.mappings.Rotation;
import xawd.jflow.geometry.splines.LineSegment;

/**
 * @author t
 *
 */
public class KochSnowflakeApp extends Application
{
	private static final int ITERATIONS = 6;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		final Canvas canvas = new Canvas(1000, 1000);
		final ContextBinding gc = new ContextBinding(canvas.getGraphicsContext2D());

		final Polygon start = KochSnowflake.getEquilateralTriangle(600);
		start.iterateOverPoints().forEach(p -> p.translateInPlace(200, 350));

		final Flow<Point> kochSnowflake = iterateSnowflake(ITERATIONS, start.iterateOverLines());
		final PointMap rot60 = Rotation.of(Math.PI/2, Point.of(500, 500));

		gc.fillPointSequence(kochSnowflake.map(rot60), Color.PURPLE);//.slice(n -> n*n*n + 3*n)

		final Scene s = new Scene(new Group(canvas), 1000, 1000);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	private Flow<Point> iterateSnowflake(int iterationCount, Flow<LineSegment> source)
	{
		for (int i = 0; i < iterationCount; i++) {
			source = source.flatten(KochSnowflake::recurseRight);
		}
		return source.flatten(line -> Iter.of(line.start(), line.end()));
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
