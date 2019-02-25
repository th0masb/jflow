/**
 *
 */
package com.github.maumay.jflow.iterators.impl.reversefromvalues;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl.ReversedSourceIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorReverseFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final double[] source)
	{
		assertDoubleIteratorAsExpected(reverse(source),
				getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(Arguments.of(new double[0]),
				Arguments.of(new double[] { 1, 2 }));
	}

	double[] reverse(final double[] toReverse)
	{
		final double[] reversed = new double[toReverse.length];
		for (int i = 0; i < toReverse.length; i++) {
			reversed[i] = toReverse[toReverse.length - 1 - i];
		}
		return reversed;
	}

	AbstractIterableDoubles getCreationFromValuesIteratorProvider(final double[] source)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return new ReversedSourceIterator.OfDouble(source);
			}
		};
	}
}
