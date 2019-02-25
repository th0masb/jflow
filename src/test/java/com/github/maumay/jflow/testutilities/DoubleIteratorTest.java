package com.github.maumay.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface DoubleIteratorTest
{
	default void assertDoubleIteratorAsExpected(final double[] expectedElements,
			final AbstractIterableDoubles iteratorProvider)
	{
		assertSizeAsExpected(expectedElements, iteratorProvider.iter());
		assertSkippingAsExpected(expectedElements, iteratorProvider.iter());
		assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iter());
		assertStandardIterationAsExpected(expectedElements, iteratorProvider.iter());
		assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iter());
		assertAlternatingNextAndSkipCallsAsExpected(expectedElements,
				iteratorProvider.iter());
	}

	static void assertSizeAsExpected(double[] expectedElements,
			AbstractDoubleIterator iterator)
	{
		if (iterator.sizeIsKnown()) {
			assertEquals(expectedElements.length, iterator.size().getAsInt());
		}
	}

	static void assertSkippingAsExpected(final double[] expectedElements,
			final AbstractDoubleIterator iterator)
	{
		IntStream.range(0, expectedElements.length).forEach(i -> iterator.skip());
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	static void assertNextElementChecksAsExpected(final double[] expectedElements,
			final AbstractDoubleIterator iterator)
	{
		IntStream.range(0, expectedElements.length).forEach(i -> {
			assertTrue(iterator.hasNext());
			iterator.skip();
		});
		assertFalse(iterator.hasNext());
	}

	static void assertStandardIterationAsExpected(final double[] expectedElements,
			final AbstractDoubleIterator iterator)
	{
		final List<Double> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			recoveredElements.add(iterator.nextDouble());
		}
		assertThrows(NoSuchElementException.class, iterator::nextDouble);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertArrayEquals(expectedElements, convertFromBoxed(recoveredElements));
	}

	static void assertUncheckedIterationAsExpected(final double[] expectedElements,
			final AbstractDoubleIterator iterator)
	{
		final List<Double> recoveredElements = new ArrayList<>();
		IntStream.range(0, expectedElements.length)
				.forEach(i -> recoveredElements.add(iterator.nextDouble()));

		assertThrows(NoSuchElementException.class, iterator::nextDouble);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertArrayEquals(expectedElements, convertFromBoxed(recoveredElements));
	}

	static void assertAlternatingNextAndSkipCallsAsExpected(
			final double[] expectedElements,
			final AbstractDoubleIterator iterator)
	{
		final List<Double> expectedOutcome = new ArrayList<>(),
				recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.length).forEach(i -> {
			if (i % 2 == 0) {
				recoveredElements.add(iterator.nextDouble());
				expectedOutcome.add(expectedElements[i]);
			} else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::nextDouble);
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}

	static double[] convertFromBoxed(final List<Double> boxedDoubles)
	{
		return boxedDoubles.stream().mapToDouble(i -> i).toArray();
	}
}
