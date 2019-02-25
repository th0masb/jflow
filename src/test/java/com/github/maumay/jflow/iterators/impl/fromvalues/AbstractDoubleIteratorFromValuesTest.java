/**
 *
 */
package com.github.maumay.jflow.iterators.impl.fromvalues;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl.SourceIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final double[] source)
	{
		assertDoubleIteratorAsExpected(source,
				getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(Arguments.of(new double[0]),
				Arguments.of(new double[] { 1, 2 }));
	}

	AbstractIterableDoubles getCreationFromValuesIteratorProvider(final double[] source)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return new SourceIterator.OfDouble(source);
			}
		};
	}
}
