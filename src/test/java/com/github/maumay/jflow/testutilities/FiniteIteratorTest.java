package com.github.maumay.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.AbstractIterator;
import com.github.maumay.jflow.impl.AbstractIteratorSize;
import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface FiniteIteratorTest
{
	default <I extends AbstractIterator> void assertIteratorAsExpected(List<?> expectedElements,
			List<AbstractTestIterable<I>> iteratorProviders)
	{
		for (AbstractTestIterable<I> iteratorProvider : iteratorProviders) {
			assertSizeDecreasesByNextAsExpected(iteratorProvider.iter());
			assertSizeDecreasesBySkipAsExpected(iteratorProvider.iter());
			assertSkippingAsExpected(expectedElements, iteratorProvider.iter());
			assertNextElementChecksAsExpected(expectedElements, iteratorProvider.iter());
			assertStandardIterationAsExpected(expectedElements, iteratorProvider.iter());
			assertUncheckedIterationAsExpected(expectedElements, iteratorProvider.iter());
			assertAlternatingNextAndSkipCallsAsExpected(expectedElements, iteratorProvider.iter());
		}
	}

	default Object next(AbstractIterator iterator)
	{
		if (iterator instanceof AbstractRichIterator<?>) {
			return ((AbstractRichIterator<?>) iterator).next();
		} else if (iterator instanceof AbstractIntIterator) {
			return ((AbstractIntIterator) iterator).nextInt();
		} else if (iterator instanceof AbstractDoubleIterator) {
			return ((AbstractDoubleIterator) iterator).nextDouble();
		} else if (iterator instanceof AbstractLongIterator) {
			return ((AbstractLongIterator) iterator).nextLong();
		} else {
			throw new AssertionError();
		}
	}

	default void assertSizeDecreasesByNextAsExpected(AbstractIterator iterator)
	{
		AbstractIteratorSize startSize = iterator.getSize().copy();

		int count = 0;
		while (iterator.hasNext()) {
			count++;
			try {
				next(iterator);
			} catch (NoSuchElementException ex) {
				fail("Fewer elements than expected.");
			}
			assertEquals(startSize.subtract(count), iterator.getSize());
		}
	}

	default void assertSizeDecreasesBySkipAsExpected(AbstractIterator iterator)
	{
		AbstractIteratorSize startSize = iterator.getSize().copy();

		int count = 0;
		while (iterator.hasNext()) {
			count++;
			try {
				iterator.skip();
			} catch (NoSuchElementException ex) {
				fail("Fewer elements than expected.");
			}
			assertEquals(startSize.subtract(count), iterator.getSize());
		}
	}

	default <T> void assertSkippingAsExpected(List<T> expectedElements, AbstractIterator iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> {
			try {
				iterator.skip();
			} catch (NoSuchElementException ex) {
				fail("Fewer elements than expected.");
			}
		});
		assertThrows(NoSuchElementException.class, iterator::skip);
	}

	default <T> void assertNextElementChecksAsExpected(List<T> expectedElements,
			AbstractIterator iterator)
	{
		IntStream.range(0, expectedElements.size()).forEach(i -> {
			assertTrue(iterator.hasNext());
			try {
				iterator.skip();
			} catch (NoSuchElementException ex) {
				fail("Fewer elements than expected.");
			}
		});
		assertFalse(iterator.hasNext());
	}

	default void assertStandardIterationAsExpected(List<?> expectedElements,
			AbstractIterator iterator)
	{
		List<Object> recoveredElements = new ArrayList<>();
		while (iterator.hasNext()) {
			try {
				recoveredElements.add(next(iterator));
			} catch (NoSuchElementException ex) {
				fail("Fewer elements than expected.");
			}
		}
		assertThrows(NoSuchElementException.class, () -> next(iterator));
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	default void assertUncheckedIterationAsExpected(List<?> expectedElements,
			AbstractIterator iterator)
	{
		List<Object> recoveredElements = new ArrayList<>();
		for (int i = 0; i < expectedElements.size(); i++) {
			try {
				recoveredElements.add(next(iterator));
			} catch (NoSuchElementException ex) {
				fail("Fewer elements than expected.");
			}
		}
		assertThrows(NoSuchElementException.class, () -> next(iterator));
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedElements, recoveredElements);
	}

	default void assertAlternatingNextAndSkipCallsAsExpected(List<?> expectedElements,
			AbstractIterator iterator)
	{
		List<Object> expectedOutcome = new ArrayList<>(), recoveredElements = new ArrayList<>();

		IntStream.range(0, expectedElements.size()).forEach(i -> {
			if (i % 2 == 0) {
				expectedOutcome.add(expectedElements.get(i));
				try {
					recoveredElements.add(next(iterator));
				} catch (NoSuchElementException ex) {
					fail("Fewer elements than expected.");
				}
			} else {
				iterator.skip();
			}
		});

		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, () -> next(iterator));
		assertThrows(NoSuchElementException.class, iterator::skip);
		assertEquals(expectedOutcome, recoveredElements);
	}
}
