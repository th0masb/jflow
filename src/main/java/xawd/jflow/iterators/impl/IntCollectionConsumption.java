/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.function.IntFunction;

import xawd.jflow.iterators.OptionallySized;
import xawd.jflow.iterators.misc.ArrayAccumulators;

/**
 * @author ThomasB
 *
 */
public final class IntCollectionConsumption
{
	private IntCollectionConsumption() {}

	public static int[] toArray(final PrimitiveIterator.OfInt iterator)
	{
		if (ImplUtils.isSized(iterator)) {
			final int[] cache = new int[((OptionallySized) iterator).size()];
			int index = 0;
			while (iterator.hasNext()) {
				cache[index++] = iterator.nextInt();
			}
			return cache;
		}
		else {
			final ArrayAccumulators.OfInt accumulater = ArrayAccumulators.intAccumulator();
			while (iterator.hasNext()) {
				accumulater.add(iterator.nextInt());
			}
			return accumulater.compress();
		}
	}

	public static <K, V> Map<K, V> toMap(final PrimitiveIterator.OfInt iterator, final IntFunction<K> keyMapper, final IntFunction<V> valueMapper)
	{
		final Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			final int next = iterator.nextInt();
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

	public static <K> Map<K, int[]> groupBy(final PrimitiveIterator.OfInt iterator, final IntFunction<K> classifier)
	{
		final Map<K, ArrayAccumulators.OfInt> accumulationMap = new HashMap<>();
		while (iterator.hasNext()) {
			final int next = iterator.nextInt();
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
