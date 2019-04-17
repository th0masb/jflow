package com.github.maumay.jflow.iterators;

import static java.util.Collections.unmodifiableMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.github.maumay.jflow.iterables.RichIterable;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.Vec;

/**
 * <p>
 * An extension of {@link Iterator} with a multitude of extra methods for piping
 * and transforming sequential data streams. There are many static factory
 * methods for constructing instances of this interface in the {@link Iter}
 * class.
 * </p>
 * <p>
 * In general a good rule of thumb when using iterators is to avoid consuming
 * them with the {@link #next()} method unless it is in the standard while loop
 * idiom. The consumption methods provided in this interface are usually far
 * more useful in general (unless you enjoy writing while loops and using
 * mutable collections in which case this library isn't for you).
 * </p>
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface RichIterator<E> extends SafeIterator<E>
{
	/**
	 * Applies a function elementwise to this {@link RichIterator} to make a new
	 * {@link RichIterator}.
	 *
	 * @param   <R> The target element type of the mapping operation.
	 *
	 * @param f A mapping function.
	 * @return A new {@link RichIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link RichIterator} instance in turn.
	 */
	<R> RichIterator<R> map(Function<? super E, ? extends R> f);

	/**
	 * Applies a function elementwise to this {@link RichIterator} to make a new
	 * {@link IntIterator}.
	 *
	 * @param mappingFunction A mapping function.
	 * @return A new {@link IntIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link RichIterator} instance in turn.
	 */
	IntIterator mapToInt(ToIntFunction<? super E> mappingFunction);

	/**
	 * Applies a function elementwise to this {@link RichIterator} to make a new
	 * {@link DoubleIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link DoubleIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link RichIterator} instance in turn.
	 */
	DoubleIterator mapToDouble(ToDoubleFunction<? super E> f);

	/**
	 * Applies a function elementwise to this {@link RichIterator} to make a new
	 * {@link LongIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link LongIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link RichIterator} instance in turn.
	 */
	LongIterator mapToLong(ToLongFunction<? super E> f);

	/**
	 * Maps elements of this {@link RichIterator} to {@link RichIterator} instances
	 * before sequentially concatenating them end to end.
	 *
	 * @param         <R> The element type of the target {@link RichIterator}
	 *                instances.
	 *
	 * @param mapping A function taking elements to instances of
	 *                {@link RichIterator}
	 * @return A {@link RichIterator} obtained by applying the mapping function to
	 *         each element in turn and sequentially concatenating the results.
	 */
	<R> RichIterator<R> flatMap(Function<? super E, ? extends Iterator<? extends R>> mapping);

	/**
	 * Combines this {@link RichIterator} with another iterator to create a new
	 * {@link RichIterator} consisting of pairs of elements with the same index in
	 * their respective origins.
	 *
	 * @param       <R> The upper type bound on the parameter {@link RichIterator}.
	 * @param other The sequence to zip this iterator with.
	 *
	 * @return Denote this source {@link RichIterator} by {@code F} with the
	 *         parameter sequence denoted by {@code I}. We return a new
	 *         {@link RichIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<R> RichIterator<Tup<E, R>> zip(Iterator<? extends R> other);

	/**
	 * Combines this {@link RichIterator} with another sequence to create a new
	 * {@link RichIterator} consisting of pairs of elements with the same index in
	 * their respective origins.
	 *
	 * @param       <R> The upper type bound on the parameter {@link RichIterator}.
	 * @param other The {@link RichIterator} to zip this source {@link RichIterator}
	 *              with.
	 *
	 * @return Denote this source {@link RichIterator} by {@code F} with the
	 *         parameter {@link RichIterator} denoted by {@code I}. We return a new
	 *         {@link RichIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<R> RichIterator<Tup<E, R>> zip(List<? extends R> other);

	/**
	 * Combines this {@link RichIterator} with another sequence to create a new
	 * {@link RichIterator} consisting of pairs of elements with the same index in
	 * their respective origins.
	 *
	 * @param       <R> The upper type bound on the parameter {@link RichIterator}.
	 * @param other The {@link RichIterator} to zip this source {@link RichIterator}
	 *              with.
	 *
	 * @return Denote this source {@link RichIterator} by {@code F} with the
	 *         parameter {@link RichIterator} denoted by {@code I}. We return a new
	 *         {@link RichIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<R> RichIterator<Tup<E, R>> zip(Vec<? extends R> other);

	/**
	 * Combines this iterator with another via an 'interleaving' operation. Elements
	 * are taken from the head of the two sources in turn (beginning with this
	 * iterator) until one of the sources is exhausted at which point the interleave
	 * iterator is considered exhausted too.
	 * 
	 * @param other the other iterator to interleave into this one
	 * @return The interleaving result as described above.
	 */
	RichIterator<E> interleave(Iterator<? extends E> other);

	/**
	 * Creates a new {@link RichIterator} by mapping each element in this source
	 * {@link RichIterator} to a pair consisting of the element and the index it
	 * appears.
	 *
	 * @return Denote this source {@link RichIterator} by {@code F}. We return a new
	 *         {@link RichIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	RichIterator<Tup<Integer, E>> enumerate();

	/**
	 * Creates a new {@link RichIterator} from this {@link RichIterator} by
	 * selecting elements with indices defined by the parameter index mapping.
	 *
	 * @param indexMap A strictly monotonically increasing function
	 *                 {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link RichIterator}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns a {@link RichIterator} {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | f(i) &lt; length(F)} </code></li>
	 *         </ul>
	 */
	RichIterator<E> slice(IntUnaryOperator indexMap);

	/**
	 * Creates a new {@link RichIterator} from this {@link RichIterator} by
	 * selecting the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link RichIterator}. We return a
	 *         {@link RichIterator} consisting of the first
	 *         {@code max(n, length(F))} elements of {@code F}.
	 */
	RichIterator<E> take(int n);

	/**
	 * Creates a new {@link RichIterator} from this {@link RichIterator} by
	 * selecting elements until an element fails the supplied test, the first
	 * failure is not selected.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link RichIterator}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a {@link RichIterator}
	 *         consisting of the first {@code n} elements of this source
	 *         {@link RichIterator}. If no element fails the predicate test then a
	 *         copy of the source is returned.
	 */
	RichIterator<E> takeWhile(Predicate<? super E> predicate);

	/**
	 * Creates a new {@link RichIterator} from this {@link RichIterator} by removing
	 * the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link RichIterator}. We return a
	 *         {@link RichIterator} missing the first {@code min(n, length(F))}
	 *         elements of {@code F}.
	 */
	RichIterator<E> drop(int n);

	/**
	 * Creates a new {@link RichIterator} from this {@link RichIterator} by removing
	 * elements until an element fails the supplied test, the first failure is the
	 * first element of the result.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link RichIterator}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a {@link RichIterator}
	 *         missing {@code n} elements of this source {@link RichIterator}. If no
	 *         element fails the predicate test then a copy of the source is
	 *         returned.
	 */
	RichIterator<E> dropWhile(Predicate<? super E> predicate);

	/**
	 * Creates a new {@link RichIterator} from this {@link RichIterator} by removing
	 * any element which fails the supplied predicate test.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link RichIterator}.
	 * @return A {@link RichIterator} containing only those elements of this source
	 *         {@link RichIterator} which pass the test defined by the parameter
	 *         predicate. The relative ordering of elements is retained.
	 */
	RichIterator<E> filter(Predicate<? super E> predicate);

	/**
	 * Creates a new {@link RichIterator} from this {@link RichIterator} by adding
	 * each element of the supplied iterator to its end in order.
	 *
	 * @param other An {@link RichIterator} containing elements of the same type as
	 *              this source {@link RichIterator}.
	 * @return a {@link RichIterator} consisting of the elements of this source
	 *         {@link RichIterator} followed by the elements of the parameter
	 *         {@link RichIterator}.
	 */
	RichIterator<E> append(Iterator<? extends E> other);

	/**
	 * Creates a new {@link RichIterator} from this {@link RichIterator} by adding
	 * each element to the end of the supplied iterator in order.
	 *
	 * @param other An {@link RichIterator} containing elements of the same type as
	 *              this source {@link RichIterator}.
	 * @return a {@link RichIterator} consisting of the elements of the parameter
	 *         {@link RichIterator} followed by the elements of this source
	 *         {@link RichIterator}.
	 */
	RichIterator<E> insert(Iterator<? extends E> other);

	/**
	 * Convenience method for appending a single element onto the end of this
	 * {@link RichIterator}.
	 *
	 * @param e The element to append
	 * @return A {@link RichIterator} consisting of the elements of this source
	 *         {@link RichIterator} followed by the parameter element
	 */
	RichIterator<E> append(E e);

	/**
	 * Convenience method for inserting a single element into the beginning of this
	 * {@link RichIterator}.
	 *
	 * @param e The element to insert.
	 * @return A {@link RichIterator} consisting of the parameter element followed
	 *         by the elements of the source flow
	 */
	RichIterator<E> insert(E e);

	/**
	 * Applies a scanning operation to this {@link RichIterator} to produce a new
	 * {@link RichIterator}.
	 *
	 * @param             <R> The target element type of the accumulation.
	 *
	 * @param id          The identity element in the accumulation.
	 * @param accumulator The accumulator function.
	 * @return Let {@code F} denote this source {@link RichIterator} and {@code g}
	 *         denote the accumulation function. Then the {@link RichIterator}
	 *         returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	<R> RichIterator<R> scan(R id, BiFunction<R, E, R> accumulator);

	/**
	 * Safely manipulates the element type of this {@link RichIterator} by filtering
	 * out elements who cannot be cast to the target type before casting the
	 * remainder.
	 *
	 * @param       <R> The target type
	 * @param klass A Class instance defining the target type
	 * @return A new {@link RichIterator} with element type given by the supplied
	 *         target type, containing only the elements of the source which are of
	 *         the target type.
	 */
	<R> RichIterator<R> cast(Class<R> klass);

	/**
	 * Calculates the minimum element in this {@link RichIterator} with respect to
	 * the ordering specified by the parameter.
	 *
	 * @param orderingFunction This function defines the ordering on this element
	 *                         type.
	 * @return Nothing if the {@link RichIterator} is empty. Otherwise the minimum
	 *         element in this {@link RichIterator}.
	 */
	Optional<E> minOp(Comparator<? super E> orderingFunction);

	/**
	 * Calculates the minimum element in this {@link RichIterator} with respect to
	 * the ordering specified by the parameter throwing an exception if this
	 * iterator is empty.
	 *
	 * @param orderingFunction This function defines the ordering on this element
	 *                         type.
	 * @return The minimum element in this {@link RichIterator}.
	 */
	E min(Comparator<? super E> orderingFunction);

	/**
	 * Calculates the maximum element in this {@link RichIterator} with respect to
	 * the ordering specified by the parameter.
	 *
	 * @param orderingFunction This function defines the ordering on this element
	 *                         type.
	 * @return Nothing if the {@link RichIterator} is empty. Otherwise the maximum
	 *         element in this {@link RichIterator}.
	 */
	Optional<E> maxOp(Comparator<? super E> orderingFunction);

	/**
	 * Calculates the maximum element in this {@link RichIterator} with respect to
	 * the ordering specified by the parameter throwing an exception if this
	 * iterator is empty.
	 *
	 * @param orderingFunction This function defines the ordering on this element
	 *                         type.
	 * @return The maximum element in this {@link RichIterator}.
	 */
	E max(Comparator<? super E> orderingFunction);

	/**
	 * Checks whether every element in this {@link RichIterator} passes the supplied
	 * predicate test. This method is a 'consuming method', i.e. it will iterate
	 * through this {@link RichIterator}.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link RichIterator}.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(Predicate<? super E> predicate);

	/**
	 * Checks whether any element in this {@link RichIterator} passes the supplied
	 * predicate test. This method is a 'consuming method', i.e. it will iterate
	 * through this {@link RichIterator}.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link RichIterator}.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(Predicate<? super E> predicate);

	/**
	 * Checks whether every element in this {@link RichIterator} fails the supplied
	 * predicate test. This method is a 'consuming method', i.e. it will iterate
	 * through this {@link RichIterator}.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link RichIterator}.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(Predicate<? super E> predicate);

	/**
	 * Fold this {@link RichIterator} to a single value via some reduction function
	 * and an initial value. This method is a 'consuming method', i.e. it will
	 * iterate through this {@link RichIterator}.
	 *
	 * @param         <R> The type of the reduction output.
	 * @param id      The identity of the reduction operation
	 * @param reducer The reduction function
	 * @return If we denote this source {@link RichIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f} then the result is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	<R> R fold(R id, BiFunction<R, E, R> reducer);

	/**
	 * Reduces this {@link RichIterator} to a single value via some reduction
	 * function. This method 'fails gracefully' if this iterator is empty by
	 * returning an {@link Optional}. This method is a 'consuming method', i.e. it
	 * will iterate through this {@link RichIterator}.
	 *
	 * @param reducer The reduction function.
	 * @return Let us denote this source {@link RichIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f}. If {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	Optional<E> foldOp(BinaryOperator<E> reducer);

	/**
	 * Reduces this {@link RichIterator} to a single value via some reduction
	 * function. This method throws an exception if this iterator is empty. This
	 * method is a 'consuming method', i.e. it will iterate through this
	 * {@link RichIterator}.
	 *
	 * @param reducer The reduction function.
	 * @return Let us denote this source {@link RichIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f}. If {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	E fold(BinaryOperator<E> reducer);

	/**
	 * Counts the number of elements in this {@link RichIterator}. This method is a
	 * 'consuming method', i.e. it will iterate through this {@link RichIterator}.
	 *
	 * @return the number of elements in this {@link RichIterator}.
	 */
	long count();

	/**
	 * Caches the elements in this {@link RichIterator} into a {@link Vec}. This
	 * method is a 'consuming method', i.e. it will iterate through this
	 * {@link RichIterator}.
	 *
	 * @return A Vec containing all elements of this source {@link RichIterator} in
	 *         the order that they appeared in the iteration.
	 */
	Vec<E> toVec();

	/**
	 * Converts this iterator into a single use {@link RichIterable}, i.e. it
	 * 'lifts' this object into a supplier function returning this object.
	 * 
	 * @return An iterable which wraps this iterator.
	 */
	RichIterable<E> lift();

	/**
	 * Adapts this iterator via the given function to produce a new iterator and
	 * remove the ability of this iterator to be used directly.
	 * 
	 * @param         <R> The element type of the adaption result.
	 * @param adapter The function describing how a new iterator should be
	 *                constructed from this iterator.
	 * @return The new, adapted iterator which is sourced from this iterator.
	 */
	<R> RichIterator<R> adapt(RichIteratorAdapter<? super E, R> adapter);

	/**
	 * Consumes this iterator using the supplied collection function to create a new
	 * instance of the given type.
	 * 
	 * @param           <R> The type of the collection result.
	 * @param collector The collection function which is used to consume this
	 *                  iterator.
	 * @return The result of the collection function applied to this
	 *         {@link RichIterator}.
	 */
	<R> R collect(RichIteratorCollector<? super E, ? extends R> collector);

	/**
	 * Consumes this iterator via some state-changing procedure.
	 * 
	 * @param consumer The procedure which will be used to consume this iterator.
	 */
	void consume(RichIteratorConsumer<? super E> consumer);

	/**
	 * Associates each unique value in this iterator with the result of applying the
	 * provided function to it. The result is similar to
	 * {@link #toMap(Function, Function)} with the first argument being the identity
	 * function. If duplicate elements appear in this iterator then an exception
	 * will be thrown.
	 * 
	 * @param             <V> The type of values in the resulting map.
	 * @param valueMapper the function which produces the associated values in the
	 *                    resultant map for each unique element in this iterator.
	 * @return a map associating the unique elements of this iterator with the
	 *         result of applying the parameter function to them.
	 */
	<V> Map<E, V> associate(Function<? super E, ? extends V> valueMapper);

	/**
	 * Builds a Map using the elements in this {@link RichIterator} via two supplied
	 * functions. This method is a 'consuming method', i.e. it will iterate through
	 * this {@link RichIterator}.
	 *
	 * @param             <K> The type of the keys in the created mapping.
	 * @param             <V> The type of the values in the created mapping.
	 * @param keyMapper   A function mapping elements of this {@link RichIterator}
	 *                    to elements of the key type.
	 * @param valueMapper A function mapping elements of this {@link RichIterator}
	 *                    to elements of the value type.
	 *
	 * @throws IllegalStateException If two elements of this {@link RichIterator}
	 *                               map to the same key.
	 *
	 * @return A Map instance whose key-value pairs have a 1-to-1 correspondence
	 *         with the elements in this source {@link RichIterator}. More
	 *         specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 *         an element of this source {@link RichIterator}, say {@code e}, is
	 *         associated to the key value pair {@code (k(e), v(e))}.
	 */
	<K, V> Map<K, V> toMap(Function<? super E, ? extends K> keyMapper,
			Function<? super E, ? extends V> valueMapper);

	/**
	 * Groups elements in this {@link RichIterator} via their image under some
	 * supplied classification function. This method is a 'consuming method', i.e.
	 * it will iterate through this {@link RichIterator}.
	 *
	 * @param            <K> The type of the keys in the grouping map.
	 *
	 * @param classifier A function defining the different groups of elements.
	 * @return A Map instance whose keys partition the elements of this source
	 *         {@link RichIterator} via the classification function. Elements in
	 *         this source {@link RichIterator} who have equal (under .equals()
	 *         contract) images under the classification function are grouped
	 *         together in a {@link List} accessed by their shared classification
	 *         key.
	 */
	<K> Map<K, List<E>> groupBy(Function<? super E, ? extends K> classifier);

	/**
	 * Caches the elements in this {@link RichIterator}. This method is a 'consuming
	 * method', i.e. it will iterate through this {@link RichIterator}.
	 *
	 * @param                   <C> The type of collection which is supplied and
	 *                          returned.
	 * @param collectionFactory A function supplying a mutable instance of
	 *                          {@link Collection}
	 * @return The collection obtained by calling the collection supply function
	 *         once and adding each element in this {@link RichIterator} to it
	 */
	<C extends Collection<E>> C toCollection(Supplier<C> collectionFactory);

	/**
	 * Caches the elements in this {@link RichIterator} into a Set. This method is a
	 * 'consuming method', i.e. it will iterate through this {@link RichIterator}.
	 *
	 * @return A Set instance containing all unique elements of the source flow.
	 */
	default Set<E> toSet()
	{
		return toCollection(HashSet::new);
	}

	/**
	 * Caches the elements in this iterator to a Set which is accessible through an
	 * unmodifiable view.
	 * 
	 * @return An immutable view of the result of {@link RichIterator#toSet()}.
	 */
	default Set<E> toUnmodifiableSet()
	{
		return Collections.unmodifiableSet(toSet());
	}

	/**
	 * Caches the elements in this {@link RichIterator} into a List. This method is
	 * a 'consuming method', i.e. it will iterate through this {@link RichIterator}.
	 *
	 * @return A List instance containing all elements of the source flow with their
	 *         order retained.
	 */
	default List<E> toList()
	{
		return toCollection(ArrayList::new);
	}

	/**
	 * Caches the elements in this iterator to a List which is accessible through an
	 * unmodifiable view.
	 * 
	 * @return An unmodifiable view of the result of {@link RichIterator#toList()}.
	 */
	default List<E> toUnmodifiableList()
	{
		return Collections.unmodifiableList(toList());
	}

	/**
	 * Constructs a Map from the elements of this iterator which is accessible
	 * through an unmodifiable view.
	 * 
	 * @param             <K> The type of the keys in the resulting map.
	 * @param             <V> The type of the values in the resulting map.
	 * @param keyMapper   The function mapping the elements of this iterator to the
	 *                    keys of the resulting map.
	 * @param valueMapper The function mapping the elements of this iterator to the
	 *                    values of the resulting map.
	 * @return An immutable view of the result of
	 *         {@link RichIterator#toMap(Function, Function)}.
	 */
	default <K, V> Map<K, V> toUnmodifiableMap(Function<? super E, ? extends K> keyMapper,
			Function<? super E, ? extends V> valueMapper)
	{
		return unmodifiableMap(toMap(keyMapper, valueMapper));
	}
}
