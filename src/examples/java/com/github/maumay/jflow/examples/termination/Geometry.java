/**
 * 
 */
package com.github.maumay.jflow.examples.termination;

/**
 * Problem: I would like to be able to take a sequence of points and compute the
 * minimal bounding box surrounding them.
 * 
 * @author thomasb
 */
public class Geometry
{
	static class Point
	{
		final double x, y;

		Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}

		Point translate(double dx, double dy)
		{
			return new Point(x + dx, y + dy);
		}
	}

	static class Bounds
	{
		final double x, y, width, height;

		Bounds(double x, double y, double width, double height)
		{
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}
}
