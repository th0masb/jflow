/**
 *
 */
package com.github.maumay.jflow.iterators.impl.fromvalues;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntIteratorFromValuesTest implements FiniteIteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(int[] source)
	{
		assertIntIteratorAsExpected(source,
				getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(Arguments.of(new int[0]), Arguments.of(new int[] { 1, 2 }));
	}

	AbstractIterableInts getCreationFromValuesIteratorProvider(int[] source)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return new ArraySource.OfInt(source);
			}
		};
	}
}
