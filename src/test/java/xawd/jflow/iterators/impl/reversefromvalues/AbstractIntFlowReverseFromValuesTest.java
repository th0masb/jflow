/**
 *
 */
package xawd.jflow.iterators.impl.reversefromvalues;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.impl.ReverseFlowFromValues;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntFlowReverseFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final int[] source)
	{
		assertIntIteratorAsExpected(reverse(source), getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(
				Arguments.of(new int[0]),
				Arguments.of(new int[] {1, 2})
				);
	}

	int[] reverse(final int[] toReverse)
	{
		final int[] reversed = new int[toReverse.length];
		for (int i = 0; i < toReverse.length; i++) {
			reversed[i] = toReverse[toReverse.length - 1 - i];
		}
		return reversed;
	}

	AbstractIterableInts getCreationFromValuesIteratorProvider(final int[] source)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return new ReverseFlowFromValues.OfInt(source);
			}
		};
	}
}
