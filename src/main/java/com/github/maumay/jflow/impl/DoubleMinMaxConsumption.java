/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.OptionalDouble;

/**
 * @author ThomasB
 */
public class DoubleMinMaxConsumption
{
	private DoubleMinMaxConsumption()
	{
	}

	public static OptionalDouble findMinOption(AbstractDoubleIterator source)
	{
		source.relinquishOwnership();
		boolean found = false;
		double min = Double.POSITIVE_INFINITY;
		while (source.hasNext()) {
			double next = source.nextDoubleImpl();
			if (!found && !Double.isNaN(next)) {
				found = true;
				min = next;
			} else if (next < min) {
				min = next;
			}
		}
		return found ? OptionalDouble.of(min) : OptionalDouble.empty();
	}

	public static double findMin(AbstractDoubleIterator source)
	{
		source.relinquishOwnership();
		boolean found = false;
		double min = Double.POSITIVE_INFINITY;
		while (source.hasNext()) {
			double next = source.nextDoubleImpl();
			if (!found && !Double.isNaN(next)) {
				found = true;
				min = next;
			} else if (next < min) {
				min = next;
			}
		}
		if (found) {
			return min;
		} else {
			throw new IllegalStateException();
		}
	}

	public static OptionalDouble findMaxOption(AbstractDoubleIterator source)
	{
		source.relinquishOwnership();
		boolean found = false;
		double max = Double.NEGATIVE_INFINITY;
		while (source.hasNext()) {
			double next = source.nextDoubleImpl();
			if (!found && !Double.isNaN(next)) {
				found = true;
				max = next;
			} else if (next > max) {
				max = next;
			}
		}
		return found ? OptionalDouble.of(max) : OptionalDouble.empty();
	}

	public static double findMax(AbstractDoubleIterator source)
	{
		source.relinquishOwnership();
		boolean found = false;
		double max = Double.NEGATIVE_INFINITY;
		while (source.hasNext()) {
			double next = source.nextDoubleImpl();
			if (!found && !Double.isNaN(next)) {
				found = true;
				max = next;
			} else if (next > max) {
				max = next;
			}
		}
		if (found) {
			return max;
		} else {
			throw new IllegalStateException();
		}
	}

}
