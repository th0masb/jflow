/**
 *
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.MapUtil.map;
import static io.xyz.common.funcutils.MapUtil.mapToDouble;
import static io.xyz.common.funcutils.PrimitiveUtil.max;
import static io.xyz.common.funcutils.PrimitiveUtil.min;
import static io.xyz.common.funcutils.RangeUtil.range;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;

import io.xyz.common.funcutils.PrimitiveUtil;
import io.xyz.common.geometry.PointMap;
import io.xyz.common.geometry.PointTransform;
import io.xyz.common.matrix.MatrixBiOperator;
import io.xyz.common.matrix.MatrixConstructor;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;

/**
 * @author t
 *
 *	Functional style immutable dense real matrix. Clean, powerful, flexible and thread-safe
 *	what more could one want?
 */
public class RMatrix implements PointTransform {

	//	private static final int STRING_FORMAT_PRECISION = 2;
	//	/** this separator cannot have special meaning in regex */
	//	private static final String ROW_SEPARATOR = ">";
	//	private static final String PAD = " ";

	private final double[] contents;
	private final short colDim;

	public RMatrix(final MatrixConstructor f, final int nrows, final int ncols) {
		this(ncols, mapToDouble(i -> f.map(i/ncols, i%ncols), range(nrows*ncols)));
	}

	protected RMatrix(final int ncols, final double[] contents) {
		this(ncols, asDescriptor(contents));
	}

	protected RMatrix(final int ncols, final DoubleRangeDescriptor contentDescriptor) {
		/* Check row and column numbers match and that dimension is not too high */
		assert ncols > 0 && len(contentDescriptor) % ncols == 0;
		assert (short) ncols == ncols;
		this.contents = collect(contentDescriptor);
		this.colDim = (short) ncols;
	}

	public double at(final int i, final int j) {
		assert inRange(i, j);
		return contents[i*colDim + j];
	}

	public DoubleRangeDescriptor row(final int index) {
		assert inRange(index, 0);
		return new ImmutableDoubleRangeDescriptor(colDim(), i -> contents[index*colDim() + i]);
	}

	public DoubleRangeDescriptor col(final int index) {
		assert inRange(0, index);
		return new ImmutableDoubleRangeDescriptor(rowDim(), i -> contents[i*colDim() + index]);
	}

	private boolean inRange(final int i, final int j) {
		return 0 <= i && i < rowDim() && 0 <= j && j <= colDim();
	}

	public MatrixConstructor functionDescriptor() {
		return this::at;
	}

	public int rowDim() {
		return len(contents)/colDim;
	}

	public int colDim() {
		return colDim;
	}

	public double minEntry() {
		return min(contents).getAsDouble();
	}

	public double maxEntry() {
		return max(contents).getAsDouble();
	}

	public RMatrix apply(final DoubleUnaryOperator f) {
		return new RMatrix(colDim, map(f, contents));
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
		throw new RuntimeException();
	}

	@Override
	public int domainDim() {
		return colDim();
	}

	@Override
	public int targetDim() {
		return rowDim();
	}

	//	@Override
	//	public String toString() {
	//		final int sfp = STRING_FORMAT_PRECISION;
	//		final DoubleToIntFunction f = d -> digitLength(sfp, d) + (d < 0? 1 : 0);
	//		final int maxLen = max(mapToInt(xs -> max(mapToInt(f, xs)).getAsInt(), contents)).getAsInt();
	//		final int formatLength = maxLen + 2;
	//		final List<List<String>> formatted = mapCollect(xs -> collect(mapToObj(x -> formatEntry(x, formatLength), xs)), contents);
	//		return concat("", ROW_SEPARATOR, mapCollect(xs -> concat("", PAD, xs), formatted));
	//	}

	//	/**
	//	 * TODO - Should probably align by max digit length per column
	//	 *
	//	 * @param d
	//	 * @param formattedlen
	//	 * @return
	//	 */
	//	private static String formatEntry(final double d, final int formattedlen) {
	//		final int sfp = STRING_FORMAT_PRECISION;
	//		final String strd = Double.toString(d);
	//		final int pointIndex = strd.indexOf(".");
	//		final String stripped = strd.substring(0, min(len(strd), pointIndex + sfp + 1));
	//		final int sPointIndex = stripped.indexOf(".");
	//		if (len(strd) < formattedlen) {
	//			final String rpad = concat(collect(mapToObj(i -> "0", range(3 - (len(stripped) - sPointIndex)))));
	//			final String lpad = concat(collect(mapToObj(i -> " ", range(formattedlen - len(stripped) - len(rpad) - 1))));
	//			return concat(lpad, strd, rpad);
	//		}
	//
	//		final String pad = concat(collect(mapToObj(i -> " ", range(formattedlen - len(stripped)))));
	//		return pad + stripped;
	//	}

	//	public void print() {
	//		final Pattern p = Pattern.compile(ROW_SEPARATOR);
	//		forEach(s -> System.out.println("(" + s + ")"), p.split(toString()));
	//	}



	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + colDim;
		result = prime * result + Arrays.hashCode(contents);
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RMatrix other = (RMatrix) obj;
		if (colDim != other.colDim)
			return false;
		if (!Arrays.equals(contents, other.contents))
			return false;
		return true;
	}

	public static void main(final String[] args) {
		//		final RMatrix A = new RMatrix(MatrixDescriptor.rand(5), 2, 2);
		//		System.out.println(Arrays.toString(A.row(0).toArray()));
		//		System.out.println(Arrays.toString(A.row(1).toArray()));
		//		System.out.println();
		//		final RMatrix B = new RMatrix(MatrixDescriptor.rand(5), 2, 1);
		//		System.out.println(Arrays.toString(B.row(0).toArray()));
		//		System.out.println(Arrays.toString(B.row(1).toArray()));
		//		System.out.println();
		//
		//		// System.out.println(Arrays.equals(A.row(0), B.row(0)));
		//		System.out.println(A.equals(B));
		//
		//		System.out.println(A.hashCode() + ", " + B.hashCode());
		//
		//		final RPoint p = RPoint.of(1, 2, 3.5), q = RPoint.of(1, 2, 3.5);
		//		System.out.println(p.equals(q));
		//		System.out.println(p.hashCode() == q.hashCode());
		//
		//		final MatrixBiOperator f = MatrixBiOperator::composition;
		//
		//		final double x = 2346.483456, y = 49855.3553;
		//		System.out.println(x);
		//		System.out.println(formatEntry(x, 12) + "|");
		//		System.out.println(formatEntry(y, 12) + "|");
		//		System.out.println(A.toString());
		//		System.out.println();
		//		A.print();
		//		System.out.println();
		//		B.print();
		//		System.out.println();
		//		f.apply(A, B).print();
	}
}
