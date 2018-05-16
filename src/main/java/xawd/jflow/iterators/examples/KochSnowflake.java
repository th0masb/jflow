/**
 *
 */
package xawd.jflow.iterators.examples;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.construction.Iterate;

/**
 * @author t
 *
 */
public final class KochSnowflake
{
	private static final double COS60 = cos(Math.PI/3), SIN60 = sin(Math.PI/3);

	public static Flow<LineSegment> getEquilateralTriangle(final double sideLength)
	{
		final Point firstCorner = new Point(0, 0);
		final Point secondCorner = new Point(sideLength, 0);
		final PointMapping rotate60 = Rotation.of(COS60, SIN60, firstCorner);
		final Point thirdCorner = rotate60.mapPoint(secondCorner);
		return Iterate.over(
				new LineSegment(firstCorner, thirdCorner),
				new LineSegment(thirdCorner, secondCorner),
				new LineSegment(secondCorner, firstCorner));
	}

	public static Flow<LineSegment> recurseRight(final LineSegment source)
	{
		final Point oneThirdUp = source.interpolate(1/3.0);
		final Point twoThirdsUp = source.interpolate(2/3.0);
		final Point rotated = Rotation.of(COS60, SIN60, oneThirdUp).mapPoint(twoThirdsUp);
		return Iterate.over(
				new LineSegment(source.start(), oneThirdUp),
				new LineSegment(oneThirdUp, rotated),
				new LineSegment(rotated, twoThirdsUp),
				new LineSegment(twoThirdsUp, source.end())
				);
	}
}
