/**
 *
 */
package xawd.jflow.geometry.mappings;

import xawd.jflow.Flow;
import xawd.jflow.construction.Iter;
import xawd.jflow.geometry.LineSegment;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.impl.StraightLineImpl;

/**
 * @author t
 *
 */
public final class KochLineFunction
{
	public static Flow<LineSegment> recurseRight(LineSegment source)
	{
		final Point oneThirdUp = source.interpolate(1/3.0);
		final Point twoThirdsUp = source.interpolate(2/3.0);
		final Point rotated = PointMap.rotate(oneThirdUp, Math.PI/3).apply(twoThirdsUp);
		return Iter.of(
				new StraightLineImpl(source.start(), oneThirdUp),
				new StraightLineImpl(oneThirdUp, rotated),
				new StraightLineImpl(rotated, twoThirdsUp),
				new StraightLineImpl(twoThirdsUp, source.end())
				);
	}
}
