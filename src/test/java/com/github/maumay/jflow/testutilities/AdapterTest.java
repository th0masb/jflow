/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * @author thomasb
 *
 */
public interface AdapterTest<T> extends ObjectIteratorTest
{
	List<Case<T>> getTestCases();

	default void test()
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

	default List<AbstractRichIterable<T>> adapt(Adapter<T> adapter,
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
