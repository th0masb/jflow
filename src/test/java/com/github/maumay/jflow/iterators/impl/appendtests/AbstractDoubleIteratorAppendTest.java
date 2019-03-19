/**
 *
 */
package com.github.maumay.jflow.iterators.impl.appendtests;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorAppendTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	@Test
	void test()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		AbstractIterableDoubles small = getSmallDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] { 0, 1, 2, 3, 4, 10, 11 },
				createAppendIteratorProviderFrom(populated, small));
		assertDoubleIteratorAsExpected(new double[] { 0, 1, 2, 3, 4 },
				createAppendIteratorProviderFrom(populated, empty));

		assertDoubleIteratorAsExpected(new double[] { 10, 11 },
				createAppendIteratorProviderFrom(empty, small));
		assertDoubleIteratorAsExpected(new double[0],
				createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableDoubles createAppendIteratorProviderFrom(AbstractIterableDoubles source,
			AbstractIterableDoubles toAppend)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return source.iter().append(toAppend.iter());
			}
		};
	}
}
