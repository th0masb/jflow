package com.github.maumay.jflow.iterators.impl.inserttests;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorInsertTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> small = getSmallObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("10", "11", "0", "1", "2", "3", "4"),
				createinsertIteratorProviderFrom(populated, small));
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createinsertIteratorProviderFrom(populated, empty));

		assertObjectIteratorAsExpected(asList("10", "11"),
				createinsertIteratorProviderFrom(empty, small));
		assertObjectIteratorAsExpected(asList(), createinsertIteratorProviderFrom(empty, empty));
	}

	private <E> AbstractEnhancedIterable<E> createinsertIteratorProviderFrom(
			final AbstractEnhancedIterable<E> source, final AbstractEnhancedIterable<E> toInsert)
	{
		return new AbstractEnhancedIterable<E>() {
			@Override
			public AbstractEnhancedIterator<E> iter()
			{
				return source.iter().insert(toInsert.iter());
			}
		};
	}
}
