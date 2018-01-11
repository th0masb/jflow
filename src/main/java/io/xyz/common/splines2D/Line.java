/**
 *
 */
package io.xyz.common.splines2D;

import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.PredicateUtil.all;
import static io.xyz.common.funcutils.PrimitiveUtil.isZero;

import java.util.function.DoubleUnaryOperator;

import io.xyz.common.fxutils.FXContextBinding;
import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.matrix.RMatrix;
import io.xyz.common.matrix.RPoint;
import io.xyz.common.matrix.impl.Matrices;

/**
 * @author t
 *
 */
public class Line extends AbstractSplineSegment {

	public Line(final RPoint to, final RPoint from)
	{
		super(to, from);
	}

	public static Line between(final RPoint start, final RPoint end)
	{
		return new Line(start, end);
	}

	@Override
	public Curve parameterise()
	{
		return Curve.straightLine(from(), to());
	}

	@Override
	public Line peturb(final RPoint peturbation)
	{
		assert all(p -> p.dim() == peturbation.dim(), getControlPoints());
		return Line.between(from().add(peturbation), to().add(peturbation));
	}

	public Line peturbToNewCentre(final RPoint newCentre)
	{
		return peturb(newCentre.subtract(midPoint()));
	}

	@Override
	public RPoint from()
	{
		return getControlPoints().get(0);
	}

	@Override
	public RPoint to()
	{
		return getControlPoints().get(1);
	}

	@Override
	public double length()
	{
		return to().distL2(from());
	}

	public boolean isParallelTo(final Line other)
	{
		final RPoint thisDir = direction().normalise(), otherDir = other.direction().normalise();
		return thisDir.equals(otherDir) || thisDir.equals(otherDir.scale(-1));
	}

	public RPoint direction()
	{
		return to().subtract(from());
	}

	/**
	 * @param distance
	 * @return 	the Point lying on this line which is a distance given by the parameter away from the
	 * 			start point of this line. If the distance is positive we move in the direction of the
	 * 			endpoint, otherwise we go the opposite way.
	 */
	public RPoint travel(final double distance)
	{
		final RPoint travelVector = direction().normalise().scale(distance);
		return from().add(travelVector);
	}

	public RPoint midPoint()
	{
		return midPoint(from(), to());
	}

	public boolean isVertical()
	{
		return isZero(to().x() - from().x());
	}

	public boolean isHorizontal()
	{
		return isZero(to().y() - from().y());
	}

	public double grad2D()
	{
		return (to().y() - from().y()) / (to().x() - from().x());
	}

	public DoubleUnaryOperator asFunction2D() {
		assert !isVertical();
		final double m = grad2D();
		final double intercept = from().y() - m*from().y();
		return x -> m*x + intercept;
	}

	public double minimalAngleWith(final Line other)
	{
		return Math.acos(direction().dot(other.direction()));
	}

	/**
	 * Given this line L1 and another line L2...
	 *
	 * @param other
	 *        - the other line
	 * @return
	 */
	public double pathAngleWith(final Line other)
	{
		assert dim() == 2: "Not done for general case yet.";

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

	public RPoint perpendicularIntersection(final RPoint p)
	{
		assert dim() == 2 : "Not done general case yet";

		if (isVertical()) {
			return RPoint.of(from().x(), p.y());
		}
		else if (isHorizontal()) {
			return RPoint.of(p.x(), from().y());
		}

		final double thisGrad = grad2D(), perpGrad = -1/thisGrad;
		final double thisIntercept = from().y() - thisGrad*from().x(), perpIntercept = p.y() - perpGrad*p.x();
		return crossingPoint2D(thisGrad, thisIntercept, perpGrad, perpIntercept);
		/*
		 * This works in the general case if we get pathAngleWith method working in the
		 * general case too.
		 */
		//		final Line other = Line.between(p, from());
		//		final double pathAngle = other.pathAngleWith(this);
		//		final double absPathAngle = abs(pathAngle);
		//
		//		if (isZero(absPathAngle) || isZero(absPathAngle - Math.PI)) {
		//			return p;
		//		}
		//		else if (isZero(absPathAngle - Math.PI/2)) {
		//			return from();
		//		}
		//
		//		final boolean acute = abs(pathAngle) < Math.PI/2;
		//		final double directionMultiplier = (acute? -1 : 1)*signum(pathAngle);
	}

	public static RPoint crossingPoint2D(final Line a, final Line b)
	{
		/*
		 * We use an assertion to check if the lines are parallel because we
		 * expect the caller to invoke this method in a situation where the
		 * lines cross.
		 */
		assert a.dim() == 2 && b.dim() == 2 && !a.isParallelTo(b);

		/*
		 * Assume a.from() is the origin then shift result at the end.
		 *
		 * We represent both lines in the form y = mx + c and solve simultaneously
		 */

		final RPoint afrom = a.from(), bfrom = b.from();

		if (a.isVertical()) {
			return RPoint.of(afrom.x(), b.asFunction2D().applyAsDouble(afrom.x()));
		}
		else if (b.isVertical()) {
			return RPoint.of(bfrom.x(), a.asFunction2D().applyAsDouble(bfrom.x()));
		}
		else {
			final double am = a.grad2D(), bm = b.grad2D();
			final double aintercept = afrom.y() - am*afrom.x(), bintercept = bfrom.y() - bm*bfrom.x();
			return crossingPoint2D(am, aintercept, bm, bintercept);
		}
	}

	private static RPoint crossingPoint2D(final double agrad, final double aintercept, final double bgrad, final double bintercept)
	{
		final double resultx = (bintercept - aintercept)/(agrad - bgrad);
		return RPoint.of(resultx, agrad*resultx + aintercept);
	}

	public static RPoint midPoint(final RPoint a, final RPoint b)
	{
		return RPoint.of(combine((x1, x2) -> (x1 + x2)/2, a, b));
	}

	public static void main(final String[] args)
	{
		//		final RPoint a = RPoint.of(1, 1), b = RPoint.of(2, 3), c = RPoint.of(1.2, 1);
		//
		//		// System.out.println(a.scale(-1));
		//
		//		final Line first = new Line(a, b), second = new Line(b, c);
		//
		//		// System.out.println(first.from().add(first.to()));
		//
		//		System.out.println(Math.toDegrees(first.pathAngleWith(second)));
		//		System.out.println(Line.crossingPoint2D(first, second));


		final Line third = Line.between(RPoint.of(0, 2000), RPoint.of(2, 0));
		final RPoint p = RPoint.of(4, 4);
		System.out.println(third.perpendicularIntersection(p));
		//		System.out.println(crossingPoint2D(-1, 2, 1, 0));
	}

	@Override
	public void trace2D(final FXContextBinding gc, final PointTransform clipTransform, final RPoint perturbation)
	{
		assert correctDimensionsToDraw2D(clipTransform);
		gc.lineTo(clipTransform.transform(to().add(perturbation)));
	}
}
