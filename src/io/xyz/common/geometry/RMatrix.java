/**
 *
 */
package io.xyz.common.geometry;

import static io.xyz.common.funcutils.CollectionUtil.forEach;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.concat;
import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.MapUtil.mapToDouble;
import static io.xyz.common.funcutils.MapUtil.mapToInt;
import static io.xyz.common.funcutils.MapUtil.mapToObj;
import static io.xyz.common.funcutils.PredicateUtil.all;
import static io.xyz.common.funcutils.PrimitiveUtil.digitLength;
import static io.xyz.common.funcutils.PrimitiveUtil.max;
import static io.xyz.common.funcutils.PrimitiveUtil.min;
import static io.xyz.common.funcutils.RangeUtil.rangeMap;
import static io.xyz.common.funcutils.RangeUtil.rangeToDouble;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.xyz.common.function.MatrixBiOperator;
import io.xyz.common.function.MatrixDescriptor;
import io.xyz.common.funcutils.PrimitiveUtil;

/**
 * @author t
 *
 */
public class RMatrix implements PointTransform {

	private static final int STRING_FORMAT_PRECISION = 2;
	/** this separator cannot have special meaning in regex */
	private static final String ROW_SEPARATOR = ">";
	private static final String PAD = " ";

	final List<double[]> contents;
	// private final int hashCode; TODO - Should I cache the hash?

	public RMatrix(final MatrixDescriptor f, final int nrows, final int ncols) {
		this(nrows, ncols, rangeMap(i -> rangeToDouble(j -> f.map(i, (int) j), ncols), nrows));
	}

	protected RMatrix(final int nrows, final int ncols, final List<double[]> contents) {
		/* Check row and column numbers match. */
		assert nrows > 0 && ncols > 0;
		assert len(contents) == nrows && all(y -> y == ncols, mapToInt(x -> len(x), contents));
		this.contents = contents;
		// this.hashCode = initHashCode();
	}

	public double at(final int i, final int j) {
		assert inRange(i, j);
		return contents.get(i)[j];
	}

	public double[] row(final int index) {
		assert inRange(index, 0);
		return Arrays.copyOf(contents.get(index), colDim());
	}

	public IntToDoubleFunction rowF(final int index) {
		assert inRange(index, 0);
		return i -> contents.get(index)[i];
	}

	public double[] col(final int index) {
		assert inRange(0, index);
		return Arrays.copyOf(contents.get(index), colDim());
	}

	public IntToDoubleFunction colF(final int index) {
		assert inRange(0, index);
		return i -> contents.get(i)[index];
	}

	private boolean inRange(final int i, final int j) {
		return 0 <= i && i < rowDim() && 0 <= j && j <= colDim();
	}

	private MatrixDescriptor functionDescriptor() {
		return this::at;
	}

	public int rowDim() {
		return len(contents);
	}

	public int colDim() {
		return len(contents.get(0));
	}

	public double minEntry() {
		return min(mapToDouble(x -> min(x), contents));
	}

	public double maxEntry() {
		return max(mapToDouble(x -> max(x), contents));
	}

	public RMatrix apply(final DoubleUnaryOperator f) {
		final MatrixDescriptor g = functionDescriptor();
		final int nrow = rowDim(), ncol = colDim();
		return new RMatrix(nrow, ncol,
				rangeMap(i -> rangeToDouble(j -> f.applyAsDouble(g.map(i, (int) j)), ncol), nrow));
	}

	public RMatrix scale(final double scaleFactor) {
		return apply(x -> scaleFactor * x);
	}

	public RMatrix abs() {
		return apply(x -> PrimitiveUtil.abs(x));
	}

	public RMatrix operateL(final MatrixBiOperator f, final RMatrix other) {
		return f.apply(this, other);
	}

	public RMatrix operateR(final MatrixBiOperator f, final RMatrix other) {
		return f.apply(other, this);
	}

