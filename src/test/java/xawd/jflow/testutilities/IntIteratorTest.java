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

import xawd.jflow.iterators.AbstractIntFlow;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface IntIteratorTest
{
	default void assertIntIteratorAsExpected(final int[] expectedElements, final AbstractIterableInts iteratorProvider)
	{
		assertSizeAsExpected(expectedElements, iteratorProvider.iterator());
		assertSkippingAsExpected(expectedElements, iteratorProvider.iterator());
		assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iterator());
		assertStandardIterationAsExpected(expectedElements, iteratorProvider.iterator());
		assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iterator());
		assertAlternatingNextAndSkipCallsAsExpected(expectedElements, iteratorProvider.iterator());
	}

	static void assertSizeAsExpected(int[] expectedElements, AbstractIntFlow iterator)
	{
		if (iterator.sizeIsKnown()) {
			assertEquals(expectedElements.length, iterator.size().getAsInt());
		}
	}

	static void assertSkippingAsExpected(final int[] expectedElements, final AbstractIntFlow iterator)
	{
		IntStream.range(0, expectedElements.length).forEach(i -> iterator.skip());
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	static void assertNextElementChecksAsExpected(final int[] expectedElements, final AbstractIntFlow iterator)
	{
		IntStream.range(0, expectedElements.length).forEach(i ->
		{
			assertTrue(iterator.hasNext());
			iterator.skip();
		});
		assertFalse(iterator.hasNext());
	}

	static void assertStandardIterationAsExpected(final int[] expectedElements, final AbstractIntFlow iterator)
	{
		final List<Integer> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			recoveredElements.add(iterator.nextInt());
		}
		assertThrows(NoSuchElementException.class, iterator::nextInt);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertArrayEquals(expectedElements, convertFromBoxed(recoveredElements));
	}

	static void assertUncheckedIterationAsExpected(final int[] expectedElements, final AbstractIntFlow iterator)
	{
		final List<Integer> recoveredElements = new ArrayList<>();
		IntStream.range(0, expectedElements.length).forEach(i -> recoveredElements.add(iterator.nextInt()));

		assertThrows(NoSuchElementException.class, iterator::nextInt);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertArrayEquals(expectedElements, convertFromBoxed(recoveredElements));
	}

	static void assertAlternatingNextAndSkipCallsAsExpected(final int[] expectedElements, final AbstractIntFlow iterator)
	{
		final List<Integer> expectedOutcome = new ArrayList<>(), recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.length).forEach(i ->
		{
			if (i % 2 == 0) {
				recoveredElements.add(iterator.nextInt());
				expectedOutcome.add(expectedElements[i]);
			}
			else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::nextInt);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}

	static int[] convertFromBoxed(final List<Integer> boxedInts)
	{
		return boxedInts.stream().mapToInt(i -> i).toArray();
	}
}
