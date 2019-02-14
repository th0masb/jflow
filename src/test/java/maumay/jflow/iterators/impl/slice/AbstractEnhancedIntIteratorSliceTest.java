/**
 *
 */
package maumay.jflow.iterators.impl.slice;

import java.util.function.IntUnaryOperator;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.testutilities.AbstractIterableInts;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIntIteratorSliceTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		final IntUnaryOperator allSlicedOperator = i -> i;
		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4 },
				createSlicedIteratorProviderFrom(populated, allSlicedOperator));
		assertIntIteratorAsExpected(new int[] {},
				createSlicedIteratorProviderFrom(empty, allSlicedOperator));

		final IntUnaryOperator someSlicedOperator = i -> 2 * i;
		assertIntIteratorAsExpected(new int[] { 0, 2, 4 },
				createSlicedIteratorProviderFrom(populated, someSlicedOperator));
		assertIntIteratorAsExpected(new int[] {},
				createSlicedIteratorProviderFrom(empty, someSlicedOperator));

		final IntUnaryOperator noneSlicedOperator = i -> i + 5;
		assertIntIteratorAsExpected(new int[] {},
				createSlicedIteratorProviderFrom(populated, noneSlicedOperator));
		assertIntIteratorAsExpected(new int[] {},
				createSlicedIteratorProviderFrom(empty, noneSlicedOperator));
	}

	private AbstractIterableInts createSlicedIteratorProviderFrom(
			final AbstractIterableInts src, final IntUnaryOperator indexMapping)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return src.iter().slice(indexMapping);
			}
		};
	}
}
