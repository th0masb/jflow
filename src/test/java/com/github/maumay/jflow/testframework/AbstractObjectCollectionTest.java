/**
 * 
 */
package com.github.maumay.jflow.testframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * @author thomasb
 */
public abstract class AbstractObjectCollectionTest<E, R> extends AbstractListBuilder
{
	protected abstract Collector<E, R> getCollectorToTest();

	protected abstract List<Case<E, R>> getTestCases();

	protected abstract List<FailCase<E>> getFailureCases();

	@Test
	public final void testPassCases()
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
				assertFalse(iterator.hasOwnership());
			}
		}
	}

	@Test
	public final void testFailureCases()
	{
		Collector<E, R> collector = getCollectorToTest();
		for (FailCase<E> testCase : getFailureCases()) {
			List<AbstractTestIterable<AbstractRichIterator<E>>> iteratorProviders = IteratorProvider
					.buildIterables(testCase.source);
			for (AbstractTestIterable<AbstractRichIterator<E>> provider : iteratorProviders) {
				AbstractRichIterator<E> iterator = provider.iter();
				assertThrows(testCase.expectedFailType, () -> collector.apply(iterator));
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

		public Case(List<E> source, R expectedResult)
		{
			this.source = source;
			this.expectedResult = expectedResult;
		}
	}

	public static class FailCase<E>
	{
		final List<E> source;
		final Class<? extends RuntimeException> expectedFailType;

		public FailCase(List<E> source, Class<? extends RuntimeException> expectedFailType)
		{
			this.source = source;
			this.expectedFailType = expectedFailType;
		}
	}
}
