/**
 * Copyright © 2018 Lhasa Limited
 * File created: 11 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.demo;

import java.util.List;

import com.google.common.collect.ImmutableList;

import io.xyz.common.fxutils.DrawingCanvas;
import io.xyz.common.fxutils.FXContextBinding;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.matrix.RPoint;
import io.xyz.common.splines2D.ISpline;
import io.xyz.common.splines2D.SplineType;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author ThomasB
 * @since 11 Jan 2018
 */
public final class SplineDemo extends Application
{
	private static final int CANVAS_SIZE = 850;

	private DrawingCanvas canvas;


	@Override
	public void start(final Stage stage) throws Exception
	{
		canvas = new DrawingCanvas(CANVAS_SIZE, CANVAS_SIZE);
		final Scene scene = new Scene(canvas, CANVAS_SIZE, CANVAS_SIZE);
		stage.setScene(scene);
		initCanvas();
		stage.show();
	}

	private void initCanvas()
	{
		final FXContextBinding gc = initDrawContext();
		final List<RPoint> ps = getKnotPoints();
		final ISpline spline = SplineType.C1_CURVE.constructFrom(ps);
		spline.draw2D(gc, PointTransform.identity(2));

		//		ps.stream().forEach(p -> {
		//			gc.fillOval(FXRenderingUtils.getCircumscribedSquare(convert(p), 10), Color.RED);
		//		});

		//		spline.getConstituentSegments().stream().forEach(seg -> {
		//			final QuadraticBezier b = (QuadraticBezier) seg;
		//			gc.fillOval(FXRenderingUtils.getCircumscribedSquare(convert(b.control()), 7), Color.BLUE);
		//		});
	}

	private Point2D convert(final RPoint p)
	{
		assert p.dim() == 2;
		return new Point2D(p.x(), p.y());
	}

	private FXContextBinding initDrawContext()
	{
		canvas.getMiddleGC().getBoundContext().setLineWidth(3);
		return canvas.getMiddleGC();
	}

	private List<RPoint> getKnotPoints()
	{
		//		return ImmutableList.of(RPoint.of(50, 60), RPoint.of(100, 50), RPoint.of(600, 600), RPoint.of(500, 800));
		//		return ImmutableList.of(RPoint.of(100, 300), RPoint.of(300, 100), RPoint.of(600, 300), RPoint.of(500, 800));
		//		return ImmutableList.of(RPoint.of(100, 300), RPoint.of(300, 100), RPoint.of(400, 200), RPoint.of(500, 300));
		return ImmutableList.of(RPoint.of(100, 100), RPoint.of(300, 200), RPoint.of(100, 300), RPoint.of(300, 400), RPoint.of(600, 200), RPoint.of(100, 100));
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		launch(args);
		//		final QuadraticBezier qb = new QuadraticBezier(RPoint.of(0, 2), RPoint.of(4, 4), RPoint.of(2, 0));
		//		System.out.println(qb.parameterise());
		//		System.out.println(asString(doubleRange(0, 1 + EPSILON, 1.0 / 10).toArray()));
	}

}

/* ---------------------------------------------------------------------*
 * This software is the confidential and proprietary
 * information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS
 * ---
 * No part of this confidential information shall be disclosed
 * and it shall be used only in accordance with the terms of a
 * written license agreement entered into by holder of the information
 * with LHASA Ltd.
 * --------------------------------------------------------------------- */