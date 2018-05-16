/**
 *
 */
package xawd.jflow.iterators.examples;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.construction.Iterate;

/**
 * @author t
 *
 */
public class KochSnowflakeApp extends Application
{
	private static final int ITERATIONS = 8;

	@Override
	public void start(final Stage primaryStage) throws Exception
	{
		final Canvas canvas = new Canvas(1000, 1000);
		final ContextBinding gc = new ContextBinding(canvas.getGraphicsContext2D());

//		final Polygon start = Translation.by(200, 350).mapPolygon(KochSnowflake.getEquilateralTriangle(600));
//
//		final Flow<Point> kochSnowflake = iterateSnowflake(ITERATIONS, start.iterateOverLines());
//		final AffinePointMapping rot60 = Rotation.of(Math.PI/2, new Point(500, 500));
//
//		gc.fillPointSequence(kochSnowflake.map(rot60::mapPoint), Color.PURPLE);//.slice(n -> n*n*n + 3*n)

		final Scene s = new Scene(new Group(canvas), 1000, 1000);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	private Flow<Point> iterateSnowflake(final int iterationCount, Flow<LineSegment> source)
	{
		for (int i = 0; i < iterationCount; i++) {
			source = source.flatten(KochSnowflake::recurseRight);
		}
		return source.flatten(line -> Iterate.over(line.start(), line.end()));
	}

	public static void main(final String[] args)
	{
		launch(args);
	}
}
