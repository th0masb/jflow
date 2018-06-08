package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import xawd.jflow.iterators.misc.DoublePair;
import xawd.jflow.iterators.misc.DoublePredicatePartition;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithDouble;

/**
 * An {@link IntFlow} instance is an {@link PrimitiveIterator.OfInt} with lots
 * of extra functionality in the style of the {@link IntStream} interface. There
 * are methods inspired by other languages too, namely Python and Haskell.
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface DoubleFlow extends PrototypeDoubleFlow
{
	/**
	 * @param f
	 *            A mapping function.
	 * @return a new {@link DoubleFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         DoubleFlow instance in turn.
	 */
	DoubleFlow map(final DoubleUnaryOperator f);

	/**
	 * @param f
	 *            A mapping function.
	 * @return a new {@link Flow} instance whose elements are obtained by applying
	 *         the parameter mapping function to each element of this
	 *         {@link DoubleFlow} instance in turn.
	 */
	<E> Flow<E> mapToObject(DoubleFunction<E> f);

	/**
	 * @param f
	 *            A mapping function.
	 * @return a new {@link LongFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link DoubleFlow} instance in turn.
	 */
	LongFlow mapToLong(DoubleToLongFunction f);

	/**
	 * @param f
	 *            A mapping function.
	 * @return a new {@link IntFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link DoubleFlow} instance in turn.
	 */
	IntFlow mapToInt(DoubleToIntFunction f);

	/**
	 * @param <E>
	 *            The upper type bound on the parameter {@link Iterator}.
	 * @param other
	 *            The Iterator to zip the source {@link DoubleFlow} with.
	 *
	 * @return Denote the source DoubleFlow by {@code F} with the parameter Iterator
	 *         denoted by {@code I}. We return a new {@link Flow} instance {@code G}
	 *         defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<E> Flow<DoubleWith<E>> zipWith(final Iterator<? extends E> other);

	/**
	 * @param other
	 *            The {@link PrimitiveIterator.OfDouble} to zip the source
	 *            {@link DoubleFlow} with.
	 *
	 * @return Denote the source DoubleFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfDouble denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<DoublePair> zipWith(final PrimitiveIterator.OfDouble other);

	/**
	 * @param other
	 *            The {@link PrimitiveIterator.OfLong} to zip the source
	 *            {@link DoubleFlow} with.
	 *
	 * @return Denote the source DoubleFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfLong denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<DoubleWithLong> zipWith(final PrimitiveIterator.OfLong other);

	/**
	 * @param other
	 *            The {@link PrimitiveIterator.OfInt} to zip the source
	 *            {@link DoubleFlow} with.
	 *
	 * @return Denote the source DoubleFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfInt denoted by {@code I}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	Flow<IntWithDouble> zipWith(final PrimitiveIterator.OfInt other);

	/**
	 * @param other
	 *            The {@link PrimitiveIterator.OfDouble} to combine the source
	 *            {@link DoubleFlow} with.
	 * @param combiner
	 *            The combining function.
	 *
	 * @return Denote the source DoubleFlow by {@code F} with the parameter
	 *         PrimitiveIterator.OfDouble denoted by {@code I} and the combining
	 *         function by {@code f}. We return a new {@link DoubleFlow} instance
	 *         {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = f(F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	DoubleFlow combineWith(final PrimitiveIterator.OfDouble other, final DoubleBinaryOperator combiner);

	/**
	 * @return Denote the source DoubleFlow by {@code F}. We return a new
	 *         {@link Flow} instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	Flow<IntWithDouble> enumerate();

	/**
	 * @param indexMap
	 *            A strictly monotonically increasing function {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link DoubleFlow}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns an DoubleFlow {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) <
	 *         length(F))}</li>
	 *         </ul>
	 */
	DoubleFlow slice(IntUnaryOperator indexMap);

	/**
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote the source {@link DoubleFlow}. We return an
	 *         IntFlow consisting of the first {@code max(n, length(F))} elements of
	 *         {@code F}.
	 */
	DoubleFlow take(final int n);

	/**
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns an {@link DoubleFlow}
	 *         consisting of the first {@code n} elements of the source DoubleFlow.
	 *         If no element fails the predicate test then a copy of the source is
	 *         returned.
	 */
	DoubleFlow takeWhile(final DoublePredicate predicate);

	/**
	 * @param n
	 *            A non-negative integer.
	 * @throws IllegalArgumentException
	 *             If parameter is negative.
	 * @return Let {@code F} denote the source {@link DoubleFlow}. We return a
	 *         DoubleFlow missing the first {@code min(n, length(F))} elements of
	 *         {@code F}.
	 */
	DoubleFlow drop(final int n);

	/**
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a {@link DoubleFlow}
	 *         missing {@code n} elements of the source DoubleFlow. If no element
	 *         fails the predicate test then a copy of the source is returned.
	 */
	DoubleFlow dropWhile(final DoublePredicate predicate);

	/**
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return an {@link DoubleFlow} containing only those elements of the source
	 *         DoubleFlow which pass the test defined by the parameter predicate.
	 *         The relative ordering of elements is retained.
	 */
	DoubleFlow filter(final DoublePredicate predicate);

	/**
	 * @param other
	 *            A {@link PrimitiveIterator.OfDouble}.
	 * @return an {@link DoubleFlow} consisting of the elements of the source
	 *         DoubleFlow followed by the elements of the parameter
	 *         PrimitiveIterator.OfDouble.
	 */
	DoubleFlow append(PrimitiveIterator.OfDouble other);

	/**
	 * @param other
	 *            A varargs double array
	 * @return an {@link LongFlow} consisting of the elements of the source LongFlow
	 *         followed by the elements in the parameter array.
	 */
	DoubleFlow append(double... other);

	/**
	 * @param other
	 *            A {@link PrimitiveIterator.OfDouble}.
	 * @return an {@link DoubleFlow} consisting of the elements of the parameter
	 *         PrimitiveIterator.OfDouble followed by the elements of the source
	 *         DoubleFlow.
	 */
	DoubleFlow insert(PrimitiveIterator.OfDouble other);

	/**
	 * @param other
	 *            A varargs double array
	 * @return an {@link DoubleFlow} consisting of the elements in the parameter
	 *         array followed by the elements of the source DoubleFlow.
	 */
	DoubleFlow insert(double... other);

	/**
	 * @param accumulator
	 *            The accumulation function.
	 * @return Let {@code F} denote the source {@link DoubleFlow} and {@code g}
	 *         denote the accumulation function. Then the DoubleFlow returned is of
	 *         the form:
	 *         <ul>
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}</li>
	 *         </ul>
	 */
	DoubleFlow accumulate(DoubleBinaryOperator accumulator);

	/**
	 * @param id
	 *            The identity element in the accumulation.
	 * @param accumulator
	 *            The accumulator function.
	 * @return Let {@code F} denote the source {@link DoubleFlow} and {@code g}
	 *         denote the accumulation function. Then the DoubleFlow returned is of
	 *         the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	DoubleFlow accumulate(double id, DoubleBinaryOperator accumulator);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @return an {@link OptionalDouble} wrapping the smallest element in this
	 *         DoubleFlow or nothing if the iteration is empty.
	 */
	OptionalDouble min();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param defaultValue
	 *            The value which will be returned if the source is empty.
	 *
	 * @return The smallest element in this DoubleFlow or the default value if the
	 *         iteration is empty.
	 */
	double min(double defaultValue);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param defaultValue
	 *            The value to be returned if the source is empty.
	 * @param key
	 *            A function {@code f: R -> R}
	 * @return the element of this DoubleFlow whose image under the key mapping is
	 *         the minimum among all images. The parameter default is returned if
	 *         the source is empty. NaN images are ignored.
	 */
	double minByKey(double defaultValue, final DoubleUnaryOperator key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param key
	 *            A function {@code f: R -> R}
	 * @return the element of this DoubleFlow whose image under the key mapping is
	 *         the minimum among all images. Nothing is returned if the source is
	 *         empty. NaN images are ignored.
	 */
	OptionalDouble minByKey(final DoubleUnaryOperator key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @return an {@link OptionalDouble} wrapping the largest element in this
	 *         DoubleFlow or nothing if the iteration is empty.
	 */
	OptionalDouble max();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param defaultValue
	 *            The value which will be returned if the source is empty.
	 *
	 * @return the largest element in this DoubleFlow or the default value if the
	 *         iteration is empty.
	 */
	double max(double defaultValue);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param defaultValue
	 *            The value to be returned if the source is empty.
	 * @param key
	 *            A function {@code f: R -> R}.
	 * @return the element of this DoubleFlow whose image under the key mapping is
	 *         the maximum among all images. The parameter default is returned if
	 *         the source is empty. NaN images are ignored.
	 */
	double maxByKey(double defaultValue, final DoubleUnaryOperator key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param key
	 *            A function {@code f: R -> R}.
	 * @return the element of this DoubleFlow whose image under the key mapping is
	 *         the maximum among all images. Nothing is returned if the source is
	 *         empty. NaN images are ignored.
	 */
	OptionalDouble maxByKey(final DoubleUnaryOperator key);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @return true is every element of this DoubleFlow is equal, false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return true if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(final DoublePredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param predicate
	 *            An {@link DoublePredicate}.
	 * @return true if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(final DoublePredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return true if all elements fail the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(final DoublePredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param predicate
	 *            A {@link DoublePredicate}.
	 * @return a partition of the cached elements split into two arrays on whether
	 *         they passed or failed the parameter predicate.
	 */
	DoublePredicatePartition partition(DoublePredicate predicate);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param id
	 *            The identity of the reduction operation
	 * @param reducer
	 *            The reduction function
	 * @return If we denote the source DoubleFlow by {@code F}, the length of
	 *         {@code F} by {@code n} and the reduction function by {@code f} then
	 *         the result is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	double reduce(double id, DoubleBinaryOperator reducer);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param reducer
	 *            The reduction function.
	 * @return Let us denote the source DoubleFlow by {@code F}, the length of
	 *         {@code F} by {@code n} and the reduction function by {@code f}. If
	 *         {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalDouble reduce(DoubleBinaryOperator reducer);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @return the number of elements in this DoubleFlow.
	 */
	long count();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @return an long array containing all elements of this DoubleFlow with their
	 *         ordering retained.
	 */
	double[] toArray();

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param <K>
	 *            The type of the keys in the created mapping.
	 * @param <V>
	 *            The type of the values in the created mapping.
	 * @param keyMapper
	 *            A function mapping elements of this DoubleFlow to elements of the
	 *            key type.
	 * @param valueMapper
	 *            A function mapping elements of this DoubleFlow to elements of the
	 *            value type.
	 * @return a {@link Map} instance whose key-value pairs have a 1-to-1
	 *         correspondence with the elements in the source DoubleFlow. More
	 *         specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 * 		an element of the source DoubleFlow, say {@code e}, is associated to
	 *         the key value pair {@code (k(e), v(e))}.<br>
	 *         <br>
	 *         If two different elements of the source DoubleFlow map to the same
	 *         key then an {@link IllegalStateException} will be thrown.
	 */
	<K, V> Map<K, V> toMap(final DoubleFunction<K> keyMapper, final DoubleFunction<V> valueMapper);

	/**
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleFlow}.
	 *
	 * @param <K>
	 *            The type of the keys in the final grouping map.
	 *
	 * @param classifier
	 *            A function defining the different groups of elements.
	 * @return A {@link Map} instance whose keys partition the elements of the
	 *         source DoubleFlow via the classification function. Elements in the
	 *         source DoubleFlow who have equal (under .equals() contract) images
	 *         under the classification function are grouped together in an array
	 *         accessed by their shared classification key.
	 */
	<K> Map<K, double[]> groupBy(final DoubleFunction<K> classifier);

	/**
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this {@link DoubleFlow}.
	 *
	 * A convenience method for applying a global function onto this DoubleFlow.
	 *
	 * @param builder
	 *            A function whose input encompasses DoubleFlow instances of this
	 *            element type.
	 * @return the output of the supplied function applied to this DoubleFlow.
	 */
	default <C> C build(final Function<? super DoubleFlow, C> builder)
	{
		return builder.apply(this);
	}

	/**
	 * @return a copy of this source {@link DoubleFlow} as a {@link Flow} of boxed
	 *         {@link Double} instances.
	 */
	default Flow<Double> boxed()
	{
		return mapToObject(x -> x);
	}
}
