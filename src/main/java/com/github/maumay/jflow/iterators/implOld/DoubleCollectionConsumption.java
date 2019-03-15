/**
 *
 */
package com.github.maumay.jflow.iterators.implOld;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.DoubleFunction;

import com.github.maumay.jflow.impl.ArrayAccumulators;

/**
 * @author ThomasB
 *
 */
public final class DoubleCollectionConsumption
{
	private DoubleCollectionConsumption()
	{
	}

	public static double[] toArray(final PrimitiveIterator.OfDouble iterator)
	{
		final OptionalInt size = ImplUtils.getSize(iterator);
		if (size.isPresent()) {
			final double[] cache = new double[size.getAsInt()];
			int index = 0;
			while (iterator.hasNext()) {
				cache[index++] = iterator.nextDouble();
			}
			return cache;
		} else {
			final ArrayAccumulators.OfDouble accumulater = ArrayAccumulators
					.createDoubleAccumulator();
			while (iterator.hasNext()) {
				accumulater.add(iterator.nextDouble());
			}
			return accumulater.compress();
		}
	}

	public static <K, V> Map<K, V> toMap(final PrimitiveIterator.OfDouble iterator,
			final DoubleFunction<K> keyMapper, final DoubleFunction<V> valueMapper)
	{
		final Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			final double next = iterator.nextDouble();
			final K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	public static <K> Map<K, double[]> groupBy(final PrimitiveIterator.OfDouble iterator,
			final DoubleFunction<K> classifier)
	{
		final Map<K, ArrayAccumulators.OfDouble> accumulationMap = new HashMap<>();
		while (iterator.hasNext()) {
			final double next = iterator.nextDouble();
			final K key = classifier.apply(next);
			accumulationMap.putIfAbsent(key, ArrayAccumulators.createDoubleAccumulator());
			accumulationMap.get(key).add(next);
		}
		final Map<K, double[]> grouped = new HashMap<>(accumulationMap.size());
		for (final K key : accumulationMap.keySet()) {
			grouped.put(key, accumulationMap.get(key).compress());
		}
		return grouped;
	}
}
