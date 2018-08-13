/**
 *
 */
package xawd.jflow.iterators.impl.inserttests;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongFlowInsertTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		final AbstractIterableLongs small = getSmallLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] {10, 11, 0, 1, 2, 3, 4}, createInsertIteratorProviderFrom(populated, small));
		assertLongIteratorAsExpected(new long[] {0, 1, 2, 3, 4}, createInsertIteratorProviderFrom(populated, empty));

		assertLongIteratorAsExpected(new long[] {10, 11}, createInsertIteratorProviderFrom(empty, small));
		assertLongIteratorAsExpected(new long[0], createInsertIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableLongs createInsertIteratorProviderFrom(final AbstractIterableLongs source, final AbstractIterableLongs toInsert)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iterator() {
				return source.iterator().insert(toInsert.iterator());
			}
		};
	}
}
