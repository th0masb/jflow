/**
 *
 */
package xawd.jflow.iterators.impl.inserttests;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntFlowInsertTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		final AbstractIterableInts small = getSmallIntTestIteratorProvider();

		assertIntIteratorAsExpected(new int[] {10, 11, 0, 1, 2, 3, 4}, createInsertIteratorProviderFrom(populated, small));
		assertIntIteratorAsExpected(new int[] {0, 1, 2, 3, 4}, createInsertIteratorProviderFrom(populated, empty));

		assertIntIteratorAsExpected(new int[] {10, 11}, createInsertIteratorProviderFrom(empty, small));
		assertIntIteratorAsExpected(new int[0], createInsertIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableInts createInsertIteratorProviderFrom(final AbstractIterableInts source, final AbstractIterableInts toInsert)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return source.iterator().insert(toInsert.iterator());
			}
		};
	}
}
