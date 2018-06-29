/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.PrimitiveIterator;
import java.util.function.DoublePredicate;

import xawd.jflow.iterators.misc.Bool;
import xawd.jflow.iterators.misc.DoublePredicatePartition;

/**
 * @author ThomasB
 */
public final class DoublePredicateConsumption
{
	private static final double EQUALITY_TOLERANCE = 0.00001;

	public DoublePredicateConsumption() {}

	public static Bool allEqual(final PrimitiveIterator.OfDouble source)
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
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static Bool allMatch(final PrimitiveIterator.OfDouble source, final DoublePredicate predicate)
	{
		while (source.hasNext()) {
			if (!predicate.test(source.nextDouble())) {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
	}

	public static Bool anyMatch(final PrimitiveIterator.OfDouble source, final DoublePredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextDouble())) {
				return Bool.TRUE;
			}
		}
		return Bool.FALSE;
	}

	public static Bool noneMatch(final PrimitiveIterator.OfDouble source, final DoublePredicate predicate)
	{
		while (source.hasNext()) {
			if (predicate.test(source.nextDouble())) {
				return Bool.FALSE;
			}
		}
		return Bool.TRUE;
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
