package maumay.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
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

import maumay.jflow.iterators.factories.Iter;
import maumay.jflow.iterators.misc.DoublePair;
import maumay.jflow.iterators.misc.DoubleWith;
import maumay.jflow.vec.DoubleVec;

/**
 * <p>
 * An extension of {@link PrimitiveIterator.OfDouble} with a multitude of extra
 * methods for piping and transforming sequential data streams. There are many
 * static factory methods for constructing instances of this interface in the
 * {@link Iter} class.
 * </p>
 * <p>
 * In general a good rule of thumb when using iterators is to avoid consuming
 * them with the {@link #nextDouble()} method unless it is in the standard while
 * loop idiom. The consumption methods provided in this interface are vastly
 * more useful general (unless you enjoy writing while loops and using mutable
 * collections in which case this library isn't for you).
 * </p>
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface EnhancedDoubleIterator extends SafeDoubleIterator
{
	/**
	 * Applies a function elementwise to this {@link EnhancedDoubleIterator} to make
	 * new {@link EnhancedDoubleIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link EnhancedDoubleIterator} instance whose elements are
	 *         obtained by applying the parameter mapping function to each element
	 *         of this {@link EnhancedDoubleIterator} instance in turn.
	 */
	EnhancedDoubleIterator map(DoubleUnaryOperator f);

	/**
	 * Applies a function elementwise to this {@link EnhancedDoubleIterator} to make
	 * new Iterator.
	 *
	 * @param   <E> The target type of the mapping function.
	 * @param f A mapping function.
	 * @return A new Iterator instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this
	 *         {@link EnhancedDoubleIterator} instance in turn.
	 */
	<E> EnhancedIterator<E> mapToObject(DoubleFunction<? extends E> f);

	/**
	 * Applies a function elementwise to this {@link EnhancedDoubleIterator} to make
	 * new {@link EnhancedLongIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link EnhancedLongIterator} instance whose elements are
	 *         obtained by applying the parameter mapping function to each element
	 *         of this {@link EnhancedDoubleIterator} instance in turn.
	 */
	EnhancedLongIterator mapToLong(DoubleToLongFunction f);

	/**
	 * Applies a function elementwise to this {@link EnhancedDoubleIterator} to make
	 * a new {@link EnhancedIntIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link EnhancedIntIterator} instance whose elements are
	 *         obtained by applying the parameter mapping function to each element
	 *         of this {@link EnhancedDoubleIterator} instance in turn.
	 */
	EnhancedIntIterator mapToInt(DoubleToIntFunction f);

	/**
	 * Combines this {@link EnhancedDoubleIterator} with another iterator to create
	 * a new Iterator consisting of pairs of elements with the same index in their
	 * respective origins.
	 *
	 * @param       <E> The upper type bound on the parameter Iterator.
	 * @param other The Iterator to zip this source Iterator with.
	 *
	 * @return Denote this source {@link EnhancedDoubleIterator} by {@code F} with
	 *         the parameter Iterator denoted by {@code I}. We return a new Iterator
	 *         instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	<E> EnhancedIterator<DoubleWith<E>> zipWith(Iterator<? extends E> other);

	/**
	 * Combines this {@link EnhancedDoubleIterator} with another primitive iterator
	 * to create a new Iterator consisting of pairs of elements with the same index
	 * in their respective origins.
	 *
	 * @param other The primitive iterator to zip this source
	 *              {@link EnhancedDoubleIterator} with.
	 *
	 * @return Denote this source {@link EnhancedDoubleIterator} by {@code F} with
	 *         the parameter primitive iterator denoted by {@code I}. We return a
	 *         new Iterator instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	EnhancedIterator<DoublePair> zipWith(PrimitiveIterator.OfDouble other);

	/**
	 * Creates a new Iterator by mapping each element in this source
	 * {@link EnhancedDoubleIterator} to a pair consisting of the element and the
	 * index it appears.
	 *
	 * @return Denote this source {@link EnhancedDoubleIterator} by {@code F}. We
	 *         return a new Iterator instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	EnhancedIterator<DoubleWith<Integer>> enumerate();

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this
	 * {@link EnhancedDoubleIterator} by selecting elements with indices defined by
	 * the parameter index mapping.
	 *
	 * @param indexMap A strictly monotonically increasing function
	 *                 {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link EnhancedDoubleIterator}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns a {@link EnhancedDoubleIterator} {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
	 *         </ul>
	 */
	EnhancedDoubleIterator slice(IntUnaryOperator indexMap);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this
	 * {@link EnhancedDoubleIterator} by selecting the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link EnhancedDoubleIterator}. We
	 *         return a {@link EnhancedDoubleIterator} consisting of the first
	 *         {@code max(n, length(F))} elements of {@code F}.
	 */
	EnhancedDoubleIterator take(int n);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this
	 * {@link EnhancedDoubleIterator} by selecting elements until an element fails
	 * the supplied test, the first failure is not selected.
	 *
	 * @param predicate A DoublePredicate.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a
	 *         {@link EnhancedDoubleIterator} consisting of the first {@code n}
	 *         elements of this source {@link EnhancedDoubleIterator}. If no element
	 *         fails the predicate test then a copy of the source is returned.
	 */
	EnhancedDoubleIterator takeWhile(DoublePredicate predicate);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this
	 * {@link EnhancedDoubleIterator} by removing the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link EnhancedDoubleIterator}. We
	 *         return a {@link EnhancedDoubleIterator} missing the first
	 *         {@code min(n, length(F))} elements of {@code F}.
	 */
	EnhancedDoubleIterator drop(int n);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this
	 * {@link EnhancedDoubleIterator} by removing elements until an element fails
	 * the supplied test, the first failure is the first element of the result.
	 *
	 * @param predicate A DoublePredicate.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a
	 *         {@link EnhancedDoubleIterator} missing {@code n} elements of this
	 *         source {@link EnhancedDoubleIterator}. If no element fails the
	 *         predicate test then a copy of the source is returned.
	 */
	EnhancedDoubleIterator dropWhile(DoublePredicate predicate);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this
	 * {@link EnhancedDoubleIterator} by removing any element which fails the
	 * supplied predicate test.
	 *
	 * @param predicate A DoublePredicate.
	 * @return A {@link EnhancedDoubleIterator} containing only those elements of
	 *         this source {@link EnhancedDoubleIterator} which pass the test
	 *         defined by the parameter predicate. The relative ordering of elements
	 *         is retained.
	 */
	EnhancedDoubleIterator filter(DoublePredicate predicate);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this
	 * {@link EnhancedDoubleIterator} by adding each element of the supplied
	 * primitive iterator to its end in order.
	 *
	 * @param other A primitive iterator.
	 * @return A {@link EnhancedDoubleIterator} consisting of the elements of this
	 *         source {@link EnhancedDoubleIterator} followed by the elements of the
	 *         parameter PrimitiveIterator.OfDouble.
	 */
	EnhancedDoubleIterator append(PrimitiveIterator.OfDouble other);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this
	 * {@link EnhancedDoubleIterator} by adding each element of the supplied varargs
	 * array to its end in order.
	 *
	 * @param other - A varargs double array
	 * @return A {@link EnhancedDoubleIterator} consisting of the elements of the
	 *         source {@link EnhancedDoubleIterator} followed by the elements in the
	 *         parameter array.
	 */
	EnhancedDoubleIterator append(double... other);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this Iterator by adding
	 * each element to the end of the supplied primitive iterator in order.
	 *
	 * @param other A primitive iterator.
	 * @return a {@link EnhancedDoubleIterator} consisting of the elements of the
	 *         parameter primitive iterator followed by the elements of this source
	 *         {@link EnhancedDoubleIterator}.
	 */
	EnhancedDoubleIterator insert(PrimitiveIterator.OfDouble other);

	/**
	 * Creates a new {@link EnhancedDoubleIterator} from this Iterator by adding
	 * each element to the end of the supplied varargs array in order.
	 *
	 * @param other - A varargs double array
	 * @return an {@link EnhancedDoubleIterator} consisting of the elements in the
	 *         parameter array followed by the elements of the source
	 *         {@link EnhancedDoubleIterator}.
	 */
	EnhancedDoubleIterator insert(double... other);

	/**
	 * Applies an accumulation operation to this {@link EnhancedDoubleIterator} to
	 * produce a new {@link EnhancedDoubleIterator}.
	 *
	 * @param accumulator The accumulation function.
	 * @return Let {@code F} denote this source {@link EnhancedDoubleIterator} and
	 *         {@code g} denote the accumulation function. Then the
	 *         {@link EnhancedDoubleIterator} returned is of the form:
	 *         <ul>
	 *         <li>{@code [F[0], g(F[0], F[1]), g(g(F[0], F[1]), F[2]), ... ]}</li>
	 *         </ul>
	 */
	EnhancedDoubleIterator accumulate(DoubleBinaryOperator accumulator);

	/**
	 * Applies an accumulation operation to this {@link EnhancedDoubleIterator} to
	 * produce a new {@link EnhancedDoubleIterator}.
	 *
	 * @param id          The identity element in the accumulation.
	 * @param accumulator The accumulator function.
	 * @return Let {@code F} denote this source {@link EnhancedDoubleIterator} and
	 *         {@code g} denote the accumulation function. Then the
	 *         {@link EnhancedDoubleIterator} returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	EnhancedDoubleIterator accumulate(double id, DoubleBinaryOperator accumulator);

	/**
	 * Calculates the minimum value in this {@link EnhancedDoubleIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @return an OptionalDouble wrapping the smallest element in this
	 *         {@link EnhancedDoubleIterator} or nothing if the iteration is empty.
	 */
	OptionalDouble minOption();

	/**
	 * Calculates the minimum value in this {@link EnhancedDoubleIterator}, throws
	 * an {@link IllegalStateException} if this flow is empty.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @return the smallest element in this {@link EnhancedDoubleIterator}.
	 */
	double min();

	/**
	 * Calculates the maximum value in this {@link EnhancedDoubleIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @return an OptionalDouble wrapping the largest element in this
	 *         {@link EnhancedDoubleIterator} or nothing if the iteration is empty.
	 */
	OptionalDouble maxOption();

	/**
	 * Calculates the maximum value in this {@link EnhancedDoubleIterator}, throws
	 * an {@link IllegalStateException} if this flow is empty.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @return The largest element in this {@link EnhancedDoubleIterator}.
	 */
	double max();

	/**
	 * Checks whether every element in this {@link EnhancedDoubleIterator} is the
	 * same.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @return True is every element of this {@link EnhancedDoubleIterator} is
	 *         equal, false otherwise.
	 */
	boolean areAllEqual();

	/**
	 * Checks whether every element in this {@link EnhancedDoubleIterator} passes
	 * the supplied DoublePredicate test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean allMatch(DoublePredicate predicate);

	/**
	 * Checks whether any element in this {@link EnhancedDoubleIterator} passes the
	 * supplied DoublePredicate test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean anyMatch(DoublePredicate predicate);

	/**
	 * Checks whether every element in this {@link EnhancedDoubleIterator} fails the
	 * supplied DoublePredicate test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean noneMatch(DoublePredicate predicate);

	/**
	 * Reduces this {@link EnhancedDoubleIterator} to a single value via some
	 * reduction function and an initial value
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param id      The identity of the reduction operation
	 * @param reducer The reduction function
	 * @return If we denote this source {@link EnhancedDoubleIterator} by {@code F},
	 *         the length of {@code F} by {@code n} and the reduction function by
	 *         {@code f} then the result is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	double fold(double id, DoubleBinaryOperator reducer);

	/**
	 * Reduces this {@link EnhancedDoubleIterator} to a single value via some
	 * reduction function. Throws exception if empty flow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param reducer The reduction function
	 * @return Let us denote this source {@link EnhancedDoubleIterator} by
	 *         {@code F}, the length of {@code F} by {@code n} and the reduction
	 *         function by {@code f}. If {@code n == 0} we return nothing, else we
	 *         return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	double fold(DoubleBinaryOperator reducer);

	/**
	 * Reduces this {@link EnhancedDoubleIterator} to a single value via some
	 * reduction function. Returns nothing if empty flow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param reducer The reduction function
	 * @return Let us denote this source {@link EnhancedDoubleIterator} by
	 *         {@code F}, the length of {@code F} by {@code n} and the reduction
	 *         function by {@code f}. If {@code n == 0} we return nothing, else we
	 *         return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalDouble foldOption(DoubleBinaryOperator reducer);

	/**
	 * Counts the number of elements in this {@link EnhancedDoubleIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @return The number of elements in this {@link EnhancedDoubleIterator}.
	 */
	long count();

	/**
	 * Caches the values in this {@link EnhancedDoubleIterator} to a
	 * {@link DoubleVec}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @return A vector containing all elements of this
	 *         {@link EnhancedDoubleIterator} with their ordering retained.
	 */
	default DoubleVec toVec()
	{
		return DoubleVec.of(toArray());
	}

	/**
	 * Caches the values in this {@link EnhancedDoubleIterator} to an array.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @return A double array containing all elements of this
	 *         {@link EnhancedDoubleIterator} with their ordering retained.
	 */
	double[] toArray();

	/**
	 * Builds a Map using the elements in this {@link EnhancedDoubleIterator} via
	 * two supplied functions.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param             <K> The type of the keys in the created mapping.
	 * @param             <V> The type of the values in the created mapping.
	 * @param keyMapper   A function mapping doubles to elements of the key type.
	 * @param valueMapper A function mapping doubles to elements of the value type.
	 *
	 * @throws IllegalStateException If two elements of this
	 *                               {@link EnhancedDoubleIterator} map to the same
	 *                               key.
	 *
	 * @return A Map instance whose key-value pairs have a 1-to-1 correspondence
	 *         with the elements in this source {@link EnhancedDoubleIterator}. More
	 *         specifically if:
	 *         <ul>
	 *         <li>{@code k} denotes the key mapping function</li>
	 *         <li>{@code v} denotes the value mapping function</li>
	 *         </ul>
	 *         an element of this source {@link EnhancedDoubleIterator}, say
	 *         {@code e}, is associated to the key value pair {@code (k(e), v(e))}.
	 */
	<K, V> Map<K, V> toMap(DoubleFunction<K> keyMapper, DoubleFunction<V> valueMapper);

	/**
	 * Groups elements in this {@link EnhancedDoubleIterator} via their image under
	 * some supplied classification function.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param            <K> The type of the keys in the grouping map.
	 *
	 * @param classifier A function defining the different groups of elements.
	 * @return A Map instance whose keys partition the elements of this source
	 *         {@link EnhancedDoubleIterator} via the classification function.
	 *         Elements in this source {@link EnhancedDoubleIterator} who have equal
	 *         (under .equals() contract) images under the classification function
	 *         are grouped together in a double array accessed by their shared
	 *         classification key.
	 */
	<K> Map<K, double[]> groupBy(DoubleFunction<K> classifier);

	/**
	 * A convenience method for applying a global function onto this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * This method is potentially (depending on the supplied function) a 'consuming
	 * method', i.e. it will iterate through this {@link EnhancedDoubleIterator}.
	 *
	 * A convenience method for applying a global function onto this
	 * {@link EnhancedDoubleIterator}.
	 *
	 * @param         <C> The target type of the build function.
	 * @param builder A function whose input encompasses
	 *                {@link EnhancedDoubleIterator} instances of this element type.
	 * @return the output of the supplied function applied to this
	 *         {@link EnhancedDoubleIterator}.
	 */
	default <C> C build(Function<? super EnhancedDoubleIterator, C> builder)
	{
		return builder.apply(this);
	}

	/**
	 * Boxes the primitive double values in this {@link EnhancedDoubleIterator}.
	 *
	 * @return a copy of this source {@link EnhancedDoubleIterator} as a Iterator of
	 *         boxed Double instances.
	 */
	default EnhancedIterator<Double> boxed()
	{
		return mapToObject(x -> x);
	}

	/**
	 * Computes the sum of elements in a non-empty iterator.
	 * 
	 * @return the sum of elements if this iterator is non-empty, throws an
	 *         exception otherwise.
	 */
	default double sum()
	{
		return fold((a, b) -> a + b);
	}

	/**
	 * Computes the product of elements in a non-empty iterator.
	 * 
	 * @return the product of elements if this iterator is non-empty, throws an
	 *         exception otherwise.
	 */
	default double product()
	{
		return fold((a, b) -> a * b);
	}

	/**
	 * Computes the average of elements in a non-empty iterator.
	 * 
	 * @return the average of elements if this iterator is non-empty, throws an
	 *         exception otherwise.
	 */
	default double average()
	{
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		int count = 0;
		double sum = 0;
		while (hasNext()) {
			count++;
			sum += nextDouble();
		}
		return sum / count;
	}

}
