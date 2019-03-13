/**
 *
 */
package com.github.maumay.jflow.iterators.impl.appendtests;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
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
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> small = getSmallObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4", "10", "11"),
				createAppendIteratorProviderFrom(populated, small));
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createAppendIteratorProviderFrom(populated, empty));

		assertObjectIteratorAsExpected(asList("10", "11"),
				createAppendIteratorProviderFrom(empty, small));
		assertObjectIteratorAsExpected(asList(), createAppendIteratorProviderFrom(empty, empty));
	}

	private <E> AbstractEnhancedIterable<E> createAppendIteratorProviderFrom(
			AbstractEnhancedIterable<E> source, AbstractEnhancedIterable<E> toAppend)
	{
		return new AbstractEnhancedIterable<E>() {
			@Override
			public AbstractEnhancedIterator<E> iter()
			{
				return source.iterator().append(toAppend.iterator());
			}
		};
	}
}
