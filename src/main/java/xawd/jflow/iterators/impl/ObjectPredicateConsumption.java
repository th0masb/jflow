/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Iterator;
import java.util.function.Predicate;

import xawd.jflow.iterators.misc.Bool;

/**
 * @author ThomasB
 */
public final class ObjectPredicateConsumption
{
	public ObjectPredicateConsumption() {}

	public static <E> Bool allEqual(final Iterator<? extends E> source)
	{
		E last = null;
		while (source.hasNext()) {
			final E next = source.next();
			if (last == null || last.equals(next)) {
				last = next;
			}
			else {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static <E> Bool allMatch(final Iterator<? extends E> source, final Predicate<? super E> predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.next())) {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static <E> Bool anyMatch(final Iterator<? extends E> source, final Predicate<? super E> predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.next())) {
				return Bool.TRUE;
			}
		}
		return Bool.FALSE;
	}

	public static <E> Bool noneMatch(final Iterator<? extends E> source, final Predicate<? super E> predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.next())) {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}
}
