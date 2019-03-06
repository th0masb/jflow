/**
 * 
 */
package com.github.maumay.jflow.examples;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.github.maumay.jflow.utils.Option;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public final class IteratorAsFunctionParameter
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

	static class BoundsXY
	{
		final double x, y, width, height;

		public BoundsXY(double x, double y, double width, double height)
		{
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		static Optional<BoundsXY> pointsCollector(Iterator<? extends Point> source)
		{
			if (!source.hasNext()) {
				return Option.empty();
			}
			double minx = Double.POSITIVE_INFINITY, maxx = Double.NEGATIVE_INFINITY;
			double miny = Double.POSITIVE_INFINITY, maxy = Double.NEGATIVE_INFINITY;
			while (source.hasNext()) {
				Point next = source.next();
				minx = Math.min(minx, next.x);
				maxx = Math.max(maxx, next.x);
				miny = Math.min(miny, next.y);
				maxy = Math.max(maxy, next.y);
			}
			if (maxx - minx > 0 && maxy - miny > 0) {
				return Option.of(new BoundsXY(minx, miny, maxx - minx, maxy - miny));
			} else {
				return Option.empty();
			}
		}

		static PointsToBoundsCollector pointsCollector()
		{
			return new PointsToBoundsCollector();
		}
	}

	// Stream version
	static class BoundsCollectorContainer
	{
		double minx = Double.POSITIVE_INFINITY, maxx = Double.NEGATIVE_INFINITY;
		double miny = Double.POSITIVE_INFINITY, maxy = Double.NEGATIVE_INFINITY;
	}

	// Implementation omitted
	static class PointsToBoundsCollector
			implements Collector<Point, BoundsCollectorContainer, Optional<BoundsXY>>
	{
		@Override
		public BiConsumer<BoundsCollectorContainer, Point> accumulator()
		{
			throw new RuntimeException("Not yet implemented");
		}

		@Override
		public Set<Characteristics> characteristics()
		{
			throw new RuntimeException("Not yet implemented");
		}

		@Override
		public BinaryOperator<BoundsCollectorContainer> combiner()
		{
			throw new RuntimeException("Not yet implemented");
		}

		@Override
		public Function<BoundsCollectorContainer, Optional<BoundsXY>> finisher()
		{
			throw new RuntimeException("Not yet implemented");
		}

		@Override
		public Supplier<BoundsCollectorContainer> supplier()
		{
			throw new RuntimeException("Not yet implemented");
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException
	{
		IteratorAsFunctionParameter.class.getClassLoader()
				.loadClass("com.github.maumay.jflow.iterators.DoubleIterator");
		Vec<Point> points = Vec.of(new Point(0, 0), new Point(1, 1));

		// With our iterator version
		Optional<BoundsXY> enclosing = points.iter().map(p -> new Point(p.x + 1, p.y + 1))
				.collect(BoundsXY::pointsCollector);

		// With our stream version
		Optional<BoundsXY> enclosing2 = points.stream().map(p -> new Point(p.x + 1, p.y + 1))
				.collect(BoundsXY.pointsCollector());
	}
}
