/**
 *
 */
package io.xyz.common.splines2D;

import io.xyz.common.geometry.Curve;
import io.xyz.common.matrix.PointMap;
import io.xyz.common.matrix.PointTransform;
import io.xyz.common.matrix.impl.RPointImpl;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author t
 *
 */
public class Line extends AbstractSplineSegment {

	public Line(final RPointImpl to, final RPointImpl from) {
		super(to, from);
	}

	@Override
	public void draw(final GraphicsContext gc, final PointTransform clipT, final RPointImpl p) {
		final PointMap map = clipT.getMapping();
		final RPointImpl p1 = map.map(from().add(p)), p2 = map.map(to().add(p));
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
	public Line peturb(final RPointImpl peturbation) {
		// TODO Auto-generated method stub
		return null;
	}

	public RPointImpl from() {
		return constituents[0];
	}

	public RPointImpl to() {
		return constituents[1];
	}

	// public boolean isParallelTo(Line other)
	// {
	// return this.di
	// }

	public RPointImpl direction() {
		return to().subtract(from()).normalise();
	}

	public double angleWith(final Line other) {
		return Math.acos(direction().dot(other.direction()));
	}

	public double signedAngleWith(final Line other) {
		final RPointImpl first = direction().normalise(), second = other.direction().normalise();
		final double angle = Math.acos(direction().dot(other.direction()));

	}

	public static void main(final String[] args) {
		System.out.println(Math.acos(0));
	}
}
