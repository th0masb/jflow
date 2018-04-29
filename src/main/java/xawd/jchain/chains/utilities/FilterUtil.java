/**
 *
 */
package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.Arrays;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

/**
 * @author ThomasB
 * @since 25 Jan 2018
 */
public final class FilterUtil
{
	private FilterUtil()
	{
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static double[] filter(final DoublePredicate p, final double[] xs)
	{
		int arrcount = 0;
		final double[] filtered = new double[len(xs)];
		for (final double x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}


	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static int[] filter(final IntPredicate p, final int[] xs)
	{
		int arrcount = 0;
		final int[] filtered = new int[len(xs)];
		for (final int x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}


	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static long[] filter(final LongPredicate p, final long[] xs)
	{
		int arrcount = 0;
		final long[] filtered = new long[len(xs)];
		for (final long x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}
}
