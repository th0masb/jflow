/**
 *
 */
package xawd.jflow.utilities;

import static xawd.jflow.utilities.CollectionUtil.sizeOf;

import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

/**
 * Static methods for combining primitive arrays with functions.
 * 
 * @author ThomasB
 * @since 14 Feb 2018
 */
public final class CombineUtil
{
	private CombineUtil()
	{
	}

	/**
	 * Combines two arrays into a single array via a binary operator.
	 *
	 * @param combiner
	 *            The operator describing how elements are combined.
	 * @param xs
	 *            The first array.
	 * @param ys
	 *            The second array.
	 * @return Let {@code f} denote the combiner operator. Then we return an array
	 *         {@code zs} defined by:
	 *         <ul>
	 *         <li>{@code zs[i] = f(xs[i], ys[i])}</li>
	 *         <li>{@code sizeOf(zs) = min(sizeOf(xs), sizeOf(ys))}</li>
	 *         </ul>
	 */
	public static int[] combine(final IntBinaryOperator combiner, final int[] xs, final int[] ys)
	{
		final int newLength = Math.min(sizeOf(xs), sizeOf(ys));
		final int[] combined = new int[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = combiner.applyAsInt(xs[i], ys[i]);
		}
		return combined;
	}

	/**
	 * Combines two arrays into a single array via a binary operator.
	 *
	 * @param combiner
	 *            The operator describing how elements are combined.
	 * @param xs
	 *            The first array.
	 * @param ys
	 *            The second array.
	 * @return Let {@code f} denote the combiner operator. Then we return an array
	 *         {@code zs} defined by:
	 *         <ul>
	 *         <li>{@code zs[i] = f(xs[i], ys[i])}</li>
	 *         <li>{@code sizeOf(zs) = min(sizeOf(xs), sizeOf(ys))}</li>
	 *         </ul>
	 */
	public static double[] combine(final DoubleBinaryOperator combiner, final double[] xs, final double[] ys)
	{
		final int newLength = Math.min(sizeOf(xs), sizeOf(ys));
		final double[] combined = new double[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = combiner.applyAsDouble(xs[i], ys[i]);
		}
		return combined;
	}

	/**
	 * Combines two arrays into a single array via a binary operator.
	 *
	 * @param combiner
	 *            The operator describing how elements are combined.
	 * @param xs
	 *            The first array.
	 * @param ys
	 *            The second array.
	 * @return Let {@code f} denote the combiner operator. Then we return an array
	 *         {@code zs} defined by:
	 *         <ul>
	 *         <li>{@code zs[i] = f(xs[i], ys[i])}</li>
	 *         <li>{@code sizeOf(zs) = min(sizeOf(xs), sizeOf(ys))}</li>
	 *         </ul>
	 */
	public static long[] combine(final LongBinaryOperator combiner, final long[] xs, final long[] ys)
	{
		final int newLength = Math.min(sizeOf(xs), sizeOf(ys));
		final long[] combined = new long[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = combiner.applyAsLong(xs[i], ys[i]);
		}
		return combined;
	}
}
