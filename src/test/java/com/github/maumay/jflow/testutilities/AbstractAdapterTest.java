/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractAdapterTest<T> implements ObjectIteratorTest
{
	@Test
	void test()
	{
		List<Case<T>> testcases = getTestCases();

		for (Case<T> testcase : testcases) {
			List<AbstractRichIterable<T>> initialProviders = IteratorExampleProviders
					.buildIterables(testcase.source);

			List<AbstractRichIterable<T>> adaptedProviders = adapt(testcase.adapter,
					initialProviders);

			assertObjectIteratorAsExpected(testcase.result, adaptedProviders);
		}
	}

	private List<AbstractRichIterable<T>> adapt(Adapter<T> adapter,
			List<AbstractRichIterable<T>> source)
	{
		List<AbstractRichIterable<T>> dest = new ArrayList<>(source.size());
		for (AbstractRichIterable<T> element : source) {
			dest.add(new AbstractRichIterable<T>() {
				@Override
				public AbstractRichIterator<T> iter()
				{
					return adapter.apply(element.iter());
				}
			});
		}
		return dest;
	}

	protected abstract List<Case<T>> getTestCases();

	@SafeVarargs
	protected final <E> List<E> list(E... ts)
	{
		return Arrays.asList(ts);
	}

	@FunctionalInterface
	public static interface Adapter<T> extends UnaryOperator<AbstractRichIterator<T>>
	{
	}

	public static class Case<T>
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
