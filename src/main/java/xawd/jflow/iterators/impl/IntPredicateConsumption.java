/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.PrimitiveIterator;
import java.util.function.IntPredicate;

import xawd.jflow.iterators.misc.ArrayAccumulators;
import xawd.jflow.iterators.misc.IntPredicatePartition;
import xawd.jflow.iterators.misc.PredicateResult;

/**
 * @author ThomasB
 */
public final class IntPredicateConsumption
{
	public IntPredicateConsumption() {}

	public static PredicateResult allEqual(final PrimitiveIterator.OfInt source)
	{
		boolean initialised = false;
		int last = -1;
		while (source.hasNext()) {
			final int next = source.nextInt();
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

	public static PredicateResult allMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.nextInt())) {
				return PredicateResult.FAIL;
			}
		}
		return PredicateResult.PASS;
	}

	public static PredicateResult anyMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextInt())) {
				return PredicateResult.PASS;
			}
		}
		return PredicateResult.FAIL;
	}

	public static PredicateResult noneMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextInt())) {
				return PredicateResult.FAIL;
			}
		}
		return PredicateResult.PASS;
	}

	public static IntPredicatePartition partition(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		final ArrayAccumulators.OfInt accepted = ArrayAccumulators.intAccumulator(), rejected = ArrayAccumulators.intAccumulator();
		while (source.hasNext()) {
			final int next = source.nextInt();
			if (predicate.test(next)) {
				accepted.add(next);
			}
			else {
				rejected.add(next);
			}
		}
		return new IntPredicatePartition(accepted.compress(), rejected.compress());
	}
}
