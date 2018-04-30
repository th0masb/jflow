package xawd.jflow.geometry.impl;

import xawd.jflow.Flow;
import xawd.jflow.geometry.SplineIterable;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.PointIterable;
import xawd.jflow.geometry.splines.Spline;
import xawd.lists.listflow.ListFlow;

public class Splinegon implements SplineIterable, PointIterable {

	private final ListFlow<Spline> curves;

	public Splinegon(Flow<Spline> source) {
		curves = source.toListFlow();
	}

	@Override
	public Flow<Spline> iterateOverCurves() {
		return curves.iter();
	}

	@Override
	public Flow<Point> iterateOverPoints() {
		return curves.iter().flatten(x -> x.iterateOverPoints());
	}
}
