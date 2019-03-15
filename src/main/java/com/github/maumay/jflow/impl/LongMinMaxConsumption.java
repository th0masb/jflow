/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.OptionalLong;
import java.util.function.LongUnaryOperator;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author ThomasB
 */
public class LongMinMaxConsumption
{
	private LongMinMaxConsumption()
	{
	}

	public static OptionalLong findMinOption(AbstractLongIterator source)
	{
		Exceptions.require(source.hasOwnership());
		boolean found = false;
		long min = Long.MAX_VALUE;
		while (source.hasNext()) {
			long next = source.nextLongImpl();
			if (!found) {
				found = true;
				min = next;
			} else if (next < min) {
				min = next;
			}
		}
		return found ? OptionalLong.of(min) : OptionalLong.empty();
	}

	public static long findMin(AbstractLongIterator source)
	{
		Exceptions.require(source.hasOwnership());
		boolean found = false;
		long min = Long.MAX_VALUE;
		while (source.hasNext()) {
			long next = source.nextLongImpl();
			if (!found) {
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

	public static OptionalLong findMin(AbstractLongIterator source, LongUnaryOperator key)
	{
		Exceptions.require(source.hasOwnership());
		boolean found = false;
		long minKey = -1;
		long minVal = Long.MAX_VALUE;
		while (source.hasNext()) {
			long nextKey = source.nextLongImpl();
			long nextVal = key.applyAsLong(nextKey);

			if (!found) {
				found = true;
				minKey = nextKey;
				minVal = nextVal;
			} else if (nextVal < minVal) {
				minVal = nextVal;
				minKey = nextKey;
			}
		}
		return found ? OptionalLong.of(minKey) : OptionalLong.empty();
	}

	public static long findMin(AbstractLongIterator source, long defaultVal, LongUnaryOperator key)
	{
		Exceptions.require(source.hasOwnership());
		boolean found = false;
		long minKey = -1;
		long minVal = Long.MAX_VALUE;
		while (source.hasNext()) {
			long nextKey = source.nextLongImpl();
			long nextVal = key.applyAsLong(nextKey);

			if (!found) {
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

	public static OptionalLong findMaxOption(AbstractLongIterator source)
	{
		Exceptions.require(source.hasOwnership());
		boolean found = false;
		long max = Long.MIN_VALUE;
		while (source.hasNext()) {
			long next = source.nextLongImpl();
			if (!found) {
				found = true;
				max = next;
			} else if (next > max) {
				max = next;
			}
		}
		return found ? OptionalLong.of(max) : OptionalLong.empty();
	}

	public static long findMax(AbstractLongIterator source)
	{
		Exceptions.require(source.hasOwnership());
		boolean found = false;
		long max = Long.MIN_VALUE;
		while (source.hasNext()) {
			long next = source.nextLongImpl();
			if (!found) {
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

	public static OptionalLong findMaxOption(AbstractLongIterator source, LongUnaryOperator key)
	{
		Exceptions.require(source.hasOwnership());
		boolean found = false;
		long maxKey = -1;
		long maxVal = Long.MIN_VALUE;
		while (source.hasNext()) {
			long nextKey = source.nextLongImpl();
			long nextVal = key.applyAsLong(nextKey);

			if (!found) {
				found = true;
				maxKey = nextKey;
				maxVal = nextVal;
			} else if (nextVal > maxVal) {
				maxVal = nextVal;
				maxKey = nextKey;
			}
		}
		return found ? OptionalLong.of(maxKey) : OptionalLong.empty();
	}

	public static long findMax(AbstractLongIterator source, long defaultVal, LongUnaryOperator key)
	{
		Exceptions.require(source.hasOwnership());
		boolean found = false;
		long maxKey = -1;
		long maxVal = Long.MIN_VALUE;
		while (source.hasNext()) {
			long nextKey = source.nextLongImpl();
			long nextVal = key.applyAsLong(nextKey);

			if (!found) {
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
