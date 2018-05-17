/**
 *
 */
package xawd.jflow.iterators.abstractflows.reversefromvaluestests;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.abstractiterables.AbstractIterableLongs;
import xawd.jflow.iterators.impl.ReverseFlowFromValues;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongFlowReverseFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final long[] source)
	{
		assertLongIteratorAsExpected(reverse(source), getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(
				Arguments.of(new long[0]),
				Arguments.of(new long[] {1, 2})
				);
	}

	long[] reverse(final long[] toReverse)
	{
		final long[] reversed = new long[toReverse.length];
		for (int i = 0; i < toReverse.length; i++) {
			reversed[i] = toReverse[toReverse.length - 1 - i];
		}
		return reversed;
	}

	AbstractIterableLongs getCreationFromValuesIteratorProvider(final long[] source)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iter() {
				return new ReverseFlowFromValues.OfLong(source);
			}
		};
	}
}
