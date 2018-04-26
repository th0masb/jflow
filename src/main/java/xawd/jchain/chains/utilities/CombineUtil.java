/**
 *
 */
package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.asFunction;
import static xawd.jchain.chains.utilities.CollectionUtil.len;
import static xawd.jchain.chains.utilities.CollectionUtil.reverse;
import static xawd.jchain.chains.utilities.CollectionUtil.str;
import static xawd.jchain.chains.utilities.CollectionUtil.tail;
import static xawd.jchain.chains.utilities.FilterUtil.filter;
import static xawd.jchain.chains.utilities.FoldUtil.accumulate;
import static xawd.jchain.chains.utilities.MapUtil.intMap;
import static xawd.jchain.chains.utilities.PrimitiveUtil.min;
import static xawd.jchain.chains.utilities.PrimitiveUtil.sum;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

import xawd.jchain.chains.Chain;
import xawd.jchain.chains.DoubleChain;
import xawd.jchain.chains.IntChain;
import xawd.jchain.chains.LongChain;
import xawd.jchain.chains.rangedfunction.RangedDoubleFunction;
import xawd.jchain.chains.rangedfunction.RangedFunction;
import xawd.jchain.chains.rangedfunction.RangedIntFunction;
import xawd.jchain.chains.rangedfunction.RangedLongFunction;

/**
 * @author ThomasB
 * @since 14 Feb 2018
 */
