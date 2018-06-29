/**
 *
 */
package xawd.jflow.iterators.factories;

import java.util.Collection;
import java.util.List;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.FlowFromIterator;
import xawd.jflow.iterators.impl.FlowFromValues;
import xawd.jflow.iterators.impl.ReverseFlowFromValues;

/**
 * Factory for building Flow instances from groups of values.
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
	 * Construct a Flow which wraps an iterator provided from an existing
	 * Collection.
	 *
	 * @param <E>
	 *            The upper bound on the source Collection element type.
	 * @param source
	 *            Some Collection of elements.
	 * @return A Flow instance wrapping an iterator constructed from the source
	 *         sequence.
	 */
	public static <E> Flow<E> over(final Collection<? extends E> source)
	{
		return new FlowFromIterator.OfObject<>(source.iterator(), source.size());
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
	public static IntFlow overInts(int... integers)
	{
		return new FlowFromValues.OfInt(integers);
	}

	/**
	 * Construct an DoubleFlow iterating over varargs primitive doubles.
	 *
	 * @param doubles
	 *            The doubles to be iterated over.
	 * @return An DoubleFlow iterating over the given doubles.
	 */
	public static DoubleFlow overDoubles(double... doubles)
	{
		return new FlowFromValues.OfDouble(doubles);
	}

	/**
	 * Construct an LongFlow iterating over varargs primitive longs.
	 *
	 * @param longs
	 *            The longs to be iterated over.
	 * @return An LongFlow iterating over the given longs.
	 */
	public static LongFlow overLongs(long... longs)
	{
		return new FlowFromValues.OfLong(longs);
	}

	/**
	 * Build a Flow reverse iterating over a List.
	 *
	 * @param <E>
	 *            The upper type bound on the elements in the source.
	 * @param source
	 *            The source List
	 * @return A Flow reversing over the source starting with the last element.
	 */
	public static <E> Flow<E> reverseOver(List<? extends E> source)
	{
		return new ReverseFlowFromValues.OfObject<>(source);
	}

	/**
	 * Construct a Flow reverse iterating over varargs elements.
	 *
	 * @param <E>
	 *            The least upper bound on the types of the passed elements.
	 * @param elements
	 *            The elements to be reversed over.
	 * @return A Flow reversing over the given elements starting with the last
	 *         element.
	 */
	@SafeVarargs
	public static <E> Flow<E> reverseOver(final E... elements)
	{
		return new ReverseFlowFromValues.OfObject<>(elements);
	}

	/**
	 * Construct a LongFlow reverse iterating over varargs elements.
	 *
	 * @param elements
	 *            The elements to be reversed over.
	 * @return A LongFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static LongFlow reverseOverLongs(final long... elements)
	{
		return new ReverseFlowFromValues.OfLong(elements);
	}

	/**
	 * Construct a DoubleFlow reverse iterating over varargs elements.
	 *
	 * @param elements
	 *            The elements to be reversed over.
	 * @return A DoubleFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static DoubleFlow reverseOverDoubles(final double... elements)
	{
		return new ReverseFlowFromValues.OfDouble(elements);
	}

	/**
	 * Construct a IntFlow reverse iterating over varargs elements.
	 *
	 * @param elements
	 *            The elements to be reversed over.
	 * @return A IntFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static IntFlow reverseOverInts(final int... elements)
	{
		return new ReverseFlowFromValues.OfInt(elements);
	}
}
