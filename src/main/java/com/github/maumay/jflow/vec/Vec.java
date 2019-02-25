/**
 *
 */
package com.github.maumay.jflow.vec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gihub.maumay.jflow.iterators.misc.Pair;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.iterables.EnhancedIterable;

/**
 * <p>
 * A Vec (vector) is an <b>immutable</b> wrapper around an object array which
 * provides a myriad of higher order methods for operating on the elements using
 * enhanced sequential iterators (see {@link EnhancedIterator}). The
 * immutability guarantee means we can also construct streams which are well
 * suited for parallelising operations. Null values are not permitted to be
 * stored inside this data structure.
 * </p>
 * 
 * @author ThomasB
 */
public interface Vec<E> extends EnhancedIterable<E>, Indexable<E>
{
	/**
	 * @return An iteration of the elements in this vector in reverse order.
	 */
	EnhancedIterator<E> revIter();

	/**
	 * @return A sequential stream over the elements of this vector in order.
	 */
	Stream<E> stream();

	/**
	 * @return A new vector obtains by mapping this vector elementwise by the
	 *         parameter function.
	 */
	<R> Vec<R> map(Function<? super E, ? extends R> mappingFunction);

	/**
	 * @return A flattened vector obtained by concatenating the images of the
	 *         parameter function applied elementwise to this vector.
	 */
	<R> Vec<R> flatMap(Function<? super E, ? extends Iterator<? extends R>> mapping);

	/**
	 * @return A vector contained the elements of this vector which pass the
	 *         supplied predicate with their relative order preserved.
	 */
	Vec<E> filter(Predicate<? super E> predicate);

	/**
	 * @return A vector with the required parameterisation containing the elements
	 *         of this vector which can be cast to the given type with their
	 *         relative order preserved.
	 */
	<R> Vec<R> cast(Class<R> klass);

	/**
	 * @return A vector containing the elements of this vector with order retained
	 *         followed by the elements of the parameter iterable whose order is
	 *         defined by the iterator it produces.
	 */
	Vec<E> append(Iterable<? extends E> other);

	/**
	 * @return A vector containing the elements of this vector with order preserved
	 *         followed by the parameter element.
	 */
	Vec<E> append(E other);

	/**
	 * @return A vector containing the elements of the parameter iterable whose
	 *         order is determined by the iterator it produces followed by the
	 *         elements of this vector whose relative order is preserved.
	 */
	Vec<E> insert(Iterable<? extends E> other);

	/**
	 * @return A vector containing the parameter element followed by the elements of
	 *         this vector whose relative order is preserved.
	 */
	Vec<E> insert(E other);

	/**
	 * @return A vector consisting of the first n elements of this vector with their
	 *         relative order retained. If the requested number of elements is
	 *         larger than the size of this vector then we will just return this
	 *         vector, if it is less than zero an exception will be thrown.
	 */
	Vec<E> take(int n);

	/**
	 * @return A vector consisting of the elements taken from the head of this
	 *         vector until an element fails the given predicate. The first failure
	 *         <b>is not</b> included.
	 */
	Vec<E> takeWhile(Predicate<? super E> predicate);

	/**
	 * @return A vector consisting of all but the first n elements of this vector
	 *         with their relative order retained. If the requested number of
	 *         elements is 0 then we just return this vector, if it is less than
	 *         zero an exception will be thrown.
	 */
	Vec<E> drop(int n);

	/**
	 * @return A vector consisting of the elements in this vector which occur after
	 *         the first element which fails the predicate. This first failure
	 *         <b>is</b> included.
	 */
	Vec<E> dropWhile(Predicate<? super E> predicate);

	/**
	 * @return A pair of vectors whose first element is the result of
	 *         {@link Vec#takeWhile(Predicate)} and whose second element is a vector
	 *         of all elements who were not included in the first vector.
	 */
	Pair<Vec<E>, Vec<E>> span(Predicate<? super E> predicate);

	/**
	 * @return A pair of vectors whose first element is all the elements of this
	 *         vector which pass the given predicate, the second is all the
	 *         failures. Relative ordering in these subvectors is preserved.
	 */
	Pair<Vec<E>, Vec<E>> partition(Predicate<? super E> predicate);

	/**
	 * @return A copy of this vector where the elements are ordered according to the
	 *         supplied comparator.
	 */
	Vec<E> sorted(Comparator<? super E> orderingFunction);

	// Default methods

	/**
	 * @param element The instance to check for membership.
	 * @return true if the given element is in this vector, false otherwise.
	 */
	default boolean contains(E element)
	{
		return anyMatch(x -> x.equals(element));
	}

	/**
	 * @return A parallel stream over the elements of this vector.
	 */
	default Stream<E> parstream()
	{
		return stream().parallel();
	}

