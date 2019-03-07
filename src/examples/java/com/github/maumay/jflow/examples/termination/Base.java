/**
 * 
 */
package com.github.maumay.jflow.examples.termination;

/**
 * @author thomasb
 *
 */
public class Base
{

	static class Point
	{
		final double x, y;

		Point(double x, double y)
		{
			this.x = x;
			this.y = y;
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
