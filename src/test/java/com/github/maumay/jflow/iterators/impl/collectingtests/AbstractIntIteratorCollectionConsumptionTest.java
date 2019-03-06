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

import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author ThomasB
 */
class AbstractIntIteratorCollectionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("collectToArrayTestDataProvider")
	void testCollectToArray(AbstractIntIterator iterator, int[] expectedResult)
	{
		assertArrayEquals(expectedResult, iterator.toArray());
	}

	static Stream<Arguments> collectToArrayTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iter(), new int[] { 0, 1, 2, 3, 4 }),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(), new int[0]));
	}

	@ParameterizedTest
	@MethodSource("collectToMapTestDataProvider")
	void testCollectToMap(AbstractIntIterator iterator, IntFunction<String> keyMapper,
			IntFunction<Integer> valueMapper, List<Tup<String, Integer>> expectedMapPairs)
	{
		Map<String, Integer> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Tup::_1, Tup::_2));
		assertEquals(expectedMap, iterator.toMap(keyMapper, valueMapper));
	}

	static Stream<Arguments> collectToMapTestDataProvider()
	{
		IntFunction<String> keyMapper = Integer::toString;
		IntFunction<Integer> valueMapper = Integer::valueOf;

		return Stream.of(
				Arguments.of(getSmallIntTestIteratorProvider().iter(), keyMapper, valueMapper,
						asList(Tup.of("10", 10), Tup.of("11", 11))),

				Arguments.of(getEmptyIntTestIteratorProvider().iter(), keyMapper, valueMapper,
						asList()));
	}

	@ParameterizedTest
	@MethodSource("groupByTestDataProvider")
	void testGroupBy(AbstractIntIterator iterator, IntFunction<Integer> classifier,
			List<Tup<Integer, int[]>> expectedMapPairs)
	{
		Map<Integer, int[]> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Tup::_1, Tup::_2));
		Map<Integer, int[]> actualMap = iterator.groupBy(classifier);
		assertEquals(expectedMap.keySet(), actualMap.keySet());
		expectedMap.keySet().stream().forEach(
				key -> assertArrayEquals(expectedMap.get(key), actualMap.get(key), key.toString()));
	}

	static Stream<Arguments> groupByTestDataProvider()
	{
		IntFunction<Integer> classifier = x -> (x) % 2;

		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iter(), classifier,
						asList(Tup.of(0, new int[] { 0, 2, 4 }), Tup.of(1, new int[] { 1, 3 }))),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(), classifier, asList()));
	}
}
