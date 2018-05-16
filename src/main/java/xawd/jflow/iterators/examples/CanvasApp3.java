/**
 *
 */
package xawd.jflow.iterators.examples;

import static java.util.Arrays.asList;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import xawd.jflow.iterators.construction.Iterate;

/**
 * @author t
 *
 */
public class CanvasApp3 extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage primaryStage) throws Exception
	{
		final Canvas canvas = new Canvas(500, 500);
		final ContextBinding gc = new ContextBinding(canvas.getGraphicsContext2D());

		final List<Point> ps = asList(
				new Point(50, 50),
				new Point(450, 50),
				new Point(250, 300),
				new Point(100, 200));

		final List<Point> cs = asList(
				new Point(100, 0),
				new Point(350, 150),
				new Point(300, 150),
				new Point(100, 75));


		final List<Spline> curves = asList(
				new QuadraticCurve(ps.get(0), cs.get(0), ps.get(1)),
				new QuadraticCurve(ps.get(1), cs.get(1), ps.get(2)),
				new QuadraticCurve(ps.get(2), cs.get(2), ps.get(3)),
				new QuadraticCurve(ps.get(3), cs.get(3), ps.get(0))
				);

		// curves is some list of quadratic bezier curves.
		final Splinegon test = Iterate.over(curves).build(Splinegon::new);

		gc.fillSplineSequence(test, Color.DODGERBLUE);
		test.iterateOverPoints().forEach(p -> gc.fillCircle(p, 6, Color.GREEN));


//		gc.strokeClosedPointSequence(test, Color.ORANGE, 3);

		final Scene s = new Scene(new Group(canvas), 500, 500);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(final String[] args)
	{
		launch(args);
	}
}
