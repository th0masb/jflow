/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.asList;
import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.CollectionUtil.take;

import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import io.xyz.common.geometry.BitArray;
import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableIntRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableRangeDescriptor;

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

	public static boolean all(final List<IntPredicate> ps, final IntRangeDescriptor xs) {
		for (int i = 0; i < len(xs); i++) {
			for (final IntPredicate p : ps) {
				if (!p.test(xs.get(i))) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean all(final IntPredicate p, final IntRangeDescriptor xs) {
		return all(asList(p), xs);
	}

	public static boolean all(final IntPredicate p, final int... xs) {
		return all(asList(p), xs);
	}

	public static boolean all(final List<IntPredicate> ps, final int... xs) {
		return all(ps, ImmutableIntRangeDescriptor.from(xs));
	}

	//---

	public static boolean all(final List<DoublePredicate> ps, final DoubleRangeDescriptor xs) {
		for (int i = 0; i < len(xs); i++) {
			for (final DoublePredicate p : ps) {
				if (!p.test(xs.get(i))) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean all(final DoublePredicate p, final DoubleRangeDescriptor xs) {
		return all(asList(p), xs);
	}

	public static boolean all(final DoublePredicate p, final double... xs) {
		return all(asList(p), xs);
	}

	public static boolean all(final List<DoublePredicate> ps, final double... xs) {
		return all(ps, ImmutableDoubleRangeDescriptor.from(xs));
	}
	//---

	public static <T> boolean all(final List<Predicate<T>> ps, final RangeDescriptor<T> xs) {
		for (int i = 0; i < len(xs); i++) {
			for (final Predicate<T> p : ps) {
				if (!p.test(xs.get(i))) {
					return false;
				}
			}
		}
		return true;
	}

	public static <T> boolean all(final Predicate<T> p, final RangeDescriptor<T> xs) {
		return all(asList(p), xs);
	}

	@SafeVarargs
	public static <T> boolean all(final List<Predicate<T>> ps, final T... xs) {
		return all(ps, ImmutableRangeDescriptor.from(xs));
	}

	@SafeVarargs
	public static <T> boolean all(final Predicate<T> p, final T... xs) {
		return all(p, ImmutableRangeDescriptor.from(xs));
	}

	public static <T> boolean all(final List<Predicate<T>> ps, final List<T> xs) {
		return all(ps, ImmutableRangeDescriptor.from(xs));
	}

	public static <T> boolean all(final Predicate<T> p, final List<T> xs) {
		return all(p, ImmutableRangeDescriptor.from(xs));
	}

	//---

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
