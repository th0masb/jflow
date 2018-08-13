package xawd.jflow.iterators.impl.reversefromvalues;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.impl.ReverseFlowFromValues;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractFlowReverseFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final List<String> source)
	{
		assertObjectIteratorAsExpected(reverse(source), getReverseFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(
				Arguments.of(asList()),
				Arguments.of(asList("1", "2"))
				);
	}

	<E> List<E> reverse(final List<E> toReverse)
	{
		final List<E> reversed = new ArrayList<>(toReverse.size());
		for (int i = toReverse.size() - 1; i > -1; i--) {
			reversed.add(toReverse.get(i));
		}
		return reversed;
	}

	<E> AbstractFlowIterable<E> getReverseFromValuesIteratorProvider(final List<E> source)
	{
		return new AbstractFlowIterable<E>() {
			@Override
			public AbstractFlow<E> iterator() {
				return new ReverseFlowFromValues.OfObject<>(source);
			}
		};
	}
}
