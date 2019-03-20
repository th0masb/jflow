/**
 * 
 */
package com.github.maumay.jflow.testframework;

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
	protected abstract List<DoubleCase<I>> getTestCases();

	@Test
	public final void testIteratorBehaviour()
	{
		for (DoubleCase<I> testcase : getTestCases()) {
			List<AbstractTestIterable<I>> initialProviders = IteratorProvider
					.buildDoubleIterables(testcase.source, testcase.adapter);

			assertIteratorAsExpected(testcase.result, initialProviders);
		}
	}

	@Test
	public final void testOwnershipBehaviour()
	{
		for (DoubleCase<I> testcase : getTestCases()) {
			List<AbstractTestIterable<AbstractDoubleIterator>> providers = IteratorProvider
					.buildDoubleIterables(testcase.source);

			for (AbstractTestIterable<AbstractDoubleIterator> provider : providers) {
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
	public static interface DoubleAdapter<I extends AbstractIterator>
			extends Function<AbstractDoubleIterator, I>
	{
	}

	public static class DoubleCase<I extends AbstractIterator>
	{
		final List<Double> source;
		final DoubleAdapter<I> adapter;
		final List<?> result;

		public DoubleCase(List<Double> source, DoubleAdapter<I> adapter, List<?> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}
}
