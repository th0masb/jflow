/**
 *
 */
package com.github.maumay.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.gihub.maumay.jflow.iterators.misc.LongPair;
import com.gihub.maumay.jflow.iterators.misc.LongWith;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractLongIteratorZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithLong()
	{
		final AbstractIterableLongs small = getSmallLongTestIteratorProvider();
		final AbstractIterableLongs mid = getLongTestIteratorProvider();
		final AbstractIterableLongs large = getLargeLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(LongPair.of(0, 0), LongPair.of(1, 1),
				LongPair.of(2, 2), LongPair.of(3, 3), LongPair.of(4, 4)),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(LongPair.of(0, 10), LongPair.of(1, 11), LongPair.of(2, 12),
						LongPair.of(3, 13), LongPair.of(4, 14)),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(LongPair.of(0, 10), LongPair.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractEnhancedIterable<LongPair> createZipIteratorProviderFrom(
			final AbstractIterableLongs first, final AbstractIterableLongs second)
	{
		return new AbstractEnhancedIterable<LongPair>() {
			@Override
			public AbstractEnhancedIterator<LongPair> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	@Test
	void testZipWithObject()
	{
		final AbstractIterableLongs populatedLongs = getLongTestIteratorProvider();
		final AbstractIterableLongs emptyLongs = getEmptyLongTestIteratorProvider();

		final AbstractEnhancedIterable<String> smallObjects = getSmallObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> midObjects = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> largeObjects = getLargeObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(LongWith.of(0, "10"), LongWith.of(1, "11")),
				createZipIteratorProviderFrom(populatedLongs, smallObjects));

		assertObjectIteratorAsExpected(
				asList(LongWith.of(0, "0"), LongWith.of(1, "1"), LongWith.of(2, "2"),
						LongWith.of(3, "3"), LongWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedLongs, midObjects));

		assertObjectIteratorAsExpected(
				asList(LongWith.of(0, "10"), LongWith.of(1, "11"), LongWith.of(2, "12"),
						LongWith.of(3, "13"), LongWith.of(4, "14")),
				createZipIteratorProviderFrom(populatedLongs, largeObjects));

		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyLongs, emptyObjects));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyLongs, smallObjects));
	}

	private <E> AbstractEnhancedIterable<LongWith<E>> createZipIteratorProviderFrom(
			final AbstractIterableLongs first, final AbstractEnhancedIterable<E> second)
	{
		return new AbstractEnhancedIterable<LongWith<E>>() {
			@Override
			public AbstractEnhancedIterator<LongWith<E>> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}
}
