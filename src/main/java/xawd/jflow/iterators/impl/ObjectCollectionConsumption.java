/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import xawd.jflow.collections.FlowList;
import xawd.jflow.collections.FlowSet;
import xawd.jflow.collections.impl.FlowArrayList;
import xawd.jflow.collections.impl.FlowHashSet;
import xawd.jflow.collections.impl.ImmutableFlowList;
import xawd.jflow.collections.impl.UnmodifiableDelegatingFlowSet;
import xawd.jflow.iterators.Flow;

/**
 * @author ThomasB
 */
public final class ObjectCollectionConsumption
{
	private ObjectCollectionConsumption() {}

	public static <E, C extends Collection<E>> C toCollection(final Iterator<? extends E> iterator, final Supplier<C> collectionFactory)
	{
		final C container = collectionFactory.get();
		while (iterator.hasNext()) {
			container.add(iterator.next());
		}
		return container;
	}

	public static <E> FlowList<E> toList(final Flow<? extends E> iterator)
	{
		final FlowList<E> container = iterator.isSized()? new FlowArrayList<>(iterator.size()) : new FlowArrayList<>();
		while (iterator.hasNext()) {
			container.add(iterator.next());
		}
		return container;
	}

	@SuppressWarnings("unchecked")
	public static <E> FlowList<E> toImmutableList(final Flow<? extends E> iterator)
	{
		if (iterator.isSized()) {
			final Object[] cache = new Object[iterator.size()];
			int index = 0;
			while (iterator.hasNext()) {
				cache[index++] = iterator.next();
			}
			return new ImmutableFlowList<>(i -> (E) cache[i], cache.length);
		}
		else {
			final FlowList<E> mutable = new FlowArrayList<>();
			while (iterator.hasNext()) {
				mutable.add(iterator.next());
			}
			return new ImmutableFlowList<>(mutable::get, mutable.size());
		}
	}

	public static <E> FlowSet<E> toSet(final Flow<? extends E> iterator)
	{
		final FlowSet<E> container = iterator.isSized()? new FlowHashSet<>(iterator.size()) : new FlowHashSet<>();
		while (iterator.hasNext()) {
			container.add(iterator.next());
		}
		return container;
	}

	public static <E> FlowSet<E> toImmutableSet(final Flow<? extends E> iterator)
	{
		final FlowSet<E> container = iterator.isSized()? new FlowHashSet<>(iterator.size()) : new FlowHashSet<>();
		while (iterator.hasNext()) {
			container.add(iterator.next());
		}
		return new UnmodifiableDelegatingFlowSet<>(container);
	}

	public static <E, K, V> Map<K, V> toMap(final Iterator<? extends E> iterator, final Function<? super E, K> keyMapper, final Function<? super E, V> valueMapper)
	{
		final Map<K, V> collected = new HashMap<>();
		while (iterator.hasNext()) {
			final E next = iterator.next();
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

	public static <E, K> Map<K, List<E>> groupBy(final Iterator<? extends E> iterator, final Function<? super E, K> classifier)
	{
		final Map<K, List<E>> collected = new HashMap<>();
		while (iterator.hasNext()) {
			final E next = iterator.next();
			final K key = classifier.apply(next);
			collected.putIfAbsent(key, new ArrayList<>());
			collected.get(key).add(next);
		}
		return collected;
	}
}
