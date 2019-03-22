/**
 * 
 */
package com.github.maumay.jflow.examples.termination;

import static com.github.maumay.jflow.utils.Exceptions.require;
import static com.github.maumay.jflow.vec.Vec.vec;
import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.StreamSupport;

import com.github.maumay.jflow.examples.termination.Geometry.Bounds;
import com.github.maumay.jflow.examples.termination.Geometry.Point;
import com.github.maumay.jflow.iterators.RichIterator;
import com.github.maumay.jflow.iterators.api.Iter;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 */
public final class CollectingPoints
{
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

		void accumulate(Geometry.Point other)
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
			return new Geometry.Bounds(minx, miny, width, height);
		}
	}

	// Iterator version
	public static Bounds fromIterator(RichIterator<? extends Point> source)
	{
		require(source.hasNext());
		double minx = POSITIVE_INFINITY, maxx = NEGATIVE_INFINITY;
		double miny = POSITIVE_INFINITY, maxy = NEGATIVE_INFINITY;

		for (Point p : source.lift()) {
			minx = Math.min(minx, p.x);
			maxx = Math.max(maxx, p.x);
			miny = Math.min(miny, p.y);
			maxy = Math.max(maxy, p.y);
		}
		double width = maxx - minx;
		double height = maxy - miny;
		require(width >= 0 && height >= 0);
		return new Bounds(minx, miny, width, height);
	}

	public static Bounds fromIterable(Iterable<? extends Point> source)
	{
		return fromIterator(Iter.wrap(source.iterator()));
	}

	static Bounds fromIterable2(Iterable<? extends Point> source)
	{
		return StreamSupport.stream(source.spliterator(), false).collect(pointsCollector());
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Vec<Point> points = vec(new Point(0, 0), new Point(1, 1));

		// With our iterator version
		Bounds withIterator = points.iter().map(p -> p.translate(0, 1))
				.collect(CollectingPoints::fromIterator);

		// With our stream version
		Bounds withStream = points.stream().map(p -> p.translate(0, 1))
				.collect(CollectingPoints.pointsCollector());

		// withIterator.equals(withStream)
		System.out.println("done");
	}
}
