/**
 *
 */
package com.github.maumay.jflow.iterators.impl.collectingtests;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorCollectionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("collectToCollectionTestDataProvider")
	void testCollectToCollection(AbstractRichIterator<String> iterator,
			Supplier<Collection<String>> containerConstructor, Collection<String> expectedResult)
	{
		assertEquals(expectedResult, iterator.toCollection(containerConstructor));
	}

	static Stream<Arguments> collectToCollectionTestDataProvider()
	{
		return Stream.of(Arguments.of(getObjectTestIteratorProvider().iterator(),
				(Supplier<Collection<String>>) ArrayList::new, asList("0", "1", "2", "3", "4")),
				Arguments.of(getObjectTestIteratorProvider().iterator(),
						(Supplier<Collection<String>>) HashSet::new,
						new HashSet<>(asList("0", "1", "2", "3", "4"))),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Supplier<Collection<String>>) ArrayList::new, asList()),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Supplier<Collection<String>>) HashSet::new, new HashSet<>()));
	}

	@ParameterizedTest
	@MethodSource("collectToMapTestDataProvider")
	void testCollectToMap(AbstractRichIterator<String> iterator,
			UnaryOperator<String> keyMapper, Function<String, Integer> valueMapper,
			List<Tup<String, Integer>> expectedMapPairs)
	{
		Map<String, Integer> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Tup::_1, Tup::_2));
		assertEquals(expectedMap, iterator.toMap(keyMapper, valueMapper));
	}

	static Stream<Arguments> collectToMapTestDataProvider()
	{
		return Stream.of(Arguments.of(getSmallObjectTestIteratorProvider().iterator(),
				(UnaryOperator<String>) x -> x + x, (Function<String, Integer>) Integer::parseInt,
				asList(Tup.of("1010", 10), Tup.of("1111", 11))),

				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(UnaryOperator<String>) x -> x + x,
						(Function<String, Integer>) Integer::parseInt, asList()));
	}

	@ParameterizedTest
	@MethodSource("groupByTestDataProvider")
	void testGroupBy(AbstractRichIterator<String> iterator,
			Function<String, Integer> classifier, List<Tup<Integer, List<String>>> expectedMapPairs)
	{
		Map<Integer, List<String>> expectedMap = expectedMapPairs.stream()
				.collect(toMap(Tup::_1, Tup::_2));
		assertEquals(expectedMap, iterator.groupBy(classifier));
	}

	static Stream<Arguments> groupByTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getObjectTestIteratorProvider().iterator(),
						(Function<String, Integer>) s -> parseInt(s) % 2,
						asList(Tup.of(0, asList("0", "2", "4")), Tup.of(1, asList("1", "3")))),

				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Function<String, Integer>) s -> parseInt(s) % 2, asList()));
	}
}
