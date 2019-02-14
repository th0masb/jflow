/**
 *
 */
package maumay.jflow.iterators.impl;

import java.util.OptionalDouble;
import java.util.function.DoubleUnaryOperator;

import maumay.jflow.iterators.EnhancedDoubleIterator;

/**
 * @author ThomasB
 */
public class DoubleMinMaxConsumption
{
	private DoubleMinMaxConsumption()
	{
	}

	public static OptionalDouble findMinOption(EnhancedDoubleIterator source)
	{
		boolean found = false;
		double min = Double.POSITIVE_INFINITY;
		while (source.hasNext()) {
			double next = source.nextDouble();
			if (!found && !Double.isNaN(next)) {
				found = true;
				min = next;
			} else if (next < min) {
				min = next;
			}
		}
		return found ? OptionalDouble.of(min) : OptionalDouble.empty();
	}

	public static double findMin(EnhancedDoubleIterator source)
	{
		boolean found = false;
		double min = Double.POSITIVE_INFINITY;
		while (source.hasNext()) {
			double next = source.nextDouble();
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

	public static OptionalDouble findMin(EnhancedDoubleIterator source,
			DoubleUnaryOperator key)
	{
		boolean found = false;
		double minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (source.hasNext()) {
			double nextKey = source.nextDouble();
			double nextVal = key.applyAsDouble(nextKey);

			if (!found && !Double.isNaN(nextVal)) {
				found = true;
				minKey = nextKey;
				minVal = nextVal;
			} else if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
			}
		}
		return found ? OptionalDouble.of(minKey) : OptionalDouble.empty();
	}

	public static double findMin(EnhancedDoubleIterator source, double defaultVal,
			DoubleUnaryOperator key)
	{
		boolean found = false;
		double minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (source.hasNext()) {
			double nextKey = source.nextDouble();
			double nextVal = key.applyAsDouble(nextKey);

			if (!found && !Double.isNaN(nextVal)) {
				found = true;
				minKey = nextKey;
				minVal = nextVal;
			} else if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
			}
		}
		return found ? minKey : defaultVal;
	}

	public static OptionalDouble findMaxOption(EnhancedDoubleIterator source)
	{
		boolean found = false;
		double max = Double.NEGATIVE_INFINITY;
		while (source.hasNext()) {
			double next = source.nextDouble();
			if (!found && !Double.isNaN(next)) {
				found = true;
				max = next;
			} else if (next > max) {
				max = next;
			}
		}
		return found ? OptionalDouble.of(max) : OptionalDouble.empty();
	}

	public static double findMax(EnhancedDoubleIterator source)
	{
		boolean found = false;
		double max = Double.NEGATIVE_INFINITY;
		while (source.hasNext()) {
			double next = source.nextDouble();
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

	public static OptionalDouble findMaxOption(EnhancedDoubleIterator source,
			DoubleUnaryOperator key)
	{
		boolean found = false;
		double maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (source.hasNext()) {
			double nextKey = source.nextDouble();
			double nextVal = key.applyAsDouble(nextKey);

			if (!found && !Double.isNaN(nextVal)) {
				found = true;
				maxKey = nextKey;
				maxVal = nextVal;
			} else if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
			}
		}
		return found ? OptionalDouble.of(maxKey) : OptionalDouble.empty();
	}

	public static double findMax(EnhancedDoubleIterator source, double defaultVal,
			DoubleUnaryOperator key)
	{
		boolean found = false;
		double maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (source.hasNext()) {
			double nextKey = source.nextDouble();
			double nextVal = key.applyAsDouble(nextKey);

			if (!found && !Double.isNaN(nextVal)) {
				found = true;
				maxKey = nextKey;
				maxVal = nextVal;
			} else if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
			}
		}
		return found ? maxKey : defaultVal;
	}
}
