/**
 * 
 */
package com.github.maumay.jflow.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractLongIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractLongCollectionTest<R> extends AbstractListBuilder
{
	protected abstract Collector<R> getCollectorToTest();

	protected abstract List<Case<R>> getTestCases();

	protected abstract List<FailCase> getFailureCases();

	@Test
	public final void test()
	{
		Collector<R> collector = getCollectorToTest();
		for (Case<R> testCase : getTestCases()) {
			List<TestIterable<AbstractLongIterator>> iteratorProviders = IteratorProvider
					.buildLongIterables(testCase.source);
			for (TestIterable<AbstractLongIterator> provider : iteratorProviders) {
				AbstractLongIterator iterator = provider.iter();

				// Make sure we have the expected result.
				assertEquals(testCase.expectedResult, collector.apply(iterator));

				// Make sure ownership was taken away by the collector
				assertFalse(iterator.hasOwnership());
			}
		}
	}

	@Test
	public final void testFailureCases()
	{
		Collector<R> collector = getCollectorToTest();
		for (FailCase testCase : getFailureCases()) {
			List<TestIterable<AbstractLongIterator>> iteratorProviders = IteratorProvider
					.buildLongIterables(testCase.source);
			for (TestIterable<AbstractLongIterator> provider : iteratorProviders) {
				AbstractLongIterator iterator = provider.iter();
				assertThrows(testCase.expectedFailType, () -> collector.apply(iterator));
			}
		}
	}

	@FunctionalInterface
	public static interface Collector<R> extends Function<AbstractLongIterator, R>
	{
	}

	public static class Case<R>
	{
		final List<Long> source;
		final R expectedResult;

		public Case(List<Long> source, R expectedResult)
		{
			this.source = source;
			this.expectedResult = expectedResult;
		}
	}

	public static class FailCase
	{
		final List<Long> source;
		final Class<? extends RuntimeException> expectedFailType;

		public FailCase(List<Long> source, Class<? extends RuntimeException> expectedFailType)
		{
			this.source = source;
			this.expectedFailType = expectedFailType;
		}
	}
}
