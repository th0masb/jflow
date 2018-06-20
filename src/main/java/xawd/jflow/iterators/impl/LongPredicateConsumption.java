/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.PrimitiveIterator;
import java.util.function.LongPredicate;

import xawd.jflow.iterators.misc.ArrayAccumulators;
import xawd.jflow.iterators.misc.LongPredicatePartition;
import xawd.jflow.iterators.misc.PredicateResult;

/**
 * @author ThomasB
 */
public final class LongPredicateConsumption
{
	public LongPredicateConsumption() {}

	public static PredicateResult allEqual(final PrimitiveIterator.OfLong source)
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
				return PredicateResult.FAIL;
			}
		}
		return PredicateResult.PASS;
	}

	public static PredicateResult allMatch(final PrimitiveIterator.OfLong source, final LongPredicate predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.nextLong())) {
				return PredicateResult.FAIL;
			}
		}
		return PredicateResult.PASS;
	}

	public static PredicateResult anyMatch(final PrimitiveIterator.OfLong source, final LongPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextLong())) {
				return PredicateResult.PASS;
			}
		}
		return PredicateResult.FAIL;
	}

	public static PredicateResult noneMatch(final PrimitiveIterator.OfLong source, final LongPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextLong())) {
				return PredicateResult.FAIL;
			}
		}
		return PredicateResult.PASS;
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
