/**
 *
 */
package xawd.jflow.testutilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.function.Executable;

import xawd.jflow.DoubleFlow;
import xawd.jflow.Flow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;

/**
 * Alternate between calling next and skip to test both
 * 
 * need to test next(), hasNext() -> next(), hasNext() -> skip(), skip()
 *
 */
public interface IteratorComparisonTest
{
	default <T> void assertIteratorAsExpected(final List<T> expected, final Flow<T> actual)
	{
		final int expectedElementCount = expected.size();

		assertTrue(expectedElementCount > 4, "Need an iterator with more elements to test behaviour properly.");
		final List<T> expectedElements = new ArrayList<>(), collectedElements = new ArrayList<>();
		int count = 0;

		// Test calling next() without a prior hasNext() call
		try {
			collectedElements.add(actual.next());
			expectedElements.add(expected.get(count++));
		}
		catch (final NoSuchElementException ex) {
			fail("exception thrown by calling next() without prior hasNext() call");
		}

		// Test calling skip
		try {
			assertTrue(actual.hasNext());
			actual.skip();
			count++;
		}
		catch (final NoSuchElementException ex) {
			fail("exception thrown by calling skip() with prior hasNext() call");
		}

		try {
			actual.skip();
			count++;
		}
		catch (final NoSuchElementException ex) {
			fail("exception thrown by calling skip() without prior hasNext() call");
		}

		for (; count < expected.size(); count++) {
			assertTrue(actual.hasNext());
			collectedElements.add(actual.next());
			expectedElements.add(expected.get(count));
		}

		assertFalse(actual.hasNext());
		final Executable attemptedNext = actual::next, attemptedSkip = actual::skip;
		assertThrows(NoSuchElementException.class, attemptedNext);
		assertThrows(NoSuchElementException.class, attemptedSkip);

		assertEquals(expectedElements, collectedElements);
	}

	default void assertIteratorAsExpected(final long[] expected, final LongFlow actual)
	{
		int count = 0;
		while (actual.hasNext()) {
			final long next = actual.nextLong();
			if (count >= expected.length) {
				fail("iterator longer than expected element list");
			}
			else {
				assertEquals(expected[count], next);
			}
			count++;
		}
		if (count != expected.length) {
			fail("iterator shorter than expected element list");
		}
	}

	default void assertIteratorAsExpected(final double[] expected, final DoubleFlow actual)
	{
		int count = 0;
		while (actual.hasNext()) {
			final double next = actual.nextDouble();
			if (count >= expected.length) {
				fail("iterator doubleer than expected element list");
			}
			else {
				assertEquals(expected[count], next, 0.001);
			}
			count++;
		}
		if (count != expected.length) {
			fail("iterator shorter than expected element list");
		}
	}

	default void assertIteratorAsExpected(final int[] expected, final IntFlow actual)
	{
		int count = 0;
		while (actual.hasNext()) {
			final int next = actual.nextInt();
			if (count >= expected.length) {
				fail("iterator inter than expected element list");
			}
			else {
				assertEquals(expected[count], next);
			}
			count++;
		}
		if (count != expected.length) {
			fail("iterator shorter than expected element list");
		}
	}
}
