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
		extends AbstractListBuilder implements FiniteIteratorTest
{
	protected abstract List<DoubleCase<I>> getDoubleTestCases();

	@Test
	public final void test()
	{
		List<DoubleCase<I>> testcases = getDoubleTestCases();

		for (DoubleCase<I> testcase : testcases) {
			List<AbstractTestIterable<I>> initialProviders = IteratorExampleProviders
					.buildDoubleIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
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
