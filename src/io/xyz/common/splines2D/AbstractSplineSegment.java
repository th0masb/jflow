package io.xyz.common.splines2D;

import static io.xyz.common.funcutils.PredicateUtil.all;

import java.util.Arrays;
import java.util.List;

import io.xyz.common.geometry.Curve;
import io.xyz.common.matrix.PointTransform;
import io.xyz.common.matrix.impl.RPointImpl;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 17 Nov 2017
 */
public abstract class AbstractSplineSegment implements ISplineSegment {

	static final int DIMENSION = 2;

	/**
	 * The constituent points which form this spline segment. In general the curve
	 * does not travel through each of these points.
	 */
	final RPointImpl[] constituents;

	/**
	*
	*/
	public AbstractSplineSegment(final RPointImpl... ps) {
		assert ps.length > 1 && all(p -> p.dim() == 2, ps);
		constituents = Arrays.copyOf(ps, ps.length);
	}

	/**
	*
	*/
	public AbstractSplineSegment(final List<RPointImpl> ps) {
		assert ps.size() > 1 && all(p -> p.dim() == DIMENSION, ps);
		constituents = ps.toArray(new RPointImpl[ps.size()]);
	}

	@Override
	public void draw(final GraphicsContext gc, final PointTransform clipT) {
		assert clipT.domainDim() == DIMENSION && clipT.targetDim() == DIMENSION;
		draw(gc, clipT, RPointImpl.origin(dim()));
	}

	@Override
	public void draw(final GraphicsContext gc, final PointTransform clipT, final RPointImpl p) {
		assert clipT.domainDim() == DIMENSION && clipT.targetDim() == DIMENSION;
		throw new RuntimeException("Not yet impl");
	}

	@Override
	public double approximateLength() {
		return Curve.length(parameterise());
	}

	@Override
	public int dim() {
		return constituents[0].dim();
	}
}
