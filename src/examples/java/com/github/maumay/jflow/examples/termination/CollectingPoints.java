/**
 * 
 */
package com.github.maumay.jflow.examples.termination;

import static com.github.maumay.jflow.utils.Exceptions.require;
import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.github.maumay.jflow.examples.termination.Base.Bounds;
import com.github.maumay.jflow.examples.termination.Base.Point;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 */
public final class CollectingPoints
{
	// Iterator version
	public static Bounds fromIterator(Iterator<? extends Point> source)
	{
		require(source.hasNext());
		double minx = POSITIVE_INFINITY, maxx = NEGATIVE_INFINITY;
		double miny = POSITIVE_INFINITY, maxy = NEGATIVE_INFINITY;
		while (source.hasNext()) {
			Point next = source.next();
			minx = Math.min(minx, next.x);
			maxx = Math.max(maxx, next.x);
			miny = Math.min(miny, next.y);
			maxy = Math.max(maxy, next.y);
		}
		double width = maxx - minx;
		double height = maxy - miny;
		require(width >= 0 && height >= 0);
		return new Bounds(minx, miny, width, height);
	}

	public static Bounds fromIterable(Iterable<? extends Point> source)
	{
		return fromIterator(source.iterator());
	}

	// Stream version
	static PointsToBoundsCollector pointsCollector()
	{
		return new PointsToBoundsCollector();
	}

	static class PointsToBoundsCollector implements Collector<Point, CollectionContainer, Bounds>
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
		public Function<CollectionContainer, Bounds> finisher()
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

		void accumulate(Base.Point other)
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

		Bounds finish()
		{
			require(Double.isFinite(minx));
			double width = maxx - minx, height = maxy - miny;
			require(width >= 0 && height >= 0);
			return new Base.Bounds(minx, miny, width, height);
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Vec<Point> points = Vec.of(new Point(0, 0), new Point(1, 1));

		// With our iterator version
		Bounds withIterator = points.iter().map(p -> new Point(p.x, p.y + 1))
				.collect(CollectingPoints::fromIterator);

		Bounds withIterator2 = CollectingPoints.fromIterable(points);
		Bounds withIterator3 = CollectingPoints.fromIterable(points.toList());
		Bounds withIterator4 = CollectingPoints.fromIterable(points.toSet());

		// With our stream version
		Bounds withStream = points.stream().map(p -> new Point(p.x, p.y + 1))
				.collect(CollectingPoints.pointsCollector());

		Bounds withStream2 = points.toList().stream().collect(CollectingPoints.pointsCollector());

		// withIterator.equals(withStream)
	}
}
