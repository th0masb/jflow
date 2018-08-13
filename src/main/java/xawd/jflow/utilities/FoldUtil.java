package xawd.jflow.utilities;

import static xawd.jflow.utilities.CollectionUtil.sizeOf;

import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

/**
 * Static methods for folding primitive arrays into a single value.
 * 
 * @author ThomasB
 * @since 14 Feb 2018
 */
public final class FoldUtil
{
	public FoldUtil()
	{
	}

	/**
	 * Reduces an array to a single value via some operator in a right associative
	 * manner.
	 *
	 * @param foldFunction
	 *            The binary operator describing how elements fold together
	 * @param id
	 *            The initial value of the fold
	 * @param xs
	 *            The array to fold
	 * @return Let us denote the fold operator by {@code *} and the array by
	 *         {@code [xs[0], ..., xs[n]]}. Then the result is:<br>
	 * 		<br>
	 *         {@code x[0] * (x[1] * (... * (x[n-1] * (x[n] * id))...)}
	 */
	public static int foldr(final IntBinaryOperator foldFunction, final int id, final int[] xs)
	{
		int cumulativeFold = id;
		for (int i = sizeOf(xs) - 1; i > -1; i--) {
			cumulativeFold = foldFunction.applyAsInt(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}

	/**
	 * Reduces an array to a single value via some operator in a right associative
	 * manner.
	 *
	 * @param foldFunction
	 *            The binary operator describing how elements fold together
	 * @param id
	 *            The initial value of the fold
	 * @param xs
	 *            The array to fold
	 * @return Let us denote the fold operator by {@code *} and the array by
	 *         {@code [xs[0], ..., xs[n]]}. Then the result is:<br>
	 * 		<br>
	 *         {@code x[0] * (x[1] * (... * (x[n-1] * (x[n] * id))...)}
	 */
	public static double foldr(final DoubleBinaryOperator foldFunction, final double id, final double[] xs)
	{
		double cumulativeFold = id;
		for (int i = sizeOf(xs) - 1; i > -1; i--) {
			cumulativeFold = foldFunction.applyAsDouble(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}

	/**
	 * Reduces an array to a single value via some operator in a right associative
	 * manner.
	 *
	 * @param foldFunction
	 *            The binary operator describing how elements fold together
	 * @param id
	 *            The initial value of the fold
	 * @param xs
	 *            The array to fold
	 * @return Let us denote the fold operator by {@code *} and the array by
	 *         {@code [xs[0], ..., xs[n]]}. Then the result is:<br>
	 * 		<br>
	 *         {@code x[0] * (x[1] * (... * (x[n-1] * (x[n] * id))...)}
	 */
	public static long foldr(final LongBinaryOperator foldFunction, final long id, final long[] xs)
	{
		long cumulativeFold = id;
		for (int i = sizeOf(xs) - 1; i > -1; i--) {
			cumulativeFold = foldFunction.applyAsLong(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}
}
