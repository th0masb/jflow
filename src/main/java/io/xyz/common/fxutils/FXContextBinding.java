/**
 * Copyright � 2018 Lhasa Limited
 * File created: 10 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.fxutils;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.PredicateUtil.all;

import io.xyz.common.matrix.RPoint;
import io.xyz.common.splines2D.Line;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * @author ThomasB
 * @since 10 Jan 2018
 */
public final class FXContextBinding {
	private final GraphicsContext gc;

	public GraphicsContext getBoundContext()
	{
		return gc;
	}

	public void beginPath()
	{
		gc.beginPath();
	}

	public void moveTo(final RPoint p)
	{
		assert twoDimensional(p);
		gc.moveTo(p.x(), p.y());
	}

	public void lineTo(final RPoint p)
	{
		assert twoDimensional(p);
		gc.lineTo(p.x(), p.y());
	}

	public void strokeLine(final Line line, final Paint p, final double width)
	{
		final Paint currentStroke = gc.getStroke();
		final double currentWidth = gc.getLineWidth();
		gc.setStroke(p);
		gc.setLineWidth(width);
		beginPath();
		moveTo(line.from());
		lineTo(line.to());
		stroke();
		closePath();
		gc.setStroke(currentStroke);
		gc.setLineWidth(currentWidth);
	}

	public void quadraticTo(final RPoint control, final RPoint end)
	{
		assert twoDimensional(control, end);
		gc.quadraticCurveTo(control.x(), control.y(), end.x(), end.y());
	}

	public void cubicTo(final RPoint control1, final RPoint control2, final RPoint end)
	{
		assert twoDimensional(control1, control2, end);
		gc.bezierCurveTo(control1.x(), control1.y(), control2.x(), control2.y(), end.x(), end.y());
	}

	public void stroke()
	{
		gc.stroke();
	}

	public void closePath()
	{
		gc.closePath();
	}

	public void fillOval(final Bounds b, final Paint color)
	{
		final Paint currentFill = gc.getFill();
		gc.setFill(color);
		gc.fillOval(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
		gc.setFill(currentFill);
	}

	public void clearCanvas()
	{
		clearCanvas(gc.getCanvas().getBoundsInLocal());
	}

	public void clearCanvas(final Bounds b)
	{
		gc.clearRect(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
	}

	public void fillCanvas(final Paint paint)
	{
		final Paint currentFill = gc.getFill();
		fillCanvas(paint);
		;
	}

	public void fillCanvas(final Bounds b, final Paint paint)
	{
		final Paint currentFill = gc.getFill();
		gc.setFill(paint);
		gc.fillRect(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
		gc.setFill(currentFill);
	}

	private boolean twoDimensional(final RPoint... ps)
	{
		assert len(ps) > 0;
		return all(p -> p.dim() == 2, ps);
	}

	/**
	 *
	 */
	public FXContextBinding(final GraphicsContext gc)
	{
		this.gc = gc;
	}
}
/*
 * ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * ---------------------------------------------------------------------
 */