	@Override
	public PointMap getMapping() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int domainDim() {
		return colDim();
	}

	@Override
	public int targetDim() {
		return rowDim();
	}

	@Override
	public String toString() {
		final int sfp = STRING_FORMAT_PRECISION;
		final DoubleToIntFunction f = d -> digitLength(sfp, d) + (d < 0? 1 : 0);
		final int maxLen = max(mapToInt(xs -> max(mapToInt(f, xs)), contents));
		final int formatLength = maxLen + 2;
		final List<List<String>> formatted = map(xs -> mapToObj(x -> formatEntry(x, formatLength), xs), contents);
		return concat("", ROW_SEPARATOR, map(xs -> concat("", PAD, xs), formatted));
	}

	/**
	 * TODO - Should probably align by max digit length per column
	 * 
	 * @param d
	 * @param formattedlen
	 * @return
	 */
	private static String formatEntry(final double d, final int formattedlen) {
		final int sfp = STRING_FORMAT_PRECISION;
		final String strd = Double.toString(d);
		final int pointIndex = strd.indexOf(".");
		final String stripped = strd.substring(0, min(len(strd), pointIndex + sfp + 1));
		final int sPointIndex = stripped.indexOf(".");
		if (len(strd) < formattedlen) {
			final String rpad = concat(rangeMap(i -> "0", 3 - (len(stripped) - sPointIndex)));
			final String lpad = concat(rangeMap(i -> " ", formattedlen - len(stripped) - len(rpad) - 1));
			return concat(lpad, strd, rpad);
		}

		final String pad = concat(rangeMap(i -> " ", formattedlen - len(stripped)));
		return pad + stripped;
	}

	public void print() {
		final Pattern p = Pattern.compile(ROW_SEPARATOR);
		forEach(s -> System.out.println("(" + s + ")"), p.split(toString()));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		/*
		 * Should I cache the hash?
		 */
		// return hashCode;
		final HashCodeBuilder builder = new HashCodeBuilder(15, 37);
		for (int i = 0; i < rowDim(); i++) {
			builder.append(contents.get(i));
		}
		return builder.toHashCode();
	}

	@SuppressWarnings("unused")
	private int initHashCode() {
		final HashCodeBuilder builder = new HashCodeBuilder(15, 37);
		for (int i = 0; i < rowDim(); i++) {
			builder.append(contents.get(i));
		}
		return builder.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RMatrix other = (RMatrix) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!all(BitArray.of(i -> Arrays.equals(contents.get(i), other.contents.get(i)), rowDim()))) {
			return false;
		}
		return true;
	}

	public static void main(final String[] args) {
		final RMatrix A = new RMatrix(MatrixDescriptor.rand(5), 2, 2);
		System.out.println(Arrays.toString(A.row(0)));
		System.out.println(Arrays.toString(A.row(1)));
		System.out.println();
		final RMatrix B = new RMatrix(MatrixDescriptor.rand(5), 2, 1);
		System.out.println(Arrays.toString(B.row(0)));
		System.out.println(Arrays.toString(B.row(1)));
		System.out.println();

		// System.out.println(Arrays.equals(A.row(0), B.row(0)));
		System.out.println(A.equals(B));

		System.out.println(A.hashCode() + ", " + B.hashCode());

		final RPoint p = RPoint.of(1, 2, 3.5), q = RPoint.of(1, 2, 3.5);
		System.out.println(p.equals(q));
		System.out.println(p.hashCode() == q.hashCode());

		final MatrixBiOperator f = MatrixBiOperator::composition;

		final double x = 2346.483456, y = 49855.3553;
		System.out.println(x);
		System.out.println(formatEntry(x, 12) + "|");
		System.out.println(formatEntry(y, 12) + "|");
		System.out.println(A.toString());
		System.out.println();
		A.print();
		System.out.println();
		B.print();
		System.out.println();
		f.apply(A, B).print();
	}
}
