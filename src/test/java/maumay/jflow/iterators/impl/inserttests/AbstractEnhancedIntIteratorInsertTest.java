/**
 *
 */
package maumay.jflow.iterators.impl.inserttests;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.testutilities.AbstractIterableInts;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIntIteratorInsertTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		final AbstractIterableInts small = getSmallIntTestIteratorProvider();

		assertIntIteratorAsExpected(new int[] { 10, 11, 0, 1, 2, 3, 4 },
				createInsertIteratorProviderFrom(populated, small));
		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4 },
				createInsertIteratorProviderFrom(populated, empty));

		assertIntIteratorAsExpected(new int[] { 10, 11 },
				createInsertIteratorProviderFrom(empty, small));
		assertIntIteratorAsExpected(new int[0],
				createInsertIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableInts createInsertIteratorProviderFrom(
			final AbstractIterableInts source, final AbstractIterableInts toInsert)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return source.iter().insert(toInsert.iter());
			}
		};
	}
}
