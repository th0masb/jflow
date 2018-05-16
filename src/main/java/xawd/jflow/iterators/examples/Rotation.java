package xawd.jflow.iterators.examples;

import java.util.function.DoubleBinaryOperator;

public final class Rotation {

	private Rotation() {
	}

	public static ScalingPointMapping of(final double cosTheta, final double sinTheta, final double xc, final double yc)
	{
		final DoubleBinaryOperator xMap = (x, y) ->
				x*cosTheta
				+ (1 - cosTheta)*xc
				+ (yc - y)*sinTheta;

		final DoubleBinaryOperator yMap = (x, y) ->
				(x - xc)*sinTheta
				+ y*cosTheta
				+ (1 - cosTheta)*yc;

		return new ScalingPointMapping() {
			@Override
			public DoubleBinaryOperator getY() {
				return yMap;
			}
			@Override
			public DoubleBinaryOperator getX() {
				return xMap;
			}
			@Override
			public double getDistanceScalingFactor() {
				return 1;
			}
		};
	}

	public static ScalingPointMapping of(final double theta, final double xc, final double yc)
	{
		return of(Math.cos(theta), Math.sin(theta), xc, yc);
	}

	public static ScalingPointMapping of(final double cosTheta, final double sinTheta, final Point centre)
	{
		return of(cosTheta, sinTheta, centre.x, centre.y);
	}

	public static ScalingPointMapping of(final double theta, final Point centre)
	{
		return of(Math.cos(theta), Math.sin(theta), centre);
	}
}
