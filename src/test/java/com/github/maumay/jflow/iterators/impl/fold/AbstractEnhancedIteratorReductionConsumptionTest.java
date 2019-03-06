package com.github.maumay.jflow.iterators.impl.fold;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorReductionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("singleTypeReductionTestDataProvider")
	void testSingleTypeReduction(BinaryOperator<String> reducer, String expectedPopulatedResult)
	{
		AbstractEnhancedIterator<String> populated = getObjectTestIteratorProvider().iterator();
		Optional<String> reduction = populated.foldOption(reducer);
		assertTrue(reduction.isPresent());
		assertEquals(expectedPopulatedResult, reduction.get());

		AbstractEnhancedIterator<String> empty = getEmptyObjectTestIteratorProvider().iterator();
		assertFalse(empty.foldOption(reducer).isPresent());
	}

	static Stream<Arguments> singleTypeReductionTestDataProvider()
	{
		return Stream.of(Arguments.of((BinaryOperator<String>) (s1, s2) -> s1 + s2, "01234"));
	}

	@ParameterizedTest
	@MethodSource("twoTypeReductionTestDataProvider")
	void testTwoTypeReduction(Double id, BiFunction<Double, String, Double> reducer,
			Double expectedPopulatedResult)
	{
		AbstractEnhancedIterator<String> populated = getObjectTestIteratorProvider().iterator();
		Double reduction = populated.fold(id, reducer);
		assertEquals(expectedPopulatedResult, reduction);

		AbstractEnhancedIterator<String> empty = getEmptyObjectTestIteratorProvider().iterator();
		assertEquals(id, empty.fold(id, reducer));
	}

	static Stream<Arguments> twoTypeReductionTestDataProvider()
	{
		return Stream.of(Arguments.of(0.0,
				(BiFunction<Double, String, Double>) (x, s) -> x + parseDouble(s), 10.0));
	}

	@ParameterizedTest
	@MethodSource("countReductionTestDataProvider")
	void testCounting(AbstractEnhancedIterator<String> iterator, Integer expectedCount)
	{
		assertEquals(expectedCount.intValue(), iterator.count());
	}

	static Stream<Arguments> countReductionTestDataProvider()
	{
		return Stream.of(Arguments.of(getObjectTestIteratorProvider().iterator(), 5),
				Arguments.of(getSmallObjectTestIteratorProvider().iterator(), 2),
				Arguments.of(getLargeObjectTestIteratorProvider().iterator(), 6),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(), 0));
	}
}
