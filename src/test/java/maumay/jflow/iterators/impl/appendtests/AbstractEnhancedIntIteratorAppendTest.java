/**
 *
 */
package maumay.jflow.iterators.impl.appendtests;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.testutilities.AbstractIterableInts;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIntIteratorAppendTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		final AbstractIterableInts small = getSmallIntTestIteratorProvider();

		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4, 10, 11 },
				createAppendIteratorProviderFrom(populated, small));
		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4 },
				createAppendIteratorProviderFrom(populated, empty));

		assertIntIteratorAsExpected(new int[] { 10, 11 },
				createAppendIteratorProviderFrom(empty, small));
		assertIntIteratorAsExpected(new int[0],
				createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableInts createAppendIteratorProviderFrom(
			final AbstractIterableInts source, final AbstractIterableInts toAppend)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return source.iter().append(toAppend.iter());
			}
		};
	}
}
