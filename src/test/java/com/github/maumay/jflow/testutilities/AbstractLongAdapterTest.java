/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIterator;
import com.github.maumay.jflow.impl.AbstractLongIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractLongAdapterTest<I extends AbstractIterator>
		extends AbstractListBuilder implements FiniteIteratorTest
{
	protected abstract List<LongCase<I>> getLongTestCases();

	@Test
	public final void test()
	{
		List<LongCase<I>> testcases = getLongTestCases();

		for (LongCase<I> testcase : testcases) {
			List<AbstractTestIterable<I>> initialProviders = IteratorExampleProviders
					.buildLongIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@FunctionalInterface
	public static interface LongAdapter<I extends AbstractIterator>
			extends Function<AbstractLongIterator, I>
	{
	}

	public static class LongCase<I extends AbstractIterator>
	{
		final List<Long> source;
		final LongAdapter<I> adapter;
		final List<?> result;

		public LongCase(List<Long> source, LongAdapter<I> adapter, List<?> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
