package com.github.maumay.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import com.github.maumay.jflow.impl.AbstractIteratorSize;
import com.github.maumay.jflow.impl.AbstractLongIterator;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface LongIteratorTest
{
	default void assertLongIteratorAsExpected(List<Long> expectedElements,
			List<AbstractIterableLongs> iteratorProviders)
	{
		for (AbstractIterableLongs iteratorProvider : iteratorProviders) {
			assertSizeDecreasesAsExpected(iteratorProvider.iter());
			assertSkippingAsExpected(expectedElements, iteratorProvider.iter());
			assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iter());
			assertStandardIterationAsExpected(expectedElements, iteratorProvider.iter());
			assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iter());
			assertAlternatingNextAndSkipCallsAsExpected(expectedElements, iteratorProvider.iter());
		}
	}

	static void assertSizeDecreasesAsExpected(AbstractLongIterator iterator)
	{
		AbstractIteratorSize startSize = iterator.getSize().copy();

		int count = 0;
		while (iterator.hasNext()) {
			count++;
			iterator.nextLong();
			assertEquals(startSize.subtract(count), iterator.getSize());
		}
	}

	static void assertSkippingAsExpected(List<Long> expectedElements, AbstractLongIterator iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> iterator.skip());
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	static void assertNextElementChecksAsExpected(List<Long> expectedElements,
			AbstractLongIterator iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> {
			assertTrue(iterator.hasNext());
			iterator.skip();
		});
		assertFalse(iterator.hasNext());
	}

	static void assertStandardIterationAsExpected(List<Long> expectedElements,
			AbstractLongIterator iterator)
	{
		List<Long> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			recoveredElements.add(iterator.nextLong());
		}
		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	static void assertUncheckedIterationAsExpected(List<Long> expectedElements,
			AbstractLongIterator iterator)
	{
		List<Long> recoveredElements = new ArrayList<>();
		IntStream.range(0, expectedElements.size())
				.forEach(i -> recoveredElements.add(iterator.nextLong()));

		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	static void assertAlternatingNextAndSkipCallsAsExpected(List<Long> expectedElements,
			AbstractLongIterator iterator)
	{
		List<Long> expectedOutcome = new ArrayList<>(), recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.size()).forEach(i -> {
			if (i % 2 == 0) {
				recoveredElements.add(iterator.nextLong());
				expectedOutcome.add(expectedElements.get(i));
			} else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}
}
