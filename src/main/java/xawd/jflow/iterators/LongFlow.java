package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.stream.IntStream;

import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithLong;
import xawd.jflow.iterators.misc.LongPair;
import xawd.jflow.iterators.misc.LongPredicatePartition;
import xawd.jflow.iterators.misc.LongWith;

/**
 * An {@link LongFlow} instance is an {@link PrimitiveIterator.OfLong} with lots
 * of extra functionality in the style of the {@link IntStream} interface. There
 * are methods inspired by other languages too, namely Python and Haskell.
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface LongFlow extends PrototypeLongFlow
{
	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link LongFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link LongFlow} instance in turn.
	 */
	LongFlow map(final LongUnaryOperator f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link Flow} instance whose elements are obtained by applying
	 *         the parameter mapping function to each element of this
	 *         {@link LongFlow} instance in turn.
	 */
	<E> Flow<E> mapToObject(LongFunction<E> f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link DoubleFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link LongFlow} instance in turn.
	 */
	DoubleFlow mapToDouble(LongToDoubleFunction f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link IntFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link LongFlow} instance in turn.
	 */
	IntFlow mapToInt(LongToIntFunction f);

	/**
	 * @param <E>
	 *            - The upper type bound on the parameter {@link Iterator}.
	 * @param other
	 *            - The Iterator to zip the source {@link LongFlow} with.
	 *
	 * @return Denote the source LongFlow by {@code F} with the parameter Iterator
	 *         denoted by {@code I}. We return a new {@link Flow} instance {@code G}
	 *         defined by:
	 *
	 *         <li>{@code G[j] = (F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	<E> Flow<LongWith<E>> zipWith(final Iterator<? extends E> other);

	/**
	 * @param other
	 *            - The {@link PrimitiveIterator.OfLong} to zip the source
	 *            {@link LongFlow} with.
	 *
	 * @return Denote the source LongFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfLong denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *
	 *         <li>{@code G[j] = (F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	Flow<LongPair> zipWith(final PrimitiveIterator.OfLong other);

	/**
	 * @param other
	 *            - The {@link PrimitiveIterator.OfDouble} to zip the source
	 *            {@link LongFlow} with.
	 *
	 * @return Denote the source LongFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfDouble denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *
	 *         <li>{@code G[j] = (F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	Flow<DoubleWithLong> zipWith(final PrimitiveIterator.OfDouble other);

	/**
	 * @param other
	 *            - The {@link PrimitiveIterator.OfInt} to zip the source
	 *            {@link LongFlow} with.
	 *
	 * @return Denote the source LongFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfInt denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *
	 *         <li>{@code G[j] = (F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	Flow<IntWithLong> zipWith(final PrimitiveIterator.OfInt other);

	/**
	 * @param other
	 *            - The {@link PrimitiveIterator.OfLong} to combine the source
	 *            {@link LongFlow} with.
	 * @param combiner
	 *            - The combining function.
	 *
	 * @return Denote the source LongFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfLong denoted by {@code I} and the combining
	 *         function by {@code f}. We return a new {@link LongFlow} instance
	 *         {@code G} defined by:
	 *
	 *         <li>{@code G[j] = f(F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	LongFlow combineWith(PrimitiveIterator.OfLong other, LongBinaryOperator combiner);

	/**
	 * @return Denote the source LongFlow by {@code F}. We return a new {@link Flow}
	 *         instance {@code G} defined by:
	 *
	 *         <li>{@code G[j] = (F[j], j)}
	 *         <li>{@code length(G) = length(F)}
	 */
	Flow<IntWithLong> enumerate();

	/**
	 * @param indexMap
	 *            - A strictly monotonically increasing function {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link LongFlow}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns an LongFlow {@code G} given by:
	 *
	 *         <li>{@code G[i] = F(f(i))}
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) < length(F))}
	 */
	LongFlow slice(IntUnaryOperator indexMap);

	/**
	 * @param n
	 *            - A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote the source {@link LongFlow}. We return a
	 *         LongFlow consisting of the first {@code max(n, length(F))} elements
	 *         of {@code F}.
	 */
	LongFlow take(final int n);

	/**
	 * @param predicate
	 *            - A {@link LongPredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns an {@link LongFlow}
	 *         consisting of the first {@code n} elements of the source LongFlow. If
	 *         no element fails the predicate test then a copy of the source is
	 *         returned.
	 */
	LongFlow takeWhile(final LongPredicate predicate);

	/**
	 * @param n
	 *            - A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote the source {@link LongFlow}. We return a
	 *         LongFlow missing the first {@code min(n, length(F))} elements of
	 *         {@code F}.
	 */
	LongFlow drop(final int n);

	/**
	 * @param predicate
	 *            - A {@link LongPredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a {@link LongFlow}
	 *         missing {@code n} elements of the source LongFlow. If no element
	 *         fails the predicate test then a copy of the source is returned.
	 */
	LongFlow dropWhile(final LongPredicate predicate);

	/**
	 * @param predicate
	 *            - A {@link LongPredicate}.
	 * @return an {@link LongFlow} containing only those elements of the source
	 *         LongFlow which pass the test defined by the parameter predicate. The
	 *         relative ordering of elements is retained.
	 */
	LongFlow filter(final LongPredicate predicate);

	/**
	 * @param other
	 *            - A {@link PrimitiveIterator.OfLong}.
	 * @return an {@link LongFlow} consisting of the elements of the source LongFlow
	 *         followed by the elements of the parameter PrimitiveIterator.OfLong.
	 */
	LongFlow append(PrimitiveIterator.OfLong other);

	/**
	 * @param other
	 *            - A varargs long array
	 * @return an {@link LongFlow} consisting of the elements of the source LongFlow
	 *         followed by the elements in the parameter array.
	 */
	LongFlow append(long... other);

	/**
	 * @param other
	 *            - A {@link PrimitiveIterator.OfLong}.
	 * @return an {@link LongFlow} consisting of the elements of the parameter
	 *         PrimitiveIterator.OfLong followed by the elements of the source
	 *         LongFlow.
	 */
	LongFlow insert(PrimitiveIterator.OfLong other);

	/**
	 * @param other
	 *            - A varargs long array
	 * @return an {@link LongFlow} consisting of the elements in the parameter array
	 *         followed by the elements of the source LongFlow.
	 */
	LongFlow insert(long... other);

	/**
	 * @param accumulator
	 *            - the accumulation function.
	 * @return Let {@code F} denote the source {@link LongFlow} and {@code g} denote
	 *         the accumulation function. Then the LongFlow returned is of the form:
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}
	 */
	LongFlow accumulate(LongBinaryOperator accumulator);

	/**
	 * @param id
	 *            - the identity element in the accumulation.
	 * @param accumulator
	 *            - the accumulator function.
	 * @return Let {@code F} denote the source {@link LongFlow} and {@code g} denote
	 *         the accumulation function. Then the LongFlow returned is of the form:
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}
	 */
	LongFlow accumulate(long id, LongBinaryOperator accumulator);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @return an {@link OptionalLong} wrapping the smallest element in this
	 *         LongFlow or nothing if the iteration is empty.
	 */
	OptionalLong min();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return the smallest element in this LongFlow or the default value if the
	 *         iteration is empty.
	 */
	long min(long defaultValue);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param defaultValue
	 *            - The value to be returned if the source is empty.
	 * @param key
	 *            - a function mapping the elements of this LongFlow into the real
	 *            numbers.
	 * @return the element of this LongFlow whose image under the key mapping is the
	 *         minimum among all images. The parameter default is returned if the
	 *         source is empty. NaN images are ignored.
	 */
	long minByKey(long defaultValue, final LongToDoubleFunction key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param key
	 *            - a function mapping the elements of this LongFlow into the real
	 *            numbers.
	 * @return the element of this LongFlow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	OptionalLong minByKey(final LongToDoubleFunction key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @return an {@link OptionalLong} wrapping the largest element in this LongFlow
	 *         or nothing if the iteration is empty.
	 */
	OptionalLong max();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return the largest element in this LongFlow or the default value if the
	 *         iteration is empty.
	 */
	long max(long defaultValue);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param defaultValue
	 *            - The value to be returned if the source is empty.
	 * @param key
	 *            - a function mapping the elements of this LongFlow into the real
	 *            numbers.
	 * @return the element of this LongFlow whose image under the key mapping is the
	 *         maximum among all images. The parameter default is returned if the
	 *         source is empty. NaN images are ignored.
	 */
	long maxByKey(long defaultValue, final LongToDoubleFunction key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param key
	 *            - a function mapping the elements of this LongFlow into the real
	 *            numbers.
	 * @return the element of this LongFlow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	OptionalLong maxByKey(final LongToDoubleFunction key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @return true is every element of this LongFlow is equal, false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param predicate
	 *            - A {@link LongPredicate}.
	 * @return true if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(final LongPredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param predicate
	 *            - An {@link LongPredicate}.
	 * @return true if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(final LongPredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param predicate
	 *            - A {@link LongPredicate}.
	 * @return true if all elements fail the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(final LongPredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param predicate
	 *            - A {@link LongPredicate}.
	 * @return a partition of the cached elements split into two arrays on whether
	 *         they passed or failed the parameter predicate.
	 */
	LongPredicatePartition partition(LongPredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param id
	 *            - the identity of the reduction operation
	 * @param reducer
	 *            - the reduction function
	 * @return If we denote the source LongFlow by {@code F}, the length of {@code F}
	 *         by {@code n} and the reduction function by {@code f} then the result
	 *         is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	long reduce(long id, LongBinaryOperator reducer);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param reducer
	 *            - the reduction function.
	 * @return Let us denote the source LongFlow by {@code F}, the length of
	 *         {@code F} by {@code n} and the reduction function by {@code f}. If
	 *         {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalLong reduce(LongBinaryOperator reducer);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @return the number of elements in this LongFlow.
	 */
	long count();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @return an long array containing all elements of this LongFlow with their
	 *         ordering retained.
	 */
	long[] toArray();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param <K>
	 *            the type of the keys in the created mapping.
	 * @param <V>
	 *            the type of the values in the created mapping.
	 * @param keyMapper
	 *            - a function mapping elements of this LongFlow to elements of the
	 *            key type.
	 * @param valueMapper
	 *            - a function mapping elements of this LongFlow to elements of the
	 *            value type.
	 * @return a {@link Map} instance whose key-value pairs have a 1-to-1
	 *         correspondence with the elements in the source LongFlow. More
	 *         specifically if:
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li> an element of
	 *         the source LongFlow, say {@code e}, is associated to the key value pair
	 *         {@code (k(e), v(e))}.<br>
	 *         <br>
	 *         If two different elements of the source LongFlow map to the same key
	 *         then an {@link IllegalStateException} will be thrown.
	 */
	<K, V> Map<K, V> toMap(final LongFunction<K> keyMapper, final LongFunction<V> valueMapper);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @param <K>
	 *            - the type of the keys in the final grouping map.
	 *
	 * @param classifier
	 *            - a function defining the different groups of elements.
	 * @return a {@link Map} instance whose keys partition the elements of the
	 *         source LongFlow via the classification function. Elements in the
	 *         source LongFlow who have equal (under .equals() contract) images under
	 *         the classification function are grouped together in an array accessed
	 *         by their shared classification key.
	 */
	<K> Map<K, long[]> groupBy(final LongFunction<K> classifier);

	/**
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this {@link LongFlow}.
	 *
	 * A convenience method for applying a global function onto this LongFlow.
	 *
	 * @param builder
	 *            - a function whose input encompasses LongFlow instances of this
	 *            element type.
	 * @return the output of the supplied function applied to this LongFlow.
	 */
	default <C> C build(final Function<? super LongFlow, C> builder)
	{
		return builder.apply(this);
	}

	/**
	 * @return a copy of this source {@link LongFlow} as a {@link Flow} of boxed
	 *         {@link Long} instances.
	 */
	default Flow<Long> boxed()
	{
		return mapToObject(x -> x);
	}
}
