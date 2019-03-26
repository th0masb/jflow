/**
 * 
 */
package com.github.maumay.jflow.examples.termination;

import static com.github.maumay.jflow.vec.Vec.vec;
import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.StreamSupport;

import com.github.maumay.jflow.examples.termination.Geometry.Bounds;
import com.github.maumay.jflow.examples.termination.Geometry.Point;
import com.github.maumay.jflow.iterators.Iter;
import com.github.maumay.jflow.iterators.RichIteratorCollector;
import com.github.maumay.jflow.utils.Option;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 */
public final class CollectingPoints
{
	// Stream version
	static PointsToBoundsCollector fromStream()
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
				return Option.empty();
			} else {
				return Option.of(new Bounds(minx, miny, maxx - minx, maxy - miny));
			}
		}
	}

	// Iterator version
	public static RichIteratorCollector<Point, Optional<Bounds>> fromIterator()
	{
		return source -> {
			double minx = POSITIVE_INFINITY, maxx = NEGATIVE_INFINITY;
			double miny = POSITIVE_INFINITY, maxy = NEGATIVE_INFINITY;
			/*
			 * Can lift the iterator to a single use iterable so it can be used with the
			 * enhanced for loop construct.
			 */
			for (Point p : source.lift()) {
				minx = Math.min(minx, p.x);
				maxx = Math.max(maxx, p.x);
				miny = Math.min(miny, p.y);
				maxy = Math.max(maxy, p.y);
			}

			/*
			 * Good practice to make the input iterator relinquish ownership so it can't
			 * be used again once it is consumed.
			 */
			source.relinquishOwnership();
			if (Double.isInfinite(minx)) {
				return Option.empty();
			} else {
				return Option.of(new Bounds(minx, miny, maxx - minx, maxy - miny));
			}
		};
	}

	public static Optional<Bounds> fromIterable(Iterable<? extends Point> source)
	{
		return Iter.wrap(source.iterator()).collect(fromIterator());
	}

	static Optional<Bounds> fromIterable2(Iterable<? extends Point> source)
	{
		return StreamSupport.stream(source.spliterator(), false).collect(fromStream());
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Vec<Point> points = vec(new Point(0, 0), new Point(1, 1));

		// With our iterator version
		Optional<Bounds> withIterator = points.iter().map(p -> p.translate(0, 1))
				.collect(CollectingPoints.fromIterator());

		// With our stream version
		Optional<Bounds> withStream = points.stream().map(p -> p.translate(0, 1))
				.collect(CollectingPoints.fromStream());

		// withIterator.equals(withStream)
		System.out.println("done");
	}
}
