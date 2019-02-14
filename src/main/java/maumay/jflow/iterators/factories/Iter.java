/**
 *
 */
package maumay.jflow.iterators.factories;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIntIterator;
import maumay.jflow.iterators.EnhancedIterator;
import maumay.jflow.iterators.EnhancedLongIterator;
import maumay.jflow.iterators.impl.EmptyIterator;
import maumay.jflow.iterators.impl.FunctionIterator;
import maumay.jflow.iterators.impl.ReversedSourceIterator;
import maumay.jflow.iterators.impl.SourceIterator;
import maumay.jflow.iterators.impl.WrappingIterator;
import maumay.jflow.iterators.misc.Exceptions;
import maumay.jflow.iterators.misc.Pair;

/**
 * Static methods for building finite Flow instances.
 *
 * @author ThomasB
 */
public final class Iter
{
	private Iter()
	{
	}

	/**
	 * Creates an empty Flow of the required type.
	 *
	 * @param <E> The element type of the Flow (it will be inferred from the context
	 *        of the method call).
	 * @return An empty Flow.
	 */
	public static <E> EnhancedIterator<E> empty()
	{
		return new EmptyIterator.OfObjects<>();
	}

	/**
	 * Construct a Flow which wraps an iterator provided from an existing
	 * Collection.
	 *
	 * @param     <E> The upper bound on the source Collection element type.
	 * @param src Some Collection of elements.
	 * @return A Flow instance wrapping an iterator constructed from the source
	 *         sequence.
	 */
	public static <E> EnhancedIterator<E> over(Collection<? extends E> src)
	{
		return new WrappingIterator.OfObject<>(src.iterator(), src.size());
	}

	/**
	 * Construct a Flow iterating over varargs elements.
	 *
	 * @param          <E> The least upper bound on the types of the passed
	 *                 elements.
	 * @param elements The elements to be iterated over.
	 * @return A Flow iterating over the given elements.
	 */
	@SafeVarargs
	public static <E> EnhancedIterator<E> over(E... elements)
	{
		return new SourceIterator.OfObject<>(elements);
	}

	/**
	 * Construct an Iterator traversing over all values in the given enumeration.
	 */
	public static <E extends Enum<E>> EnhancedIterator<E> over(Class<E> enumclass)
	{
		return Iter.over(enumclass.getEnumConstants());
	}

	/**
	 * Build a Flow reverse iterating over a List.
	 *
	 * @param        <E> The upper type bound on the elements in the source.
	 * @param source The source List
	 * @return A Flow reversing over the source starting with the last element.
	 */
	public static <E> EnhancedIterator<E> reverse(List<? extends E> source)
	{
		return new ReversedSourceIterator.OfObject<>(source);
	}

	/**
	 * Construct a Flow reverse iterating over varargs elements.
	 *
	 * @param          <E> The least upper bound on the types of the passed
	 *                 elements.
	 * @param elements The elements to be reversed over.
	 * @return A Flow reversing over the given elements starting with the last
	 *         element.
	 */
	@SafeVarargs
	public static <E> EnhancedIterator<E> reverse(E... elements)
	{
		return new ReversedSourceIterator.OfObject<>(elements);
	}

	/**
	 * Build a finite length Flow from a function which accepts a positive integer
	 * argument representing a sequence index.
	 *
	 * @param                  <E> The target type of the indexing function.
	 * @param indexingFunction A function whose domain is the natural numbers.
	 * @param indexBound       The upper bound (exclusive) of the index range. It
	 *                         must be non-negative otherwise an exception will be
	 *                         thrown.
	 * @return A Flow built from apply the indexing function to a bounded range of
	 *         natural numbers.
	 */
	public static <E> EnhancedIterator<E> byIndexing(IntFunction<E> indexingFunction,
			int indexBound)
	{
		Exceptions.requireArg(indexBound >= 0);
		return new FunctionIterator.OfObject<>(indexingFunction, indexBound);
	}

	/**
	 * Converts an Optional into an Iterator, if the input is present then returns a
	 * single element Iterator containing that element else an empty Iterator is
	 * returned.
	 */
	public static <E> EnhancedIterator<E> option(Optional<? extends E> src)
	{
		return src.isPresent() ? Iter.over(src.get()) : Iter.empty();
	}

	public static <V> EnhancedIterator<V> values(Map<?, ? extends V> src)
	{
		return Iter.over(src.values());
	}

	public static <K> EnhancedIterator<K> keys(Map<? extends K, ?> src)
	{
		return Iter.over(src.keySet());
	}

	public static <K, V> EnhancedIterator<Pair<K, V>> entries(Map<K, V> src)
	{
		return Iter.over(src.entrySet())
				.map(entry -> Pair.of(entry.getKey(), entry.getValue()));
	}

	// Ints

	/**
	 * Creates an empty IntFlow.
	 *
	 * @return An empty IntFlow.
	 */
	public static EnhancedIntIterator emptyInts()
	{
		return EmptyIterator.OF_INTS;
	}

