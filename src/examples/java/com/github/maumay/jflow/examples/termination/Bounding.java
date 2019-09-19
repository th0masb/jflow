/**
 * 
 */
package com.github.maumay.jflow.examples.termination;

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

import com.github.maumay.jflow.examples.termination.Geometry.Bounds;
import com.github.maumay.jflow.examples.termination.Geometry.Point;

/**
 * @author thomasb
 *
 */
public final class Bounding
{
	private Bounding()
	{
	}

	public static Optional<Bounds> points(Iterator<? extends Point> points)
	{
		double minx = POSITIVE_INFINITY, maxx = NEGATIVE_INFINITY;
		double miny = POSITIVE_INFINITY, maxy = NEGATIVE_INFINITY;
		while (points.hasNext()) {
			Point p = points.next();
			minx = Math.min(minx, p.x);
			maxx = Math.max(maxx, p.x);
			miny = Math.min(miny, p.y);
			maxy = Math.max(maxy, p.y);
		}
		if (Double.isInfinite(minx)) {
			return Optional.empty();
		} else {
			return Optional.of(new Bounds(minx, miny, maxx - minx, maxy - miny));
		}
	}

	public static PointsToBoundsCollector pointsCollector()
	{
		return new PointsToBoundsCollector();
	}

	static class PointsToBoundsCollector
			implements Collector<Point, CollectionContainer, Optional<Bounds>>
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
		public Function<CollectionContainer, Optional<Bounds>> finisher()
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

		Optional<Bounds> finish()
		{
			if (Double.isInfinite(minx)) {
				return Optional.empty();
			} else {
				return Optional.of(new Bounds(minx, miny, maxx - minx, maxy - miny));
			}
		}
	}

}
