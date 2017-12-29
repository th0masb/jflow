/**
 * 
 */
package io.xyz.common.geometry;

/**
 * @author t
 *
 */
public interface ICurveParameterisation 
{
	Point getPoint(double t);

	static ICurveParameterisation straightLine(Point p1, Point p2) 
	{
		return t -> new Point((1-t)*p1.x + t*p2.x, (1-t)*p1.y + t*p2.y);
	}
}
