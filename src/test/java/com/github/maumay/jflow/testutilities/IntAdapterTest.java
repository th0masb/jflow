/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.AbstractIterator;

/**
 * @author thomasb
 *
 */
public abstract class IntAdapterTest<I extends AbstractIterator>
		implements FiniteIteratorTest, ListBuilder
{
	protected abstract List<IntCase<I>> getIntTestCases();

	@Test
	public final void test()
	{
		List<IntCase<I>> testcases = getIntTestCases();

		for (IntCase<I> testcase : testcases) {
			List<AbstractTestIterable<I>> initialProviders = IteratorExampleProviders
					.buildIntIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@FunctionalInterface
	public static interface IntAdapter<I extends AbstractIterator>
			extends Function<AbstractIntIterator, I>
	{
	}

	public static class IntCase<I extends AbstractIterator>
	{
		final List<Integer> source;
		final IntAdapter<I> adapter;
		final List<?> result;

		public IntCase(List<Integer> source, IntAdapter<I> adapter, List<?> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
