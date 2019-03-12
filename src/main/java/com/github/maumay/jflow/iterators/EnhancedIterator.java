package com.github.maumay.jflow.iterators;

import static java.util.Collections.unmodifiableMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PrimitiveIterator;
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

import com.github.maumay.jflow.iterators.custom.IteratorCollector;
import com.github.maumay.jflow.iterators.custom.IteratorConsumer;
import com.github.maumay.jflow.iterators.factories.Iter;
import com.github.maumay.jflow.utils.DoubleWith;
import com.github.maumay.jflow.utils.IntWith;
import com.github.maumay.jflow.utils.LongWith;
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
public interface EnhancedIterator<E> extends SafeIterator<E>
{
	/**
	 * Applies a function elementwise to this {@link EnhancedIterator} to make a new
	 * {@link EnhancedIterator}.
	 *
	 * @param   <R> The target element type of the mapping operation.
	 *
	 * @param f A mapping function.
	 * @return A new {@link EnhancedIterator} instance whose elements are obtained
	 *         by applying the parameter mapping function to each element of this
	 *         {@link EnhancedIterator} instance in turn.
	 */
	<R> EnhancedIterator<R> map(Function<? super E, ? extends R> f);

	/**
	 * Applies a function elementwise to this {@link EnhancedIterator} to make a new
	 * {@link IntIterator}.
	 *
	 * @param mappingFunction A mapping function.
	 * @return A new {@link IntIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link EnhancedIterator} instance in turn.
	 */
	IntIterator mapToInt(ToIntFunction<? super E> mappingFunction);

	/**
	 * Applies a function elementwise to this {@link EnhancedIterator} to make a new
	 * {@link DoubleIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link DoubleIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link EnhancedIterator} instance in turn.
	 */
	DoubleIterator mapToDouble(ToDoubleFunction<? super E> f);

	/**
	 * Applies a function elementwise to this {@link EnhancedIterator} to make a new
	 * {@link LongIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link LongIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link EnhancedIterator} instance in turn.
	 */
	LongIterator mapToLong(ToLongFunction<? super E> f);

	/**
	 * Maps elements of this {@link EnhancedIterator} to {@link EnhancedIterator}
	 * instances before sequentially concatenating them end to end.
	 *
	 * @param         <R> The element type of the target {@link EnhancedIterator}
	 *                instances.
	 *
	 * @param mapping A function taking elements to instances of
	 *                {@link EnhancedIterator}
	 * @return A {@link EnhancedIterator} obtained by applying the mapping function
	 *         to each element in turn and sequentially concatenating the results.
	 */
	<R> EnhancedIterator<R> flatMap(Function<? super E, ? extends Iterator<? extends R>> mapping);

	/**
	 * Maps elements of this {@link EnhancedIterator} to {@link IntIterator}
	 * instances before sequentially concatenating them end to end.
	 *
	 * @param mapping A function taking elements to instances of {@link IntIterator}
	 * @return A {@link IntIterator} obtained by applying the mapping function to
	 *         each element in turn and sequentially concatenating the results.
	 */
	IntIterator flatMapToInt(Function<? super E, ? extends IntIterator> mapping);

	/**
	 * Maps elements of this {@link EnhancedIterator} to {@link LongIterator}
	 * instances before sequentially concatenating them end to end.
	 *
	 * @param mapping A function taking elements to instances of
	 *                {@link LongIterator}
	 * @return A {@link LongIterator} obtained by applying the mapping function to
	 *         each element in turn and sequentially concatenating the results.
	 */
	LongIterator flatMapToLong(Function<? super E, ? extends LongIterator> mapping);

	/**
	 * Maps elements of this {@link EnhancedIterator} to {@link DoubleIterator}
	 * instances before sequentially concatenating them end to end.
	 *
	 * @param mapping A function taking elements to instances of
	 *                {@link DoubleIterator}
	 * @return A {@link DoubleIterator} obtained by applying the mapping function to
	 *         each element in turn and sequentially concatenating the results.
	 */
	DoubleIterator flatMapToDouble(Function<? super E, ? extends DoubleIterator> mapping);

