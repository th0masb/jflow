/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.function.Predicate;

/**
 * @author ThomasB
 */
public class ObjectPredicateConsumption
{
	public ObjectPredicateConsumption()
	{
	}

	public static <E> boolean allEqual(AbstractRichIterator<? extends E> source)
	{
		source.relinquishOwnership();
		E last = null;
		while (source.hasNext()) {
			E next = source.nextImpl();
			if (last == null || last.equals(next)) {
				last = next;
			} else {
				return false;
			}
		}
		return true;
	}

	public static <E> boolean allMatch(AbstractRichIterator<? extends E> source,
			Predicate<? super E> predicate)
	{
		source.relinquishOwnership();
		while (source.hasNext()) {
			if (!predicate.test(source.nextImpl())) {
				return false;
			}
		}
		return true;
	}

	public static <E> boolean anyMatch(AbstractRichIterator<? extends E> source,
			Predicate<? super E> predicate)
	{
		source.relinquishOwnership();
		while (source.hasNext()) {
			if (predicate.test(source.nextImpl())) {
				return true;
			}
		}
		return false;
	}

	public static <E> boolean noneMatch(AbstractRichIterator<? extends E> source,
			Predicate<? super E> predicate)
	{
		source.relinquishOwnership();
		while (source.hasNext()) {
			if (predicate.test(source.nextImpl())) {
				return false;
			}
		}
		return true;
	}
}
