/**
 *
 */
package com.github.maumay.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.gihub.maumay.jflow.iterators.misc.IntPair;
import com.gihub.maumay.jflow.iterators.misc.IntWith;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIntIteratorZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithInt()
	{
		final AbstractIterableInts small = getSmallIntTestIteratorProvider();
		final AbstractIterableInts mid = getIntTestIteratorProvider();
		final AbstractIterableInts large = getLargeIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(IntPair.of(0, 0), IntPair.of(1, 1), IntPair.of(2, 2),
				IntPair.of(3, 3), IntPair.of(4, 4)), createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(asList(IntPair.of(0, 10), IntPair.of(1, 11),
				IntPair.of(2, 12), IntPair.of(3, 13), IntPair.of(4, 14)),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(IntPair.of(0, 10), IntPair.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractEnhancedIterable<IntPair> createZipIteratorProviderFrom(
			final AbstractIterableInts first, final AbstractIterableInts second)
	{
		return new AbstractEnhancedIterable<IntPair>() {
			@Override
			public AbstractEnhancedIterator<IntPair> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	@Test
	void testZipWithObject()
	{
		final AbstractIterableInts populatedInts = getIntTestIteratorProvider();
		final AbstractIterableInts emptyInts = getEmptyIntTestIteratorProvider();

		final AbstractEnhancedIterable<String> smallObjects = getSmallObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> midObjects = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> largeObjects = getLargeObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

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
			final AbstractIterableInts first, final AbstractEnhancedIterable<E> second)
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
