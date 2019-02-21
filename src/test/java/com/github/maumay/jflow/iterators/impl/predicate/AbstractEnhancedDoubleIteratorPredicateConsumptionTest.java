/**
 *
 */
package com.github.maumay.jflow.iterators.impl.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.DoublePredicate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractEnhancedDoubleIteratorPredicateConsumptionTest
		extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("allEqualTestDataProvider")
	void testAllEqual(final AbstractEnhancedDoubleIterator iterator,
			final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.areAllEqual());
	}

	static Stream<Arguments> allEqualTestDataProvider()
	{
		return Stream.of(Arguments.of(getAllEqualFlow(), Boolean.TRUE),
				Arguments.of(getDoubleTestIteratorProvider().iter(), Boolean.FALSE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(),
						Boolean.TRUE));
	}

	private static AbstractEnhancedDoubleIterator getAllEqualFlow()
	{
		return new AbstractEnhancedDoubleIterator(OptionalInt.of(3)) {
			int count = 0;

			@Override
			public boolean hasNext()
			{
				return count < 3;
			}

			@Override
			public double nextDouble()
			{
				if (count++ >= 3) {
					throw new NoSuchElementException();
				}
				return 0;
			}

			@Override
			public void skip()
			{
				nextDouble();
			}
		};
	}

	@ParameterizedTest
	@MethodSource("allMatchTestDataProvider")
	void testAllMatch(final AbstractEnhancedDoubleIterator iterator,
			final DoublePredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.allMatch(predicate));
	}

	static Stream<Arguments> allMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x > -1, Boolean.TRUE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x > -1, Boolean.TRUE));
	}

	@ParameterizedTest
	@MethodSource("anyMatchTestDataProvider")
	void testAnyMatch(final AbstractEnhancedDoubleIterator iterator,
			final DoublePredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.anyMatch(predicate));
	}

	static Stream<Arguments> anyMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x < -1, Boolean.FALSE),
				Arguments.of(getDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x > 3, Boolean.TRUE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x > -1, Boolean.FALSE));
	}

	@ParameterizedTest
	@MethodSource("noneMatchTestDataProvider")
	void testNoneMatch(final AbstractEnhancedDoubleIterator iterator,
			final DoublePredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.noneMatch(predicate));
	}

	static Stream<Arguments> noneMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x < -1, Boolean.TRUE),
				Arguments.of(getDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x > 3, Boolean.FALSE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iter(),
						(DoublePredicate) x -> x > -1, Boolean.TRUE));
	}
}
