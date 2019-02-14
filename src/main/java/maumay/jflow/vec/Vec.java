/**
 *
 */
package maumay.jflow.vec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import maumay.jflow.iterators.EnhancedIterator;
import maumay.jflow.iterators.iterables.EnhancedIterable;
import maumay.jflow.iterators.misc.Pair;

/**
 * <p>
 * A sequence is an <b>immutable</b> alternative to a {@link List} which
 * provides a myriad of higher order methods for operating on the elements in
 * the style of {@link Stream} but using sequential iterators, see
 * {@link EnhancedIterator}. That is not to say that streams are not supported,
 * in fact because of the immutability guarantee we can construct a
 * {@link Spliterator} that is well suited for parallelising operations.
 * </p>
 * 
 * @author ThomasB
 */
public interface Vec<E> extends EnhancedIterable<E>, IIndexable<E>
{
	/**
	 * @return An iteration of the elements in this List in reverse order.
	 */
	EnhancedIterator<E> revIter();

	/**
	 * @return A sequential stream over the elements of this sequence in order.
	 */
	Stream<E> stream();

	/**
	 * @return A new sequence obtains by mapping this sequence elementwise by the
	 *         parameter function.
	 */
	<R> Vec<R> map(Function<? super E, ? extends R> mappingFunction);

	/**
	 * @return A flattened sequence obtained by concatenating the images of the
	 *         parameter function applied elementwise to this sequence.
	 */
	<R> Vec<R> flatMap(Function<? super E, ? extends Iterator<? extends R>> mapping);

	/**
	 * @return A sequence contained the elements of this sequence which pass the
	 *         supplied predicate with their relative order preserved.
	 */
	Vec<E> filter(Predicate<? super E> predicate);

	/**
	 * @return A sequence with the required parameterisation containing the elements
	 *         of this sequence which can be cast to the given type with their
	 *         relative order preserved.
	 */
	<R> Vec<R> cast(Class<R> klass);

	/**
	 * @return A sequence containing the elements of this sequence with order
	 *         retained followed by the elements of the parameter iterable whose
	 *         order is defined by the iterator it produces.
	 */
	Vec<E> append(Iterable<? extends E> other);

	/**
	 * @return A sequence containing the elements of this sequence with order
	 *         preserved followed by the parameter element.
	 */
	Vec<E> append(E other);

	/**
	 * @return A sequence containing the elements of the parameter iterable whose
	 *         order is determined by the iterator it produces followed by the
	 *         elements of this sequence whose relative order is preserved.
	 */
	Vec<E> insert(Iterable<? extends E> other);

	/**
	 * @return A sequence containing the parameter element followed by the elements
	 *         of this sequence whose relative order is preserved.
	 */
	Vec<E> insert(E other);

	/**
	 * @return A sequence consisting of the first n elements of this sequence with
	 *         their relative order retained. If the requested number of elements is
	 *         larger than the size of this sequence then we will just return this
	 *         sequence, if it is less than zero an exception will be thrown.
	 */
	Vec<E> take(int n);

	/**
	 * @return A sequence consisting of the elements taken from the head of this
	 *         sequence until an element fails the given predicate. The first
	 *         failure <b>is not</b> included.
	 */
	Vec<E> takeWhile(Predicate<? super E> predicate);

	/**
	 * @return A sequence consisting of all but the first n elements of this
	 *         sequence with their relative order retained. If the requested number
	 *         of elements is 0 then we just return this sequence, if it is less
	 *         than zero an exception will be thrown.
	 */
	Vec<E> drop(int n);

	/**
	 * @return A sequence consisting of the elements in this sequence which occur
	 *         after the first element which fails the predicate. This first failure
	 *         <b>is</b> included.
	 */
	Vec<E> dropWhile(Predicate<? super E> predicate);

	/**
	 * @return A pair of sequences whose first element is the result of
	 *         {@link Vec#takeWhile(Predicate)} and whose second element is a
	 *         sequence of all elements who were not included in the first sequence.
	 */
	Pair<Vec<E>, Vec<E>> span(Predicate<? super E> predicate);

	/**
	 * @return A pair of sequences whose first element is all the elements of this
	 *         sequence which pass the given predicate, the second is all the
	 *         failures. Relative ordering in these subsequences is preserved.
	 */
	Pair<Vec<E>, Vec<E>> partition(Predicate<? super E> predicate);

	/**
	 * @return A copy of this sequence where the elements are ordered according to
	 *         the supplied comparator.
	 */
	Vec<E> sorted(Comparator<? super E> orderingFunction);

	// Default methods

	/**
	 * @param element The instance to check for membership.
	 * @return true if the given element is in this sequence, false otherwise.
	 */
	default boolean contains(E element)
	{
		return anyMatch(x -> x.equals(element));
	}

	/**
	 * @return A parallel stream over the elements of this sequence.
	 */
	default Stream<E> parstream()
	{
		return stream().parallel();
	}

	default Optional<E> getOption(int index)
	{
		return -1 < index && index < size() ? Optional.of(get(index)) : Optional.empty();
	}

	default E head()
	{
		return get(0);
	}

	default Optional<E> headOption()
	{
		return size() > 0 ? Optional.of(head()) : Optional.empty();
	}

	default E last()
	{
		return get(size() - 1);
	}

	default Optional<E> lastOption()
	{
		return size() > 0 ? Optional.of(last()) : Optional.empty();
	}

	default int[] mapToInt(ToIntFunction<? super E> mappingFunction)
	{
		return iter().mapToInt(mappingFunction).toArray();
	}

	default double[] mapToDouble(ToDoubleFunction<? super E> mappingFunction)
	{
		return iter().mapToDouble(mappingFunction).toArray();
	}

	default long[] mapToLong(ToLongFunction<? super E> mappingFunction)
	{
		return iter().mapToLong(mappingFunction).toArray();
	}

	/**
	 * @return An empty sequence
	 */
	static <E> Vec<E> empty()
	{
		return new VecImpl<>();
	}

	/**
	 * Note that this method is only designed for varargs. If it is passed an array
	 * it won't make a copy.
	 * 
	 * @param elements
	 * @return
	 */
	@SafeVarargs
	static <E> Vec<E> of(E... elements)
	{
		return new VecImpl<>(elements);
	}

	static <E> Vec<E> copy(Collection<? extends E> collection)
	{
		return new VecImpl<>(collection);
	}

	static <E> Vec<E> copy(Iterable<? extends E> iterable)
	{
		return new VecImpl<>(iterable.iterator());
	}

	static <E> Vec<E> fromStream(Stream<? extends E> source)
	{
		return copy(source.sequential().collect(Collectors.toCollection(ArrayList::new)));
	}
}
