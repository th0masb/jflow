/**
 *
 */
package xawd.jflow.iterators.minmaxconsumptiontests;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.ToDoubleFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.abstractiterables.AbstractFlowIterable;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 *
 */
class AbstractFlowMinMaxTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("minByKeyTestDataProvider")
	void testMinByKey(final ToDoubleFunction<String> key, final String expectedPopulatedResult)
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final Optional<String> result = populated.iterator().minByKey(key);
		assertTrue(result.isPresent());
		assertEquals(expectedPopulatedResult, result.get());

		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		assertFalse(empty.iterator().minByKey(key).isPresent());
	}

	static Stream<Arguments> minByKeyTestDataProvider()
	{
		return Stream.of(
				Arguments.of((ToDoubleFunction<String>) s -> -parseDouble(s), "4"),
				Arguments.of((ToDoubleFunction<String>) s -> Double.POSITIVE_INFINITY, "0"),
				Arguments.of((ToDoubleFunction<String>) s -> Double.NEGATIVE_INFINITY, "0"),
				Arguments.of((ToDoubleFunction<String>) s -> parseDouble(s) < 3? Double.NaN : parseDouble(s), "3")
				);
	}

	@Test
	void testMinByObjectKey()
	{
		final UnaryOperator<String> key = x -> x;

		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final Optional<String> result = populated.iterator().minByObjectKey(key);
		assertTrue(result.isPresent());
		assertEquals("0", result.get());

		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		assertFalse(empty.iterator().minByObjectKey(key).isPresent());
	}

	@ParameterizedTest
	@MethodSource("maxByKeyTestDataProvider")
	void testMaxByKey(final ToDoubleFunction<String> key, final String expectedPopulatedResult)
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final Optional<String> result = populated.iterator().maxByKey(key);
		assertTrue(result.isPresent());
		assertEquals(expectedPopulatedResult, result.get());

		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		assertFalse(empty.iterator().maxByKey(key).isPresent());
	}

	static Stream<Arguments> maxByKeyTestDataProvider()
	{
		return Stream.of(
				Arguments.of((ToDoubleFunction<String>) s -> -parseDouble(s), "0"),
				Arguments.of((ToDoubleFunction<String>) s -> Double.POSITIVE_INFINITY, "0"),
				Arguments.of((ToDoubleFunction<String>) s -> Double.NEGATIVE_INFINITY, "0"),
				Arguments.of((ToDoubleFunction<String>) s -> parseDouble(s) < 3? Double.NaN : parseDouble(s), "4")
				);
	}

	@Test
	void testMaxByObjectKey()
	{
		final UnaryOperator<String> key = x -> x;

		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final Optional<String> result = populated.iterator().maxByObjectKey(key);
		assertTrue(result.isPresent());
		assertEquals("4", result.get());

		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		assertFalse(empty.iterator().maxByObjectKey(key).isPresent());
	}
}
