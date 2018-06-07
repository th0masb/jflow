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

import xawd.jflow.iterators.misc.IntPair;
import xawd.jflow.iterators.misc.IntPredicatePartition;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.IntWithDouble;
import xawd.jflow.iterators.misc.IntWithLong;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface IntFlow extends PrototypeIntFlow
{
	default <C> C build(final Function<? super IntFlow, C> builder)
	{
		return builder.apply(this);
	}

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
	 *         the parameter mapping function to each element of this Flow instance
	 *         in turn.
	 */
	<E> Flow<E> mapToObject(IntFunction<E> f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link DoubleFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         DoubleFlow instance in turn.
	 */
	DoubleFlow mapToDouble(IntToDoubleFunction f);

	/**
	 * @param f
	 *            - A mapping function.
	 * @return a new {@link LongFlow} instance whose elements are obtained by
	 *         applying the parameter mapping function to each element of this
	 *         LongFlow instance in turn.
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
	 * @param f
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
	 *
	 * @param indexMap
	 *            - A strictly monotonically increasing function {@code f: N -> N}
	 * @return Let {@code F} denote this source {@link IntFlow}, let
	 *         {@code n = length(F)} and denote the indexMap by {@code f}. Then this
	 *         method returns an IntFlow {@code G} whose i-th element is given by:
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
	 *         consisting of the first {@code n} elements of the source stream. If
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
	 *         missing {@code n} elements of the source stream. If no element fails
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


	IntFlow append(int... xs);

	IntFlow append(PrimitiveIterator.OfInt other);

	IntFlow insert(PrimitiveIterator.OfInt other);

	IntFlow insert(int... xs);

	IntFlow accumulate(IntBinaryOperator accumulator);

	IntFlow accumulate(int id, IntBinaryOperator accumulator);

	OptionalInt min();

	int min(int defaultValue);

	int minByKey(int defaultValue, final IntToDoubleFunction key);

	OptionalInt minByKey(final IntToDoubleFunction key);

	<C extends Comparable<C>> OptionalInt minByObjectKey(final IntFunction<C> key);

	OptionalInt max();

	int max(int defaultValue);

	int maxByKey(int defaultValue, final IntToDoubleFunction key);

	OptionalInt maxByKey(final IntToDoubleFunction key);

	<C extends Comparable<C>> OptionalInt maxByObjectKey(final IntFunction<C> key);

	boolean areAllEqual();

	boolean allMatch(final IntPredicate predicate);

	boolean anyMatch(final IntPredicate predicate);

	boolean noneMatch(final IntPredicate predicate);

	IntPredicatePartition partition(IntPredicate predicate);

	long count();

	int reduce(int id, IntBinaryOperator reducer);

	OptionalInt reduce(IntBinaryOperator reducer);

	int[] toArray();

	<K, V> Map<K, V> toMap(final IntFunction<K> keyMapper, final IntFunction<V> valueMapper);

	<K> Map<K, int[]> groupBy(final IntFunction<K> classifier);

	default Flow<Integer> boxed()
	{
		return mapToObject(x -> x);
	}
}
