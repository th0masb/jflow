/**
 *
 */
package xawd.jflow.iterators.impl.fromvalues;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.impl.FlowFromValues;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntFlowFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final int[] source)
	{
		assertIntIteratorAsExpected(source, getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(
				Arguments.of(new int[0]),
				Arguments.of(new int[] {1, 2})
				);
	}

	AbstractIterableInts getCreationFromValuesIteratorProvider(final int[] source)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return new FlowFromValues.OfInt(source);
			}
		};
	}
}
