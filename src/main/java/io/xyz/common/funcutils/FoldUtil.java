package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;
import static io.xyz.common.funcutils.CollectionUtil.len;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import io.xyz.common.function.BiToDoubleFunction;
import io.xyz.common.function.BiToIntFunction;
import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.generators.IntGenerator;
import io.xyz.common.generators.impl.ImmutableDoubleGenerator;
import io.xyz.common.generators.impl.ImmutableIntGenerator;
import io.xyz.common.generators.impl.ImmutableGenerator;
import io.xyz.common.generators.Generator;

/**
 *
 * @author t
 *
 */
public final class FoldUtil {

	public FoldUtil()
	{
	}

	public static OptionalInt foldr(final IntBinaryOperator f, final int id, final IntGenerator xs)
	{
		if (len(xs) == 0) {
			return OptionalInt.empty();
		}
		int cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.applyAsInt(xs.get(i), cumulativeFold);
		}
		return OptionalInt.of(cumulativeFold);
	}

	public static OptionalInt foldr(final IntBinaryOperator f, final int id, final int[] xs)
	{
		return foldr(f, id, asDescriptor(xs));
	}

	public static OptionalDouble foldr(final DoubleBinaryOperator f, final double id, final DoubleGenerator xs)
	{
		if (len(xs) == 0) {
			return OptionalDouble.empty();
		}
		double cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.applyAsDouble(xs.get(i), cumulativeFold);
		}
		return OptionalDouble.of(cumulativeFold);
	}

	public static OptionalDouble foldr(final DoubleBinaryOperator f, final double id, final double... xs)
	{
		return foldr(f, id, asDescriptor(xs));
	}

	public static <T> Optional<T> foldr(final BinaryOperator<T> f, final T id, final Generator<? extends T> xs)
	{
		if (len(xs) == 0) {
			return Optional.empty();
		}
		T cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.apply(xs.get(i), cumulativeFold);
		}
		return Optional.of(cumulativeFold);
	}

	public static <T> Optional<T> foldr(final BinaryOperator<T> f, final T id, final List<? extends T> xs)
	{
		return foldr(f, id, asDescriptor(xs));
	}

	/**
	 * @param f
	 * @param start
	 * @param xs
	 * @return
	 */

	public static OptionalInt foldl(final IntBinaryOperator f, final int id, final IntGenerator xs)
	{
		if (len(xs) == 0) {
			return OptionalInt.empty();
		}
		int cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.applyAsInt(cumulativeFold, xs.get(i));
		}
		return OptionalInt.of(cumulativeFold);
	}

	public static OptionalInt foldl(final IntBinaryOperator f, final int id, final int[] xs)
	{
		return foldl(f, id, asDescriptor(xs));
	}

	public static OptionalDouble foldl(final DoubleBinaryOperator f, final double id, final DoubleGenerator xs)
	{
		if (len(xs) == 0) {
			return OptionalDouble.empty();
		}
		double cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.applyAsDouble(cumulativeFold, xs.get(i));
		}
		return OptionalDouble.of(cumulativeFold);
	}

	public static OptionalDouble foldl(final DoubleBinaryOperator f, final double id, final double... xs)
	{
		return foldl(f, id, asDescriptor(xs));
	}

	public static <T> Optional<T> foldl(final BinaryOperator<T> f, final T id, final Generator<? extends T> xs)
	{
		if (len(xs) == 0) {
			return Optional.empty();
		}
		T cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.apply(cumulativeFold, xs.get(i));
		}
		return Optional.of(cumulativeFold);
	}

	public static <T> Optional<T> foldl(final BinaryOperator<T> f, final T id, final List<? extends T> xs)
	{
		return foldl(f, id, asDescriptor(xs));
	}

	/**
	 *
	 * @param f
	 * @param start
	 * @param xs
	 * @return
	 */

	//	public static double accumulate(final DoubleBinaryOperator f, final double start, final DoubleRangeDescriptor xs)
	//	{
	//		final int loopUpperBound = len(xs) - 1;
	//		double accumulation = start;
	//		for (int i = 0; i < loopUpperBound; i++) {
	//			accumulation = f.applyAsDouble(accumulation, xs.get(i + 1));
	//		}
	//		return accumulation;
	//	}
	//
	//	public static double accumulate(final DoubleBinaryOperator f, final double start, final double[] xs)
	//	{
	//		return accumulate(f, start, asDescriptor(xs));
	//	}
	//
	//	public static int accumulate(final IntBinaryOperator f, final int start, final IntRangeDescriptor xs)
	//	{
	//		final int loopUpperBound = len(xs) - 1;
	//		int accumulation = start;
	//		for (int i = 0; i < loopUpperBound; i++) {
	//			accumulation += f.applyAsInt(xs.get(i), xs.get(i + 1));
	//		}
	//		return accumulation;
	//	}
	//
	//	public static int accumulate(final IntBinaryOperator f, final int start, final int[] xs)
	//	{
	//		return accumulate(f, start, asDescriptor(xs));
	//	}
	//
	//	public static <T> T accumulate(final BinaryOperator<T> f, final T start, final RangeDescriptor<T> xs)
	//	{
	//		final int loopUpperBound = len(xs) - 1;
	//		T accumulation = start;
	//		for (int i = 0; i < loopUpperBound; i++) {
	//			accumulation = f.apply(accumulation, xs.get(i + 1));
	//		}
	//		return accumulation;
	//	}
	//
	//	public static <T> T accumulate(final BinaryOperator<T> f, final T start, final T[] xs)
	//	{
	//		return accumulate(f, start, asDescriptor(xs));
	//	}
	//
	//	public static <T> T accumulate(final BinaryOperator<T> f, final T start, final List<T> xs)
	//	{
	//		return accumulate(f, start, asDescriptor(xs));
	//	}

	// // TODO - pair reduce??
	//
	public static <T> IntGenerator pairFoldToInt(final BiToIntFunction<T, T> f, final Generator<T> xs)
	{
		final IntUnaryOperator g = i -> f.apply(xs.get(i), xs.get(i + 1));
		return ImmutableIntGenerator.of(len(xs) - 1, g);
	}

	public static <T> DoubleGenerator pairFoldToDouble(final BiToDoubleFunction<T, T> f, final Generator<T> xs)
	{
		final IntToDoubleFunction g = i -> f.apply(xs.get(i), xs.get(i + 1));
		return ImmutableDoubleGenerator.of(len(xs) - 1, g);
	}

	public static <T, S> Generator<S> pairFold(final BiFunction<T, T, S> f, final Generator<T> xs)
	{
		final IntFunction<S> g = i -> f.apply(xs.get(i), xs.get(i + 1));
		return new ImmutableGenerator<>(len(xs) - 1, g);
	}

	public static DoubleGenerator pairFold(final DoubleBinaryOperator f, final DoubleGenerator xs)
	{
		final IntToDoubleFunction g = i -> f.applyAsDouble(xs.get(i), xs.get(i + 1));
		return ImmutableDoubleGenerator.of(len(xs) - 1, g);
	}

	public static <T, S> Generator<S> pairFold(final BiFunction<T, T, S> f, final List<T> xs)
	{
		final IntFunction<S> g = i -> f.apply(xs.get(i), xs.get(i + 1));
		return new ImmutableGenerator<>(len(xs) - 1, g);
	}
}
