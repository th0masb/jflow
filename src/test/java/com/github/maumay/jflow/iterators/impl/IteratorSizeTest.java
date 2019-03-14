/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author thomasb
 *
 */
class IteratorSizeTest
{

	@ParameterizedTest
	@MethodSource
	void testSum(AbstractIteratorSize expected, List<AbstractIteratorSize> sizes)
	{
		assertEquals(expected, IteratorSizeUtils.sum((AbstractIteratorSize[]) sizes.toArray()));
	}

	static Stream<Arguments> testSum()
	{
		// Case 1
		List<AbstractIteratorSize> input1 = Arrays.asList(new KnownSize(5), new UpperBound(10));
		AbstractIteratorSize expected1 = new BoundedSize(5, 15);

		// Case 2
		List<AbstractIteratorSize> input2 = Arrays.asList(new LowerBound(6), new UpperBound(5));
		AbstractIteratorSize expected2 = new LowerBound(6);

		// Case 3
		List<AbstractIteratorSize> input3 = Arrays.asList(UnknownSize.instance(), new KnownSize(5));
		AbstractIteratorSize expected3 = new LowerBound(5);

		return Stream.of(Arguments.of(expected1, input1), Arguments.of(expected2, input2),
				Arguments.of(expected3, input3));
	}

	@ParameterizedTest
	@MethodSource
	void testMin(AbstractIteratorSize expected, List<AbstractIteratorSize> sizes)
	{
		assertEquals(expected, IteratorSizeUtils.min((AbstractIteratorSize[]) sizes.toArray()));
	}

	static Stream<Arguments> testMin()
	{
		// Case 1
		List<AbstractIteratorSize> input1 = Arrays.asList(new KnownSize(5), new UpperBound(10));
		AbstractIteratorSize expected1 = new UpperBound(5);

		// Case 2
		List<AbstractIteratorSize> input2 = Arrays.asList(new LowerBound(6), new UpperBound(5));
		AbstractIteratorSize expected2 = new UpperBound(5);

		// Case 3
		List<AbstractIteratorSize> input3 = Arrays.asList(UnknownSize.instance(), new KnownSize(5));
		AbstractIteratorSize expected3 = new UpperBound(5);

		// Case 4
		List<AbstractIteratorSize> input4 = Arrays.asList(new BoundedSize(3, 6), new KnownSize(5));
		AbstractIteratorSize expected4 = new BoundedSize(3, 5);

		return Stream.of(Arguments.of(expected1, input1), Arguments.of(expected2, input2),
				Arguments.of(expected3, input3), Arguments.of(expected4, input4));
	}
}
