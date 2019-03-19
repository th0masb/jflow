/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.UnaryOperator;

import com.github.maumay.jflow.impl.AbstractIntIterator;

/**
 * @author thomasb
 *
 */
public interface IntAdapterTest extends IntIteratorTest
{
	List<IntCase> getIntTestCases();

	default void testInts()
	{
		List<IntCase> testcases = getIntTestCases();

		for (IntCase testcase : testcases) {
			List<AbstractIterableInts> initialProviders = IteratorExampleProviders
					.buildIntIterables(testcase.source, testcase.adapter);

			assertIntIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@FunctionalInterface
	static interface IntAdapter extends UnaryOperator<AbstractIntIterator>
	{
	}

	static class IntCase
	{
		final List<Integer> source, result;
		final IntAdapter adapter;

		public IntCase(List<Integer> source, IntAdapter adapter, List<Integer> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
