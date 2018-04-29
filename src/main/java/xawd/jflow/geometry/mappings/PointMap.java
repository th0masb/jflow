/**
 *
 */
package xawd.jflow.geometry.mappings;

import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.impl.PointImpl;

/**
 * @author t
 *
 */
public interface PointMap extends Function<Point, Point>, Consumer<Point>
{
	DoubleBinaryOperator getX();

	DoubleBinaryOperator getY();

	default void mapInPlace(Point p)
	{
		p.set(this);
	}

	@Override
	default Point apply(Point p)
	{
		final DoubleBinaryOperator xMap = getX(), yMap = getY();
		return new PointImpl(
				xMap.applyAsDouble(p.x(), p.y()),
				yMap.applyAsDouble(p.x(), p.y()));
	}

	@Deprecated
	@Override
	default void accept(Point p)
	{
		mapInPlace(p);
	}

	static PointMap translationOf(final double dx, final double dy)
	{
		return new PointMap() {
			@Override
			public DoubleBinaryOperator getX() {
				return (x, y) -> x + dx;
			}
			@Override
			public DoubleBinaryOperator getY() {
				return (x, y) -> y + dy;
			}
		};
	}

	static PointMap rotationOf(double cosTheta, double sinTheta, Point centre)
	{
		final DoubleBinaryOperator xMap = (x, y) -> x*cosTheta
				+ (1 - cosTheta)*centre.x()
				+ (centre.y() - y)*sinTheta;

		final DoubleBinaryOperator yMap = (x, y) -> (x - centre.x())*sinTheta
				+ y*cosTheta
				+ (1 - cosTheta)*centre.y();

		return new PointMap() {
			@Override
			public DoubleBinaryOperator getY() {
				return yMap;
			}
			@Override
			public DoubleBinaryOperator getX() {
				return xMap;
			}
		};
	}

	static PointMap rotationOf(double theta, Point centre)
	{
		return rotationOf(Math.cos(theta), Math.sin(theta), centre);
	}
}
