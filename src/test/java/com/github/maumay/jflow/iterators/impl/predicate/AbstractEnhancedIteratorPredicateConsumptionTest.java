/**
 *
 */
package com.github.maumay.jflow.iterators.impl.predicate;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.KnownSize;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorPredicateConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("allEqualTestDataProvider")
	void testAllEqual(AbstractRichIterator<String> iterator, Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.areAllEqual());
	}

	static Stream<Arguments> allEqualTestDataProvider()
	{
		return Stream.of(Arguments.of(getAllEqualFlow(), Boolean.TRUE),
				Arguments.of(getObjectTestIteratorProvider().iterator(), Boolean.FALSE),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(), Boolean.TRUE));
	}

	private static AbstractRichIterator<String> getAllEqualFlow()
	{
		return new AbstractRichIterator<String>(KnownSize.of(3)) {
			int count = 0;

			@Override
			public boolean hasNext()
			{
				return count < 3;
			}

			@Override
			public String nextImpl()
			{
				if (count++ >= 3) {
					throw new NoSuchElementException();
				}
				return "x";
			}

			@Override
			public void skipImpl()
			{
				next();
			}
		};
	}

	@ParameterizedTest
	@MethodSource("allMatchTestDataProvider")
	void testAllMatch(AbstractRichIterator<String> iterator, Predicate<String> predicate,
			Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.allMatch(predicate));
	}

	static Stream<Arguments> allMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) < 3, Boolean.FALSE),
				Arguments.of(getObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) > -1, Boolean.TRUE),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) < 3, Boolean.TRUE),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) > -1, Boolean.TRUE));
	}

	@ParameterizedTest
	@MethodSource("anyMatchTestDataProvider")
	void testAnyMatch(AbstractRichIterator<String> iterator, Predicate<String> predicate,
			Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.anyMatch(predicate));
	}

	static Stream<Arguments> anyMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) < -1, Boolean.FALSE),
				Arguments.of(getObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) > 3, Boolean.TRUE),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) < 3, Boolean.FALSE),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) > -1, Boolean.FALSE));
	}

	@ParameterizedTest
	@MethodSource("noneMatchTestDataProvider")
	void testNoneMatch(AbstractRichIterator<String> iterator, Predicate<String> predicate,
			Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.noneMatch(predicate));
	}

	static Stream<Arguments> noneMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) < -1, Boolean.TRUE),
				Arguments.of(getObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) > 3, Boolean.FALSE),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) < 3, Boolean.TRUE),
				Arguments.of(getEmptyObjectTestIteratorProvider().iterator(),
						(Predicate<String>) s -> parseDouble(s) > -1, Boolean.TRUE));
	}
}
