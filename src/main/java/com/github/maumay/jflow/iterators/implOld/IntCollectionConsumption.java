/**
 *
 */
package com.github.maumay.jflow.iterators.implOld;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntFunction;

import com.github.maumay.jflow.impl.ArrayAccumulators;

/**
 * @author ThomasB
 *
 */
public final class IntCollectionConsumption
{
	private IntCollectionConsumption()
	{
	}

	public static int[] toArray(PrimitiveIterator.OfInt iterator)
	{
		OptionalInt size = ImplUtils.getSize(iterator);
		if (size.isPresent()) {
			int[] cache = new int[size.getAsInt()];
			int index = 0;
			while (iterator.hasNext()) {
				cache[index++] = iterator.nextInt();
			}
			return cache;
		} else {
			ArrayAccumulators.OfInt accumulater = ArrayAccumulators.createInt();
			while (iterator.hasNext()) {
				accumulater.add(iterator.nextInt());
			}
			return accumulater.compress();
		}
	}

	public static <K, V> Map<K, V> toMap(PrimitiveIterator.OfInt iterator,
			IntFunction<? extends K> keyMapper, IntFunction<? extends V> valueMapper)
	{
		Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			int next = iterator.nextInt();
			K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	public static <K> Map<K, int[]> groupBy(PrimitiveIterator.OfInt iterator,
			IntFunction<? extends K> classifier)
	{
		Map<K, ArrayAccumulators.OfInt> accumulationMap = new HashMap<>();
		while (iterator.hasNext()) {
			int next = iterator.nextInt();
			K key = classifier.apply(next);
			accumulationMap.putIfAbsent(key, ArrayAccumulators.createInt());
			accumulationMap.get(key).add(next);
		}
		Map<K, int[]> grouped = new HashMap<>(accumulationMap.size());
		for (K key : accumulationMap.keySet()) {
			grouped.put(key, accumulationMap.get(key).compress());
		}
		return grouped;
	}
}
