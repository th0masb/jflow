/**
 * 
 */
package com.github.maumay.jflow.examples;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import java.util.EnumSet;
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

		static Optional<BoundsXY> enclosing(Iterator<? extends Point> source)
		{
			if (!source.hasNext()) {
				return Option.empty();
			}
			double minx = POSITIVE_INFINITY, maxx = NEGATIVE_INFINITY;
			double miny = POSITIVE_INFINITY, maxy = NEGATIVE_INFINITY;
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
	static class PointsToBoundsCollector
			implements Collector<Point, CollectionContainer, Optional<BoundsXY>>
	{
		@Override
		public BiConsumer<CollectionContainer, Point> accumulator()
		{
			return CollectionContainer::accumulate;
		}

		@Override
		public Set<Characteristics> characteristics()
		{
			return EnumSet.noneOf(Characteristics.class);
		}

		@Override
		public BinaryOperator<CollectionContainer> combiner()
		{
			return CollectionContainer::combine;
		}

		@Override
		public Function<CollectionContainer, Optional<BoundsXY>> finisher()
		{
			return CollectionContainer::finish;
		}

		@Override
		public Supplier<CollectionContainer> supplier()
		{
			return CollectionContainer::new;
		}
	}

	static class CollectionContainer
	{
		private double minx = POSITIVE_INFINITY, maxx = NEGATIVE_INFINITY;
		private double miny = POSITIVE_INFINITY, maxy = NEGATIVE_INFINITY;

		void accumulate(Point other)
		{
			minx = Math.min(minx, other.x);
			maxx = Math.max(maxx, other.x);
			miny = Math.min(miny, other.y);
			maxy = Math.max(maxy, other.y);
		}

		CollectionContainer combine(CollectionContainer other)
		{
			CollectionContainer result = new CollectionContainer();
			result.minx = Math.min(minx, other.minx);
			result.maxx = Math.max(maxx, other.maxx);
			result.miny = Math.min(miny, other.miny);
			result.maxy = Math.max(maxy, other.maxy);
			return result;
		}

		Optional<BoundsXY> finish()
		{
			if (Double.isInfinite(minx)) {
				return Option.empty();
			}
			double xdiff = maxx - minx, ydiff = maxy - miny;
			if (xdiff > 0 && ydiff > 0) {
				return Option.of(new BoundsXY(minx, miny, xdiff, ydiff));
			} else {
				return Option.empty();
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Vec<Point> points = Vec.of(new Point(0, 0), new Point(1, 1));

		// With our iterator version
		Optional<BoundsXY> withIterator = points.iter().map(p -> new Point(p.x, p.y + 1))
				.collect(BoundsXY::enclosing);

		// With our stream version
		Optional<BoundsXY> withStream = points.stream().map(p -> new Point(p.x, p.y + 1))
				.collect(BoundsXY.pointsCollector());

		// withIterator.equals(withStream)
	}
}
