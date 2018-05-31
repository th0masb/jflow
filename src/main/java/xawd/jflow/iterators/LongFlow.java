package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

import xawd.jflow.iterators.iterables.DoubleFlowIterable;
import xawd.jflow.iterators.iterables.IntFlowIterable;
import xawd.jflow.iterators.iterables.LongFlowIterable;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithLong;
import xawd.jflow.iterators.misc.LongPair;
import xawd.jflow.iterators.misc.LongPredicatePartition;
import xawd.jflow.iterators.misc.LongWith;
import xawd.jflow.iterators.skippable.SkippableLongIterator;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface LongFlow extends SkippableLongIterator
{
	LongFlow map(final LongUnaryOperator f);

	<E> Flow<E> mapToObject(LongFunction<E> f);

	DoubleFlow mapToDouble(LongToDoubleFunction f);

	IntFlow mapToInt(LongToIntFunction f);

	<E> Flow<LongWith<E>> zipWith(final Iterator<? extends E> other);

	Flow<LongPair> zipWith(final PrimitiveIterator.OfLong other);

	Flow<DoubleWithLong> zipWith(final PrimitiveIterator.OfDouble other);

	Flow<IntWithLong> zipWith(final PrimitiveIterator.OfInt other);

	LongFlow combineWith(PrimitiveIterator.OfLong other, LongBinaryOperator combiner);

	Flow<IntWithLong> enumerate();

	LongFlow take(final int n);

	LongFlow takeWhile(final LongPredicate p);

	LongFlow skip(final int n);

	LongFlow skipWhile(final LongPredicate p);

	LongFlow filter(final LongPredicate p);

	LongFlow append(long... xs);

	LongFlow append(PrimitiveIterator.OfLong other);

	LongFlow insert(PrimitiveIterator.OfLong other);

	LongFlow insert(long... xs);

	LongFlow slice(IntUnaryOperator indexMapping);

	LongFlow accumulate(LongBinaryOperator accumulator);

	LongFlow accumulate(long id, LongBinaryOperator accumulator);


	OptionalLong min();

	long min(long defaultValue);

	long minByKey(long defaultValue, final LongToDoubleFunction key);

	OptionalLong minByKey(final LongToDoubleFunction key);

	OptionalLong max();

	long max(long defaultValue);

	long maxByKey(long defaultValue, final LongToDoubleFunction key);

	OptionalLong maxByKey(final LongToDoubleFunction key);


	boolean areAllEqual();

	boolean allMatch(final LongPredicate predicate);

	boolean anyMatch(final LongPredicate predicate);

	boolean noneMatch(final LongPredicate predicate);

	LongPredicatePartition partition(LongPredicate predicate);


	int count();

	long reduce(long id, LongBinaryOperator reducer);

	OptionalLong reduce(LongBinaryOperator reducer);


	long[] toArray();

	<K, V> Map<K, V> toMap(final LongFunction<K> keyMapper, final LongFunction<V> valueMapper);

	<K> Map<K, long[]> groupBy(final LongFunction<K> classifier);


	default Flow<Long> boxed()
	{
		return mapToObject(x -> x);
	}

	default <E> Flow<LongWith<E>> zipWith(final Iterable<E> other)
	{
		return zipWith(other.iterator());
	}

	default Flow<LongPair> zipWith(final LongFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default Flow<DoubleWithLong> zipWith(final DoubleFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default Flow<IntWithLong> zipWith(final IntFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default LongFlow combineWith(final LongFlowIterable other, final LongBinaryOperator combiner)
	{
		return combineWith(other.iter(), combiner);
	}

	default LongFlow append(final LongFlowIterable other)
	{
		return append(other.iter());
	}

	default LongFlow insert(final LongFlowIterable other)
	{
		return insert(other.iter());
	}
}
