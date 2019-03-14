/**
 *
 */
package com.github.maumay.jflow.iterators.impl.slice;

import java.util.function.IntUnaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractDoubleIteratorSliceTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		IntUnaryOperator allSlicedOperator = i -> i;
		assertDoubleIteratorAsExpected(new double[] { 0, 1, 2, 3, 4 },
				createSlicedIteratorProviderFrom(populated, allSlicedOperator));
		assertDoubleIteratorAsExpected(new double[] {},
				createSlicedIteratorProviderFrom(empty, allSlicedOperator));

		IntUnaryOperator someSlicedOperator = i -> 2 * i;
		assertDoubleIteratorAsExpected(new double[] { 0, 2, 4 },
				createSlicedIteratorProviderFrom(populated, someSlicedOperator));
		assertDoubleIteratorAsExpected(new double[] {},
				createSlicedIteratorProviderFrom(empty, someSlicedOperator));

		IntUnaryOperator noneSlicedOperator = i -> i + 5;
		assertDoubleIteratorAsExpected(new double[] {},
				createSlicedIteratorProviderFrom(populated, noneSlicedOperator));
		assertDoubleIteratorAsExpected(new double[] {},
				createSlicedIteratorProviderFrom(empty, noneSlicedOperator));
	}

	private AbstractIterableDoubles createSlicedIteratorProviderFrom(AbstractIterableDoubles src,
			IntUnaryOperator indexMapping)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return src.iter().slice(indexMapping);
			}
		};
	}
}
