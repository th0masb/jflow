package com.github.maumay.jflow.iterator;

import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;

import com.github.maumay.jflow.impl.DoubleIteratorAdapter;
import com.github.maumay.jflow.iterator.collector.DoubleIteratorCollector;
import com.github.maumay.jflow.utils.DoubleTup;
import com.github.maumay.jflow.vec.DoubleVec;

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
 * loop idiom. The consumption methods provided in this interface are usually
 * far more useful in general (unless you enjoy writing while loops and using
 * mutable collections in which case this library isn't for you).
 * </p>
 *
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface DoubleIterator extends OptionalDoubleIterator
{
	/**
	 * Applies a function elementwise to this {@link DoubleIterator} to make new
	 * {@link DoubleIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link DoubleIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link DoubleIterator} instance in turn.
	 */
	DoubleIterator map(DoubleUnaryOperator f);

	/**
	 * Applies a function elementwise to this {@link DoubleIterator} to make new
	 * Iterator.
	 *
	 * @param   <E> The target type of the mapping function.
	 * @param f A mapping function.
	 * @return A new Iterator instance whose elements are obtained by applying the
	 *         parameter mapping function to each element of this
	 *         {@link DoubleIterator} instance in turn.
	 */
	<E> RichIterator<E> mapToObj(DoubleFunction<? extends E> f);

	/**
	 * Applies a function elementwise to this {@link DoubleIterator} to make new
	 * {@link LongIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link LongIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link DoubleIterator} instance in turn.
	 */
	LongIterator mapToLong(DoubleToLongFunction f);

	/**
	 * Applies a function elementwise to this {@link DoubleIterator} to make a new
	 * {@link IntIterator}.
	 *
	 * @param f A mapping function.
	 * @return A new {@link IntIterator} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         {@link DoubleIterator} instance in turn.
	 */
	IntIterator mapToInt(DoubleToIntFunction f);

	/**
	 * Combines this {@link DoubleIterator} with another primitive iterator to
	 * create a new Iterator consisting of pairs of elements with the same index in
	 * their respective origins.
	 *
	 * @param other The primitive iterator to zip this source {@link DoubleIterator}
	 *              with.
	 *
	 * @return Denote this source {@link DoubleIterator} by {@code F} with the
	 *         parameter primitive iterator denoted by {@code I}. We return a new
	 *         Iterator instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], I[j])}</li>
	 *         <li>{@code length(G) = min(length(F), length(I))}</li>
	 *         </ul>
	 */
	RichIterator<DoubleTup> zip(PrimitiveIterator.OfDouble other);

	/**
	 * Creates a new Iterator by mapping each element in this source
	 * {@link DoubleIterator} to a pair consisting of the element and the index it
	 * appears.
	 *
	 * @return Denote this source {@link DoubleIterator} by {@code F}. We return a
	 *         new Iterator instance {@code G} defined by:
	 *         <ul>
	 *         <li>{@code G[j] = (F[j], j)}</li>
	 *         <li>{@code length(G) = length(F)}</li>
	 *         </ul>
	 */
	RichIterator<DoubleTup> enumerate();

	/**
	 * Creates a new {@link DoubleIterator} from this {@link DoubleIterator} by
	 * selecting elements with indices defined by the parameter index mapping.
	 *
	 * @param indexMap A strictly monotonically increasing function
	 *                 {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link DoubleIterator}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns a {@link DoubleIterator} {@code G} given by:
	 *         <ul>
	 *         <li>{@code G[i] = F(f(i))}</li>
	 *         <li><code> length(G) = supremum {i | (i in N) and (f(i) &lt; length(F))} </code></li>
	 *         </ul>
	 */
	DoubleIterator slice(IteratorSlicer indexMap);

	/**
	 * Creates a new {@link DoubleIterator} from this {@link DoubleIterator} by
	 * selecting the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link DoubleIterator}. We return a
	 *         {@link DoubleIterator} consisting of the first
	 *         {@code max(n, length(F))} elements of {@code F}.
	 */
	DoubleIterator take(int n);

	/**
	 * Creates a new {@link DoubleIterator} from this {@link DoubleIterator} by
	 * selecting elements until an element fails the supplied test, the first
	 * failure is not selected.
	 *
	 * @param predicate A DoublePredicate.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a
	 *         {@link DoubleIterator} consisting of the first {@code n} elements of
	 *         this source {@link DoubleIterator}. If no element fails the predicate
	 *         test then a copy of the source is returned.
	 */
	DoubleIterator takeWhile(DoublePredicate predicate);

	/**
	 * Creates a new {@link DoubleIterator} from this {@link DoubleIterator} by
	 * removing the first n elements.
	 *
	 * @param n A non-negative integer.
	 * @throws IllegalArgumentException If parameter is negative.
	 * @return Let {@code F} denote this source {@link DoubleIterator}. We return a
	 *         {@link DoubleIterator} missing the first {@code min(n, length(F))}
	 *         elements of {@code F}.
	 */
	DoubleIterator skip(int n);

	/**
	 * Creates a new {@link DoubleIterator} from this {@link DoubleIterator} by
	 * removing elements until an element fails the supplied test, the first failure
	 * is the first element of the result.
	 *
	 * @param predicate A DoublePredicate.
	 * @return Let {@code n} be the index of the first element that the parameter
	 *         predicate fails for. Then this method returns a
	 *         {@link DoubleIterator} missing {@code n} elements of this source
	 *         {@link DoubleIterator}. If no element fails the predicate test then a
	 *         copy of the source is returned.
	 */
	DoubleIterator skipWhile(DoublePredicate predicate);

	/**
	 * Creates a new {@link DoubleIterator} from this {@link DoubleIterator} by
	 * removing any element which fails the supplied predicate test.
	 *
	 * @param predicate A DoublePredicate.
	 * @return A {@link DoubleIterator} containing only those elements of this
	 *         source {@link DoubleIterator} which pass the test defined by the
	 *         parameter predicate. The relative ordering of elements is retained.
	 */
	DoubleIterator filter(DoublePredicate predicate);

	/**
	 * Creates a new {@link DoubleIterator} from this {@link DoubleIterator} by
	 * adding each element of the supplied primitive iterator to its end in order.
	 *
	 * @param other A primitive iterator.
	 * @return A {@link DoubleIterator} consisting of the elements of this source
	 *         {@link DoubleIterator} followed by the elements of the parameter
	 *         PrimitiveIterator.OfDouble.
	 */
	DoubleIterator chain(PrimitiveIterator.OfDouble other);

	/**
	 * Creates a new {@link DoubleIterator} from this {@link DoubleIterator} by
	 * adding each element of the supplied varargs array to its end in order.
	 *
	 * @param other - A varargs double array
	 * @return A {@link DoubleIterator} consisting of the elements of the source
	 *         {@link DoubleIterator} followed by the elements in the parameter
	 *         array.
	 */
	DoubleIterator append(double... other);

	/**
	 * Creates a new {@link DoubleIterator} from this Iterator by adding each
	 * element to the end of the supplied primitive iterator in order.
	 *
	 * @param other A primitive iterator.
	 * @return a {@link DoubleIterator} consisting of the elements of the parameter
	 *         primitive iterator followed by the elements of this source
	 *         {@link DoubleIterator}.
	 */
	DoubleIterator rchain(PrimitiveIterator.OfDouble other);

	/**
	 * Creates a new {@link DoubleIterator} from this Iterator by adding each
	 * element to the end of the supplied varargs array in order.
	 *
	 * @param other - A varargs double array
	 * @return an {@link DoubleIterator} consisting of the elements in the parameter
	 *         array followed by the elements of the source {@link DoubleIterator}.
	 */
	DoubleIterator insert(double... other);

	/**
	 * Applies an accumulation operation to this {@link DoubleIterator} to produce a
	 * new {@link DoubleIterator}.
	 *
	 * @param id          The identity element in the accumulation.
	 * @param accumulator The accumulator function.
	 * @return Let {@code F} denote this source {@link DoubleIterator} and {@code
	 g}     denote the accumulation function. Then the {@link DoubleIterator}
	 *         returned is of the form:
	 *         <ul>
	 *         <li>{@code [id, g(id, F[0]), g(g(id, F[0]), F[1]), ... ]}</li>
	 *         </ul>
	 */
	DoubleIterator scan(double id, DoubleBinaryOperator accumulator);

	/**
	 * Calculates the minimum value in this {@link DoubleIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @return an OptionalDouble wrapping the smallest element in this
	 *         {@link DoubleIterator} or nothing if the iteration is empty.
	 */
	OptionalDouble minOp();

	/**
	 * Calculates the minimum value in this {@link DoubleIterator}, throws an
	 * {@link IllegalStateException} if this flow is empty.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @return the smallest element in this {@link DoubleIterator}.
	 */
	double min();

	/**
	 * Calculates the maximum value in this {@link DoubleIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @return an OptionalDouble wrapping the largest element in this
	 *         {@link DoubleIterator} or nothing if the iteration is empty.
	 */
	OptionalDouble maxOp();

	/**
	 * Calculates the maximum value in this {@link DoubleIterator}, throws an
	 * {@link IllegalStateException} if this flow is empty.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @return The largest element in this {@link DoubleIterator}.
	 */
	double max();

	/**
	 * Checks whether every element in this {@link DoubleIterator} is the same
	 * according to the equality contract: {@link Double#compare(double, double)}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @return True is every element of this {@link DoubleIterator} is equal, false
	 *         otherwise.
	 */
	boolean areAllEqual();

	/**
	 * Checks whether every element in this {@link DoubleIterator} passes the
	 * supplied DoublePredicate test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if every element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean all(DoublePredicate predicate);

	/**
	 * Checks whether any element in this {@link DoubleIterator} passes the supplied
	 * DoublePredicate test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if any element passes the parameter predicate test, false
	 *         otherwise.
	 */
	boolean any(DoublePredicate predicate);

	/**
	 * Checks whether every element in this {@link DoubleIterator} fails the
	 * supplied DoublePredicate test.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @param predicate The supplied test.
	 * @return True if every element fails the parameter predicate test, false
	 *         otherwise.
	 */
	boolean none(DoublePredicate predicate);

	/**
	 * Reduces this {@link DoubleIterator} to a single value via some reduction
	 * function and an initial value
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @param id      The identity of the reduction operation
	 * @param reducer The reduction function
	 * @return If we denote this source {@link DoubleIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f} then the result is equal to: <br>
	 *         <br>
	 *         {@code f(...f(f(id, F[0]), F[1])..., F[n - 1])}
	 */
	double fold(double id, DoubleBinaryOperator reducer);

	/**
	 * Reduces this {@link DoubleIterator} to a single value via some reduction
	 * function. Throws exception if empty flow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @param reducer The reduction function
	 * @return Let us denote this source {@link DoubleIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f}. If {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	double fold(DoubleBinaryOperator reducer);

	/**
	 * Reduces this {@link DoubleIterator} to a single value via some reduction
	 * function. Returns nothing if empty flow.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @param reducer The reduction function
	 * @return Let us denote this source {@link DoubleIterator} by {@code F}, the
	 *         length of {@code F} by {@code n} and the reduction function by
	 *         {@code f}. If {@code n == 0} we return nothing, else we return: <br>
	 *         <br>
	 *         {@code f(...f(f(F[0], F[1]), F[2])..., F[n - 1])}
	 */
	OptionalDouble foldOp(DoubleBinaryOperator reducer);

	/**
	 * Counts the number of elements in this {@link DoubleIterator}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @return The number of elements in this {@link DoubleIterator}.
	 */
	long count();

	/**
	 * Caches the values in this {@link DoubleIterator} to a {@link DoubleVec}.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @return A vector containing all elements of this {@link DoubleIterator} with
	 *         their ordering retained.
	 */
	DoubleVec toVec();

	/**
	 * Caches the values in this {@link DoubleIterator} to an array.
	 *
	 * This method is a 'consuming method', i.e. it will iterate through this
	 * {@link DoubleIterator}.
	 *
	 * @return A double array containing all elements of this {@link DoubleIterator}
	 *         with their ordering retained.
	 */
	double[] toArray();

	/**
	 * Adapts this iterator via the given function to produce a new iterator and
	 * remove the ability of this iterator to be used directly.
	 * 
	 * @param adapter The function describing how a new iterator should be
	 *                constructed from this iterator.
	 * @return The new, adapted iterator which is sourced from this iterator.
	 */
	DoubleIterator adapt(DoubleIteratorAdapter adapter);

	/**
	 * Consumes this iterator using the supplied collection function to create a new
	 * instance of the given type.
	 * 
	 * @param           <R> The type of the collection result.
	 * @param collector The collection function which is used to consume this
	 *                  iterator.
	 * @return The result of the collection function applied to this
	 *         {@link DoubleIterator}.
	 */
	<R> R collect(DoubleIteratorCollector<R> collector);

	/**
	 * Boxes the primitive double values in this {@link DoubleIterator}.
	 *
	 * @return a copy of this source {@link DoubleIterator} as a Iterator of boxed
	 *         Double instances.
	 */
	default RichIterator<Double> boxed()
	{
		return mapToObj(x -> x);
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
}
