/**
 * 
 */
package com.github.maumay.jflow.examples.termination;

import static com.github.maumay.jflow.vec.Vec.vec;

import java.util.Optional;

import com.github.maumay.jflow.examples.termination.Geometry.Bounds;
import com.github.maumay.jflow.examples.termination.Geometry.Point;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 */
public final class CollectingPoints
{
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Vec<Point> points = vec(new Point(0, 0), new Point(1, 1));

		// With our iterator version
		Optional<Bounds> withIterator = points.iter().map(p -> p.translate(0, 1))
				.collect(Bounding::points);

		// With our stream version
		Optional<Bounds> withStream = points.stream().map(p -> p.translate(0, 1))
				.collect(Bounding.pointsCollector());
	}
}
