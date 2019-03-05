/**
 *
 */
package com.github.maumay.jflow.iterators.impl.collectingtests;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.function.LongFunction;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.gihub.maumay.jflow.iterators.misc.Tup;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractLongIteratorCollectionConsumptionTest
		extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("collectToArrayTestDataProvider")
	void testCollectToArray(final AbstractLongIterator iterator,
			final long[] expectedResult)
	{
		assertArrayEquals(expectedResult, iterator.toArray());
	}

	static Stream<Arguments> collectToArrayTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iter(),
						new long[] { 0, 1, 2, 3, 4 }),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(), new long[0]));
	}

	@ParameterizedTest
	@MethodSource("collectToMapTestDataProvider")
	void testCollectToMap(final AbstractLongIterator iterator,
			final LongFunction<String> keyMapper, final LongFunction<Long> valueMapper,
			final List<Tup<String, Long>> expectedMapPairs)
	{
		final Map<String, Long> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Tup::_1, Tup::_2));
		assertEquals(expectedMap, iterator.toMap(keyMapper, valueMapper));
	}

	static Stream<Arguments> collectToMapTestDataProvider()
	{
		final LongFunction<String> keyMapper = Long::toString;
		final LongFunction<Long> valueMapper = Long::valueOf;

		return Stream.of(
				Arguments.of(getSmallLongTestIteratorProvider().iter(), keyMapper,
						valueMapper, asList(Tup.of("10", 10L), Tup.of("11", 11L))),

				Arguments.of(getEmptyLongTestIteratorProvider().iter(), keyMapper,
						valueMapper, asList()));
	}

	@ParameterizedTest
	@MethodSource("groupByTestDataProvider")
	void testGroupBy(final AbstractLongIterator iterator,
			final LongFunction<Integer> classifier,
			final List<Tup<Integer, long[]>> expectedMapPairs)
	{
		final Map<Integer, long[]> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Tup::_1, Tup::_2));
		final Map<Integer, long[]> actualMap = iterator.groupBy(classifier);
		assertEquals(expectedMap.keySet(), actualMap.keySet());
		expectedMap.keySet().stream()
				.forEach(key -> assertArrayEquals(expectedMap.get(key),
						actualMap.get(key), key.toString()));
	}

	static Stream<Arguments> groupByTestDataProvider()
	{
		final LongFunction<Integer> classifier = x -> ((int) x) % 2;

		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iter(), classifier,
						asList(Tup.of(0, new long[] { 0, 2, 4 }),
								Tup.of(1, new long[] { 1, 3 }))),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(), classifier,
						asList()));
	}
}
