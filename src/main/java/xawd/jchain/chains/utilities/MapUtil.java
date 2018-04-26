/**
 *
 */
package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.len;
import static xawd.jchain.chains.utilities.CompositionUtil.compose;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

import xawd.jchain.chains.Chain;
import xawd.jchain.chains.DoubleChain;
import xawd.jchain.chains.IntChain;
import xawd.jchain.chains.LongChain;
import xawd.jchain.chains.misc.BitArray;
import xawd.jchain.chains.rangedfunction.RangedDoubleFunction;
import xawd.jchain.chains.rangedfunction.RangedFunction;
import xawd.jchain.chains.rangedfunction.RangedIntFunction;
import xawd.jchain.chains.rangedfunction.RangedLongFunction;

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
	public static RangedDoubleFunction doubleMap(final DoubleUnaryOperator f, final DoubleChain xs)
	{
		return RangedDoubleFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static ImmutableDoubleArray doubleMap(final DoubleUnaryOperator f, final ImmutableDoubleArray xs)
	{
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsDouble(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static RangedDoubleFunction doubleMap(final IntToDoubleFunction f, final IntChain xs)
	{
		return RangedDoubleFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static ImmutableDoubleArray doubleMap(final IntToDoubleFunction f, final ImmutableIntArray xs)
	{
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsDouble(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static RangedDoubleFunction doubleMap(final LongToDoubleFunction f, final LongChain xs)
	{
		return RangedDoubleFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static ImmutableDoubleArray doubleMap(final LongToDoubleFunction f, final ImmutableLongArray xs)
	{
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsDouble(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static <T> RangedDoubleFunction doubleMap(final ToDoubleFunction<T> f, final Chain<T> xs)
	{
		return RangedDoubleFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	 * The doubleMap function provides a unified way of mapping sequences to primitive double sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T, E extends T> double[] doubleMap(final ToDoubleFunction<T> f, final E[] xs)
	{
		final double[] mapped = new double[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
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
	public static RangedIntFunction intMap(final IntUnaryOperator f, final IntChain xs)
	{
		return RangedIntFunction.of(f.compose(xs.getDescriptor()), len(xs));
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
	public static ImmutableIntArray intMap(final IntUnaryOperator f, final ImmutableIntArray xs)
	{
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsInt(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static RangedIntFunction intMap(final DoubleToIntFunction f, final DoubleChain xs)
	{
		return RangedIntFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static ImmutableIntArray intMap(final DoubleToIntFunction f, final ImmutableDoubleArray xs)
	{
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsInt(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static RangedIntFunction intMap(final LongToIntFunction f, final LongChain xs)
	{
		return RangedIntFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static ImmutableIntArray intMap(final LongToIntFunction f, final ImmutableLongArray xs)
	{
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsInt(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static <T> RangedIntFunction intMap(final ToIntFunction<T> f, final Chain<T> xs)
	{
		return RangedIntFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	 * The intMap function provides a unified way of mapping sequences to primitive int sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T, E extends T> int[] intMap(final ToIntFunction<T> f, final E[] xs)
	{
		final int[] mapped = new int[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsInt(xs[i]);
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
	public static RangedLongFunction longMap(final LongUnaryOperator f, final LongChain xs)
	{
		return RangedLongFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static ImmutableLongArray longMap(final LongUnaryOperator f, final ImmutableLongArray xs)
	{
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsLong(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static RangedLongFunction longMap(final IntToLongFunction f, final IntChain xs)
	{
		return RangedLongFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static ImmutableLongArray longMap(final IntToLongFunction f, final ImmutableIntArray xs)
	{
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsLong(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static RangedLongFunction longMap(final DoubleToLongFunction f, final DoubleChain xs)
	{
		return RangedLongFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static ImmutableLongArray longMap(final DoubleToLongFunction f, final ImmutableDoubleArray xs)
	{
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder(len(xs));
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.applyAsLong(xs.get(i)));
		}
		return builder.build().trimmed();
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
	public static <T> RangedLongFunction longMap(final ToLongFunction<T> f, final Chain<T> xs)
	{
		return RangedLongFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	 * The longMap function provides a unified way of mapping sequences to primitive long sequences. Generally
	 * the type of the returned sequence is the same as the parameter one, the exception being Object Lists and arrays
	 * which are mapped to primitive arrays.
	 *
	 * @param f - The function defining the mapping
	 * @param xs - The sequence to map
	 * @return a sequence of the same length as <b>xs</b> containing the mapped values of <b>xs</b> (with order retained).
	 */
	public static <T, E extends T> long[] longMap(final ToLongFunction<T> f, final E[] xs)
	{
		final long[] mapped = new long[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			mapped[i] = f.applyAsLong(xs[i]);
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
	public static <T, R> RangedFunction<R> objMap(final Function<T, R> f, final Chain<? extends T> xs)
	{
		return RangedFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static <T, R> ImmutableList<R> objMap(final Function<T, R> f, final List<? extends T> xs)
	{
		final ImmutableList.Builder<R> builder = ImmutableList.builder();
		for (final T x : xs) {
			builder.add(f.apply(x));
		}
		return builder.build();
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
	public static <T, E extends T, R> ImmutableList<R> objMap(final Function<T, R> f, final E[] xs)
	{
		final ImmutableList.Builder<R> builder = ImmutableList.builder();
		for (final T x : xs) {
			builder.add(f.apply(x));
		}
		return builder.build();
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
	public static <T> RangedFunction<T> objMap(final IntFunction<T> f, final IntChain xs)
	{
		return RangedFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static <T> ImmutableList<T> objMap(final IntFunction<T> f, final ImmutableIntArray xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.apply(xs.get(i)));
		}
		return builder.build();
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
	public static <T> ImmutableList<T> objMap(final IntFunction<T> f, final int[] xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (final int x : xs) {
			builder.add(f.apply(x));
		}
		return builder.build();
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
	public static <T> RangedFunction<T> objMap(final DoubleFunction<T> f, final DoubleChain xs)
	{
		return RangedFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static <T> ImmutableList<T> objMap(final DoubleFunction<T> f, final ImmutableDoubleArray xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.apply(xs.get(i)));
		}
		return builder.build();
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
	public static <T> ImmutableList<T> objMap(final DoubleFunction<T> f, final double[] xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (final double x : xs) {
			builder.add(f.apply(x));
		}
		return builder.build();
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
	public static <T> RangedFunction<T> objMap(final LongFunction<T> f, final LongChain xs)
	{
		return RangedFunction.of(compose(f, xs.getDescriptor()), len(xs));
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
	public static <T> ImmutableList<T> objMap(final LongFunction<T> f, final ImmutableLongArray xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (int i = 0; i < len(xs); i++) {
			builder.add(f.apply(xs.get(i)));
		}
		return builder.build();
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
	public static <T> ImmutableList<T> objMap(final LongFunction<T> f, final long[] xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (final long x : xs) {
			builder.add(f.apply(x));
		}
		return builder.build();
	}

	/**
	 * TODO - keep this stuff below?
	 *
	 * @param f
	 * @param xs
	 * @return
	 */
	public static BitArray boolRange(final DoublePredicate f, final double[] xs)
	{
		return new BitArray(i -> f.test(xs[i]), len(xs));
	}

	public static BitArray boolRange(final DoublePredicate f, final DoubleChain xs)
	{
		return new BitArray(i -> f.test(xs.elementAt(i)), len(xs));
	}

	public static BitArray boolRange(final IntPredicate f, final int[] xs)
	{
		return new BitArray(i -> f.test(xs[i]), len(xs));
	}

	public static BitArray boolRange(final IntPredicate f, final IntChain xs)
	{
		return new BitArray(i -> f.test(xs.elementAt(i)), len(xs));
	}

	public static <T> BitArray boolRange(final Predicate<T> f, final List<T> xs)
	{
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	public static <T> BitArray boolRange(final Predicate<T> f, final Chain<T> xs)
	{
		return new BitArray(i -> f.test(xs.elementAt(i)), len(xs));
	}
}
