package xawd.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import xawd.jflow.iterators.AbstractLongFlow;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface LongIteratorTest
{
	default void assertLongIteratorAsExpected(final long[] expectedElements, final AbstractIterableLongs iteratorProvider)
	{
		assertSizeAsExpected(expectedElements, iteratorProvider.iterator());
		assertSkippingAsExpected(expectedElements, iteratorProvider.iterator());
		assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iterator());
		assertStandardIterationAsExpected(expectedElements, iteratorProvider.iterator());
		assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iterator());
		assertAlternatingNextAndSkipCallsAsExpected(expectedElements, iteratorProvider.iterator());
	}

	static void assertSizeAsExpected(long[] expectedElements, AbstractLongFlow iterator)
	{
		if (iterator.sizeIsKnown()) {
			assertEquals(expectedElements.length, iterator.size().getAsInt());
		}
	}

	static void assertSkippingAsExpected(final long[] expectedElements, final AbstractLongFlow iterator)
	{
		IntStream.range(0, expectedElements.length).forEach(i -> iterator.skip());
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	static void assertNextElementChecksAsExpected(final long[] expectedElements, final AbstractLongFlow iterator)
	{
		IntStream.range(0, expectedElements.length).forEach(i ->
		{
			assertTrue(iterator.hasNext());
			iterator.skip();
		});
		assertFalse(iterator.hasNext());
	}

	static void assertStandardIterationAsExpected(final long[] expectedElements, final AbstractLongFlow iterator)
	{
		final List<Long> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			recoveredElements.add(iterator.nextLong());
		}
		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertArrayEquals(expectedElements, convertFromBoxed(recoveredElements));
	}

	static void assertUncheckedIterationAsExpected(final long[] expectedElements, final AbstractLongFlow iterator)
	{
		final List<Long> recoveredElements = new ArrayList<>();
		IntStream.range(0, expectedElements.length).forEach(i -> recoveredElements.add(iterator.nextLong()));

		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertArrayEquals(expectedElements, convertFromBoxed(recoveredElements));
	}

	static void assertAlternatingNextAndSkipCallsAsExpected(final long[] expectedElements, final AbstractLongFlow iterator)
	{
		final List<Long> expectedOutcome = new ArrayList<>(), recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.length).forEach(i ->
		{
			if (i % 2 == 0) {
				recoveredElements.add(iterator.nextLong());
				expectedOutcome.add(expectedElements[i]);
			}
			else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::nextLong);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}

	static long[] convertFromBoxed(final List<Long> boxedLongs)
	{
		return boxedLongs.stream().mapToLong(i -> i).toArray();
	}
}
