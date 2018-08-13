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

import xawd.jflow.iterators.misc.Bool;
import xawd.jflow.iterators.misc.IntPair;
import xawd.jflow.iterators.misc.IntPredicatePartition;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.IntWithDouble;
import xawd.jflow.iterators.misc.IntWithLong;


/**
 * A sequential, single use iterator with lots of functionality in the style of
 * the {@link IntStream} interface. It bears a strong resemblance in that it
 * they are lazily-evaluated, possibly infinite sequences of values. It was
 * written with the intention of providing a cleaner API for common sequence
 * manipulations as well as providing the user more fine-grained control over
 * value consumption compared to streams. This finer control comes at the cost
 * of any potential parallelism in computations and therefore Flows should be
 * viewed as an accompaniment to Streams, not as replacements.
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface IntFlow extends PrototypeIntFlow
{
	/**
	 * Applies a function elementwise to this IntFlow to make new IntFlow.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new IntFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this IntFlow instance
	 *         in turn.
	 */
	IntFlow map(IntUnaryOperator f);

	/**
	 * Applies a function elementwise to this IntFlow to make new Flow.
	 *
	 * @param <E>
	 *            The target type of the mapping function.
	 * @param f
	 *            A mapping function.
	 * @return A new Flow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this IntFlow instance
	 *         in turn.
	 */
	<E> Flow<E> mapToObject(IntFunction<E> f);

	/**
	 * Applies a function elementwise to this IntFlow to make a new DoubleFlow.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new DoubleFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this IntFlow instance
	 *         in turn.
	 */
	DoubleFlow mapToDouble(IntToDoubleFunction f);

	/**
	 * Applies a function elementwise to this IntFlow to make new LongFlow.
	 *
	 * @param f
	 *            A mapping function.
	 * @return A new LongFlow instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this IntFlow instance
	 *         in turn.
	 */
	LongFlow mapToLong(IntToLongFunction f);

	/**
	 * Combines this IntFlow with an iterator to create a new Flow consisting of
	 * pairs of elements with the same index in their respective origins.
	 *
	 * @param <E>
	 *            The upper type bound on the parameter iterator.
	 * @param other
	 *            The Iterator to zip this source IntFlow with.
	 *
	 * @return Denote this source IntFlow by {@code F} with the parameter iterator
	 *         denoted by {@code I}. We return a new Flow instance {@code G} defined
	 *         by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<E> Flow<IntWith<E>> zipWith(Iterator<? extends E> other);

	/**
	 * Combines this IntFlow with another primitive iterator to create a new Flow
	 * consisting of pairs of elements with the same index in their respective
	 * origins.
	 *
	 * @param other
	 *            The primitive iterator to zip this source IntFlow with.
	 *
	 * @return Denote this source IntFlow by {@code F} with the parameter primitive
	 *         iterator denoted by {@code I}. We return a new Flow instance
	 *         {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<IntPair> zipWith(PrimitiveIterator.OfInt other);

	/**
	 * Combines this IntFlow with another primitive iterator to create a new Flow
	 * consisting of pairs of elements with the same index in their respective
	 * origins.
	 *
	 * @param other
	 *            The primitive iterator to zip this source IntFlow with.
	 *
	 * @return Denote this source IntFlow by {@code F} with the parameter primitive
	 *         iterator denoted by {@code I}. We return a new Flow instance
	 *         {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<IntWithDouble> zipWith(PrimitiveIterator.OfDouble other);

	/**
	 * Combines this IntFlow with another primitive iterator to create a new Flow
	 * consisting of pairs of elements with the same index in their respective
	 * origins.
	 *
	 * @param other
	 *            The primitive iterator to zip this source IntFlow with.
	 *
	 * @return Denote this source IntFlow by {@code F} with the parameter primitive
	 *         iterator denoted by {@code I}. We return a new Flow instance
	 *         {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<IntWithLong> zipWith(PrimitiveIterator.OfLong other);

	/**
	 * Combines this IntFlow with another primitive iterator via a two argument
	 * function to create a new Flow consisting of the images of pairs of elements
	 * with the same index in their origin.
	 *
	 * @param other
	 *            The primitive iterator to combine this source IntFlow with.
	 * @param combiner
	 *            The combining function.
	 *
	 * @return Denote this source IntFlow by {@code F} with the parameter primitive
	 *         iterator denoted by {@code I} and the combining function by
	 *         {@code f}. We return a new Flow instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = f(F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	IntFlow combineWith(PrimitiveIterator.OfInt other, IntBinaryOperator combiner);

	/**
	 * Creates a new Flow by mapping each element in this source IntFlow to a pair
	 * consisting of the element and the index it appears.
	 *
	 * @return Denote this source IntFlow by {@code F}. We return a new Flow
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	Flow<IntPair> enumerate();

	/**
	 * Creates a new IntFlow from this IntFlow by selecting elements with indices
	 * defined by the parameter index mapping.
	 *
	 * @param indexMap
	 *            A strictly monotonically increasing function {@code f: N -> N}
	 * @return Let {@code F} denote this source IntFlow, let {@code n = length(F)}
	 *         and denote the indexMap by {@code f}. Then this method returns a
	 *         LongFlow {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
	 *         </ul>
	 */
	IntFlow slice(IntUnaryOperator indexMap);

	/**
	 * Creates a new IntFlow from this IntFlow by selecting the first n elements.
	 *
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote this source IntFlow. We return a IntFlow
	 *         consisting of the first {@code max(n, length(F))} elements of
	 *         {@code F}.
	 */
	IntFlow take(int n);

	/**
	 * Creates a new IntFlow from this IntFlow by selecting elements until an
	 * element fails the supplied test, the first failure is not selected.
	 *
	 * @param predicate
	 *            A IntPredicate.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a IntFlow consisting of
	 *         the first {@code n} elements of this source IntFlow. If no element
	 *         fails the predicate test then a copy of the source is returned.
	 */
	IntFlow takeWhile(IntPredicate predicate);

	/**
	 * Creates a new IntFlow from this IntFlow by removing the first n elements.
	 *
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote this source IntFlow. We return a IntFlow missing
	 *         the first {@code min(n, length(F))} elements of {@code F}.
	 */
	IntFlow drop(int n);

	/**
	 * Creates a new IntFlow from this IntFlow by removing elements until an element
	 * fails the supplied test, the first failure is the first element of the
	 * result.
	 *
	 * @param predicate
	 *            An IntPredicate.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a IntFlow missing
	 *         {@code n} elements of this source IntFlow. If no element fails the
	 *         predicate test then a copy of the source is returned.
	 */
	IntFlow dropWhile(IntPredicate predicate);

	/**
	 * Creates a new IntFlow from this IntFlow by removing any element which fails
	 * the supplied predicate test.
	 *
	 * @param predicate
	 *            A IntPredicate.
	 * @return An IntFlow containing only those elements of this source IntFlow
	 *         which pass the test defined by the parameter predicate. The relative
	 *         ordering of elements is retained.
	 */
	IntFlow filter(IntPredicate predicate);

	/**
	 * Creates a new IntFlow from this IntFlow by adding each element of the
	 * supplied primitive iterator to its end in order.
	 *
	 * @param other
	 *            A primitive iterator.
	 * @return An IntFlow consisting of the elements of this source IntFlow followed
	 *         by the elements of the parameter primitive iterator.
	 */
	IntFlow append(PrimitiveIterator.OfInt other);

	/**
	 * Creates a new IntFlow from this IntFlow by adding each element of the
	 * supplied varargs array to its end in order.
	 *
	 * @param other
	 *            - A varargs int array
	 * @return An IntFlow consisting of the elements of the source IntFlow followed
	 *         by the elements in the parameter array.
	 */
	IntFlow append(int... other);

	/**
	 * Creates a new IntFlow from this Flow by adding each element to the end of the
	 * supplied primitive iterator in order.
	 *
	 * @param other
	 *            A primitive iterator.
	 * @return a IntFlow consisting of the elements of the parameter primitive
	 *         iterator followed by the elements of this source IntFlow.
	 */
	IntFlow insert(PrimitiveIterator.OfInt other);

	/**
	 * Creates a new IntFlow from this Flow by adding each element to the end of the
	 * supplied varargs array in order.
	 *
	 * @param other
	 *            - A varargs int array
	 * @return an IntFlow consisting of the elements in the parameter array followed
	 *         by the elements of the source IntFlow.
	 */
	IntFlow insert(int... other);

	/**
	 * Applies an accumulation operation to this IntFlow to produce a new IntFlow.
	 *
	 * @param accumulator
	 *            The accumulation function.
	 * @return Let {@code F} denote this source IntFlow and {@code g} denote the
	 *         accumulation function. Then the IntFlow returned is of the form:
	 *         <ul>
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}</li>
	 *         </ul>
	 */
	IntFlow accumulate(IntBinaryOperator accumulator);

	/**
	 * Applies an accumulation operation to this IntFlow to produce a new IntFlow.
	 *
	 * @param id
	 *            The identity element in the accumulation.
	 * @param accumulator
	 *            The accumulator function.
	 * @return Let {@code F} denote this source IntFlow and {@code g} denote the
	 *         accumulation function. Then the IntFlow returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	IntFlow accumulate(int id, IntBinaryOperator accumulator);

	/**
	 * Calculates the minimum value in this IntFlow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @return an {@link OptionalInt} wrapping the smallest element in this IntFlow
	 *         or nothing if the iteration is empty.
	 */
	OptionalInt min();

	/**
	 * Calculates the minimum value in this IntFlow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return the smallest element in this IntFlow or the default value if the
	 *         iteration is empty.
	 */
	int min(int defaultValue);

	/**
	 * Calculates the minimum element in this IntFlow by a mapping to a type
	 * equipped with a natural ordering.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param <C>
	 *            A type equipped with a natural ordering.
	 *
	 * @param key
	 *            A function mapping the elements of this IntFlow to some data type
	 *            with an ordering.
	 * @return The element of this IntFlow whose image under the key mapping is the
	 *         minimum among all images. Nothing is returned if the source is empty.
	 */
	<C extends Comparable<C>> OptionalInt minByKey(IntFunction<C> key);

	/**
	 * Calculates the maximum value in this IntFlow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @return an {@link OptionalInt} wrapping the largest element in this IntFlow
	 *         or nothing if the iteration is empty.
	 */
	OptionalInt max();

	/**
	 * Calculates the maximum value in this IntFlow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param defaultValue
	 *            - The value which will be returned if the source is empty.
	 *
	 * @return The largest element in this IntFlow or the default value if the
	 *         iteration is empty.
	 */
	int max(int defaultValue);

	/**
	 * Calculates the maximum element in this IntFlow by a mapping to a type
	 * equipped with a natural ordering.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param <C>
	 *            A type equipped with a natural ordering.
	 *
	 * @param key
	 *            A function mapping the elements of this IntFlow to some data type
	 *            with an ordering.
	 * @return The element of this IntFlow whose image under the key mapping is the
	 *         maximum among all images. Nothing is returned if the source is empty.
	 */
	<C extends Comparable<C>> OptionalInt maxByKey(IntFunction<C> key);

	/**
	 * Checks whether every element in this IntFlow is the same.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @return True is every element of this IntFlow is equal, false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * Checks whether every element in this IntFlow is the same.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @return True is every element of this IntFlow is equal, false otherwise.
	 */
	default Bool areAllEqual2()
	{
		return Bool.of(areAllEqual());
	}

	/**
	 * Checks whether every element in this IntFlow passes the supplied IntPredicate
	 * test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(IntPredicate predicate);

	/**
	 * Checks whether every element in this IntFlow passes the supplied IntPredicate
	 * test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	default Bool allMatch2(IntPredicate predicate)
	{
		return Bool.of(allMatch(predicate));
	}

	/**
	 * Checks whether any element in this IntFlow passes the supplied IntPredicate
	 * test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(IntPredicate predicate);

	/**
	 * Checks whether any element in this IntFlow passes the supplied IntPredicate
	 * test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	default Bool anyMatch2(IntPredicate predicate)
	{
		return Bool.of(anyMatch(predicate));
	}

	/**
	 * Checks whether every element in this IntFlow fails the supplied IntPredicate
	 * test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(IntPredicate predicate);

	/**
	 * Checks whether every element in this IntFlow fails the supplied IntPredicate
	 * test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	default Bool noneMatch2(IntPredicate predicate)
	{
		return Bool.of(noneMatch(predicate));
	}

	/**
	 * Partitions the elements of this IntFlow on whether they pass the supplied
	 * IntPredicate test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param predicate
	 *            The supplied test.
	 * @return A partition of the cached elements split into two arrays on whether
	 *         they passed or failed the parameter predicate.
	 */
	IntPredicatePartition partition(IntPredicate predicate);

	/**
	 * Reduces this IntFlow to a single value via some reduction function and an
	 * initial value.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param id
	 *            The identity of the reduction operation
	 * @param reducer
	 *            The reduction function
	 * @return If we denote this source IntFlow by {@code F}, the length of
	 *         {@code F} by {@code n} and the reduction function by {@code f} then
	 *         the result is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	int fold(int id, IntBinaryOperator reducer);

	/**
	 * Reduces this IntFlow to a single value via some reduction function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param reducer
	 *            The reduction function
	 * @return Let us denote this source IntFlow by {@code F}, the length of
	 *         {@code F} by {@code n} and the reduction function by {@code f}. If
	 *         {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalInt fold(IntBinaryOperator reducer);

	/**
	 * Counts the number of elements in this IntFlow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @return The number of elements in this IntFlow.
	 */
	long count();

	/**
	 * Caches the values in this IntFlow to an array.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @return A long array containing all elements of this IntFlow with their
	 *         ordering retained.
	 */
	int[] toArray();

	/**
	 * Builds a Map using the elements in this IntFlow via two supplied functions.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param <K>
	 *            The type of the keys in the created mapping.
	 * @param <V>
	 *            The type of the values in the created mapping.
	 * @param keyMapper
	 *            A function mapping longs to elements of the key type.
	 * @param valueMapper
	 *            A function mapping longs to elements of the value type.
	 *
	 * @throws IllegalStateException
	 *             If two elements of this IntFlow map to the same key.
	 *
	 * @return A Map instance whose key-value pairs have a 1-to-1 correspondence
	 *         with the elements in this source IntFlow. More specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 *         an element of this source IntFlow, say {@code e}, is associated to
	 *         the key value pair {@code (k(e), v(e))}.
	 */
	<K, V> Map<K, V> toMap(IntFunction<K> keyMapper, IntFunction<V> valueMapper);

	/**
	 * Groups elements in this IntFlow via their image under some supplied
	 * classification function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * IntFlow.
	 *
	 * @param <K>
	 *            The type of the keys in the grouping map.
	 *
	 * @param classifier
	 *            A function defining the different groups of elements.
	 * @return A Map instance whose keys partition the elements of this source
	 *         IntFlow via the classification function. Elements in this source
	 *         IntFlow who have equal (under .equals() contract) images under the
	 *         classification function are grouped together in a long array accessed
	 *         by their shared classification key.
	 */
	<K> Map<K, int[]> groupBy(IntFunction<K> classifier);

	/**
	 * A convenience method for applying a global function onto this IntFlow.
	 *
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this IntFlow.
	 *
	 * A convenience method for applying a global function onto this IntFlow.
	 *
	 * @param <C>
	 *            The target type of the build function.
	 * @param builder
	 *            - a function whose input encompasses IntFlow instances of this
	 *            element type.
	 * @return the output of the supplied function applied to this IntFlow.
	 */
	default <C> C build(Function<? super IntFlow, C> builder)
	{
		return builder.apply(this);
	}

	/**
	 * Boxes the primitive long values in this IntFlow.
	 *
	 * @return a copy of this source IntFlow as a Flow of boxed {@linkplain Integer}
	 *         instances.
	 */
	default Flow<Integer> boxed()
	{
		return mapToObject(x -> x);
	}
}
