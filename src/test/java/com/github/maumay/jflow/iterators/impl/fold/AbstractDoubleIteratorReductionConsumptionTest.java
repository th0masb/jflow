/**
 *
 */
package com.github.maumay.jflow.iterators.impl.fold;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterators.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorReductionConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("reductionWithoutIdTestDataProvider")
	void testReductionWithoutId(DoubleBinaryOperator reducer, Double expectedPopulatedResult)
	{
		AbstractDoubleIterator populated = getDoubleTestIteratorProvider().iter();
		OptionalDouble reduction = populated.foldOption(reducer);
		assertTrue(reduction.isPresent());
		assertEquals(expectedPopulatedResult.doubleValue(), reduction.getAsDouble());

		AbstractDoubleIterator empty = getEmptyDoubleTestIteratorProvider().iter();
		assertFalse(empty.foldOption(reducer).isPresent());
	}

	static Stream<Arguments> reductionWithoutIdTestDataProvider()
	{
		return Stream.of(Arguments.of((DoubleBinaryOperator) (x1, x2) -> x1 + x2, 10.0));
	}

	@ParameterizedTest
	@MethodSource("reductionWithIdTestDataProvider")
	void testReductionWithId(Double id, DoubleBinaryOperator reducer,
			Double expectedPopulatedResult)
	{
		AbstractDoubleIterator populated = getDoubleTestIteratorProvider().iter();
		double reduction = populated.fold(id.doubleValue(), reducer);
		assertEquals(expectedPopulatedResult.doubleValue(), reduction);

		AbstractDoubleIterator empty = getEmptyDoubleTestIteratorProvider().iter();
		assertEquals(id.doubleValue(), empty.fold(id.doubleValue(), reducer));
	}

	static Stream<Arguments> reductionWithIdTestDataProvider()
	{
		return Stream.of(Arguments.of(0.0, (DoubleBinaryOperator) (x1, x2) -> x1 + x2, 10.0));
	}

	@ParameterizedTest
	@MethodSource("countReductionTestDataProvider")
	void testCounting(AbstractDoubleIterator iterator, Integer expectedCount)
	{
		assertEquals(expectedCount.intValue(), iterator.count());
	}

	static Stream<Arguments> countReductionTestDataProvider()
	{
		return Stream.of(Arguments.of(getDoubleTestIteratorProvider().iter(), 5),
				Arguments.of(getSmallDoubleTestIteratorProvider().iter(), 2),
				Arguments.of(getLargeDoubleTestIteratorProvider().iter(), 6),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(), 0));
	}
}
