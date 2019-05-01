/**
 * 
 */
package com.github.maumay.jflow.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIterator;

/**
 * @author thomasb
 *
 */
public abstract class AbstractSourceTest<I extends AbstractIterator> extends AbstractListBuilder
		implements FiniteIteratorTest
{
	protected abstract List<Case<I>> getTestCases();

	@Test
	public final void test()
	{
		for (Case<I> testCase : getTestCases()) {
			// Check the source iterator is created with ownership.
			assertTrue(testCase.source.get().hasOwnership());

			TestIterable<I> iterable = new TestIterable<I>() {
				@Override
				public I iter()
				{
					return testCase.source.get();
				}
			};
			assertIteratorAsExpected(testCase.expected, list(iterable));
		}
	}

	public static class Case<I extends AbstractIterator>
	{
		final Supplier<I> source;
		final List<?> expected;

		public Case(Supplier<I> source, List<?> expected)
		{
			this.source = source;
			this.expected = expected;
		}
	}
}
