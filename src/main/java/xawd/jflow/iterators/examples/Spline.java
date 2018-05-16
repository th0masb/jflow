/**
 *
 */
package xawd.jflow.iterators.examples;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author t
 *
 */
public interface Spline extends PointIterable
{
	ParameterisedCurve parameterise();

	Spline map(PointMapping mapping);

	void fillTrace(GraphicsContext gc);

	void strokeTrace(GraphicsContext gc);

	Point start();

	int size();
}
