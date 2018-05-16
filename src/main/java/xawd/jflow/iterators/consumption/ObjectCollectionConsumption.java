/**
 *
 */
package xawd.jflow.iterators.consumption;

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
	private ObjectCollectionConsumption() {}

	public static <E, C extends Collection<E>> C toCollection(final Iterator<? extends E> iterator, final Supplier<C> collectionFactory)
	{
		final C container = collectionFactory.get();
		while (iterator.hasNext()) {
			container.add(iterator.next());
		}
		return container;
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
