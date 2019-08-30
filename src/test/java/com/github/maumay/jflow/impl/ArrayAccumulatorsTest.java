/**
 * 
 */
package com.github.maumay.jflow.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterator.Iter;
import com.github.maumay.jflow.iterator.RichIterator;

/**
 * @author ThomasB
 */
class ArrayAccumulatorsTest
{
	@ParameterizedTest
	@MethodSource
	void testIntAccumulation(Integer upperBound)
	{
		ArrayAccumulators.OfInt acc = ArrayAccumulators.createIntAccumulator();
		int[] expected = new int[upperBound];

		for (int i = 0; i < upperBound; i++) {
			acc.add(i);
			expected[i] = i;
		}

		assertArrayEquals(expected, acc.compress());
	}

	static RichIterator<Arguments> testIntAccumulation()
	{
		return Iter.args(Arguments.of(10), Arguments.of(20), Arguments.of(21), Arguments.of(39),
				Arguments.of(12009));
	}

	@ParameterizedTest
	@MethodSource
	void testDoubleAccumulation(Integer upperBound)
	{
		ArrayAccumulators.OfDouble acc = ArrayAccumulators.createDoubleAccumulator();
		double[] expected = new double[upperBound];

		for (int i = 0; i < upperBound; i++) {
			acc.add(i);
			expected[i] = i;
		}

		assertArrayEquals(expected, acc.compress());
	}

	static RichIterator<Arguments> testDoubleAccumulation()
	{
		return Iter.args(Arguments.of(10), Arguments.of(20), Arguments.of(21), Arguments.of(39),
				Arguments.of(10239));
	}

	@ParameterizedTest
	@MethodSource
	void testLongAccumulation(Integer upperBound)
	{
		ArrayAccumulators.OfLong acc = ArrayAccumulators.createLongAccumulator();
		long[] expected = new long[upperBound];

		for (int i = 0; i < upperBound; i++) {
			acc.add(i);
			expected[i] = i;
		}

		assertArrayEquals(expected, acc.compress());
	}

	static RichIterator<Arguments> testLongAccumulation()
	{
		return Iter.args(Arguments.of(10), Arguments.of(20), Arguments.of(21), Arguments.of(284),
				Arguments.of(9375));
	}

	@ParameterizedTest
	@MethodSource
	void testObjectAccumulation(Integer upperBound)
	{
		ArrayAccumulators.OfObject<Integer> acc = ArrayAccumulators.createObjectAccumulator();
		Integer[] expected = new Integer[upperBound];

		for (int i = 0; i < upperBound; i++) {
			acc.add(i);
			expected[i] = i;
		}

		assertArrayEquals(expected, acc.compress());
	}

	static RichIterator<Arguments> testObjectAccumulation()
	{
		return Iter.args(Arguments.of(10), Arguments.of(20), Arguments.of(21), Arguments.of(284),
				Arguments.of(9375));
	}

	@ParameterizedTest
	@MethodSource
	void testObjectCombining(Integer upperBound, Double split)
	{
		assertTrue(0.0 <= split && split <= 1.0);
		ArrayAccumulators.OfObject<Integer> a = ArrayAccumulators.createObjectAccumulator();
		ArrayAccumulators.OfObject<Integer> b = ArrayAccumulators.createObjectAccumulator();
		Integer[] expected = new Integer[upperBound];
		for (int i = 0; i < upperBound; i++) {
			expected[i] = i;
			if (i < upperBound * split) {
				a.add(i);
			} else {
				b.add(i);
			}
		}
		a.add(b);
		assertArrayEquals(expected, a.compress());
	}

	static RichIterator<Arguments> testObjectCombining()
	{
		return Iter.args(Arguments.of(36, 0.5), Arguments.of(241, 0.3), Arguments.of(241, 0.9));
	}
}
