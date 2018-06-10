/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.OptionalLong;
import java.util.function.LongToDoubleFunction;

import xawd.jflow.iterators.LongFlow;

/**
 * @author ThomasB
 */
public final class LongMinMaxConsumption
{
	private LongMinMaxConsumption() {}

	public static OptionalLong findMin(final LongFlow source)
	{
		boolean found = false;
		long min = Long.MAX_VALUE;
		while (source.hasNext())
		{
			final long next = source.nextLong();
			if (!found) {
				found = true;
				min = next;
			}
			else if (next < min) {
				min = next;
			}
		}
		return found? OptionalLong.of(min) : OptionalLong.empty();
	}

	public static long findMin(final LongFlow source, final long defaultVal)
	{
		boolean found = false;
		long min = Long.MAX_VALUE;
		while (source.hasNext())
		{
			final long next = source.nextLong();
			if (!found) {
				found = true;
				min = next;
			}
			else if (next < min) {
				min = next;
			}
		}
		return found? min : defaultVal;
	}

	public static OptionalLong findMin(final LongFlow source, final LongToDoubleFunction key)
	{
		boolean found = false;
		long minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (source.hasNext())
		{
			final long nextKey = source.nextLong();
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
		return found? OptionalLong.of(minKey) : OptionalLong.empty();
	}

	public static long findMin(final LongFlow source, final long defaultVal, final LongToDoubleFunction key)
	{
		boolean found = false;
		long minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (source.hasNext())
		{
			final long nextKey = source.nextLong();
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

	public static OptionalLong findMax(final LongFlow source)
	{
		boolean found = false;
		long max = Long.MIN_VALUE;
		while (source.hasNext())
		{
			final long next = source.nextLong();
			if (!found) {
				found = true;
				max = next;
			}
			else if (next > max) {
				max = next;
			}
		}
		return found? OptionalLong.of(max) : OptionalLong.empty();
	}

	public static long findMax(final LongFlow source, final long defaultVal)
	{
		boolean found = false;
		long max = Long.MIN_VALUE;
		while (source.hasNext())
		{
			final long next = source.nextLong();
			if (!found) {
				found = true;
				max = next;
			}
			else if (next > max) {
				max = next;
			}
		}
		return found? max : defaultVal;
	}

	public static OptionalLong findMax(final LongFlow source, final LongToDoubleFunction key)
	{
		boolean found = false;
		long maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (source.hasNext())
		{
			final long nextKey = source.nextLong();
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
		return found? OptionalLong.of(maxKey) : OptionalLong.empty();
	}

	public static long findMax(final LongFlow source, final long defaultVal, final LongToDoubleFunction key)
	{
		boolean found = false;
		long maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (source.hasNext())
		{
			final long nextKey = source.nextLong();
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
