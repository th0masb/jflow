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
import java.util.function.IntFunction;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.gihub.maumay.jflow.iterators.misc.Pair;
import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractEnhancedIntIteratorCollectionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("collectToArrayTestDataProvider")
	void testCollectToArray(final AbstractEnhancedIntIterator iterator,
			final int[] expectedResult)
	{
		assertArrayEquals(expectedResult, iterator.toArray());
	}

	static Stream<Arguments> collectToArrayTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iter(),
						new int[] { 0, 1, 2, 3, 4 }),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(), new int[0]));
	}

	@ParameterizedTest
	@MethodSource("collectToMapTestDataProvider")
	void testCollectToMap(final AbstractEnhancedIntIterator iterator,
			final IntFunction<String> keyMapper, final IntFunction<Integer> valueMapper,
			final List<Pair<String, Integer>> expectedMapPairs)
	{
		final Map<String, Integer> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Pair::_1, Pair::_2));
		assertEquals(expectedMap, iterator.toMap(keyMapper, valueMapper));
	}

	static Stream<Arguments> collectToMapTestDataProvider()
	{
		final IntFunction<String> keyMapper = Integer::toString;
		final IntFunction<Integer> valueMapper = Integer::valueOf;

		return Stream.of(
				Arguments.of(getSmallIntTestIteratorProvider().iter(), keyMapper,
						valueMapper, asList(Pair.of("10", 10), Pair.of("11", 11))),

				Arguments.of(getEmptyIntTestIteratorProvider().iter(), keyMapper,
						valueMapper, asList()));
	}

	@ParameterizedTest
	@MethodSource("groupByTestDataProvider")
	void testGroupBy(final AbstractEnhancedIntIterator iterator,
			final IntFunction<Integer> classifier,
			final List<Pair<Integer, int[]>> expectedMapPairs)
	{
		final Map<Integer, int[]> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Pair::_1, Pair::_2));
		final Map<Integer, int[]> actualMap = iterator.groupBy(classifier);
		assertEquals(expectedMap.keySet(), actualMap.keySet());
		expectedMap.keySet().stream()
				.forEach(key -> assertArrayEquals(expectedMap.get(key),
						actualMap.get(key), key.toString()));
	}

	static Stream<Arguments> groupByTestDataProvider()
	{
		final IntFunction<Integer> classifier = x -> (x) % 2;

		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iter(), classifier,
						asList(Pair.of(0, new int[] { 0, 2, 4 }),
								Pair.of(1, new int[] { 1, 3 }))),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(), classifier,
						asList()));
	}
}
