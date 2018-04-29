/**
 *
 */
package xawd.jflow.geometry;

import java.util.function.DoubleBinaryOperator;

import xawd.jflow.geometry.mappings.PointMap;

/**
 * @author t
 *
 */
public interface Point
{
	double x();

	double y();

	void setX(double newX);

	void setY(double newY);

	default void set(DoubleBinaryOperator xMap, DoubleBinaryOperator yMap)
	{
		final double tmpX = x(), tmpY = y();
		setX(xMap.applyAsDouble(tmpX, tmpY));
		setY(yMap.applyAsDouble(tmpX, tmpY));
	}

	default void set(PointMap mapping)
	{
		set(mapping.getX(), mapping.getY());
	}
}
