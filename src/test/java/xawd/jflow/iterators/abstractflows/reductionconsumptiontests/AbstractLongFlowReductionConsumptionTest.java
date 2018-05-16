/**
 *
 */
package xawd.jflow.iterators.abstractflows.reductionconsumptiontests;

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
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractLongFlowReductionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("reductionWithoutIdTestDataProvider")
	void testReductionWithoutId(final LongBinaryOperator reducer, final Long expectedPopulatedResult)
	{
		final AbstractLongFlow populated = getLongTestIteratorProvider().iter();
		final OptionalLong reduction = populated.reduce(reducer);
		assertTrue(reduction.isPresent());
		assertEquals(expectedPopulatedResult.longValue(), reduction.getAsLong());

		final AbstractLongFlow empty = getEmptyLongTestIteratorProvider().iter();
		assertFalse(empty.reduce(reducer).isPresent());
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
		final AbstractLongFlow populated = getLongTestIteratorProvider().iter();
		final long reduction = populated.reduce(id.longValue(), reducer);
		assertEquals(expectedPopulatedResult.longValue(), reduction);

		final AbstractLongFlow empty = getEmptyLongTestIteratorProvider().iter();
		assertEquals(id.longValue(), empty.reduce(id.longValue(), reducer));
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
				Arguments.of(getLongTestIteratorProvider().iter(), 5),
				Arguments.of(getSmallLongTestIteratorProvider().iter(), 2),
				Arguments.of(getLargeLongTestIteratorProvider().iter(), 6),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(), 0)
				);
	}
}
