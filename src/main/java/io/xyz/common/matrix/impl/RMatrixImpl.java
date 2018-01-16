/**
 *
 */
package io.xyz.common.matrix.impl;

import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;
import static io.xyz.common.funcutils.CollectionUtil.asString;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CombineUtil.combine;
import static io.xyz.common.funcutils.MapUtil.boolRange;
import static io.xyz.common.funcutils.MapUtil.doubleRange;
import static io.xyz.common.funcutils.PredicateUtil.all;
import static io.xyz.common.funcutils.PrimitiveUtil.isZero;
import static io.xyz.common.funcutils.StreamUtil.collect;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;

import io.xyz.common.funcutils.MapUtil;
import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.generators.impl.ImmutableDoubleGenerator;
import io.xyz.common.matrix.MatrixConstructor;
import io.xyz.common.matrix.RMatrix;
import io.xyz.common.matrix.RPoint;

/**
 * @author t
 *
 *         Functional style immutable dense real matrix. Clean, powerful,
 *         flexible and thread-safe what more could one want?
 */
public class RMatrixImpl implements RMatrix {

	// private static final int STRING_FORMAT_PRECISION = 2;
	// /** this separator cannot have special meaning in regex */
	// private static final String ROW_SEPARATOR = ">";
	// private static final String PAD = " ";

	private final double[] contents;
	private final short colDim;

	public RMatrixImpl(final MatrixConstructor f, final int nrows, final int ncols)
	{
		this(ncols, doubleRange(i -> f.map(i / ncols, i % ncols), MapUtil.range(nrows * ncols)));
	}

	protected RMatrixImpl(final int ncols, final double[] contents)
	{
		this(ncols, asDescriptor(contents));
	}

	/**
	 * @param ncols
	 *        - column count of the matrix
	 * @param contentDescriptor
	 *        - the values contained in the matrix
	 */
	RMatrixImpl(final int ncols, final DoubleGenerator contentDescriptor)
	{
		/* Check row and column numbers match and that dimension is not too high */
		assert all(boolRange(x -> Double.isFinite(x), contentDescriptor)) : asString(contentDescriptor.toArray());
		assert all(boolRange(x -> !Double.isNaN(x), contentDescriptor)) : asString(contentDescriptor.toArray());
		assert ncols > 0 && len(contentDescriptor) % ncols == 0;
		assert (short) ncols == ncols;

		this.contents = collect(contentDescriptor);
		this.colDim = (short) ncols;
	}

	@Override
	public double at(final int i, final int j)
	{
		assert inRange(i, j);
		return contents[i * colDim + j];
	}

	@Override
	public DoubleGenerator row(final int index)
	{
		assert inRange(index, 0);
		return ImmutableDoubleGenerator.of(colDim(), i -> contents[index * colDim() + i]);
	}

	@Override
	public DoubleGenerator col(final int index)
	{
		assert inRange(0, index);
		return ImmutableDoubleGenerator.of(rowDim(), i -> contents[i * colDim() + index]);
	}

	@Override
	public RMatrix apply(final DoubleUnaryOperator f)
	{
		return new RMatrixImpl(colDim, MapUtil.doubleRange(f, contents));
	}

	@Override
	public int rowDim()
	{
		return len(contents) / colDim;
	}

	@Override
	public int colDim()
	{
		return colDim;
	}

	@Override
	public double flatAt(final int index)
	{
		assert flatInRange(index);
		return contents[index];
	}

	@Override
	public RMatrix toDescriptorForm()
	{
		return new RMatrixDescriptor(this::at, (short) rowDim(), colDim);
	}

	@Override
	public RMatrix toCachedForm()
	{
		return this;
	}

	@Override
	public RMatrix composeL(final RMatrix other)
	{
		return operateL(Matrices.COMPOSE, other);
	}

	@Override
	public RMatrix add(final RMatrix other)
	{
		return operateL(Matrices.SUM, other);
	}

