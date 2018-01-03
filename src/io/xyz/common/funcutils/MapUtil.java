/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;
import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	private MapUtil() {
	}

	public static double[] mapCollect(final DoubleUnaryOperator f, final double[] xs) {
		final double[] result = new double[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			result[i] = f.applyAsDouble(xs[i]);
		}
		return result;
	}

	public static int[] mapCollect(final IntUnaryOperator f, final int[] xs) {
		final int[] result = new int[len(xs)];
		for (int i = 0; i < len(xs); i++) {
			result[i] = f.applyAsInt(xs[i]);
		}
		return result;
	}

	public static <T, S> List<S> mapCollect(final Function<T, S> f, final T[] xs) {
		final List<S> result = new ArrayList<>(len(xs));
		for (int i = 0; i < len(xs); i++) {
			result.add(f.apply(xs[i]));
		}
		return result;
	}

	public static <T, S> List<S> mapCollect(final Function<T, S> f, final List<T> xs) {
		final List<S> result = new ArrayList<>(len(xs));
		for (int i = 0; i < len(xs); i++) {
			result.add(f.apply(xs.get(i)));
		}
		return result;
	}

	public static <T, S> Set<S> mapCollect(final Function<T, S> f, final Set<T> xs) {
		final Set<S> result = new HashSet<>(len(xs));
		for (final T x : xs) {
			result.add(f.apply(x));
		}
		return result;
	}

	/**
	 * 
	 * @param f
	 * @param xs
	 * @return
	 */

	public static IntRangeDescriptor map(final IntUnaryOperator f, final IntRangeDescriptor xs) {
		return xs.map(f);
	}

	public static IntRangeDescriptor map(final IntUnaryOperator f, final int... xs) {
		return ImmutableIntRangeDescriptor.from(xs).map(f);
	}

	public static DoubleRangeDescriptor map(final DoubleUnaryOperator f, final DoubleRangeDescriptor xs) {
		return xs.map(f);
	}

	public static DoubleRangeDescriptor map(final DoubleUnaryOperator f, final double... xs) {
		return ImmutableDoubleRangeDescriptor.from(xs).map(f);
	}

	// public static <T> RangeDescriptor<T> map(final UnaryOperator<T> f, final
	// RangeDescriptor<T> xs) {
	// return xs.map(f);
	// }

	public static <T, R> RangeDescriptor<R> map(final Function<T, R> f, final RangeDescriptor<T> xs) {
		return xs.map(f);
	}

	// public static <T> RangeDescriptor<T> map(final UnaryOperator<T> f, final
	// List<T> xs) {
	// return ImmutableRangeDescriptor.from(xs).map(f);
	// }

	public static <T, R> RangeDescriptor<R> map(final Function<T, R> f, final List<T> xs) {
		return asDescriptor(xs).map(f);
	}

	/**
	 * 
	 * @param f
	 * @param xs
	 * @return
	 */

	public static <T> DoubleRangeDescriptor mapToDouble(final ToDoubleFunction<T> f, final List<T> xs) {
		return ImmutableRangeDescriptor.from(xs).mapToDouble(f);
	}

	public static <T> DoubleRangeDescriptor mapToDouble(final ToDoubleFunction<T> f, final RangeDescriptor<T> xs) {
		return xs.mapToDouble(f);
	}

	public static DoubleRangeDescriptor mapToDouble(final IntToDoubleFunction f, final int... xs) {
		return ImmutableIntRangeDescriptor.from(xs).mapToDouble(f);
	}

	public static DoubleRangeDescriptor mapToDouble(final IntToDoubleFunction f, final IntRangeDescriptor xs) {
		return xs.mapToDouble(f);
	}

	/**
	 * 
	 * @param f
	 * @param xs
	 * @return
	 */

	public static <T> IntRangeDescriptor mapToInt(final ToIntFunction<T> f, final List<T> xs) {
		return ImmutableRangeDescriptor.from(xs).mapToInt(f);
	}

	public static <T> IntRangeDescriptor mapToInt(final ToIntFunction<T> f, final RangeDescriptor<T> xs) {
		return xs.mapToInt(f);
	}

	public static IntRangeDescriptor mapToInt(final DoubleToIntFunction f, final double... xs) {
		return ImmutableDoubleRangeDescriptor.from(xs).mapToInt(f);
	}

	public static IntRangeDescriptor mapToInt(final DoubleToIntFunction f, final DoubleRangeDescriptor xs) {
		return xs.mapToInt(f);
	}

	/**
	 * 
	 * @param f
	 * @param xs
	 * @return
	 */

	public static BitArray mapToBool(final DoublePredicate f, final double[] xs) {
		return new BitArray(i -> f.test(xs[i]), len(xs));
	}

	public static BitArray mapToBool(final DoublePredicate f, final DoubleRangeDescriptor xs) {
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	public static BitArray mapToBool(final IntPredicate f, final int[] xs) {
		return new BitArray(i -> f.test(xs[i]), len(xs));
	}

	public static BitArray mapToBool(final IntPredicate f, final IntRangeDescriptor xs) {
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	public static <T> BitArray mapToBool(final Predicate<T> f, final List<T> xs) {
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	public static <T> BitArray mapToBool(final Predicate<T> f, final RangeDescriptor<T> xs) {
		return new BitArray(i -> f.test(xs.get(i)), len(xs));
	}

	/**
	 * 
	 * @param f
	 * @param xs
	 * @return
	 */

	public static <T> RangeDescriptor<T> mapToObj(final IntFunction<T> f, final int[] xs) {
		return ImmutableIntRangeDescriptor.from(xs).mapToObj(f);
	}

	public static <T> RangeDescriptor<T> mapToObj(final IntFunction<T> f, final IntRangeDescriptor xs) {
		return xs.mapToObj(f);
	}

	public static <T> RangeDescriptor<T> mapToObj(final DoubleFunction<T> f, final double[] xs) {
		return ImmutableDoubleRangeDescriptor.from(xs).mapToObj(f);
	}

	public static <T> RangeDescriptor<T> mapToObj(final DoubleFunction<T> f, final DoubleRangeDescriptor xs) {
		return xs.mapToObj(f);
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
