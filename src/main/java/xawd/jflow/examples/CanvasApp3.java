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
import xawd.jflow.geometry.impl.Polygon;
import xawd.jflow.geometry.mappings.PointMap;
import xawd.jflow.geometry.mappings.Rotation;

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

		final List<Point> ps = asList(Point.of(50, 50), Point.of(450, 50), Point.of(250, 300), Point.of(100, 200));
		gc.fillPointSequence(ps, Color.RED);

		final PointMap rotate60 = Rotation.of(Math.PI/3, Point.of(250, 250));

		final Polygon firstPolygon = Iter.of(ps).map(rotate60).build(Polygon::new);

		gc.fillPointSequence(firstPolygon, Color.GREEN);

		// Mutate polygon using the fact a PointMap is a Consumer<Point>
		firstPolygon.iterateOverPoints().forEach(rotate60);

		gc.fillPointSequence(firstPolygon, Color.BLUE);
		gc.strokeSplineSequence(firstPolygon, Color.BLACK, 5);

		final Scene s = new Scene(new Group(canvas), 500, 500);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(final String[] args)
	{
		launch(args);
	}
}
