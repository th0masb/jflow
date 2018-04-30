/**
 *
 */
package xawd.jflow.geometry.splines;

import javafx.scene.canvas.GraphicsContext;
import xawd.jflow.geometry.ParameterisedCurve;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.PointIterable;
import xawd.jflow.geometry.mappings.PointMap;

/**
 * @author t
 *
 */
public interface Spline extends PointIterable
{
	ParameterisedCurve parameterise();

	Spline map(PointMap mapping);

	void mapInPlace(PointMap mapping);

	void trace(GraphicsContext gc);

	Spline copy();

	Point start();

	int size();
}
