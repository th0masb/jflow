package io.xyz.chains.utilities;


import static io.xyz.chains.utilities.CompositionUtil.compose;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

import io.xyz.chains.BaseChain;
import io.xyz.chains.Chain;
import io.xyz.chains.DoubleChain;
import io.xyz.chains.IntChain;
import io.xyz.chains.LongChain;
import io.xyz.chains.rangedfunction.RangedDoubleFunction;
import io.xyz.chains.rangedfunction.RangedFunction;
import io.xyz.chains.rangedfunction.RangedIntFunction;
import io.xyz.chains.rangedfunction.RangedLongFunction;

/**
 * @author ThomasB
 * @since 25 Jan 2018
 */
public final class CollectionUtil
{

	// Uninstantiable
	private CollectionUtil()
	{
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified length/size function |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * A unified length/size function
	 *
	 * @param s - the String to calculate the length of
	 * @return the length of <b>s</b>
	 */
	public static int len(final String s)
	{
		return s.length();
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Collection to calculate the size of
	 * @return the size of <b>xs</b>
	 */
	public static <T> int len(final Collection<T> xs)
	{
		return xs.size();
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static <T> int len(final T[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - The {@link ImmutableIntArray} to calculate the length of.
	 * @return the length of <b>xs</b>
	 */
	public static int len(final ImmutableIntArray xs)
	{
		return xs.length();
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final int[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - The {@link ImmutableDoubleArray} to calculate the length of.
	 * @return the length of <b>xs</b>
	 */
	public static int len(final ImmutableDoubleArray xs)
	{
		return xs.length();
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final double[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - The {@link ImmutableLongArray} to calculate the length of.
	 * @return the length of <b>xs</b>
	 */
	public static int len(final ImmutableLongArray xs)
	{
		return xs.length();
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final long[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final int[][] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final double[][] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final long[][] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Generator to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final BaseChain xs)
	{
		return xs.linkCount();
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified String conversion function |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * A unified String conversion function
	 *
	 * @param t - the object to convert
	 * @return the string representation of <b>t</b>
	 */
	public static <T> String str(final T t)
	{
		return t.toString();
	}

	/**
	 * A unified String conversion function
	 *
	 * @param x - the primitive int to convert
	 * @return the String representation of <b>x</b>
	 */
	public static String str(final int x)
	{
		return Integer.toString(x);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param x - the primitive double to convert
	 * @return the String representation of <b>x</b>
	 */
	public static String str(final double x)
	{
		return Double.toString(x);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param x - the primitive long to convert
	 * @return the String representation of <b>x</b>
	 */
	public static String str(final long x)
	{
		return Long.toString(x);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param xs - the int Array to convert
	 * @return the String representation of <b>xs</b>
	 */
	public static String str(final int[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param xs - the double Array to convert
	 * @return the String representation of <b>xs</b>
	 */
	public static String str(final double[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param xs - the long Array to convert
	 * @return the String representation of <b>xs</b>
	 */
	public static String str(final long[] xs)
	{
		return Arrays.toString(xs);
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified List conversion function |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * A unified List conversion function
	 *
	 * @param c - the Collection to convert to a List
	 * @return an {@link ImmutableList} containing all elements of <b>c</b>
	 */
	public static <T> ImmutableList<T> asList(final Iterable<T> c)
	{
		return ImmutableList.copyOf(c);
	}

	/**
	 * A unified List conversion function
	 *
	 * @param xs - the elements to convert to a List
	 * @return an {@link ImmutableList} containing all elements of <b>xs</b>
	 */
	@SafeVarargs
	public static <T> ImmutableList<T> asList(final T... xs)
	{
		return ImmutableList.copyOf(xs);
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified Set conversion function |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * A unified Set conversion function
	 *
	 * @param c - the Collection to convert to a Set
	 * @return an {@link ImmutableSet} containing all elements of <b>c</b>
	 */
	public static <T> ImmutableSet<T> asSet(final Iterable<T> c)
	{
		return ImmutableSet.copyOf(c);
	}

	/**
	 * A unified Set conversion function
	 *
	 * @param xs - the elements to convert to a Set
	 * @return an {@link ImmutableSet} containing all elements of <b>xs</b>
	 */
	@SafeVarargs
	public static <T, E extends T> ImmutableSet<T> asSet(final E... xs)
	{
		return ImmutableSet.copyOf(xs);
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified RangedFunction conversion tool |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * A unified RangedFunction conversion function
	 *
	 * @param xs - the elements to convert
	 * @return the {@link RangedIntFunction} representation of <b>xs</b>
	 */
	public static RangedIntFunction asFunction(final ImmutableIntArray xs)
	{
		return RangedIntFunction.from(xs);
	}

	/**
	 * A unified RangedFunction conversion function
	 * <br>
	 * <br>
	 * <b>Note:</b> Mutations in the source Array will be reflected in the returned function.
	 *
	 * @param xs - the elements to convert
	 * @return the {@link RangedIntFunction} representation of <b>xs</b>
	 */
	public static RangedIntFunction asFunction(final int... xs)
	{
		return RangedIntFunction.from(xs);
	}

	/**
	 * A unified RangedFunction conversion function
	 *
	 * @param xs - the elements to convert
	 * @return the {@link RangedDoubleFunction} representation of <b>xs</b>
	 */
	public static RangedDoubleFunction asFunction(final ImmutableDoubleArray xs)
	{
		return RangedDoubleFunction.from(xs);
	}

	/**
	 * A unified RangedFunction conversion function
	 * <br>
	 * <br>
	 * <b>Note:</b> Mutations in the source Array will be reflected in the returned function.
	 *
	 * @param xs - the elements to convert
	 * @return the {@link RangedDoubleFunction} representation of <b>xs</b>
	 */
	public static RangedDoubleFunction asFunction(final double... xs)
	{
		return RangedDoubleFunction.from(xs);
	}

	/**
	 * A unified RangedFunction conversion function
	 *
	 * @param xs - the elements to convert
	 * @return the {@link RangedLongFunction} representation of <b>xs</b>
	 */
	public static RangedLongFunction asFunction(final ImmutableLongArray xs)
	{
		return RangedLongFunction.from(xs);
	}

	/**
	 * A unified RangedFunction conversion function
	 * <br>
	 * <br>
	 * <b>Note:</b> Mutations in the source Array will be reflected in the returned function.
	 *
	 * @param xs - the elements to convert
	 * @return the {@link RangedDoubleFunction} representation of <b>xs</b>
	 */
	public static RangedLongFunction asFunction(final long... xs)
	{
		return RangedLongFunction.from(xs);
	}

	/**
	 * A unified RangedFunction conversion function
	 * <br>
	 * <br>
	 * <b>Note:</b> Mutations in the source List will be reflected in the returned function.
	 *
	 * @param xs - the elements to convert
	 * @return the {@link RangedFunction} representation of <b>xs</b>
	 */
	public static <T> RangedFunction<T> asFunction(final List<T> xs)
	{
		return RangedFunction.from(xs);
	}

	/**
	 * A unified RangedFunction conversion function
	 * <br>
	 * <br>
	 * <b>Note:</b> Mutations in the source Array will be reflected in the returned function.
	 *
	 * @param xs - the elements to convert
	 * @return the {@link RangedFunction} representation of <b>xs</b>
	 */
	@SafeVarargs
	public static <T> RangedFunction<T> asFunction(final T... xs)
	{
		return RangedFunction.from(xs);
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified reverse function |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static int[] reverse(final int[] xs)
	{
		final int n = len(xs);
		final int[] reversed = new int[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static ImmutableIntArray reverse(final ImmutableIntArray xs)
	{
		final int n = len(xs);
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder();
		for (int i = 0; i < n; i++) {
			builder.add(xs.get(n - (i + 1)));
		}
		return builder.build().trimmed();
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static RangedIntFunction reverse(final IntChain xs)
	{
		return RangedIntFunction.of(i -> xs.getDescriptor().applyAsInt(len(xs) - (i + 1)), len(xs));
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static double[] reverse(final double[] xs)
	{
		final int n = len(xs);
		final double[] reversed = new double[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static ImmutableDoubleArray reverse(final ImmutableDoubleArray xs)
	{
		final int n = len(xs);
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder();
		for (int i = 0; i < n; i++) {
			builder.add(xs.get(n - (i + 1)));
		}
		return builder.build().trimmed();
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static RangedDoubleFunction reverse(final DoubleChain xs)
	{
		return RangedDoubleFunction.of(i -> xs.getDescriptor().applyAsDouble(len(xs) - (i + 1)), len(xs));
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static long[] reverse(final long[] xs)
	{
		final int n = len(xs);
		final long[] reversed = new long[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static ImmutableLongArray reverse(final ImmutableLongArray xs)
	{
		final int n = len(xs);
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
		for (int i = 0; i < n; i++) {
			builder.add(xs.get(n - (i + 1)));
		}
		return builder.build().trimmed();
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static RangedLongFunction reverse(final LongChain xs)
	{
		return RangedLongFunction.of(i -> xs.getDescriptor().applyAsLong(len(xs) - (i + 1)), len(xs));
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static <T> ImmutableList<T> reverse(final ImmutableList<T> xs)
	{
		return xs.reverse();
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static <T> ImmutableList<T> reverse(final List<T> xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (int i = len(xs) - 1; i > -1; i--) {
			builder.add(xs.get(i));
		}
		return builder.build();
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static <T> ImmutableList<T> reverse(final T[] xs)
	{
		final int n = len(xs);
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (int i = 0; i < n; i++) {
			builder.add(xs[n - (i + 1)]);
		}
		return builder.build();
	}

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static <T> RangedFunction<T> reverse(final Chain<T> xs)
	{
		return RangedFunction.of(i -> xs.getDescriptor().apply(len(xs) - (i + 1)), len(xs));
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified take function a la Haskell |
	 * -----------------------------------------------------------------------------------------------
	 *
	 * TODO - we could reduce amount of objects created here by implementing each individually */
	/**
	 * Take the first n elements from the beginning of an {@link RangedIntFunction} retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The sequence to take from
	 * @return a {@linkplain RangedIntFunction} representing the taken elements.
	 */
	public static RangedIntFunction take(final int n, final IntChain xs)
	{
		assert 0 <= n && n <= len(xs);
		return RangedIntFunction.of(xs.getDescriptor(), n);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The sequence to take from
	 * @return an {@link ImmutableIntArray} containing the taken elements.
	 */
	public static ImmutableIntArray take(final int n, final ImmutableIntArray xs)
	{
		return take(n, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The sequence to take from
	 * @return an int array containing the taken elements.
	 */
	public static int[] take(final int n, final int[] xs)
	{
		return take(n, asFunction(xs)).toArray();
	}

	/**
	 * Take the first n elements from the beginning of an {@link RangedDoubleFunction} retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The sequence to take from
	 * @return a {@linkplain RangedDoubleFunction} representing the taken elements.
	 */
	public static RangedDoubleFunction take(final int n, final DoubleChain xs)
	{
		assert 0 <= n && n <= len(xs);
		return RangedDoubleFunction.of(xs.getDescriptor(), n);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The array to take from
	 * @return an {@link ImmutableDoubleArray} containing the taken elements.
	 */
	public static ImmutableDoubleArray take(final int n, final ImmutableDoubleArray xs)
	{
		return take(n, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The array to take from
	 * @return a double array containing the taken elements.
	 */
	public static double[] take(final int n, final double[] xs)
	{
		return take(n, asFunction(xs)).toArray();
	}

	/**
	 * Take the first n elements from the beginning of an {@link RangedLongFunction} retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The sequence to take from
	 * @return a {@linkplain RangedLongFunction} representing the taken elements.
	 */
	public static RangedLongFunction take(final int n, final LongChain xs)
	{
		assert 0 <= n && n <= len(xs);
		return RangedLongFunction.of(xs.getDescriptor(), n);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The array to take from
	 * @return an {@link ImmutableLongArray} containing the taken elements.
	 */
	public static ImmutableLongArray take(final int n, final ImmutableLongArray xs)
	{
		return take(n, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The array to take from
	 * @return a long array containing the taken elements.
	 */
	public static long[] take(final int n, final long[] xs)
	{
		return take(n, asFunction(xs)).toArray();
	}

	/**
	 * Take the first n elements from the beginning of a {@link RangedFunction} retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The generator to take from
	 * @return a {@linkplain RangedFunction} representing the taken elements.
	 */
	public static <T> RangedFunction<T> take(final int n, final Chain<T> xs)
	{
		assert 0 <= n && n <= len(xs);
		return RangedFunction.of(xs.getDescriptor(), n);
	}

	/**
	 * Take the first n elements from the beginning of a List retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The List to take from
	 * @return an {@link ImmutableList} containing the taken elements.
	 */
	public static <T> ImmutableList<T> take(final int n, final List<T> xs)
	{
		return take(n, asFunction(xs)).toList();
	}

	/**
	 * Take the first n elements from the beginning of a Array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The array to take from
	 * @return an {@link ImmutableList} containing the taken elements.
	 */
	public static <T> ImmutableList<T> take(final int n, final T[] xs)
	{
		return take(n, asFunction(xs)).toList();
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified drop function a la Haskell |
	 * -----------------------------------------------------------------------------------------------
	 *
	 * TODO - we could reduce amount of objects created here by implementing each individually */

	/**
	 * Drop the first n elements from the beginning of an {@link RangedIntFunction} retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The sequence to drop from
	 * @return an {@linkplain RangedIntFunction} representing the remaining elements.
	 */
	public static RangedIntFunction drop(final int n, final IntChain xs)
	{
		assert 0 <= n && n <= len(xs);
		return RangedIntFunction.of(xs.getDescriptor().compose(i -> i + n), len(xs) - n);
	}

	/**
	 * Drop the first n elements from the beginning of an {@link ImmutableIntArray} retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The generator to drop from
	 * @return an {@linkplain ImmutableIntArray} containing the remaining elements.
	 */
	public static ImmutableIntArray drop(final int n, final ImmutableIntArray xs)
	{
		return drop(n, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Drop the first n elements from the beginning of an int array retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The int array to drop from
	 * @return an int array containing the remaining elements.
	 */
	public static int[] drop(final int n, final int[] xs)
	{
		return drop(n, asFunction(xs)).toArray();
	}

	/**
	 * Drop the first n elements from the beginning of an {@link RangedDoubleFunction} retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The sequence to drop from
	 * @return an {@linkplain RangedDoubleFunction} representing the remaining elements.
	 */
	public static RangedDoubleFunction drop(final int n, final DoubleChain xs)
	{
		assert 0 <= n && n <= len(xs);
		return RangedDoubleFunction.of(compose(xs.getDescriptor(), i -> i + n), len(xs) - n);
	}

	/**
	 * Drop the first n elements from the beginning of an {@link ImmutableDoubleArray} retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The array to drop from
	 * @return an {@linkplain ImmutableDoubleArray} containing the remaining elements.
	 */
	public static ImmutableDoubleArray drop(final int n, final ImmutableDoubleArray xs)
	{
		return drop(n, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The array to drop from
	 * @return a double array containing the remaining elements.
	 */
	public static double[] drop(final int n, final double[] xs)
	{
		return drop(n, asFunction(xs)).toArray();
	}

	/**
	 * Drop the first n elements from the beginning of an {@link RangedLongFunction} retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The array to drop from
	 * @return an {@linkplain RangedLongFunction} representing the remaining elements.
	 */
	public static RangedLongFunction drop(final int n, final LongChain xs)
	{
		assert 0 <= n && n <= len(xs);
		return RangedLongFunction.of(compose(xs.getDescriptor(), i -> i + n), len(xs) - n);
	}

	/**
	 * Drop the first n elements from the beginning of an {@link ImmutableLongArray} retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The generator to drop from
	 * @return an {@linkplain ImmutableLongArray} containing the remaining elements.
	 */
	public static ImmutableLongArray drop(final int n, final ImmutableLongArray xs)
	{
		return drop(n, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The array to drop from
	 * @return a long array containing the remaining elements.
	 */
	public static long[] drop(final int n, final long[] xs)
	{
		return drop(n, asFunction(xs)).toArray();
	}

	/**
	 * Drop the first n elements from the beginning of an {@link RangedFunction} retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The sequence to drop from
	 * @return an {@linkplain RangedFunction} representing the remaining elements.
	 */
	public static <T> RangedFunction<T> drop(final int n, final Chain<T> xs)
	{
		assert 0 <= n && n <= len(xs);
		return RangedFunction.of(compose(xs.getDescriptor(), i -> i + n), len(xs) - n);
	}

	/**
	 * Drop the first n elements from the beginning of a List retaining the remaining order.
	 *
	 * @param n - the number of elements to drop
	 * @param xs - The List to drop from
	 * @return an {@linkplain ImmutableList} containing the remaining elements.
	 */
	public static <T> ImmutableList<T> drop(final int n, final List<T> xs)
	{
		return drop(n, asFunction(xs)).toList();
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The array to drop from
	 * @return an {@linkplain ImmutableList} containing the remaining elements.
	 */
	public static <T> ImmutableList<T> drop(final int n, final T[] xs)
	{
		return drop(n, asFunction(xs)).toList();
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified head function a la Haskell |
	 * ----------------------------------------------------------------------------------------------- */

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static int head(final IntChain xs)
	{
		assert len(xs) > 0;
		return xs.elementAt(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static int head(final ImmutableIntArray xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static int head(final int[] xs)
	{
		assert len(xs) > 0;
		return xs[0];
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static double head(final DoubleChain xs)
	{
		assert len(xs) > 0;
		return xs.elementAt(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static double head(final ImmutableDoubleArray xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static double head(final double[] xs)
	{
		assert len(xs) > 0;
		return xs[0];
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static long head(final LongChain xs)
	{
		assert len(xs) > 0;
		return xs.elementAt(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static long head(final ImmutableLongArray xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static long head(final long[] xs)
	{
		assert len(xs) > 0;
		return xs[0];
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static <T> T head(final Chain<T> xs)
	{
		assert len(xs) > 0;
		return xs.elementAt(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static <T> T head(final List<T> xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static <T> T head(final T[] xs)
	{
		assert len(xs) > 0;
		return xs[0];
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified tail function a la Haskell |
	 * ----------------------------------------------------------------------------------------------- */

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static int tail(final IntChain xs)
	{
		assert len(xs) > 0;
		return xs.elementAt(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static int tail(final ImmutableIntArray xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static int tail(final int[] xs)
	{
		assert len(xs) > 0;
		return xs[len(xs) - 1];
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static double tail(final DoubleChain xs)
	{
		assert len(xs) > 0;
		return xs.elementAt(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static double tail(final ImmutableDoubleArray xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static double tail(final double[] xs)
	{
		assert len(xs) > 0;
		return xs[len(xs) - 1];
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static long tail(final LongChain xs)
	{
		assert len(xs) > 0;
		return xs.elementAt(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static long tail(final ImmutableLongArray xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static long tail(final long[] xs)
	{
		assert len(xs) > 0;
		return xs[len(xs) - 1];
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static <T> T tail(final Chain<T> xs)
	{
		assert len(xs) > 0;
		return xs.elementAt(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static <T> T tail(final List<T> xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static <T> T tail(final T[] xs)
	{
		assert len(xs) > 0;
		return xs[len(xs) - 1];
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified append function |
	 * -----------------------------------------------------------------------------------------------
	 *
	 * TODO - performance of method to append elements to the google primitives will depened on length */
	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static <T, E extends T> RangedFunction<T> append(final E x, final Chain<T> xs)
	{
		final int n = len(xs);
		return RangedFunction.of(i -> i < n ? xs.elementAt(i) : x, n + 1);
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static <T, E extends T> ImmutableList<T> append(final E x, final List<T> xs)
	{
		final int n = len(xs);
		final Chain<T> combined = RangedFunction.of(i -> i < n ? xs.get(i) : x, n + 1);
		return ImmutableList.copyOf(combined);
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static <T, E extends T> ImmutableList<T> append(final E x, final T[] xs)
	{
		final int n = len(xs);
		final Chain<T> combined = RangedFunction.of(i -> i < n ? xs[i] : x, n + 1);
		return ImmutableList.copyOf(combined);
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static RangedDoubleFunction append(final double x, final DoubleChain xs)
	{
		final int n = len(xs);
		return RangedDoubleFunction.of(i -> i < n ? xs.elementAt(i) : x, n + 1);
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static ImmutableDoubleArray append(final double x, final ImmutableDoubleArray xs)
	{
		return append(x, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static double[] append(final double x, final double[] xs)
	{
		final int n = len(xs);
		final double[] newArr = new double[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static RangedIntFunction append(final int x, final IntChain xs)
	{
		final int n = len(xs);
		return RangedIntFunction.of(i -> i < n ? xs.elementAt(i) : x, n + 1);
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static ImmutableIntArray append(final int x, final ImmutableIntArray xs)
	{
		return append(x, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static int[] append(final int x, final int[] xs)
	{
		final int n = len(xs);
		final int[] newArr = new int[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static RangedLongFunction append(final long x, final LongChain xs)
	{
		final int n = len(xs);
		return RangedLongFunction.of(i -> i < n ? xs.elementAt(i) : x, n + 1);
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static ImmutableLongArray append(final long x, final ImmutableLongArray xs)
	{
		return append(x, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static long[] append(final long x, final long[] xs)
	{
		final int n = len(xs);
		final long[] newArr = new long[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified insert function |
	 * -----------------------------------------------------------------------------------------------
	 *
	 * TODO - performance of method to append elements to the google primitives will depened on length */
	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static <T, E extends T> RangedFunction<T> insert(final E x, final Chain<T> xs)
	{
		final int n = len(xs);
		return RangedFunction.of(i -> i > 0 ? xs.elementAt(i - 1) : x, n + 1);
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static <T, E extends T> ImmutableList<T> insert(final E x, final T[] xs)
	{
		final int n = len(xs);
		final Chain<T> combined = RangedFunction.of(i -> i > 0 ? xs[i - 1] : x, n + 1);
		return ImmutableList.copyOf(combined);
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static <T, E extends T> ImmutableList<T> insert(final E x, final List<T> xs)
	{
		final int n = len(xs);
		final Chain<T> combined = RangedFunction.of(i -> i > 0 ? xs.get(i - 1) : x, n + 1);
		return ImmutableList.copyOf(combined);
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static RangedDoubleFunction insert(final double x, final DoubleChain xs)
	{
		final int n = len(xs);
		return RangedDoubleFunction.of(i -> i > 0 ? xs.elementAt(i - 1) : x, n + 1);
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static ImmutableDoubleArray insert(final double x, final ImmutableDoubleArray xs)
	{
		return insert(x, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static double[] insert(final double x, final double[] xs)
	{
		final int n = len(xs);
		final double[] newArr = new double[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static RangedIntFunction insert(final int x, final IntChain xs)
	{
		final int n = len(xs);
		return RangedIntFunction.of(i -> i > 0 ? xs.elementAt(i - 1) : x, n + 1);
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static ImmutableIntArray insert(final int x, final ImmutableIntArray xs)
	{
		return insert(x, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static int[] insert(final int x, final int[] xs)
	{
		final int n = len(xs);
		final int[] newArr = new int[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static RangedLongFunction insert(final long x, final LongChain xs)
	{
		final int n = len(xs);
		return RangedLongFunction.of(i -> i > 0 ? xs.elementAt(i - 1) : x, n + 1);
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static ImmutableLongArray insert(final long x, final ImmutableLongArray xs)
	{
		return insert(x, asFunction(xs)).toImmutableArray();
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static long[] insert(final long x, final long[] xs)
	{
		final int n = len(xs);
		final long[] newArr = new long[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	public static void main(final String[] args)
	{
		final int[] originalInt = { 1, 2, 3, 4, 5 };
		final String[] originalT = { "1", "2", "3", "4", "5" };

		final int[] x = drop(2, originalInt);
		final List<String> y = drop(4, originalT);

		System.out.println(str(x));
		System.out.println(str(y));

		final int[] z = take(2, originalInt);
		// final List<String> w = collect(take(4, originalT));

		// System.out.println(asString());
		System.out.println(str(z));
		// System.out.println(asString(w));

		// final IntUnaryOperator f = i -> x[i];
		// System.out.println(f.applyAsInt(0));
		// x[0] = 100;
		// System.out.println(f.applyAsInt(0));
	}
}
