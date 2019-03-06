/**
 *
 */
package com.github.maumay.jflow.iterators.impl.inserttests;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorInsertTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		AbstractIterableDoubles small = getSmallDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] { 10, 11, 0, 1, 2, 3, 4 },
				createInsertIteratorProviderFrom(populated, small));
		assertDoubleIteratorAsExpected(new double[] { 0, 1, 2, 3, 4 },
				createInsertIteratorProviderFrom(populated, empty));

		assertDoubleIteratorAsExpected(new double[] { 10, 11 },
				createInsertIteratorProviderFrom(empty, small));
		assertDoubleIteratorAsExpected(new double[0],
				createInsertIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableDoubles createInsertIteratorProviderFrom(AbstractIterableDoubles source,
			AbstractIterableDoubles toInsert)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return source.iter().insert(toInsert.iter());
			}
		};
	}
}
