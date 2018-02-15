/**
 *
 */
package io.xyz.chains.utilities;


import static io.xyz.chains.utilities.CollectionUtil.len;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

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
 * @since 25 Jan 2018
 */
public final class FilterUtil
{
	private FilterUtil()
	{
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new double array
	 * containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static double[] filter(final DoublePredicate p, final DoubleChain xs)
	{
		final int n = len(xs);
		int arrcount = 0;
		final double[] filtered = new double[n];
		for (int i = 0; i < n; i++) {
			final double x = xs.elementAt(i);
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static ImmutableDoubleArray filter(final DoublePredicate p, final ImmutableDoubleArray xs)
	{
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder();
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.get(i))) {
				builder.add(xs.get(i));
			}
		}
		return builder.build().trimmed();
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static double[] filter(final DoublePredicate p, final double[] xs)
	{
		int arrcount = 0;
		final double[] filtered = new double[len(xs)];
		for (final double x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new int array
	 * containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static int[] filter(final IntPredicate p, final IntChain xs)
	{
		final int n = len(xs);
		int arrcount = 0;
		final int[] filtered = new int[n];
		for (int i = 0; i < n; i++) {
			final int x = xs.elementAt(i);
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static ImmutableIntArray filter(final IntPredicate p, final ImmutableIntArray xs)
	{
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder();
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.get(i))) {
				builder.add(xs.get(i));
			}
		}
		return builder.build().trimmed();
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static int[] filter(final IntPredicate p, final int[] xs)
	{
		int arrcount = 0;
		final int[] filtered = new int[len(xs)];
		for (final int x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new long array
	 * containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static long[] filter(final LongPredicate p, final LongChain xs)
	{
		final int n = len(xs);
		int arrcount = 0;
		final long[] filtered = new long[n];
		for (int i = 0; i < n; i++) {
			final long x = xs.elementAt(i);
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static ImmutableLongArray filter(final LongPredicate p, final ImmutableLongArray xs)
	{
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
		for (int i = 0; i < len(xs); i++) {
			if (p.test(xs.get(i))) {
				builder.add(xs.get(i));
			}
		}
		return builder.build().trimmed();
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static long[] filter(final LongPredicate p, final long[] xs)
	{
		int arrcount = 0;
		final long[] filtered = new long[len(xs)];
		for (final long x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new {@link ImmutableList} containing only
	 * those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static <T, E extends T> ImmutableList<E> filter(final Predicate<T> p, final E[] xs)
	{
		final ImmutableList.Builder<E> builder = ImmutableList.builder();
		for (final E element : xs) {
			if (p.test(element)) {
				builder.add(element);
			}
		}
		return builder.build();
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new sequence of the same type as the
	 * parameter containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static <T, E extends T> ImmutableList<E> filter(final Predicate<T> p, final List<E> xs)
	{
		final ImmutableList.Builder<E> builder = ImmutableList.builder();
		for (final E element : xs) {
			if (p.test(element)) {
				builder.add(element);
			}
		}
		return builder.build();
	}

	/**
	 * Filters the passed sequence according to the given predicate. Returns a new {@link ImmutableList}
	 * containing only those elements which satisfy the predicate. The relative order is maintained.
	 *
	 * @param p - The predicate
	 * @param xs - The sequence
	 * @return a sequence containing the filtered elements.
	 */
	public static <T, E extends T> ImmutableList<E> filter(final Predicate<T> p, final Chain<E> xs)
	{
		final ImmutableList.Builder<E> builder = ImmutableList.builder();
		for (final E element : xs) {
			if (p.test(element)) {
				builder.add(element);
			}
		}
		return builder.build();
	}
}
