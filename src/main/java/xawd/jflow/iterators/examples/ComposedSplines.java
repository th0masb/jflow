package xawd.jflow.iterators.examples;

import xawd.jflow.iterators.Flow;


/**
 * @author ThomasB
 */
public final class ComposedSplines implements SplineIterable
{
	private final ListFlow<Spline> constituents;

	public ComposedSplines(final Flow<Spline> source)
	{
		constituents = source.toListFlow();
	}

	@Override
	public Flow<Spline> iterateOverSplines() {
		return constituents.iter();
	}
}
