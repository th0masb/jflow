package xawd.jflow.iterators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import java.util.stream.Stream;

import xawd.jflow.iterators.construction.Iterate;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.LongWith;
import xawd.jflow.iterators.misc.Pair;
import xawd.jflow.iterators.misc.PredicatePartition;

/**
 * A {@link Flow} instance is an {@link Iterator} with lots of extra
 * functionality in the style of the {@link Stream} interface. There are methods
 * inspired by other languages too, namely Python and Haskell.
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface Flow<E> extends PrototypeFlow<E>
{
	/**
	 * Applies a function elementwise to this {@linkplain Flow} to make a new Flow.
	 *
	 * @param <R>
	 *            The target element type of the mapping operation.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new Flow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this Flow instance in
	 *         turn.
	 */
	<R> Flow<R> map(final Function<? super E, R> f);

	/**
	 * Applies a function elementwise to this {@linkplain Flow} to make a new
	 * {@linkplain IntFlow}.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new IntFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this Flow instance in
	 *         turn.
	 */
	IntFlow mapToInt(ToIntFunction<? super E> f);

	/**
	 * Applies a function elementwise to this {@linkplain Flow} to make a new
	 * {@linkplain DoubleFlow}.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new DoubleFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this Flow instance in
	 *         turn.
	 */
	DoubleFlow mapToDouble(ToDoubleFunction<? super E> f);

	/**
	 * Applies a function elementwise to this {@linkplain Flow} to make a new
	 * {@linkplain LongFlow}.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new LongFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this Flow instance in
	 *         turn.
	 */
	LongFlow mapToLong(ToLongFunction<? super E> f);

	/**
	 * Maps elements of this {@linkplain Flow} to Flow instances before sequentially
	 * concatenating them end to end.
	 *
	 * @param <R>
	 *            The element type of the target Flow instances.
	 *
	 * @param mapping
	 *            A function taking elements to instances of Flow
	 * @return A Flow obtained by applying the mapping function to each element in
	 *         turn and sequentially concatenating the results.
	 */
	<R> Flow<R> flatten(Function<? super E, ? extends Flow<R>> mapping);

	/**
	 * Maps elements of this {@linkplain Flow} to {@linkplain IntFlow} instances
	 * before sequentially concatenating them end to end.
	 *
	 * @param mapping
	 *            A function taking elements to instances of IntFlow
	 * @return A IntFlow obtained by applying the mapping function to each element
	 *         in turn and sequentially concatenating the results.
	 */
	IntFlow flattenToInts(Function<? super E, ? extends IntFlow> mapping);

	/**
	 * Maps elements of this {@linkplain Flow} to {@linkplain LongFlow} instances
	 * before sequentially concatenating them end to end.
	 *
	 * @param mapping
	 *            A function taking elements to instances of LongFlow
	 * @return A LongFlow obtained by applying the mapping function to each element
	 *         in turn and sequentially concatenating the results.
	 */
	LongFlow flattenToLongs(Function<? super E, ? extends LongFlow> mapping);

	/**
	 * Maps elements of this {@linkplain Flow} to {@linkplain DoubleFlow} instances
	 * before sequentially concatenating them end to end.
	 *
	 * @param mapping
	 *            A function taking elements to instances of DoubleFlow
	 * @return A DoubleFlow obtained by applying the mapping function to each
	 *         element in turn and sequentially concatenating the results.
	 */
	DoubleFlow flattenToDoubles(Function<? super E, ? extends DoubleFlow> mapping);

	/**
	 * Combines this {@linkplain Flow} with another {@linkplain Iterator} to create
	 * a new Flow consisting of pairs of elements with the same index in their
	 * respective origins.
	 *
	 * @param <R>
	 *            The upper type bound on the parameter Iterator.
	 * @param other
	 *            The Iterator to zip this source Flow with.
	 *
	 * @return Denote this source Flow by {@code F} with the parameter Iterator
	 *         denoted by {@code I}. We return a new Flow instance {@code G} defined
	 *         by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<R> Flow<Pair<E, R>> zipWith(final Iterator<? extends R> other);

	/**
	 * Combines this {@linkplain Flow} with another
	 * {@linkplain PrimitiveIterator.OfInt}} to create a new Flow consisting of
	 * pairs of elements with the same index in their respective origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfInt to zip this source Flow with.
	 *
	 * @return Denote this source Flow by {@code F} with the parameter
	 *         PrimitiveIterator.OfInt denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<IntWith<E>> zipWith(final PrimitiveIterator.OfInt other);

	/**
	 * Combines this {@linkplain Flow} with another
	 * {@linkplain PrimitiveIterator.OfDouble}} to create a new Flow consisting of
	 * pairs of elements with the same index in their respective origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfDouble to zip this source Flow with.
	 *
	 * @return Denote this source Flow by {@code F} with the parameter
	 *         PrimitiveIterator.OfDouble denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<DoubleWith<E>> zipWith(final PrimitiveIterator.OfDouble other);

	/**
	 * Combines this {@linkplain Flow} with another
	 * {@linkplain PrimitiveIterator.OfLong}} to create a new Flow consisting of
	 * pairs of elements with the same index in their respective origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfLong to zip this source Flow with.
	 *
	 * @return Denote this source Flow by {@code F} with the parameter
	 *         PrimitiveIterator.OfLong denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<LongWith<E>> zipWith(final PrimitiveIterator.OfLong other);

	/**
	 * Combines this {@linkplain Flow} with another {@linkplain Iterator} via a two
	 * argument function to create a new Flow consisting of the images of pairs of
	 * elements with the same index in their origin.
	 *
	 * @param <R>
	 *            The result type of the combining operation.
	 * @param <E2>
	 *            The upper type bound on the parameter Iterator.
	 * @param other
	 *            The Iterator to combine this source Flow with.
	 * @param f
	 *            The combining function.
	 *
	 * @return Denote this source Flow by {@code F} with the parameter Iterator
	 *         denoted by {@code I} and the combining function by {@code f}. We
	 *         return a new Flow instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = f(F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<E2, R> Flow<R> combineWith(final Iterator<? extends E2> other, final BiFunction<? super E, ? super E2, R> f);

	/**
	 * Creates a new {@linkplain Flow} by mapping each element in this source Flow
	 * to a pair consisting of the element and the index it appears.
	 *
	 * @return Denote this source Flow by {@code F}. We return a new Flow instance
	 *         {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	Flow<IntWith<E>> enumerate();

	/**
	 * Creates a new {@linkplain Flow} from this Flow by selecting elements with
	 * indices defined by the parameter index mapping.
	 *
	 * @param indexMap
	 *            A strictly monotonically increasing function {@code f: N -> N}
	 * @return Let {@code F} denote this source Flow, let {@code n = length(F)} and
	 *         denote the indexMap by {@code f}. Then this method returns a Flow
	 *         {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
	 *         </ul>
	 */
	Flow<E> slice(final IntUnaryOperator indexMap);

	/**
	 * Creates a new {@linkplain Flow} from this Flow by selecting the first n
	 * elements.
	 *
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote this source Flow. We return a Flow consisting of
	 *         the first {@code max(n, length(F))} elements of {@code F}.
	 */
	Flow<E> take(final int n);

	/**
	 * Creates a new {@linkplain Flow} from this Flow by selecting elements until an
	 * element fails the supplied test, the first failure is not selected.
	 *
	 * @param predicate
	 *            A {@link Predicate} applicable to the type of elements in this
	 *            Flow.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a Flow consisting of
	 *         the first {@code n} elements of this source Flow. If no element fails
	 *         the predicate test then a copy of the source is returned.
	 */
	Flow<E> takeWhile(final Predicate<? super E> predicate);

	/**
	 * Creates a new {@linkplain Flow} from this Flow by removing the first n
	 * elements.
	 *
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote this source Flow. We return a Flow missing the
	 *         first {@code min(n, length(F))} elements of {@code F}.
	 */
	Flow<E> drop(final int n);

	/**
	 * Creates a new {@linkplain Flow} from this Flow by removing elements until an
	 * element fails the supplied test, the first failure is the first element of
	 * the result.
	 *
	 * @param predicate
	 *            A {@link Predicate} applicable to the type of elements in this
	 *            Flow.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a Flow missing
	 *         {@code n} elements of this source Flow. If no element fails the
	 *         predicate test then a copy of the source is returned.
	 */
	Flow<E> dropWhile(final Predicate<? super E> predicate);

	/**
	 * Creates a new {@linkplain Flow} from this Flow by removing any element which
	 * fails the supplied predicate test.
	 *
	 * @param predicate
	 *            A {@link Predicate} applicable to the type of elements in this
	 *            Flow.
	 * @return A Flow containing only those elements of this source Flow which pass
	 *         the test defined by the parameter predicate. The relative ordering of
	 *         elements is retained.
	 */
	Flow<E> filter(final Predicate<? super E> predicate);

	/**
	 * Creates a new {@linkplain Flow} from this Flow by adding each element of the
	 * supplied {@linkplain Iterator} to its end in order.
	 *
	 * @param other
	 *            An Iterator containing elements of the same type as this source
	 *            Flow.
	 * @return a Flow consisting of the elements of this source Flow followed by the
	 *         elements of the parameter Iterator.
	 */
	Flow<E> append(Iterator<? extends E> other);

	/**
	 * Creates a new {@linkplain Flow} from this Flow by adding each element to the
	 * end of the supplied {@linkplain Iterator} in order.
	 *
	 * @param other
	 *            An Iterator containing elements of the same type as this source
	 *            Flow.
	 * @return a Flow consisting of the elements of the parameter Iterator followed
	 *         by the elements of this source Flow.
	 */
	Flow<E> insert(Iterator<? extends E> other);

	/**
	 * Applies an accumulation operation to this {@linkplain Flow} to produce a new
	 * Flow.
	 *
	 * @param accumulator
	 *            The accumulation function.
	 * @return Let {@code F} denote this source Flow and {@code g} denote the
	 *         accumulation function. Then the Flow returned is of the form:
	 *         <ul>
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}</li>
	 *         </ul>
	 */
	Flow<E> accumulate(BinaryOperator<E> accumulator);

	/**
	 * Applies an accumulation operation to this {@linkplain Flow} to produce a new
	 * Flow.
	 *
	 * @param <R>
	 *            The target element type of the accumulation.
	 *
	 * @param id
	 *            The identity element in the accumulation.
	 * @param accumulator
	 *            The accumulator function.
	 * @return Let {@code F} denote this source Flow and {@code g} denote the
	 *         accumulation function. Then the Flow returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	<R> Flow<R> accumulate(R id, BiFunction<R, E, R> accumulator);

	/**
	 * Combines consecutive pairs of elements in this {@linkplain Flow} via a two
	 * argument function to create a new Flow which is one element shorter.
	 *
	 * @param <R>
	 *            The target type of the pair folding operation.
	 * @param foldFunction
	 *            The function defining the pair folding operation.
	 * @return Let {@code F = [F[0], F[1], ...]} represent this source Flow and
	 *         denote the folding function by {@code f}. Then we return a new Flow
	 *         instance defined as follows:
	 *         <ul>
	 *         <li>{@code [f(F[0], F[1]), f(F[1], F[2]), ...]}</li>
	 *         </ul>
	 *         <br>
	 *         If this source Flow has less that two elements an empty Flow is
	 *         returned.
	 */
	<R> Flow<R> pairFold(final BiFunction<? super E, ? super E, R> foldFunction);

	/**
	 * Calculates the minimum element in this {@linkplain Flow} by an embedding into
	 * the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param key
	 *            A function mapping the elements of this Flow into the real
	 *            numbers.
	 * @return The element of this Flow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	Optional<E> minByKey(final ToDoubleFunction<? super E> key);

	/**
	 * Calculates the minimum element in this {@linkplain Flow} by a mapping to a
	 * type equipped with a natural ordering.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <C>
	 *            A type equipped with a natural ordering.
	 *
	 * @param key
	 *            A function mapping the elements of this Flow to some data type
	 *            with an ordering.
	 * @return The element of this Flow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 */
	<C extends Comparable<C>> Optional<E> minByObjectKey(final Function<? super E, C> key);

	/**
	 * Calculates the maximum element in this {@linkplain Flow} by an embedding into
	 * the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param key
	 *            A function mapping the elements of this Flow into the real
	 *            numbers.
	 * @return The element of this Flow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	Optional<E> maxByKey(final ToDoubleFunction<? super E> key);

	/**
	 * Calculates the maximum element in this {@linkplain Flow} by a mapping to a
	 * type equipped with a natural ordering.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <C>
	 *            A type equipped with a natural ordering.
	 *
	 * @param key
	 *            A function mapping the elements of this Flow to some data type
	 *            with an ordering.
	 * @return The element of this Flow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 */
	<C extends Comparable<C>> Optional<E> maxByObjectKey(final Function<? super E, C> key);

	/**
	 * Checks whether every element in this {@linkplain Flow} is the same.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @return True is every element of this Flow is equal (under .equals contract),
	 *         false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * Checks whether every element in this {@linkplain Flow} passes the supplied
	 * {@linkplain Predicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param predicate
	 *            A predicate applicable to the type of elements in this Flow.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(final Predicate<? super E> predicate);

	/**
	 * Checks whether any element in this {@linkplain Flow} passes the supplied
	 * {@linkplain Predicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param predicate
	 *            A predicate applicable to the type of elements in this Flow.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(final Predicate<? super E> predicate);

	/**
	 * Checks whether every element in this {@linkplain Flow} fails the supplied
	 * {@linkplain Predicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param predicate
	 *            A predicate applicable to the type of elements in this Flow.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(final Predicate<? super E> predicate);

	/**
	 * Partitions the elements of this {@linkplain Flow} on whether they pass the
	 * supplied {@linkplain Predicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param predicate
	 *            A predicate applicable to the type of elements in this Flow.
	 * @return A partition of the cached elements split into two lists on whether
	 *         they passed or failed the parameter predicate.
	 */
	PredicatePartition<E> partition(Predicate<? super E> predicate);

	/**
	 * Reduces this {@linkplain Flow} to a single value via some reduction function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <R>
	 *            The type of the reduction output.
	 * @param id
	 *            The identity of the reduction operation
	 * @param reducer
	 *            The reduction function
	 * @return If we denote this source Flow by {@code F}, the length of {@code F}
	 *         by {@code n} and the reduction function by {@code f} then the result
	 *         is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	<R> R reduce(R id, BiFunction<R, E, R> reducer);

	/**
	 * Reduces this {@linkplain Flow} to a single value via some reduction function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param reducer
	 *            The reduction function.
	 * @return Let us denote this source Flow by {@code F}, the length of {@code F}
	 *         by {@code n} and the reduction function by {@code f}. If
	 *         {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	Optional<E> reduce(BinaryOperator<E> reducer);

	/**
	 * Counts the number of elements in this {@linkplain Flow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @return the number of elements in this Flow.
	 */
	long count();

	/**
	 * Caches the elements in this {@linkplain Flow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <C>
	 *            The type of collection which is supplied and returned.
	 * @param collectionFactory
	 *            A function supplying a mutable instance of {@link Collection}
	 * @return The collection obtained by calling the collection supply function
	 *         once and adding each element in this Flow to it
	 */
	<C extends Collection<E>> C toCollection(final Supplier<C> collectionFactory);

	/**
	 * Builds a {@linkplain Map} using the elements in this {@linkplain Flow} via
	 * two supplied functions.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <K>
	 *            The type of the keys in the created mapping.
	 * @param <V>
	 *            The type of the values in the created mapping.
	 * @param keyMapper
	 *            A function mapping elements of this Flow to elements of the key
	 *            type.
	 * @param valueMapper
	 *            A function mapping elements of this Flow to elements of the value
	 *            type.
	 *
	 * @throws IllegalStateException
	 *             If two elements of this Flow map to the same key.
	 *
	 * @return A Map instance whose key-value pairs have a 1-to-1 correspondence
	 *         with the elements in this source Flow. More specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 *         an element of this source Flow, say {@code e}, is associated to the
	 *         key value pair {@code (k(e), v(e))}.
	 */
	<K, V> Map<K, V> toMap(final Function<? super E, K> keyMapper, final Function<? super E, V> valueMapper);

	/**
	 * Groups elements in this {@linkplain Flow} via their image under some supplied
	 * classification function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <K>
	 *            The type of the keys in the final grouping map.
	 *
	 * @param classifier
	 *            A function defining the different groups of elements.
	 * @return A Map instance whose keys partition the elements of this source Flow
	 *         via the classification function. Elements in this source Flow who
	 *         have equal (under .equals() contract) images under the classification
	 *         function are grouped together in a {@link List} accessed by their
	 *         shared classification key.
	 */
	<K> Map<K, List<E>> groupBy(final Function<? super E, K> classifier);

	/**
	 * A convenience method for safely manipulating the element type of this
	 * {@linkplain Flow}.
	 *
	 * @param <R>
	 *            The target type
	 * @param klass
	 *            A {@link Class} instance defining the target type
	 * @return A new Flow with element type given by the supplied target type,
	 *         containing only the elements of the source which are of the target
	 *         type.
	 */
	default <R> Flow<R> filterAndCastTo(final Class<R> klass)
	{
		return filter(klass::isInstance).map(klass::cast);
	}

	/**
	 * A convenience method for applying a global function onto this
	 * {@linkplain Flow}.
	 *
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this Flow.
	 *
	 * @param <R>
	 *            The target type of the builder function.
	 *
	 * @param builder
	 *            A function whose input encompasses Flow instances of this element
	 *            type.
	 * @return The output of the supplied function applied to this Flow.
	 */
	default <R> R build(final Function<? super Flow<E>, R> builder)
	{
		return builder.apply(this);
	}

	/**
	 * Convenience method for appending a single element onto the end of this
	 * {@linkplain Flow}.
	 *
	 * @param e
	 *            The element to append
	 * @return A Flow consisting of the elements of this source Flow followed by the
	 *         parameter element
	 */
	default Flow<E> append(final E e)
	{
		return append(Iterate.over(e));
	}

	/**
	 * Convenience method for inserting a single element into the beginning of this
	 * {@linkplain Flow}.
	 *
	 * @param e
	 *            The element to insert.
	 * @return A Flow consisting of the parameter element followed by the elements
	 *         of the source flow
	 */
	default Flow<E> insert(final E e)
	{
		return append(Iterate.over(e));
	}

	/**
	 * Caches the elements in this {@linkplain Flow} into a {@linkplain List}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @return A List instance containing all elements of this source Flow in the
	 *         order that they appeared in the iteration.
	 */
	default List<E> toList()
	{
		return toCollection(ArrayList::new);
	}

	/**
	 * Caches the elements in this {@linkplain Flow} into an immutable
	 * (unmodifiable) {@linkplain List}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @return An immutable List instance containing all elements of the source Flow
	 *         in the order that they appeared in the iteration.
	 */
	default List<E> toImmutableList()
	{
		return Collections.unmodifiableList(toList());
	}

	/**
	 * Caches the elements in this {@linkplain Flow} into a {@linkplain Set}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @return A Set instance containing all unique elements of the source flow.
	 */
	default Set<E> toSet()
	{
		return toCollection(HashSet::new);
	}

	/**
	 * Caches the elements in this {@linkplain Flow} into an immutable
	 * (unmodifiable) {@linkplain Set}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @return An immutable Set instance containing all unique elements of the
	 *         source flow.
	 */
	default Set<E> toImmutableSet()
	{
		return Collections.unmodifiableSet(toSet());
	}
}
