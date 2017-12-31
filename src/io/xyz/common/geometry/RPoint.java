/**
 *
 */
package io.xyz.common.geometry;

import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.FilterUtil.filter;
import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.MapUtil.mapToInt;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;
import static io.xyz.common.funcutils.RangeUtil.rangeMap;
import static java.lang.Math.sqrt;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.DoubleStream;

/**
 * @author t
 *
 *         R^n real point
 */
public final class RPoint {
	private static final List<RPoint> ORIGIN = rangeMap(RPoint::emptyInit, 15);

	private final double[] coords;

	public RPoint(final double... ds) {
		coords = ds;
	}

	public RPoint(final RPoint src) {
		coords = Arrays.copyOf(src.coords, src.dim());
	}

	public static RPoint copy(final double... ds) {
		return new RPoint(Arrays.copyOf(ds, ds.length));
	}

	public static RPoint origin(final int dim) {
		return ORIGIN.get(dim);
	}

	private static RPoint emptyInit(final int dim) {
		return new RPoint(new double[dim]);
	}

	public double x() {
		return coords[0];
	}

	public double y() {
		return coords[1];
	}

	public double z() {
		return coords[2];
	}

	public double w() {
		return coords[3];
	}

	public double get(final int index) {
		return coords[index];
	}

	public double[] coords() {
		return Arrays.copyOf(coords, dim());
	}

	public int dim() {
		return coords.length;
	}

	// public IntStream dimStream()
	// {
	// return range(dim());
	// }

	public DoubleStream valStream() {
		return DoubleStream.of(coords);
	}

	public RPoint add(final RPoint p) {
		return new RPoint(combine((a, b) -> a+b, coords, p.coords));
	}

	public RPoint subtract(final RPoint p) {
		return new RPoint(combine((a, b) -> a-b, coords, p.coords));
	}

	public double magnitude() {
		return sqrt(sum(map(x -> x*x, coords)));
	}

	public RPoint scale(final double scale) {
		return new RPoint(map(x -> scale*x, coords));
	}

	public double distFrom(final RPoint other) {
		final DoubleBinaryOperator f = (a, b) -> Math.pow(a-b, 2);
		return sqrt(sum(combine(f, coords, other.coords)));
	}

	public static boolean dimensionsAgree(final RPoint... ps) {
		final int n = ps.length;
		if (n==0) {
			return true;
		}
		final int[] dims = mapToInt(p -> p.dim(), ps);
		return filter(x -> x==dims[0], dims).length==n;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime*result+Arrays.hashCode(coords);
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this==obj)
			return true;
		if (obj==null)
			return false;
		if (getClass()!=obj.getClass())
			return false;
		final RPoint other = (RPoint) obj;
		if (!Arrays.equals(coords, other.coords))
			return false;
		return true;
	}
}
