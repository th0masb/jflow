/**
 *
 */
package xawd.jflow.iterators.examples;

import java.util.function.DoubleBinaryOperator;

/**
 * @author t
 *
 */
public final class Translation
{
	public static ScalingPointMapping by(final double dx, final double dy)
	{
		return new ScalingPointMapping() {
			@Override
			public DoubleBinaryOperator getX() {
				return (x, y) -> x + dx;
			}
			@Override
			public DoubleBinaryOperator getY() {
				return (x, y) -> y + dy;
			}
			@Override
			public double getDistanceScalingFactor() {
				return 1;
			}
		};
	}
}
