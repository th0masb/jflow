/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.function.DoublePredicate;

/**
 * @author ThomasB
 */
public final class DoublePredicateConsumption
{
	private static final double EQUALITY_TOLERANCE = 0.00001;

	public DoublePredicateConsumption()
	{
	}

	public static boolean allEqual(AbstractDoubleIterator source)
	{
		source.relinquishOwnership();
		boolean initialised = false;
		double last = -1;
		while (source.hasNext()) {
			double next = source.nextDoubleImpl();
			if (!initialised) {
				initialised = true;
				last = next;
			} else if (Math.abs(last - next) < EQUALITY_TOLERANCE) {
				last = next;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean allMatch(AbstractDoubleIterator source, DoublePredicate predicate)
	{
		source.relinquishOwnership();
		while (source.hasNext()) {
			if (!predicate.test(source.nextDoubleImpl())) {
				return false;
			}
		}
		return true;
	}

	public static boolean anyMatch(AbstractDoubleIterator source, DoublePredicate predicate)
	{
		source.relinquishOwnership();
		while (source.hasNext()) {
			if (predicate.test(source.nextDoubleImpl())) {
				return true;
			}
		}
		return false;
	}

	public static boolean noneMatch(AbstractDoubleIterator source, DoublePredicate predicate)
	{
		source.relinquishOwnership();
		while (source.hasNext()) {
			if (predicate.test(source.nextDoubleImpl())) {
				return false;
			}
		}
		return true;
	}
}
