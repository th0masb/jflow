/**
 *
 */
package xawd.jflow.utilities;

import static xawd.jflow.utilities.CollectionUtil.sizeOf;

import java.util.Arrays;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

/**
 * Static methods for filtering primitive arrays using predicates.
 * 
 * @author ThomasB
 * @since 25 Jan 2018
 */
public final class FilterUtil
{
	private FilterUtil()
	{
	}

	/**
	 * Filters an array according to some predicate.
	 *
	 * @param p
	 *            The predicate.
	 * @param xs
	 *            The source.
	 * @return An array which contains only those elements in the source who pass
	 *         the predicate test.
	 */
	public static double[] filter(final DoublePredicate p, final double[] xs)
	{
		int arrcount = 0;
		final double[] filtered = new double[sizeOf(xs)];
		for (final double x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters an array according to some predicate.
	 *
	 * @param p
	 *            The predicate.
	 * @param xs
	 *            The source.
	 * @return An array which contains only those elements in the source who pass
	 *         the predicate test.
	 */
	public static int[] filter(final IntPredicate p, final int[] xs)
	{
		int arrcount = 0;
		final int[] filtered = new int[sizeOf(xs)];
		for (final int x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters an array according to some predicate.
	 *
	 * @param p
	 *            The predicate.
	 * @param xs
	 *            The source.
	 * @return An array which contains only those elements in the source who pass
	 *         the predicate test.
	 */
	public static long[] filter(final LongPredicate p, final long[] xs)
	{
		int arrcount = 0;
		final long[] filtered = new long[sizeOf(xs)];
		for (final long x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}
}
