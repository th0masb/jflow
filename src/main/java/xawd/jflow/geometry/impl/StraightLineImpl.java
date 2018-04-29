package xawd.jflow.geometry.impl;

import xawd.jflow.geometry.LineSegment;
import xawd.jflow.geometry.Point;

public class StraightLineImpl implements LineSegment {

	private final Point start, end;

	public StraightLineImpl(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Point start() {
		return start;

	}

	@Override
	public Point end() {
		return end;
	}

	@Override
	public Point interpolate(double t) {
		return new PointImpl((1 - t)*start.x() + t*end.x(), (1 - t)*start.y() + t*end.y());
	}
}
