package io.xyz.common.splines2D;

import static io.xyz.common.funcutils.CollectionUtil.allEqual;
import static io.xyz.common.funcutils.MapUtil.intRange;

import java.util.List;

import com.google.common.collect.ImmutableList;

import io.xyz.common.geometry.Curve;
import io.xyz.common.matrix.RPoint;

/**
 * @author ThomasB
 * @since 17 Nov 2017
 */
public abstract class AbstractSplineSegment implements ISplineSegment {

	/**
	 * The constituent points which form this spline segment. In general the curve
	 * does not travel through each of these points.
	 */
	private final ImmutableList<RPoint> constituents;

	public AbstractSplineSegment(final ImmutableList<RPoint> ps) {
		assert ps.size() > 1 && allEqual(intRange(p -> p.dim(), ps));
		constituents = ps;
	}

	public AbstractSplineSegment(final RPoint... ps) {
		this(ImmutableList.copyOf(ps));
	}

	public AbstractSplineSegment(final List<RPoint> ps) {
		this(ImmutableList.copyOf(ps));
	}

	@Override
	public double length() {
		return Curve.length(parameterise());
	}

	@Override
	public int dim() {
		return constituents.get(0).dim();
	}

	@Override
	public List<RPoint> getControlPoints()
	{
		return constituents;
	}
}
