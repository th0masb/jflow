/**
 *
 */
package xawd.jflow.iterators.impl.filter;

import java.util.function.IntPredicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractIntFlowFilterTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		final IntPredicate allFilteredPredicate = x -> x < 0;
		assertIntIteratorAsExpected(new int[0], createFilterIteratorProviderFrom(populated, allFilteredPredicate));
		assertIntIteratorAsExpected(new int[0], createFilterIteratorProviderFrom(empty, allFilteredPredicate));

		final IntPredicate someFilteredPredicate = x -> x < 3;
		assertIntIteratorAsExpected(new int[] {0, 1, 2}, createFilterIteratorProviderFrom(populated, someFilteredPredicate));
		assertIntIteratorAsExpected(new int[0], createFilterIteratorProviderFrom(empty, someFilteredPredicate));

		final IntPredicate noneFilteredPredicate = x -> x < 5;
		assertIntIteratorAsExpected(new int[] {0, 1, 2, 3, 4}, createFilterIteratorProviderFrom(populated, noneFilteredPredicate));
		assertIntIteratorAsExpected(new int[0], createFilterIteratorProviderFrom(empty, noneFilteredPredicate));
	}

	private AbstractIterableInts createFilterIteratorProviderFrom(final AbstractIterableInts source, final IntPredicate predicate)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return source.iterator().filter(predicate);
			}
		};
	}
}
