/**
 *
 */
package com.github.maumay.jflow.iterators.impl.slice;

import java.util.function.IntUnaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedDoubleIteratorSliceTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		final IntUnaryOperator allSlicedOperator = i -> i;
		assertDoubleIteratorAsExpected(new double[] { 0, 1, 2, 3, 4 },
				createSlicedIteratorProviderFrom(populated, allSlicedOperator));
		assertDoubleIteratorAsExpected(new double[] {},
				createSlicedIteratorProviderFrom(empty, allSlicedOperator));

		final IntUnaryOperator someSlicedOperator = i -> 2 * i;
		assertDoubleIteratorAsExpected(new double[] { 0, 2, 4 },
				createSlicedIteratorProviderFrom(populated, someSlicedOperator));
		assertDoubleIteratorAsExpected(new double[] {},
				createSlicedIteratorProviderFrom(empty, someSlicedOperator));

		final IntUnaryOperator noneSlicedOperator = i -> i + 5;
		assertDoubleIteratorAsExpected(new double[] {},
				createSlicedIteratorProviderFrom(populated, noneSlicedOperator));
		assertDoubleIteratorAsExpected(new double[] {},
				createSlicedIteratorProviderFrom(empty, noneSlicedOperator));
	}

	private AbstractIterableDoubles createSlicedIteratorProviderFrom(
			final AbstractIterableDoubles src, final IntUnaryOperator indexMapping)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractEnhancedDoubleIterator iter()
			{
				return src.iter().slice(indexMapping);
			}
		};
	}
}
