/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.OptionalInt;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;

import xawd.jflow.iterators.IntFlow;

/**
 * @author ThomasB
 */
public final class IntMinMaxConsumption
{
	private IntMinMaxConsumption() {}

	public static OptionalInt findMin(final IntFlow source)
	{
		boolean found = false;
		int min = Integer.MAX_VALUE;
		while (source.hasNext())
		{
			final int next = source.nextInt();
			if (!found) {
				found = true;
				min = next;
			}
			else if (next < min) {
				min = next;
			}
		}
		return found? OptionalInt.of(min) : OptionalInt.empty();
	}

	public static int findMin(final IntFlow source, final int defaultVal)
	{
		boolean found = false;
		int min = Integer.MAX_VALUE;
		while (source.hasNext())
		{
			final int next = source.nextInt();
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

	public static OptionalInt findMin(final IntFlow source, final IntToDoubleFunction key)
	{
		boolean found = false;
		int minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (source.hasNext())
		{
			final int nextKey = source.nextInt();
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
		return found? OptionalInt.of(minKey) : OptionalInt.empty();
	}

	public static int findMin(final IntFlow source, final int defaultVal, final IntToDoubleFunction key)
	{
		boolean found = false;
		int minKey = -1;
		double minVal = Double.POSITIVE_INFINITY;
		while (source.hasNext())
		{
			final int nextKey = source.nextInt();
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

	public static <C extends Comparable<C>> OptionalInt findMin(final IntFlow source, final IntFunction<C> key)
	{
		int minKey = -1;
		C minVal = null;
		while (source.hasNext())
		{
			final int nextKey = source.nextInt();
			final C nextVal = key.apply(nextKey);

			if (minVal == null) {
				minKey = nextKey;
				minVal = nextVal;
			}
			else if (nextVal.compareTo(minVal) < 0) {
				minVal = nextVal;
				minKey = nextKey;
			}
		}
		return minVal == null? OptionalInt.empty() : OptionalInt.of(minKey);
	}

	public static OptionalInt findMax(final IntFlow source)
	{
		boolean found = false;
		int max = Integer.MIN_VALUE;
		while (source.hasNext())
		{
			final int next = source.nextInt();
			if (!found) {
				found = true;
				max = next;
			}
			else if (next > max) {
				max = next;
			}
		}
		return found? OptionalInt.of(max) : OptionalInt.empty();
	}

	public static int findMax(final IntFlow source, final int defaultVal)
	{
		boolean found = false;
		int max = Integer.MIN_VALUE;
		while (source.hasNext())
		{
			final int next = source.nextInt();
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

	public static OptionalInt findMax(final IntFlow source, final IntToDoubleFunction key)
	{
		boolean found = false;
		int maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (source.hasNext())
		{
			final int nextKey = source.nextInt();
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
		return found? OptionalInt.of(maxKey) : OptionalInt.empty();
	}

	public static int findMax(final IntFlow source, final int defaultVal, final IntToDoubleFunction key)
	{
		boolean found = false;
		int maxKey = -1;
		double maxVal = Double.NEGATIVE_INFINITY;
		while (source.hasNext())
		{
			final int nextKey = source.nextInt();
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

	public static <C extends Comparable<C>> OptionalInt findMax(final IntFlow source, final IntFunction<C> key)
	{
		int maxKey = -1;
		C maxVal = null;
		while (source.hasNext())
		{
			final int nextKey = source.nextInt();
			final C nextVal = key.apply(nextKey);

			if (maxVal == null) {
				maxKey = nextKey;
				maxVal = nextVal;
			}
			else if (nextVal.compareTo(maxVal) > 0) {
				maxVal = nextVal;
				maxKey = nextKey;
			}
		}
		return maxVal == null? OptionalInt.empty() : OptionalInt.of(maxKey);
	}
}