	@Override
	public RPoint composeL(final RPoint other)
	{
		return operateL(Matrices.POINT_COMPOSITION, other);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + colDim;
		result = prime * result + Arrays.hashCode(contents);
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RMatrix))
			return false;
		if ((this instanceof RPoint) && !(obj instanceof RPoint))
			return false;
		final RMatrix other = ((RMatrix) obj).toCachedForm();
		final int otherColDim = (other instanceof RPoint)? other.rowDim() : other.colDim();
		if (colDim != otherColDim)
			return false;
		if (!arraysEqual(contents, collect(other.flatContents())))
			return false;
		return true;
	}

	private static boolean arraysEqual(final double[] a, final double[] b)
	{
		if (len(a) != len(b)) {
			return false;
		}
		return all(d -> isZero(d), combine((x, y) -> x - y, a, b));
	}

	// @Override
	// public String toString() {
	// final int sfp = STRING_FORMAT_PRECISION;
	// final DoubleToIntFunction f = d -> digitLength(sfp, d) + (d < 0? 1 : 0);
	// final int maxLen = max(mapToInt(xs -> max(mapToInt(f, xs)).getAsInt(),
	// contents)).getAsInt();
	// final int formatLength = maxLen + 2;
	// final List<List<String>> formatted = mapCollect(xs -> collect(mapToObj(x ->
	// formatEntry(x, formatLength), xs)), contents);
	// return concat("", ROW_SEPARATOR, mapCollect(xs -> concat("", PAD, xs),
	// formatted));
	// }

	// /**
	// * TODO - Should probably align by max digit length per column
	// *
	// * @param d
	// * @param formattedlen
	// * @return
	// */
	// private static String formatEntry(final double d, final int formattedlen) {
	// final int sfp = STRING_FORMAT_PRECISION;
	// final String strd = Double.toString(d);
	// final int pointIndex = strd.indexOf(".");
	// final String stripped = strd.substring(0, min(len(strd), pointIndex + sfp +
	// 1));
	// final int sPointIndex = stripped.indexOf(".");
	// if (len(strd) < formattedlen) {
	// final String rpad = concat(collect(mapToObj(i -> "0", range(3 -
	// (len(stripped) - sPointIndex)))));
	// final String lpad = concat(collect(mapToObj(i -> " ", range(formattedlen -
	// len(stripped) - len(rpad) - 1))));
	// return concat(lpad, strd, rpad);
	// }
	//
	// final String pad = concat(collect(mapToObj(i -> " ", range(formattedlen -
	// len(stripped)))));
	// return pad + stripped;
	// }

	// public void print() {
	// final Pattern p = Pattern.compile(ROW_SEPARATOR);
	// forEach(s -> System.out.println("(" + s + ")"), p.split(toString()));
	// }

	// @Override
	// public int hashCode()
	// {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + colDim;
	// result = prime * result + Arrays.hashCode(contents);
	// return result;
	// }
	//
	// @Override
	// public boolean equals(final Object obj)
	// {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// final RMatrixImpl other = (RMatrixImpl) obj;
	// if (colDim != other.colDim)
	// return false;
	// if (!Arrays.equals(contents, other.contents))
	// return false;
	// return true;
	// }

	public static void main(final String[] args)
	{
		// final RMatrix A = new RMatrix(MatrixDescriptor.rand(5), 2, 2);
		// System.out.println(Arrays.toString(A.row(0).toArray()));
		// System.out.println(Arrays.toString(A.row(1).toArray()));
		// System.out.println();
		// final RMatrix B = new RMatrix(MatrixDescriptor.rand(5), 2, 1);
		// System.out.println(Arrays.toString(B.row(0).toArray()));
		// System.out.println(Arrays.toString(B.row(1).toArray()));
		// System.out.println();
		//
		// // System.out.println(Arrays.equals(A.row(0), B.row(0)));
		// System.out.println(A.equals(B));
		//
		// System.out.println(A.hashCode() + ", " + B.hashCode());
		//
		// final RPoint p = RPoint.of(1, 2, 3.5), q = RPoint.of(1, 2, 3.5);
		// System.out.println(p.equals(q));
		// System.out.println(p.hashCode() == q.hashCode());
		//
		// final MatrixBiOperator f = MatrixBiOperator::composition;
		//
		// final double x = 2346.483456, y = 49855.3553;
		// System.out.println(x);
		// System.out.println(formatEntry(x, 12) + "|");
		// System.out.println(formatEntry(y, 12) + "|");
		// System.out.println(A.toString());
		// System.out.println();
		// A.print();
		// System.out.println();
		// B.print();
		// System.out.println();
		// f.apply(A, B).print();
	}
}
