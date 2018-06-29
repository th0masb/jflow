/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.LongFunction;

/**
 * @author ThomasB
 *
 */
public final class LongCollectionConsumption
{
	private LongCollectionConsumption() {}

	public static long[] toArray(final PrimitiveIterator.OfLong iterator)
	{
		final OptionalInt size = ImplUtils.getSize(iterator);
		if (size.isPresent()) {
			final long[] cache = new long[size.getAsInt()];
			int index = 0;
			while (iterator.hasNext()) {
				cache[index++] = iterator.nextLong();
			}
			return cache;
		}
		else {
			final ArrayAccumulators.OfLong accumulater = ArrayAccumulators.longAccumulator();
			while (iterator.hasNext()) {
				accumulater.add(iterator.nextLong());
			}
			return accumulater.compress();
		}
	}

	public static <K, V> Map<K, V> toMap(final PrimitiveIterator.OfLong iterator, final LongFunction<K> keyMapper, final LongFunction<V> valueMapper)
	{
		final Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			final long next = iterator.nextLong();
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

	public static <K> Map<K, long[]> groupBy(final PrimitiveIterator.OfLong iterator, final LongFunction<K> classifier)
	{
		final Map<K, ArrayAccumulators.OfLong> accumulationMap = new HashMap<>();
		while (iterator.hasNext()) {
			final long next = iterator.nextLong();
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
