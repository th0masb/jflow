/**
 *
 */
package com.github.maumay.jflow.iterators.impl.inserttests;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongIteratorInsertTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	@Test
	void test()
	{
		AbstractIterableLongs populated = getLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		AbstractIterableLongs small = getSmallLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] { 10, 11, 0, 1, 2, 3, 4 },
				createInsertIteratorProviderFrom(populated, small));
		assertLongIteratorAsExpected(new long[] { 0, 1, 2, 3, 4 },
				createInsertIteratorProviderFrom(populated, empty));

		assertLongIteratorAsExpected(new long[] { 10, 11 },
				createInsertIteratorProviderFrom(empty, small));
		assertLongIteratorAsExpected(new long[0], createInsertIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableLongs createInsertIteratorProviderFrom(AbstractIterableLongs source,
			AbstractIterableLongs toInsert)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return source.iter().insert(toInsert.iter());
			}
		};
	}
}
