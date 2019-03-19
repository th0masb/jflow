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
public abstract class AbstractIntAdapterTest<I extends AbstractIterator> extends AbstractListBuilder
		implements FiniteIteratorTest
{
	protected abstract List<IntCase<I>> getTestCases();

	@Test
	public final void test()
	{
		for (IntCase<I> testcase : getTestCases()) {
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
