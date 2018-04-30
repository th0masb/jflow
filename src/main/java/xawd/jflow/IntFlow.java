package xawd.jflow;

import java.util.HashMap;
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

import xawd.jflow.iterables.DoubleFlowIterable;
import xawd.jflow.iterables.IntFlowIterable;
import xawd.jflow.iterables.LongFlowIterable;
import xawd.jflow.iterators.SkippableIntIterator;
import xawd.jflow.zippedpairs.IntPair;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.IntWithDouble;
import xawd.jflow.zippedpairs.IntWithLong;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface IntFlow extends SkippableIntIterator
{

	default <C> C build(Function<? super IntFlow, C> builder)
	{
		return builder.apply(this);
	}

	IntFlow map(final IntUnaryOperator f);

	<T> Flow<T> mapToObject(IntFunction<T> f);

	DoubleFlow mapToDouble(IntToDoubleFunction f);

	LongFlow mapToLong(IntToLongFunction f);

	<T> Flow<IntWith<T>> zipWith(final Iterator<T> other);

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

	boolean allMatch(final IntPredicate predicate);

	boolean anyMatch(final IntPredicate predicate);

	boolean noneMatch(final IntPredicate predicate);

	int count();

	int reduce(int id, IntBinaryOperator reducer);

	OptionalInt reduce(IntBinaryOperator reducer);

	IntFlow accumulate(IntBinaryOperator accumulator);

	IntFlow accumulate(int id, IntBinaryOperator accumulator);



	default int[] toArray()
	{
		final ArrayAccumulators.OfInt accumulater = ArrayAccumulators.intAccumulator();
		while (hasNext()) {
			accumulater.add(nextInt());
		}
		return accumulater.compress();
	}

	default <K, V> Map<K, V> toMap(final IntFunction<K> keyMapper, final IntFunction<V> valueMapper)
	{
		final Map<K, V> collected = new HashMap<>();
		while (hasNext()) {
			final int next = nextInt();
			final K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			}
			else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	default <K> Map<K, int[]> groupBy(final IntFunction<K> classifier)
	{
		final Map<K, ArrayAccumulators.OfInt> accumulationMap = new HashMap<>();
		while (hasNext()) {
			final int next = nextInt();
			final K key = classifier.apply(next);
			accumulationMap.putIfAbsent(key, ArrayAccumulators.intAccumulator());
			accumulationMap.get(key).add(next);
		}
		final Map<K, int[]> grouped = new HashMap<>(accumulationMap.size());
		for (final K key : accumulationMap.keySet()) {
			grouped.put(key, accumulationMap.get(key).compress());
		}
		return grouped;
	}

	default <T> Flow<IntWith<T>> zipWith(final Iterable<T> other)
	{
		return zipWith(other.iterator());
	}

	default Flow<IntPair> zipWith(final IntFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default Flow<IntWithDouble> zipWith(final DoubleFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default Flow<IntWithLong> zipWith(final LongFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default IntFlow combineWith(final IntFlowIterable other, final IntBinaryOperator combiner)
	{
		return combineWith(other.iter(), combiner);
	}

	default IntFlow append(final IntFlowIterable other)
	{
		return append(other.iter());
	}

	default IntFlow insert(final IntFlowIterable other)
	{
		return insert(other.iter());
	}
}