	/**
	 * Combines this {@link EnhancedIterator} with another iterator to create a new
	 * {@link EnhancedIterator} consisting of pairs of elements with the same index
	 * in their respective origins.
	 *
	 * @param       <R> The upper type bound on the parameter
	 *              {@link EnhancedIterator}.
	 * @param other The {@link EnhancedIterator} to zip this source
	 *              {@link EnhancedIterator} with.
	 *
	 * @return Denote this source {@link EnhancedIterator} by {@code F} with the
	 *         parameter {@link EnhancedIterator} denoted by {@code I}. We return a
	 *         new {@link EnhancedIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<R> EnhancedIterator<Tup<E, R>> zipWith(Iterator<? extends R> other);

	/**
	 * Combines this {@link EnhancedIterator} with another primitive iterator to
	 * create a new {@link EnhancedIterator} consisting of pairs of elements with
	 * the same index in their respective origins.
	 *
	 * @param other The primitive iterator to zip this source
	 *              {@link EnhancedIterator} with.
	 *
	 * @return Denote this source {@link EnhancedIterator} by {@code F} with the
	 *         parameter primitive iterator denoted by {@code I}. We return a new
	 *         {@link EnhancedIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	EnhancedIterator<IntWith<E>> zipWith(PrimitiveIterator.OfInt other);

	/**
	 * Combines this {@link EnhancedIterator} with another primitive iterator to
	 * create a new {@link EnhancedIterator} consisting of pairs of elements with
	 * the same index in their respective origins.
	 *
	 * @param other The primitive iterator to zip this source
	 *              {@link EnhancedIterator} with.
	 *
	 * @return Denote this source {@link EnhancedIterator} by {@code F} with the
	 *         parameter primitive iterator denoted by {@code I}. We return a new
	 *         {@link EnhancedIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	EnhancedIterator<DoubleWith<E>> zipWith(PrimitiveIterator.OfDouble other);

	/**
	 * Combines this {@link EnhancedIterator} with another primitive iterator to
	 * create a new {@link EnhancedIterator} consisting of pairs of elements with
	 * the same index in their respective origins.
	 *
	 * @param other The primitive iterator to zip this source
	 *              {@link EnhancedIterator} with.
	 *
	 * @return Denote this source {@link EnhancedIterator} by {@code F} with the
	 *         parameter primitive iterator denoted by {@code I}. We return a new
	 *         {@link EnhancedIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	EnhancedIterator<LongWith<E>> zipWith(PrimitiveIterator.OfLong other);

	/**
	 * Creates a new {@link EnhancedIterator} by mapping each element in this source
	 * {@link EnhancedIterator} to a pair consisting of the element and the index it
	 * appears.
	 *
	 * @return Denote this source {@link EnhancedIterator} by {@code F}. We return a
	 *         new {@link EnhancedIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	EnhancedIterator<IntWith<E>> enumerate();

	/**
	 * Creates a new {@link EnhancedIterator} from this {@link EnhancedIterator} by
	 * selecting elements with indices defined by the parameter index mapping.
	 *
	 * @param indexMap A strictly monotonically increasing function
	 *                 {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link EnhancedIterator}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns a {@link EnhancedIterator} {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
	 *         </ul>
	 */
	EnhancedIterator<E> slice(IntUnaryOperator indexMap);

	/**
	 * Creates a new {@link EnhancedIterator} from this {@link EnhancedIterator} by
	 * selecting the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link EnhancedIterator}. We return
	 *         a {@link EnhancedIterator} consisting of the first
	 *         {@code max(n, length(F))} elements of {@code F}.
	 */
	EnhancedIterator<E> take(int n);

	/**
	 * Creates a new {@link EnhancedIterator} from this {@link EnhancedIterator} by
	 * selecting elements until an element fails the supplied test, the first
	 * failure is not selected.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link EnhancedIterator}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a
	 *         {@link EnhancedIterator} consisting of the first {@code n} elements
	 *         of this source {@link EnhancedIterator}. If no element fails the
	 *         predicate test then a copy of the source is returned.
	 */
	EnhancedIterator<E> takeWhile(Predicate<? super E> predicate);

	/**
	 * Creates a new {@link EnhancedIterator} from this {@link EnhancedIterator} by
	 * removing the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link EnhancedIterator}. We return
	 *         a {@link EnhancedIterator} missing the first
	 *         {@code min(n, length(F))} elements of {@code F}.
	 */
	EnhancedIterator<E> skip(int n);

