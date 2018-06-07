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

import xawd.jflow.iterators.construction.Iterate;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.LongWith;
import xawd.jflow.iterators.misc.Pair;
import xawd.jflow.iterators.misc.PredicatePartition;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface Flow<E> extends SkippableIterator<E>
{
	default <R> Flow<R> pairFold(final BiFunction<? super E, ? super E, R> foldFunction)
	{
		throw new RuntimeException();
	}

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@linkplain Flow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@linkplain Flow} instance in turn.
	 */
	<R> Flow<R> map(final Function<? super E, R> f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@linkplain IntFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@linkplain Flow} instance in turn.
	 */
	IntFlow mapToInt(ToIntFunction<? super E> f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@linkplain DoubleFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@linkplain Flow} instance in turn.
	 */
	DoubleFlow mapToDouble(ToDoubleFunction<? super E> f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@linkplain LongFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@linkplain Flow} instance in turn.
	 */
	LongFlow mapToLong(ToLongFunction<? super E> f);

	/**
	 * @param mapping
	 *            - A function taking elements to instances of {@linkplain Flow}
	 * @return a {@linkplain Flow} obtained by applying the mapping function to each
	 *         element in turn and sequentially concatenating the results.
	 */
	<R> Flow<R> flatten(Function<? super E, ? extends Flow<R>> mapping);

	/**
	 * @param mapping
	 *            - A function taking elements to instances of {@linkplain IntFlow}
	 * @return a {@linkplain IntFlow} obtained by applying the mapping function to
	 *         each element in turn and sequentially concatenating the results.
	 */
	IntFlow flattenToInts(Function<? super E, ? extends IntFlow> mapping);

	/**
	 * @param mapping
	 *            - A function taking elements to instances of {@linkplain LongFlow}
	 * @return a {@linkplain LongFlow} obtained by applying the mapping function to
	 *         each element in turn and sequentially concatenating the results.
	 */
	LongFlow flattenToLongs(Function<? super E, ? extends LongFlow> mapping);

	/**
	 * @param mapping
	 *            - A function taking elements to instances of
	 *            {@linkplain DoubleFlow}
	 * @return a {@linkplain DoubleFlow} obtained by applying the mapping function
	 *         to each element in turn and sequentially concatenating the results.
	 */
	DoubleFlow flattenToDoubles(Function<? super E, ? extends DoubleFlow> mapping);

	/**
	 * Zips this {@linkplain Flow} {@code F} with the parameter
	 * {@linkplain Iterator} {@code I} producing a new {@linkplain Flow} instance
	 * {@code G} given by:<br>
	 * <br>
	 * {@code G[j] = (F[j], I[j])} <br>
	 * {@code length(G) = min(length(F), length(I))}
	 *
	 * @param <R>
	 *            - The upper type bound on the iterator to zip this flow with.
	 * @param other
	 *            - The {@linkplain Iterator} to zip with.
	 * @return the resulting {@linkplain Flow} described above.
	 */
	<R> Flow<Pair<E, R>> zipWith(final Iterator<? extends R> other);

	/**
	 * Zips this {@linkplain Flow} {@code F} with the parameter
	 * {@linkplain PrimitiveIterator.OfInt} {@code I} producing a new
	 * {@linkplain Flow} instance {@code G} given by:<br>
	 * <br>
	 * {@code G[j] = (F[j], I[j])} <br>
	 * {@code length(G) = min(length(F), length(I))}
	 *
	 * @param other
	 *            - The {@linkplain PrimitiveIterator.OfInt} to zip with.
	 * @return the resulting {@linkplain Flow} described above.
	 */
	Flow<IntWith<E>> zipWith(final PrimitiveIterator.OfInt other);

	/**
	 * Zips this {@linkplain Flow} {@code F} with the parameter
	 * {@linkplain PrimitiveIterator.OfDouble} {@code I} producing a new
	 * {@linkplain Flow} instance {@code G} given by:<br>
	 * <br>
	 * {@code G[j] = (F[j], I[j])} <br>
	 * {@code length(G) = min(length(F), length(I))}
	 *
	 * @param other
	 *            - The {@linkplain PrimitiveIterator.OfDouble} to zip with.
	 * @return the resulting {@linkplain Flow} described above.
	 */
	Flow<DoubleWith<E>> zipWith(final PrimitiveIterator.OfDouble other);

	/**
	 * Zips this {@linkplain Flow} {@code F} with the parameter
	 * {@linkplain PrimitiveIterator.OfLong} {@code I} producing a new
	 * {@linkplain Flow} instance {@code G} given by:<br>
	 * <br>
	 * {@code G[j] = (F[j], I[j])} <br>
	 * {@code length(G) = min(length(F), length(I))}
	 *
	 * @param other
	 *            - The {@linkplain PrimitiveIterator.OfLong} to zip with.
	 * @return the resulting {@linkplain Flow} described above.
	 */
	Flow<LongWith<E>> zipWith(final PrimitiveIterator.OfLong other);

	/**
	 * Combines this {@linkplain Flow} {@code F} with the parameter
	 * {@linkplain PrimitiveIterator.OfLong} {@code I} producing a new
	 * {@linkplain Flow} instance {@code G} given by:<br>
	 * <br>
	 * {@code G[j] = f(F[j], I[j])} <br>
	 * {@code length(G) = min(length(F), length(I))}
	 *
	 * @param <E2>
	 *            - The upper type bound on the iterator to combine this flow with.
	 * @param <R>
	 *            - The target type of the combiner function.
	 * @param other
	 *            - The {@linkplain PrimitiveIterator.OfLong} to zip with.
	 * @param f
	 *            - The combining function
	 * @return the resulting {@linkplain Flow} described above.
	 */
	<E2, R> Flow<R> combineWith(final Iterator<? extends E2> other, final BiFunction<? super E, ? super E2, R> f);

	/**
	 * Zips this {@linkplain Flow} {@code F} with the natural numbers producing a
	 * new {@linkplain Flow} instance {@code G} given by:<br>
	 * <br>
	 * {@code G[j] = (F[j], j)} <br>
	 * {@code length(G) = length(F)}
	 *
	 * @param other
	 *            - The {@linkplain PrimitiveIterator.OfLong} to zip with.
	 * @return the resulting {@linkplain Flow} described above.
	 */
	Flow<IntWith<E>> enumerate();

	/**
	 * Let {@code F} denote this source flow and let {@code n = length(F)}. Then
	 * this method returns a Flow {@code G} whose i-th element is given by
	 * {@code F(f(i))} and whose length is equal to the last integer j such that
	 * {@code f(j) < n}.
	 *
	 * @param f
	 *            - A strictly monotonically increasing function {@code f: N -> N}
	 * @return the {@linkplain Flow} instance described above.
	 */
	Flow<E> slice(final IntUnaryOperator f);

	/**
	 * @param n
	 *            - a non-negative integer.
	 * @return a {@linkplain Flow} consisting of the first n elements of the source
	 *         flow.
	 */
	Flow<E> take(final int n);

	/**
	 * @param p
	 *            - a predicate applicable to the type of elements in this Flow.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a Flow consisting of
	 *         the first {@code n} elements of the source stream. If no element
	 *         fails the predicate test then a copy of the Flow is returned.
	 */
	Flow<E> takeWhile(final Predicate<? super E> p);

	/**
	 * @param n
	 *            - a non-negative integer.
	 * @return a {@linkplain Flow} missing the first n elements of the source flow.
	 */
	Flow<E> skip(final int n);

	/**
	 * @param p
	 *            - a predicate applicable to the type of elements in this Flow.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a Flow missing
	 *         {@code n} elements of the source stream. If no element fails the
	 *         predicate test then a copy of the Flow is returned.
	 */
	Flow<E> skipWhile(final Predicate<? super E> p);

	/**
	 * @param p
	 *            - a predicate applicable to the type of elements in this Flow.
	 * @return a Flow containing only those elements of the source flow which pass
	 *         the test defined by the parameter predicate. The relative ordering of
	 *         elements is retained.
	 */
	Flow<E> filter(final Predicate<? super E> p);

	/**
	 * @param other
	 *            - an Iterator containing elements of the same type as this source
	 *            flow
	 * @return a Flow consisting of the elements of the source Flow followed by the
	 *         elements of the parameter Iterator.
	 */
	Flow<E> append(Iterator<? extends E> other);

	/**
	 * @param other
	 *            - an Iterator containing elements of the same type as this source
	 *            flow
	 * @return a Flow consisting of the elements of the parameter Iterator followed
	 *         by the elements of the source Flow.
	 */
	Flow<E> insert(Iterator<? extends E> other);

	/**
	 * @param accumulator
	 *            - the accumulation function.
	 * @return Let {@code F} denote the source flow and {@code g} denote the
	 *         accumulation function. Then the Flow returned is of the form: <br>
	 *         <br>
	 *         {@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}
	 */
	Flow<E> accumulate(BinaryOperator<E> accumulator);

	/**
	 * @param id
	 *            - the identity element in the accumulation.
	 * @param accumulator
	 *            - the accumulator function.
	 * @return Let {@code F} denote the source flow and {@code g} denote the
	 *         accumulation function. Then the Flow returned is of the form: <br>
	 *         <br>
	 *         {@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}
	 */
	<R> Flow<R> accumulate(R id, BiFunction<R, E, R> accumulator);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param key
	 *            - a function mapping the elements of this flow into the real
	 *            numbers.
	 * @return the element of this Flow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	Optional<E> minByKey(final ToDoubleFunction<? super E> key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param key
	 *            - a function mapping the elements of this flow to some data type
	 *            with an ordering.
	 * @return the element of this Flow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 */
	<C extends Comparable<C>> Optional<E> minByObjectKey(final Function<? super E, C> key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param key
	 *            - a function mapping the elements of this flow into the real
	 *            numbers.
	 * @return the element of this Flow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	Optional<E> maxByKey(final ToDoubleFunction<? super E> key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param key
	 *            - a function mapping the elements of this flow to some data type
	 *            with an ordering.
	 * @return the element of this Flow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 */
	<C extends Comparable<C>> Optional<E> maxByObjectKey(final Function<? super E, C> key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @return true is every element of this Flow is equal (under .equals contract),
	 *         false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param predicate
	 *            - a predicate applicable to the type of elements in this Flow.
	 * @return true if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(final Predicate<? super E> predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param predicate
	 *            - a predicate applicable to the type of elements in this Flow.
	 * @return true if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(final Predicate<? super E> predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param predicate
	 *            - a predicate applicable to the type of elements in this Flow.
	 * @return true if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(final Predicate<? super E> predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param predicate
	 *            - a predicate applicable to the type of elements in this Flow.
	 * @return a partition of the cached elements split into two lists on whether
	 *         they passed or failed the parameter predicate.
	 */
	PredicatePartition<E> partition(Predicate<? super E> predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <R>
	 *            - the type of the reduction output.
	 * @param id
	 *            - the identity of the reduction operation
	 * @param reducer
	 *            - the reduction function
	 * @return If we denote the source flow by {@code F}, the length of {@code F} by
	 *         {@code n} and the reduction function by {@code f} then the result is
	 *         equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	<R> R reduce(R id, BiFunction<R, E, R> reducer);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param reducer
	 *            - the reduction function.
	 * @return Let us denote the source flow by {@code F}, the length of {@code F}
	 *         by {@code n} and the reduction function by {@code f}. If
	 *         {@code n == 0} we return nothing else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	Optional<E> reduce(BinaryOperator<E> reducer);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @return the number of elements in this Flow.
	 */
	long count();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <C>
	 *            the type of collection which is supplied and returned.
	 * @param collectionFactory
	 *            - a function supplying a mutable instance of {@link Collection}
	 * @return the collection obtained by calling the collection supply function
	 *         once and adding each element in this Flow to it
	 */
	<C extends Collection<E>> C toCollection(final Supplier<C> collectionFactory);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <K>
	 *            the type of the keys in the created mapping.
	 * @param <V>
	 *            the type of the values in the created mapping.
	 * @param keyMapper
	 *            - a function mapping elements of this Flow to elements of the key
	 *            type.
	 * @param valueMapper
	 *            - a function mapping elements of this Flow to elements of the
	 *            value type.
	 * @return a {@link Map} instance whose key-value pairs have a 1-to-1
	 *         correspondence with the elements in the source flow. More
	 *         specifically if:
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li> an element of
	 *         the source flow, say {@code e}, is associated to the key value pair
	 *         {@code (k(e), v(e))}.<br>
	 *         <br>
	 *         If two different elements of the source flow map to the same key then
	 *         an {@linkplain IllegalStateException} will be thrown.
	 */
	<K, V> Map<K, V> toMap(final Function<? super E, K> keyMapper, final Function<? super E, V> valueMapper);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this Flow.
	 *
	 * @param <K>
	 *            - the type of the keys in the final grouping map.
	 *
	 * @param classifier
	 *            - a function defining the different groups of elements.
	 * @return a {@link Map} instance whose keys partition the elements of the
	 *         source flow via the classification function. Elements in the source
	 *         flow who have equal (under .equals() contract) images under the
	 *         classification function are grouped together in a {@link List}
	 *         accessed by their shared classification key.
	 */
	<K> Map<K, List<E>> groupBy(final Function<? super E, K> classifier);

	/**
	 * A convenience method for safely casting elements in this Flow to an arbitrary
	 * type.
	 *
	 * @param <R>
	 *            - the target type
	 * @param klass
	 *            - a {@link Class} instance defining the target type
	 * @return an instance of {@link Flow} with element type given by the supplied
	 *         target type, containing only the elements of the source which are of
	 *         the target type.
	 */
	default <R> Flow<R> filterAndCastTo(final Class<R> klass)
	{
		return filter(klass::isInstance).map(klass::cast);
	}

	/**
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this Flow.
	 *
	 * A convenience method for applying a global function onto this Flow.
	 *
	 * @param builder
	 *            - a function whose input includes {@link Flow} instances of this
	 *            element type.
	 * @return the output of the supplied function applied to this flow.
	 */
	default <R> R build(final Function<? super Flow<E>, R> builder)
	{
		return builder.apply(this);
	}

	/**
	 * Convenience method for appending a single element onto the end of this flow.
	 *
	 * @param e
	 *            - the element to append
	 * @return a {@link Flow} consisting of the elements of the source flow followed
	 *         by the parameter element
	 */
	default Flow<E> append(final E e)
	{
		return append(Iterate.over(e));
	}

	/**
	 * Convenience method for inserting a single element into the beginning of this
	 * flow.
	 *
	 * @param e
	 *            - the element to insert.
	 * @return a {@link Flow} consisting of the parameter element followed by the
	 *         elements of the source flow
	 */
	default Flow<E> insert(final E e)
	{
		return append(Iterate.over(e));
	}

	default List<E> toList()
	{
		return toCollection(ArrayList::new);
	}

	default List<E> toImmutableList()
	{
		return Collections.unmodifiableList(toList());
	}

	default Set<E> toImmutableSet()
	{
		return Collections.unmodifiableSet(toSet());
	}

	default Set<E> toSet()
	{
		return toCollection(HashSet::new);
	}
}
