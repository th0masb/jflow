/**
 * 
 */
package com.github.maumay.jflow.testframework;

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
	protected abstract List<LongCase<I>> getTestCases();

	@Test
	public final void testIterationBehaviour()
	{
		for (LongCase<I> testcase : getTestCases()) {
			List<AbstractTestIterable<I>> initialProviders = IteratorProvider
					.buildLongIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@Test
	public final void testOwnershipBehaviour()
	{
		for (LongCase<I> testcase : getTestCases()) {
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
