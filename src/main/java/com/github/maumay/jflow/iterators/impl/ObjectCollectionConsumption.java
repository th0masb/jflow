/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ThomasB
 */
public final class ObjectCollectionConsumption
{
	private ObjectCollectionConsumption()
	{
	}

	public static <E, C extends Collection<E>> C toCollection(
			final Iterator<? extends E> iterator, final Supplier<C> collectionFactory)
	{
		final C container = collectionFactory.get();
		while (iterator.hasNext()) {
			container.add(iterator.next());
		}
		return container;
	}

	// public static <E> Seq<E> toSeq(final Flow<? extends E> iterator)
	// {
	// if (iterator.size().isPresent()) {
	// return new VectorSeq<>(iterator);
	// }
	// else {
	// final List<E> mutable = new ArrayList<>();
	// while (iterator.hasNext()) {
	// mutable.add(iterator.next());
	// }
	// return new VectorSeq<>(mutable);
	// }
	// }

	// public static <E> FSet<E> toMutableSet(final Flow<? extends E> iterator)
	// {
	// final OptionalInt size = iterator.size();
	// final FSet<E> container = size.isPresent()? new
	// FlowHashSet<>(size.getAsInt()) : new FlowHashSet<>();
	// while (iterator.hasNext()) {
	// container.add(iterator.next());
	// }
	// return container;
	// }
	//
	// public static <E> FSet<E> toImmutableSet(final Flow<? extends E> iterator)
	// {
	// final OptionalInt size = iterator.size();
	// final FSet<E> container = size.isPresent()? new
	// FlowHashSet<>(size.getAsInt()) : new FlowHashSet<>();
	// while (iterator.hasNext()) {
	// container.add(iterator.next());
	// }
	// return new UnmodifiableDelegatingFlowSet<>(container);
	// }

	public static <E, K, V> Map<K, V> toMap(Iterator<? extends E> iterator,
			Function<? super E, ? extends K> keyMapper,
			Function<? super E, ? extends V> valueMapper)
	{
		Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			E next = iterator.next();
			K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	public static <E, K> Map<K, List<E>> groupBy(Iterator<? extends E> iterator,
			Function<? super E, ? extends K> classifier)
	{
		Map<K, List<E>> collected = new HashMap<>();
		while (iterator.hasNext()) {
			E next = iterator.next();
			K key = classifier.apply(next);
			collected.putIfAbsent(key, new ArrayList<>());
			collected.get(key).add(next);
		}
		return collected;
	}
}
