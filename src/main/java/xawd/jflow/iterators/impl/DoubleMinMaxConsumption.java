/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.OptionalDouble;
import java.util.function.DoubleUnaryOperator;

import xawd.jflow.iterators.DoubleFlow;

/**
 * @author ThomasB
 */
public final class DoubleMinMaxConsumption
{
	private DoubleMinMaxConsumption() {}

	public static OptionalDouble findMin(final DoubleFlow source)
	{
		boolean found = false;
		double min = Double.POSITIVE_INFINITY;
		while (source.hasNext())
		{
			final double next = source.nextDouble();
			if (!found && !Double.isNaN(next)) {
				found = true;
				min = next;
			}
			else if (next < min) {
				min = next;
			}
		}
		return found? OptionalDouble.of(min) : OptionalDouble.empty();
	}

	public static double findMin(final DoubleFlow source, final double defaultVal)
	{
		boolean found = false;
		double min = Double.POSITIVE_INFINITY;
		while (source.hasNext())
		{
			final double next = source.nextDouble();
			if (!found && !Double.isNaN(next)) {
				found = true;
				min = next;
			}
			else if (next < min) {
				min = next;
			}
		}
		return found? min : defaultVal;
	}

	public static OptionalDouble findMin(final DoubleFlow source, final DoubleUnaryOperator key)
	{
		boolean found = false;
		double minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (source.hasNext())
		{
			final double nextKey = source.nextDouble();
			final double nextVal = key.applyAsDouble(nextKey);

			if (!found && !Double.isNaN(nextVal)) {
				found = true;
				minKey = nextKey;
				minVal = nextVal;
			}
			else if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
			}
		}
		return found? OptionalDouble.of(minKey) : OptionalDouble.empty();
	}

	public static double findMin(final DoubleFlow source, final double defaultVal, final DoubleUnaryOperator key)
	{
		boolean found = false;
		double minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (source.hasNext())
		{
			final double nextKey = source.nextDouble();
			final double nextVal = key.applyAsDouble(nextKey);

			if (!found && !Double.isNaN(nextVal)) {
				found = true;
				minKey = nextKey;
				minVal = nextVal;
			}
			else if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
			}
		}
		return found? minKey : defaultVal;
	}

	public static OptionalDouble findMax(final DoubleFlow source)
	{
		boolean found = false;
		double max = Double.NEGATIVE_INFINITY;
		while (source.hasNext())
		{
			final double next = source.nextDouble();
			if (!found && !Double.isNaN(next)) {
				found = true;
				max = next;
			}
			else if (next > max) {
				max = next;
			}
		}
		return found? OptionalDouble.of(max) : OptionalDouble.empty();
	}

	public static double findMax(final DoubleFlow source, final double defaultVal)
	{
		boolean found = false;
		double max = Double.NEGATIVE_INFINITY;
		while (source.hasNext())
		{
			final double next = source.nextDouble();
			if (!found && !Double.isNaN(next)) {
				found = true;
				max = next;
			}
			else if (next > max) {
				max = next;
			}
		}
		return found? max : defaultVal;
	}

	public static OptionalDouble findMax(final DoubleFlow source, final DoubleUnaryOperator key)
	{
		boolean found = false;
		double maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (source.hasNext())
		{
			final double nextKey = source.nextDouble();
			final double nextVal = key.applyAsDouble(nextKey);

			if (!found && !Double.isNaN(nextVal)) {
				found = true;
				maxKey = nextKey;
				maxVal = nextVal;
			}
			else if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
			}
		}
		return found? OptionalDouble.of(maxKey) : OptionalDouble.empty();
	}

	public static double findMax(final DoubleFlow source, final double defaultVal, final DoubleUnaryOperator key)
	{
		boolean found = false;
		double maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (source.hasNext())
		{
			final double nextKey = source.nextDouble();
			final double nextVal = key.applyAsDouble(nextKey);

			if (!found && !Double.isNaN(nextVal)) {
				found = true;
				maxKey = nextKey;
				maxVal = nextVal;
			}
			else if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
			}
		}
		return found? maxKey : defaultVal;
	}
}
