package com.github.maumay.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractIteratorSize;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface DoubleIteratorTest
{
	default void assertDoubleIteratorAsExpected(List<Double> expectedElements,
			List<AbstractIterableDoubles> iteratorProviders)
	{
		for (AbstractIterableDoubles iteratorProvider : iteratorProviders) {
			assertSizeDecreasesAsExpected(iteratorProvider.iter());
			assertSkippingAsExpected(expectedElements, iteratorProvider.iter());
			assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iter());
			assertStandardIterationAsExpected(expectedElements, iteratorProvider.iter());
			assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iter());
			assertAlternatingNextAndSkipCallsAsExpected(expectedElements, iteratorProvider.iter());
		}
	}

	static void assertSizeDecreasesAsExpected(AbstractDoubleIterator iterator)
	{
		AbstractIteratorSize startSize = iterator.getSize().copy();

		int count = 0;
		while (iterator.hasNext()) {
			count++;
			iterator.nextDouble();
			assertEquals(startSize.subtract(count), iterator.getSize());
		}
	}

	static void assertSkippingAsExpected(List<Double> expectedElements,
			AbstractDoubleIterator iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> iterator.skip());
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	static void assertNextElementChecksAsExpected(List<Double> expectedElements,
			AbstractDoubleIterator iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> {
			assertTrue(iterator.hasNext());
			iterator.skip();
		});
		assertFalse(iterator.hasNext());
	}

	static void assertStandardIterationAsExpected(List<Double> expectedElements,
			AbstractDoubleIterator iterator)
	{
		List<Double> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			recoveredElements.add(iterator.nextDouble());
		}
		assertThrows(NoSuchElementException.class, iterator::nextDouble);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	static void assertUncheckedIterationAsExpected(List<Double> expectedElements,
			AbstractDoubleIterator iterator)
	{
		List<Double> recoveredElements = new ArrayList<>();
		IntStream.range(0, expectedElements.size())
				.forEach(i -> recoveredElements.add(iterator.nextDouble()));

		assertThrows(NoSuchElementException.class, iterator::nextDouble);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	static void assertAlternatingNextAndSkipCallsAsExpected(List<Double> expectedElements,
			AbstractDoubleIterator iterator)
	{
		List<Double> expectedOutcome = new ArrayList<>(), recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.size()).forEach(i -> {
			if (i % 2 == 0) {
				recoveredElements.add(iterator.nextDouble());
				expectedOutcome.add(expectedElements.get(i));
			} else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::nextDouble);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}
}
