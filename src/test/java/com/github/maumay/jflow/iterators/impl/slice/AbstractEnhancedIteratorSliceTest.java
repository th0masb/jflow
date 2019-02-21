/**
 *
 */
package com.github.maumay.jflow.iterators.impl.slice;

import static java.util.Arrays.asList;

import java.util.function.IntUnaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorSliceTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final IntUnaryOperator allSlicedOperator = i -> i;
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createSlicedIteratorProviderFrom(populated, allSlicedOperator));
		assertObjectIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(empty, allSlicedOperator));

		final IntUnaryOperator someSlicedOperator = i -> 2 * i;
		assertObjectIteratorAsExpected(asList("0", "2", "4"),
				createSlicedIteratorProviderFrom(populated, someSlicedOperator));
		assertObjectIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(empty, someSlicedOperator));

		final IntUnaryOperator noneSlicedOperator = i -> i + 5;
		assertObjectIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(populated, noneSlicedOperator));
		assertObjectIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(empty, noneSlicedOperator));
	}

	private <E> AbstractEnhancedIterable<E> createSlicedIteratorProviderFrom(
			final AbstractEnhancedIterable<E> source, final IntUnaryOperator slicemap)
	{
		return new AbstractEnhancedIterable<E>() {
			@Override
			public AbstractEnhancedIterator<E> iter()
			{
				return source.iter().slice(slicemap);
			}
		};
	}
}
