/**
 *
 */
package com.github.maumay.jflow.iterators.impl.appendtests;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongIteratorAppendTest extends IteratorExampleProviders implements IteratorTest
{
	@Test
	void test()
	{
		AbstractIterableLongs populated = getLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		AbstractIterableLongs small = getSmallLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] { 0, 1, 2, 3, 4, 10, 11 },
				createAppendIteratorProviderFrom(populated, small));
		assertLongIteratorAsExpected(new long[] { 0, 1, 2, 3, 4 },
				createAppendIteratorProviderFrom(populated, empty));

		assertLongIteratorAsExpected(new long[] { 10, 11 },
				createAppendIteratorProviderFrom(empty, small));
		assertLongIteratorAsExpected(new long[0], createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableLongs createAppendIteratorProviderFrom(AbstractIterableLongs source,
			AbstractIterableLongs toAppend)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return source.iter().append(toAppend.iter());
			}
		};
	}
}
