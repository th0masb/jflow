/**
 *
 */
package xawd.jflow.iterators.impl.appendtests;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongFlowAppendTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		final AbstractIterableLongs small = getSmallLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] {0, 1, 2, 3, 4, 10, 11}, createAppendIteratorProviderFrom(populated, small));
		assertLongIteratorAsExpected(new long[] {0, 1, 2, 3, 4}, createAppendIteratorProviderFrom(populated, empty));

		assertLongIteratorAsExpected(new long[] {10, 11}, createAppendIteratorProviderFrom(empty, small));
		assertLongIteratorAsExpected(new long[0], createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableLongs createAppendIteratorProviderFrom(final AbstractIterableLongs source, final AbstractIterableLongs toAppend)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iterator() {
				return source.iterator().append(toAppend.iterator());
			}
		};
	}
}
