/**
 * 
 */
package xawd.jflow.iterators.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.factories.Iterate;
import xawd.jflow.iterators.impl.ArrayAccumulators;

/**
 * @author ThomasB
 */
class ArrayAccumulatorsTest
{
	@ParameterizedTest
	@MethodSource
	void testIntAccumulation(Integer upperBound)
	{
		ArrayAccumulators.OfInt acc = ArrayAccumulators.intAccumulator();
		int[] expected = new int[upperBound];
		
		for (int i = 0; i < upperBound; i++) {
			acc.add(i);
			expected[i] = i;
		}
		
		assertArrayEquals(expected, acc.compress());
	}
	
	static Flow<Arguments> testIntAccumulation()
	{
		return Iterate.over(
				Arguments.of(10),
				Arguments.of(20),
				Arguments.of(21),
				Arguments.of(39),
				Arguments.of(12009)
				);
	}

	@ParameterizedTest
	@MethodSource
	void testDoubleAccumulation(Integer upperBound)
	{
		ArrayAccumulators.OfDouble acc = ArrayAccumulators.doubleAccumulator();
		double[] expected = new double[upperBound];
		
		for (int i = 0; i < upperBound; i++) {
			acc.add(i);
			expected[i] = i;
		}
		
		assertArrayEquals(expected, acc.compress());
	}
	
	static Flow<Arguments> testDoubleAccumulation()
	{
		return Iterate.over(
				Arguments.of(10),
				Arguments.of(20),
				Arguments.of(21),
				Arguments.of(39),
				Arguments.of(10239)
				);
	}
	
	@ParameterizedTest
	@MethodSource
	void testLongAccumulation(Integer upperBound)
	{
		ArrayAccumulators.OfLong acc = ArrayAccumulators.longAccumulator();
		long[] expected = new long[upperBound];
		
		for (int i = 0; i < upperBound; i++) {
			acc.add(i);
			expected[i] = i;
		}
		
		assertArrayEquals(expected, acc.compress());
	}
	
	static Flow<Arguments> testLongAccumulation()
	{
		return Iterate.over(
				Arguments.of(10),
				Arguments.of(20),
				Arguments.of(21),
				Arguments.of(284),
				Arguments.of(9375)
				);
	}
}
