package com.github.maumay.jflow.testutilities;

import java.util.OptionalInt;

import com.github.maumay.jflow.utils.Option;

/**
 * @author ThomasB
 */
final class Utils
{
	static final int ITERATION_UPPER_BOUND = 100;

	static final String[] OBJECT_EXAMPLE_SRC = { "0", "1", "2", "3", "4" };
	static final int[] INT_EXAMPLE_SRC = { 0, 1, 2, 3, 4 };
	static final double[] DOUBLE_EXAMPLE_SRC = { 0, 1, 2, 3, 4 };
	static final long[] LONG_EXAMPLE_SRC = { 0, 1, 2, 3, 4 };

	static final String[] SHORT_OBJECT_EXAMPLE_SRC = { "10", "11" };
	static final int[] SHORT_INT_EXAMPLE_SRC = { 10, 11 };
	static final double[] SHORT_DOUBLE_EXAMPLE_SRC = { 10, 11 };
	static final long[] SHORT_LONG_EXAMPLE_SRC = { 10, 11 };

	static final String[] LARGE_OBJECT_EXAMPLE_SRC = { "10", "11", "12", "13", "14", "15" };
	static final int[] LARGE_INT_EXAMPLE_SRC = { 10, 11, 12, 13, 14, 15 };
	static final double[] LARGE_DOUBLE_EXAMPLE_SRC = { 10, 11, 12, 13, 14, 15 };
	static final long[] LARGE_LONG_EXAMPLE_SRC = { 10, 11, 12, 13, 14, 15 };

	static final String[] SINGLETON_OBJECT_EXAMPLE_SRC = { "1" };

	static OptionalInt subtractSize(OptionalInt size, int subtraction)
	{
		if (size.isPresent()) {
			return Option.of(Math.max(0, size.getAsInt() - subtraction));
		} else {
			return size;
		}
	}

	private Utils()
	{
	}
}
