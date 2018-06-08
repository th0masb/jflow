/**
 *
 */
package xawd.jflow.utilities;

import static xawd.jflow.utilities.CollectionUtil.sizeOf;
import static xawd.jflow.utilities.FoldUtil.foldr;

/**
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class PrimitiveUtil
{
	private PrimitiveUtil()
	{
	}

	/**
	 * Converts an integer to a boolean value.
	 *
	 * @param x
	 *            An integer.
	 * @return True if the argument is non-zero, false otherwise.
	 */
	public static boolean bool(final long x)
	{
		return x != 0;
	}

	/**
	 * Calculates the signum of a real number.
	 *
	 * @param x A number.
	 * @return The signum of the input.
	 */
	public static int signum(final double x)
	{
		return (int) Math.signum(x);
	}

	/**
	 * Calculates the signum of an input.
	 *
	 * @param x A number.
	 * @return The signum of the input.
	 */
	public static int signum(final long x)
	{
		return (int) Math.signum(x);
	}

	public static double sum(final double... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr((a, b) -> a + b, 0, xs);
	}

	public static int sum(final int... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(Math::addExact, 0, xs);
	}

	public static long sum(final long... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(Math::addExact, 0, xs);
	}

	public static double product(final double... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr((a, b) -> a * b, 1, xs);
	}

	public static int product(final int... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(Math::multiplyExact, 1, xs);
	}

	public static long product(final long... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(Math::multiplyExact, 1, xs);
	}

	public static int min(final int a, final int b)
	{
		return a < b ? a : b;
	}

	public static int min(final int... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::min, Integer.MAX_VALUE, xs);
	}

	public static double min(final double a, final double b)
	{
		return a < b ? a : b;
	}

	public static double min(final double... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::min, Double.POSITIVE_INFINITY, xs);
	}

	public static long min(final long a, final long b)
	{
		return a < b ? a : b;
	}

	public static long min(final long... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::min, Long.MAX_VALUE, xs);
	}

	public static int max(final int a, final int b)
	{
		return a < b ? b : a;
	}

	public static int max(final int... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::max, Integer.MIN_VALUE, xs);
	}

	public static double max(final double a, final double b)
	{
		return a < b ? b : a;
	}

	public static double max(final double... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::max, Double.NEGATIVE_INFINITY, xs);
	}

	public static long max(final long a, final long b)
	{
		return a < b ? b : a;
	}

	public static long max(final long... xs)
	{
		if (sizeOf(xs) == 0) {
			throw new IllegalArgumentException();
		}
		return foldr(PrimitiveUtil::max, Long.MIN_VALUE, xs);
	}
}
