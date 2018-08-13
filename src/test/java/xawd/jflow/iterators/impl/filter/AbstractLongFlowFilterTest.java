package xawd.jflow.iterators.impl.filter;

import java.util.function.LongPredicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongFlowFilterTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		final LongPredicate allFilteredPredicate = x -> x < 0;
		assertLongIteratorAsExpected(new long[0], createFilterIteratorProviderFrom(populated, allFilteredPredicate));
		assertLongIteratorAsExpected(new long[0], createFilterIteratorProviderFrom(empty, allFilteredPredicate));

		final LongPredicate someFilteredPredicate = x -> x < 3;
		assertLongIteratorAsExpected(new long[] {0, 1, 2}, createFilterIteratorProviderFrom(populated, someFilteredPredicate));
		assertLongIteratorAsExpected(new long[0], createFilterIteratorProviderFrom(empty, someFilteredPredicate));

		final LongPredicate noneFilteredPredicate = x -> x < 5;
		assertLongIteratorAsExpected(new long[] {0, 1, 2, 3, 4}, createFilterIteratorProviderFrom(populated, noneFilteredPredicate));
		assertLongIteratorAsExpected(new long[0], createFilterIteratorProviderFrom(empty, noneFilteredPredicate));
	}

	private AbstractIterableLongs createFilterIteratorProviderFrom(final AbstractIterableLongs source, final LongPredicate predicate)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iterator() {
				return source.iterator().filter(predicate);
			}
		};
	}
}
