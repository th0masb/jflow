/**
 *
 */
package xawd.jflow.geometry.mappings;

import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.impl.PointImpl;

/**
 * @author t
 *
 */
public interface PointMap extends Function<Point, Point>, Consumer<Point>
{
	DoubleBinaryOperator getX();

	DoubleBinaryOperator getY();

	void mapInPlace(Point p);

	@Deprecated
	@Override
	default void accept(Point p)
	{
		mapInPlace(p);
	}

	static PointMap translationOf(final double dx, final double dy)
	{
		return new PointMap() {
			DoubleBinaryOperator xMapping = (x, y) -> x + dx;
			DoubleBinaryOperator yMapping = (x, y) -> y + dy;

			@Override
			public Point apply(Point t) {
				return new PointImpl(t.x() + dx, t.y() + dy);
			}

			@Override
			public DoubleBinaryOperator getY() {
				return xMapping;
			}

			@Override
			public DoubleBinaryOperator getX() {
				return yMapping;
			}

			@Override
			public void mapInPlace(Point t) {
				t.setX(t.x() + dx);
				t.setY(t.y() + dy);
			}
		};
	}

	static PointMap rotate(Point centre, double theta)
	{


		throw new RuntimeException();
		//		return new PointMap() {
		//			double cosTheta = Math.cos(theta), sinTheta = Math.sin(theta);
		//			DoubleBinaryOperator xMapping = (x, y) -> cosTheta*;
		//			DoubleBinaryOperator yMapping = (x, y) -> y + dy;
		//
		//			@Override
		//			public Point apply(Point t) {
		//				// TODO Auto-generated method stub
		//				return null;
		//			}
		//
		//			@Override
		//			public void mapInPlace(Point p) {
		//				// TODO Auto-generated method stub
		//
		//			}
		//
		//			@Override
		//			public DoubleBinaryOperator getY() {
		//				// TODO Auto-generated method stub
		//				return null;
		//			}
		//
		//			@Override
		//			public DoubleBinaryOperator getX() {
		//				// TODO Auto-generated method stub
		//				return null;
		//			}
		//		};
	}
}
