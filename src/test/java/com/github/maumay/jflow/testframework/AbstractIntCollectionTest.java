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

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.IteratorOwnershipException;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIntCollectionTest<R>
{
	protected abstract Collector<R> getCollectorToTest();

	protected abstract List<Case<R>> getTestCases();

	protected abstract List<FailCase> getFailureCases();

	@Test
	public final void test()
	{
		Collector<R> collector = getCollectorToTest();
		for (Case<R> testCase : getTestCases()) {
			List<AbstractTestIterable<AbstractIntIterator>> iteratorProviders = IteratorProvider
					.buildIntIterables(testCase.source);
			for (AbstractTestIterable<AbstractIntIterator> provider : iteratorProviders) {
				AbstractIntIterator iterator = provider.iter();

				// Make sure we have the expected result.
				assertEquals(testCase.expectedResult, collector.apply(iterator));

				// Make sure ownership was taken away by the collector
				assertThrows(IteratorOwnershipException.class, iterator::nextInt);
				assertThrows(IteratorOwnershipException.class, iterator::skip);
				// Make sure the collector completely consumed the iterator
				assertFalse(iterator.hasNext());
				assertThrows(NoSuchElementException.class, iterator::nextIntImpl);
				assertThrows(NoSuchElementException.class, iterator::skipImpl);
			}
		}
	}

	@Test
	public final void testFailureCases()
	{
		Collector<R> collector = getCollectorToTest();
		for (FailCase testCase : getFailureCases()) {
			List<AbstractTestIterable<AbstractIntIterator>> iteratorProviders = IteratorProvider
					.buildIntIterables(testCase.source);
			for (AbstractTestIterable<AbstractIntIterator> provider : iteratorProviders) {
				AbstractIntIterator iterator = provider.iter();
				assertThrows(testCase.expectedFailType, () -> collector.apply(iterator));
			}
		}
	}

	@FunctionalInterface
	public static interface Collector<R> extends Function<AbstractIntIterator, R>
	{
	}

	public static class Case<R>
	{
		final List<Integer> source;
		final R expectedResult;

		protected Case(List<Integer> source, R expectedResult)
		{
			this.source = source;
			this.expectedResult = expectedResult;
		}
	}

	public static class FailCase
	{
		final List<Integer> source;
		final Class<? extends RuntimeException> expectedFailType;

		public FailCase(List<Integer> source,
				Class<? extends RuntimeException> expectedFailType)
		{
			this.source = source;
			this.expectedFailType = expectedFailType;
		}
	}
}
