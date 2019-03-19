/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.UnaryOperator;

import com.github.maumay.jflow.impl.AbstractLongIterator;

/**
 * @author thomasb
 *
 */
public interface LongAdapterTest extends LongIteratorTest
{
	List<LongCase> getLongTestCases();

	default void testLongs()
	{
		List<LongCase> testcases = getLongTestCases();

		for (LongCase testcase : testcases) {
			List<AbstractIterableLongs> initialProviders = IteratorExampleProviders
					.buildLongIterables(testcase.source, testcase.adapter);

			assertLongIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@FunctionalInterface
	static interface LongAdapter extends UnaryOperator<AbstractLongIterator>
	{
	}

	static class LongCase
	{
		final List<Long> source, result;
		final LongAdapter adapter;

		public LongCase(List<Long> source, LongAdapter adapter, List<Long> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
