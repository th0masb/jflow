/**
 *
 */
package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.len;
import static xawd.jchain.chains.utilities.PrimitiveUtil.sum;

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
		throw new RuntimeException();
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
		throw new RuntimeException();
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
		throw new RuntimeException();
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
	public static long dotProduct(final long[] a, final long[] b)
	{
		assert len(a) > 0 && len(a) == len(b);
		return sum(combine((x, y) -> x * y, a, b)).getAsLong();
	}

}
