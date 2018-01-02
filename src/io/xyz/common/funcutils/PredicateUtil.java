/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.take;

import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import io.xyz.common.geometry.BitArray;

/**
 * @author t
 *
 */
public final class PredicateUtil {
	public PredicateUtil() {
	}

	/*
	 * all any
	 *
	 * takeWhile, takeUntil, dropWhile, dropUntil
	 */

	public static boolean all(final boolean... bs) {
		for (final boolean b : bs) {
			if (!b) {
				return false;
			}
		}
		return true;
	}

	public static boolean all(final BitArray bits) {
		return bits.cardinality() == bits.length();
	}

	public static boolean all(final IntPredicate p, final int... xs) {
		for (final int x : xs) {
			if (!p.test(x)) {
				return false;
			}
		}
		return true;
	}

	public static boolean all(final DoublePredicate p, final double... xs) {
		for (final double x : xs) {
			if (!p.test(x)) {
				return false;
			}
		}
		return true;
	}

	@SafeVarargs
	public static <T> boolean all(final Predicate<T> p, final T... xs) {
		for (final T x : xs) {
			if (!p.test(x)) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean all(final Predicate<T> p, final List<T> xs) {
		for (final T x : xs) {
			if (!p.test(x)) {
				return false;
			}
		}
		return true;
	}

	public static boolean any(final boolean... bs) {
		for (final boolean b : bs) {
			if (b) {
				return true;
			}
		}
		return false;
	}

	public static boolean any(final IntPredicate p, final int... xs) {
		for (final int x : xs) {
			if (p.test(x)) {
				return true;
			}
		}
		return false;
	}

	public static boolean any(final DoublePredicate p, final double... xs) {
		for (final double x : xs) {
			if (p.test(x)) {
				return true;
			}
		}
		return false;
	}

	@SafeVarargs
	public static <T> boolean any(final Predicate<T> p, final T... xs) {
		for (final T x : xs) {
			if (p.test(x)) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean any(final Predicate<T> p, final List<T> xs) {
		for (final T x : xs) {
			if (p.test(x)) {
				return true;
			}
		}
		return false;
	}

	public static int[] takeWhile(final IntPredicate p, final int... xs) {
		int takeCount = 0;
		for (int i = 0; i < xs.length; i++) {
			if (!p.test(xs[i])) {
				break;
			}
			takeCount++;
		}
		return take(takeCount, xs);
	}
}
