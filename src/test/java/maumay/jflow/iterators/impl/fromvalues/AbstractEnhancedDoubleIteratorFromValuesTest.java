/**
 *
 */
package maumay.jflow.iterators.impl.fromvalues;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import maumay.jflow.iterators.impl.SourceIterator;
import maumay.jflow.testutilities.AbstractIterableDoubles;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedDoubleIteratorFromValuesTest implements IteratorTest
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
			public AbstractEnhancedDoubleIterator iter()
			{
				return new SourceIterator.OfDouble(source);
			}
		};
	}
}
