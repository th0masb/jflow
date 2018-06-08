/**
 *
 */
package xawd.jflow.utilities;


import static xawd.jflow.utilities.CollectionUtil.sizeOf;

import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

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
		final int newLength = Math.min(sizeOf(a), sizeOf(b));
		final int[] combined = new int[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = f.applyAsInt(a[i], b[i]);
		}
		return combined;
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
		final int newLength = Math.min(sizeOf(a), sizeOf(b));
		final double[] combined = new double[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = f.applyAsDouble(a[i], b[i]);
		}
		return combined;
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
		final int newLength = Math.min(sizeOf(a), sizeOf(b));
		final long[] combined = new long[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = f.applyAsLong(a[i], b[i]);
		}
		return combined;
	}
}
