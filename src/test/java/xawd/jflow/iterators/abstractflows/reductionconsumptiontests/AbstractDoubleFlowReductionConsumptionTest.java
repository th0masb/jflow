/**
 *
 */
package xawd.jflow.iterators.abstractflows.reductionconsumptiontests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractDoubleFlowReductionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("reductionWithoutIdTestDataProvider")
	void testReductionWithoutId(final DoubleBinaryOperator reducer, final Double expectedPopulatedResult)
	{
		final AbstractDoubleFlow populated = getDoubleTestIteratorProvider().iter();
		final OptionalDouble reduction = populated.reduce(reducer);
		assertTrue(reduction.isPresent());
		assertEquals(expectedPopulatedResult.doubleValue(), reduction.getAsDouble());

		final AbstractDoubleFlow empty = getEmptyDoubleTestIteratorProvider().iter();
		assertFalse(empty.reduce(reducer).isPresent());
	}

	static Stream<Arguments> reductionWithoutIdTestDataProvider()
	{
		return Stream.of(
				Arguments.of((DoubleBinaryOperator) (x1, x2) -> x1 + x2, 10.0)
				);
	}

	@ParameterizedTest
	@MethodSource("reductionWithIdTestDataProvider")
	void testReductionWithId(final Double id, final DoubleBinaryOperator reducer, final Double expectedPopulatedResult)
	{
		final AbstractDoubleFlow populated = getDoubleTestIteratorProvider().iter();
		final double reduction = populated.reduce(id.doubleValue(), reducer);
		assertEquals(expectedPopulatedResult.doubleValue(), reduction);

		final AbstractDoubleFlow empty = getEmptyDoubleTestIteratorProvider().iter();
		assertEquals(id.doubleValue(), empty.reduce(id.doubleValue(), reducer));
	}

	static Stream<Arguments> reductionWithIdTestDataProvider()
	{
		return Stream.of(
				Arguments.of(0.0, (DoubleBinaryOperator) (x1, x2) -> x1 + x2, 10.0)
				);
	}


	@ParameterizedTest
	@MethodSource("countReductionTestDataProvider")
	void testCounting(final AbstractDoubleFlow iterator, final Integer expectedCount)
	{
		assertEquals(expectedCount.intValue(), iterator.count());
	}

	static Stream<Arguments> countReductionTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iter(), 5),
				Arguments.of(getSmallDoubleTestIteratorProvider().iter(), 2),
				Arguments.of(getLargeDoubleTestIteratorProvider().iter(), 6),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(), 0)
				);
	}
}
