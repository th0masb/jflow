/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.UnaryOperator;

import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * @author thomasb
 *
 */
public interface AdapterTest<T> extends ObjectIteratorTest, ListBuilder
{
	List<Case<T>> getTestCases();

	default void test()
	{
		List<Case<T>> testcases = getTestCases();

		for (Case<T> testcase : testcases) {
			List<AbstractRichIterable<T>> providers = IteratorExampleProviders
					.buildIterables(testcase.source, testcase.adapter);

			assertObjectIteratorAsExpected(testcase.result, providers);
		}
	}

	@FunctionalInterface
	static interface Adapter<T> extends UnaryOperator<AbstractRichIterator<T>>
	{
	}

	static class Case<T>
	{
		final List<T> source, result;
		final Adapter<T> adapter;

		public Case(List<T> source, Adapter<T> adapter, List<T> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
