/**
 *
 */
package maumay.jflow.iterators.impl.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractEnhancedIntIteratorPredicateConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("allEqualTestDataProvider")
	void testAllEqual(final AbstractEnhancedIntIterator iterator,
			final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.areAllEqual());
	}

	static Stream<Arguments> allEqualTestDataProvider()
	{
		return Stream.of(Arguments.of(getAllEqualFlow(), Boolean.TRUE),
				Arguments.of(getIntTestIteratorProvider().iter(), Boolean.FALSE),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(), Boolean.TRUE));
	}

	private static AbstractEnhancedIntIterator getAllEqualFlow()
	{
		return new AbstractEnhancedIntIterator(OptionalInt.of(3)) {
			int count = 0;

			@Override
			public boolean hasNext()
			{
				return count < 3;
			}

			@Override
			public int nextInt()
			{
				if (count++ >= 3) {
					throw new NoSuchElementException();
				}
				return 0;
			}

			@Override
			public void skip()
			{
				nextInt();
			}
		};
	}

	@ParameterizedTest
	@MethodSource("allMatchTestDataProvider")
	void testAllMatch(final AbstractEnhancedIntIterator iterator,
			final IntPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.allMatch(predicate));
	}

	static Stream<Arguments> allMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x > -1, Boolean.TRUE),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x > -1, Boolean.TRUE));
	}

	@ParameterizedTest
	@MethodSource("anyMatchTestDataProvider")
	void testAnyMatch(final AbstractEnhancedIntIterator iterator,
			final IntPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.anyMatch(predicate));
	}

	static Stream<Arguments> anyMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x < -1, Boolean.FALSE),
				Arguments.of(getIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x > 3, Boolean.TRUE),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x > -1, Boolean.FALSE));
	}

	@ParameterizedTest
	@MethodSource("noneMatchTestDataProvider")
	void testNoneMatch(final AbstractEnhancedIntIterator iterator,
			final IntPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.noneMatch(predicate));
	}

	static Stream<Arguments> noneMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x < -1, Boolean.TRUE),
				Arguments.of(getIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x > 3, Boolean.FALSE),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyIntTestIteratorProvider().iter(),
						(IntPredicate) x -> x > -1, Boolean.TRUE));
	}
}
