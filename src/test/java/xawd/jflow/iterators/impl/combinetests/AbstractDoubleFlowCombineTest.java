package xawd.jflow.iterators.impl.combinetests;

import java.util.function.DoubleBinaryOperator;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleFlowCombineTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableDoubles small = getSmallDoubleTestIteratorProvider();
		final AbstractIterableDoubles mid = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles large = getLargeDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] {10, 12}, createCombineIteratorProviderFrom(mid, small, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[] {0, 2, 4, 6, 8}, createCombineIteratorProviderFrom(mid, mid, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[] {10, 12, 14, 16, 18}, createCombineIteratorProviderFrom(mid, large, (a, b) -> a + b));

		assertDoubleIteratorAsExpected(new double[0], createCombineIteratorProviderFrom(mid, empty, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[0], createCombineIteratorProviderFrom(empty, empty, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[0], createCombineIteratorProviderFrom(empty, mid, (a, b) -> a + b));
	}

	private AbstractIterableDoubles createCombineIteratorProviderFrom(final AbstractIterableDoubles first, final AbstractIterableDoubles second, final DoubleBinaryOperator combiner)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iterator() {
				return first.iterator().combineWith(second.iterator(), combiner);
			}
		};
	}
}
