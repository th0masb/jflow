/**
 *
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.allEqual;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.MapUtil.mapCollect;
import static io.xyz.common.funcutils.MapUtil.mapToObj;
import static io.xyz.common.funcutils.PredicateUtil.all;
import static io.xyz.common.funcutils.PrimitiveUtil.isZero;
import static io.xyz.common.funcutils.PrimitiveUtil.pow;
import static io.xyz.common.funcutils.PrimitiveUtil.sqrt;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;
import static io.xyz.common.funcutils.RangeUtil.range;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.Arrays;
import java.util.List;

import io.xyz.common.funcutils.PrimitiveUtil;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;

/**
 * @author t
 *
 *         R^n real point represented as a column vector, i.e. a nx1 matrix.
 */
public final class RPoint extends RMatrix {
	private static final List<RPoint> ORIGIN = collect(mapToObj(RPoint::emptyInit, range(1, 15)));

	public RPoint(final double... ds) {
		this(ImmutableDoubleRangeDescriptor.from(ds));
	}

	public RPoint(final DoubleRangeDescriptor xs) {
		super(len(xs), xs);
	}

	public static RPoint of(final double... ds) {
		return new RPoint(Arrays.copyOf(ds, ds.length));
	}

	public static RPoint copy(final RPoint src) {
		return new RPoint(src.coords());
	}

	public static RPoint origin(final int dim) {
		return ORIGIN.get(dim);
	}

	private static RPoint emptyInit(final int dim) {
		assert dim > 0;
		return new RPoint(new double[dim]);
	}

	public double x() {
		return super.at(0, 0);
	}

	public double y() {
		return super.at(0, 1);
	}

	public double z() {
		return super.at(0, 2);
	}

	public double get(final int index) {
		return super.at(0, index);
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

	public DoubleRangeDescriptor coords() {
		return super.row(0);
	}

	public int dim() {
		return super.colDim();
	}

	public RPoint add(final RPoint p) {
		return new RPoint(combine((a, b) -> a + b, coords(), p.coords()));
	}

	public RPoint subtract(final RPoint p) {
		return new RPoint(combine((a, b) -> a - b, coords(), p.coords()));
	}

	@Override
	public RPoint scale(final double scale) {
		return new RPoint(map(x -> scale * x, coords()));
	}

	@Override
	public RPoint abs() {
		return new RPoint(map(x -> PrimitiveUtil.abs(x), coords()));
	}

	public double magnitude() {
		return sqrt(sum(map(x -> x * x, coords())).getAsDouble());
	}

	public RPoint normalise() {
		if (isOrigin()) {
			return this;
		}
		return scale(1 / magnitude());
	}

	public double distFrom(final RPoint other) {
		return sqrt(sum(combine((a, b) -> pow(a - b, 2), coords(), other.coords())).getAsDouble());
	}

	public double dot(final RPoint other) {
		return sum(combine((a, b) -> a * b, coords(), other.coords())).getAsDouble();
	}

	public boolean isOrigin() {
		return all(x -> isZero(x), coords());
	}

	//	public Vector toVector() {
	//		return DenseVector.fromArray(coords());
	//	}

	public static boolean dimensionsAgree(final RPoint... ps) {
		return allEqual(mapCollect(p -> p.dim(), ps));
	}
}
