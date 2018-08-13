/**
 *
 */
package xawd.jflow.utilities;

import static xawd.jflow.utilities.CollectionUtil.sizeOf;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.factories.Iterate;

/**
 * Static methods for mapping primitive arrays element-wise with functions.
 * 
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class MapUtil
{
	private MapUtil()
	{
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a double array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return A double array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static double[] doubleMap(final DoubleUnaryOperator f, final double[] xs)
	{
		final double[] mapped = new double[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a double array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return A double array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static double[] doubleMap(final IntToDoubleFunction f, final int[] xs)
	{
		final double[] mapped = new double[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a double array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return A double array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static double[] doubleMap(final LongToDoubleFunction f, final long[] xs)
	{
		final double[] mapped = new double[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a double array.
	 *
	 * @param <E>
	 *            The upper bound on the type of elements which will be mapped.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source {@linkplain List}.
	 * @return A double array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static <E> double[] doubleMap(final ToDoubleFunction<? super E> f, final List<? extends E> xs)
	{
		final double[] mapped = new double[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsDouble(xs.get(i));
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an int array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return An int array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static int[] intMap(final IntUnaryOperator f, final int[] xs)
	{
		final int[] mapped = new int[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an int array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return An int array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static int[] intMap(final DoubleToIntFunction f, final double[] xs)
	{
		final int[] mapped = new int[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an int array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return An int array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static int[] intMap(final LongToIntFunction f, final long[] xs)
	{
		final int[] mapped = new int[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an int array.
	 *
	 * @param <E>
	 *            The upper bound on the type of elements which will be mapped.
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source {@linkplain List}.
	 * @return An int array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static <E> int[] intMap(final ToIntFunction<? super E> f, final List<? extends E> xs)
	{
		final int[] mapped = new int[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsInt(xs.get(i));
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an long array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return A long array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static long[] longMap(final LongUnaryOperator f, final long[] xs)
	{
		final long[] mapped = new long[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an long array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return A long array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static long[] longMap(final IntToLongFunction f, final int[] xs)
	{
		final long[] mapped = new long[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an long array.
	 *
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 * @return A long array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static long[] longMap(final DoubleToLongFunction f, final double[] xs)
	{
		final long[] mapped = new long[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an long array.
	 *
	 * @param <E>
	 *            The upper bound on the type of elements which will be mapped.
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source {@linkplain List}.
	 * @return A long array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static <E> long[] longMap(final ToLongFunction<? super E> f, final List<? extends E> xs)
	{
		final long[] mapped = new long[sizeOf(xs)];
		for (int i = 0; i < sizeOf(xs); i++) {
			mapped[i] = f.applyAsLong(xs.get(i));
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a {@linkplain List}.
	 *
	 * @param <E>
	 *            The upper bound on the type of elements which will be mapped.
	 *
	 * @param <R>
	 *            The target type of the mapping operation.
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source {@linkplain List}.
	 *
	 * @return A List of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static <E, R> List<R> objMap(final Function<? super E, R> f, final List<? extends E> xs)
	{
		return Iterate.over(xs).map(f).toList();
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a {@linkplain List}.
	 *
	 * @param <R>
	 *            The target type of the mapping operation.
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 *
	 * @return A List of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static <R> List<R> objMap(final IntFunction<R> f, final int[] xs)
	{
		return Iterate.overInts(xs).mapToObject(f).toList();
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a {@linkplain List}.
	 *
	 * @param <R>
	 *            The target type of the mapping operation.
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 *
	 * @return A List of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static <R> List<R> objMap(final DoubleFunction<R> f, final double[] xs)
	{
		return Iterate.overDoubles(xs).mapToObject(f).toList();
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a {@linkplain List}.
	 *
	 * @param <R>
	 *            The target type of the mapping operation.
	 * @param f
	 *            The function defining the mapping.
	 * @param xs
	 *            The source array.
	 *
	 * @return A List of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static <R> List<R> objMap(final LongFunction<R> f, final long[] xs)
	{
		return Iterate.overLongs(xs).mapToObject(f).toList();
	}
}
