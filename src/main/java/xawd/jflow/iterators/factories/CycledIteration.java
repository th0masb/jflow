/**
 *
 */
package xawd.jflow.iterators.factories;

import java.util.Arrays;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.CyclicFlow;
import xawd.jflow.iterators.iterables.DoubleFlowIterable;
import xawd.jflow.iterators.iterables.IntFlowIterable;
import xawd.jflow.iterators.iterables.LongFlowIterable;

/**
 * Factory for building infinite cycling Flows from groups of values.
 *
 * @author ThomasB
 */
public class CycledIteration
{
	private CycledIteration()
	{
	}

	/**
	 * Creates an infinite, continuously looping Flow from an iterable sequence.
	 *
	 * @param <E>
	 *            The upper bound on the type of elements in the source.
	 *
	 * @param source
	 *            The sequence which will be continuously looped over.
	 * @return A Flow which continuously loops through the elements of the source
	 *         sequence.
	 */
	public static <E> Flow<E> of(final Iterable<? extends E> source)
	{
		return new CyclicFlow.OfObject<>(source::iterator);
	}

	/**
	 * Creates an infinite, continuously looping Flow from a varargs array source.
	 *
	 * @param <E>
	 *            The type of the element which will be repeated.
	 *
	 * @param elements
	 *            The references which will be cycled through.
	 * @return A Flow which indefinitely cycles through the source elements.
	 */
	@SafeVarargs
	public static <E> Flow<E> of(final E... elements)
	{
		return of(Arrays.asList(elements));
	}

	/**
	 * Creates an infinite, continuously looping IntFlow from an iterable sequence
	 * of ints.
	 *
	 * @param source
	 *            The sequence which will be continuously looped over.
	 * @return An IntFlow which continuously loops through the elements of the
	 *         source sequence.
	 */
	public static IntFlow ofInts(final IntFlowIterable source)
	{
		return new CyclicFlow.OfInt(source::iterator);
	}

	/**
	 * Creates an infinite, continuously looping IntFlow from an int array.
	 *
	 * @param source
	 *            The array which will be continuously looped over.
	 * @return An IntFlow which continuously loops through the elements of the
	 *         source array.
	 */
	public static IntFlow ofInts(final int... source)
	{
		return new CyclicFlow.OfInt(() -> Iterate.overInts(source));
	}

	/**
	 * Creates an infinite, continuously looping DoubleFlow from an iterable
	 * sequence of doubles.
	 *
	 * @param source
	 *            The sequence which will be continuously looped over.
	 * @return An DoubleFlow which continuously loops through the elements of the
	 *         source sequence.
	 */
	public static DoubleFlow ofDoubles(final DoubleFlowIterable source)
	{
		return new CyclicFlow.OfDouble(source::iterator);
	}

	/**
	 * Creates an infinite, continuously looping DoubleFlow from an double array.
	 *
	 * @param source
	 *            The array which will be continuously looped over.
	 * @return An DoubleFlow which continuously loops through the elements of the
	 *         source array.
	 */
	public static DoubleFlow ofDoubles(final double... source)
	{
		return new CyclicFlow.OfDouble(() -> Iterate.overDoubles(source));
	}

	/**
	 * Creates an infinite, continuously looping LongFlow from an iterable sequence
	 * of longs.
	 *
	 * @param source
	 *            The sequence which will be continuously looped over.
	 * @return An LongFlow which continuously loops through the elements of the
	 *         source sequence.
	 */
	public static LongFlow ofLongs(final LongFlowIterable source)
	{
		return new CyclicFlow.OfLong(source::iterator);
	}

	/**
	 * Creates an infinite, continuously looping LongFlow from an long array.
	 *
	 * @param source
	 *            The array which will be continuously looped over.
	 * @return An LongFlow which continuously loops through the elements of the
	 *         source array.
	 */
	public static LongFlow ofLongs(final long... source)
	{
		return new CyclicFlow.OfLong(() -> Iterate.overLongs(source));
	}
}
