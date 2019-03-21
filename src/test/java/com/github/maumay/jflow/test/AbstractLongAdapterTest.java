/**
 * 
 */
package com.github.maumay.jflow.test;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIterator;
import com.github.maumay.jflow.impl.AbstractLongIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractLongAdapterTest<I extends AbstractIterator>
		extends AbstractAdapterTest implements FiniteIteratorTest
{
	protected abstract List<Case<I>> getTestCases();

	@Test
	public final void testIterationBehaviour()
	{
		for (Case<I> testcase : getTestCases()) {
			List<AbstractTestIterable<I>> initialProviders = IteratorProvider
					.buildLongIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@Test
	public final void testOwnershipBehaviour()
	{
		for (Case<I> testcase : getTestCases()) {
			List<AbstractTestIterable<AbstractLongIterator>> providers = IteratorProvider
					.buildLongIterables(testcase.source);

			for (AbstractTestIterable<AbstractLongIterator> provider : providers) {
				assertAdaptionRemovesOwnership(provider.iter(), testcase.adapter);
			}
		}
	}

	protected final PrimitiveIterator.OfLong iter(List<Long> src)
	{
		Iterator<Long> boxed = src.iterator();
		return new PrimitiveIterator.OfLong() {
			@Override
			public boolean hasNext()
			{
				return boxed.hasNext();
			}

			@Override
			public long nextLong()
			{
				return boxed.next();
			}
		};
	}

	@FunctionalInterface
	public static interface Adapter<I extends AbstractIterator>
			extends Function<AbstractLongIterator, I>
	{
	}

	public static class Case<I extends AbstractIterator>
	{
		final List<Long> source;
		final Adapter<I> adapter;
		final List<?> result;

		public Case(List<Long> source, Adapter<I> adapter, List<?> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
