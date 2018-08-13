/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.PrimitiveIterator;
import java.util.function.IntPredicate;

import xawd.jflow.iterators.misc.IntPredicatePartition;

/**
 * @author ThomasB
 */
public final class IntPredicateConsumption
{
	public IntPredicateConsumption() {}

	public static boolean allEqual(final PrimitiveIterator.OfInt source)
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
				return false;
			}
		}
		return true;
	}

	public static boolean allMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.nextInt())) {
				return false;
			}
		}
		return true;
	}

	public static boolean anyMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextInt())) {
				return true;
			}
		}
		return false;
	}

	public static boolean noneMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextInt())) {
				return false;
			}
		}
		return true;
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
