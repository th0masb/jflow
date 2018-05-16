/**
 *
 */
package xawd.jflow.iterators.examples;

/**
 * @author ThomasB
 *
 */
public final class Point
{
	private static final Point ORIGIN = new Point(0, 0);

	public final double x, y;

	public Point(final double x, final double y)
	{
		this.x = x;
		this.y = y;
	}

	public static Point origin()
	{
		return ORIGIN;
	}

}
