/**
 *
 */
package maumay.jflow.iterators.impl.scan;

import java.util.function.LongBinaryOperator;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedLongIterator;
import maumay.jflow.testutilities.AbstractIterableLongs;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedLongIteratorAccumulationTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void testAccumulationWithoutId()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] { 0, 1, 3, 6, 10 },
				createAccumulationWithoutIdIteratorProviderFrom(populated,
						(a, b) -> a + b));
		assertLongIteratorAsExpected(new long[] {},
				createAccumulationWithoutIdIteratorProviderFrom(empty, (a, b) -> a + b));
	}

	private AbstractIterableLongs createAccumulationWithoutIdIteratorProviderFrom(
			final AbstractIterableLongs source, final LongBinaryOperator accumulator)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractEnhancedLongIterator iter()
			{
				return source.iter().scan(accumulator);
			}
		};
	}

	@Test
	void testAccumulationWithId()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] { 5, 6, 8, 11, 15 },
				createAccumulationWithIdIteratorProviderFrom(populated, 5,
						(a, b) -> a + b));
		assertLongIteratorAsExpected(new long[] {},
				createAccumulationWithIdIteratorProviderFrom(empty, 5, (a, b) -> a + b));
	}

	private AbstractIterableLongs createAccumulationWithIdIteratorProviderFrom(
			final AbstractIterableLongs source, final long id,
			final LongBinaryOperator accumulator)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractEnhancedLongIterator iter()
			{
				return source.iter().scan(id, accumulator);
			}
		};
	}
}
