/**
 *
 */
package xawd.jflow.iterators.abstractflows.minmaxconsumptiontests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalDouble;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.abstractiterables.AbstractIterableDoubles;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 *
 */
class AbstractDoubleFlowMinMaxTest extends IteratorExampleProvider
{
	@Test
	void testMin()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final OptionalDouble minimum = populated.iter().min();
		assertTrue(minimum.isPresent());
		assertEquals(0, minimum.getAsDouble());

		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertFalse(empty.iter().min().isPresent());
	}

	@Test
	void testMinWithDefaultValue()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final double minimum = populated.iter().min(-1);
		assertEquals(0, minimum);

		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertEquals(-1, empty.iter().min(-1));
	}

	@ParameterizedTest
	@MethodSource("minByKeyWithoutDefaultValueTestDataProvider")
	void testMinWithKeyWithoutDefaultValue(final DoubleUnaryOperator key, final Double expectedPopulatedResult)
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final OptionalDouble minimum = populated.iter().minByKey(key);
		assertTrue(minimum.isPresent());
		assertEquals(expectedPopulatedResult.doubleValue(), minimum.getAsDouble());

		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertFalse(empty.iter().minByKey(key).isPresent());
	}

	static Stream<Arguments> minByKeyWithoutDefaultValueTestDataProvider()
	{
		return Stream.of(
				Arguments.of((DoubleUnaryOperator) x -> x, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> Double.POSITIVE_INFINITY, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> Double.NEGATIVE_INFINITY, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> x < 3? Double.NaN : x, 3.0)
				);
	}

	@ParameterizedTest
	@MethodSource("minByKeyWithDefaultValueTestDataProvider")
	void testMinWithKeyWithDefaultValue(final DoubleUnaryOperator key, final Double defaultVal, final Double expectedPopulatedResult)
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final double minimum = populated.iter().minByKey(defaultVal, key);
		assertEquals(expectedPopulatedResult.doubleValue(), minimum);

		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertEquals(defaultVal.doubleValue(), empty.iter().minByKey(defaultVal, key));
	}

	static Stream<Arguments> minByKeyWithDefaultValueTestDataProvider()
	{
		return Stream.of(
				Arguments.of((DoubleUnaryOperator) x -> x, -1.0, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> Double.POSITIVE_INFINITY, -1.0, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> Double.NEGATIVE_INFINITY, -1.0, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> x < 3? Double.NaN : x, -1.0, 3.0)
				);
	}

	@Test
	void testMax()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final OptionalDouble maximum = populated.iter().max();
		assertTrue(maximum.isPresent());
		assertEquals(4, maximum.getAsDouble());

		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertFalse(empty.iter().max().isPresent());
	}

	@Test
	void testMaxWithDefaultValue()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final double maximum = populated.iter().max(-1);
		assertEquals(4, maximum);

		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertEquals(-1, empty.iter().max(-1));
	}

	@ParameterizedTest
	@MethodSource("maxByKeyWithoutDefaultValueTestDataProvider")
	void testMaxWithKeyWithoutDefaultValue(final DoubleUnaryOperator key, final Double expectedPopulatedResult)
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final OptionalDouble maximum = populated.iter().maxByKey(key);
		assertTrue(maximum.isPresent());
		assertEquals(expectedPopulatedResult.doubleValue(), maximum.getAsDouble());

		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertFalse(empty.iter().maxByKey(key).isPresent());
	}

	static Stream<Arguments> maxByKeyWithoutDefaultValueTestDataProvider()
	{
		return Stream.of(
				Arguments.of((DoubleUnaryOperator) x -> x, 4.0),
				Arguments.of((DoubleUnaryOperator) x -> Double.POSITIVE_INFINITY, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> Double.NEGATIVE_INFINITY, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> x < 3? Double.NaN : x, 4.0)
				);
	}

	@ParameterizedTest
	@MethodSource("maxByKeyWithDefaultValueTestDataProvider")
	void testMaxWithKeyWithDefaultValue(final DoubleUnaryOperator key, final Double defaultVal, final Double expectedPopulatedResult)
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final double maximum = populated.iter().maxByKey(defaultVal, key);
		assertEquals(expectedPopulatedResult.doubleValue(), maximum);

		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertEquals(defaultVal.doubleValue(), empty.iter().maxByKey(defaultVal, key));
	}

	static Stream<Arguments> maxByKeyWithDefaultValueTestDataProvider()
	{
		return Stream.of(
				Arguments.of((DoubleUnaryOperator) x -> x, -1.0, 4.0),
				Arguments.of((DoubleUnaryOperator) x -> Double.POSITIVE_INFINITY, -1.0, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> Double.NEGATIVE_INFINITY, -1.0, 0.0),
				Arguments.of((DoubleUnaryOperator) x -> x < 3? Double.NaN : x, -1.0, 4.0)
				);
	}
}
