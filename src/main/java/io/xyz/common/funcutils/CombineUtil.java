/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;
import static io.xyz.common.funcutils.CollectionUtil.asList;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.PrimitiveUtil.min;
import static io.xyz.common.funcutils.PrimitiveUtil.sum;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;

import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.generators.IntGenerator;
import io.xyz.common.generators.impl.ImmutableDoubleGenerator;
import io.xyz.common.generators.impl.ImmutableIntGenerator;
import io.xyz.common.generators.impl.ImmutableGenerator;
import io.xyz.common.generators.Generator;

/**
 * @author t
 */
public final class CombineUtil {
	private CombineUtil()
	{
	}

	/**
	 * @return The String xs[0] + delimiter + xs[1] + ... + delimiter + xs[n-1]
	 */
	public static String concat(final String delimiter, final List<String> xs)
	{
		final StringBuilder sb = new StringBuilder();
		final int length = len(xs);
		for (int i = 0; i < length; i++) {
			sb.append(xs.get(i));
			if (i < length - 1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	/**
	 * @return The String xs[0] + delimiter + xs[1] + ... + delimiter + xs[n-1]
	 */
	public static String concat(final String delimiter, final Generator<String> xs)
	{
		final StringBuilder sb = new StringBuilder();
		final int length = len(xs);
		for (int i = 0; i < length; i++) {
			sb.append(xs.get(i));
			if (i < length - 1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	public static String concat(final List<String> xs)
	{
		return concat("", xs);
	}

	public static String concat(final String delimiter, final String[] xs)
	{
		return concat(delimiter, asList(xs));
	}

	public static String concat(final String... xs)
	{
		return concat(asList(xs));
	}

	@SafeVarargs
	public static <T> List<T> append(final List<T> xs, final T... ys)
	{
		final List<T> newList = new ArrayList<>(len(xs) + len(ys));
		newList.addAll(xs);
		for (final T y : ys) {
			xs.add(y);
		}
		return newList;
	}

	// -------------------------------------------------------------------

	public static DoubleGenerator combine(final DoubleBinaryOperator f, final double[] a, final double[] b)
	{
		return combine(f, asDescriptor(a), asDescriptor(b));
	}

	public static DoubleGenerator combine(final DoubleBinaryOperator f, final DoubleGenerator a, final DoubleGenerator b)
	{
		final int size = min(len(a), len(b));
		return ImmutableDoubleGenerator.of(size, i -> f.applyAsDouble(a.get(i), b.get(i)));
	}

	public static IntGenerator combine(final IntBinaryOperator f, final IntGenerator a, final IntGenerator b)
	{
		final int size = min(len(a), len(b));
		return ImmutableIntGenerator.of(size, i -> f.applyAsInt(a.get(i), b.get(i)));
	}

	public static IntGenerator combine(final IntBinaryOperator f, final int[] a, final int[] b)
	{
		return combine(f, asDescriptor(a), asDescriptor(b));
	}

	public static <T> Generator<T> combine(final BinaryOperator<T> f, final Generator<? extends T> a, final Generator<? extends T> b)
	{
		final int size = min(len(a), len(b));
		return new ImmutableGenerator<>(size, i -> f.apply(a.get(i), b.get(i)));
	}

	public static <T> Generator<T> combine(final BinaryOperator<T> f, final List<? extends T> a, final List<? extends T> b)
	{
		return combine(f, asDescriptor(a), asDescriptor(b));
	}

	// -------------------------------------------------------------------

	//	public static DoubleRangeDescriptor multiCombine(final DoubleCompressor f, final DoubleRangeDescriptor... xs)
	//	{
	//		assert len(xs) > 1;
	//		final int size = min(intRange(x -> len(x), xs)).getAsInt();
	//		final IntFunction<DoubleRangeDescriptor> rowMapper = i -> doubleRange(x -> x.get(i), asDescriptor(xs));
	//		return doubleRange(i -> f.compress(rowMapper.apply(i)), size);
	//	}

	// -------------------------------------------------------------------

	public static double dotProduct(final DoubleGenerator a, final DoubleGenerator b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsDouble();
	}

	public static double dotProduct(final double[] a, final double[] b)
	{
		return dotProduct(asDescriptor(a), asDescriptor(b));
	}

	public static double dotProduct(final IntGenerator a, final IntGenerator b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsInt();
	}

	public static double dotProduct(final int[] a, final int[] b)
	{
		return dotProduct(asDescriptor(a), asDescriptor(b));
	}

	public static void main(final String[] args)
	{
	}
}
