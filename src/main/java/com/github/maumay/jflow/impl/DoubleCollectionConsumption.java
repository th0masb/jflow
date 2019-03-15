/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleFunction;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author ThomasB
 *
 */
public final class DoubleCollectionConsumption
{
	private DoubleCollectionConsumption()
	{
	}

	public static <K, V> Map<K, V> toMap(AbstractDoubleIterator iterator,
			DoubleFunction<K> keyMapper, DoubleFunction<V> valueMapper)
	{
		Exceptions.require(iterator.hasOwnership());
		Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			double next = iterator.nextDoubleImpl();
			K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	public static <K> Map<K, double[]> groupBy(AbstractDoubleIterator iterator,
			DoubleFunction<K> classifier)
	{
		Exceptions.require(iterator.hasOwnership());
		Map<K, ArrayAccumulators.OfDouble> accumulationMap = new HashMap<>();
		while (iterator.hasNext()) {
			double next = iterator.nextDoubleImpl();
			K key = classifier.apply(next);
			accumulationMap.putIfAbsent(key, ArrayAccumulators.createDoubleAccumulator());
			accumulationMap.get(key).add(next);
		}
		Map<K, double[]> grouped = new HashMap<>(accumulationMap.size());
		for (K key : accumulationMap.keySet()) {
			grouped.put(key, accumulationMap.get(key).compress());
		}
		return grouped;
	}
}
