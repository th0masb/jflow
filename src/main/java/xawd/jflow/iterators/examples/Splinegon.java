package xawd.jflow.iterators.examples;

import java.util.List;
import java.util.NoSuchElementException;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.construction.EmptyIteration;
import xawd.jflow.iterators.construction.IterRange;
import xawd.jflow.iterators.construction.Iterate;

/**
 * @author ThomasB
 */
public class Splinegon implements Shape
{
	private final List<Spline> curves;

	public Splinegon(final Flow<? extends Spline> source)
	{
		curves = source.map(Spline.class::cast).toList();
	}

	@Override
	public Flow<Spline> iterateOverSplines()
	{
		return Iterate.over(curves);
	}

	@Override
	public Flow<Point> iterateOverPoints()
	{
		return new SplinegonPointIterator(Iterate.over(curves));
	}

	private static class SplinegonPointIterator extends AbstractFlow<Point>
	{
		private static final int SPLINE_SPLIT = 5;

		private final Flow<Spline> splineSource;

		private Spline currentSpline;
		private Flow<Point> currentSplineFlow;

		// input should be List not Flow
		public SplinegonPointIterator(final Flow<Spline> splineSource)
		{
			this.splineSource = splineSource;
			try {
				currentSplineFlow = convertSplineToIterator(splineSource.next(), true, true);
			}
			catch (final NoSuchElementException ex) {
				throw new IllegalArgumentException(ex);
			}
		}

		@Override
		public boolean hasNext() {
			return currentSplineFlow.hasNext();
		}

		@Override
		public Point next() {
			final Point next = currentSplineFlow.next();
			while (!currentSplineFlow.hasNext() && splineSource.hasNext())
			{
				currentSpline = splineSource.next();
				currentSplineFlow = convertSplineToIterator(currentSpline, false, splineSource.hasNext());
			}
			return next;
		}

		@Override
		public void skip() {
			next();
		}

		private Flow<Point> convertSplineToIterator(final Spline spline, final boolean includeStart, final boolean includeEnd)
		{
			if (spline instanceof LineSegment) {
				return convertLineToIterator((LineSegment) spline, includeStart, includeEnd);
			}

			final ParameterisedCurve asFunction = spline.parameterise();

			if (includeStart && includeEnd) {
				return IterRange.to(SPLINE_SPLIT + 1).mapToObject(i -> asFunction.apply(i / (double) SPLINE_SPLIT));
			}
			else if (includeStart) {
				return IterRange.to(SPLINE_SPLIT).mapToObject(i -> asFunction.apply(i / (double) SPLINE_SPLIT));
			}
			else if (includeEnd) {
				return IterRange.between(1, SPLINE_SPLIT + 1).mapToObject(i -> asFunction.apply(i / (double) SPLINE_SPLIT));
			}
			else {
				return IterRange.between(1, SPLINE_SPLIT).mapToObject(i -> asFunction.apply(i / (double) SPLINE_SPLIT));
			}

		}

		private Flow<Point> convertLineToIterator(final LineSegment spline, final boolean includeStart, final boolean includeEnd)
		{
			if (includeStart && includeEnd) {
				return Iterate.over(spline.start(), spline.end());
			}
			else if (includeStart) {
				return Iterate.over(spline.start());
			}
			else if (includeEnd) {
				return Iterate.over(spline.end());
			}
			else {
				return EmptyIteration.ofObjects();
			}
		}
	}
}
