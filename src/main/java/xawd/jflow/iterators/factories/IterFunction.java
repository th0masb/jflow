/**
 *
 */
package xawd.jflow.iterators.factories;

import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.iterators.impl.FlowFromFunction;

/**
 * Factory for building both finite and infinite Flows from functions.
 *
 * @author ThomasB
 */
public final class IterFunction
{
	private IterFunction()
	{
	}

	/**
	 * Build an infinite Flow from a function which accepts a positive integer
	 * argument representing a sequence index.
	 *
	 * @param <E>
	 *            The target type of the indexing function.
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @return An infinite Flow built from apply the indexing function to each
	 *         natural number in turn.
	 */
	public static <E> Flow<E> objectsFrom(final IntFunction<E> indexingFunction)
	{
		return new FlowFromFunction.OfObject<>(indexingFunction);
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
	 *            The upper bound (exclusive) of the index range. If the bound is
	 *            negative it will be ignored and an infinite iteration returned.
	 * @return A Flow built from apply the indexing function to a bounded range of
	 *         natural numbers.
	 */
	public static <E> Flow<E> objectsFrom(final IntFunction<E> indexingFunction, final int indexBound)
	{
		return new FlowFromFunction.OfObject<>(indexingFunction, indexBound);
	}

	/**
	 * Build an infinite LongFlow from a function which accepts a positive integer
	 * argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @return An infinite LongFlow built from apply the indexing function to each
	 *         natural number in turn.
	 */
	public static LongFlow longsFrom(final IntToLongFunction indexingFunction)
	{
		return new FlowFromFunction.OfLong(indexingFunction, -1);
	}

	/**
	 * Build a finite length LongFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @param indexBound
	 *            The upper bound (exclusive) of the index range. If the bound is
	 *            negative it will be ignored and an infinite iteration returned.
	 * @return A LongFlow built from apply the indexing function to a bounded range
	 *         of natural numbers.
	 */
	public static LongFlow longsFrom(final IntToLongFunction indexingFunction, final int indexBound)
	{
		return new FlowFromFunction.OfLong(indexingFunction, indexBound);
	}

	/**
	 * Build an infinite IntFlow from a function which accepts a positive integer
	 * argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @return An infinite IntFlow built from apply the indexing function to each
	 *         natural number in turn.
	 */
	public static IntFlow intsFrom(final IntUnaryOperator indexingFunction)
	{
		return new FlowFromFunction.OfInt(indexingFunction, -1);
	}

	/**
	 * Build a finite length IntFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @param indexBound
	 *            The upper bound (exclusive) of the index range. If the bound is
	 *            negative it will be ignored and an infinite iteration returned.
	 * @return A IntFlow built from apply the indexing function to a bounded range
	 *         of natural numbers.
	 */
	public static IntFlow intsFrom(final IntUnaryOperator indexingFunction, final int indexBound)
	{
		return new FlowFromFunction.OfInt(indexingFunction, indexBound);
	}

	/**
	 * Build an infinite DoubleFlow from a function which accepts a positive integer
	 * argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @return An infinite DoubleFlow built from apply the indexing function to each
	 *         natural number in turn.
	 */
	public static DoubleFlow doublesFrom(final IntToDoubleFunction indexingFunction)
	{
		return new FlowFromFunction.OfDouble(indexingFunction, -1);
	}

	/**
	 * Build a finite length DoubleFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction
	 *            A function whose domain is the natural numbers.
	 * @param indexBound
	 *            The upper bound (exclusive) of the index range. If the bound is
	 *            negative it will be ignored and an infinite iteration returned.
	 * @return A DoubleFlow built from apply the indexing function to a bounded
	 *         range
	 */
	public static DoubleFlow doublesFrom(final IntToDoubleFunction indexingFunction, final int indexBound)
	{
		return new FlowFromFunction.OfDouble(indexingFunction, indexBound);
	}
}
