package io.xyz.chains.utilities;


import static io.xyz.chains.utilities.CollectionUtil.len;
import static io.xyz.chains.utilities.CollectionUtil.str;
import static io.xyz.chains.utilities.RangeUtil.rangei;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

import io.xyz.chains.Chain;
import io.xyz.chains.DoubleChain;
import io.xyz.chains.IntChain;
import io.xyz.chains.LongChain;
import io.xyz.chains.rangedfunction.RangedDoubleFunction;
import io.xyz.chains.rangedfunction.RangedFunction;
import io.xyz.chains.rangedfunction.RangedIntFunction;
import io.xyz.chains.rangedfunction.RangedLongFunction;

/**
 * Provides a slice method for extracting ranges of elements from sequences and manipulating orders etc.
 *
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class SliceUtil
{
	private SliceUtil() {}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static RangedDoubleFunction slice(final IntChain slicer, final DoubleChain xs)
	{
		return RangedDoubleFunction.of(i -> xs.elementAt(slicer.getDescriptor().applyAsInt(i)), len(slicer));
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static double[] slice(final IntChain slicer, final double[] xs)
	{
		final double[] sliced = new double[len(slicer)];
		for (int i = 0; i < len(slicer); i++) {
			sliced[i] = xs[slicer.getDescriptor().applyAsInt(i)];
		}
		return sliced;
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static ImmutableDoubleArray slice(final IntChain slicer, final ImmutableDoubleArray xs)
	{
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder(len(slicer));
		for (int i = 0; i < len(slicer); i++) {
			builder.add(xs.get(slicer.getDescriptor().applyAsInt(i)));
		}
		return builder.build();
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static RangedIntFunction slice(final IntChain slicer, final IntChain xs)
	{
		return RangedIntFunction.of(i -> xs.elementAt(slicer.getDescriptor().applyAsInt(i)), len(slicer));
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static int[] slice(final IntChain slicer, final int[] xs)
	{
		final int[] sliced = new int[len(slicer)];
		for (int i = 0; i < len(slicer); i++) {
			sliced[i] = xs[slicer.getDescriptor().applyAsInt(i)];
		}
		return sliced;
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static ImmutableIntArray slice(final IntChain slicer, final ImmutableIntArray xs)
	{
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder(len(slicer));
		for (int i = 0; i < len(slicer); i++) {
			builder.add(xs.get(slicer.getDescriptor().applyAsInt(i)));
		}
		return builder.build();
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static RangedLongFunction slice(final IntChain slicer, final LongChain xs)
	{
		return RangedLongFunction.of(i -> xs.elementAt(slicer.getDescriptor().applyAsInt(i)), len(slicer));
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static long[] slice(final IntChain slicer, final long[] xs)
	{
		final long[] sliced = new long[len(slicer)];
		for (int i = 0; i < len(slicer); i++) {
			sliced[i] = xs[slicer.getDescriptor().applyAsInt(i)];
		}
		return sliced;
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static ImmutableLongArray slice(final IntChain slicer, final ImmutableLongArray xs)
	{
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder(len(slicer));
		for (int i = 0; i < len(slicer); i++) {
			builder.add(xs.get(slicer.getDescriptor().applyAsInt(i)));
		}
		return builder.build();
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static <T> RangedFunction<T> slice(final IntChain slicer, final Chain<T> xs)
	{
		return RangedFunction.of(i -> xs.elementAt(slicer.getDescriptor().applyAsInt(i)), len(slicer));
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static <T> ImmutableList<T> slice(final IntChain slicer, final List<T> xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (int i = 0; i < len(slicer); i++) {
			builder.add(xs.get(slicer.getDescriptor().applyAsInt(i)));
		}
		return builder.build();
	}

	/**
	 * Retrieves the elements of a sequence at positions described by the target set of the slicer and in
	 * the order those positions occur in the slicer. The sliced sequence is unaffected and the elements
	 * in question are returned in a new sequence of the same type as the parameter (except for Object array
	 * parameter which are returned as {@link ImmutableList}).
	 *
	 * @param slicer - An instance of {@link IntChain} describing the positions to slice out of the parameter sequence.
	 * @param xs - The sequence to slice
	 * @return a new sequence of the same type containing the sliced elements.
	 */
	public static <T> ImmutableList<T> slice(final IntChain slicer, final T[] xs)
	{
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (int i = 0; i < len(slicer); i++) {
			builder.add(xs[slicer.getDescriptor().applyAsInt(i)]);
		}
		return builder.build();
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final int[] array = {1, 3, 5, 2, 5, 6};
		final List<String> list = ImmutableList.of("1", "3", "5", "2", "5", "6");

		final int[] slicedArray = slice(rangei(5, 0, -2), array);
		final List<String> slicedList = slice(rangei(5, 0, -2), list);

		System.out.println(str(slicedArray));
		System.out.println(str(slicedList));
		System.out.println();

		final int[] arrayrotated4 = slice(Slicers.rotate(-4, len(array)), array);
		final List<String> listrotated4 = slice(Slicers.rotate(-4, len(list)), list);

		System.out.println(str(arrayrotated4));
		System.out.println(str(listrotated4));
	}
}