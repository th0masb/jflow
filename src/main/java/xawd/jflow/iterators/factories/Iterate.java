/**
 *
 */
package xawd.jflow.iterators.factories;

import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.EmptyFlow;
import xawd.jflow.iterators.impl.FlowFromFunction;
import xawd.jflow.iterators.impl.FlowFromIterator;
import xawd.jflow.iterators.impl.FlowFromValues;
import xawd.jflow.iterators.impl.ReverseFlowFromValues;
import xawd.jflow.utilities.Exceptions;

/**
 * Static methods for building finite Flow instances.
 *
 * @author ThomasB
 */
public final class Iterate
{
	private Iterate()
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
	public static <E> Flow<E> empty()
	{
		return new EmptyFlow.OfObjects<>();
	}

	/**
	 * Construct a Flow which wraps an iterator provided from an existing
	 * Collection.
	 *
	 * @param <E>
	 *            The upper bound on the source Collection element type.
	 * @param src
	 *            Some Collection of elements.
	 * @return A Flow instance wrapping an iterator constructed from the source
	 *         sequence.
	 */
	public static <E> Flow<E> over(Collection<? extends E> src)
	{
		return new FlowFromIterator.OfObject<>(src.iterator(), src.size());
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
	public static <E> Flow<E> over(E... elements)
	{
		return new FlowFromValues.OfObject<>(elements);
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
	public static <E> Flow<E> overReversed(List<? extends E> source)
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
	public static <E> Flow<E> overReversed(E... elements)
	{
		return new ReverseFlowFromValues.OfObject<>(elements);
	}
	
	/**
	 * Build a finite length Flow from a function which accepts a positive integer
	 * argument representing a sequence index.
	 *
	 * @param <E>
	 *            The target type of the indexing function.
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @param indexBound
	 *            The upper bound (exclusive) of the index range. It must be non-negative
	 *            otherwise an exception will be thrown.
	 * @return A Flow built from apply the indexing function to a bounded range of
	 *         natural numbers.
	 */
	public static <E> Flow<E> byIndexing(IntFunction<E> indexingFunction, int indexBound)
	{
		Exceptions.requireArgument(indexBound >= 0);
		return new FlowFromFunction.OfObject<>(indexingFunction, indexBound);
	}
	
	// Ints
	
	/**
	 * Creates an empty IntFlow.
	 *
	 * @return An empty IntFlow.
	 */
	public static IntFlow emptyInts()
	{
		return EmptyFlow.OF_INTS;
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
	 * Construct a IntFlow reverse iterating over varargs elements.
	 *
	 * @param elements
	 *            The elements to be reversed over.
	 * @return A IntFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static IntFlow overReversedInts(int... elements)
	{
		return new ReverseFlowFromValues.OfInt(elements);
	}
	

	/**
	 * Build a finite length IntFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @param indexBound
	 *            The upper bound (exclusive) of the index range. It must be non-negative
	 *            otherwise an exception will be thrown.
	 * @return A IntFlow built from apply the indexing function to a bounded range
	 *         of natural numbers.
	 */
	public static IntFlow intsByIndexing(IntUnaryOperator indexingFunction, int indexBound)
	{
		Exceptions.requireArgument(indexBound >= 0);
		return new FlowFromFunction.OfInt(indexingFunction, indexBound);
	}
	
	// Doubles
	
	/**
	 * Creates an empty DoubleFlow.
	 *
	 * @return An empty DoubleFlow.
	 */
	public static DoubleFlow emptyDoubles()
	{
		return EmptyFlow.OF_DOUBLES;
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
	 * Construct a DoubleFlow reverse iterating over varargs elements.
	 *
	 * @param elements
	 *            The elements to be reversed over.
	 * @return A DoubleFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static DoubleFlow overReversedDoubles(double... elements)
	{
		return new ReverseFlowFromValues.OfDouble(elements);
	}
	
	/**
	 * Build a finite length DoubleFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @param indexBound
	 *            The upper bound (exclusive) of the index range. It must be non-negative
	 *            otherwise an exception will be thrown.
	 * @return A DoubleFlow built from apply the indexing function to a bounded
	 *         range
	 */
	public static DoubleFlow doublesByIndexing(IntToDoubleFunction indexingFunction, int indexBound)
	{
		Exceptions.requireArgument(indexBound >= 0);
		return new FlowFromFunction.OfDouble(indexingFunction, indexBound);
	}
	
	// Longs
	
	/**
	 * Creates an empty LongFlow.
	 *
	 * @return An empty LongFlow.
	 */
	public static LongFlow emptyLongs()
	{
		return EmptyFlow.OF_LONGS;
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
	 * Construct a LongFlow reverse iterating over varargs elements.
	 *
	 * @param elements
	 *            The elements to be reversed over.
	 * @return A LongFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static LongFlow overReversedLongs(long... elements)
	{
		return new ReverseFlowFromValues.OfLong(elements);
	}


	/**
	 * Build a finite length LongFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @param indexBound
	 *            The upper bound (exclusive) of the index range. It must be non-negative
	 *            otherwise an exception will be thrown.
	 * @return A LongFlow built from apply the indexing function to a bounded range
	 *         of natural numbers.
	 */
	public static LongFlow longsByIndexing(IntToLongFunction indexingFunction, int indexBound)
	{
		Exceptions.requireArgument(indexBound >= 0);
		return new FlowFromFunction.OfLong(indexingFunction, indexBound);
	}
}
