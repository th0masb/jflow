/**
 *
 */
package xawd.jflow.iterators.impl.minmax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalLong;

import org.junit.jupiter.api.Test;

import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 *
 */
class AbstractLongFlowMinMaxTest extends IteratorExampleProvider
{
	@Test
	void testMin()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final OptionalLong minimum = populated.iterator().min();
		assertTrue(minimum.isPresent());
		assertEquals(0, minimum.getAsLong());

		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		assertFalse(empty.iterator().min().isPresent());
	}

	@Test
	void testMinWithDefaultValue()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final double minimum = populated.iterator().min(-1);
		assertEquals(0, minimum);

		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		assertEquals(-1, empty.iterator().min(-1));
	}

//	@ParameterizedTest
//	@MethodSource("minByKeyWithoutDefaultValueTestDataProvider")
//	void testMinWithKeyWithoutDefaultValue(final LongToDoubleFunction key, final Long expectedPopulatedResult)
//	{
//		final AbstractIterableLongs populated = getLongTestIteratorProvider();
//		final OptionalLong minimum = populated.iterator().minByKey(key);
//		assertTrue(minimum.isPresent());
//		assertEquals(expectedPopulatedResult.longValue(), minimum.getAsLong());
//
//		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
//		assertFalse(empty.iterator().minByKey(key).isPresent());
//	}
//
//	static Stream<Arguments> minByKeyWithoutDefaultValueTestDataProvider()
//	{
//		return Stream.of(
//				Arguments.of((LongToDoubleFunction) x -> x, 0L),
//				Arguments.of((LongToDoubleFunction) x -> Double.POSITIVE_INFINITY, 0L),
//				Arguments.of((LongToDoubleFunction) x -> Double.NEGATIVE_INFINITY, 0L),
//				Arguments.of((LongToDoubleFunction) x -> x < 3? Double.NaN : x, 3L)
//				);
//	}

//	@ParameterizedTest
//	@MethodSource("minByKeyWithDefaultValueTestDataProvider")
//	void testMinWithKeyWithDefaultValue(final LongToDoubleFunction key, final Long defaultVal, final Long expectedPopulatedResult)
//	{
//		final AbstractIterableLongs populated = getLongTestIteratorProvider();
//		final double minimum = populated.iterator().minByKey(defaultVal, key);
//		assertEquals(expectedPopulatedResult.longValue(), minimum);
//
//		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
//		assertEquals(defaultVal.longValue(), empty.iterator().minByKey(defaultVal, key));
//	}
//
//	static Stream<Arguments> minByKeyWithDefaultValueTestDataProvider()
//	{
//		return Stream.of(
//				Arguments.of((LongToDoubleFunction) x -> x, -1L, 0L),
//				Arguments.of((LongToDoubleFunction) x -> Double.POSITIVE_INFINITY, -1L, 0L),
//				Arguments.of((LongToDoubleFunction) x -> Double.NEGATIVE_INFINITY, -1L, 0L),
//				Arguments.of((LongToDoubleFunction) x -> x < 3? Double.NaN : x, -1L, 3L)
//				);
//	}

	@Test
	void testMax()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final OptionalLong maximum = populated.iterator().max();
		assertTrue(maximum.isPresent());
		assertEquals(4, maximum.getAsLong());

		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		assertFalse(empty.iterator().max().isPresent());
	}

	@Test
	void testMaxWithDefaultValue()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final double maximum = populated.iterator().max(-1);
		assertEquals(4, maximum);

		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		assertEquals(-1, empty.iterator().max(-1));
	}

//	@ParameterizedTest
//	@MethodSource("maxByKeyWithoutDefaultValueTestDataProvider")
//	void testMaxWithKeyWithoutDefaultValue(final LongToDoubleFunction key, final Long expectedPopulatedResult)
//	{
//		final AbstractIterableLongs populated = getLongTestIteratorProvider();
//		final OptionalLong maximum = populated.iterator().maxByKey(key);
//		assertTrue(maximum.isPresent());
//		assertEquals(expectedPopulatedResult.longValue(), maximum.getAsLong());
//
//		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
//		assertFalse(empty.iterator().maxByKey(key).isPresent());
//	}
//
//	static Stream<Arguments> maxByKeyWithoutDefaultValueTestDataProvider()
//	{
//		return Stream.of(
//				Arguments.of((LongToDoubleFunction) x -> x, 4L),
//				Arguments.of((LongToDoubleFunction) x -> Double.POSITIVE_INFINITY, 0L),
//				Arguments.of((LongToDoubleFunction) x -> Double.NEGATIVE_INFINITY, 0L),
//				Arguments.of((LongToDoubleFunction) x -> x < 3? Double.NaN : x, 4L)
//				);
//	}

//	@ParameterizedTest
//	@MethodSource("maxByKeyWithDefaultValueTestDataProvider")
//	void testMaxWithKeyWithDefaultValue(final LongToDoubleFunction key, final Long defaultVal, final Long expectedPopulatedResult)
//	{
//		final AbstractIterableLongs populated = getLongTestIteratorProvider();
//		final double maximum = populated.iterator().maxByKey(defaultVal, key);
//		assertEquals(expectedPopulatedResult.longValue(), maximum);
//
//		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
//		assertEquals(defaultVal.longValue(), empty.iterator().maxByKey(defaultVal, key));
//	}
//
//	static Stream<Arguments> maxByKeyWithDefaultValueTestDataProvider()
//	{
//		return Stream.of(
//				Arguments.of((LongToDoubleFunction) x -> x, -1L, 4L),
//				Arguments.of((LongToDoubleFunction) x -> Double.POSITIVE_INFINITY, -1L, 0L),
//				Arguments.of((LongToDoubleFunction) x -> Double.NEGATIVE_INFINITY, -1L, 0L),
//				Arguments.of((LongToDoubleFunction) x -> x < 3? Double.NaN : x, -1L, 4L)
//				);
//	}
}
