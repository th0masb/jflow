/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.PrimitiveUtil.abs;
import static io.xyz.common.funcutils.PrimitiveUtil.max;
import static io.xyz.common.funcutils.PrimitiveUtil.signum;
import static io.xyz.common.geometry.Constants.EPSILON;

import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import io.xyz.common.geometry.BitArray;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableIntRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableRangeDescriptor;

/**
 * @author t
 *
 */
public final class MapUtil {
	private MapUtil()
	{
	}

	// public static double[] mapCollect(final DoubleUnaryOperator f, final double[]
	// xs) {
	// final double[] result = new double[len(xs)];
	// for (int i = 0; i < len(xs); i++) {
	// result[i] = f.applyAsDouble(xs[i]);
	// }
	// return result;
	// }
	//
	// public static int[] mapCollect(final IntUnaryOperator f, final int[] xs) {
	// final int[] result = new int[len(xs)];
	// for (int i = 0; i < len(xs); i++) {
	// result[i] = f.applyAsInt(xs[i]);
	// }
	// return result;
	// }
	//
	// public static <T, S> List<S> mapCollect(final Function<T, S> f, final T[] xs)
	// {
	// final List<S> result = new ArrayList<>(len(xs));
	// for (int i = 0; i < len(xs); i++) {
	// result.add(f.apply(xs[i]));
	// }
	// return result;
	// }
	//
	// public static <T, S> List<S> mapCollect(final Function<T, S> f, final List<T>
	// xs) {
	// final List<S> result = new ArrayList<>(len(xs));
	// for (int i = 0; i < len(xs); i++) {
	// result.add(f.apply(xs.get(i)));
	// }
	// return result;
	// }
	//
	// public static <T, S> Set<S> mapCollect(final Function<T, S> f, final Set<T>
	// xs) {
	// final Set<S> result = new HashSet<>(len(xs));
	// for (final T x : xs) {
	// result.add(f.apply(x));
	// }
	// return result;
	// }

	/**
	 *
	 * @param f
	 * @param xs
	 * @return
	 */

	public static DoubleRangeDescriptor doubleRange(final DoubleUnaryOperator f, final DoubleRangeDescriptor xs)
	{
		return xs.asDoubleRange(f);
	}

	public static DoubleRangeDescriptor doubleRange(final DoubleUnaryOperator f, final double[] xs)
	{
		return ImmutableDoubleRangeDescriptor.from(xs).asDoubleRange(f);
	}

	public static <T> DoubleRangeDescriptor doubleRange(final ToDoubleFunction<T> f, final List<T> xs)
	{
		return ImmutableRangeDescriptor.from(xs).asDoubleRange(f);
	}

	public static <T> DoubleRangeDescriptor doubleRange(final ToDoubleFunction<T> f, final RangeDescriptor<T> xs)
	{
		return xs.asDoubleRange(f);
	}

	public static DoubleRangeDescriptor doubleRange(final IntToDoubleFunction f, final int[] xs)
	{
		return ImmutableIntRangeDescriptor.from(xs).asDoubleRange(f);
	}

	public static DoubleRangeDescriptor doubleRange(final IntToDoubleFunction f, final IntRangeDescriptor xs)
	{
		return xs.asDoubleRange(f);
	}

	/**
	 *
	 * @param f
	 * @param xs
	 * @return
	 */

	public static IntRangeDescriptor intRange(final IntUnaryOperator f, final IntRangeDescriptor xs)
	{
		return xs.asIntRange(f);
	}

	public static IntRangeDescriptor intRange(final IntUnaryOperator f, final int[] xs)
	{
		return ImmutableIntRangeDescriptor.from(xs).asIntRange(f);
	}

	public static <T> IntRangeDescriptor intRange(final ToIntFunction<T> f, final List<T> xs)
	{
		return ImmutableRangeDescriptor.from(xs).asIntRange(f);
	}

	public static <T> IntRangeDescriptor intRange(final ToIntFunction<T> f, final T[] xs)
	{
		return ImmutableRangeDescriptor.from(xs).asIntRange(f);
	}

	public static <T> IntRangeDescriptor intRange(final ToIntFunction<T> f, final RangeDescriptor<T> xs)
	{
		return xs.asIntRange(f);
	}

	public static IntRangeDescriptor intRange(final DoubleToIntFunction f, final double[] xs)
	{
		return ImmutableDoubleRangeDescriptor.from(xs).asIntRange(f);
	}

	public static IntRangeDescriptor intRange(final DoubleToIntFunction f, final DoubleRangeDescriptor xs)
	{
		return xs.asIntRange(f);
	}

	/**
	 *
	 * @param f
	 * @param xs
	 * @return
	 */

