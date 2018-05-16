/**
 *
 */
package xawd.jflow.iterators.examples;

import java.util.function.DoubleFunction;

/**
 * @author t
 */
@FunctionalInterface
public interface ParameterisedCurve extends DoubleFunction<Point>
{
	static ParameterisedCurve straightLine(final Point p1, final Point p2)
	{
		return t -> {
			return new Point((1 - t) * p1.x + t * p2.x, (1 - t) * p1.y + t * p2.y);
		};
	}

	static ParameterisedCurve quadLine(final Point p1, final Point c, final Point p2)
	{
		return t -> {
			final double s = 1 - t;
			return new Point(
					s * s * p1.x + 2 * t * s * c.x + t * t * p2.x,
					s * s * p1.y + 2 * t * s * c.y + t * t * p2.y);
		};
	}

	static ParameterisedCurve cubicLine(final Point p1, final Point c1, final Point c2, final Point p2)
	{
		return t -> {
			final double s = 1 - t;
			final double a = s * s * s, b = 3 * s * s * t, c = 3 * s * t * t, d = t * t * t;
			return new Point(
					a*p1.x + b*c1.x + c*c2.x + d*p2.x,
					a*p1.y + b*c1.y + c*c2.y + d*p2.y);
		};
	}
}
