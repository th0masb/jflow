/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;

import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import io.xyz.common.generators.DoubleGenerator;
import io.xyz.common.generators.IntGenerator;
import io.xyz.common.generators.Generator;

/**
 * @author t
 *
 */
public final class FilterUtil {
	private FilterUtil()
	{
	}

	public static IntGenerator filter(final IntPredicate p, final int[] xs)
	{
		return filter(p, asDescriptor(xs));
	}

	public static final IntGenerator filter(final IntPredicate p, final IntGenerator xs)
	{
		return xs.filter(p);
	}

	public static DoubleGenerator filter(final DoublePredicate p, final double[] xs)
	{
		return filter(p, asDescriptor(xs));
	}

	public static DoubleGenerator filter(final DoublePredicate p, final DoubleGenerator xs)
	{
		return xs.filter(p);
	}

	public static <T> Generator<T> filter(final Predicate<T> p, final Generator<T> xs)
	{
		return xs.filter(p);
	}

	public static <T> Generator<T> filter(final Predicate<T> p, final T[] xs)
	{
		return filter(p, asDescriptor(xs));
	}

	public static <T> Generator<T> filter(final Predicate<T> p, final List<T> xs)
	{
		return filter(p, asDescriptor(xs));
	}
}
