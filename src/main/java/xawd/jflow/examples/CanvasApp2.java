/**
 *
 */
package xawd.jflow.examples;

import static java.util.Arrays.asList;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import xawd.jflow.construction.Iter;
import xawd.jflow.geometry.ContextBinding;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.impl.PointImpl;
import xawd.jflow.geometry.impl.Polygon;
import xawd.jflow.geometry.impl.Splinegon;
import xawd.jflow.geometry.splines.QuadraticCurve;
import xawd.jflow.geometry.splines.Spline;

/**
 * @author t
 *
 */
public class CanvasApp2 extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		final Canvas canvas = new Canvas(500, 500);
		final ContextBinding gc = new ContextBinding(canvas.getGraphicsContext2D());

		final List<Point> ps = asList(
				new PointImpl(50, 50),
				new PointImpl(450, 50),
				new PointImpl(250, 300),
				new PointImpl(100, 200));

		final List<Point> cs = asList(
				new PointImpl(100, 0),
				new PointImpl(350, 150),
				new PointImpl(300, 150),
				new PointImpl(100, 75));


		final List<Spline> curves = asList(
				new QuadraticCurve(ps.get(0), cs.get(0), ps.get(1)),
				new QuadraticCurve(ps.get(1), cs.get(1), ps.get(2)),
				new QuadraticCurve(ps.get(2), cs.get(2), ps.get(3)),
				new QuadraticCurve(ps.get(3), cs.get(3), ps.get(0))
				);

		final Polygon pgon = Iter.of(curves)
				.flatten(spline -> spline.iterateOverPoints())
				.build(Polygon::new);

		final Splinegon sgon = Iter.of(curves).build(Splinegon::new);
		gc.fillSplineSequence(sgon, Color.DODGERBLUE);
		gc.fillPointSequence(pgon, Color.GREEN);
		gc.strokeSplineSequence(sgon, Color.INDIANRED, 5);

		gc.strokeSplineSequence(curves.subList(0, 2), Color.PURPLE, 10);

		final Scene s = new Scene(new Group(canvas), 500, 500);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
