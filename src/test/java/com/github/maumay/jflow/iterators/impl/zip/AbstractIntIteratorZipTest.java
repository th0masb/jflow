/**
 *
 */
package com.github.maumay.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;
import com.github.maumay.jflow.utils.IntTup;
import com.github.maumay.jflow.utils.IntWith;

/**
 * @author ThomasB
 */
class AbstractIntIteratorZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithInt()
	{
		AbstractIterableInts small = getSmallIntTestIteratorProvider();
		AbstractIterableInts mid = getIntTestIteratorProvider();
		AbstractIterableInts large = getLargeIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(IntTup.of(0, 0), IntTup.of(1, 1), IntTup.of(2, 2),
				IntTup.of(3, 3), IntTup.of(4, 4)), createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(asList(IntTup.of(0, 10), IntTup.of(1, 11), IntTup.of(2, 12),
				IntTup.of(3, 13), IntTup.of(4, 14)), createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(IntTup.of(0, 10), IntTup.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractEnhancedIterable<IntTup> createZipIteratorProviderFrom(
			AbstractIterableInts first, AbstractIterableInts second)
	{
		return new AbstractEnhancedIterable<IntTup>() {
			@Override
			public AbstractEnhancedIterator<IntTup> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	@Test
	void testZipWithObject()
	{
		AbstractIterableInts populatedInts = getIntTestIteratorProvider();
		AbstractIterableInts emptyInts = getEmptyIntTestIteratorProvider();

		AbstractEnhancedIterable<String> smallObjects = getSmallObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> midObjects = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> largeObjects = getLargeObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(IntWith.of(0, "10"), IntWith.of(1, "11")),
				createZipIteratorProviderFrom(populatedInts, smallObjects));

		assertObjectIteratorAsExpected(
				asList(IntWith.of(0, "0"), IntWith.of(1, "1"), IntWith.of(2, "2"),
						IntWith.of(3, "3"), IntWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedInts, midObjects));

		assertObjectIteratorAsExpected(
				asList(IntWith.of(0, "10"), IntWith.of(1, "11"), IntWith.of(2, "12"),
						IntWith.of(3, "13"), IntWith.of(4, "14")),
				createZipIteratorProviderFrom(populatedInts, largeObjects));

		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyInts, emptyObjects));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyInts, smallObjects));
	}

	private <E> AbstractEnhancedIterable<IntWith<E>> createZipIteratorProviderFrom(
			AbstractIterableInts first, AbstractEnhancedIterable<E> second)
	{
		return new AbstractEnhancedIterable<IntWith<E>>() {
			@Override
			public AbstractEnhancedIterator<IntWith<E>> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}
}
