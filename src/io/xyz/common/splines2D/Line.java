/**
 *
 */
package io.xyz.common.splines2D;

import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointMap;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.matrix.RMatrix;
import io.xyz.common.matrix.RPoint;
import io.xyz.common.matrix.impl.Matrices;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author t
 *
 */
public class Line extends AbstractSplineSegment {

	public Line(final RPoint to, final RPoint from)
	{
		super(to, from);
	}

	@Override
	public void draw(final GraphicsContext gc, final PointTransform clipT, final RPoint p)
	{
		final PointMap map = clipT.getMapping();
		final RPoint p1 = map.transform(from().add(p)), p2 = map.transform(to().add(p));
		gc.beginPath();
		gc.moveTo(p1.x(), p1.y());
		gc.lineTo(p2.x(), p2.y());
		gc.stroke();
		gc.closePath();
	}

	@Override
	public Curve parameterise()
	{
		return Curve.straightLine(from(), to());
	}

	@Override
	public Line peturb(final RPoint peturbation)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public RPoint from()
	{
		return constituents[0];
	}

	public RPoint to()
	{
		return constituents[1];
	}

	public boolean isParallelTo(final Line other)
	{
		final RPoint thisDir = direction().normalise(), otherDir = other.direction().normalise();
		return thisDir.equals(otherDir) || thisDir.equals(otherDir.scale(-1));
	}

	public RPoint direction()
	{
		return to().subtract(from()).normalise();
	}

	/**
	 * Given this line L1 and another line L2...
	 * 
	 * @param other
	 *        - the other line
	 * @return
	 */
	public double signedAngleWith(final Line other)
	{
		final RPoint first = from().subtract(to()).normalise(), second = other.direction().normalise();

		if (first.equals(second)) {
			return Math.PI;
		} else if (first.equals(second.scale(-1))) {
			return 0;
		}

		final double angle = Math.acos(first.dot(second));
		final RMatrix antiRotation = Matrices.rotate2D(angle);
		return (antiRotation.transform(second).equals(first)? 1 : -1) * (Math.PI - angle);
	}

	public static void main(final String[] args)
	{
		final RPoint a = RPoint.of(1, 1), b = RPoint.of(2, 3), c = RPoint.of(1.2, 1);

		// System.out.println(a.scale(-1));

		final Line first = new Line(a, b), second = new Line(b, c);

		// System.out.println(first.from().add(first.to()));

		System.out.println(Math.toDegrees(first.signedAngleWith(second)));

	}
}
