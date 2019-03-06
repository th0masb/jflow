/**
 *
 */
package com.github.maumay.jflow.iterators.impl.filter;

import java.util.function.DoublePredicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorFilterTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		DoublePredicate allFilteredPredicate = x -> x < 0;
		assertDoubleIteratorAsExpected(new double[0],
				createFilterIteratorProviderFrom(populated, allFilteredPredicate));
		assertDoubleIteratorAsExpected(new double[0],
				createFilterIteratorProviderFrom(empty, allFilteredPredicate));

		DoublePredicate someFilteredPredicate = x -> x < 3;
		assertDoubleIteratorAsExpected(new double[] { 0, 1, 2 },
				createFilterIteratorProviderFrom(populated, someFilteredPredicate));
		assertDoubleIteratorAsExpected(new double[0],
				createFilterIteratorProviderFrom(empty, someFilteredPredicate));

		DoublePredicate noneFilteredPredicate = x -> x < 5;
		assertDoubleIteratorAsExpected(new double[] { 0, 1, 2, 3, 4 },
				createFilterIteratorProviderFrom(populated, noneFilteredPredicate));
		assertDoubleIteratorAsExpected(new double[0],
				createFilterIteratorProviderFrom(empty, noneFilteredPredicate));
	}

	private AbstractIterableDoubles createFilterIteratorProviderFrom(AbstractIterableDoubles source,
			DoublePredicate predicate)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return source.iter().filter(predicate);
			}
		};
	}
}
