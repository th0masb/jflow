/**
 *
 */
package xawd.jflow.geometry.mappings;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import xawd.jflow.Flow;
import xawd.jflow.construction.Iter;
import xawd.jflow.geometry.LineSegment;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.Polygon;
import xawd.jflow.geometry.impl.LineSegmentImpl;
import xawd.jflow.geometry.impl.PolygonImpl;

/**
 * @author t
 *
 */
public final class KochSnowflake
{
	private static final double COS60 = cos(Math.PI/3), SIN60 = sin(Math.PI/3);

	public static Polygon getEquilateralTriangle(double sideLength)
	{
		final Point firstCorner = Point.of(0, 0);
		final Point secondCorner = Point.of(sideLength, 0);
		final PointMap rotate60 = PointMap.rotationOf(COS60, SIN60, firstCorner);
		final Point thirdCorner = rotate60.apply(secondCorner);
		return PolygonImpl.fromPoints(Iter.of(firstCorner, thirdCorner, secondCorner));
	}

	public static Flow<LineSegment> recurseRight(LineSegment source)
	{
		final Point oneThirdUp = source.interpolate(1/3.0);
		final Point twoThirdsUp = source.interpolate(2/3.0);
		final Point rotated = PointMap.rotationOf(Math.PI/3, oneThirdUp).apply(twoThirdsUp);
		return Iter.of(
				new LineSegmentImpl(source.start(), oneThirdUp),
				new LineSegmentImpl(oneThirdUp, rotated),
				new LineSegmentImpl(rotated, twoThirdsUp),
				new LineSegmentImpl(twoThirdsUp, source.end())
				);
	}
}
