/**
 * 
 */
package com.github.maumay.jflow.test;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.AbstractIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIntAdapterTest<I extends AbstractIterator> extends AbstractAdapterTest
		implements FiniteIteratorTest
{
	protected abstract List<Case<I>> getTestCases();

	@Test
	public final void testIterationBehaviour()
	{
		for (Case<I> testcase : getTestCases()) {
			List<AbstractTestIterable<I>> initialProviders = IteratorProvider
					.buildIntIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@Test
	public final void testOwnershipBehaviour()
	{
		for (Case<I> testcase : getTestCases()) {
			List<AbstractTestIterable<AbstractIntIterator>> providers = IteratorProvider
					.buildIntIterables(testcase.source);

			for (AbstractTestIterable<AbstractIntIterator> provider : providers) {
				assertAdaptionRemovesOwnership(provider.iter(), testcase.adapter);
			}
		}
	}

	protected final PrimitiveIterator.OfInt iter(List<Integer> src)
	{
		Iterator<Integer> boxed = src.iterator();
		return new PrimitiveIterator.OfInt() {
			@Override
			public boolean hasNext()
			{
				return boxed.hasNext();
			}

			@Override
			public int nextInt()
			{
				return boxed.next();
			}
		};
	}

	@FunctionalInterface
	public static interface Adapter<I extends AbstractIterator>
			extends Function<AbstractIntIterator, I>
	{
	}

	public static class Case<I extends AbstractIterator>
	{
		final List<Integer> source;
		final Adapter<I> adapter;
		final List<?> result;

		public Case(List<Integer> source, Adapter<I> adapter, List<?> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
