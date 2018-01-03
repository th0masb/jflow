/**
 *
 */
package io.xyz.common.geometry;

import static io.xyz.common.funcutils.CollectionUtil.allEqual;
import static io.xyz.common.funcutils.CollectionUtil.asList;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.MapUtil.mapCollect;
import static io.xyz.common.funcutils.PredicateUtil.all;
import static io.xyz.common.funcutils.PrimitiveUtil.isZero;
import static io.xyz.common.funcutils.PrimitiveUtil.pow;
import static io.xyz.common.funcutils.PrimitiveUtil.sqrt;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;
import static io.xyz.common.funcutils.RangeUtil.rangeMap;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import org.la4j.Vector;
import org.la4j.vector.DenseVector;

import io.xyz.common.funcutils.PrimitiveUtil;

/**
 * @author t
 *
 *         R^n real point represented as a column vector, i.e. a nx1 matrix.
 */
public final class RPoint extends RMatrix {
	private static final List<RPoint> ORIGIN = rangeMap(RPoint::emptyInit, 15);

	public RPoint(final double... ds) {
		super(1, len(ds), asList(ds));
	}

	public static RPoint of(final double... ds) {
		return new RPoint(Arrays.copyOf(ds, ds.length));
	}

	public static RPoint copy(final RPoint src) {
		return of(src.uncopiedCoords());
	}

	public static RPoint origin(final int dim) {
		return ORIGIN.get(dim);
	}

	private static RPoint emptyInit(final int dim) {
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

	/**
	 * We store a point in a row vector but it is a column vector.
	 */
	@Override
	public double at(final int i, final int j) {
		return super.at(j, i);
	}

	public double[] coords() {
		return super.row(0);
	}

	private double[] uncopiedCoords() {
		return contents.get(0);
	}

	public int dim() {
		return super.colDim();
	}

	public RPoint add(final RPoint p) {
		return new RPoint(combine((a, b) -> a + b, uncopiedCoords(), p.uncopiedCoords()));
	}

	public RPoint subtract(final RPoint p) {
		return new RPoint(combine((a, b) -> a - b, uncopiedCoords(), p.uncopiedCoords()));
	}

	@Override
	public RPoint scale(final double scale) {
		return new RPoint(mapCollect(x -> scale * x, uncopiedCoords()));
	}

	@Override
	public RPoint abs() {
		return new RPoint(mapCollect(x -> PrimitiveUtil.abs(x), uncopiedCoords()));
	}

	public double magnitude() {
		return sqrt(sum(mapCollect(x -> x * x, uncopiedCoords())));
	}

	public RPoint normalise() {
		if (isOrigin()) {
			return this;
		}
		return scale(1 / magnitude());
	}

	public double distFrom(final RPoint other) {
		final DoubleBinaryOperator f = (a, b) -> pow(2, a - b);
		return sqrt(sum(combine(f, uncopiedCoords(), other.uncopiedCoords())));
	}

	public double dot(final RPoint other) {
		final DoubleBinaryOperator f = (a, b) -> a * b;
		return sum(combine(f, uncopiedCoords(), other.uncopiedCoords()));
	}

	public boolean isOrigin() {
		return all(x -> isZero(x), uncopiedCoords());
	}

	public Vector toVector() {
		return DenseVector.fromArray(coords());
	}

	public static boolean dimensionsAgree(final RPoint... ps) {
		return allEqual(mapCollect(p -> p.dim(), ps));
	}
}
