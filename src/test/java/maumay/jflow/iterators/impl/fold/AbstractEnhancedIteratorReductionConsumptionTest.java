package maumay.jflow.iterators.impl.fold;

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

import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorReductionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("singleTypeReductionTestDataProvider")
	void testSingleTypeReduction(final BinaryOperator<String> reducer,
			final String expectedPopulatedResult)
	{
		final AbstractEnhancedIterator<String> populated = getObjectTestIteratorProvider()
				.iterator();
		final Optional<String> reduction = populated.foldOption(reducer);
		assertTrue(reduction.isPresent());
		assertEquals(expectedPopulatedResult, reduction.get());

		final AbstractEnhancedIterator<String> empty = getEmptyObjectTestIteratorProvider()
				.iterator();
		assertFalse(empty.foldOption(reducer).isPresent());
	}

	static Stream<Arguments> singleTypeReductionTestDataProvider()
	{
		return Stream
				.of(Arguments.of((BinaryOperator<String>) (s1, s2) -> s1 + s2, "01234"));
	}

	@ParameterizedTest
	@MethodSource("twoTypeReductionTestDataProvider")
	void testTwoTypeReduction(final Double id,
			final BiFunction<Double, String, Double> reducer,
			final Double expectedPopulatedResult)
	{
		final AbstractEnhancedIterator<String> populated = getObjectTestIteratorProvider()
				.iterator();
		final Double reduction = populated.fold(id, reducer);
		assertEquals(expectedPopulatedResult, reduction);

		final AbstractEnhancedIterator<String> empty = getEmptyObjectTestIteratorProvider()
				.iterator();
		assertEquals(id, empty.fold(id, reducer));
	}

	static Stream<Arguments> twoTypeReductionTestDataProvider()
	{
		return Stream.of(Arguments.of(0.0,
				(BiFunction<Double, String, Double>) (x, s) -> x + parseDouble(s), 10.0));
	}

	@ParameterizedTest
	@MethodSource("countReductionTestDataProvider")
	void testCounting(final AbstractEnhancedIterator<String> iterator,
			final Integer expectedCount)
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
