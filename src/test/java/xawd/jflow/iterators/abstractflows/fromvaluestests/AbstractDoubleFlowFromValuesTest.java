/**
 *
 */
package xawd.jflow.iterators.abstractflows.fromvaluestests;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.abstractiterables.AbstractIterableDoubles;
import xawd.jflow.iterators.impl.FlowFromValues;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleFlowFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final double[] source)
	{
		assertDoubleIteratorAsExpected(source, getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(
				Arguments.of(new double[0]),
				Arguments.of(new double[] {1, 2})
				);
	}

	AbstractIterableDoubles getCreationFromValuesIteratorProvider(final double[] source)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iter() {
				return new FlowFromValues.OfDouble(source);
			}
		};
	}
}
