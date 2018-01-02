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

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		@SuppressWarnings("unused")
		final int[] ys = collect(
				stream(10).map(
						x -> x * x * x).distinct());

	}

}
