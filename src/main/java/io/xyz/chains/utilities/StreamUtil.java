/**
 *
 */
package io.xyz.chains.utilities;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

import io.xyz.chains.Chain;
import io.xyz.chains.DoubleChain;
import io.xyz.chains.IntChain;
import io.xyz.chains.LongChain;

/**
 * @author ThomasB
 * @since 29 Jan 2018
 */
public final class StreamUtil {

	private StreamUtil() {
	}

	/**
	 * The collect function converts uncached sequences (generators and streams) into their
	 * default cached form. Primitives to primitive arrays and Objects to {@link ImmutableList}.
	 *
	 * @param xs - The uncached sequence
	 * @return the cached sequence in its default form.
	 */
	public static int[] collect(final IntChain xs)
	{
		return xs.toArray();
	}

	/**
	 * The collect function converts uncached sequences (generators and streams) into their
	 * default cached form. Primitives to primitive arrays and Objects to {@link ImmutableList}.
	 *
	 * @param xs - The uncached sequence
	 * @return the cached sequence in its default form.
	 */
	public static int[] collect(final IntStream xs)
	{
		return xs.toArray();
	}

	/**
	 * The collect function converts uncached sequences (generators and streams) into their
	 * default cached form. Primitives to primitive arrays and Objects to {@link ImmutableList}.
	 *
	 * @param xs - The uncached sequence
	 * @return the cached sequence in its default form.
	 */
	public static double[] collect(final DoubleChain xs)
	{
		return xs.toArray();
	}

	/**
	 * The collect function converts uncached sequences (generators and streams) into their
	 * default cached form. Primitives to primitive arrays and Objects to {@link ImmutableList}.
	 *
	 * @param xs - The uncached sequence
	 * @return the cached sequence in its default form.
	 */
	public static double[] collect(final DoubleStream xs)
	{
		return xs.toArray();
	}

	/**
	 * The collect function converts uncached sequences (generators and streams) into their
	 * default cached form. Primitives to primitive arrays and Objects to {@link ImmutableList}.
	 *
	 * @param xs - The uncached sequence
	 * @return the cached sequence in its default form.
	 */
	public static long[] collect(final LongChain xs)
	{
		return xs.toArray();
	}

	/**
	 * The collect function converts uncached sequences (generators and streams) into their
	 * default cached form. Primitives to primitive arrays and Objects to {@link ImmutableList}.
	 *
	 * @param xs - The uncached sequence
	 * @return the cached sequence in its default form.
	 */
	public static long[] collect(final LongStream xs)
	{
		return xs.toArray();
	}

	/**
	 * The collect function converts uncached sequences (generators and streams) into their
	 * default cached form. Primitives to primitive arrays and Objects to {@link ImmutableList}.
	 *
	 * @param xs - The uncached sequence
	 * @return the cached sequence in its default form.
	 */
	public static <T> ImmutableList<T> collect(final Stream<T> xs)
	{

		return xs.collect(ImmutableList.toImmutableList());
	}

	/**
	 * The collect function converts uncached sequences (generators and streams) into their
	 * default cached form. Primitives to primitive arrays and Objects to {@link ImmutableList}.
	 *
	 * @param xs - The uncached sequence
	 * @return the cached sequence in its default form.
	 */
	public static <T> ImmutableList<T> collect(final Chain<T> xs)
	{
		return xs.toList();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static IntStream stream(final IntChain xs)
	{
		return xs.stream();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static IntStream stream(final ImmutableIntArray xs)
	{
		return xs.stream();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static IntStream stream(final int... xs)
	{
		return Arrays.stream(xs);
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static DoubleStream stream(final DoubleChain xs)
	{
		return xs.stream();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static DoubleStream stream(final ImmutableDoubleArray xs)
	{
		return xs.stream();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static DoubleStream stream(final double... xs)
	{
		return Arrays.stream(xs);
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static LongStream stream(final LongChain xs)
	{
		return xs.stream();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static LongStream stream(final ImmutableLongArray xs)
	{
		return xs.stream();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static LongStream stream(final long... xs)
	{
		return Arrays.stream(xs);
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static <T> Stream<T> stream(final Chain<T> xs)
	{
		return xs.stream();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static <T> Stream<T> stream(final Collection<T> xs)
	{
		return xs.stream();
	}

	/**
	 * A unified streaming function for sequences. It simply streams a sequence.
	 *
	 * @param xs - The sequence
	 * @return the sequence stream
	 */
	public static <T> Stream<T> stream(final T[] xs)
	{
		return Arrays.stream(xs);
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		@SuppressWarnings("unused")
		final int[] ys = collect(stream(10).map(x -> x * x * x).distinct());
	}
}