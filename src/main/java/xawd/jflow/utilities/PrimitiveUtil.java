/**
 *
 */
package xawd.jflow.utilities;


import static xawd.jflow.utilities.CollectionUtil.len;
import static xawd.jflow.utilities.FoldUtil.foldr;

/**
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class PrimitiveUtil
{
	private static final double EPSILON = 0.00001;

	private PrimitiveUtil()
	{
	}

	/**
	 * Checks if value is equal to zero
	 */
	public static boolean isZero(final int x)
	{
		return x == 0;
	}

	/**
	 * Checks if value is equal to zero
	 */
	public static boolean isZero(final long x)
	{
		return x == 0;
	}

	/**
	 * Checks if value is equal to zero
	 */
	public static boolean isZero(final double x)
	{
		return Math.abs(x) < EPSILON;
	}

	/**
	 * Converts value to boolean
	 */
	public static boolean bool(final int x)
	{
		return !isZero(x);
	}

	/**
	 * Converts value to boolean
	 */
	public static boolean bool(final double x)
	{
		return !isZero(x);
	}

	/**
	 * Converts value to boolean
	 */
	public static boolean bool(final long x)
	{
		return !isZero(x);
	}

	/**
	 * Calculate signum of a numerical value
	 */
	public static int signum(final double x)
	{
		return isZero(x) ? 0 : x > 0 ? 1 : -1;
	}

	/**
	 * Calculate signum of a numerical value
	 */
	public static int signum(final int x)
	{
		return isZero(x) ? 0 : x > 0 ? 1 : -1;
	}

	/**
	 * Calculate signum of a numerical value
	 */
	public static int signum(final long x)
	{
		return isZero(x) ? 0 : x > 0 ? 1 : -1;
	}

	/**
	 * Calculates the sum of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static double sum(final double... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr((a, b) -> a + b, 0, xs);
	}

	/**
	 * Calculates the sum of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static int sum(final int... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(Math::addExact, 0, xs);
	}

	/**
	 * Calculates the sum of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static long sum(final long... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(Math::addExact, 0, xs);
	}


	/**
	 * Calculates the product of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static double product(final double... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr((a, b) -> a * b, 1, xs);
	}

	/**
	 * Calculates the product of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and ints
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static int product(final int... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(Math::multiplyExact, 1, xs);
	}

	/**
	 * Calculates the product of all primitive elements in the parameter sequence. This function is fail-fast on numerical
	 * overflow for longs and longs
	 *
	 * @param xs - the sequence to sum
	 * @return the sum of all elements.
	 */
	public static long product(final long... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(Math::multiplyExact, 1, xs);
	}

	/**
	 * Calculates the minimum value out of two values
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
	public static int min(final int... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::min, Integer.MAX_VALUE, xs);
	}

	/**
	 * Calculates the minimum value out of two values
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
	public static double min(final double... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::min, Double.POSITIVE_INFINITY, xs);
	}

	/**
	 * Calculates the minimum value out of two values
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
	public static long min(final long... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::min, Long.MAX_VALUE, xs);
	}

	/**
	 * Calculates the maximum value out of two values
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
	public static int max(final int... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::max, Integer.MIN_VALUE, xs);
	}

	/**
	 * Calculates the maximum value out of two values
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
	public static double max(final double... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::max, Double.NEGATIVE_INFINITY, xs);
	}

	/**
	 * Calculates the maximum value out of two values
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
	public static long max(final long... xs)
	{
		if (len(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::max, Long.MIN_VALUE, xs);
	}
}
