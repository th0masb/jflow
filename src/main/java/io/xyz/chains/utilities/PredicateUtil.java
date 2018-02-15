/**
 *
 */
package io.xyz.chains.utilities;


import static io.xyz.chains.utilities.CollectionUtil.asFunction;
import static io.xyz.chains.utilities.CollectionUtil.asList;
import static io.xyz.chains.utilities.CollectionUtil.len;
import static io.xyz.chains.utilities.PrimitiveUtil.isZero;

import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

import io.xyz.chains.Chain;
import io.xyz.chains.DoubleChain;
import io.xyz.chains.IntChain;
import io.xyz.chains.LongChain;
import io.xyz.chains.misc.BitArray;

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

	// public static boolean all(final boolean[] bs)
	// {
	// for (final boolean b : bs) {
	// if (!b) {
	// return false;
	// }
	// }
	// return true;
	// }
	//
	// public static boolean all(final BitArray bits)
	// {
	// return bits.cardinality() == bits.length();
	// }

	/**
	 * Checks whether every element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a false result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether every element in <b>xs</b> satisfies <b>p</b>
	 */
	public static boolean all(final IntPredicate p, final IntChain xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs.elementAt(i))) {
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
	public static boolean all(final IntPredicate p, final ImmutableIntArray xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs.get(i))) {
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
	public static boolean all(final DoublePredicate p, final DoubleChain xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs.elementAt(i))) {
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
	public static boolean all(final DoublePredicate p, final ImmutableDoubleArray xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs.get(i))) {
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
	public static boolean all(final LongPredicate p, final LongChain xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs.elementAt(i))) {
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
	public static boolean all(final LongPredicate p, final ImmutableLongArray xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs.get(i))) {
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
	public static <T> boolean all(final Predicate<T> p, final Chain<T> xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs.elementAt(i))) {
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
	public static <T> boolean all(final Predicate<T> p, final List<T> xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs.get(i))) {
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
	public static <T> boolean all(final Predicate<T> p, final T[] xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (!p.test(xs[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean any(final boolean[] bs)
	{
		for (final boolean b : bs) {
			if (b) {
				return true;
			}
		}
		return false;
	}

	public static boolean any(final BitArray xs)
	{
		return xs.cardinality() > 0;
	}

	/**
	 * Checks whether some element in a sequence satisfies a given predicate. The method evaluates lazily, i.e. only
	 * it checks until it encounters a true result or the end of the sequence.
	 *
	 * @param p - The given predicate
	 * @param xs - The given sequence of elements
	 * @return whether an element in <b>xs</b> satisfies <b>p</b>
	 */
	public static boolean any(final IntPredicate p, final IntChain xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.elementAt(i))) {
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
	public static boolean any(final IntPredicate p, final ImmutableIntArray xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.get(i))) {
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
	public static boolean any(final DoublePredicate p, final DoubleChain xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.elementAt(i))) {
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
	public static boolean any(final DoublePredicate p, final ImmutableDoubleArray xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.get(i))) {
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
	public static boolean any(final LongPredicate p, final LongChain xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.elementAt(i))) {
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
	public static boolean any(final LongPredicate p, final ImmutableLongArray xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.get(i))) {
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
	public static <T> boolean any(final Predicate<T> p, final Chain<T> xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.elementAt(i))) {
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
	public static <T> boolean any(final Predicate<T> p, final List<T> xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.get(i))) {
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
	public static <T> boolean any(final Predicate<T> p, final T[] xs)
	{
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs[i])) {
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
	public static boolean allEqual(final IntChain xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (xs.elementAt(i) != xs.elementAt(i + 1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final ImmutableIntArray xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (xs.get(i) != xs.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final int[] xs)
	{
		return allEqual(asFunction(xs));
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final DoubleChain xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (!isZero(xs.elementAt(i) - xs.elementAt(i + 1))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final ImmutableDoubleArray xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (!isZero(xs.get(i) - xs.get(i + 1))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final double[] xs)
	{
		return allEqual(asFunction(xs));
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final LongChain xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (!isZero(xs.elementAt(i) - xs.elementAt(i + 1))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final ImmutableLongArray xs)
	{
		final int upper = len(xs) - 1;
		for (int i = 0; i < upper; i++) {
			if (!isZero(xs.get(i) - xs.get(i + 1))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static boolean allEqual(final long[] xs)
	{
		return allEqual(asFunction(xs));
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

	/**
	 * This function checks whether all elements in a sequence are equal.
	 *
	 * @param xs - The input sequence
	 * @return whether all elements in the sequence are semantically equal.
	 */
	public static <T> boolean allEqual(final T[] xs)
	{
		return allEqual(asList(xs));
	}
}
