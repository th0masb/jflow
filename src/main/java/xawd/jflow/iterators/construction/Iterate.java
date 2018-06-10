/**
 *
 */
package xawd.jflow.iterators.construction;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.FlowFromIterator;
import xawd.jflow.iterators.impl.FlowFromValues;

/**
 * Static constructors for creating Flow instances from groupings of values.
 *
 * @author ThomasB
 */
public final class Iterate
{
	private Iterate()
	{
	}

	/**
	 * Construct a Flow which wraps an iterator provided from an existing iterable
	 * object.
	 *
	 * @param <E>
	 *            The upper bound on the source iterable element type.
	 * @param source
	 *            An object which can construct an iterator over it's elements.
	 * @return A Flow instance wrapping an iterator constructed from the source
	 *         sequence.
	 */
	public static <E> Flow<E> over(final Iterable<? extends E> source)
	{
		return new FlowFromIterator.OfObject<>(source.iterator());
	}

	/**
	 * Construct a Flow iterating over varargs elements.
	 *
	 * @param <E>
	 *            The least upper bound on the types of the passed elements.
	 * @param elements
	 *            The elements to be iterated over.
	 * @return A Flow iterating over the given elements.
	 */
	@SafeVarargs
	public static <E> Flow<E> over(final E... elements)
	{
		return new FlowFromValues.OfObject<>(elements);
	}

	/**
	 * Construct an IntFlow iterating over varargs primitive integers.
	 *
	 * @param integers
	 *            The integers to be iterated over.
	 * @return An IntFlow iterating over the given integers.
	 */
	public static IntFlow over(int... integers)
	{
		return new FlowFromValues.OfInt(integers);
	}

	/**
	 * Construct an DoubleFlow iterating over varargs primitive doubles.
	 *
	 * @param integers
	 *            The doubles to be iterated over.
	 * @return An DoubleFlow iterating over the given doubles.
	 */
	public static DoubleFlow over(double... is)
	{
		return new FlowFromValues.OfDouble(is);
	}

	/**
	 * Construct an LongFlow iterating over varargs primitive longs.
	 *
	 * @param integers
	 *            The longs to be iterated over.
	 * @return An LongFlow iterating over the given longs.
	 */
	public static LongFlow over(long... is)
	{
		return new FlowFromValues.OfLong(is);
	}
}
