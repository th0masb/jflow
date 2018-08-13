/**
 *
 */
package xawd.jflow.iterators.impl.fold;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractIntFlowReductionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("reductionWithoutIdTestDataProvider")
	void testReductionWithoutId(final IntBinaryOperator reducer, final Integer expectedPopulatedResult)
	{
		final AbstractIntFlow populated = getIntTestIteratorProvider().iterator();
		final OptionalInt reduction = populated.fold(reducer);
		assertTrue(reduction.isPresent());
		assertEquals(expectedPopulatedResult.intValue(), reduction.getAsInt());

		final AbstractIntFlow empty = getEmptyIntTestIteratorProvider().iterator();
		assertFalse(empty.fold(reducer).isPresent());
	}

	static Stream<Arguments> reductionWithoutIdTestDataProvider()
	{
		return Stream.of(
				Arguments.of((IntBinaryOperator) (x1, x2) -> x1 + x2, 10)
				);
	}

	@ParameterizedTest
	@MethodSource("reductionWithIdTestDataProvider")
	void testReductionWithId(final Integer id, final IntBinaryOperator reducer, final Integer expectedPopulatedResult)
	{
		final AbstractIntFlow populated = getIntTestIteratorProvider().iterator();
		final int reduction = populated.fold(id.intValue(), reducer);
		assertEquals(expectedPopulatedResult.intValue(), reduction);

		final AbstractIntFlow empty = getEmptyIntTestIteratorProvider().iterator();
		assertEquals(id.intValue(), empty.fold(id.intValue(), reducer));
	}

	static Stream<Arguments> reductionWithIdTestDataProvider()
	{
		return Stream.of(
				Arguments.of(0, (IntBinaryOperator) (x1, x2) -> x1 + x2, 10)
				);
	}


	@ParameterizedTest
	@MethodSource("countReductionTestDataProvider")
	void testCounting(final AbstractIntFlow iterator, final Integer expectedCount)
	{
		assertEquals(expectedCount.intValue(), iterator.count());
	}

	static Stream<Arguments> countReductionTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iterator(), 5),
				Arguments.of(getSmallIntTestIteratorProvider().iterator(), 2),
				Arguments.of(getLargeIntTestIteratorProvider().iterator(), 6),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), 0)
				);
	}
}