	/**
	 * Creates a new {@link EnhancedIterator} from this {@link EnhancedIterator} by
	 * removing elements until an element fails the supplied test, the first failure
	 * is the first element of the result.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link EnhancedIterator}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a
	 *         {@link EnhancedIterator} missing {@code n} elements of this source
	 *         {@link EnhancedIterator}. If no element fails the predicate test then
	 *         a copy of the source is returned.
	 */
	EnhancedIterator<E> skipWhile(Predicate<? super E> predicate);

	/**
	 * Creates a new {@link EnhancedIterator} from this {@link EnhancedIterator} by
	 * removing any element which fails the supplied predicate test.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link EnhancedIterator}.
	 * @return A {@link EnhancedIterator} containing only those elements of this
	 *         source {@link EnhancedIterator} which pass the test defined by the
	 *         parameter predicate. The relative ordering of elements is retained.
	 */
	EnhancedIterator<E> filter(Predicate<? super E> predicate);

	/**
	 * Creates a new {@link EnhancedIterator} from this {@link EnhancedIterator} by
	 * adding each element of the supplied iterator to its end in order.
	 *
	 * @param other An {@link EnhancedIterator} containing elements of the same type
	 *              as this source {@link EnhancedIterator}.
	 * @return a {@link EnhancedIterator} consisting of the elements of this source
	 *         {@link EnhancedIterator} followed by the elements of the parameter
	 *         {@link EnhancedIterator}.
	 */
	EnhancedIterator<E> append(Iterator<? extends E> other);

	/**
	 * Creates a new {@link EnhancedIterator} from this {@link EnhancedIterator} by
	 * adding each element to the end of the supplied iterator in order.
	 *
	 * @param other An {@link EnhancedIterator} containing elements of the same type
	 *              as this source {@link EnhancedIterator}.
	 * @return a {@link EnhancedIterator} consisting of the elements of the
	 *         parameter {@link EnhancedIterator} followed by the elements of this
	 *         source {@link EnhancedIterator}.
	 */
	EnhancedIterator<E> insert(Iterator<? extends E> other);

	/**
	 * Applies a scanning operation to this {@link EnhancedIterator} to produce a
	 * new {@link EnhancedIterator}.
	 *
	 * @param accumulator The accumulation function.
	 * @return Let {@code F} denote this source {@link EnhancedIterator} and
	 *         {@code g} denote the accumulation function. Then the
	 *         {@link EnhancedIterator} returned is of the form:
	 *         <ul>
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}</li>
	 *         </ul>
	 */
	EnhancedIterator<E> scan(BinaryOperator<E> accumulator);

	/**
	 * Applies a scanning operation to this {@link EnhancedIterator} to produce a
	 * new {@link EnhancedIterator}.
	 *
	 * @param             <R> The target element type of the accumulation.
	 *
	 * @param id          The identity element in the accumulation.
	 * @param accumulator The accumulator function.
	 * @return Let {@code F} denote this source {@link EnhancedIterator} and
	 *         {@code g} denote the accumulation function. Then the
	 *         {@link EnhancedIterator} returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	<R> EnhancedIterator<R> scan(R id, BiFunction<R, E, R> accumulator);

	/**
	 * Calculates the minimum element in this {@link EnhancedIterator} with respect
	 * to the ordering specified by the parameter.
	 *
	 * @param orderingFunction This function defines the ordering on this element
	 *                         type.
	 * @return Nothing if the {@link EnhancedIterator} is empty. Otherwise the
	 *         minimum element in this {@link EnhancedIterator}.
	 */
	Optional<E> minOption(Comparator<? super E> orderingFunction);

	/**
	 * Calculates the minimum element in this {@link EnhancedIterator} with respect
	 * to the ordering specified by the parameter throwing an exception if this
	 * iterator is empty.
	 *
	 * @param orderingFunction This function defines the ordering on this element
	 *                         type.
	 * @return The minimum element in this {@link EnhancedIterator}.
	 */
	default E min(Comparator<? super E> orderingFunction)
	{
		return minOption(orderingFunction).get();
	}

