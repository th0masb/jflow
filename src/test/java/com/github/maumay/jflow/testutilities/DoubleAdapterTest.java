/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.UnaryOperator;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;

/**
 * @author thomasb
 *
 */
public interface DoubleAdapterTest extends DoubleIteratorTest
{
	List<DoubleCase> getDoubleTestCases();

	default void testDoubles()
	{
		List<DoubleCase> testcases = getDoubleTestCases();

		for (DoubleCase testcase : testcases) {
			List<AbstractIterableDoubles> initialProviders = IteratorExampleProviders
					.buildDoubleIterables(testcase.source, testcase.adapter);

			assertDoubleIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@FunctionalInterface
	static interface DoubleAdapter extends UnaryOperator<AbstractDoubleIterator>
	{
	}

	static class DoubleCase
	{
		final List<Double> source, result;
		final DoubleAdapter adapter;

		public DoubleCase(List<Double> source, DoubleAdapter adapter, List<Double> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
