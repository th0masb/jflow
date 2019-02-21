package com.github.maumay.jflow.iterators;

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

import com.gihub.maumay.jflow.iterators.misc.LongPair;
import com.gihub.maumay.jflow.iterators.misc.LongWith;
import com.github.maumay.jflow.iterators.factories.Iter;

/**
 * <p>
 * An extension of {@link PrimitiveIterator.OfInt} with a multitude of extra
 * methods for piping and transforming sequential data streams. There are many
 * static factory methods for constructing instances of this interface in the
 * {@link Iter} class.
 * </p>
 * <p>
 * In general a good rule of thumb when using iterators is to avoid consuming
 * them with the {@link #nextLong()} method unless it is in the standard while
 * loop idiom. The consumption methods provided in this interface are usually
 * far more useful in general (unless you enjoy writing while loops and using
 * mutable collections in which case this library isn't for you).
 * </p>
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface EnhancedLongIterator extends SafeLongIterator
{
	/**
	 * Applies a function elementwise to this {@link EnhancedLongIterator} to make
	 * new {@link EnhancedLongIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link EnhancedLongIterator} instance whose elements are
	 *         obtained by applying the parameter mapping function to each element
	 *         of this {@link EnhancedLongIterator} instance in turn.
	 */
	EnhancedLongIterator map(LongUnaryOperator f);

	/**
	 * Applies a function elementwise to this {@link EnhancedLongIterator} to make
	 * new {@link EnhancedIterator}.
	 *
	 * @param   <E> The target type of the mapping function.
	 * @param f A mapping function.
	 * @return A new {@link EnhancedIterator} instance whose elements are obtained
	 *         by applying the parameter mapping function to each element of this
	 *         {@link EnhancedLongIterator} instance in turn.
	 */
	<E> EnhancedIterator<E> mapToObject(LongFunction<? extends E> f);

	/**
	 * Applies a function elementwise to this {@link EnhancedLongIterator} to make a
	 * new {@link EnhancedDoubleIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link EnhancedDoubleIterator} instance whose elements are
	 *         obtained by applying the parameter mapping function to each element
	 *         of this {@link EnhancedLongIterator} instance in turn.
	 */
	EnhancedDoubleIterator mapToDouble(LongToDoubleFunction f);

	/**
	 * Applies a function elementwise to this {@link EnhancedLongIterator} to make a
	 * new {@link EnhancedIntIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link EnhancedIntIterator} instance whose elements are
	 *         obtained by applying the parameter mapping function to each element
	 *         of this {@link EnhancedLongIterator} instance in turn.
	 */
	EnhancedIntIterator mapToInt(LongToIntFunction f);

	/**
	 * Combines this {@link EnhancedLongIterator} with another iterator to create a
	 * new {@link EnhancedIterator} consisting of pairs of elements with the same
	 * index in their respective origins.
	 *
	 * @param       <E> The upper type bound on the parameter
	 *              {@link EnhancedIterator}.
	 * @param other The {@link EnhancedIterator} to zip this source
	 *              {@link EnhancedIterator} with.
	 *
	 * @return Denote this source {@link EnhancedLongIterator} by {@code F} with the
	 *         parameter {@link EnhancedIterator} denoted by {@code I}. We return a
	 *         new {@link EnhancedIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<E> EnhancedIterator<LongWith<E>> zipWith(Iterator<? extends E> other);

	/**
	 * Combines this {@link EnhancedLongIterator} with another primitive iterator to
	 * create a new {@link EnhancedIterator} consisting of pairs of elements with
	 * the same index in their respective origins.
	 *
	 * @param other The primitive iterator to zip this source
	 *              {@link EnhancedLongIterator} with.
	 *
	 * @return Denote this source {@link EnhancedLongIterator} by {@code F} with the
	 *         parameter primitive iterator denoted by {@code I}. We return a new
	 *         {@link EnhancedIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	EnhancedIterator<LongPair> zipWith(PrimitiveIterator.OfLong other);

	/**
	 * Creates a new {@link EnhancedIterator} by mapping each element in this source
	 * {@link EnhancedLongIterator} to a pair consisting of the element and the
	 * index it appears.
	 *
	 * @return Denote this source {@link EnhancedLongIterator} by {@code F}. We
	 *         return a new {@link EnhancedIterator} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	EnhancedIterator<LongWith<Integer>> enumerate();

	/**
	 * Creates a new {@link EnhancedLongIterator} from this
	 * {@link EnhancedLongIterator} by selecting elements with indices defined by
	 * the parameter index mapping.
	 *
	 * @param indexMap A strictly monotonically increasing function
	 *                 {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link EnhancedLongIterator}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns a {@link EnhancedLongIterator} {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
	 *         </ul>
	 */
	EnhancedLongIterator slice(IntUnaryOperator indexMap);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this
	 * {@link EnhancedLongIterator} by selecting the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link EnhancedLongIterator}. We
	 *         return a {@link EnhancedLongIterator} consisting of the first
	 *         {@code max(n, length(F))} elements of {@code F}.
	 */
	EnhancedLongIterator take(int n);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this
	 * {@link EnhancedLongIterator} by selecting elements until an element fails the
	 * supplied test, the first failure is not selected.
	 *
	 * @param predicate A {@link LongPredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a
	 *         {@link EnhancedLongIterator} consisting of the first {@code n}
	 *         elements of this source {@link EnhancedLongIterator}. If no element
	 *         fails the predicate test then a copy of the source is returned.
	 */
	EnhancedLongIterator takeWhile(LongPredicate predicate);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this
	 * {@link EnhancedLongIterator} by removing the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link EnhancedLongIterator}. We
	 *         return a {@link EnhancedLongIterator} missing the first
	 *         {@code min(n, length(F))} elements of {@code F}.
	 */
	EnhancedLongIterator drop(int n);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this
	 * {@link EnhancedLongIterator} by removing elements until an element fails the
	 * supplied test, the first failure is the first element of the result.
	 *
	 * @param predicate A {@link LongPredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a
	 *         {@link EnhancedLongIterator} missing {@code n} elements of this
	 *         source {@link EnhancedLongIterator}. If no element fails the
	 *         predicate test then a copy of the source is returned.
	 */
	EnhancedLongIterator dropWhile(LongPredicate predicate);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this
	 * {@link EnhancedLongIterator} by removing any element which fails the supplied
	 * predicate test.
	 *
	 * @param predicate A {@link LongPredicate}.
	 * @return A {@link EnhancedLongIterator} containing only those elements of this
	 *         source {@link EnhancedLongIterator} which pass the test defined by
	 *         the parameter predicate. The relative ordering of elements is
	 *         retained.
	 */
	EnhancedLongIterator filter(LongPredicate predicate);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this
	 * {@link EnhancedLongIterator} by adding each element of the supplied primitive
	 * iterator to its end in order.
	 *
	 * @param other A primitive iterator.
	 * @return A {@link EnhancedLongIterator} consisting of the elements of this
	 *         source {@link EnhancedLongIterator} followed by the elements of the
	 *         parameter primitive iterator.
	 */
	EnhancedLongIterator append(PrimitiveIterator.OfLong other);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this
	 * {@link EnhancedLongIterator} by adding each element of the supplied varargs
	 * array to its end in order.
	 *
	 * @param other - A varargs long array
	 * @return A {@link EnhancedLongIterator} consisting of the elements of the
	 *         source {@link EnhancedLongIterator} followed by the elements in the
	 *         parameter array.
	 */
	EnhancedLongIterator append(long... other);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this {@link EnhancedIterator}
	 * by adding each element to the end of the supplied primitive iterator in
	 * order.
	 *
	 * @param other A primitive iterator.
	 * @return a {@link EnhancedLongIterator} consisting of the elements of the
	 *         parameter primitive iterator followed by the elements of this source
	 *         {@link EnhancedLongIterator}.
	 */
	EnhancedLongIterator insert(PrimitiveIterator.OfLong other);

	/**
	 * Creates a new {@link EnhancedLongIterator} from this {@link EnhancedIterator}
	 * by adding each element to the end of the supplied varargs array in order.
	 *
	 * @param other - A varargs long array
	 * @return an {@link EnhancedLongIterator} consisting of the elements in the
	 *         parameter array followed by the elements of the source
	 *         {@link EnhancedLongIterator}.
	 */
	EnhancedLongIterator insert(long... other);

	/**
	 * Applies an accumulation operation to this {@link EnhancedLongIterator} to
	 * produce a new {@link EnhancedLongIterator}.
	 *
	 * @param accumulator The accumulation function.
	 * @return Let {@code F} denote this source {@link EnhancedLongIterator} and
	 *         {@code g} denote the accumulation function. Then the
	 *         {@link EnhancedLongIterator} returned is of the form:
	 *         <ul>
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}</li>
	 *         </ul>
	 */
	EnhancedLongIterator scan(LongBinaryOperator accumulator);

	/**
	 * Applies an accumulation operation to this {@link EnhancedLongIterator} to
	 * produce a new {@link EnhancedLongIterator}.
	 *
	 * @param id          The identity element in the accumulation.
	 * @param accumulator The accumulator function.
	 * @return Let {@code F} denote this source {@link EnhancedLongIterator} and
	 *         {@code g} denote the accumulation function. Then the
	 *         {@link EnhancedLongIterator} returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	EnhancedLongIterator scan(long id, LongBinaryOperator accumulator);

	/**
	 * Calculates the minimum value in this {@link EnhancedLongIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @return an {@link OptionalLong} wrapping the smallest element in this
	 *         {@link EnhancedLongIterator} or nothing if the iteration is empty.
	 */
	OptionalLong min();

	/**
	 * Calculates the minimum value in this {@link EnhancedLongIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param defaultValue - The value which will be returned if the source is
	 *                     empty.
	 *
	 * @return the smallest element in this {@link EnhancedLongIterator} or the
	 *         default value if the iteration is empty.
	 */
	long min(long defaultValue);

	/**
	 * Calculates the maximum value in this {@link EnhancedLongIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @return an {@link OptionalLong} wrapping the largest element in this
	 *         {@link EnhancedLongIterator} or nothing if the iteration is empty.
	 */
	OptionalLong max();

	/**
	 * Calculates the maximum value in this {@link EnhancedLongIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param defaultValue - The value which will be returned if the source is
	 *                     empty.
	 *
	 * @return The largest element in this {@link EnhancedLongIterator} or the
	 *         default value if the iteration is empty.
	 */
	long max(long defaultValue);

	/**
	 * Checks whether every element in this {@link EnhancedLongIterator} is the
	 * same.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @return True is every element of this {@link EnhancedLongIterator} is equal,
	 *         false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * Checks whether every element in this {@link EnhancedLongIterator} passes the
	 * supplied {@linkplain LongPredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(LongPredicate predicate);

	/**
	 * Checks whether any element in this {@link EnhancedLongIterator} passes the
	 * supplied {@linkplain LongPredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(LongPredicate predicate);

	/**
	 * Checks whether every element in this {@link EnhancedLongIterator} fails the
	 * supplied {@linkplain LongPredicate} test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(LongPredicate predicate);

	/**
	 * Reduces this {@link EnhancedLongIterator} to a single value via some
	 * reduction function and an initial value.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param id      The identity of the reduction operation
	 * @param reducer The reduction function
	 * @return If we denote this source {@link EnhancedLongIterator} by {@code F},
	 *         the length of {@code F} by {@code n} and the reduction function by
	 *         {@code f} then the result is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	long fold(long id, LongBinaryOperator reducer);

	/**
	 * Reduces this {@link EnhancedLongIterator} to a single value via some
	 * reduction function. Throws an exception if empty iterator.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param reducer The reduction function
	 * @return Let us denote this source {@link EnhancedLongIterator} by {@code F},
	 *         the length of {@code F} by {@code n} and the reduction function by
	 *         {@code f}. If {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	long fold(LongBinaryOperator reducer);

	/**
	 * Reduces this {@link EnhancedLongIterator} to a single value via some
	 * reduction function. Return nothing if empty iterator.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param reducer The reduction function
	 * @return Let us denote this source {@link EnhancedLongIterator} by {@code F},
	 *         the length of {@code F} by {@code n} and the reduction function by
	 *         {@code f}. If {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalLong foldOption(LongBinaryOperator reducer);

	/**
	 * Counts the number of elements in this {@link EnhancedLongIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @return The number of elements in this {@link EnhancedLongIterator}.
	 */
	long count();

	/**
	 * Caches the values in this {@link EnhancedLongIterator} to an array.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @return A long array containing all elements of this
	 *         {@link EnhancedLongIterator} with their ordering retained.
	 */
	long[] toArray();

	/**
	 * Builds a {@linkplain Map} using the elements in this
	 * {@link EnhancedLongIterator} via two supplied functions.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param             <K> The type of the keys in the created mapping.
	 * @param             <V> The type of the values in the created mapping.
	 * @param keyMapper   A function mapping longs to elements of the key type.
	 * @param valueMapper A function mapping longs to elements of the value type.
	 *
	 * @throws IllegalStateException If two elements of this
	 *                               {@link EnhancedLongIterator} map to the same
	 *                               key.
	 *
	 * @return A Map instance whose key-value pairs have a 1-to-1 correspondence
	 *         with the elements in this source {@link EnhancedLongIterator}. More
	 *         specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 *         an element of this source {@link EnhancedLongIterator}, say
	 *         {@code e}, is associated to the key value pair {@code (k(e), v(e))}.
	 */
	<K, V> Map<K, V> toMap(LongFunction<K> keyMapper, LongFunction<V> valueMapper);

	/**
	 * Groups elements in this {@link EnhancedLongIterator} via their image under
	 * some supplied classification function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param            <K> The type of the keys in the grouping map.
	 *
	 * @param classifier A function defining the different groups of elements.
	 * @return A Map instance whose keys partition the elements of this source
	 *         {@link EnhancedLongIterator} via the classification function.
	 *         Elements in this source {@link EnhancedLongIterator} who have equal
	 *         (under .equals() contract) images under the classification function
	 *         are grouped together in a long array accessed by their shared
	 *         classification key.
	 */
	<K> Map<K, long[]> groupBy(LongFunction<K> classifier);

	/**
	 * A convenience method for applying a global function onto this
	 * {@link EnhancedLongIterator}.
	 *
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this {@link EnhancedLongIterator}.
	 *
	 * A convenience method for applying a global function onto this
	 * {@link EnhancedLongIterator}.
	 *
	 * @param         <C> The target type of the build function.
	 * @param builder - a function whose input encompasses
	 *                {@link EnhancedLongIterator} instances of this element type.
	 * @return the output of the supplied function applied to this
	 *         {@link EnhancedLongIterator}.
	 */
	default <C> C build(Function<? super EnhancedLongIterator, C> builder)
	{
		return builder.apply(this);
	}

	/**
	 * Boxes the primitive long values in this {@link EnhancedLongIterator}.
	 *
	 * @return a copy of this source {@link EnhancedLongIterator} as a
	 *         {@link EnhancedIterator} of boxed {@linkplain Long} instances.
	 */
	default EnhancedIterator<Long> boxed()
	{
		return mapToObject(x -> x);
	}
}
