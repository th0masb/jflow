/**
 *
 */
package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

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

	/* all any
	 *
	 * takeWhile, takeUntil, dropWhile, dropUntil */

	/**
	 * Checks whether every element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a false result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether every element in <b>xs</b> satisfies <b>p</b>
	 */
	public static boolean all(final IntPredicate p, final int[] xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a false result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether every element in <b>xs</b> satisfies <b>p</b>
	 */
	public static boolean all(final DoublePredicate p, final double[] xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a false result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether every element in <b>xs</b> satisfies <b>p</b>
	 */
	public static boolean all(final LongPredicate p, final long[] xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a false result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether every element in <b>xs</b> satisfies <b>p</b>
	 */
	public static <T> boolean all(final Predicate<? super T> p, final Iterable<T> xs)
	{
		for (final T t : xs) {
			if (!p.test(t)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Checks whether some element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a true result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether an element in <b>xs</b> satisfies <b>p</b>
	 */
	public static boolean any(final IntPredicate p, final int[] xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether some element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a true result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether an element in <b>xs</b> satisfies <b>p</b>
	 */
	public static boolean any(final DoublePredicate p, final double[] xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether some element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a true result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether an element in <b>xs</b> satisfies <b>p</b>
	 */
	public static boolean any(final LongPredicate p, final long[] xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether some element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a true result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether an element in <b>xs</b> satisfies <b>p</b>
	 */
	public static <T> boolean any(final Predicate<? super T> p, final Iterable<T> xs)
	{
		for (final T t : xs) {
			if (p.test(t)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final int[] xs)
	{
		throw new RuntimeException();
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final double[] xs)
	{
		throw new RuntimeException();
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final long[] xs)
	{
		throw new RuntimeException();
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static <T> boolean allEqual(final Iterable<T> xs)
	{
		T last = null;
		for (final T x : xs) {
			if (last != null && !x.equals(last)) {
				return false;
			}
			last = x;
		}
		return true;
	}
}
