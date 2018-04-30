package xawd.jflow.geometry.mappings;

import java.util.function.DoubleBinaryOperator;

import xawd.jflow.geometry.Point;

public final class Rotation {

	private Rotation() {
	}

	public static PointMap of(double cosTheta, double sinTheta, double xc, double yc)
	{
		final DoubleBinaryOperator xMap = (x, y) -> x*cosTheta
				+ (1 - cosTheta)*xc
				+ (yc - y)*sinTheta;

		final DoubleBinaryOperator yMap = (x, y) -> (x - xc)*sinTheta
				+ y*cosTheta
				+ (1 - cosTheta)*yc;

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

	public static PointMap of(double cosTheta, double sinTheta, Point centre)
	{
		return of(cosTheta, sinTheta, centre.x(), centre.y());
	}

	public static PointMap of(double theta, Point centre)
	{
		return of(Math.cos(theta), Math.sin(theta), centre);
	}
}
