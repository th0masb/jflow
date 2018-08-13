/**
 *
 */
package xawd.jflow.iterators.impl.scan;

import java.util.function.LongBinaryOperator;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongFlowAccumulationTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testAccumulationWithoutId()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] {0, 1, 3, 6, 10}, createAccumulationWithoutIdIteratorProviderFrom(populated, (a, b) -> a + b));
		assertLongIteratorAsExpected(new long[] {}, createAccumulationWithoutIdIteratorProviderFrom(empty, (a, b) -> a + b));
	}

	private AbstractIterableLongs createAccumulationWithoutIdIteratorProviderFrom(final AbstractIterableLongs source, final LongBinaryOperator accumulator)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iterator() {
				return source.iterator().accumulate(accumulator);
			}
		};
	}

	@Test
	void testAccumulationWithId()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] {5, 6, 8, 11, 15}, createAccumulationWithIdIteratorProviderFrom(populated, 5, (a, b) -> a + b));
		assertLongIteratorAsExpected(new long[] {}, createAccumulationWithIdIteratorProviderFrom(empty, 5, (a, b) -> a + b));
	}

	private AbstractIterableLongs createAccumulationWithIdIteratorProviderFrom(final AbstractIterableLongs source, final long id, final LongBinaryOperator accumulator)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iterator() {
				return source.iterator().accumulate(id, accumulator);
			}
		};
	}
}
