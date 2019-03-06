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
import java.util.function.DoubleFunction;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorCollectionConsumptionTest
		extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("collectToArrayTestDataProvider")
	void testCollectToArray(final AbstractDoubleIterator iterator,
			final double[] expectedResult)
	{
		assertArrayEquals(expectedResult, iterator.toArray());
	}

	static Stream<Arguments> collectToArrayTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iter(),
						new double[] { 0, 1, 2, 3, 4 }),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(),
						new double[0]));
	}

	@ParameterizedTest
	@MethodSource("collectToMapTestDataProvider")
	void testCollectToMap(final AbstractDoubleIterator iterator,
			final DoubleFunction<String> keyMapper,
			final DoubleFunction<Double> valueMapper,
			final List<Tup<String, Double>> expectedMapPairs)
	{
		final Map<String, Double> expectedMap = expectedMapPairs.stream()
				.collect(toMap(x -> x._1, x -> x._2));
		assertEquals(expectedMap, iterator.toMap(keyMapper, valueMapper));
	}

	static Stream<Arguments> collectToMapTestDataProvider()
	{
		final DoubleFunction<String> keyMapper = Double::toString;
		final DoubleFunction<Double> valueMapper = Double::valueOf;

		return Stream.of(
				Arguments.of(getSmallDoubleTestIteratorProvider().iter(), keyMapper,
						valueMapper,
						asList(Tup.of("10.0", 10.0), Tup.of("11.0", 11.0))),

				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(), keyMapper,
						valueMapper, asList()));
	}

	@ParameterizedTest
	@MethodSource("groupByTestDataProvider")
	void testGroupBy(final AbstractDoubleIterator iterator,
			final DoubleFunction<Integer> classifier,
			final List<Tup<Integer, double[]>> expectedMapPairs)
	{
		final Map<Integer, double[]> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Tup::_1, Tup::_2));
		final Map<Integer, double[]> actualMap = iterator.groupBy(classifier);
		assertEquals(expectedMap.keySet(), actualMap.keySet());
		expectedMap.keySet().stream()
				.forEach(key -> assertArrayEquals(expectedMap.get(key),
						actualMap.get(key), key.toString()));
	}

	static Stream<Arguments> groupByTestDataProvider()
	{
		final DoubleFunction<Integer> classifier = x -> ((int) x) % 2;

		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iter(), classifier,
						asList(Tup.of(0, new double[] { 0, 2, 4 }),
								Tup.of(1, new double[] { 1, 3 }))),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(), classifier,
						asList()));
	}
}
