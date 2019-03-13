package com.github.maumay.jflow.iterators.impl.filter;

import java.util.function.LongPredicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl2.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongIteratorFilterTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractIterableLongs populated = getLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		LongPredicate allFilteredPredicate = x -> x < 0;
		assertLongIteratorAsExpected(new long[0],
				createFilterIteratorProviderFrom(populated, allFilteredPredicate));
		assertLongIteratorAsExpected(new long[0],
				createFilterIteratorProviderFrom(empty, allFilteredPredicate));

		LongPredicate someFilteredPredicate = x -> x < 3;
		assertLongIteratorAsExpected(new long[] { 0, 1, 2 },
				createFilterIteratorProviderFrom(populated, someFilteredPredicate));
		assertLongIteratorAsExpected(new long[0],
				createFilterIteratorProviderFrom(empty, someFilteredPredicate));

		LongPredicate noneFilteredPredicate = x -> x < 5;
		assertLongIteratorAsExpected(new long[] { 0, 1, 2, 3, 4 },
				createFilterIteratorProviderFrom(populated, noneFilteredPredicate));
		assertLongIteratorAsExpected(new long[0],
				createFilterIteratorProviderFrom(empty, noneFilteredPredicate));
	}

	private AbstractIterableLongs createFilterIteratorProviderFrom(AbstractIterableLongs source,
			LongPredicate predicate)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return source.iter().filter(predicate);
			}
		};
	}
}
