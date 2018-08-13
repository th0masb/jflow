/**
 *
 */
package xawd.jflow.iterators.impl.collectingtests;

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

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.misc.Pair;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractLongFlowCollectionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("collectToArrayTestDataProvider")
	void testCollectToArray(final AbstractLongFlow iterator, final long[] expectedResult)
	{
		assertArrayEquals(expectedResult, iterator.toArray());
	}

	static Stream<Arguments> collectToArrayTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iterator(), new long[] {0, 1, 2, 3, 4}),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), new long[0])
				);
	}

	@ParameterizedTest
	@MethodSource("collectToMapTestDataProvider")
	void testCollectToMap(final AbstractLongFlow iterator, final LongFunction<String> keyMapper, final LongFunction<Long> valueMapper, final List<Pair<String, Long>> expectedMapPairs)
	{
		final Map<String, Long> expectedMap = expectedMapPairs.stream().collect(toMap(Pair::first, Pair::second));
		assertEquals(expectedMap, iterator.toMap(keyMapper, valueMapper));
	}

	static Stream<Arguments> collectToMapTestDataProvider()
	{
		final LongFunction<String> keyMapper = Long::toString;
		final LongFunction<Long> valueMapper = Long::valueOf;

		return Stream.of(
				Arguments.of(
						getSmallLongTestIteratorProvider().iterator(),
						keyMapper,
						valueMapper,
						asList(Pair.of("10", 10L), Pair.of("11", 11L))
						),

				Arguments.of(
						getEmptyLongTestIteratorProvider().iterator(),
						keyMapper,
						valueMapper,
						asList()
						)
				);
	}

	@ParameterizedTest
	@MethodSource("groupByTestDataProvider")
	void testGroupBy(final AbstractLongFlow iterator, final LongFunction<Integer> classifier, final List<Pair<Integer, long[]>> expectedMapPairs)
	{
		final Map<Integer, long[]> expectedMap = expectedMapPairs.stream().collect(toMap(Pair::first, Pair::second));
		final Map<Integer, long[]> actualMap = iterator.groupBy(classifier);
		assertEquals(expectedMap.keySet(), actualMap.keySet());
		expectedMap.keySet().stream().forEach(key -> assertArrayEquals(expectedMap.get(key), actualMap.get(key), key.toString()));
	}

	static Stream<Arguments> groupByTestDataProvider()
	{
		final LongFunction<Integer> classifier = x -> ((int) x) % 2;

		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iterator(), classifier, asList(Pair.of(0, new long[] {0, 2, 4}), Pair.of(1, new long[] {1, 3}))),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), classifier, asList())
				);
	}
}

