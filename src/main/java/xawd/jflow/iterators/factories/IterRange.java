package xawd.jflow.iterators.factories;

import static java.lang.Math.abs;
import static xawd.jflow.utilities.PrimitiveUtil.signum;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.IntFlow;

/**
 * Factory for building primitive number range Flows.
 *
 * @author t
 */
public final class IterRange
{
	private IterRange()
	{
	}

	/**
	 * Builds an integer range between 0 and some provided upper bound.
	 *
	 * @param upperBound
	 *            The upper bound (exclusive) on the iteration interval.
	 * @return Let n be the upper bound. If n is non-positive we return an empty
	 *         iteration otherwise we return an ordered iteration over the integers
	 *         contained in the half open interval:
	 *
	 *         <pre>
	 * 				[0,n)
	 *         </pre>
	 */
	public static IntFlow to(final int upperBound)
	{
		return upperBound > 0 ? IterFunction.intsFrom(i -> i, upperBound) : EmptyIteration.ofInts();
	}

	/**
	 * Builds an integer range between provided lower and upper bounds.
	 *
	 * @param low
	 *            The lower bound (inclusive) on the iteration interval.
	 *
	 * @param high
	 *            The upper bound (exclusive) on the iteration interval.
	 * @return Let m be the lower bound and n be the upper bound. If (n - m) is
	 *         non-positive we return an empty iteration otherwise we return an
	 *         ordered iteration over the integers contained in the half open
	 *         interval:
	 *
	 *         <pre>
	 * 				[m,n)
	 *         </pre>
	 */
	public static IntFlow between(final int low, final int high)
	{
		return high > low ? IterFunction.intsFrom(i -> i + low, high - low) : EmptyIteration.ofInts();
	}

	/**
	 * Builds an integer range between provided lower and upper bounds with a given
	 * step size.
	 *
	 * @param start
	 *            The start bound (inclusive) on the iteration interval.
	 *
	 * @param end
	 *            The end bound (exclusive) on the iteration interval.
	 *
	 * @param step
	 *            The difference between consecutive integers in the iteration.
	 * @return Let m be the start bound and n be the end bound. If sign(n - m) is
	 *         not equal to sign(step) we return an empty iteration otherwise we
	 *         return an ordered iteration starting at m over the integers contained
	 *         in the half open interval [m, n) whose consecutive difference is
	 *         equal to the step size.
	 */
	public static IntFlow between(final int start, final int end, final int step)
	{
		final int length = end - start;
		final int elementCount = (int) Math.ceil(abs((double) length / step));
		return signum(step) == signum(length) ? IterFunction.intsFrom(i -> start + i * step, elementCount)
				: EmptyIteration.ofInts();
	}

	/**
	 * Builds a Flow iterating over the boundary points of a partitioned interval.
	 *
	 * @param start
	 *            The start of the interval to be partitioned.
	 * @param end
	 *            The end of the interval to be partitioned.
	 * @param nSubIntervals
	 *            The number of equal length subintervals to partition the interval
	 *            into.
	 * @return Let m be the required number of subintervals and J the be interval.
	 *         Then partition J into m non-overlapping same length sub intervals say
	 *         {J_m}. Take the unique boundary points of all intervals in the family
	 *         {J_m} and return an iteration over them ordered on proximity to the
	 *         'start' of the interval J.
	 */
	public static DoubleFlow partition(final double start, final double end, final int nSubIntervals)
	{
		if (nSubIntervals < 1 || Math.abs(end - start) < 0.00001) {
			throw new IllegalArgumentException();
		}
		final double step = (end - start) / nSubIntervals;
		return to(nSubIntervals + 1).mapToDouble(i -> start + i * step);
	}
}
