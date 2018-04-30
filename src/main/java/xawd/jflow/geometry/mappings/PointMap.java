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
}
