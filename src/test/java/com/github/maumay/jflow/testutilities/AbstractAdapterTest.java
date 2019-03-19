/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIterator;
import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractAdapterTest<T, I extends AbstractIterator>
		implements FiniteIteratorTest, ListBuilder
{

	protected abstract List<Case<T, I>> getTestCases();

	@Test
	public final void test()
	{
		List<Case<T, I>> testcases = getTestCases();

		for (Case<T, I> testcase : testcases) {
			List<AbstractTestIterable<I>> providers = IteratorExampleProviders
					.buildIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, providers);
		}
	}

	@FunctionalInterface
	public static interface Adapter<T, I extends AbstractIterator>
			extends Function<AbstractRichIterator<T>, I>
	{
	}

	public static class Case<T, I extends AbstractIterator>
	{
		final List<T> source;
		final Adapter<T, I> adapter;
		final List<?> result;

		public Case(List<T> source, Adapter<T, I> adapter, List<?> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
