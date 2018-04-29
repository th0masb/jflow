/**
 *
 */
package xawd.jflow.construction;

import xawd.jflow.DoubleFlow;
import xawd.jflow.Flow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;
import xawd.jflow.impl.CyclicFlow;
import xawd.jflow.iterables.DoubleFlowIterable;
import xawd.jflow.iterables.IntFlowIterable;
import xawd.jflow.iterables.LongFlowIterable;

/**
 * @author t
 *
 */
public class IterCycle
{
	public static <T> Flow<T> of(Iterable<T> source)
	{
		return new CyclicFlow.OfObject<>(source::iterator);
	}

	public static IntFlow of(IntFlowIterable source)
	{
		return new CyclicFlow.OfInt(source::iter);
	}

	public static IntFlow of(int... xs)
	{
		return new CyclicFlow.OfInt(() -> Iter.of(xs));
	}

	public static DoubleFlow of(DoubleFlowIterable source)
	{
		return new CyclicFlow.OfDouble(source::iter);
	}

	public static DoubleFlow of(double... xs)
	{
		return new CyclicFlow.OfDouble(() -> Iter.of(xs));
	}

	public static LongFlow of(LongFlowIterable source)
	{
		return new CyclicFlow.OfLong(source::iter);
	}

	public static LongFlow of(long... xs)
	{
		return new CyclicFlow.OfLong(() -> Iter.of(xs));
	}
}
