package xawd.jflow.geometry.splines;

import xawd.jflow.Flow;
import xawd.jflow.geometry.SplineIterable;
import xawd.lists.listflow.ListFlow;

public final class ComposedSplines implements SplineIterable
{
	private final ListFlow<Spline> constituents;

	public ComposedSplines(Flow<Spline> source)
	{
		constituents = source.toListFlow();
	}

	@Override
	public Flow<Spline> iterateOverCurves() {
		return constituents.iter();
	}
}
