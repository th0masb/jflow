/**
 *
 */
package com.github.maumay.jflow.iterators.impl.appendtests;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl2.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntIteratorAppendTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		AbstractIterableInts small = getSmallIntTestIteratorProvider();

		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4, 10, 11 },
				createAppendIteratorProviderFrom(populated, small));
		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4 },
				createAppendIteratorProviderFrom(populated, empty));

		assertIntIteratorAsExpected(new int[] { 10, 11 },
				createAppendIteratorProviderFrom(empty, small));
		assertIntIteratorAsExpected(new int[0], createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableInts createAppendIteratorProviderFrom(AbstractIterableInts source,
			AbstractIterableInts toAppend)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return source.iter().append(toAppend.iter());
			}
		};
	}
}
