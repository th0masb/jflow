/**
 *
 */
package xawd.jflow.iterators.impl.appendtests;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleFlowAppendTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		final AbstractIterableDoubles small = getSmallDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] {0, 1, 2, 3, 4, 10, 11}, createAppendIteratorProviderFrom(populated, small));
		assertDoubleIteratorAsExpected(new double[] {0, 1, 2, 3, 4}, createAppendIteratorProviderFrom(populated, empty));

		assertDoubleIteratorAsExpected(new double[] {10, 11}, createAppendIteratorProviderFrom(empty, small));
		assertDoubleIteratorAsExpected(new double[0], createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableDoubles createAppendIteratorProviderFrom(final AbstractIterableDoubles source, final AbstractIterableDoubles toAppend)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iterator() {
				return source.iterator().append(toAppend.iterator());
			}
		};
	}
}
