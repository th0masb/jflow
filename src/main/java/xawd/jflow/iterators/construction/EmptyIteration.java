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
 * Static factory methods for creating empty Flow instances.
 *
 * @author ThomasB
 */
public final class EmptyIteration
{
	private EmptyIteration()
	{
	}

	/**
	 * Creates an empty Flow of the required type.
	 *
	 * @param <E>
	 *            The element type of the Flow (it will be inferred from the context
	 *            of the method call).
	 * @return An empty Flow.
	 */
	public static <E> Flow<E> ofObjects()
	{
		return new EmptyFlow.OfObjects<>();
	}

	/**
	 * Creates an empty DoubleFlow.
	 *
	 * @return An empty DoubleFlow.
	 */
	public static DoubleFlow ofDoubles()
	{
		return new EmptyFlow.OfDoubles();
	}

	/**
	 * Creates an empty LongFlow.
	 *
	 * @return An empty LongFlow.
	 */
	public static LongFlow ofLongs()
	{
		return new EmptyFlow.OfLongs();
	}

	/**
	 * Creates an empty IntFlow.
	 *
	 * @return An empty IntFlow.
	 */
	public static IntFlow ofInts()
	{
		return new EmptyFlow.OfInts();
	}
}