	/**
	 * Calculates the maximum element in this {@link EnhancedIterator} with respect
	 * to the ordering specified by the parameter.
	 *
	 * @param orderingFunction This function defines the ordering on this element
	 *                         type.
	 * @return Nothing if the {@link EnhancedIterator} is empty. Otherwise the
	 *         maximum element in this {@link EnhancedIterator}.
	 */
	Optional<E> maxOption(Comparator<? super E> orderingFunction);

	/**
	 * Calculates the maximum element in this {@link EnhancedIterator} with respect
	 * to the ordering specified by the parameter throwing an exception if this
	 * iterator is empty.
	 *
	 * @param orderingFunction This function defines the ordering on this element
	 *                         type.
	 * @return The maximum element in this {@link EnhancedIterator}.
	 */
	default E max(Comparator<? super E> orderingFunction)
	{
		return maxOption(orderingFunction).get();
	}

	/**
	 * Checks whether every element in this {@link EnhancedIterator} is the same.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedIterator}.
	 *
	 * @return True is every element of this {@link EnhancedIterator} is equal
	 *         (under .equals contract), false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * Checks whether every element in this {@link EnhancedIterator} passes the
	 * supplied predicate test. This method is a 'consuming method', i.e. it will
	 * iterate through this {@link EnhancedIterator}.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link EnhancedIterator}.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(Predicate<? super E> predicate);

	/**
	 * Checks whether any element in this {@link EnhancedIterator} passes the
	 * supplied predicate test. This method is a 'consuming method', i.e. it will
	 * iterate through this {@link EnhancedIterator}.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link EnhancedIterator}.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(Predicate<? super E> predicate);

	/**
	 * Checks whether every element in this {@link EnhancedIterator} fails the
	 * supplied predicate test. This method is a 'consuming method', i.e. it will
	 * iterate through this {@link EnhancedIterator}.
	 *
	 * @param predicate A predicate applicable to the type of elements in this
	 *                  {@link EnhancedIterator}.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(Predicate<? super E> predicate);

	/**
	 * Fold this {@link EnhancedIterator} to a single value via some reduction
	 * function and an initial value. This method is a 'consuming method', i.e. it
	 * will iterate through this {@link EnhancedIterator}.
	 *
	 * @param         <R> The type of the reduction output.
	 * @param id      The identity of the reduction operation
	 * @param reducer The reduction function
	 * @return If we denote this source {@link EnhancedIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f} then the result is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	<R> R fold(R id, BiFunction<R, E, R> reducer);

	/**
	 * Reduces this {@link EnhancedIterator} to a single value via some reduction
	 * function. This method 'fails gracefully' if this iterator is empty by
	 * returning an {@link Optional}. This method is a 'consuming method', i.e. it
	 * will iterate through this {@link EnhancedIterator}.
	 *
	 * @param reducer The reduction function.
	 * @return Let us denote this source {@link EnhancedIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f}. If {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	Optional<E> foldOption(BinaryOperator<E> reducer);

	/**
	 * Reduces this {@link EnhancedIterator} to a single value via some reduction
	 * function. This method throws an exception if this iterator is empty. This
	 * method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedIterator}.
	 *
	 * @param reducer The reduction function.
	 * @return Let us denote this source {@link EnhancedIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f}. If {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	E fold(BinaryOperator<E> reducer);

	/**
	 * Counts the number of elements in this {@link EnhancedIterator}. This method
	 * is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedIterator}.
	 *
	 * @return the number of elements in this {@link EnhancedIterator}.
	 */
	long count();

	/**
	 * Caches the elements in this {@link EnhancedIterator} into a {@link Vec}. This
	 * method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedIterator}.
	 *
	 * @return A Vec containing all elements of this source {@link EnhancedIterator}
	 *         in the order that they appeared in the iteration.
	 */
	default Vec<E> toVec()
	{
		return Vec.fromIterator(this);
	}

	/**
	 * Caches the elements in this {@link EnhancedIterator}. This method is a
	 * 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedIterator}.
	 *
	 * @param                   <C> The type of collection which is supplied and
	 *                          returned.
	 * @param collectionFactory A function supplying a mutable instance of
	 *                          {@link Collection}
	 * @return The collection obtained by calling the collection supply function
	 *         once and adding each element in this {@link EnhancedIterator} to it
	 */
	default <C extends Collection<E>> C toCollection(Supplier<C> collectionFactory)
	{
		C coll = collectionFactory.get();
		while (hasNext()) {
			coll.add(next());
		}
		return coll;
	}

