package com.github.maumay.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.AbstractIteratorSize;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface ObjectIteratorTest
{
	default <T> void assertObjectIteratorAsExpected(List<T> expectedElements,
			AbstractRichIterable<T> iteratorProvider)
	{
		assertSizeAsExpected(expectedElements, iteratorProvider.iterator());
		assertSizeDecreasesAsExpected(iteratorProvider.iterator());
		assertSkippingAsExpected(expectedElements, iteratorProvider.iterator());
		assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iterator());
		assertStandardIterationAsExpected(expectedElements, iteratorProvider.iterator());
		assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iterator());
		assertAlternatingNextAndSkipCallsAsExpected(expectedElements, iteratorProvider.iterator());
	}

	static <T> void assertSizeAsExpected(List<T> expectedElements,
			AbstractRichIterator<T> iterator)
	{
		iterator.getSize().getExactSize().ifPresent(n -> {
			assertEquals(expectedElements.size(), n);
		});
		iterator.getSize().getMinimalUpperBound().ifPresent(n -> {
			assertTrue(expectedElements.size() <= n);
		});
		iterator.getSize().getMaximalLowerBound().ifPresent(n -> {
			assertTrue(n <= expectedElements.size());
		});
	}

	static void assertSizeDecreasesAsExpected(AbstractRichIterator<?> iterator)
	{
		AbstractIteratorSize size = iterator.getSize();
		OptionalInt lower = size.getMaximalLowerBound(), exact = size.getExactSize(),
				upper = size.getMinimalUpperBound();

		int count = 0;
		while (iterator.hasNext()) {
			count++;
			iterator.next();
			assertEquals(size.getMaximalLowerBound(), Utils.subtractSize(lower, count));
			assertEquals(size.getMinimalUpperBound(), Utils.subtractSize(upper, count));
			assertEquals(size.getExactSize(), Utils.subtractSize(exact, count));
		}
	}

	static <T> void assertSkippingAsExpected(List<T> expectedElements,
			AbstractRichIterator<T> iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> iterator.skip());
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	static <T> void assertNextElementChecksAsExpected(List<T> expectedElements,
			AbstractRichIterator<T> iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> {
			assertTrue(iterator.hasNext());
			iterator.skip();
		});
		assertFalse(iterator.hasNext());
	}

	static <T> void assertStandardIterationAsExpected(List<T> expectedElements,
			AbstractRichIterator<T> iterator)
	{
		List<T> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			recoveredElements.add(iterator.next());
		}
		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	static <T> void assertUncheckedIterationAsExpected(List<T> expectedElements,
			AbstractRichIterator<T> iterator)
	{
		List<T> recoveredElements = new ArrayList<>();
		IntStream.range(0, expectedElements.size())
				.forEach(i -> recoveredElements.add(iterator.next()));

		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	static <T> void assertAlternatingNextAndSkipCallsAsExpected(List<T> expectedElements,
			AbstractRichIterator<T> iterator)
	{
		List<T> expectedOutcome = new ArrayList<>(), recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.size()).forEach(i -> {
			if (i % 2 == 0) {
				recoveredElements.add(iterator.next());
				expectedOutcome.add(expectedElements.get(i));
			} else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}
}
