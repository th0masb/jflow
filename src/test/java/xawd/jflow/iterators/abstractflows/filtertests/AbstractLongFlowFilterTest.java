package xawd.jflow.iterators.abstractflows.filtertests;

import java.util.function.LongPredicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.abstractiterables.AbstractIterableLongs;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;
import xawd.jflow.iterators.testutilities.IteratorTest;

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
			public AbstractLongFlow iter() {
				return source.iter().filter(predicate);
			}
		};
	}
}
