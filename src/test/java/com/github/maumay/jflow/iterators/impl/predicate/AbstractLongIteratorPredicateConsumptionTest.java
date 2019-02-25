/**
 *
 */
package com.github.maumay.jflow.iterators.impl.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.LongPredicate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractLongIteratorPredicateConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("allEqualTestDataProvider")
	void testAllEqual(final AbstractLongIterator iterator,
			final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.areAllEqual());
	}

	static Stream<Arguments> allEqualTestDataProvider()
	{
		return Stream.of(Arguments.of(getAllEqualFlow(), Boolean.TRUE),
				Arguments.of(getLongTestIteratorProvider().iter(), Boolean.FALSE),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(),
						Boolean.TRUE));
	}

	private static AbstractLongIterator getAllEqualFlow()
	{
		return new AbstractLongIterator(OptionalInt.of(3)) {
			int count = 0;

			@Override
			public boolean hasNext()
			{
				return count < 3;
			}

			@Override
			public long nextLong()
			{
				if (count++ >= 3) {
					throw new NoSuchElementException();
				}
				return 0;
			}

			@Override
			public void skip()
			{
				nextLong();
			}
		};
	}

	@ParameterizedTest
	@MethodSource("allMatchTestDataProvider")
	void testAllMatch(final AbstractLongIterator iterator,
			final LongPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.allMatch(predicate));
	}

	static Stream<Arguments> allMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x > -1, Boolean.TRUE),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x > -1, Boolean.TRUE));
	}

	@ParameterizedTest
	@MethodSource("anyMatchTestDataProvider")
	void testAnyMatch(final AbstractLongIterator iterator,
			final LongPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.anyMatch(predicate));
	}

	static Stream<Arguments> anyMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x < -1, Boolean.FALSE),
				Arguments.of(getLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x > 3, Boolean.TRUE),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x > -1, Boolean.FALSE));
	}

	@ParameterizedTest
	@MethodSource("noneMatchTestDataProvider")
	void testNoneMatch(final AbstractLongIterator iterator,
			final LongPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.noneMatch(predicate));
	}

	static Stream<Arguments> noneMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x < -1, Boolean.TRUE),
				Arguments.of(getLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x > 3, Boolean.FALSE),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyLongTestIteratorProvider().iter(),
						(LongPredicate) x -> x > -1, Boolean.TRUE));
	}
}
