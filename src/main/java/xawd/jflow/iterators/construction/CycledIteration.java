/**
 *
 */
package xawd.jflow.iterators.construction;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.CyclicFlow;
import xawd.jflow.iterators.iterables.DoubleFlowIterable;
import xawd.jflow.iterators.iterables.IntFlowIterable;
import xawd.jflow.iterators.iterables.LongFlowIterable;

/**
 * @author t
 *
 */
public class CycledIteration
{
	public static <T> Flow<T> of(final Iterable<? extends T> source)
	{
		return new CyclicFlow.OfObject<>(source::iterator);
	}

	public static IntFlow of(final IntFlowIterable source)
	{
		return new CyclicFlow.OfInt(source::iterator);
	}

	public static IntFlow of(final int... xs)
	{
		return new CyclicFlow.OfInt(() -> Iterate.over(xs));
	}

	public static DoubleFlow of(final DoubleFlowIterable source)
	{
		return new CyclicFlow.OfDouble(source::iterator);
	}

	public static DoubleFlow of(final double... xs)
	{
		return new CyclicFlow.OfDouble(() -> Iterate.over(xs));
	}

	public static LongFlow of(final LongFlowIterable source)
	{
		return new CyclicFlow.OfLong(source::iterator);
	}

	public static LongFlow of(final long... xs)
	{
		return new CyclicFlow.OfLong(() -> Iterate.over(xs));
	}
}
