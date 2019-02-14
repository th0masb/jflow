/**
 *
 */
package maumay.jflow.iterators.impl.fold;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractEnhancedIntIteratorReductionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("reductionWithoutIdTestDataProvider")
	void testReductionWithoutId(final IntBinaryOperator reducer,
			final Integer expectedPopulatedResult)
	{
		final AbstractEnhancedIntIterator populated = getIntTestIteratorProvider()
				.iter();
		final OptionalInt reduction = populated.foldOption(reducer);
		assertTrue(reduction.isPresent());
		assertEquals(expectedPopulatedResult.intValue(), reduction.getAsInt());

		final AbstractEnhancedIntIterator empty = getEmptyIntTestIteratorProvider()
				.iter();
		assertFalse(empty.foldOption(reducer).isPresent());
	}

	static Stream<Arguments> reductionWithoutIdTestDataProvider()
	{
		return Stream.of(Arguments.of((IntBinaryOperator) (x1, x2) -> x1 + x2, 10));
	}

	@ParameterizedTest
	@MethodSource("reductionWithIdTestDataProvider")
	void testReductionWithId(final Integer id, final IntBinaryOperator reducer,
			final Integer expectedPopulatedResult)
	{
		final AbstractEnhancedIntIterator populated = getIntTestIteratorProvider()
				.iter();
		final int reduction = populated.fold(id.intValue(), reducer);
		assertEquals(expectedPopulatedResult.intValue(), reduction);

		final AbstractEnhancedIntIterator empty = getEmptyIntTestIteratorProvider()
				.iter();
		assertEquals(id.intValue(), empty.fold(id.intValue(), reducer));
	}

	static Stream<Arguments> reductionWithIdTestDataProvider()
	{
		return Stream.of(Arguments.of(0, (IntBinaryOperator) (x1, x2) -> x1 + x2, 10));
	}

	@ParameterizedTest
	@MethodSource("countReductionTestDataProvider")
	void testCounting(final AbstractEnhancedIntIterator iterator,
			final Integer expectedCount)
	{
		assertEquals(expectedCount.intValue(), iterator.count());
	}

	static Stream<Arguments> countReductionTestDataProvider()
	{
		return Stream.of(Arguments.of(getIntTestIteratorProvider().iter(), 5),
				Arguments.of(getSmallIntTestIteratorProvider().iter(), 2),
				Arguments.of(getLargeIntTestIteratorProvider().iter(), 6),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(), 0));
	}
}
