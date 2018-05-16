/**
 *
 */
package xawd.jflow.iterators.abstractflows.minmaxconsumptiontests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalInt;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.abstractiterables.AbstractIterableInts;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 *
 */
class AbstractIntFlowMinMaxTest extends IteratorExampleProvider
{
	@Test
	void testMin()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final OptionalInt minimum = populated.iter().min();
		assertTrue(minimum.isPresent());
		assertEquals(0, minimum.getAsInt());

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertFalse(empty.iter().min().isPresent());
	}

	@Test
	void testMinWithDefaultValue()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final double minimum = populated.iter().min(-1);
		assertEquals(0, minimum);

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertEquals(-1, empty.iter().min(-1));
	}

	@ParameterizedTest
	@MethodSource("minByKeyWithoutDefaultValueTestDataProvider")
	void testMinWithKeyWithoutDefaultValue(final IntToDoubleFunction key, final Integer expectedPopulatedResult)
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final OptionalInt minimum = populated.iter().minByKey(key);
		assertTrue(minimum.isPresent());
		assertEquals(expectedPopulatedResult.intValue(), minimum.getAsInt());

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertFalse(empty.iter().minByKey(key).isPresent());
	}

	static Stream<Arguments> minByKeyWithoutDefaultValueTestDataProvider()
	{
		return Stream.of(
				Arguments.of((IntToDoubleFunction) x -> x, 0),
				Arguments.of((IntToDoubleFunction) x -> Double.POSITIVE_INFINITY, 0),
				Arguments.of((IntToDoubleFunction) x -> Double.NEGATIVE_INFINITY, 0),
				Arguments.of((IntToDoubleFunction) x -> x < 3? Double.NaN : x, 3)
				);
	}

	@ParameterizedTest
	@MethodSource("minByKeyWithDefaultValueTestDataProvider")
	void testMinWithKeyWithDefaultValue(final IntToDoubleFunction key, final Integer defaultVal, final Integer expectedPopulatedResult)
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final double minimum = populated.iter().minByKey(defaultVal, key);
		assertEquals(expectedPopulatedResult.intValue(), minimum);

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertEquals(defaultVal.intValue(), empty.iter().minByKey(defaultVal, key));
	}

	static Stream<Arguments> minByKeyWithDefaultValueTestDataProvider()
	{
		return Stream.of(
				Arguments.of((IntToDoubleFunction) x -> x, -1, 0),
				Arguments.of((IntToDoubleFunction) x -> Double.POSITIVE_INFINITY, -1, 0),
				Arguments.of((IntToDoubleFunction) x -> Double.NEGATIVE_INFINITY, -1, 0),
				Arguments.of((IntToDoubleFunction) x -> x < 3? Double.NaN : x, -1, 3)
				);
	}

	@Test
	void testMinByObjectKey()
	{
		final IntFunction<String> key = Integer::toString;

		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final OptionalInt minimum = populated.iter().minByObjectKey(key);
		assertTrue(minimum.isPresent());
		assertEquals(0, minimum.getAsInt());

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertFalse(empty.iter().minByObjectKey(key).isPresent());
	}

	@Test
	void testMax()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final OptionalInt maximum = populated.iter().max();
		assertTrue(maximum.isPresent());
		assertEquals(4, maximum.getAsInt());

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertFalse(empty.iter().max().isPresent());
	}

	@Test
	void testMaxWithDefaultValue()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final double maximum = populated.iter().max(-1);
		assertEquals(4, maximum);

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertEquals(-1, empty.iter().max(-1));
	}

	@ParameterizedTest
	@MethodSource("maxByKeyWithoutDefaultValueTestDataProvider")
	void testMaxWithKeyWithoutDefaultValue(final IntToDoubleFunction key, final Integer expectedPopulatedResult)
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final OptionalInt maximum = populated.iter().maxByKey(key);
		assertTrue(maximum.isPresent());
		assertEquals(expectedPopulatedResult.intValue(), maximum.getAsInt());

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertFalse(empty.iter().maxByKey(key).isPresent());
	}

	static Stream<Arguments> maxByKeyWithoutDefaultValueTestDataProvider()
	{
		return Stream.of(
				Arguments.of((IntToDoubleFunction) x -> x, 4),
				Arguments.of((IntToDoubleFunction) x -> Double.POSITIVE_INFINITY, 0),
				Arguments.of((IntToDoubleFunction) x -> Double.NEGATIVE_INFINITY, 0),
				Arguments.of((IntToDoubleFunction) x -> x < 3? Double.NaN : x, 4)
				);
	}

	@ParameterizedTest
	@MethodSource("maxByKeyWithDefaultValueTestDataProvider")
	void testMaxWithKeyWithDefaultValue(final IntToDoubleFunction key, final Integer defaultVal, final Integer expectedPopulatedResult)
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final double maximum = populated.iter().maxByKey(defaultVal, key);
		assertEquals(expectedPopulatedResult.intValue(), maximum);

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertEquals(defaultVal.intValue(), empty.iter().maxByKey(defaultVal, key));
	}

	static Stream<Arguments> maxByKeyWithDefaultValueTestDataProvider()
	{
		return Stream.of(
				Arguments.of((IntToDoubleFunction) x -> x, -1, 4),
				Arguments.of((IntToDoubleFunction) x -> Double.POSITIVE_INFINITY, -1, 0),
				Arguments.of((IntToDoubleFunction) x -> Double.NEGATIVE_INFINITY, -1, 0),
				Arguments.of((IntToDoubleFunction) x -> x < 3? Double.NaN : x, -1, 4)
				);
	}

	@Test
	void testMaxByObjectKey()
	{
		final IntFunction<String> key = Integer::toString;

		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final OptionalInt maximum = populated.iter().maxByObjectKey(key);
		assertTrue(maximum.isPresent());
		assertEquals(4, maximum.getAsInt());

		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		assertFalse(empty.iter().maxByObjectKey(key).isPresent());
	}
}
