/**
 *
 */
package xawd.jflow.iterators.impl.fold;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractLongFlowReductionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("reductionWithoutIdTestDataProvider")
	void testReductionWithoutId(final LongBinaryOperator reducer, final Long expectedPopulatedResult)
	{
		final AbstractLongFlow populated = getLongTestIteratorProvider().iterator();
		final OptionalLong reduction = populated.fold(reducer);
		assertTrue(reduction.isPresent());
		assertEquals(expectedPopulatedResult.longValue(), reduction.getAsLong());

		final AbstractLongFlow empty = getEmptyLongTestIteratorProvider().iterator();
		assertFalse(empty.fold(reducer).isPresent());
	}

	static Stream<Arguments> reductionWithoutIdTestDataProvider()
	{
		return Stream.of(
				Arguments.of((LongBinaryOperator) (x1, x2) -> x1 + x2, 10L)
				);
	}

	@ParameterizedTest
	@MethodSource("reductionWithIdTestDataProvider")
	void testReductionWithId(final Long id, final LongBinaryOperator reducer, final Long expectedPopulatedResult)
	{
		final AbstractLongFlow populated = getLongTestIteratorProvider().iterator();
		final long reduction = populated.fold(id.longValue(), reducer);
		assertEquals(expectedPopulatedResult.longValue(), reduction);

		final AbstractLongFlow empty = getEmptyLongTestIteratorProvider().iterator();
		assertEquals(id.longValue(), empty.fold(id.longValue(), reducer));
	}

	static Stream<Arguments> reductionWithIdTestDataProvider()
	{
		return Stream.of(
				Arguments.of(0L, (LongBinaryOperator) (x1, x2) -> x1 + x2, 10L)
				);
	}

	@ParameterizedTest
	@MethodSource("countReductionTestDataProvider")
	void testCounting(final AbstractLongFlow iterator, final Integer expectedCount)
	{
		assertEquals(expectedCount.longValue(), iterator.count());
	}

	static Stream<Arguments> countReductionTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iterator(), 5),
				Arguments.of(getSmallLongTestIteratorProvider().iterator(), 2),
				Arguments.of(getLargeLongTestIteratorProvider().iterator(), 6),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), 0)
				);
	}
}
