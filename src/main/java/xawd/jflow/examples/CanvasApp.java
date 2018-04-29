/**
 *
 */
package xawd.jflow.examples;

import static java.util.Arrays.asList;
import static xawd.jflow.geometry.impl.PointProcessors.createFillProcessor;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import xawd.jflow.construction.Iter;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.Polygon;
import xawd.jflow.geometry.impl.PointImpl;
import xawd.jflow.geometry.impl.PointProcessor;
import xawd.jflow.geometry.impl.PolygonImpl;
import xawd.jflow.geometry.mappings.PointMap;

/**
 * @author t
 *
 */
public class CanvasApp extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		final Canvas canvas = new Canvas(500, 500);
		final PointProcessor redPolygonFill = createFillProcessor(canvas.getGraphicsContext2D(), Color.INDIANRED);
		final PointProcessor bluePolygonFill = createFillProcessor(canvas.getGraphicsContext2D(), Color.DODGERBLUE);
		final PointProcessor greenPolygonFill = createFillProcessor(canvas.getGraphicsContext2D(), Color.DARKGREEN);

		final List<Point> ps = asList(
				new PointImpl(50, 50),
				new PointImpl(450, 50),
				new PointImpl(250, 300),
				new PointImpl(100, 200));

		Iter.of(ps).consumeUsing(redPolygonFill);

		final PointMap translation = PointMap.translationOf(0, 50);
		Iter.of(ps).map(translation).consumeUsing(bluePolygonFill);

		final Polygon mapped = Iter.of(ps)
				.map(translation)
				.build(PolygonImpl::fromPoints);

		final PointMap rotation = PointMap.rotationOf(Math.PI/2, Point.of(250, 250));

		mapped.points().forEach(rotation::mapInPlace);
		mapped.points().consumeUsing(greenPolygonFill);

		final Scene s = new Scene(new Group(canvas), 500, 500);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
