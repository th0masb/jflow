package io.xyz.common.geometryutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.xyz.common.matrix.RPoint;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class FXRenderingUtils {
	/**
	 * Renders the supplied text within the given bounds. We can improve the
	 * generality of this method to take into account arbitrary line spacing and
	 * insets within the render bounds.
	 * 
	 * @param gc
	 * @param renderBounds
	 * @param linesToRender
	 */
	public static void renderText(final GraphicsContext gc, final Bounds renderBounds, final List<String> linesToRender)
	{
		final List<String> copy = new ArrayList<>(linesToRender);
		Collections.sort(copy, (a, b) -> -Double.compare(getTextWidth(a, 10), getTextWidth(b, 10)));
		final String longestLine = copy.get(0);
		final int nLines = linesToRender.size();

		// Choice of number is arbitrary, just need aspect ratio
		final double aspectRatio = 20 / getTextWidth(longestLine, 20);
		double requiredHeight = aspectRatio * (8 * renderBounds.getWidth() / 9);

		final double blockSizeAtThisHeight = nLines * requiredHeight + (nLines - 1) * requiredHeight / 3;

		requiredHeight = blockSizeAtThisHeight <= renderBounds.getHeight()? requiredHeight : renderBounds.getHeight() / ((4 * nLines - 1) / 3);

		gc.setFont(Font.font(requiredHeight));
		gc.setLineWidth(0.5);

		final double lineSpacing = requiredHeight / 3;
		final double textBlockHeight = (linesToRender.size() - 1) * (requiredHeight + lineSpacing) + requiredHeight;
		final double blockShift = renderBounds.getHeight() / 2 - textBlockHeight / 2;

		for (int i = 0; i < linesToRender.size(); i++) {
			final String line = linesToRender.get(i);
			final double lineWidth = getTextWidth(line, requiredHeight);
			final double y = renderBounds.getMinY() + requiredHeight + blockShift + i * requiredHeight + (i - 1) * lineSpacing;
			final double x = renderBounds.getMinX() + (renderBounds.getWidth() - lineWidth) / 2;
			gc.fillText(line, x, y);
		}
	}

	public static double getReqTextHeight(final String text, final double widthConstraint, final String fontFamily, final double maxHeight)
	{
		double height = maxHeight;
		double width = getTextWidth(text, fontFamily, height);
		while (width > widthConstraint && height > 0) {
			width = getTextWidth(text, fontFamily, --height);
		}
		return Math.max(height, 0);
	}

	/**
	 * Returns the rendering width of the text in the default font at the provided
	 * height
	 * 
	 * @param text
	 * @param textHeight
	 * @return
	 */
	public static double getTextWidth(final String text, final double textHeight)
	{
		final Text t = new Text(text);
		t.setBoundsType(TextBoundsType.VISUAL);
		t.setFont(Font.font(textHeight));
		return t.getLayoutBounds().getWidth();
	}

	/**
	 * Returns the rendering width of the text in the default font at the provided
	 * height
	 * 
	 * @param text
	 * @param textHeight
	 * @return
	 */
	public static double getTextWidth(final String text, final String textID, final double textHeight)
	{
		final Text t = new Text(text);
		t.setFont(Font.font(textID, textHeight));
		return t.getLayoutBounds().getWidth();
	}

	/**
	 * Returns the rendering width of the text in the default font at the provided
	 * height
	 * 
	 * @param text
	 * @param textHeight
	 * @return
	 */
	public static double getTextWidth(final String text, final Font font)
	{
		final Text t = new Text(text);
		t.setFont(font);
		return t.getLayoutBounds().getWidth();
	}

	// public static void renderText(final GraphicsContext gc, final Bounds
	// renderBounds, final List<String> linesToRender, Insets insets)

	public static void fillRect(final GraphicsContext gc, final Bounds b, final Paint p)
	{
		gc.setFill(p);
		gc.fillRect(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
	}

	public static void fillRoundRect(final GraphicsContext gc, final Bounds b, final Paint p, final double arcWidth, final double arcHeight)
	{
		gc.setFill(p);
		gc.fillRoundRect(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight(), arcWidth, arcHeight);
	}

	public static void strokeRect(final GraphicsContext gc, final Bounds b, final Paint p, final double lineWidth)
	{
		gc.setStroke(p);
		gc.setLineWidth(lineWidth);
		gc.strokeRect(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
	}

	public static void fillOval(final GraphicsContext gc, final Bounds b, final Paint p)
	{
		gc.setFill(p);
		gc.fillOval(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
	}

	public static void strokeOval(final GraphicsContext gc, final Bounds b, final Paint p, final double lineWidth)
	{
		gc.setStroke(p);
		gc.setLineWidth(lineWidth);
		gc.strokeOval(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
	}

	public static Bounds getInscribedSquare(final Point2D centre, final double radius)
	{
		final double halfSqSideLength = 0.5 * Math.sqrt(2) * radius; // radius * sin(pi/4)
		return new BoundingBox(centre.getX() - halfSqSideLength, centre.getY() - halfSqSideLength, 2 * halfSqSideLength, 2 * halfSqSideLength);
	}

	public static Bounds getCircumscribedSquare(final Point2D centre, final double radius)
	{
		return new BoundingBox(centre.getX() - radius, centre.getY() - radius, 2 * radius, 2 * radius);
	}

	public static Bounds getCircumscribedSquare(final RPoint centre, final double radius)
	{
		return new BoundingBox(centre.x() - radius, centre.y() - radius, 2 * radius, 2 * radius);
	}

	/**
	 * Returns an instance of {@link Bounds} which has sideLength =
	 * paramBounds.sideLength * sideLengthPercentage centred at the same point as
	 * paramBounds.
	 * 
	 * @param outer
	 * @param sideLengthPercentage
	 * @return
	 */
	public static Bounds getInnerBounds(final Bounds outer, final double sideLengthPercentage)
	{
		final double widthDifference = outer.getWidth() - outer.getWidth() * sideLengthPercentage;
		final double heightDifference = outer.getHeight() - outer.getHeight() * sideLengthPercentage;

		final double adjustedWidth = outer.getWidth() * sideLengthPercentage,
				adjustedHeight = outer.getHeight() * sideLengthPercentage;
		final double adjustedX = outer.getMinX() + widthDifference / 2,
				adjustedY = outer.getMinY() + heightDifference / 2;

		return new BoundingBox(adjustedX, adjustedY, adjustedWidth, adjustedHeight);
	}

	public static Bounds scale(final Bounds b, final double sf)
	{
		final double midX = b.getMinX() + b.getWidth() / 2, midY = b.getMinY() + b.getHeight() / 2;
		final double newWidth = sf * b.getWidth(), newHeight = sf * b.getHeight();
		return new BoundingBox(midX - newWidth / 2, midY - newHeight / 2, newWidth, newHeight);
	}

	public static void clearCanvas(final GraphicsContext gc)
	{
		final double cw = gc.getCanvas().getWidth(), ch = gc.getCanvas().getHeight();
		gc.clearRect(0, 0, cw, ch);
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