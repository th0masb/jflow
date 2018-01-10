/**
 * 
 */
package io.xyz.common.fxutils;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * @author t
 *
 */
public final class Draw 
{
	public static void filledOval(GraphicsContext gc, Bounds b, Paint p)
	{
		gc.fillOval(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
	}
}
