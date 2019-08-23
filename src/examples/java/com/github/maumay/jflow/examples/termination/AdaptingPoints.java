package com.github.maumay.jflow.examples.termination;

import com.github.maumay.jflow.examples.termination.Geometry.Line;
import com.github.maumay.jflow.examples.termination.Geometry.Point;
import com.github.maumay.jflow.impl.AbstractIteratorAdapter;
import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.vec.Vec;

/**
 * 
 * @author thomasb
 *
 */
public final class AdaptingPoints
{
	/**
	 * Lazily connects an iterator of points with lines connecting consecutive
	 * points.
	 */
	static class PointFoldIterator
			extends AbstractIteratorAdapter.OfObject<AbstractRichIterator<? extends Point>, Line>
	{
		private Point cached;

		public PointFoldIterator(AbstractRichIterator<? extends Point> src)
		{
			super(src.getSize().subtract(1), src);
			cached = src.hasNext() ? src.nextImpl() : null;
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		public Line nextImpl()
		{
			Point srcNext = getSource().nextImpl();
			Line nextLine = new Line(cached, srcNext);
			cached = srcNext;
			return nextLine;
		}

		@Override
		public void forwardImpl()
		{
			nextImpl();
		}
	}

	public static void main(String[] args)
	{
		Vec<Point> points = Vec.of(new Point(1, 0), new Point(3, 6));

		points.iter().adapt(PointFoldIterator::new);
	}
}