	public static BitArray boolRange(final DoublePredicate f, final double[] xs)
	{
		return new BitArray(i -> f.test(xs[i]), len(xs));
	}

	public static BitArray boolRange(final DoublePredicate f, final DoubleRangeDescriptor xs)
	{
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	public static BitArray boolRange(final IntPredicate f, final int[] xs)
	{
		return new BitArray(i -> f.test(xs[i]), len(xs));
	}

	public static BitArray boolRange(final IntPredicate f, final IntRangeDescriptor xs)
	{
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	public static <T> BitArray boolRange(final Predicate<T> f, final List<T> xs)
	{
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	public static <T> BitArray boolRange(final Predicate<T> f, final RangeDescriptor<T> xs)
	{
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	/**
	 *
	 * @param f
	 * @param xs
	 * @return
	 */

	public static <T, R> RangeDescriptor<R> objRange(final Function<T, R> f, final RangeDescriptor<T> xs)
	{
		return xs.asObjRange(f);
	}

	public static <T, R> RangeDescriptor<R> objRange(final Function<T, R> f, final List<T> xs)
	{
		return asDescriptor(xs).asObjRange(f);
	}

	public static <T> RangeDescriptor<T> objRange(final IntFunction<T> f, final int[] xs)
	{
		return ImmutableIntRangeDescriptor.from(xs).asObjRange(f);
	}

	public static <T> RangeDescriptor<T> objRange(final IntFunction<T> f, final IntRangeDescriptor xs)
	{
		return xs.asObjRange(f);
	}

	public static <T> RangeDescriptor<T> objRange(final DoubleFunction<T> f, final double[] xs)
	{
		return ImmutableDoubleRangeDescriptor.from(xs).asObjRange(f);
	}

	public static <T> RangeDescriptor<T> objRange(final DoubleFunction<T> f, final DoubleRangeDescriptor xs)
	{
		return xs.asObjRange(f);
	}

	/**
	 * Think geometrically
	 *
	 * @param startBound
	 * @param endBound
	 * @param step
	 * @return
	 */
	public static IntRangeDescriptor range(final int startBound, final int endBound, final int step)
	{
		assert step != 0 : "Cannot have 0 step";
		final int boundDiff = endBound - startBound, stepSign = signum(step);

		if (startBound == endBound) {
			return IntRangeDescriptor.EMPTY;
		} else if (signum(boundDiff) != stepSign) {
			return new ImmutableIntRangeDescriptor(1, i -> startBound);
		} else {
			final int n = (int) max(Math.ceil(((double) boundDiff) / step), 1);
			return new ImmutableIntRangeDescriptor(n, i -> startBound + i * step);
		}
	}

	public static IntRangeDescriptor rangei(final int startBound, final int endBound)
	{
		return range(startBound, endBound + (startBound < endBound? 1 : -1), 1);
	}

	public static IntRangeDescriptor range(final int startBound, final int endBound)
	{
		return range(startBound, endBound, 1);
	}

	public static IntRangeDescriptor rangei(final int upperBound)
	{
		return MapUtil.range(upperBound + (upperBound > 0? 1 : -1));
	}

	public static IntRangeDescriptor range(final int endBound)
	{
		return range(0, endBound);
	}

	/**
	 * @param lower
	 * @param upper
	 * @param step
	 * @return
	 */

	public static DoubleRangeDescriptor drange(final double lower, final double upper, final double step)
	{
		assert abs(step) >= EPSILON : "Cannot have 0 step";
		final double boundDiff = upper - lower, stepSign = signum(step);

		if (abs(upper - lower) < EPSILON) {
			return DoubleRangeDescriptor.EMPTY;
		} else if (signum(boundDiff) != stepSign) {
			return new ImmutableDoubleRangeDescriptor(1, i -> lower);
		} else {
			final int n = max((int) Math.ceil(boundDiff / step), 1);
			return new ImmutableDoubleRangeDescriptor(n, i -> lower + i * step);
		}
	}

	public static DoubleRangeDescriptor drange(final int upper)
	{
		return drange(0, upper, 1);
	}

	// public static <T> Stream<T> mapToObjStream(final IntFunction<T> f, final
	// int[] xs) {
	// return mapToObj(f, xs).stream();
	// }
	//
	// public static <T> Stream<T> mapToObjStream(final DoubleFunction<T> f, final
	// double[] xs) {
	// return mapToObj(f, xs).stream();
	// }
	//
	// public static <T, S> Stream<T> mapToObjStream(final Function<S, T> f, final
	// S[] xs) {
	// return mapCollect(f, xs).stream();
	// }
	//
	// public static <T, S> Stream<T> mapToObjStream(final Function<S, T> f, final
	// List<S> xs) {
	// return mapCollect(f, xs).stream();
	// }
}
