/**
 * 
 */
package io.xyz.common.demo;

import static io.xyz.common.funcutils.MapUtil.doubleRange;
import static io.xyz.common.funcutils.MapUtil.objRange;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import io.xyz.common.fxutils.DrawingCanvas;
import io.xyz.common.fxutils.FXContextBinding;
import io.xyz.common.matrix.RPoint;
import io.xyz.common.splines2D.Line;
import io.xyz.common.splines2D.SplineType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author t
 *
 *         Just cts functions for the moment!
 */
public final class FunctionDemo extends Application {

	private static final int SIZE = 600;
	private static final DoubleUnaryOperator f = x -> 2 * Math.sin(x);// 0.07 * (x * x * x - 4 * x * x + 5);
	private static final double MAX_AXIS_VAL = 10;
	private static final int STEPS = 40;

	private DrawingCanvas canvas;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage primaryStage) throws Exception
	{
		canvas = new DrawingCanvas(SIZE, SIZE);
		drawAxes();
		drawFunction();

		final Scene scene = new Scene(canvas, SIZE, SIZE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void drawFunction()
	{
		final FXContextBinding gc = canvas.getMiddleGC();
		final Color plotColor = Color.RED;
		final double plotWidth = 3;
		gc.getBoundContext().setStroke(plotColor);
		gc.getBoundContext().setLineWidth(plotWidth);

		final RPoint origin = getOrigin();
		final double scale = SIZE / (2 * MAX_AXIS_VAL);
		final DoubleFunction<RPoint> mapper = x -> RPoint.of(origin.x() + scale * x, origin.y() - scale * f.applyAsDouble(x));
		final List<RPoint> plotPoints = collect(objRange(mapper, getFunctionDomain()));

		// for (int i = 0; i < len(plotPoints); i += 1) {
		// gc.fillOval(FXRenderingUtils.getCircumscribedSquare(plotPoints.get(i), 2),
		// Color.RED);
		// }
		SplineType.C1_CURVE.constructFrom(plotPoints).draw2D(gc);
	}

	private double[] getFunctionDomain()
	{
		return doubleRange(-MAX_AXIS_VAL, MAX_AXIS_VAL, 2 * MAX_AXIS_VAL / STEPS).toArray();
	}

	private RPoint getOrigin()
	{
		return RPoint.of(SIZE / 2.0, SIZE / 2.0);
	}

	private void drawAxes()
	{
		final RPoint origin = getOrigin();

		final Color axisColor = Color.gray(0.5);
		final double axisWidth = 2;
		final FXContextBinding gc = canvas.getBackingGC();
		gc.strokeLine(Line.between(RPoint.of(0, origin.y()), RPoint.of(SIZE, origin.y())), axisColor, axisWidth);
		gc.strokeLine(Line.between(RPoint.of(origin.x(), 0), RPoint.of(origin.x(), SIZE)), axisColor, axisWidth);
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		launch(args);
	}

}
