/**
 *
 */
package xawd.jflow.iterators.consumption;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import xawd.jflow.iterators.misc.PredicatePartition;

/**
 * @author ThomasB
 */
public final class ObjectPredicateConsumption
{
	public ObjectPredicateConsumption() {}

	public static <E> boolean allEqual(final Iterator<? extends E> source)
	{
		E last = null;
		while (source.hasNext()) {
			final E next = source.next();
			if (last == null || last.equals(next)) {
				last = next;
			}
			else {
				return false;
			}
		}
		return true;
	}

	public static <E> boolean allMatch(final Iterator<? extends E> source, final Predicate<? super E> predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.next())) {
				return false;
			}
		}
		return true;
	}

	public static <E> boolean anyMatch(final Iterator<? extends E> source, final Predicate<? super E> predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.next())) {
				return true;
			}
		}
		return false;
	}

	public static <E> boolean noneMatch(final Iterator<? extends E> source, final Predicate<? super E> predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.next())) {
				return false;
			}
		}
		return true;
	}

	public static <E> PredicatePartition<E> partition(final Iterator<? extends E> source, final Predicate<? super E> predicate)
	{
		final List<E> accepted = new ArrayList<>(), rejected = new ArrayList<>();
		while (source.hasNext()) {
			final E next = source.next();
			if (predicate.test(next)) {
				accepted.add(next);
			}
			else {
				rejected.add(next);
			}
		}
		return new PredicatePartition<>(accepted, rejected);
	}
}
