package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.MapUtil.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 *
 * @author t
 *
 */
public final class CollectionUtil {
	private CollectionUtil() {
	}

	public static <T> List<T> asList(final Collection<T> c) {
		return new ArrayList<>(c);
	}

	public static <T> List<T> asList(final T[] xs) {
		return map(UnaryOperator.identity(), xs);
	}

	public static <T> Set<T> asSet(final Collection<T> c) {
		return new HashSet<>(c);
	}

	public static <T> Set<T> asSet(final T[] xs) {
		final Set<T> newSet = new HashSet<>();
		for (final T x : xs) {
			newSet.add(x);
		}
		return newSet;
	}
}
