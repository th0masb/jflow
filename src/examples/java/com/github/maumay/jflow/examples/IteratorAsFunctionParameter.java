/**
 * 
 */
package com.github.maumay.jflow.examples;

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

		public <P extends Point> BoundsXY(Iterable<P> source)
		{
			this(source.iterator());
		}

		public <P extends Point> BoundsXY(Iterator<P> source)
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
			x = minx;
			y = miny;
			width = maxx - minx;
			height = maxy - miny;
			require(width >= 0 && height >= 0);
		}

		static PointsToBoundsCollector pointsCollector()
		{
			return new PointsToBoundsCollector();
		}
	}

	// Stream version
	static class PointsToBoundsCollector
			implements Collector<Point, CollectionContainer, BoundsXY>
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
		public Function<CollectionContainer, BoundsXY> finisher()
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

		BoundsXY finish()
		{
			require(Double.isFinite(minx));
			double width = maxx - minx, height = maxy - miny;
			require(width >= 0 && height >= 0);
			return new BoundsXY(minx, miny, width, height);
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Vec<Point> points = Vec.of(new Point(0, 0), new Point(1, 1));

		// With our iterator version
		BoundsXY withIterator = points.iter().map(p -> new Point(p.x, p.y + 1))
				.collect(BoundsXY::new);

		BoundsXY withIterator2 = new BoundsXY(points);
		BoundsXY withIterator3 = new BoundsXY(points.toList());
		BoundsXY withIterator4 = new BoundsXY(points.toSet());

		// With our stream version
		BoundsXY withStream = points.stream().map(p -> new Point(p.x, p.y + 1))
				.collect(BoundsXY.pointsCollector());

		// withIterator.equals(withStream)
	}
}