	/**
	 * Safely attempts to retrieve the element at a given index.
	 * 
	 * @param index the index to search at.
	 * @return an optional wrapping the element if the index is valid, nothing
	 *         otherwise.
	 */
	default Optional<E> getOption(int index)
	{
		return -1 < index && index < size() ? Optional.of(get(index)) : Optional.empty();
	}

	/**
	 * Attempts to get the head (first element) of this vector in an unsafe manner.
	 * If this vector is empty then an exception will be thrown.
	 * 
	 * @return the head of this vector.
	 */
	default E head()
	{
		return get(0);
	}

	/**
	 * Gets the head (first element) of this vector in an safe manner.
	 * 
	 * @return an optional wrapping the head of this vector if is is non-empty,
	 *         nothing otherwise.
	 */
	default Optional<E> headOption()
	{
		return size() > 0 ? Optional.of(head()) : Optional.empty();
	}

	/**
	 * Attempts to get the last element of this vector in an unsafe manner. If this
	 * vector is empty then an exception will be thrown.
	 * 
	 * @return the last element of this vector.
	 */
	default E last()
	{
		return get(size() - 1);
	}

	/**
	 * Attempts to get the last element of this vector in an safe manner.
	 * 
	 * @return the last element of this vector if it is non-empty, nothing
	 *         otherwise.
	 */
	default Optional<E> lastOption()
	{
		return size() > 0 ? Optional.of(last()) : Optional.empty();
	}

	default DoubleVec mapToDouble(ToDoubleFunction<? super E> mappingFunction)
	{
		return DoubleVec.of(mapToDoubleArray(mappingFunction));
	}

	default double[] mapToDoubleArray(ToDoubleFunction<? super E> mappingFunction)
	{
		return iter().mapToDouble(mappingFunction).toArray();
	}

	default int[] mapToIntArray(ToIntFunction<? super E> mappingFunction)
	{
		return iter().mapToInt(mappingFunction).toArray();
	}

	default long[] mapToLongArray(ToLongFunction<? super E> mappingFunction)
	{
		return iter().mapToLong(mappingFunction).toArray();
	}

	// Static factories

	/**
	 * @return An empty vector
	 */
	static <E> Vec<E> empty()
	{
		return new VecImpl<>();
	}

	/**
	 * Note that this method is only designed for varargs. If it is passed an array
	 * it won't make a copy and therefore immutability is compromised, this is the
	 * only way to make a mutable instance of this class and it is <b>not</b>
	 * advised. If a null reference is passed as an argument then an exception will
	 * be thrown.
	 * 
	 * @param elements the elements which will populate the resulting vector.
	 * @return a vector wrapping the arguments.
	 */
	@SafeVarargs
	static <E> Vec<E> of(E... elements)
	{
		return new VecImpl<>(elements);
	}

	/**
	 * Copies the contents of the given collection into a vector, the ordering is
	 * determined by the particular implementation of {@link Collection#iterator()}.
	 * If the passed collection contains a null reference then an exception will be
	 * thrown.
	 * 
	 * @param collection the collection to copy the contents of.
	 * @return a vector containing all the elements contained in the argument.
	 */
	static <E> Vec<E> copy(Collection<? extends E> collection)
	{
		return new VecImpl<>(collection);
	}

	/**
	 * Creates a vector of elements described by the iterator returned by
	 * {@link Iterable#iterator()} applied to the parameter. An exception will be
	 * thrown if the source iterable produces an iterator containing a null
	 * reference.
	 * 
	 * @param iterable the source of the elements which will be stored in the
	 *                 resultant vector.
	 * @return a vector containing all the elements in the iterator produced by the
	 *         source iterable.
	 */
	static <E> Vec<E> copy(Iterable<? extends E> iterable)
	{
		return new VecImpl<>(iterable.iterator());
	}

	/**
	 * Creates a vector of elements from a Stream source. The argument will be
	 * consumed, if the stream produces a null reference then an exception will be
	 * thrown.
	 * 
	 * @param source the source of elements
	 * @return a vector containing all the elements in the source stream.
	 */
	static <E> Vec<E> fromStream(Stream<? extends E> source)
	{
		return copy(source.sequential().collect(Collectors.toCollection(ArrayList::new)));
	}

	/**
	 * Creates a vector of elements from an {@link Iterator} source. The argument
	 * will be consumed, if the stream produces a null reference then an exception
	 * will be thrown.
	 * 
	 * @param source the source of elements
	 * @return a vector containing all the elements in the source iterator.
	 */
	static <E> Vec<E> fromIterator(Iterator<? extends E> source)
	{
		return new VecImpl<>(source);
	}
}
