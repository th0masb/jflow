/**
 *
 */
package xawd.jflow.iterators.inserttests;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.abstractiterables.AbstractIterableDoubles;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleFlowInsertTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		final AbstractIterableDoubles small = getSmallDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] {10, 11, 0, 1, 2, 3, 4}, createInsertIteratorProviderFrom(populated, small));
		assertDoubleIteratorAsExpected(new double[] {0, 1, 2, 3, 4}, createInsertIteratorProviderFrom(populated, empty));

		assertDoubleIteratorAsExpected(new double[] {10, 11}, createInsertIteratorProviderFrom(empty, small));
		assertDoubleIteratorAsExpected(new double[0], createInsertIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableDoubles createInsertIteratorProviderFrom(final AbstractIterableDoubles source, final AbstractIterableDoubles toInsert)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iterator() {
				return source.iterator().insert(toInsert.iterator());
			}
		};
	}
}
