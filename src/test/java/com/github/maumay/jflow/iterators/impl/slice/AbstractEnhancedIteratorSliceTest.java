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
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorSliceTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	@Test
	void test()
	{
		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();

		IntUnaryOperator allSlicedOperator = i -> i;
		assertIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createSlicedIteratorProviderFrom(populated, allSlicedOperator));
		assertIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(empty, allSlicedOperator));

		IntUnaryOperator someSlicedOperator = i -> 2 * i;
		assertIteratorAsExpected(asList("0", "2", "4"),
				createSlicedIteratorProviderFrom(populated, someSlicedOperator));
		assertIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(empty, someSlicedOperator));

		IntUnaryOperator noneSlicedOperator = i -> i + 5;
		assertIteratorAsExpected(asList(),
				createSlicedIteratorProviderFrom(populated, noneSlicedOperator));
		assertIteratorAsExpected(asList(),
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
