package xawd.jflow.iterators.impl.combinetests;

import java.util.function.IntBinaryOperator;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntFlowCombineTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableInts small = getSmallIntTestIteratorProvider();
		final AbstractIterableInts mid = getIntTestIteratorProvider();
		final AbstractIterableInts large = getLargeIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		assertIntIteratorAsExpected(new int[] {10, 12}, createCombineIteratorProviderFrom(mid, small, (a, b) -> a + b));
		assertIntIteratorAsExpected(new int[] {0, 2, 4, 6, 8}, createCombineIteratorProviderFrom(mid, mid, (a, b) -> a + b));
		assertIntIteratorAsExpected(new int[] {10, 12, 14, 16, 18}, createCombineIteratorProviderFrom(mid, large, (a, b) -> a + b));

		assertIntIteratorAsExpected(new int[0], createCombineIteratorProviderFrom(mid, empty, (a, b) -> a + b));
		assertIntIteratorAsExpected(new int[0], createCombineIteratorProviderFrom(empty, empty, (a, b) -> a + b));
		assertIntIteratorAsExpected(new int[0], createCombineIteratorProviderFrom(empty, mid, (a, b) -> a + b));
	}

	private AbstractIterableInts createCombineIteratorProviderFrom(final AbstractIterableInts first, final AbstractIterableInts second, final IntBinaryOperator combiner)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return first.iterator().combineWith(second.iterator(), combiner);
			}
		};
	}
}
