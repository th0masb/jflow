/**
 *
 */
package xawd.jflow.iterators.impl.appendtests;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntFlowAppendTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		final AbstractIterableInts small = getSmallIntTestIteratorProvider();

		assertIntIteratorAsExpected(new int[] {0, 1, 2, 3, 4, 10, 11}, createAppendIteratorProviderFrom(populated, small));
		assertIntIteratorAsExpected(new int[] {0, 1, 2, 3, 4}, createAppendIteratorProviderFrom(populated, empty));

		assertIntIteratorAsExpected(new int[] {10, 11}, createAppendIteratorProviderFrom(empty, small));
		assertIntIteratorAsExpected(new int[0], createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableInts createAppendIteratorProviderFrom(final AbstractIterableInts source, final AbstractIterableInts toAppend)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return source.iterator().append(toAppend.iterator());
			}
		};
	}
}
