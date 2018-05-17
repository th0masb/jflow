package xawd.jflow.iterators.abstractflows.fromvaluestests;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.abstractiterables.AbstractFlowIterable;
import xawd.jflow.iterators.impl.FlowFromValues;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractFlowFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final List<String> source)
	{
		assertObjectIteratorAsExpected(source, getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(
				Arguments.of(asList()),
				Arguments.of(asList("1", "1"))
				);
	}

	<E> AbstractFlowIterable<E> getCreationFromValuesIteratorProvider(final List<E> source)
	{
		return new AbstractFlowIterable<E>() {
			@Override
			public AbstractFlow<E> iter() {
				return new FlowFromValues.OfObject<>(source);
			}
		};
	}
}
