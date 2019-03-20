/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractDoubleAdapterTest<I extends AbstractIterator>
		extends AbstractAdapterTest implements FiniteIteratorTest
{
	protected abstract List<DoubleCase<I>> getTestCases();

	@Test
	public final void testIteratorBehaviour()
	{
		for (DoubleCase<I> testcase : getTestCases()) {
			List<AbstractTestIterable<I>> initialProviders = IteratorExampleProviders
					.buildDoubleIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@Test
	public final void testOwnershipBehaviour()
	{
		for (DoubleCase<I> testcase : getTestCases()) {
			List<AbstractTestIterable<AbstractDoubleIterator>> providers = IteratorExampleProviders
					.buildDoubleIterables(testcase.source);

			for (AbstractTestIterable<AbstractDoubleIterator> provider : providers) {
				assertAdaptionRemovesOwnership(provider.iter(), testcase.adapter);
			}
		}
	}

	@FunctionalInterface
	public static interface DoubleAdapter<I extends AbstractIterator>
			extends Function<AbstractDoubleIterator, I>
	{
	}

	public static class DoubleCase<I extends AbstractIterator>
	{
		final List<Double> source;
		final DoubleAdapter<I> adapter;
		final List<?> result;

		public DoubleCase(List<Double> source, DoubleAdapter<I> adapter, List<?> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
