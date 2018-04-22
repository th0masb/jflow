package xawd.jflow;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

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
public interface IntFlow extends IterableInts
{
	@Override
	SkippableIntIterator iterator();
	
	IntFlow map(final IntUnaryOperator f);

	<T> ObjectFlow<T> mapToObject(IntFunction<T> f);

	DoubleFlow mapToDouble(IntToDoubleFunction f);

	LongFlow mapToLong(IntToLongFunction f);

	<T> ObjectFlow<IntWith<T>> zipWith(final Iterable<T> other);

	ObjectFlow<IntPair> zipWith(final IterableInts other);

	ObjectFlow<IntWithDouble> zipWith(final IterableDoubles other);

	ObjectFlow<IntWithLong> zipWith(final IterableLongs other);

	ObjectFlow<IntPair> enumerate();

	IntFlow cycle();
	
	IntFlow repeat(int ntimes);

	IntFlow take(final int n);

	IntFlow takeWhile(final IntPredicate p);

	IntFlow drop(final int n);

	IntFlow dropWhile(final IntPredicate p);

	IntFlow filter(final IntPredicate p);

	IntFlow append(IterableInts other);
	
	IntFlow append(int x);

	IntFlow insert(IterableInts other);
	
	IntFlow insert(int x);
	
	int minByKey(int defaultValue, final IntToDoubleFunction key);

	OptionalInt minByKey(final IntToDoubleFunction key);

	<C extends Comparable<C>> OptionalInt minByObjectKey(final IntFunction<C> key);

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
		final SkippableIntIterator iterator = iterator();
		final ArrayAccumulators.OfInt accumulater = ArrayAccumulators.intAccumulator();
		while (iterator.hasNext()) {
			accumulater.add(iterator.nextInt());
		}
		return accumulater.compress();
	}
	
	default <K, V> Map<K, V> toMap(final IntFunction<K> keyMapper, final IntFunction<V> valueMapper)
	{
		final Map<K, V> collected = new HashMap<>();
		for (final SkippableIntIterator itr = iterator(); itr.hasNext();) {
			final int next = itr.nextInt();
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
	
	default IntStream stream()
	{
		return StreamSupport.intStream(spliterator(), false) ;
	}
	
//	default Stream sortByKey(final ToLongFunction<? super T> key)
//	{
//		return stream().sorted((a, b) -> {
//			final long aMap = key.applyAsLong(a), bMap = key.applyAsLong(b);
//			return aMap < bMap ? -1 : a == b? 0 : 1 ;
//		});
//	}
//
//	default  Stream sortByObjectKey(final Function<? super T, C> key)
//	{
//		return stream().sorted((a, b) -> key.apply(a).compareTo(key.apply(b)));
//	}
}
