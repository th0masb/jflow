/**
 *
 */
package xawd.jflow.iterators.impl.minmax;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 *
 */
class AbstractFlowMinMaxTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("minByKeyTestDataProvider")
	void testMinByKey(final Comparator<String> key, final String expectedPopulatedResult)
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final Optional<String> result = populated.iterator().min(key);
		assertTrue(result.isPresent());
		assertEquals(expectedPopulatedResult, result.get());

		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		assertFalse(empty.iterator().min(key).isPresent());
	}

	static Stream<Arguments> minByKeyTestDataProvider()
	{
		return Stream.of(
				Arguments.of(Comparator.comparingDouble((String s) -> -parseDouble(s)) , "4"),
				Arguments.of(Comparator.comparingDouble((String s) -> Double.POSITIVE_INFINITY), "0"),
				Arguments.of(Comparator.comparingDouble((String s) -> Double.NEGATIVE_INFINITY), "0")
				);
	}

	@ParameterizedTest
	@MethodSource("maxByKeyTestDataProvider")
	void testMax(final Comparator<String> key, final String expectedPopulatedResult)
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final Optional<String> result = populated.iterator().max(key);
		assertTrue(result.isPresent());
		assertEquals(expectedPopulatedResult, result.get());

		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		assertFalse(empty.iterator().max(key).isPresent());
	}

	static Stream<Arguments> maxByKeyTestDataProvider()
	{
		return Stream.of(
				Arguments.of(Comparator.comparingDouble((String s) -> -parseDouble(s)) , "0"),
				Arguments.of(Comparator.comparingDouble((String s) -> Double.POSITIVE_INFINITY), "0"),
				Arguments.of(Comparator.comparingDouble((String s) -> Double.NEGATIVE_INFINITY), "0")
				);
	}
}