	/**
	 * Caches the elements in this {@link EnhancedIterator} into a Set. This method
	 * is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedIterator}.
	 *
	 * @return A Set instance containing all unique elements of the source flow.
	 */
	default Set<E> toSet()
	{
		Set<E> set = sizeIsKnown() ? new HashSet<>(size().getAsInt()) : new HashSet<>();
		while (hasNext()) {
			set.add(next());
		}
		return set;
	}

	/**
	 * Caches the elements in this iterator to a Set which is accessible through an
	 * unmodifiable view.
	 * 
	 * @return An immutable view of the result of {@link EnhancedIterator#toSet()}.
	 */
	default Set<E> toUnmodifiableSet()
	{
		return Collections.unmodifiableSet(toSet());
	}

	/**
	 * Caches the elements in this {@link EnhancedIterator} into a List. This method
	 * is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedIterator}.
	 *
	 * @return A List instance containing all elements of the source flow with their
	 *         order retained.
	 */
	default List<E> toList()
	{
		List<E> xs = new ArrayList<>(size().orElse(10));
		while (hasNext()) {
			xs.add(next());
		}
		return xs;
	}

	/**
	 * Caches the elements in this iterator to a List which is accessible through an
	 * unmodifiable view.
	 * 
	 * @return An unmodifiable view of the result of
	 *         {@link EnhancedIterator#toList()}.
	 */
	default List<E> toUnmodifiableList()
	{
		return Collections.unmodifiableList(toList());
	}

