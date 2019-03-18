/**
 *
 */
package com.github.maumay.jflow.iterators.impl.appendtests;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorAppendTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractRichIterable<String> populated = getObjectTestIteratorProvider();
		AbstractRichIterable<String> empty = getEmptyObjectTestIteratorProvider();
		AbstractRichIterable<String> small = getSmallObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4", "10", "11"),
				createAppendIteratorProviderFrom(populated, small));
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createAppendIteratorProviderFrom(populated, empty));

		assertObjectIteratorAsExpected(asList("10", "11"),
				createAppendIteratorProviderFrom(empty, small));
		assertObjectIteratorAsExpected(asList(), createAppendIteratorProviderFrom(empty, empty));
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
