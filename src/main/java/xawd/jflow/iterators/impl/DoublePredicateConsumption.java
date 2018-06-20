/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.PrimitiveIterator;
import java.util.function.DoublePredicate;

import xawd.jflow.iterators.misc.ArrayAccumulators;
import xawd.jflow.iterators.misc.DoublePredicatePartition;
import xawd.jflow.iterators.misc.PredicateResult;

/**
 * @author ThomasB
 */
public final class DoublePredicateConsumption
{
	private static final double EQUALITY_TOLERANCE = 0.00001;

	public DoublePredicateConsumption() {}

	public static PredicateResult allEqual(final PrimitiveIterator.OfDouble source)
	{
		boolean initialised = false;
		double last = -1;
		while (source.hasNext()) {
			final double next = source.nextDouble();
			if (!initialised) {
				initialised = true;
				last = next;
			}
			else if (Math.abs(last - next) < EQUALITY_TOLERANCE) {
				last = next;
			}
			else {
				return PredicateResult.FAIL;
			}
		}
		return PredicateResult.PASS;
	}

	public static PredicateResult allMatch(final PrimitiveIterator.OfDouble source, final DoublePredicate predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.nextDouble())) {
				return PredicateResult.FAIL;
			}
		}
		return PredicateResult.PASS;
	}

	public static PredicateResult anyMatch(final PrimitiveIterator.OfDouble source, final DoublePredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextDouble())) {
				return PredicateResult.PASS;
			}
		}
		return PredicateResult.FAIL;
	}

	public static PredicateResult noneMatch(final PrimitiveIterator.OfDouble source, final DoublePredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextDouble())) {
				return PredicateResult.FAIL;
			}
		}
		return PredicateResult.PASS;
	}

	public static DoublePredicatePartition partition(final PrimitiveIterator.OfDouble source, final DoublePredicate predicate)
	{
		final ArrayAccumulators.OfDouble accepted = ArrayAccumulators.doubleAccumulator(), rejected = ArrayAccumulators.doubleAccumulator();
		while (source.hasNext()) {
			final double next = source.nextDouble();
			if (predicate.test(next)) {
				accepted.add(next);
			}
			else {
				rejected.add(next);
			}
		}
		return new DoublePredicatePartition(accepted.compress(), rejected.compress());
	}
}
