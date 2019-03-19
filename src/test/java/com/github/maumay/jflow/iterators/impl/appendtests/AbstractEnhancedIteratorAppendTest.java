/**
 *
 */
package com.github.maumay.jflow.iterators.impl.appendtests;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorAppendTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	@Test
	void test()
	{
		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();
		AbstractRichIterable<String> small = getShortObjectIteratorProviders();

		assertIteratorAsExpected(asList("0", "1", "2", "3", "4", "10", "11"),
				createAppendIteratorProviderFrom(populated, small));
		assertIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createAppendIteratorProviderFrom(populated, empty));

		assertIteratorAsExpected(asList("10", "11"),
				createAppendIteratorProviderFrom(empty, small));
		assertIteratorAsExpected(asList(), createAppendIteratorProviderFrom(empty, empty));
	}

	private <E> AbstractRichIterable<E> createAppendIteratorProviderFrom(
			AbstractRichIterable<E> source, AbstractRichIterable<E> toAppend)
	{
		return new AbstractRichIterable<E>() {
			@Override
			public AbstractRichIterator<E> iter()
			{
				return source.iterator().append(toAppend.iterator());
			}
		};
	}
}
