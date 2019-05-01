/**
 * 
 */
package com.github.maumay.jflow.test;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractDoubleAdapterTest<I extends AbstractIterator>
		extends AbstractAdapterTest implements FiniteIteratorTest
{
	protected abstract List<Case<I>> getTestCases();

	@Test
	public final void testIteratorBehaviour()
	{
		for (Case<I> testcase : getTestCases()) {
			List<TestIterable<I>> initialProviders = IteratorProvider
					.buildDoubleIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@Test
	public final void testOwnershipBehaviour()
	{
		for (Case<I> testcase : getTestCases()) {
			List<TestIterable<AbstractDoubleIterator>> providers = IteratorProvider
					.buildDoubleIterables(testcase.source);

			for (TestIterable<AbstractDoubleIterator> provider : providers) {
				assertAdaptionRemovesOwnership(provider.iter(), testcase.adapter);
			}
		}
	}

	protected final PrimitiveIterator.OfDouble iter(List<Double> src)
	{
		Iterator<Double> boxed = src.iterator();
		return new PrimitiveIterator.OfDouble() {
			@Override
			public boolean hasNext()
			{
				return boxed.hasNext();
			}

			@Override
			public double nextDouble()
			{
				return boxed.next();
			}
		};
	}

	@FunctionalInterface
	public static interface Adapter<I extends AbstractIterator>
			extends Function<AbstractDoubleIterator, I>
	{
	}

	public static class Case<I extends AbstractIterator>
	{
		final List<Double> source;
		final Adapter<I> adapter;
		final List<?> result;

		public Case(List<Double> source, Adapter<I> adapter, List<?> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
