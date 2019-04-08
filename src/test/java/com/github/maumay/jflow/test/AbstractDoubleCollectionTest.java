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

import com.github.maumay.jflow.impl.AbstractDoubleIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractDoubleCollectionTest<R> extends AbstractListBuilder
{
	protected abstract Collector<R> getCollectorToTest();

	protected abstract List<Case<R>> getTestCases();

	protected abstract List<FailCase> getFailureCases();

	@Test
	public final void test()
	{
		Collector<R> collector = getCollectorToTest();
		for (Case<R> testCase : getTestCases()) {
			List<TestIterable<AbstractDoubleIterator>> iteratorProviders = IteratorProvider
					.buildDoubleIterables(testCase.source);
			for (TestIterable<AbstractDoubleIterator> provider : iteratorProviders) {
				AbstractDoubleIterator iterator = provider.iter();

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
			List<TestIterable<AbstractDoubleIterator>> iteratorProviders = IteratorProvider
					.buildDoubleIterables(testCase.source);
			for (TestIterable<AbstractDoubleIterator> provider : iteratorProviders) {
				AbstractDoubleIterator iterator = provider.iter();
				assertThrows(testCase.expectedFailType, () -> collector.apply(iterator));
			}
		}
	}

	@FunctionalInterface
	public static interface Collector<R> extends Function<AbstractDoubleIterator, R>
	{
	}

	public static class Case<R>
	{
		final List<Double> source;
		final R expectedResult;

		public Case(List<Double> source, R expectedResult)
		{
			this.source = source;
			this.expectedResult = expectedResult;
		}
	}

	public static class FailCase
	{
		final List<Double> source;
		final Class<? extends RuntimeException> expectedFailType;

		public FailCase(List<Double> source, Class<? extends RuntimeException> expectedFailType)
		{
			this.source = source;
			this.expectedFailType = expectedFailType;
		}
	}
}
