/**
 *
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.MapUtil.mapToObj;
import static io.xyz.common.funcutils.RangeUtil.range;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import io.xyz.common.matrix.PointBiOperator;
import io.xyz.common.matrix.RPoint;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;

/**
 * @author t
 *
 *         R^n real point represented as a column vector, i.e. a nx1 matrix.
 */
public final class RPointImpl extends RMatrixImpl implements RPoint {

	private static final List<RPointImpl> ORIGIN = collect(mapToObj(RPointImpl::emptyInit, range(1, 15)));

	public RPointImpl(final double... ds) {
		this(ImmutableDoubleRangeDescriptor.from(ds));
	}

	public RPointImpl(final DoubleRangeDescriptor xs) {
		super(len(xs), xs);
	}

	public static RPointImpl of(final double... ds) {
		return new RPointImpl(Arrays.copyOf(ds, ds.length));
	}

	public static RPointImpl copy(final RPointImpl src) {
		return new RPointImpl(src.coords());
	}

	public static RPointImpl origin(final int dim) {
		return ORIGIN.get(dim);
	}

	private static RPointImpl emptyInit(final int dim) {
		assert dim > 0;
		return new RPointImpl(new double[dim]);
	}

	/*
	 * We store a point in a row vector but it is a column vector.
	 * We therefore override the following three methods to reflect
	 * this change.
	 */
	@Override
	public double at(final int i, final int j) {
		return super.at(j, i);
	}

	@Override
	public int colDim() {
		return super.rowDim();
	}

	@Override
	public int rowDim() {
		return super.colDim();
	}

	//	public Vector toVector() {
	//		return DenseVector.fromArray(coords());
	//	}

	@Override
	public RPointImpl apply(final DoubleUnaryOperator f)
	{
		return new RPointImpl(map(f, coords()));
	}

	@Override
	public RPointImpl toDescriptorPoint()
	{
		return null;
	}

	@Override
	public RPointImpl toCachedPoint()
	{
		return this;
	}

	@Override
	public RPoint add(final RPoint other)
	{
		return operateL(PointBiOperator.SUM, other);
	}

	//	@Override
	//	public RPoint cross(final RPoint other)
	//	{
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
}
