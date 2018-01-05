package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableIntRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableRangeDescriptor;

/**
 *
 * @author t
 *
 */
public final class FoldUtil {

	public FoldUtil() {
	}

	public static OptionalInt foldr(final IntBinaryOperator f, final int id, final IntRangeDescriptor xs) {
		if (len(xs) == 0) {
			return OptionalInt.empty();
		}
		int cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.applyAsInt(xs.get(i), cumulativeFold);
		}
		return OptionalInt.of(cumulativeFold);
	}

	public static OptionalInt foldr(final IntBinaryOperator f, final int id, final int[] xs) {
		return foldr(f, id, ImmutableIntRangeDescriptor.from(xs));
	}

	public static OptionalDouble foldr(final DoubleBinaryOperator f, final double id, final DoubleRangeDescriptor xs) {
		if (len(xs) == 0) {
			return OptionalDouble.empty();
		}
		double cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.applyAsDouble(xs.get(i), cumulativeFold);
		}
		return OptionalDouble.of(cumulativeFold);
	}

	public static OptionalDouble foldr(final DoubleBinaryOperator f, final double id, final double... xs) {
		return foldr(f, id, ImmutableDoubleRangeDescriptor.from(xs));
	}

	public static <T> Optional<T> foldr(final BinaryOperator<T> f, final T id, final RangeDescriptor<T> xs) {
		if (len(xs) == 0) {
			return Optional.empty();
		}
		T cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.apply(xs.get(i), cumulativeFold);
		}
		return Optional.of(cumulativeFold);
	}

	public static <T> Optional<T> foldr(final BinaryOperator<T> f, final T id, final List<T> xs) {
		return foldr(f, id, ImmutableRangeDescriptor.from(xs));
	}

	/**
	 * @param f
	 * @param start
	 * @param xs
	 * @return
	 */

	public static OptionalInt foldl(final IntBinaryOperator f, final int id, final IntRangeDescriptor xs) {
		if (len(xs) == 0) {
			return OptionalInt.empty();
		}
		int cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.applyAsInt(cumulativeFold, xs.get(i));
		}
		return OptionalInt.of(cumulativeFold);
	}

	public static OptionalInt foldl(final IntBinaryOperator f, final int id, final int[] xs) {
		return foldl(f, id, ImmutableIntRangeDescriptor.from(xs));
	}

	public static OptionalDouble foldl(final DoubleBinaryOperator f, final double id, final DoubleRangeDescriptor xs) {
		if (len(xs) == 0) {
			return OptionalDouble.empty();
		}
		double cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.applyAsDouble(cumulativeFold, xs.get(i));
		}
		return OptionalDouble.of(cumulativeFold);
	}

	public static OptionalDouble foldl(final DoubleBinaryOperator f, final double id, final double... xs) {
		return foldl(f, id, ImmutableDoubleRangeDescriptor.from(xs));
	}

	public static <T> Optional<T> foldl(final BinaryOperator<T> f, final T id, final RangeDescriptor<T> xs) {
		if (len(xs) == 0) {
			return Optional.empty();
		}
		T cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.apply(cumulativeFold, xs.get(i));
		}
		return Optional.of(cumulativeFold);
	}

	public static <T> Optional<T> foldl(final BinaryOperator<T> f, final T id, final List<T> xs) {
		return foldl(f, id, ImmutableRangeDescriptor.from(xs));
	}

	/**
	 *
	 * @param f
	 * @param start
	 * @param xs
	 * @return
	 */

	public static double accumulate(final DoubleBinaryOperator f, final double start, final DoubleRangeDescriptor xs) {
		final int loopUpperBound = len(xs) - 1;
		double accumulation = start;
		for (int i = 0; i < loopUpperBound; i++) {
			accumulation += f.applyAsDouble(xs.get(i), xs.get(i + 1));
		}
		return accumulation;
	}

	public static double accumulate(final DoubleBinaryOperator f, final double start, final double[] xs) {
		return accumulate(f, start, ImmutableDoubleRangeDescriptor.from(xs));
	}

	public static int accumulate(final IntBinaryOperator f, final int start, final IntRangeDescriptor xs) {
		final int loopUpperBound = len(xs) - 1;
		int accumulation = start;
		for (int i = 0; i < loopUpperBound; i++) {
			accumulation += f.applyAsInt(xs.get(i), xs.get(i + 1));
		}
		return accumulation;
	}

	public static int accumulate(final IntBinaryOperator f, final int start, final int[] xs) {
		return accumulate(f, start, ImmutableIntRangeDescriptor.from(xs));
	}
}
