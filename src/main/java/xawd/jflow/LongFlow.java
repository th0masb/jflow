package xawd.jflow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

import xawd.jflow.iterators.SkippableLongIterator;
import xawd.jflow.primitiveiterables.IterableDoubles;
import xawd.jflow.primitiveiterables.IterableInts;
import xawd.jflow.primitiveiterables.IterableLongs;
import xawd.jflow.zippedpairs.DoubleWithLong;
import xawd.jflow.zippedpairs.IntWithLong;
import xawd.jflow.zippedpairs.LongPair;
import xawd.jflow.zippedpairs.LongWith;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface LongFlow extends SkippableLongIterator
{
	LongFlow map(final LongUnaryOperator f);

	<T> ObjectFlow<T> mapToObject(LongFunction<T> f);

	DoubleFlow mapToDouble(LongToDoubleFunction f);

	LongFlow mapToInt(LongToIntFunction f);
	
	<T> ObjectFlow<LongWith<T>> zipWith(final Iterator<T> other);

	default <T> ObjectFlow<LongWith<T>> zipWith(final Iterable<T> other) {
		return zipWith(other.iterator());
	}

	ObjectFlow<LongPair> zipWith(final PrimitiveIterator.OfLong other);
	
	default ObjectFlow<LongPair> zipWith(final IterableLongs other) {
		return zipWith(other.iterator());
	}

	ObjectFlow<DoubleWithLong> zipWith(final PrimitiveIterator.OfDouble other);
	
	default ObjectFlow<DoubleWithLong> zipWith(final IterableDoubles other) {
		return zipWith(other.iterator());
	}

	ObjectFlow<IntWithLong> zipWith(final PrimitiveIterator.OfInt other);
	
	default ObjectFlow<IntWithLong> zipWith(final IterableInts other) {
		return zipWith(other.iterator());
	}

	ObjectFlow<IntWithLong> enumerate();

	LongFlow take(final int n);

	LongFlow takeWhile(final LongPredicate p);

	LongFlow drop(final int n);

	LongFlow dropWhile(final LongPredicate p);

	LongFlow filter(final LongPredicate p);
	
	LongFlow append(long x);

	LongFlow append(PrimitiveIterator.OfLong other);
	
	default LongFlow append(final IterableLongs other) {
		return append(other.iterator());
	}
	
	LongFlow insert(PrimitiveIterator.OfLong other);
	
	default LongFlow insert(final IterableLongs other) {
		return insert(other.iterator());
	}
	
	LongFlow insert(long x);
	
	OptionalLong min();
	
	long min(long defaultValue);
	
	long minByKey(long defaultValue, final LongToDoubleFunction key);

	OptionalLong minByKey(final LongToDoubleFunction key);

	<C extends Comparable<C>> OptionalLong minByObjectKey(final LongFunction<C> key);
	
	OptionalLong max();
	
	long max(long defaultValue);

	long maxByKey(long defaultValue, final LongToDoubleFunction key);

	OptionalLong maxByKey(final LongToDoubleFunction key);

	<C extends Comparable<C>> OptionalLong maxByObjectKey(final LongFunction<C> key);
	
	boolean allMatch(final LongPredicate predicate);

	boolean anyMatch(final LongPredicate predicate);

	boolean noneMatch(final LongPredicate predicate);
	
	int count();
	
	long reduce(long id, LongBinaryOperator reducer);
	
	OptionalLong reduce(LongBinaryOperator reducer);
	
	LongFlow accumulate(LongBinaryOperator accumulator);
	
	LongFlow accumulate(long id, LongBinaryOperator accumulator);
	
	default long[] toArray()
	{
		final ArrayAccumulators.OfLong accumulater = ArrayAccumulators.longAccumulator();
		while (hasNext()) {
			accumulater.add(nextLong());
		}
		return accumulater.compress();
	}
	
	default <K, V> Map<K, V> toMap(final LongFunction<K> keyMapper, final LongFunction<V> valueMapper)
	{
		final Map<K, V> collected = new HashMap<>();
		while (hasNext()) {
			final long next = nextLong();
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

	default <K> Map<K, long[]> groupBy(final LongFunction<K> classifier)
	{
		final Map<K, ArrayAccumulators.OfLong> accumulationMap = new HashMap<>();
		while (hasNext()) {
			final long next = next();
			final K key = classifier.apply(next);
			accumulationMap.putIfAbsent(key, ArrayAccumulators.longAccumulator());
			accumulationMap.get(key).add(next);
		}
		final Map<K, long[]> grouped = new HashMap<>(accumulationMap.size());
		for (final K key : accumulationMap.keySet()) {
			grouped.put(key, accumulationMap.get(key).compress());
		}
		return grouped;
	}
}
