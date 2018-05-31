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

import xawd.jflow.iterators.iterables.DoubleFlowIterable;
import xawd.jflow.iterators.iterables.IntFlowIterable;
import xawd.jflow.iterators.iterables.LongFlowIterable;
import xawd.jflow.iterators.misc.IntPair;
import xawd.jflow.iterators.misc.IntPredicatePartition;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.IntWithDouble;
import xawd.jflow.iterators.misc.IntWithLong;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface IntFlow extends SkippableIntIterator
{
	default <C> C build(final Function<? super IntFlow, C> builder)
	{
		return builder.apply(this);
	}

	IntFlow map(final IntUnaryOperator f);

	<T> Flow<T> mapToObject(IntFunction<T> f);

	DoubleFlow mapToDouble(IntToDoubleFunction f);

	LongFlow mapToLong(IntToLongFunction f);

	<E> Flow<IntWith<E>> zipWith(final Iterator<? extends E> other);

	Flow<IntPair> zipWith(final PrimitiveIterator.OfInt other);

	Flow<IntWithDouble> zipWith(final PrimitiveIterator.OfDouble other);

	Flow<IntWithLong> zipWith(final PrimitiveIterator.OfLong other);

	IntFlow combineWith(PrimitiveIterator.OfInt other, IntBinaryOperator combiner);

	Flow<IntPair> enumerate();

	IntFlow take(final int n);

	IntFlow takeWhile(final IntPredicate p);

	IntFlow skip(final int n);

	IntFlow skipWhile(final IntPredicate p);

	IntFlow filter(final IntPredicate p);

	IntFlow append(int... xs);

	IntFlow append(PrimitiveIterator.OfInt other);

	IntFlow insert(PrimitiveIterator.OfInt other);

	IntFlow insert(int... xs);

	IntFlow slice(IntUnaryOperator indexMap);

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


	int count();

	int reduce(int id, IntBinaryOperator reducer);

	OptionalInt reduce(IntBinaryOperator reducer);


	int[] toArray();

	<K, V> Map<K, V> toMap(final IntFunction<K> keyMapper, final IntFunction<V> valueMapper);

	<K> Map<K, int[]> groupBy(final IntFunction<K> classifier);


	default Flow<Integer> boxed()
	{
		return mapToObject(x -> x);
	}

	default <E> Flow<IntWith<E>> zipWith(final Iterable<E> other)
	{
		return zipWith(other.iterator());
	}

	default Flow<IntPair> zipWith(final IntFlowIterable other)
	{
		return zipWith(other.iterator());
	}

	default Flow<IntWithDouble> zipWith(final DoubleFlowIterable other)
	{
		return zipWith(other.iterator());
	}

	default Flow<IntWithLong> zipWith(final LongFlowIterable other)
	{
		return zipWith(other.iterator());
	}

	default IntFlow combineWith(final IntFlowIterable other, final IntBinaryOperator combiner)
	{
		return combineWith(other.iterator(), combiner);
	}

	default IntFlow append(final IntFlowIterable other)
	{
		return append(other.iterator());
	}

	default IntFlow insert(final IntFlowIterable other)
	{
		return insert(other.iterator());
	}
}
