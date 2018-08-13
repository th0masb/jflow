package xawd.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import xawd.jflow.iterators.AbstractFlow;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface ObjectIteratorTest
{
	default <T> void assertObjectIteratorAsExpected(final List<T> expectedElements, final AbstractFlowIterable<T> iteratorProvider)
	{
		assertSizeAsExpected(expectedElements, iteratorProvider.iterator());
		assertSkippingAsExpected(expectedElements, iteratorProvider.iterator());
		assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iterator());
		assertStandardIterationAsExpected(expectedElements, iteratorProvider.iterator());
		assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iterator());
		assertAlternatingNextAndSkipCallsAsExpected(expectedElements, iteratorProvider.iterator());
	}

	static <T> void assertSizeAsExpected(List<T> expectedElements, AbstractFlow<T> iterator)
	{
		if (iterator.sizeIsKnown()) {
			assertEquals(expectedElements.size(), iterator.size().getAsInt());
		}
	}

	static <T> void assertSkippingAsExpected(final List<T> expectedElements, final AbstractFlow<T> iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> iterator.skip());
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	static <T> void assertNextElementChecksAsExpected(final List<T> expectedElements, final AbstractFlow<T> iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> {
			assertTrue(iterator.hasNext());
			iterator.skip();
		});
		assertFalse(iterator.hasNext());
	}

	static <T> void assertStandardIterationAsExpected(final List<T> expectedElements, final AbstractFlow<T> iterator)
	{
		final List<T> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			recoveredElements.add(iterator.next());
		}
		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	static <T> void assertUncheckedIterationAsExpected(final List<T> expectedElements, final AbstractFlow<T> iterator)
	{
		final List<T> recoveredElements = new ArrayList<>();
		IntStream.range(0, expectedElements.size()).forEach(i -> recoveredElements.add(iterator.next()));

		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	static <T> void assertAlternatingNextAndSkipCallsAsExpected(final List<T> expectedElements, final AbstractFlow<T> iterator)
	{
		final List<T> expectedOutcome = new ArrayList<>(), recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.size()).forEach(i ->
		{
			if (i % 2 == 0) {
				recoveredElements.add(iterator.next());
				expectedOutcome.add(expectedElements.get(i));
			}
			else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::next);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}
}
