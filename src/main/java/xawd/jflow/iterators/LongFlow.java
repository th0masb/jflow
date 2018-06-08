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
	 * Applies a function elementwise to this {@linkplain LongFlow} to make new
	 * LongFlow.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new LongFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this LongFlow instance
	 *         in turn.
	 */
	LongFlow map(final LongUnaryOperator f);

	/**
	 * Applies a function elementwise to this {@linkplain LongFlow} to make new
	 * {@linkplain Flow}.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new Flow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this LongFlow instance
	 *         in turn.
	 */
	<E> Flow<E> mapToObject(LongFunction<E> f);

	/**
	 * Applies a function elementwise to this {@linkplain LongFlow} to make a new
	 * {@linkplain DoubleFlow}.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new DoubleFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this LongFlow instance
	 *         in turn.
	 */
	DoubleFlow mapToDouble(LongToDoubleFunction f);

	/**
	 * Applies a function elementwise to this {@linkplain LongFlow} to make a new
	 * {@linkplain IntFlow}.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new IntFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this LongFlow instance
	 *         in turn.
	 */
	IntFlow mapToInt(LongToIntFunction f);

	/**
	 * Combines this {@linkplain LongFlow} with another {@linkplain Iterator} to
	 * create a new Flow consisting of pairs of elements with the same index in
	 * their respective origins.
	 *
	 * @param <E>
	 *            The upper type bound on the parameter Iterator.
	 * @param other
	 *            The Iterator to zip this source Flow with.
	 *
	 * @return Denote this source LongFlow by {@code F} with the parameter Iterator
	 *         denoted by {@code I}. We return a new Flow instance {@code G} defined
	 *         by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<E> Flow<LongWith<E>> zipWith(final Iterator<? extends E> other);

	/**
	 * Combines this {@linkplain LongFlow} with another
	 * {@linkplain PrimitiveIterator.OfLong}} to create a new LongFlow consisting of
	 * pairs of elements with the same index in their respective origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfLong to zip this source LongFlow with.
	 *
	 * @return Denote this source LongFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfLong denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<LongPair> zipWith(final PrimitiveIterator.OfLong other);

	/**
	 * Combines this {@linkplain LongFlow} with another
	 * {@linkplain PrimitiveIterator.OfDouble}} to create a new Flow consisting of
	 * pairs of elements with the same index in their respective origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfDouble to zip this source LongFlow with.
	 *
	 * @return Denote this source LongFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfDouble denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<DoubleWithLong> zipWith(final PrimitiveIterator.OfDouble other);

	/**
	 * Combines this {@linkplain LongFlow} with another
	 * {@linkplain PrimitiveIterator.OfInt}} to create a new Flow consisting of
	 * pairs of elements with the same index in their respective origins.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfInt to zip this source LongFlow with.
	 *
	 * @return Denote this source LongFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfInt denoted by {@code I}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<IntWithLong> zipWith(final PrimitiveIterator.OfInt other);

	/**
	 * Combines this {@linkplain LongFlow} with another
	 * {@linkplain PrimitiveIterator.OfLong} via a two argument function to create a
	 * new LongFlow consisting of the images of pairs of elements with the same
	 * index in their origin.
	 *
	 * @param other
	 *            The PrimitiveIterator.OfLong to combine this source Flow with.
	 * @param f
	 *            The combining function.
	 *
	 * @return Denote this source LongFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfLong denoted by {@code I} and the combining
	 *         function by {@code f}. We return a new Flow instance {@code G}
	 *         defined by:
	 *         <ul>
	 *         <li>{@code G[j] = f(F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	LongFlow combineWith(PrimitiveIterator.OfLong other, LongBinaryOperator combiner);

	/**
	 * Creates a new {@linkplain Flow} by mapping each element in this source
	 * {@linkplain LongFlow} to a pair consisting of the element and the index it
	 * appears.
	 *
	 * @return Denote this source LongFlow by {@code F}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	Flow<IntWithLong> enumerate();

	/**
	 * Creates a new {@linkplain LongFlow} from this LongFlow by selecting elements
	 * with indices defined by the parameter index mapping.
	 *
	 * @param indexMap
	 *            A strictly monotonically increasing function {@code f: N -> N}
	 * @return Let {@code F} denote this source LongFlow, let {@code n = length(F)}
	 *         and denote the indexMap by {@code f}. Then this method returns a
	 *         LongFlow {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
	 *         </ul>
	 */
	LongFlow slice(IntUnaryOperator indexMap);

	/**
	 * Creates a new {@linkplain LongFlow} from this LongFlow by selecting the first
	 * n elements.
	 *
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote this source LongFlow. We return a LongFlow
	 *         consisting of the first {@code max(n, length(F))} elements of
	 *         {@code F}.
	 */
	LongFlow take(final int n);

	/**
	 * Creates a new {@linkplain LongFlow} from this LongFlow by selecting elements
	 * until an element fails the supplied test, the first failure is not selected.
	 *
	 * @param predicate
	 *            A {@link LongPredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a LongFlow consisting
	 *         of the first {@code n} elements of this source LongFlow. If no
	 *         element fails the predicate test then a copy of the source is
	 *         returned.
	 */
	LongFlow takeWhile(final LongPredicate predicate);

	/**
	 * Creates a new {@linkplain LongFlow} from this LongFlow by removing the first
	 * n elements.
	 *
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote this source LongFlow. We return a LongFlow
	 *         missing the first {@code min(n, length(F))} elements of {@code F}.
	 */
	LongFlow drop(final int n);

	/**
	 * Creates a new {@linkplain LongFlow} from this LongFlow by removing elements
	 * until an element fails the supplied test, the first failure is the first
	 * element of the result.
	 *
	 * @param predicate
	 *            A {@link LongPredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a LongFlow missing
	 *         {@code n} elements of this source LongFlow. If no element fails the
	 *         predicate test then a copy of the source is returned.
	 */
	LongFlow dropWhile(final LongPredicate predicate);

	/**
	 * Creates a new {@linkplain LongFlow} from this LongFlow by removing any
	 * element which fails the supplied predicate test.
	 *
	 * @param predicate
	 *            A {@link LongPredicate}.
	 * @return A LongFlow containing only those elements of this source LongFlow
	 *         which pass the test defined by the parameter predicate. The relative
	 *         ordering of elements is retained.
	 */
	LongFlow filter(final LongPredicate predicate);

	/**
	 * Creates a new {@linkplain LongFlow} from this LongFlow by adding each element
	 * of the supplied {@linkplain PrimitiveIterator.OfLong} to its end in order.
	 *
	 * @param other
	 *            A PrimitiveIterator.OfLong.
	 * @return a LongFlow consisting of the elements of this source LongFlow
	 *         followed by the elements of the parameter PrimitiveIterator.OfLong.
	 */
	LongFlow append(PrimitiveIterator.OfLong other);

	/**
	 * Creates a new {@linkplain LongFlow} from this LongFlow by adding each element
	 * of the supplied varargs array to its end in order.
	 *
	 * @param other
	 *            - A varargs long array
	 * @return an LongFlow consisting of the elements of the source LongFlow
	 *         followed by the elements in the parameter array.
	 */
	LongFlow append(long... other);

	/**
	 * Creates a new {@linkplain LongFlow} from this Flow by adding each element to
	 * the end of the supplied {@linkplain PrimitiveIterator.OfLong} in order.
	 *
	 * @param other
	 *            A PrimitiveIterator.OfLong.
	 * @return a LongFlow consisting of the elements of the parameter
	 *         PrimitiveIterator.OfLong followed by the elements of this source
	 *         LongFlow.
	 */
	LongFlow insert(PrimitiveIterator.OfLong other);

	/**
	 * Creates a new {@linkplain LongFlow} from this Flow by adding each element to
	 * the end of the supplied varargs array in order.
	 *
	 * @param other
	 *            - A varargs long array
	 * @return an LongFlow consisting of the elements in the parameter array
	 *         followed by the elements of the source LongFlow.
	 */
	LongFlow insert(long... other);

	/**
	 * Applies an accumulation operation to this {@linkplain LongFlow} to produce a
	 * new LongFlow.
	 *
	 * @param accumulator
	 *            The accumulation function.
	 * @return Let {@code F} denote this source LongFlow and {@code g} denote the
	 *         accumulation function. Then the LongFlow returned is of the form:
	 *         <ul>
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}</li>
	 *         </ul>
	 */
	LongFlow accumulate(LongBinaryOperator accumulator);

	/**
	 * Applies an accumulation operation to this {@linkplain LongFlow} to produce a
	 * new LongFlow.
	 *
	 * @param id
	 *            The identity element in the accumulation.
	 * @param accumulator
	 *            The accumulator function.
	 * @return Let {@code F} denote this source LongFlow and {@code g} denote the
	 *         accumulation function. Then the LongFlow returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	LongFlow accumulate(long id, LongBinaryOperator accumulator);

	/**
	 * Calculates the minimum value in this {@linkplain LongFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @return an {@link OptionalLong} wrapping the smallest element in this
	 *         LongFlow or nothing if the iteration is empty.
	 */
	OptionalLong min();

	/**
	 * Calculates the minimum value in this {@linkplain LongFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return the smallest element in this LongFlow or the default value if the
	 *         iteration is empty.
	 */
	long min(long defaultValue);

	/**
	 * Calculates the minimum element in this {@linkplain LongFlow} by an embedding
	 * into the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param defaultValue
	 *            The value returned if this LongFlow is empty.
	 *
	 * @param key
	 *            A function mapping the elements of this LongFlow into the real
	 *            numbers.
	 * @return The element of this LongFlow whose image under the key mapping is the
	 *         minimum among all images. A parameter default value is returned if
	 *         the source is empty. NaN images are ignored.
	 */
	long minByKey(long defaultValue, final LongToDoubleFunction key);

	/**
	 * Calculates the minimum element in this {@linkplain LongFlow} by an embedding
	 * into the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param key
	 *            A function mapping the elements of this LongFlow into the real
	 *            numbers.
	 * @return The element of this LongFlow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	OptionalLong minByKey(final LongToDoubleFunction key);

	/**
	 * Calculates the maximum value in this {@linkplain LongFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @return an {@link OptionalLong} wrapping the largest element in this LongFlow
	 *         or nothing if the iteration is empty.
	 */
	OptionalLong max();

	/**
	 * Calculates the maximum value in this {@linkplain LongFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return The largest element in this LongFlow or the default value if the
	 *         iteration is empty.
	 */
	long max(long defaultValue);

	/**
	 * Calculates the maximum element in this {@linkplain LongFlow} by an embedding
	 * into the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param defaultValue
	 *            The value returned if this LongFlow is empty.
	 *
	 * @param key
	 *            A function mapping the elements of this LongFlow into the real
	 *            numbers.
	 * @return The element of this LongFlow whose image under the key mapping is the
	 *         maximum among all images. A parameter default value is returned if
	 *         the source is empty. NaN images are ignored.
	 */
	long maxByKey(long defaultValue, final LongToDoubleFunction key);

	/**
	 * Calculates the maximum element in this {@linkplain LongFlow} by an embedding
	 * into the real numbers.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param key
	 *            A function mapping the elements of this LongFlow into the real
	 *            numbers.
	 * @return The element of this LongFlow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	OptionalLong maxByKey(final LongToDoubleFunction key);

	/**
	 * Checks whether every element in this {@linkplain LongFlow} is the same.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @return True is every element of this LongFlow is equal, false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * Checks whether every element in this {@linkplain LongFlow} passes the supplied
	 * {@linkplain LongPredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(final LongPredicate predicate);

	/**
	 * Checks whether any element in this {@linkplain LongFlow} passes the supplied
	 * {@linkplain LongPredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(final LongPredicate predicate);

	/**
	 * Checks whether every element in this {@linkplain LongFlow} fails the supplied
	 * {@linkplain LongPredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * LongFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(final LongPredicate predicate);

	/**
	 * Partitions the elements of this {@linkplain LongFlow} on whether they pass the
	 * supplied {@linkplain LongPredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this LongFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return A partition of the cached elements split into two arrays on whether
	 *         they passed or failed the parameter predicate.
	 */
	LongPredicatePartition partition(LongPredicate predicate);

	/**
	 * Reduces this {@linkplain LongFlow} to a single value via some reduction function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this LongFlow.
	 *
	 * @param id
	 *            The identity of the reduction operation
	 * @param reducer
	 *            The reduction function
	 * @return If we denote this source LongFlow by {@code F}, the length of {@code F}
	 *         by {@code n} and the reduction function by {@code f} then the result
	 *         is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	long reduce(long id, LongBinaryOperator reducer);

	/**
	 * Reduces this {@linkplain LongFlow} to a single value via some reduction function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this LongFlow.
	 *
	 * @param id
	 *            The identity of the reduction operation
	 * @param reducer
	 *            The reduction function
	 * @return Let us denote this source LongFlow by {@code F}, the length of {@code F}
	 *         by {@code n} and the reduction function by {@code f}. If
	 *         {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalLong reduce(LongBinaryOperator reducer);

	/**
	 * Counts the number of elements in this {@linkplain LongFlow}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this LongFlow.
	 *
	 * @return The number of elements in this LongFlow.
	 */
	long count();

	/**
	 * Caches the values in this {@linkplain LongFlow} to an array.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link LongFlow}.
	 *
	 * @return A long array containing all elements of this LongFlow with their
	 *         ordering retained.
	 */
	long[] toArray();

	/**
	 * Builds a {@linkplain Map} using the elements in this {@linkplain LongFlow} via
	 * two supplied functions.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this LongFlow.
	 *
	 * @param <K>
	 *            The type of the keys in the created mapping.
	 * @param <V>
	 *            The type of the values in the created mapping.
	 * @param keyMapper
	 *            A function mapping longs to elements of the key
	 *            type.
	 * @param valueMapper
	 *            A function mapping longs to elements of the value
	 *            type.
	 *
	 * @throws IllegalStateException
	 *             If two elements of this LongFlow map to the same key.
	 *
	 * @return A Map instance whose key-value pairs have a 1-to-1 correspondence
	 *         with the elements in this source LongFlow. More specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 *         an element of this source LongFlow, say {@code e}, is associated to the
	 *         key value pair {@code (k(e), v(e))}.
	 */
	<K, V> Map<K, V> toMap(final LongFunction<K> keyMapper, final LongFunction<V> valueMapper);

	/**
	 * Groups elements in this {@linkplain LongFlow} via their image under some supplied
	 * classification function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this LongFlow.
	 *
	 * @param <K>
	 *            The type of the keys in the final grouping map.
	 *
	 * @param classifier
	 *            A function defining the different groups of elements.
	 * @return A Map instance whose keys partition the elements of this source LongFlow
	 *         via the classification function. Elements in this source LongFlow who
	 *         have equal (under .equals() contract) images under the classification
	 *         function are grouped together in a long array accessed by their
	 *         shared classification key.
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
