package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.head;
import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

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
	public static OptionalInt foldr(final IntBinaryOperator f, final int id, final int[] xs)
	{
		if (len(xs) == 0) {
			return OptionalInt.empty();
		}
		int cumulativeFold = id;
		for (int i = len(xs) - 1; i > -1; i--) {
			cumulativeFold = f.applyAsInt(xs[i], cumulativeFold);
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
	public static OptionalDouble foldr(final DoubleBinaryOperator f, final double id, final double[] xs)
	{
		throw new RuntimeException();
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
		throw new RuntimeException();
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
	public static <T, E extends T> Optional<T> foldr(final BinaryOperator<T> f, final E id, final Iterable<? extends T> xs)
	{
		throw new RuntimeException();
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
		if (len(xs) == 0) {
			return OptionalInt.empty();
		}
		int cumulativeFold = id;
		for (int i = 0; i < len(xs); i++) {
			cumulativeFold = f.applyAsInt(cumulativeFold, xs[i]);
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
	public static OptionalDouble foldl(final DoubleBinaryOperator f, final double id, final double[] xs)
	{
		throw new RuntimeException();
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
		throw new RuntimeException();
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
	public static <T, E extends T> Optional<T> foldl(final BinaryOperator<T> f, final E id, final Iterable<? extends T> xs)
	{
		throw new RuntimeException();
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
}
