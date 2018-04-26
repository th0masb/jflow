package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.asFunction;
import static xawd.jchain.chains.utilities.CollectionUtil.head;
import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

import xawd.jchain.chains.Chain;
import xawd.jchain.chains.DoubleChain;
import xawd.jchain.chains.IntChain;
import xawd.jchain.chains.LongChain;

/**
 * @author ThomasB
 * @since 14 Feb 2018
 */
public final class FoldUtil
{
	public FoldUtil()
	{
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalInt foldr(final IntBinaryOperator f, final int id, final IntChain xs)
	{
		if (len(xs) == 0) {
			return OptionalInt.empty();
		}
		int cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.applyAsInt(xs.elementAt(i), cumulativeFold);
		}
		return OptionalInt.of(cumulativeFold);
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalInt foldr(final IntBinaryOperator f, final int id, final ImmutableIntArray xs)
	{
		return foldr(f, id, asFunction(xs));
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalInt foldr(final IntBinaryOperator f, final int id, final int[] xs)
	{
		return foldr(f, id, asFunction(xs));
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalDouble foldr(final DoubleBinaryOperator f, final double id, final DoubleChain xs)
	{
		if (len(xs) == 0) {
			return OptionalDouble.empty();
		}
		double cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.applyAsDouble(xs.elementAt(i), cumulativeFold);
		}
		return OptionalDouble.of(cumulativeFold);
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalDouble foldr(final DoubleBinaryOperator f, final double id, final ImmutableDoubleArray xs)
	{
		return foldr(f, id, asFunction(xs));
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalDouble foldr(final DoubleBinaryOperator f, final double id, final double[] xs)
	{
		return foldr(f, id, asFunction(xs));
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalLong foldr(final LongBinaryOperator f, final long id, final LongChain xs)
	{
		if (len(xs) == 0) {
			return OptionalLong.empty();
		}
		long cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.applyAsLong(xs.elementAt(i), cumulativeFold);
		}
		return OptionalLong.of(cumulativeFold);
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalLong foldr(final LongBinaryOperator f, final long id, final ImmutableLongArray xs)
	{
		return foldr(f, id, asFunction(xs));
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalLong foldr(final LongBinaryOperator f, final long id, final long[] xs)
	{
		return foldr(f, id, asFunction(xs));
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static <T, E extends T> Optional<T> foldr(final BinaryOperator<T> f, final E id, final Chain<? extends T> xs)
	{
		if (len(xs) == 0) {
			return Optional.empty();
		}
		T cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.apply(xs.elementAt(i), cumulativeFold);
		}
		return Optional.of(cumulativeFold);
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static <T, E extends T> Optional<T> foldr(final BinaryOperator<T> f, final E id, final List<? extends T> xs)
	{
		return foldr(f, id, asFunction(xs));
	}

	/**
	 * The fold right (foldr) function collapses a sequence to a single value in a right associative manner. I.e. starting
	 * at the tail of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code x_1 * (x_2 * (... * (x_[n-1] * (x_n * id))...)}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static <T, E1 extends T, E2 extends T> Optional<T> foldr(final BinaryOperator<T> f, final E1 id, final E2[] xs)
	{
		return foldr(f, id, asFunction(xs));
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalInt foldl(final IntBinaryOperator f, final int id, final IntChain xs)
	{
		if (len(xs) == 0) {
			return OptionalInt.empty();
		}
		int cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.applyAsInt(cumulativeFold, xs.elementAt(i));
		}
		return OptionalInt.of(cumulativeFold);
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalInt foldl(final IntBinaryOperator f, final int id, final ImmutableIntArray xs)
	{
		return foldl(f, id, asFunction(xs));
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalInt foldl(final IntBinaryOperator f, final int id, final int[] xs)
	{
		return foldl(f, id, asFunction(xs));
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalDouble foldl(final DoubleBinaryOperator f, final double id, final DoubleChain xs)
	{
		if (len(xs) == 0) {
			return OptionalDouble.empty();
		}
		double cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.applyAsDouble(cumulativeFold, xs.elementAt(i));
		}
		return OptionalDouble.of(cumulativeFold);
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalDouble foldl(final DoubleBinaryOperator f, final double id, final ImmutableDoubleArray xs)
	{
		return foldl(f, id, asFunction(xs));
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalDouble foldl(final DoubleBinaryOperator f, final double id, final double[] xs)
	{
		return foldl(f, id, asFunction(xs));
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalLong foldl(final LongBinaryOperator f, final long id, final LongChain xs)
	{
		if (len(xs) == 0) {
			return OptionalLong.empty();
		}
		long cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.applyAsLong(cumulativeFold, xs.elementAt(i));
		}
		return OptionalLong.of(cumulativeFold);
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalLong foldl(final LongBinaryOperator f, final long id, final ImmutableLongArray xs)
	{
		return foldl(f, id, asFunction(xs));
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static OptionalLong foldl(final LongBinaryOperator f, final long id, final long[] xs)
	{
		return foldl(f, id, asFunction(xs));
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static <T, E extends T> Optional<T> foldl(final BinaryOperator<T> f, final E id, final Chain<? extends T> xs)
	{
		if (len(xs) == 0) {
			return Optional.empty();
		}
		T cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.apply(cumulativeFold, xs.elementAt(i));
		}
		return Optional.of(cumulativeFold);
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static <T, E extends T> Optional<T> foldl(final BinaryOperator<T> f, final E id, final List<? extends T> xs)
	{
		return foldl(f, id, asFunction(xs));
	}

	/**
	 * The fold left (foldl) function collapses a sequence to a single value in a left associative manner. I.e. starting
	 * at the head of the sequence. If we denote the operator by {@code *} and the sequence by {@code (x_1,...,x_n)} then the result
	 * is<br>
	 * {@code (...(id * x_1) * x_2) *...) * x_[n-1]) * x_n}
	 *
	 * @param f - The binary operator describing how elements fold together
	 * @param id - The initial value of the fold
	 * @param xs - The sequence to fold
	 * @return the result obtained by folding the whole sequence into one value or nothing if the sequence is empty.
	 */
	public static <T, E1 extends T, E2 extends T> Optional<T> foldl(final BinaryOperator<T> f, final E1 id, final E2[] xs)
	{
		return foldl(f, id, asFunction(xs));
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new double array of the same length whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static double[] accumulate(final DoubleBinaryOperator f, final DoubleChain xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return new double[] {};
		}
		final double[] accumulated = new double[n];
		accumulated[0] = head(xs);
		for (int i = 1; i < n; i++) {
			accumulated[i] = f.applyAsDouble(accumulated[i - 1], xs.elementAt(i));
		}
		return accumulated;
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new sequence of the same length and type as xs whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static double[] accumulate(final DoubleBinaryOperator f, final double[] xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return new double[] {};
		}
		final double[] accumulated = new double[n];
		accumulated[0] = head(xs);
		for (int i = 1; i < n; i++) {
			accumulated[i] = f.applyAsDouble(accumulated[i - 1], xs[i]);
		}
		return accumulated;
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new sequence of the same length and type as xs whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static ImmutableDoubleArray accumulate(final DoubleBinaryOperator f, final ImmutableDoubleArray xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return ImmutableDoubleArray.of();
		}
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder(n);
		builder.add(head(xs));
		double accumulation = head(xs);
		for (int i = 1; i < n; i++) {
			accumulation = f.applyAsDouble(accumulation, xs.get(i));
			builder.add(accumulation);
		}
		return builder.build().trimmed();
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new int array of the same length whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static int[] accumulate(final IntBinaryOperator f, final IntChain xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return new int[] {};
		}
		final int[] accumulated = new int[n];
		accumulated[0] = head(xs);
		for (int i = 1; i < n; i++) {
			accumulated[i] = f.applyAsInt(accumulated[i - 1], xs.elementAt(i));
		}
		return accumulated;
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new sequence of the same length and type as xs whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static int[] accumulate(final IntBinaryOperator f, final int[] xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return new int[] {};
		}
		final int[] accumulated = new int[n];
		accumulated[0] = head(xs);
		for (int i = 1; i < n; i++) {
			accumulated[i] = f.applyAsInt(accumulated[i - 1], xs[i]);
		}
		return accumulated;
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new sequence of the same length and type as xs whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static ImmutableIntArray accumulate(final IntBinaryOperator f, final ImmutableIntArray xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return ImmutableIntArray.of();
		}
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder(n);
		builder.add(head(xs));
		int accumulation = head(xs);
		for (int i = 1; i < n; i++) {
			accumulation = f.applyAsInt(accumulation, xs.get(i));
			builder.add(accumulation);
		}
		return builder.build().trimmed();
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new long array of the same length whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static long[] accumulate(final LongBinaryOperator f, final LongChain xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return new long[] {};
		}
		final long[] accumulated = new long[n];
		accumulated[0] = head(xs);
		for (int i = 1; i < n; i++) {
			accumulated[i] = f.applyAsLong(accumulated[i - 1], xs.elementAt(i));
		}
		return accumulated;
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new sequence of the same length and type as xs whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static long[] accumulate(final LongBinaryOperator f, final long[] xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return new long[] {};
		}
		final long[] accumulated = new long[n];
		accumulated[0] = head(xs);
		for (int i = 1; i < n; i++) {
			accumulated[i] = f.applyAsLong(accumulated[i - 1], xs[i]);
		}
		return accumulated;
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new sequence of the same length and type as xs whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static ImmutableLongArray accumulate(final LongBinaryOperator f, final ImmutableLongArray xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return ImmutableLongArray.of();
		}
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder(n);
		builder.add(head(xs));
		long accumulation = head(xs);
		for (int i = 1; i < n; i++) {
			accumulation = f.applyAsLong(accumulation, xs.get(i));
			builder.add(accumulation);
		}
		return builder.build().trimmed();
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new {@link ImmutableList} of the same length whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static <T> ImmutableList<T> accumulate(final BinaryOperator<T> f, final Chain<T> xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return ImmutableList.of();
		}
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		T prev = head(xs);
		builder.add(prev);
		for (int i = 1; i < n; i++) {
			prev = f.apply(prev, xs.elementAt(i));
			builder.add(prev);
		}
		return builder.build();
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new sequence of the same length and type as xs whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static <T> ImmutableList<T> accumulate(final BinaryOperator<T> f, final List<T> xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return ImmutableList.of();
		}
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		T prev = head(xs);
		builder.add(prev);
		for (int i = 1; i < n; i++) {
			prev = f.apply(prev, xs.get(i));
			builder.add(prev);
		}
		return builder.build();
	}

	/**
	 * The accumulate function accumulates values of a sequence according to the given binary operator (say *)
	 * in a left associative way. More specifically if the parameter sequence is {@code xs = (x_0,...,x_n)} it returns
	 * a new sequence of the same length and type as xs whose ith entry is: {@code x_0 *...* x_i}
	 *
	 * @param f - The operator defining how elements are accumulated
	 * @param xs - the sequence to accumulate
	 * @return The sequence resulting from the accumulation operation.
	 */
	public static <T> ImmutableList<T> accumulate(final BinaryOperator<T> f, final T[] xs)
	{
		final int n = len(xs);
		if (n == 0) {
			return ImmutableList.of();
		}
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		T prev = head(xs);
		builder.add(prev);
		for (int i = 1; i < n; i++) {
			prev = f.apply(prev, xs[i]);
			builder.add(prev);
		}
		return builder.build();
	}
}
