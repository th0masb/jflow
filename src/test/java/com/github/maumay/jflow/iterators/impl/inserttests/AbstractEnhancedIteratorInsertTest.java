package com.github.maumay.jflow.iterators.impl.inserttests;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractEnhancedIterator;
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
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> small = getSmallObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("10", "11", "0", "1", "2", "3", "4"),
				createinsertIteratorProviderFrom(populated, small));
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createinsertIteratorProviderFrom(populated, empty));

		assertObjectIteratorAsExpected(asList("10", "11"),
				createinsertIteratorProviderFrom(empty, small));
		assertObjectIteratorAsExpected(asList(), createinsertIteratorProviderFrom(empty, empty));
	}

	private <E> AbstractEnhancedIterable<E> createinsertIteratorProviderFrom(
			AbstractEnhancedIterable<E> source, AbstractEnhancedIterable<E> toInsert)
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
