/**
 *
 */
package maumay.jflow.iterators.impl.fromvalues;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.iterators.impl.SourceIterator;
import maumay.jflow.testutilities.AbstractIterableInts;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIntIteratorFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final int[] source)
	{
		assertIntIteratorAsExpected(source,
				getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(Arguments.of(new int[0]), Arguments.of(new int[] { 1, 2 }));
	}

	AbstractIterableInts getCreationFromValuesIteratorProvider(final int[] source)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return new SourceIterator.OfInt(source);
			}
		};
	}
}
