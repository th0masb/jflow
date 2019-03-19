package com.github.maumay.jflow.iterators.impl.inserttests;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorInsertTest extends IteratorExampleProviders implements IteratorTest
{
	@Test
	void test()
	{
		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();
		AbstractRichIterable<String> small = getShortObjectIteratorProviders();

		assertObjectIteratorAsExpected(asList("10", "11", "0", "1", "2", "3", "4"),
				createinsertIteratorProviderFrom(populated, small));
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createinsertIteratorProviderFrom(populated, empty));

		assertObjectIteratorAsExpected(asList("10", "11"),
				createinsertIteratorProviderFrom(empty, small));
		assertObjectIteratorAsExpected(asList(), createinsertIteratorProviderFrom(empty, empty));
	}

	private <E> AbstractRichIterable<E> createinsertIteratorProviderFrom(
			AbstractRichIterable<E> source, AbstractRichIterable<E> toInsert)
	{
		return new AbstractRichIterable<E>() {
			@Override
			public AbstractRichIterator<E> iter()
			{
				return source.iter().insert(toInsert.iter());
			}
		};
	}
}
