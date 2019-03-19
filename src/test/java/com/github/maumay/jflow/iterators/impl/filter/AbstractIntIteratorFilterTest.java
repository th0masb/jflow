/**
 *
 */
package com.github.maumay.jflow.iterators.impl.filter;

import java.util.function.IntPredicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractIntIteratorFilterTest extends IteratorExampleProviders implements IteratorTest
{
	@Test
	void test()
	{
		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		IntPredicate allFilteredPredicate = x -> x < 0;
		assertIntIteratorAsExpected(new int[0],
				createFilterIteratorProviderFrom(populated, allFilteredPredicate));
		assertIntIteratorAsExpected(new int[0],
				createFilterIteratorProviderFrom(empty, allFilteredPredicate));

		IntPredicate someFilteredPredicate = x -> x < 3;
		assertIntIteratorAsExpected(new int[] { 0, 1, 2 },
				createFilterIteratorProviderFrom(populated, someFilteredPredicate));
		assertIntIteratorAsExpected(new int[0],
				createFilterIteratorProviderFrom(empty, someFilteredPredicate));

		IntPredicate noneFilteredPredicate = x -> x < 5;
		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4 },
				createFilterIteratorProviderFrom(populated, noneFilteredPredicate));
		assertIntIteratorAsExpected(new int[0],
				createFilterIteratorProviderFrom(empty, noneFilteredPredicate));
	}

	private AbstractIterableInts createFilterIteratorProviderFrom(AbstractIterableInts source,
			IntPredicate predicate)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return source.iter().filter(predicate);
			}
		};
	}
}
