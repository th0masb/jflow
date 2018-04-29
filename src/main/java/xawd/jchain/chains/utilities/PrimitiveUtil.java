/**
 *
 */
package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.FoldUtil.foldr;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class PrimitiveUtil
{
	private PrimitiveUtil()
	{
	}

	public static boolean isZero(final int x)
	{
		return x == 0;
	}

	public static boolean isZero(final double x)
	{
		return Math.abs(x) < 0.00001;
	}

	public static boolean bool(final double x)
	{
		return x != 0;
	}

	public static boolean bool(final long x)
	{
		return x != 0;
	}

	/**
	 * Calculates the sum of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static OptionalDouble sum(final double... xs)
	{
		return foldr((a, b) -> a + b, 0, xs);
	}

	/**
	 * Calculates the sum of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static OptionalInt sum(final int... xs)
	{
		return foldr(Math::addExact, 0, xs);
	}

	/**
	 * Calculates the sum of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static OptionalLong sum(final long... xs)
	{
		return foldr(Math::addExact, 0, xs);
	}


	/**
	 * Calculates the product of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static OptionalDouble product(final double... xs)
	{
		return foldr((a, b) -> a * b, 1, xs);
	}

	/**
	 * Calculates the product of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static OptionalInt product(final int... xs)
	{
		return foldr(Math::multiplyExact, 1, xs);
	}

	/**
	 * Calculates the product of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and longs
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static OptionalLong product(final long... xs)
	{
		return foldr(Math::multiplyExact, 1, xs);
	}

	/**
	 * Calculates the minimum value out of the parameter sequence
	 *
	 * @param a - The first value
	 * @param b - The second value
	 * @return minimum of <b>a</b> and <b>b</b>.
	 */
	public static int min(final int a, final int b)
	{
		return a < b ? a : b;
	}


	/**
	 * Calculates the minimum value out of the parameter sequence
	 *
	 * @param xs - the parameter sequence
	 * @return the minimum value in the sequence
	 */
	public static OptionalInt min(final int... xs)
	{
		return foldr(PrimitiveUtil::min, Integer.MAX_VALUE, xs);
	}

	/**
	 * Calculates the minimum value out of the parameter sequence
	 *
	 * @param a - The first value
	 * @param b - The second value
	 * @return minimum of <b>a</b> and <b>b</b>.
	 */
	public static double min(final double a, final double b)
	{
		return a < b ? a : b;
	}

	/**
	 * Calculates the minimum value out of the parameter sequence
	 *
	 * @param xs - the parameter sequence
	 * @return the minimum value in the sequence
	 */
	public static OptionalDouble min(final double... xs)
	{
		return foldr(PrimitiveUtil::min, Double.MAX_VALUE, xs);
	}

	/**
	 * Calculates the minimum value out of the parameter sequence
	 *
	 * @param a - The first value
	 * @param b - The second value
	 * @return minimum of <b>a</b> and <b>b</b>.
	 */
	public static long min(final long a, final long b)
	{
		return a < b ? a : b;
	}

	/**
	 * Calculates the minimum value out of the parameter sequence
	 *
	 * @param xs - the parameter sequence
	 * @return the minimum value in the sequence
	 */
	public static OptionalLong min(final long... xs)
	{
		return foldr(PrimitiveUtil::min, Long.MAX_VALUE, xs);
	}

	/**
	 * Calculates the maximum value out of the parameter sequence
	 *
	 * @param a - The first value
	 * @param b - The second value
	 * @return maximum of <b>a</b> and <b>b</b>.
	 */
	public static int max(final int a, final int b)
	{
		return a < b ? b : a;
	}

	/**
	 * Calculates the maximum value out of the parameter sequence
	 *
	 * @param xs - the parameter sequence
	 * @return the maximum value in the sequence
	 */
	public static OptionalInt max(final int... xs)
	{
		return foldr(PrimitiveUtil::max, Integer.MIN_VALUE, xs);
	}

	/**
	 * Calculates the maximum value out of the parameter sequence
	 *
	 * @param a - The first value
	 * @param b - The second value
	 * @return maximum of <b>a</b> and <b>b</b>.
	 */
	public static double max(final double a, final double b)
	{
		return a < b ? b : a;
	}

	/**
	 * Calculates the maximum value out of the parameter sequence
	 *
	 * @param xs - the parameter sequence
	 * @return the maximum value in the sequence
	 */
	public static OptionalDouble max(final double... xs)
	{
		return foldr(PrimitiveUtil::max, Double.MIN_VALUE, xs);
	}

	/**
	 * Calculates the maximum value out of the parameter sequence
	 *
	 * @param a - The first value
	 * @param b - The second value
	 * @return maximum of <b>a</b> and <b>b</b>.
	 */
	public static long max(final long a, final long b)
	{
		return a < b ? b : a;
	}

	/**
	 * Calculates the maximum value out of the parameter sequence
	 *
	 * @param xs - the parameter sequence
	 * @return the maximum value in the sequence
	 */
	public static OptionalLong max(final long... xs)
	{
		return foldr(PrimitiveUtil::max, Long.MIN_VALUE, xs);
	}

	public static double pow(final double x, final double power)
	{
		return Math.pow(x, power);
	}

	public static double sqrt(final double x)
	{
		return Math.sqrt(x);
	}

	public static double square(final double x)
	{
		return x * x;
	}

	public static int square(final int x)
	{
		return x * x;
	}

	public static double abs(final double x)
	{
		return Math.abs(x);
	}

	public static int abs(final int x)
	{
		return Math.abs(x);
	}

	public static double signum(final double x)
	{
		return Math.signum(x);
	}

	public static int signum(final int x)
	{
		return (int) Math.signum(x);
	}
}
