/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongFunction;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author ThomasB
 *
 */
public final class LongCollectionConsumption
{
	private LongCollectionConsumption()
	{
	}

	public static <K, V> Map<K, V> toMap(AbstractLongIterator iterator, LongFunction<K> keyMapper,
			LongFunction<V> valueMapper)
	{
		Exceptions.require(iterator.hasOwnership());
		Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			long next = iterator.nextLongImpl();
			K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	public static <K> Map<K, long[]> groupBy(AbstractLongIterator iterator,
			LongFunction<K> classifier)
	{
		Exceptions.require(iterator.hasOwnership());
		Map<K, ArrayAccumulators.OfLong> accumulationMap = new HashMap<>();
		while (iterator.hasNext()) {
			long next = iterator.nextLongImpl();
			K key = classifier.apply(next);
			accumulationMap.putIfAbsent(key, ArrayAccumulators.createLongAccumulator());
			accumulationMap.get(key).add(next);
		}
		Map<K, long[]> grouped = new HashMap<>(accumulationMap.size());
		for (K key : accumulationMap.keySet()) {
			grouped.put(key, accumulationMap.get(key).compress());
		}
		return grouped;
	}
}
