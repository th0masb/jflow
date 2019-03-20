/**
 * 
 */
package com.github.maumay.jflow.testframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.IteratorOwnershipException;

/**
 * @author thomasb
 */
public abstract class AbstractObjectCollectionTest<E, R>
{
	protected abstract Collector<E, R> getCollectorToTest();

	protected abstract List<Case<E, R>> getTestCases();

	@Test
	public final void test()
	{
		Collector<E, R> collector = getCollectorToTest();
		for (Case<E, R> testCase : getTestCases()) {
			List<AbstractTestIterable<AbstractRichIterator<E>>> iteratorProviders = IteratorProvider
					.buildIterables(testCase.source);
			for (AbstractTestIterable<AbstractRichIterator<E>> provider : iteratorProviders) {
				AbstractRichIterator<E> iterator = provider.iter();

				// Make sure we have the expected result.
				assertEquals(testCase.expectedResult, collector.apply(iterator));

				// Make sure ownership was taken away by the collector
				assertThrows(IteratorOwnershipException.class, iterator::next);
				assertThrows(IteratorOwnershipException.class, iterator::skip);
				// Make sure the collector completely consumed the iterator
				assertFalse(iterator.hasNext());
				assertThrows(NoSuchElementException.class, iterator::nextImpl);
				assertThrows(NoSuchElementException.class, iterator::skipImpl);
			}
		}
	}

	@FunctionalInterface
	public static interface Collector<E, R> extends Function<AbstractRichIterator<E>, R>
	{
	}

	public static class Case<E, R>
	{
		final List<E> source;
		final R expectedResult;

		protected Case(List<E> source, R expectedResult)
		{
			this.source = source;
			this.expectedResult = expectedResult;
		}
	}
}
