/**
 *
 */
package maumay.jflow.iterators.impl.appendtests;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedLongIterator;
import maumay.jflow.testutilities.AbstractIterableLongs;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedLongIteratorAppendTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		final AbstractIterableLongs small = getSmallLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] { 0, 1, 2, 3, 4, 10, 11 },
				createAppendIteratorProviderFrom(populated, small));
		assertLongIteratorAsExpected(new long[] { 0, 1, 2, 3, 4 },
				createAppendIteratorProviderFrom(populated, empty));

		assertLongIteratorAsExpected(new long[] { 10, 11 },
				createAppendIteratorProviderFrom(empty, small));
		assertLongIteratorAsExpected(new long[0],
				createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableLongs createAppendIteratorProviderFrom(
			final AbstractIterableLongs source, final AbstractIterableLongs toAppend)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractEnhancedLongIterator iter()
			{
				return source.iter().append(toAppend.iter());
			}
		};
	}
}
