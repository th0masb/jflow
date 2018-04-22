package xawd.jflow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.SkippableIntIterator;
import xawd.jflow.primitiveiterables.IterableDoubles;
import xawd.jflow.primitiveiterables.IterableInts;
import xawd.jflow.primitiveiterables.IterableLongs;
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
	IntFlow map(final IntUnaryOperator f);

	<T> Flow<T> mapToObject(IntFunction<T> f);

	DoubleFlow mapToDouble(IntToDoubleFunction f);

	IntFlow mapToInt(IntUnaryOperator f);
	
	<T> Flow<IntWith<T>> zipWith(final Iterator<T> other);

	default <T> Flow<IntWith<T>> zipWith(final Iterable<T> other) {
		return zipWith(other.iterator());
	}

	Flow<IntPair> zipWith(final PrimitiveIterator.OfInt other);
	
	default Flow<IntPair> zipWith(final IterableInts other) {
		return zipWith(other.iterator());
	}

	Flow<IntWithDouble> zipWith(final PrimitiveIterator.OfDouble other);
	
	default Flow<IntWithDouble> zipWith(final IterableDoubles other) {
		return zipWith(other.iterator());
	}

	Flow<IntWithLong> zipWith(final PrimitiveIterator.OfLong other);
	
	default Flow<IntWithLong> zipWith(final IterableLongs other) {
		return zipWith(other.iterator());
	}

	Flow<IntPair> enumerate();

	IntFlow take(final int n);

	IntFlow takeWhile(final IntPredicate p);

	IntFlow drop(final int n);

	IntFlow dropWhile(final IntPredicate p);

	IntFlow filter(final IntPredicate p);
	
	IntFlow append(int x);

	IntFlow append(PrimitiveIterator.OfInt other);
	
	default IntFlow append(final IterableInts other) {
		return append(other.iterator());
	}
	
	IntFlow insert(PrimitiveIterator.OfInt other);
	
	default IntFlow insert(final IterableInts other) {
		return insert(other.iterator());
	}
	
	IntFlow insert(int x);
	
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
			final int next = next();
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
}
