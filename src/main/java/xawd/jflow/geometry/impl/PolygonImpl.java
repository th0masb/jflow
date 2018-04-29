/**
 *
 */
package xawd.jflow.geometry.impl;

import static xawd.jchain.chains.utilities.CollectionUtil.head;
import static xawd.jchain.chains.utilities.CollectionUtil.tail;

import java.util.NoSuchElementException;

import xawd.jflow.AbstractFlow;
import xawd.jflow.Flow;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.Polygon;
import xawd.jflow.geometry.LineSegment;
import xawd.lists.listflow.ArrayListFlow;
import xawd.lists.listflow.ListFlow;

/**
 * @author t
 *
 */
public class PolygonImpl implements Polygon
{
	private final ListFlow<LineSegment> lines;

	public PolygonImpl(Flow<LineSegment> srcLines)
	{
		lines = srcLines.toListFlow();
		if (lines.size() < 3) {
			throw new IllegalArgumentException();
		}
	}

	private PolygonImpl()
	{
		lines = new ArrayListFlow<>();
	}

	public static PolygonImpl fromLines(Flow<LineSegment> srcLines)
	{
		return new PolygonImpl(srcLines);
	}

	public static PolygonImpl fromPoints(Flow<? extends Point> srcPoints)
	{
		final PolygonImpl newInstance = new PolygonImpl();
		try {
			final ListFlow<LineSegment> lines = newInstance.lines;
			Point from = srcPoints.next(), to = srcPoints.next();
			lines.add(new StraightLineImpl(from, to));
			while (srcPoints.hasNext()) {
				from = to;
				to = srcPoints.next();
				lines.add(new StraightLineImpl(from, to));
			}
			lines.add(new StraightLineImpl(tail(lines).end(), head(lines).start()));

		}
		catch (final NoSuchElementException ex) {
			throw new IllegalArgumentException(ex);
		}
		return newInstance;
	}

	@Override
	public Flow<Point> points() {
		final PolygonImpl src = this;

		return new AbstractFlow<Point>() {
			int count = 0;
			int pointCount = src.lines.size();
			@Override
			public boolean hasNext() {
				return count < pointCount;
			}
			@Override
			public Point next() {
				try {
					return src.lines.get(count++).start();
				}
				catch (final IndexOutOfBoundsException ex) {
					throw new NoSuchElementException();
				}
			}
			@Override
			public void skip() {
				if (count++ >= pointCount) {
					throw new NoSuchElementException();
				}
			}
		};
	}

	@Override
	public Flow<LineSegment> lines() {
		return lines.iter();
	}

}
