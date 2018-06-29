/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.PrimitiveIterator;
import java.util.function.LongPredicate;

import xawd.jflow.iterators.misc.Bool;
import xawd.jflow.iterators.misc.LongPredicatePartition;

/**
 * @author ThomasB
 */
public final class LongPredicateConsumption
{
	public LongPredicateConsumption() {}

	public static Bool allEqual(final PrimitiveIterator.OfLong source)
	{
		boolean initialised = false;
		long last = -1L;
		while (source.hasNext()) {
			final long next = source.nextLong();
			if (!initialised) {
				initialised = true;
				last = next;
			}
			else if (last == next) {
				last = next;
			}
			else {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static Bool allMatch(final PrimitiveIterator.OfLong source, final LongPredicate predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.nextLong())) {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static Bool anyMatch(final PrimitiveIterator.OfLong source, final LongPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextLong())) {
				return Bool.TRUE;
			}
		}
		return Bool.FALSE;
	}

	public static Bool noneMatch(final PrimitiveIterator.OfLong source, final LongPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextLong())) {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static LongPredicatePartition partition(final PrimitiveIterator.OfLong source, final LongPredicate predicate)
	{
		final ArrayAccumulators.OfLong accepted = ArrayAccumulators.longAccumulator(), rejected = ArrayAccumulators.longAccumulator();
		while (source.hasNext()) {
			final long next = source.nextLong();
			if (predicate.test(next)) {
				accepted.add(next);
			}
			else {
				rejected.add(next);
			}
		}
		return new LongPredicatePartition(accepted.compress(), rejected.compress());
	}
}
