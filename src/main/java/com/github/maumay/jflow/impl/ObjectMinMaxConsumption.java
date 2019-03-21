/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.Comparator;
import java.util.Optional;

/**
 * @author ThomasB
 *
 */
public final class ObjectMinMaxConsumption
{
	private ObjectMinMaxConsumption()
	{
	}

	public static <E> Optional<E> findMin(AbstractRichIterator<? extends E> source,
			Comparator<? super E> orderingFunction)
	{
		source.relinquishOwnership();
		Comparator<? super E> cf = orderingFunction;
		E min = null;
		while (source.hasNext()) {
			E next = source.nextImpl();
			if (min == null || cf.compare(min, next) > 0) {
				min = next;
			}
		}
		return min == null ? Optional.empty() : Optional.of(min);
	}

	public static <E> Optional<E> findMax(AbstractRichIterator<? extends E> source,
			Comparator<? super E> orderingFunction)
	{
		source.relinquishOwnership();
		Comparator<? super E> cf = orderingFunction;
		E max = null;
		while (source.hasNext()) {
			E next = source.nextImpl();
			if (max == null || cf.compare(max, next) < 0) {
				max = next;
			}
		}
		return max == null ? Optional.empty() : Optional.of(max);
	}
}
