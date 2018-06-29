/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.PrimitiveIterator;
import java.util.function.IntPredicate;

import xawd.jflow.iterators.misc.ArrayAccumulators;
import xawd.jflow.iterators.misc.IntPredicatePartition;
import xawd.jflow.valuewrappers.Bool;

/**
 * @author ThomasB
 */
public final class IntPredicateConsumption
{
	public IntPredicateConsumption() {}

	public static Bool allEqual(final PrimitiveIterator.OfInt source)
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
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static Bool allMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.nextInt())) {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static Bool anyMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextInt())) {
				return Bool.TRUE;
			}
		}
		return Bool.FALSE;
	}

	public static Bool noneMatch(final PrimitiveIterator.OfInt source, final IntPredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextInt())) {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
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
