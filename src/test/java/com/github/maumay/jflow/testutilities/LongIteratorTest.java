package com.github.maumay.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.github.maumay.jflow.iterators.impl.AbstractIteratorSize;
import com.github.maumay.jflow.iterators.impl.AbstractLongIterator;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface LongIteratorTest
{
	default void assertLongIteratorAsExpected(long[] expectedElements,
			AbstractIterableLongs iteratorProvider)
	{
		assertSizeAsExpected(expectedElements, iteratorProvider.iter());
		assertSizeDecreasesAsExpected(iteratorProvider.iter());
		assertSkippingAsExpected(expectedElements, iteratorProvider.iter());
		assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iter());
		assertStandardIterationAsExpected(expectedElements, iteratorProvider.iter());
		assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iter());
		assertAlternatingNextAndSkipCallsAsExpected(expectedElements, iteratorProvider.iter());
	}

	static void assertSizeAsExpected(long[] expectedElements, AbstractLongIterator iterator)
	{
		iterator.getSize().getExactSize().ifPresent(n -> {
			assertEquals(expectedElements.length, n);
		});
		iterator.getSize().getUpperBound().ifPresent(n -> {
			assertTrue(expectedElements.length <= n);
		});
		iterator.getSize().getLowerBound().ifPresent(n -> {
			assertTrue(n <= expectedElements.length);
		});
	}

	static void assertSizeDecreasesAsExpected(AbstractLongIterator iterator)
	{
		AbstractIteratorSize size = iterator.getSize();
		OptionalInt lower = size.getLowerBound(), exact = size.getExactSize(),
				upper = size.getUpperBound();

		int count = 0;
		while (iterator.hasNext()) {
			count++;
			iterator.nextLong();
			assertEquals(size.getLowerBound(), Utils.subtractSize(lower, count));
			assertEquals(size.getUpperBound(), Utils.subtractSize(upper, count));
			assertEquals(size.getExactSize(), Utils.subtractSize(exact, count));
		}
	}

	static void assertSkippingAsExpected(long[] expectedElements, AbstractLongIterator iterator)
	{
		IntStream.range(0, expectedElements.length).forEach(i -> iterator.skip());
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	static void assertNextElementChecksAsExpected(long[] expectedElements,
			AbstractLongIterator iterator)
	{
		IntStream.range(0, expectedElements.length).forEach(i -> {
			assertTrue(iterator.hasNext());
			iterator.skip();
		});
		assertFalse(iterator.hasNext());
	}

	static void assertStandardIterationAsExpected(long[] expectedElements,
			AbstractLongIterator iterator)
	{
		List<Long> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			recoveredElements.add(iterator.nextLong());
		}
		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertArrayEquals(expectedElements, convertFromBoxed(recoveredElements));
	}

	static void assertUncheckedIterationAsExpected(long[] expectedElements,
			AbstractLongIterator iterator)
	{
		List<Long> recoveredElements = new ArrayList<>();
		IntStream.range(0, expectedElements.length)
				.forEach(i -> recoveredElements.add(iterator.nextLong()));

		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertArrayEquals(expectedElements, convertFromBoxed(recoveredElements));
	}

	static void assertAlternatingNextAndSkipCallsAsExpected(long[] expectedElements,
			AbstractLongIterator iterator)
	{
		List<Long> expectedOutcome = new ArrayList<>(), recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.length).forEach(i -> {
			if (i % 2 == 0) {
				recoveredElements.add(iterator.nextLong());
				expectedOutcome.add(expectedElements[i]);
			} else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}

	static long[] convertFromBoxed(List<Long> boxedLongs)
	{
		return boxedLongs.stream().mapToLong(i -> i).toArray();
	}
}
