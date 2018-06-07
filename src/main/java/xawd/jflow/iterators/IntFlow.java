package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import xawd.jflow.iterators.misc.IntPair;
import xawd.jflow.iterators.misc.IntPredicatePartition;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.IntWithDouble;
import xawd.jflow.iterators.misc.IntWithLong;

/**
 * An {@link IntFlow} instance is an {@link PrimitiveIterator.OfInt} with lots
 * of extra functionality in the style of the {@link IntStream} interface. There
 * are methods inspired by other languages too, namely Python and Haskell.
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface IntFlow extends PrototypeIntFlow
{
	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link IntFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         IntFlow instance in turn.
	 */
	IntFlow map(final IntUnaryOperator f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link Flow} instance whose elements are obtained by applying
	 *         the parameter mapping function to each element of this
	 *         {@link IntFlow} instance in turn.
	 */
	<E> Flow<E> mapToObject(IntFunction<E> f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link DoubleFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link IntFlow} instance in turn.
	 */
	DoubleFlow mapToDouble(IntToDoubleFunction f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link LongFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link IntFlow} instance in turn.
	 */
	LongFlow mapToLong(IntToLongFunction f);

	/**
	 * @param <E>
	 *            - The upper type bound on the parameter {@link Iterator}.
	 * @param other
	 *            - The Iterator to zip the source {@link IntFlow} with.
	 *
	 * @return Denote the source IntFlow by {@code F} with the parameter Iterator
	 *         denoted by {@code I}. We return a new {@link Flow} instance {@code G}
	 *         defined by:
	 *
	 *         <li>{@code G[j] = (F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	<E> Flow<IntWith<E>> zipWith(final Iterator<? extends E> other);

	/**
	 * @param other
	 *            - The {@link PrimitiveIterator.OfInt} to zip the source
	 *            {@link IntFlow} with.
	 *
	 * @return Denote the source IntFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfInt denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *
	 *         <li>{@code G[j] = (F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	Flow<IntPair> zipWith(final PrimitiveIterator.OfInt other);

	/**
	 * @param other
	 *            - The {@link PrimitiveIterator.OfDouble} to zip the source
	 *            {@link IntFlow} with.
	 *
	 * @return Denote the source IntFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfDouble denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *
	 *         <li>{@code G[j] = (F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	Flow<IntWithDouble> zipWith(final PrimitiveIterator.OfDouble other);

	/**
	 * @param other
	 *            - The {@link PrimitiveIterator.OfLong} to zip the source
	 *            {@link IntFlow} with.
	 *
	 * @return Denote the source IntFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfLong denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *
	 *         <li>{@code G[j] = (F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	Flow<IntWithLong> zipWith(final PrimitiveIterator.OfLong other);

	/**
	 * @param other
	 *            - The {@link PrimitiveIterator.OfInt} to combine the source
	 *            {@link IntFlow} with.
	 * @param combiner
	 *            - The combining function.
	 *
	 * @return Denote the source IntFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfInt denoted by {@code I} and the combining
	 *         function by {@code f}. We return a new {@link Flow} instance
	 *         {@code G} defined by:
	 *
	 *         <li>{@code G[j] = f(F[j], I[j])}
	 *         <li>{@code length(G) = min(length(F), length(I))}
	 */
	IntFlow combineWith(PrimitiveIterator.OfInt other, IntBinaryOperator combiner);

	/**
	 * @return Denote the source IntFlow by {@code F}. We return a new {@link Flow}
	 *         instance {@code G} defined by:
	 *
	 *         <li>{@code G[j] = (F[j], j)}
	 *         <li>{@code length(G) = length(F)}
	 */
	Flow<IntPair> enumerate();

	/**
	 * @param indexMap
	 *            - A strictly monotonically increasing function {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link IntFlow}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns an IntFlow {@code G} given by:
	 *
	 *         <li>{@code G[i] = F(f(i))}
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) < length(F))}
	 */
	IntFlow slice(IntUnaryOperator indexMap);

	/**
	 * @param n
	 *            - A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote the source {@link IntFlow}. We return an IntFlow
	 *         consisting of the first {@code max(n, length(F))} elements of
	 *         {@code F}.
	 */
	IntFlow take(final int n);

	/**
	 * @param predicate
	 *            - An {@link IntPredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns an {@link IntFlow}
	 *         consisting of the first {@code n} elements of the source IntFlow. If
	 *         no element fails the predicate test then a copy of the source is
	 *         returned.
	 */
	IntFlow takeWhile(final IntPredicate predicate);

	/**
	 * @param n
	 *            - A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote the source {@link IntFlow}. We return a IntFlow
	 *         missing the first {@code min(n, length(F))} elements of {@code F}.
	 */
	IntFlow drop(final int n);

	/**
	 * @param predicate
	 *            - An {@link IntPredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a {@link IntFlow}
	 *         missing {@code n} elements of the source IntFlow. If no element fails
	 *         the predicate test then a copy of the source is returned.
	 */
	IntFlow dropWhile(final IntPredicate predicate);

	/**
	 * @param predicate
	 *            - An {@link IntPredicate}.
	 * @return an {@link IntFlow} containing only those elements of the source
	 *         IntFlow which pass the test defined by the parameter predicate. The
	 *         relative ordering of elements is retained.
	 */
	IntFlow filter(final IntPredicate predicate);

	/**
	 * @param other
	 *            - A {@link PrimitiveIterator.OfInt}.
	 * @return an {@link IntFlow} consisting of the elements of the source IntFlow
	 *         followed by the elements of the parameter PrimitiveIterator.OfInt.
	 */
	IntFlow append(PrimitiveIterator.OfInt other);

	/**
	 * @param other
	 *            - A varargs int array
	 * @return an {@link IntFlow} consisting of the elements of the source IntFlow
	 *         followed by the elements in the parameter array.
	 */
	IntFlow append(int... other);

	/**
	 * @param other
	 *            - A {@link PrimitiveIterator.OfInt}.
	 * @return an {@link IntFlow} consisting of the elements of the parameter
	 *         PrimitiveIterator.OfInt followed by the elements of the source
	 *         IntFlow.
	 */
	IntFlow insert(PrimitiveIterator.OfInt other);

	/**
	 * @param other
	 *            - A varargs int array
	 * @return an {@link IntFlow} consisting of the elements in the parameter array
	 *         followed by the elements of the source IntFlow.
	 */
	IntFlow insert(int... other);

	/**
	 * @param accumulator
	 *            - the accumulation function.
	 * @return Let {@code F} denote the source {@link IntFlow} and {@code g} denote
	 *         the accumulation function. Then the IntFlow returned is of the form:
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}
	 */
	IntFlow accumulate(IntBinaryOperator accumulator);

	/**
	 * @param id
	 *            - the identity element in the accumulation.
	 * @param accumulator
	 *            - the accumulator function.
	 * @return Let {@code F} denote the source {@link IntFlow} and {@code g} denote
	 *         the accumulation function. Then the IntFlow returned is of the form:
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}
	 */
	IntFlow accumulate(int id, IntBinaryOperator accumulator);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @return an {@link OptionalInt} wrapping the smallest element in this IntFlow or
	 *         nothing if the iteration is empty.
	 */
	OptionalInt min();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return the smallest element in this IntFlow or the default value if the
	 *         iteration is empty.
	 */
	int min(int defaultValue);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param defaultValue
	 *            - The value to be returned if the source is empty.
	 * @param key
	 *            - a function mapping the elements of this IntFlow into the real
	 *            numbers.
	 * @return the element of this IntFlow whose image under the key mapping is the
	 *         minimum among all images. The parameter default is returned if the
	 *         source is empty. NaN images are ignored.
	 */
	int minByKey(int defaultValue, final IntToDoubleFunction key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param key
	 *            - a function mapping the elements of this IntFlow into the real
	 *            numbers.
	 * @return the element of this IntFlow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	OptionalInt minByKey(final IntToDoubleFunction key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param key
	 *            - a function mapping the elements of this IntFlow to some data
	 *            type with an ordering.
	 * @return the element of this IntFlow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 */
	<C extends Comparable<C>> OptionalInt minByObjectKey(final IntFunction<C> key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @return an {@link OptionalInt} wrapping the largest element in this IntFlow or
	 *         nothing if the iteration is empty.
	 */
	OptionalInt max();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return the largest element in this IntFlow or the default value if the
	 *         iteration is empty.
	 */
	int max(int defaultValue);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param defaultValue
	 *            - The value to be returned if the source is empty.
	 * @param key
	 *            - a function mapping the elements of this IntFlow into the real
	 *            numbers.
	 * @return the element of this IntFlow whose image under the key mapping is the
	 *         maximum among all images. The parameter default is returned if the
	 *         source is empty. NaN images are ignored.
	 */
	int maxByKey(int defaultValue, final IntToDoubleFunction key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param key
	 *            - a function mapping the elements of this IntFlow into the real
	 *            numbers.
	 * @return the element of this IntFlow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 *         NaN images are ignored.
	 */
	OptionalInt maxByKey(final IntToDoubleFunction key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param key
	 *            - a function mapping the elements of this IntFlow to some data
	 *            type with an ordering.
	 * @return the element of this IntFlow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 */
	<C extends Comparable<C>> OptionalInt maxByObjectKey(final IntFunction<C> key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @return true is every element of this IntFlow is equal, false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param predicate
	 *            - An {@link IntPredicate}.
	 * @return true if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(final IntPredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param predicate
	 *            - An {@link IntPredicate}.
	 * @return true if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(final IntPredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param predicate
	 *            - An {@link IntPredicate}.
	 * @return true if all elements fail the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(final IntPredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param predicate
	 *            - An {@link IntPredicate}.
	 * @return a partition of the cached elements split into two arrays on whether
	 *         they passed or failed the parameter predicate.
	 */
	IntPredicatePartition partition(IntPredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param id
	 *            - the identity of the reduction operation
	 * @param reducer
	 *            - the reduction function
	 * @return If we denote the source IntFlow by {@code F}, the length of {@code F}
	 *         by {@code n} and the reduction function by {@code f} then the result
	 *         is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	int reduce(int id, IntBinaryOperator reducer);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param reducer
	 *            - the reduction function.
	 * @return Let us denote the source IntFlow by {@code F}, the length of
	 *         {@code F} by {@code n} and the reduction function by {@code f}. If
	 *         {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalInt reduce(IntBinaryOperator reducer);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @return the number of elements in this IntFlow.
	 */
	long count();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @return an int array containing all elements of this IntFlow with their
	 *         ordering retained.
	 */
	int[] toArray();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param <K>
	 *            the type of the keys in the created mapping.
	 * @param <V>
	 *            the type of the values in the created mapping.
	 * @param keyMapper
	 *            - a function mapping elements of this IntFlow to elements of the
	 *            key type.
	 * @param valueMapper
	 *            - a function mapping elements of this IntFlow to elements of the
	 *            value type.
	 * @return a {@link Map} instance whose key-value pairs have a 1-to-1
	 *         correspondence with the elements in the source IntFlow. More
	 *         specifically if:
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li> an element of
	 *         the source flow, say {@code e}, is associated to the key value pair
	 *         {@code (k(e), v(e))}.<br>
	 *         <br>
	 *         If two different elements of the source IntFlow map to the same key
	 *         then an {@link IllegalStateException} will be thrown.
	 */
	<K, V> Map<K, V> toMap(final IntFunction<K> keyMapper, final IntFunction<V> valueMapper);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link IntFlow}.
	 *
	 * @param <K>
	 *            - the type of the keys in the final grouping map.
	 *
	 * @param classifier
	 *            - a function defining the different groups of elements.
	 * @return a {@link Map} instance whose keys partition the elements of the
	 *         source IntFlow via the classification function. Elements in the
	 *         source IntFlow who have equal (under .equals() contract) images under
	 *         the classification function are grouped together in an array accessed
	 *         by their shared classification key.
	 */
	<K> Map<K, int[]> groupBy(final IntFunction<K> classifier);

	/**
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this {@link IntFlow}.
	 *
	 * A convenience method for applying a global function onto this IntFlow.
	 *
	 * @param builder
	 *            - a function whose input encompasses IntFlow instances of this
	 *            element type.
	 * @return the output of the supplied function applied to this IntFlow.
	 */
	default <C> C build(final Function<? super IntFlow, C> builder)
	{
		return builder.apply(this);
	}

	/**
	 * @return a copy of this source {@link IntFlow} as a {@link Flow} of boxed
	 *         {@link Integer} instances.
	 */
	default Flow<Integer> boxed()
	{
		return mapToObject(x -> x);
	}
}
