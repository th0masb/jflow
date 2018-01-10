/**
 *
 */
package io.xyz.common.funcutils;

import static io.xyz.common.funcutils.CollectionUtil.asDescriptor;

import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;

/**
 * @author t
 *
 */
public final class FilterUtil {
	private FilterUtil()
	{
	}

	public static IntRangeDescriptor filter(final IntPredicate p, final int[] xs)
	{
		return filter(p, asDescriptor(xs));
	}

	public static final IntRangeDescriptor filter(final IntPredicate p, final IntRangeDescriptor xs)
	{
		return xs.filter(p);
	}

	public static DoubleRangeDescriptor filter(final DoublePredicate p, final double[] xs)
	{
		return filter(p, asDescriptor(xs));
	}

	public static DoubleRangeDescriptor filter(final DoublePredicate p, final DoubleRangeDescriptor xs)
	{
		return xs.filter(p);
	}

	public static <T> RangeDescriptor<T> filter(final Predicate<T> p, final RangeDescriptor<T> xs)
	{
		return xs.filter(p);
	}

	public static <T> RangeDescriptor<T> filter(final Predicate<T> p, final T[] xs)
	{
		return filter(p, asDescriptor(xs));
	}

	public static <T> RangeDescriptor<T> filter(final Predicate<T> p, final List<T> xs)
	{
		return filter(p, asDescriptor(xs));
	}
}
