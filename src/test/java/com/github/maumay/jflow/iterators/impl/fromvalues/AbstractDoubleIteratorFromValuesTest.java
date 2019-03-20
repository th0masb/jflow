/**
 *
 */
package com.github.maumay.jflow.iterators.impl.fromvalues;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.testframework.AbstractIterableDoubles;
import com.github.maumay.jflow.testframework.FiniteIteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorFromValuesTest implements FiniteIteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(double[] source)
	{
		assertDoubleIteratorAsExpected(source,
				getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(Arguments.of(new double[0]),
				Arguments.of(new double[] { 1, 2 }));
	}

	AbstractIterableDoubles getCreationFromValuesIteratorProvider(double[] source)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return new ArraySource.OfDouble(source);
			}
		};
	}
}
