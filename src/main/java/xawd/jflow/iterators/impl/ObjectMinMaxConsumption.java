/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;

/**
 * @author ThomasB
 *
 */
public final class ObjectMinMaxConsumption
{
	private ObjectMinMaxConsumption() {}

	public static <E> Optional<E> findMin(final Iterator<? extends E> source, final Comparator<? super E> orderingFunction)
	{
		final Comparator<? super E> cf = orderingFunction;
		E min = null;
		while (source.hasNext()) {
			final E next = source.next();
			if (min == null || cf.compare(min, next) > 0) {
				min = next;
			}
		}
		return min == null? Optional.empty() : Optional.of(min);
	}

	public static <E> Optional<E> findMax(final Iterator<? extends E> source, final Comparator<? super E> orderingFunction)
	{
		final Comparator<? super E> cf = orderingFunction;
		E max = null;
		while (source.hasNext()) {
			final E next = source.next();
			if (max == null || cf.compare(max, next) < 0) {
				max = next;
			}
		}
		return max == null? Optional.empty() : Optional.of(max);
	}
}
