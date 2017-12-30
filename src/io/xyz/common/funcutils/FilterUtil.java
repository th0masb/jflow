/**
 *
 */
package io.xyz.common.funcutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author t
 *
 */
public final class FilterUtil {
	private FilterUtil() {
	}

	public static int[] filter(final IntPredicate p, final int[] xs) {
		final int n = xs.length;
		final int[] tmp = new int[n];
		int counter = 0;
		for (int i = 0; i<n; i++) {
			if (p.test(xs[i])) {
				tmp[counter++] = xs[i];
			}
		}
		return Arrays.copyOf(tmp, counter);
	}

	public static double[] filter(final DoublePredicate p, final double[] xs) {
		final int n = xs.length;
		final double[] tmp = new double[n];
		int counter = 0;
		for (int i = 0; i<n; i++) {
			if (p.test(xs[i])) {
				tmp[counter++] = xs[i];
			}
		}
		return Arrays.copyOf(tmp, counter);
	}

	public static <T> List<T> filter(final Predicate<T> p, final T[] ts) {
		final int n = ts.length;
		final List<T> tmp = new ArrayList<>();
		for (int i = 0; i<n; i++) {
			if (p.test(ts[i])) {
				tmp.add(ts[i]);
			}
		}
		return tmp;
	}

	public static <T> List<T> filter(final Predicate<T> p, final List<T> ts) {
		final int n = ts.size();
		final List<T> tmp = new ArrayList<>();
		for (int i = 0; i<n; i++) {
			if (p.test(ts.get(i))) {
				tmp.add(ts.get(i));
			}
		}
		return tmp;
	}
}
