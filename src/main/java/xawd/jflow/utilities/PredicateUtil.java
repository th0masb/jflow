/**
 *
 */
package xawd.jflow.utilities;

import static xawd.jflow.utilities.CollectionUtil.sizeOf;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class PredicateUtil
{
	public PredicateUtil()
	{
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate.
	 *
	 * @param predicate
	 *            The predicate test.
	 * @param xs
	 *            The source sequence.
	 * @return True if every element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean all(final IntPredicate predicate, final int[] xs)
	{
		for (int i = 0; i < sizeOf(xs); i++) {
			if (!predicate.test(xs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate.
	 *
	 * @param predicate
	 *            The predicate test.
	 * @param xs
	 *            The source sequence.
	 * @return True if every element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean all(final DoublePredicate predicate, final double[] xs)
	{
		for (int i = 0; i < sizeOf(xs); i++) {
			if (!predicate.test(xs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate.
	 *
	 * @param predicate
	 *            The predicate test.
	 * @param xs
	 *            The source sequence.
	 * @return True if every element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean all(final LongPredicate predicate, final long[] xs)
	{
		for (int i = 0; i < sizeOf(xs); i++) {
			if (!predicate.test(xs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate.
	 *
	 * @param <E>
	 *            The upper bound on the source element type.
	 * @param predicate
	 *            The predicate test.
	 * @param xs
	 *            The source sequence.
	 * @return True if every element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static <E> boolean all(final Predicate<? super E> predicate, final Iterable<? extends E> xs)
	{
		for (final E t : xs) {
			if (!predicate.test(t)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether any element in a sequence satisfies a given predicate.
	 *
	 * @param predicate
	 *            The predicate test.
	 * @param xs
	 *            The source sequence.
	 * @return True if any element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean any(final IntPredicate predicate, final int[] xs)
	{
		for (int i = 0; i < sizeOf(xs); i++) {
			if (predicate.test(xs[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether any element in a sequence satisfies a given predicate.
	 *
	 * @param predicate
	 *            The predicate test.
	 * @param xs
	 *            The source sequence.
	 * @return True if any element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean any(final DoublePredicate predicate, final double[] xs)
	{
		for (int i = 0; i < sizeOf(xs); i++) {
			if (predicate.test(xs[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether any element in a sequence satisfies a given predicate.
	 *
	 * @param predicate
	 *            The predicate test.
	 * @param xs
	 *            The source sequence.
	 * @return True if any element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean any(final LongPredicate predicate, final long[] xs)
	{
		for (int i = 0; i < sizeOf(xs); i++) {
			if (predicate.test(xs[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether any element in a sequence satisfies a given predicate.
	 *
	 * @param <E>
	 *            The upper bound on the source element type.
	 * @param predicate
	 *            The predicate test.
	 * @param xs
	 *            The source sequence.
	 * @return True if any element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static <E> boolean any(final Predicate<? super E> predicate, final Iterable<? extends E> xs)
	{
		for (final E t : xs) {
			if (predicate.test(t)) {
				return true;
			}
		}
		return false;
	}
}
