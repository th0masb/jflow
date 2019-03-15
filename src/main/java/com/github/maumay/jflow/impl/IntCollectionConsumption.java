/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author ThomasB
 *
 */
public final class IntCollectionConsumption
{
	private IntCollectionConsumption()
	{
	}

	public static <K, V> Map<K, V> toMap(AbstractIntIterator iterator,
			IntFunction<? extends K> keyMapper, IntFunction<? extends V> valueMapper)
	{
		Exceptions.require(iterator.hasOwnership());
		Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			int next = iterator.nextIntImpl();
			K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	public static <K> Map<K, int[]> groupBy(AbstractIntIterator iterator,
			IntFunction<? extends K> classifier)
	{
		Exceptions.require(iterator.hasOwnership());
		Map<K, ArrayAccumulators.OfInt> accumulationMap = new HashMap<>();
		while (iterator.hasNext()) {
			int next = iterator.nextIntImpl();
			K key = classifier.apply(next);
			accumulationMap.putIfAbsent(key, ArrayAccumulators.createIntAccumulator());
			accumulationMap.get(key).add(next);
		}
		Map<K, int[]> grouped = new HashMap<>(accumulationMap.size());
		for (K key : accumulationMap.keySet()) {
			grouped.put(key, accumulationMap.get(key).compress());
		}
		return grouped;
	}
}
