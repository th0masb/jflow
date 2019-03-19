/**
 *
 */
package com.github.maumay.jflow.iterators.impl.slice;

import static java.util.Arrays.asList;

import java.util.function.IntUnaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorSliceTest extends IteratorExampleProviders implements IteratorTest
{
	@Test
	void test()
	{
		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();

		IntUnaryOperator allSlicedOperator = i -> i;
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createSlicedIteratorProviderFrom(populated, allSlicedOperator));
		assertObjectIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(empty, allSlicedOperator));

		IntUnaryOperator someSlicedOperator = i -> 2 * i;
		assertObjectIteratorAsExpected(asList("0", "2", "4"),
				createSlicedIteratorProviderFrom(populated, someSlicedOperator));
		assertObjectIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(empty, someSlicedOperator));

		IntUnaryOperator noneSlicedOperator = i -> i + 5;
		assertObjectIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(populated, noneSlicedOperator));
		assertObjectIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(empty, noneSlicedOperator));
	}

	private <E> AbstractRichIterable<E> createSlicedIteratorProviderFrom(
			AbstractRichIterable<E> source, IntUnaryOperator slicemap)
	{
		return new AbstractRichIterable<E>() {
			@Override
			public AbstractRichIterator<E> iter()
			{
				return source.iter().slice(slicemap);
			}
		};
	}
}