public final class CombineUtil
{
	private CombineUtil()
	{
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static double[] join(final double[]... sequences)
	{
		assert len(sequences) > 0;
		final int totalLen = sum(intMap(arr -> len(arr), asFunction(sequences))).getAsInt();
		final double[] concatted = new double[totalLen];
		int indexCount = 0;
		for (final double[] arr : sequences) {
			final int n = len(arr);
			System.arraycopy(arr, 0, concatted, indexCount, n);
			indexCount += n;
		}
		return concatted;
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static ImmutableDoubleArray join(final ImmutableDoubleArray... sequences)
	{
		assert len(sequences) > 0;
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder();
		for (final ImmutableDoubleArray arr : sequences) {
			builder.addAll(arr);
		}
		return builder.build().trimmed();
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static RangedDoubleFunction join(final DoubleChain... sequences)
	{
		assert len(sequences) > 0;

		final List<DoubleChain> nonEmpty = filter(g -> len(g) > 0, sequences);
		if (len(nonEmpty) == 0) {
			return RangedDoubleFunction.empty();
		}
		final int[] cumulatedLens = accumulate((a, b) -> a + b, intMap(g -> len(g), nonEmpty));
		final int total = tail(cumulatedLens);

		return RangedDoubleFunction.of(i -> {
			final int j = Arrays.binarySearch(cumulatedLens, i);
			if (j >= 0) {
				return nonEmpty.get(j + 1).getDescriptor().applyAsDouble(0);
			}
			final int insertPoint = -j - 1;
			final int y = insertPoint == 0 ? 0 : cumulatedLens[insertPoint - 1];
			return nonEmpty.get(insertPoint).getDescriptor().applyAsDouble(i - y);
		}, total);
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static int[] join(final int[]... sequences)
	{
		assert len(sequences) > 0;
		final int totalLen = sum(intMap(arr -> len(arr), asFunction(sequences))).getAsInt();
		final int[] concatted = new int[totalLen];
		int indexCount = 0;
		for (final int[] arr : sequences) {
			final int n = len(arr);
			System.arraycopy(arr, 0, concatted, indexCount, n);
			indexCount += n;
		}
		return concatted;
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static ImmutableIntArray join(final ImmutableIntArray... sequences)
	{
		assert len(sequences) > 0;
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder();
		for (final ImmutableIntArray arr : sequences) {
			builder.addAll(arr);
		}
		return builder.build().trimmed();
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static RangedIntFunction join(final IntChain... sequences)
	{
		assert len(sequences) > 0;

		final List<IntChain> nonEmpty = filter(g -> len(g) > 0, sequences);
		if (len(nonEmpty) == 0) {
			return RangedIntFunction.empty();
		}
		final int[] cumulatedLens = accumulate((a, b) -> a + b, intMap(g -> len(g), nonEmpty));
		final int total = tail(cumulatedLens);

		return RangedIntFunction.of(i -> {
			final int j = Arrays.binarySearch(cumulatedLens, i);
			if (j >= 0) {
				return nonEmpty.get(j + 1).getDescriptor().applyAsInt(0);
			}
			final int ip = -j - 1;
			final int y = ip == 0 ? 0 : cumulatedLens[ip - 1];
			return nonEmpty.get(ip).getDescriptor().applyAsInt(i - y);
		}, total);
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static long[] join(final long[]... sequences)
	{
		assert len(sequences) > 0;
		final int totalLen = sum(intMap(arr -> len(arr), asFunction(sequences))).getAsInt();
		final long[] concatted = new long[totalLen];
		int indexCount = 0;
		for (final long[] arr : sequences) {
			final int n = len(arr);
			System.arraycopy(arr, 0, concatted, indexCount, n);
			indexCount += n;
		}
		return concatted;
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static ImmutableLongArray join(final ImmutableLongArray... sequences)
	{
		assert len(sequences) > 0;
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
		for (final ImmutableLongArray arr : sequences) {
			builder.addAll(arr);
		}
		return builder.build().trimmed();
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static RangedLongFunction join(final LongChain... sequences)
	{
		assert len(sequences) > 0;

		final List<LongChain> nonEmpty = filter(g -> len(g) > 0, sequences);
		if (len(nonEmpty) == 0) {
			return RangedLongFunction.empty();
		}
		final int[] cumulatedLens = accumulate((a, b) -> a + b, intMap(g -> len(g), nonEmpty));
		final int total = tail(cumulatedLens);

		return RangedLongFunction.of(i -> {
			final int j = Arrays.binarySearch(cumulatedLens, i);
			if (j >= 0) {
				return nonEmpty.get(j + 1).getDescriptor().applyAsLong(0);
			}
			final int ip = -j - 1;
			final int y = ip == 0 ? 0 : cumulatedLens[ip - 1];
			return nonEmpty.get(ip).getDescriptor().applyAsLong(i - y);
		}, total);
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	@SafeVarargs
	public static <T> ImmutableList<T> join(final List<? extends T>... sequences)
	{
		assert len(sequences) > 0;
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (final List<? extends T> arr : sequences) {
			builder.addAll(arr);
		}
		return builder.build();
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	public static <T> ImmutableList<T> join(final List<List<? extends T>> sequences)
	{
		assert len(sequences) > 0;
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (final List<? extends T> arr : sequences) {
			builder.addAll(arr);
		}
		return builder.build();
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	@SafeVarargs
	public static <T> ImmutableList<T> join(final T[]... sequences)
	{
		assert len(sequences) > 0;
		final ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (final T[] arr : sequences) {
			builder.add(arr);
		}
		return builder.build();
	}

	/**
	 * Joins the parameter sequences end to end in the specified order to form a new sequence of the same type as those passed
	 *
	 * @param sequences - the sequences to join together
	 * @return the joined sequence
	 */
	@SafeVarargs
	public static <T> RangedFunction<T> join(final Chain<? extends T>... sequences)
	{
		assert len(sequences) > 0;

		final List<Chain<? extends T>> nonEmpty = filter(g -> len(g) > 0, sequences);
		if (len(nonEmpty) == 0) {
			return RangedFunction.of(i -> null, 0);
		}
		final int[] cumulatedLens = accumulate((a, b) -> a + b, intMap(g -> len(g), nonEmpty));
		final int total = tail(cumulatedLens);

		return RangedFunction.of(i -> {
			assert 0 <= i && i < total;
			final int j = Arrays.binarySearch(cumulatedLens, i);
			if (j >= 0) {
				return nonEmpty.get(j + 1).getDescriptor().apply(0);
			}
			final int ip = -j - 1;
			final int y = ip == 0 ? 0 : cumulatedLens[ip - 1];
			return nonEmpty.get(ip).getDescriptor().apply(i - y);
		}, total);
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static RangedIntFunction combine(final IntBinaryOperator f, final IntChain a, final IntChain b)
	{
		final int size = min(len(a), len(b));
		return RangedIntFunction.of(i -> f.applyAsInt(a.elementAt(i), b.elementAt(i)), size);
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static int[] combine(final IntBinaryOperator f, final int[] a, final int[] b)
	{
		return combine(f, asFunction(a), asFunction(b)).toArray();
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static ImmutableIntArray combine(final IntBinaryOperator f, final ImmutableIntArray a, final ImmutableIntArray b)
	{
		return combine(f, asFunction(a), asFunction(b)).toImmutableArray();
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static RangedDoubleFunction combine(final DoubleBinaryOperator f, final DoubleChain a, final DoubleChain b)
	{
		final int size = min(len(a), len(b));
		return RangedDoubleFunction.of(i -> f.applyAsDouble(a.elementAt(i), b.elementAt(i)), size);
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static double[] combine(final DoubleBinaryOperator f, final double[] a, final double[] b)
	{
		return combine(f, asFunction(a), asFunction(b)).toArray();
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static ImmutableDoubleArray combine(final DoubleBinaryOperator f, final ImmutableDoubleArray a, final ImmutableDoubleArray b)
	{
		return combine(f, asFunction(a), asFunction(b)).toImmutableArray();
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static RangedLongFunction combine(final LongBinaryOperator f, final LongChain a, final LongChain b)
	{
		final int size = min(len(a), len(b));
		return RangedLongFunction.of(i -> f.applyAsLong(a.elementAt(i), b.elementAt(i)), size);
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static long[] combine(final LongBinaryOperator f, final long[] a, final long[] b)
	{
		return combine(f, asFunction(a), asFunction(b)).toArray();
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static ImmutableLongArray combine(final LongBinaryOperator f, final ImmutableLongArray a, final ImmutableLongArray b)
	{
		return combine(f, asFunction(a), asFunction(b)).toImmutableArray();
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static <T, U, R> RangedFunction<R> combine(final BiFunction<T, U, R> f, final Chain<T> a, final Chain<U> b)
	{
		final int size = min(len(a), len(b));
		return RangedFunction.of(i -> f.apply(a.elementAt(i), b.elementAt(i)), size);
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static <T, U, R> ImmutableList<R> combine(final BiFunction<T, U, R> f, final List<T> a, final List<U> b)
	{
		return combine(f, asFunction(a), asFunction(b)).toList();
	}

	/**
	 * Combines two sequences into a sequence of the same type via the provided binary operator. It can be thought of as zipping
	 * the sequences together into one which has the same length as the shortest parameter sequence (remaining values are discarded
	 * from the longer sequence).
	 *
	 * @param f - the binary operator describing the method of combination
	 * @param a - the first parameter sequence
	 * @param b - the second parameter sequence
	 * @return the combined sequence.
	 */
	public static <T, U, R> ImmutableList<R> combine(final BiFunction<T, U, R> f, final T[] a, final U[] b)
	{
		return combine(f, asFunction(a), asFunction(b)).toList();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static double dotProduct(final DoubleChain a, final DoubleChain b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsDouble();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static double dotProduct(final ImmutableDoubleArray a, final ImmutableDoubleArray b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsDouble();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static double dotProduct(final double[] a, final double[] b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsDouble();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static int dotProduct(final IntChain a, final IntChain b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsInt();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static int dotProduct(final ImmutableIntArray a, final ImmutableIntArray b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsInt();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static int dotProduct(final int[] a, final int[] b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsInt();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static long dotProduct(final LongChain a, final LongChain b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsLong();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static long dotProduct(final ImmutableLongArray a, final ImmutableLongArray b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsLong();
	}

	/**
	 * Convenience method for computing the dot product of two primitive number sequences. It is is fail fast with
	 * regards to numerical overflow (in case of long and int). An assertion statement checks that the sequences
	 * are of the same length.
	 *
	 * @param a - The first sequence of primitives
	 * @param b - The second sequence of primitives
	 * @return the dot product of the two sequences
	 */
	public static long dotProduct(final long[] a, final long[] b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsLong();
	}

	public static void main(final String[] args)
	{
		final double[] xs = { 1, 5, 6, 8 }, ys = { 10, 3, 4, 5, 62, 54 }, zs = { 23, 867, 2.1 };
		System.out.println(str(join(xs, ys, zs)));

		final List<String> xs2 = ImmutableList.of("1", "2", "3"), ys2 = ImmutableList.of("4", "5", "6");
		System.out.println(str(join(xs2, ys2, xs2)));

		final ImmutableDoubleArray vals1 = ImmutableDoubleArray.of(1.5, 6.3), vals2 = ImmutableDoubleArray.of(17, 9.43),
				vals3 = ImmutableDoubleArray.of(2.878, 7.77);
		System.out.println(str(join(vals1, vals2)));
		System.out.println(str(join(vals2, vals3, vals1, reverse(vals3))));

		final double d = Double.MAX_VALUE;
		System.out.println(d + 1);
	}
}
