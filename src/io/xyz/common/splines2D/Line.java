/**
 *
 */
package io.xyz.common.splines2D;

import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointMap;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.matrix.RPoint;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author t
 *
 */
public class Line extends AbstractSplineSegment {

	public Line(final RPoint to, final RPoint from) {
		super(to, from);
	}

	@Override
	public void draw(final GraphicsContext gc, final PointTransform clipT, final RPoint p) {
		final PointMap map = clipT.getMapping();
		final RPoint p1 = map.transform(from().add(p)), p2 = map.transform(to().add(p));
		gc.beginPath();
		gc.moveTo(p1.x(), p1.y());
		gc.lineTo(p2.x(), p2.y());
		gc.stroke();
		gc.closePath();
	}

	@Override
	public Curve parameterise() {
		return Curve.straightLine(from(), to());
	}

	@Override
	public Line peturb(final RPoint peturbation) {
		// TODO Auto-generated method stub
		return null;
	}

	public RPoint from() {
		return constituents[0];
	}

	public RPoint to() {
		return constituents[1];
	}

	// public boolean isParallelTo(Line other)
	// {
	// return this.di
	// }

	public RPoint direction() {
		return to().subtract(from()).normalise();
	}

	public double angleWith(final Line other) {
		return Math.acos(direction().dot(other.direction()));
	}

	public double signedAngleWith(final Line other) {
		final RPoint first = direction().normalise(), second = other.direction().normalise();
		final double angle = Math.acos(direction().dot(other.direction()));

	}

	public static void main(final String[] args) {
		System.out.println(Math.acos(0));
	}
}
