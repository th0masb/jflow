/**
 *
 */
package io.xyz.common.geometry;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.mapToInt;
import static io.xyz.common.funcutils.PredicateUtil.all;
import static io.xyz.common.funcutils.RangeUtil.rangeMap;
import static io.xyz.common.funcutils.RangeUtil.rangeToDouble;

import java.util.List;
import java.util.function.DoubleUnaryOperator;

import io.xyz.common.function.MatrixBinaryOperator;
import io.xyz.common.function.MatrixDescriptor;

/**
 * @author t
 *
 */
public class RMatrix {

	private final List<double[]> contents;

	public RMatrix(final int nrows, final int ncols, final MatrixDescriptor f) {
		this(nrows, ncols, rangeMap(i -> rangeToDouble(j -> f.map(i, (int) j), ncols), nrows));
	}

	private RMatrix(final int nrows, final int ncols, final List<double[]> contents) {
		/* Check row and column numbers match. */
		assert nrows > 0 && ncols > 0;
		assert len(contents) == nrows && all(y -> y == ncols, mapToInt(x -> len(x), contents));
		this.contents = contents;
	}

	public double at(final int i, final int j) {
		assert inRange(i, j);
		return contents.get(i)[j];
	}

	private boolean inRange(final int i, final int j) {
		return 0 <= i && i < rowDim() && 0 <= j && j <= colDim();
	}

	private MatrixDescriptor descriptor() {
		return this::at;
	}

	public int rowDim() {
		return len(contents);
	}

	public int colDim() {
		return len(contents.get(0));
	}

	public RMatrix apply(final DoubleUnaryOperator f) {
		final MatrixDescriptor g = descriptor();
		final int nrow = rowDim(), ncol = colDim();
		return new RMatrix(nrow, ncol,
				rangeMap(i -> rangeToDouble(j -> f.applyAsDouble(g.map(i, (int) j)), ncol), nrow));
	}

	public RMatrix operateL(final MatrixBinaryOperator f, final RMatrix other) {
		return f.apply(this, other);
	}

	public RMatrix operateR(final MatrixBinaryOperator f, final RMatrix other) {
		return f.apply(other, this);
	}
}
