/**
 *
 */
package io.xyz.common.funcutils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import io.xyz.common.rangedescriptor.DoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.IntRangeDescriptor;
import io.xyz.common.rangedescriptor.RangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableDoubleRangeDescriptor;
import io.xyz.common.rangedescriptor.impl.ImmutableIntRangeDescriptor;

/**
 * @author t
 *
 */
public final class StreamUtil {
	private StreamUtil() {
	}

	public static int[] collect(final IntStream xs) {
		return xs.toArray();
	}

	public static double[] collect(final DoubleStream xs) {
		return xs.toArray();
	}

	public static long[] collect(final LongStream xs) {
		return xs.toArray();
	}

	public static <T> List<T> collect(final Stream<T> xs) {
		return xs.collect(Collectors.toList());
	}

	public static int[] collect(final IntRangeDescriptor xs) {
		return xs.toArray();
	}

	public static double[] collect(final DoubleRangeDescriptor xs) {
		return xs.toArray();
	}

	public static <T> List<T> collect(final RangeDescriptor<T> xs) {
		return xs.toList();
	}

	public static IntStream stream(final int... xs) {
		return Arrays.stream(xs);
	}

	public static DoubleStream stream(final double... xs) {
		return Arrays.stream(xs);
	}

	public static LongStream stream(final long... xs) {
		return Arrays.stream(xs);
	}

	public static IntStream stream(final int n) {
		return IntStream.range(0, n);
	}

	public static <T> Stream<T> stream(final Collection<T> xs) {
		return xs.stream();
	}

	public static IntRangeDescriptor descriptor(final int[] xs) {
		return ImmutableIntRangeDescriptor.from(xs);
	}

	public static DoubleRangeDescriptor descriptor(final double[] xs) {
		return ImmutableDoubleRangeDescriptor.from(xs);
	}

	@SafeVarargs
	public static <T> RangeDescriptor<T> descriptor(final T... xs) {
		return ImmutableIntRangeDescriptor.from(xs);
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		@SuppressWarnings("unused")
		final int[] ys = collect(stream(10).map(x -> x * x * x).distinct());

	}

}
