/**
 *
 */
package xawd.jflow.iterators.construction;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.EmptyFlow;

/**
 * @author ThomasB
 *
 */
public final class EmptyIteration
{
	public EmptyIteration() {}

	public static <E> Flow<E> ofObjects()
	{
		return new EmptyFlow.OfObjects<>();
	}

	public static DoubleFlow ofDoubles()
	{
		return new EmptyFlow.OfDoubles();
	}

	public static LongFlow ofLongs()
	{
		return new EmptyFlow.OfLongs();
	}

	public static IntFlow ofInts()
	{
		return new EmptyFlow.OfInts();
	}
}
