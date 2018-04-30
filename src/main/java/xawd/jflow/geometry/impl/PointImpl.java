/**
 *
 */
package xawd.jflow.geometry.impl;

import xawd.jflow.geometry.Point;

/**
 * @author t
 *
 */
public class PointImpl implements Point {

	private double x = 0, y = 0;

	public PointImpl(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public PointImpl() {
	}

	@Override
	public double x() {
		return x;
	}

	@Override
	public double y() {
		return y;
	}

	@Override
	public void setX(double newX) {
		x = newX;
	}

	@Override
	public void setY(double newY) {
		y = newY;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(").append(x).append(", ").append(y).append(")").toString();
	}

	@Override
	public Point copy() {
		return new PointImpl(x, y);
	}
}
