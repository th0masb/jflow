/**
 *
 */
package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

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

/**
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class MapUtil
{
	private MapUtil()
	{
	}

	/**
	 * The doubleMap function provides a unified way of mapping sequences to primitive double sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static double[] doubleMap(final DoubleUnaryOperator f, final double[] xs)
	{
		final double[] mapped = new double[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}


	/**
	 * The doubleMap function provides a unified way of mapping sequences to primitive double sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static double[] doubleMap(final IntToDoubleFunction f, final int[] xs)
	{
		final double[] mapped = new double[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}


	/**
	 * The doubleMap function provides a unified way of mapping sequences to primitive double sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static double[] doubleMap(final LongToDoubleFunction f, final long[] xs)
	{
		final double[] mapped = new double[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}

	/**
	 * The doubleMap function provides a unified way of mapping sequences to primitive double sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T> double[] doubleMap(final ToDoubleFunction<T> f, final List<? extends T> xs)
	{
		final double[] mapped = new double[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsDouble(xs.get(i));
		}
		return mapped;
	}

	/**
	 * The intMap function provides a unified way of mapping sequences to primitive int sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static int[] intMap(final IntUnaryOperator f, final int[] xs)
	{
		final int[] mapped = new int[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * The intMap function provides a unified way of mapping sequences to primitive int sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static int[] intMap(final DoubleToIntFunction f, final double[] xs)
	{
		final int[] mapped = new int[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * The intMap function provides a unified way of mapping sequences to primitive int sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static int[] intMap(final LongToIntFunction f, final long[] xs)
	{
		final int[] mapped = new int[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * The intMap function provides a unified way of mapping sequences to primitive int sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T> int[] intMap(final ToIntFunction<T> f, final List<? extends T> xs)
	{
		final int[] mapped = new int[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsInt(xs.get(i));
		}
		return mapped;
	}


	/**
	 * The longMap function provides a unified way of mapping sequences to primitive long sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static long[] longMap(final LongUnaryOperator f, final long[] xs)
	{
		final long[] mapped = new long[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}


	/**
	 * The longMap function provides a unified way of mapping sequences to primitive long sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static long[] longMap(final IntToLongFunction f, final int[] xs)
	{
		final long[] mapped = new long[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}


	/**
	 * The longMap function provides a unified way of mapping sequences to primitive long sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static long[] longMap(final DoubleToLongFunction f, final double[] xs)
	{
		final long[] mapped = new long[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}

	/**
	 * The longMap function provides a unified way of mapping sequences to primitive long sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T> long[] longMap(final ToLongFunction<T> f, final List<? extends T> xs)
	{
		final long[] mapped = new long[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsLong(xs.get(i));
		}
		return mapped;
	}


	/**
	 * The objMap function provides a unified way of mapping sequences to object sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being arrays
	 * which are mapped to {@link ImmutableList} instances.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T, R> List<R> objMap(final Function<T, R> f, final List<? extends T> xs)
	{
		throw new RuntimeException();
	}


	/**
	 * The objMap function provides a unified way of mapping sequences to object sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being arrays
	 * which are mapped to {@link ImmutableList} instances.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T> List<T> objMap(final IntFunction<T> f, final int[] xs)
	{
		throw new RuntimeException();
	}


	/**
	 * The objMap function provides a unified way of mapping sequences to object sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being arrays
	 * which are mapped to {@link ImmutableList} instances.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T> List<T> objMap(final DoubleFunction<T> f, final double[] xs)
	{
		throw new RuntimeException();
	}

	/**
	 * The objMap function provides a unified way of mapping sequences to object sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being arrays
	 * which are mapped to {@link ImmutableList} instances.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T> List<T> objMap(final LongFunction<T> f, final long[] xs)
	{
		throw new RuntimeException();
	}
}
