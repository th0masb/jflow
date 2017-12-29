/**
 * 
 */
package io.xyz.common.geometry;

import static java.lang.Math.sqrt;

/**
 * @author t
 */
public final class Point 
{
	public static final Point ROT_ID = new Point(1,  0);
	
	public final double x, y;

	public Point(double x, double y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public Point add(Point other)
	{
		return new Point(x + other.x, y + other.y);
	}
	
	public Point subtract(Point other)
	{
		return new Point(x - other.x, y - other.y);
	}
	
	public Point scale(double scaleF)
	{
		return new Point(x*scaleF, y*scaleF);
	}
	
	public Point rotate(double angle)
	{
		double cos = Math.cos(angle), sin = Math.sin(angle);
		return new Point(cos*x - sin*y, sin*x + cos*y);
	}
	
	public Point normalise()
	{
		double mag = magnitde();
		return new Point(x/mag, y/mag);
	}
	
	public double magnitde()
	{
		return sqrt(x*x + y*y);
	}
}
