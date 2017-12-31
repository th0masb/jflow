/**
 *
 */
package io.xyz.common.splines;

import io.xyz.common.geometry.Curve;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.geometry.RPoint;
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
	public void draw(final GraphicsContext gc, final PointTransform clipT, final RPoint perturbation) {
		// TODO Auto-generated method stub
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
}