	/**
	 * Builds a Map using the elements in this {@link EnhancedIterator} via two
	 * supplied functions. This method is a 'consuming method', i.e. it will iterate
	 * through this {@link EnhancedIterator}.
	 *
	 * @param             <K> The type of the keys in the created mapping.
	 * @param             <V> The type of the values in the created mapping.
	 * @param keyMapper   A function mapping elements of this
	 *                    {@link EnhancedIterator} to elements of the key type.
	 * @param valueMapper A function mapping elements of this
	 *                    {@link EnhancedIterator} to elements of the value type.
	 *
	 * @throws IllegalStateException If two elements of this
	 *                               {@link EnhancedIterator} map to the same key.
	 *
	 * @return A Map instance whose key-value pairs have a 1-to-1 correspondence
	 *         with the elements in this source {@link EnhancedIterator}. More
	 *         specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 *         an element of this source {@link EnhancedIterator}, say {@code e}, is
	 *         associated to the key value pair {@code (k(e), v(e))}.
	 */
	default <K, V> Map<K, V> toMap(Function<? super E, ? extends K> keyMapper,
			Function<? super E, ? extends V> valueMapper)
	{
		Map<K, V> collected = new HashMap<>();
		while (hasNext()) {
			E next = next();
			K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	/**
	 * Applies the same logic as {@link #toMap(Function, Function)} but optimizes
	 * the result for enumerate types by returning an instance of {@link EnumMap}.
	 * Note that if this iterator is empty then a {@link HashMap} will be returned
	 * instead.
	 * 
	 * @param             <K> The type of key in the resulting map.
	 * @param             <V> The type of value in the resulting map.
	 * @param keyMapper   see {@link #toMap(Function, Function)}
	 * @param valueMapper see {@link #toMap(Function, Function)}
	 * @return see {@link #toMap(Function, Function)}
	 */
	default <K extends Enum<K>, V> Map<K, V> toEnumMap(Function<? super E, K> keyMapper,
			Function<? super E, ? extends V> valueMapper)
	{
		// If there are no concrete elements we can't instantiate an enum map so return
		// a HashMap
		if (!hasNext()) {
			return new HashMap<>();
		}
		E first = next();
		K firstKey = keyMapper.apply(first);
		@SuppressWarnings("unchecked")
		Map<K, V> dest = new EnumMap<>((Class<K>) firstKey.getClass());
		dest.put(firstKey, valueMapper.apply(first));
		while (hasNext()) {
			E next = next();
			K key = keyMapper.apply(next);
			if (dest.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				dest.put(key, valueMapper.apply(next));
			}
		}
		return dest;
	}

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
	default <V> Map<E, V> associate(Function<? super E, ? extends V> valueMapper)
	{
		Map<E, V> collected = new HashMap<>();
		while (hasNext()) {
			E key = next();
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(key));
			}
		}
		return collected;
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
	 *         {@link EnhancedIterator#toMap(Function, Function)}.
	 */
	default <K, V> Map<K, V> toUnmodifiableMap(Function<? super E, ? extends K> keyMapper,
			Function<? super E, ? extends V> valueMapper)
	{
		return unmodifiableMap(toMap(keyMapper, valueMapper));
	}

	/**
	 * Groups elements in this {@link EnhancedIterator} via their image under some
	 * supplied classification function. This method is a 'consuming method', i.e.
	 * it will iterate through this {@link EnhancedIterator}.
	 *
	 * @param            <K> The type of the keys in the grouping map.
	 *
	 * @param classifier A function defining the different groups of elements.
	 * @return A Map instance whose keys partition the elements of this source
	 *         {@link EnhancedIterator} via the classification function. Elements in
	 *         this source {@link EnhancedIterator} who have equal (under .equals()
	 *         contract) images under the classification function are grouped
	 *         together in a {@link List} accessed by their shared classification
	 *         key.
	 */
	default <K> Map<K, List<E>> groupBy(Function<? super E, ? extends K> classifier)
	{
		Map<K, List<E>> collected = new HashMap<>();
		while (hasNext()) {
			E next = next();
			K key = classifier.apply(next);
			collected.putIfAbsent(key, new ArrayList<>());
			collected.get(key).add(next);
		}
		return collected;
	}

	/**
	 * Safely manipulates the element type of this {@link EnhancedIterator} by
	 * filtering out elements who cannot be cast to the target type before casting
	 * the remainder.
	 *
	 * @param       <R> The target type
	 * @param klass A Class instance defining the target type
	 * @return A new {@link EnhancedIterator} with element type given by the
	 *         supplied target type, containing only the elements of the source
	 *         which are of the target type.
	 */
	default <R> EnhancedIterator<R> cast(Class<R> klass)
	{
		return filter(klass::isInstance).map(klass::cast);
	}

	/**
	 * Consumes this iterator using the supplied collection function to create a new
	 * instance of the given type.
	 * 
	 * @param           <R> The type of the collection result..
	 * @param collector The collection function which is used to consume this
	 *                  iterator.
	 * @return The result of the collection function applied to this
	 *         {@link EnhancedIterator}.
	 */
	default <R> R collect(IteratorCollector<? super E, ? extends R> collector)
	{
		return collector.collect(this);
	}

	/**
	 * Consumes this iterator via some state-changing procedure.
	 * 
	 * @param consumer The procedure which will be used to consume this iterator.
	 */
	default void consume(IteratorConsumer<? super E> consumer)
	{
		consumer.consume(this);
	}

	/**
	 * Convenience method for appending a single element onto the end of this
	 * {@link EnhancedIterator}.
	 *
	 * @param e The element to append
	 * @return A {@link EnhancedIterator} consisting of the elements of this source
	 *         {@link EnhancedIterator} followed by the parameter element
	 */
	default EnhancedIterator<E> append(E e)
	{
		return append(Iter.over(e));
	}

	/**
	 * Convenience method for inserting a single element into the beginning of this
	 * {@link EnhancedIterator}.
	 *
	 * @param e The element to insert.
	 * @return A {@link EnhancedIterator} consisting of the parameter element
	 *         followed by the elements of the source flow
	 */
	default EnhancedIterator<E> insert(E e)
	{
		return insert(Iter.over(e));
	}

	/**
	 * Convenience method which delegates to
	 * {@link EnhancedIterator#zipWith(Iterator)}.
	 * 
	 * @param       <R> The element type of the iterable element source.
	 * @param other Some iterable object.
	 * @return the result of zipping this {@link EnhancedIterator} with an iterator
	 *         created from the parameter iterable.
	 */
	default <R> EnhancedIterator<Tup<E, R>> zipWith(Iterable<? extends R> other)
	{
		return zipWith(other.iterator());
	}
}
