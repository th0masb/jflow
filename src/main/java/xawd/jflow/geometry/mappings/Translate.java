/**
 *
 */
package xawd.jflow.geometry.mappings;

import java.util.function.DoubleBinaryOperator;

/**
 * @author t
 *
 */
public final class Translate
{
	public static PointMap by(final double dx, final double dy)
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
}
