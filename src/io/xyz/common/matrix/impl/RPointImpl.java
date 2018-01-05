/**
 *
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.concat;
import static io.xyz.common.funcutils.MapUtil.doubleRange;
import static io.xyz.common.funcutils.MapUtil.objRange;
import static io.xyz.common.funcutils.PrimitiveUtil.digitLength;
import static io.xyz.common.funcutils.PrimitiveUtil.isZero;
import static io.xyz.common.funcutils.RangeUtil.range;
import static io.xyz.common.funcutils.StreamUtil.collect;

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

	private static final List<RPoint> ORIGIN = collect(objRange(RPointImpl::emptyInit, range(1, 15)));

	public RPointImpl(final double... ds) {
		this(ImmutableDoubleRangeDescriptor.from(ds));
	}

	public RPointImpl(final DoubleRangeDescriptor xs) {
		super(len(xs), xs);
	}

	public static RPoint origin(final int dim) {
		return ORIGIN.get(dim);
	}

	private static RPoint emptyInit(final int dim) {
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
	public RPoint apply(final DoubleUnaryOperator f)
	{
		return new RPointImpl(doubleRange(f, coords()));
	}

	@Override
	public RPoint toDescriptorPoint()
	{
		return new RPointDescriptor(this);
	}

	@Override
	public RPoint toCachedPoint()
	{
		return this;
	}

	@Override
	public RPoint add(final RPoint other)
	{
		return operateL(PointBiOperator.SUM, other);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("(");
		final double[] rounded = doubleRange(d -> isZero(d)? 0 : d, this).toArray();
		sb.append(concat(" ", objRange(d -> Double.toString(d).substring(0, digitLength(2, d)), rounded)));
		sb.append(")");
		return sb.toString();
	}
}