	/**
	 * Construct an IntFlow iterating over varargs primitive integers.
	 *
	 * @param integers The integers to be iterated over.
	 * @return An IntFlow iterating over the given integers.
	 */
	public static EnhancedIntIterator ints(int... integers)
	{
		return new SourceIterator.OfInt(integers);
	}

	/**
	 * Construct a IntFlow reverse iterating over varargs elements.
	 *
	 * @param elements The elements to be reversed over.
	 * @return A IntFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static EnhancedIntIterator reverseInts(int... elements)
	{
		return new ReversedSourceIterator.OfInt(elements);
	}

	/**
	 * Build a finite length IntFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction A function whose domain is the natural numbers.
	 * @param indexBound       The upper bound (exclusive) of the index range. It
	 *                         must be non-negative otherwise an exception will be
	 *                         thrown.
	 * @return A IntFlow built from apply the indexing function to a bounded range
	 *         of natural numbers.
	 */
	public static EnhancedIntIterator intsByIndexing(IntUnaryOperator indexingFunction,
			int indexBound)
	{
		Exceptions.requireArg(indexBound >= 0);
		return new FunctionIterator.OfInt(indexingFunction, indexBound);
	}

	// Doubles

	/**
	 * Creates an empty DoubleFlow.
	 *
	 * @return An empty DoubleFlow.
	 */
	public static EnhancedDoubleIterator emptyDoubles()
	{
		return EmptyIterator.OF_DOUBLES;
	}

	/**
	 * Construct an DoubleFlow iterating over varargs primitive doubles.
	 *
	 * @param doubles The doubles to be iterated over.
	 * @return An DoubleFlow iterating over the given doubles.
	 */
	public static EnhancedDoubleIterator doubles(double... doubles)
	{
		return new SourceIterator.OfDouble(doubles);
	}

	/**
	 * Construct a DoubleFlow reverse iterating over varargs elements.
	 *
	 * @param elements The elements to be reversed over.
	 * @return A DoubleFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static EnhancedDoubleIterator reverseDoubles(double... elements)
	{
		return new ReversedSourceIterator.OfDouble(elements);
	}

	/**
	 * Build a finite length DoubleFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction A function whose domain is the natural numbers.
	 * @param indexBound       The upper bound (exclusive) of the index range. It
	 *                         must be non-negative otherwise an exception will be
	 *                         thrown.
	 * @return A DoubleFlow built from apply the indexing function to a bounded
	 *         range
	 */
	public static EnhancedDoubleIterator doublesByIndexing(
			IntToDoubleFunction indexingFunction, int indexBound)
	{
		Exceptions.requireArg(indexBound >= 0);
		return new FunctionIterator.OfDouble(indexingFunction, indexBound);
	}

	// Longs

	/**
	 * Creates an empty LongFlow.
	 *
	 * @return An empty LongFlow.
	 */
	public static EnhancedLongIterator emptyLongs()
	{
		return EmptyIterator.OF_LONGS;
	}

	/**
	 * Construct an LongFlow iterating over varargs primitive longs.
	 *
	 * @param longs The longs to be iterated over.
	 * @return An LongFlow iterating over the given longs.
	 */
	public static EnhancedLongIterator longs(long... longs)
	{
		return new SourceIterator.OfLong(longs);
	}

	/**
	 * Construct a LongFlow reverse iterating over varargs elements.
	 *
	 * @param elements The elements to be reversed over.
	 * @return A LongFlow reversing over the given elements starting with the last
	 *         element.
	 */
	public static EnhancedLongIterator reverseLongs(long... elements)
	{
		return new ReversedSourceIterator.OfLong(elements);
	}

	/**
	 * Build a finite length LongFlow from a function which accepts a positive
	 * integer argument representing a sequence index.
	 *
	 * @param indexingFunction A function whose domain is the natural numbers.
	 * @param indexBound       The upper bound (exclusive) of the index range. It
	 *                         must be non-negative otherwise an exception will be
	 *                         thrown.
	 * @return A LongFlow built from apply the indexing function to a bounded range
	 *         of natural numbers.
	 */
	public static EnhancedLongIterator longsByIndexing(IntToLongFunction indexingFunction,
			int indexBound)
	{
		Exceptions.requireArg(indexBound >= 0);
		return new FunctionIterator.OfLong(indexingFunction, indexBound);
	}

	/**
	 * Wraps an existing iterator in a Flow to enable use of all extra
	 * functionality.
	 *
	 * @param src The iterator to wrap.
	 * @return A flow wrapping the provided iterator.
	 */
	public static <E> EnhancedIterator<E> wrap(Iterator<? extends E> src)
	{
		return new WrappingIterator.OfObject<>(src);
	}

	/**
	 * Construct a Flow which wraps an iterator provided from an existing iterable
	 * object.
	 *
	 * @param     <E> The upper bound on the source iterable element type.
	 * @param src An object which can construct an iterator over it's elements.
	 * @return A Flow instance wrapping an iterator constructed from the source
	 *         sequence.
	 */
	public static <E> EnhancedIterator<E> wrap(Iterable<? extends E> src)
	{
		return wrap(src.iterator());
	}
}
