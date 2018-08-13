/**
 *
 */
package xawd.jflow.utilities;

import static xawd.jflow.utilities.FoldUtil.foldr;


/**
 * Static methods for standard operations on primitive number sequences.
 * 
 * @author ThomasB
 * @since 26 Jan 2018
 */
public class PrimitiveUtil
{
	private PrimitiveUtil()
	{
	}

	/**
	 * Calculates the sum of the parameter values.
	 * 
	 * @param xs
	 *            The values to sum
	 * 
	 * @return The sum of the values if non-empty, zero otherwise.
	 */
	public static double sum(double... xs)
	{
		return foldr((a, b) -> a + b, 0, xs);
	}

	/**
	 * Calculates the sum of the parameter values. It is fail-fast on numerical
	 * overflow.
	 * 
	 * @param xs
	 *            The values to sum
	 * 
	 * @return The sum of the values if non-empty, zero otherwise.
	 */
	public static int sum(int... xs)
	{
		return foldr(Math::addExact, 0, xs);
	}

	/**
	 * Calculates the sum of the parameter values. It is fail-fast on numerical
	 * overflow.
	 * 
	 * @param xs
	 *            The values to sum
	 * 
	 * @return The sum of the values if non-empty, zero otherwise.
	 */
	public static long sum(long... xs)
	{
		return foldr(Math::addExact, 0, xs);
	}

	/**
	 * Calculates the product of the parameter values.
	 * 
	 * @param xs
	 *            The values to multiply
	 * 
	 * @return The product of the values if non-empty, one otherwise.
	 */
	public static double product(double... xs)
	{
		return foldr((a, b) -> a * b, 1, xs);
	}

	/**
	 * Calculates the product of the parameter values. It is fail-fast on numerical
	 * overflow.
	 * 
	 * @param xs
	 *            The values to multiply
	 * 
	 * @return The product of the values if non-empty, one otherwise.
	 */
	public static int product(int... xs)
	{
		return foldr(Math::multiplyExact, 1, xs);
	}

	/**
	 * Calculates the product of the parameter values. It is fail-fast on numerical
	 * overflow.
	 * 
	 * @param xs
	 *            The values to multiply
	 * 
	 * @return The product of the values if non-empty, one otherwise.
	 */
	public static long product(long... xs)
	{
		return foldr(Math::multiplyExact, 1, xs);
	}

	/**
	 * Calculates the minimum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs
	 *            The values to find the minimum of.
	 * 
	 * @return The minimum value in the sequence.
	 */
	public static int min(int... xs)
	{
		Exceptions.requireArgument(xs.length > 0);
		return foldr(Math::min, Integer.MAX_VALUE, xs);
	}

	/**
	 * Calculates the minimum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs
	 *            The values to find the minimum of.
	 * 
	 * @return The minimum value in the sequence.
	 */
	public static double min(double... xs)
	{
		return foldr(Math::min, Double.POSITIVE_INFINITY, xs);
	}

	/**
	 * Calculates the minimum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs
	 *            The values to find the minimum of.
	 * 
	 * @return The minimum value in the sequence.
	 */
	public static long min(long... xs)
	{
		return foldr(Math::min, Long.MAX_VALUE, xs);
	}

	/**
	 * Calculates the maximum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs
	 *            The values to find the maximum of.
	 * 
	 * @return The maximum value in the sequence.
	 */
	public static int max(int... xs)
	{
		Exceptions.requireArgument(xs.length > 0);
		return foldr(Math::max, Integer.MIN_VALUE, xs);
	}

	/**
	 * Calculates the maximum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs
	 *            The values to find the maximum of.
	 * 
	 * @return The maximum value in the sequence.
	 */
	public static double max(double... xs)
	{
		return foldr(Math::max, Double.NEGATIVE_INFINITY, xs);
	}

	/**
	 * Calculates the maximum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs
	 *            The values to find the maximum of.
	 * 
	 * @return The maximum value in the sequence.
	 */
	public static long max(long... xs)
	{
		return foldr(Math::max, Long.MIN_VALUE, xs);
	}
}
