/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ThomasB
 */
public class ObjectCollectionConsumption
{
	private ObjectCollectionConsumption()
	{
	}

	public static <E, C extends Collection<E>> C toCollection(
			AbstractRichIterator<? extends E> iterator, Supplier<C> collectionFactory)
	{
		iterator.relinquishOwnership();
		C container = collectionFactory.get();
		while (iterator.hasNext()) {
			container.add(iterator.nextImpl());
		}
		return container;
	}

	public static <E, K, V> Map<K, V> toMap(AbstractRichIterator<? extends E> iterator,
			Function<? super E, ? extends K> keyMapper,
			Function<? super E, ? extends V> valueMapper)
	{
		iterator.relinquishOwnership();
		Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			E nextImpl = iterator.nextImpl();
			K key = keyMapper.apply(nextImpl);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(nextImpl));
			}
		}
		return collected;
	}

	public static <E, K> Map<K, List<E>> groupBy(AbstractRichIterator<? extends E> iterator,
			Function<? super E, ? extends K> classifier)
	{
		iterator.relinquishOwnership();
		Map<K, List<E>> collected = new HashMap<>();
		while (iterator.hasNext()) {
			E nextImpl = iterator.nextImpl();
			K key = classifier.apply(nextImpl);
			collected.putIfAbsent(key, new ArrayList<>());
			collected.get(key).add(nextImpl);
		}
		return collected;
	}
}
