package com.github.maumay.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorZipTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void testZipWithObject()
	{
		AbstractEnhancedIterable<String> small = getSmallObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> mid = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> large = getLargeObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(Tup.of("0", "10"), Tup.of("1", "11")),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(
				asList(Tup.of("0", "0"), Tup.of("1", "1"), Tup.of("2", "2"),
						Tup.of("3", "3"), Tup.of("4", "4")),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(Tup.of("0", "10"), Tup.of("1", "11"), Tup.of("2", "12"),
						Tup.of("3", "13"), Tup.of("4", "14")),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(empty, mid));
	}

	private <E1, E2> AbstractEnhancedIterable<Tup<E1, E2>> createZipIteratorProviderFrom(
			AbstractEnhancedIterable<E1> first, AbstractEnhancedIterable<E2> second)
	{
		return new AbstractEnhancedIterable<Tup<E1, E2>>() {
			@Override
			public AbstractEnhancedIterator<Tup<E1, E2>> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	// @Test
	// void testZipWithDouble()
	// {
	// AbstractEnhancedIterable<String> populatedObjects =
	// getObjectTestIteratorProvider();
	// AbstractEnhancedIterable<String> emptyObjects =
	// getEmptyObjectTestIteratorProvider();
	//
	// AbstractIterableDoubles smallDoubles = getSmallDoubleTestIteratorProvider();
	// AbstractIterableDoubles midDoubles = getDoubleTestIteratorProvider();
	// AbstractIterableDoubles largeDoubles = getLargeDoubleTestIteratorProvider();
	// AbstractIterableDoubles emptyDoubles = getSmallDoubleTestIteratorProvider();
	//
	// assertObjectIteratorAsExpected(asList(DoubleWith.of(10, "0"),
	// DoubleWith.of(11, "1")),
	// createZipIteratorProviderFrom(populatedObjects, smallDoubles));
	//
	// assertObjectIteratorAsExpected(
	// asList(DoubleWith.of(0, "0"), DoubleWith.of(1, "1"), DoubleWith.of(2, "2"),
	// DoubleWith.of(3, "3"), DoubleWith.of(4, "4")),
	// createZipIteratorProviderFrom(populatedObjects, midDoubles));
	//
	// assertObjectIteratorAsExpected(
	// asList(DoubleWith.of(10, "0"), DoubleWith.of(11, "1"), DoubleWith.of(12,
	// "2"),
	// DoubleWith.of(13, "3"), DoubleWith.of(14, "4")),
	// createZipIteratorProviderFrom(populatedObjects, largeDoubles));
	//
	// assertObjectIteratorAsExpected(asList(),
	// createZipIteratorProviderFrom(emptyObjects, emptyDoubles));
	// assertObjectIteratorAsExpected(asList(),
	// createZipIteratorProviderFrom(emptyObjects, smallDoubles));
	// }
	//
	// private <E> AbstractEnhancedIterable<DoubleWith<E>>
	// createZipIteratorProviderFrom(
	// AbstractEnhancedIterable<E> first, AbstractIterableDoubles second)
	// {
	// return new AbstractEnhancedIterable<DoubleWith<E>>() {
	// @Override
	// public AbstractEnhancedIterator<DoubleWith<E>> iter()
	// {
	// return first.iter().zipWith(second.iter());
	// }
	// };
	// }

	// @Test
	// void testZipWithLong()
	// {
	// AbstractEnhancedIterable<String> populatedObjects =
	// getObjectTestIteratorProvider();
	// AbstractEnhancedIterable<String> emptyObjects =
	// getEmptyObjectTestIteratorProvider();
	//
	// AbstractIterableLongs smallLongs = getSmallLongTestIteratorProvider();
	// AbstractIterableLongs midLongs = getLongTestIteratorProvider();
	// AbstractIterableLongs largeLongs = getLargeLongTestIteratorProvider();
	// AbstractIterableLongs emptyLongs = getSmallLongTestIteratorProvider();
	//
	// assertObjectIteratorAsExpected(asList(LongWith.of(10, "0"), LongWith.of(11,
	// "1")),
	// createZipIteratorProviderFrom(populatedObjects, smallLongs));
	//
	// assertObjectIteratorAsExpected(
	// asList(LongWith.of(0, "0"), LongWith.of(1, "1"), LongWith.of(2, "2"),
	// LongWith.of(3, "3"), LongWith.of(4, "4")),
	// createZipIteratorProviderFrom(populatedObjects, midLongs));
	//
	// assertObjectIteratorAsExpected(
	// asList(LongWith.of(10, "0"), LongWith.of(11, "1"), LongWith.of(12, "2"),
	// LongWith.of(13, "3"), LongWith.of(14, "4")),
	// createZipIteratorProviderFrom(populatedObjects, largeLongs));
	//
	// assertObjectIteratorAsExpected(asList(),
	// createZipIteratorProviderFrom(emptyObjects, emptyLongs));
	// assertObjectIteratorAsExpected(asList(),
	// createZipIteratorProviderFrom(emptyObjects, smallLongs));
	// }
	//
	// private <E> AbstractEnhancedIterable<LongWith<E>>
	// createZipIteratorProviderFrom(
	// AbstractEnhancedIterable<E> first, AbstractIterableLongs second)
	// {
	// return new AbstractEnhancedIterable<LongWith<E>>() {
	// @Override
	// public AbstractEnhancedIterator<LongWith<E>> iter()
	// {
	// return first.iter().zipWith(second.iter());
	// }
	// };
	// }

	// @Test
	// void testZipWithInt()
	// {
	// AbstractEnhancedIterable<String> populatedObjects =
	// getObjectTestIteratorProvider();
	// AbstractEnhancedIterable<String> emptyObjects =
	// getEmptyObjectTestIteratorProvider();
	//
	// AbstractIterableInts smallInts = getSmallIntTestIteratorProvider();
	// AbstractIterableInts midInts = getIntTestIteratorProvider();
	// AbstractIterableInts largeInts = getLargeIntTestIteratorProvider();
	// AbstractIterableInts emptyInts = getSmallIntTestIteratorProvider();
	//
	// assertObjectIteratorAsExpected(asList(IntWith.of(10, "0"), IntWith.of(11,
	// "1")),
	// createZipIteratorProviderFrom(populatedObjects, smallInts));
	//
	// assertObjectIteratorAsExpected(
	// asList(IntWith.of(0, "0"), IntWith.of(1, "1"), IntWith.of(2, "2"),
	// IntWith.of(3, "3"), IntWith.of(4, "4")),
	// createZipIteratorProviderFrom(populatedObjects, midInts));
	//
	// assertObjectIteratorAsExpected(
	// asList(IntWith.of(10, "0"), IntWith.of(11, "1"), IntWith.of(12, "2"),
	// IntWith.of(13, "3"), IntWith.of(14, "4")),
	// createZipIteratorProviderFrom(populatedObjects, largeInts));
	//
	// assertObjectIteratorAsExpected(asList(),
	// createZipIteratorProviderFrom(emptyObjects, emptyInts));
	// assertObjectIteratorAsExpected(asList(),
	// createZipIteratorProviderFrom(emptyObjects, smallInts));
	// }
	//
	// private <E> AbstractEnhancedIterable<IntWith<E>>
	// createZipIteratorProviderFrom(
	// AbstractEnhancedIterable<E> first, AbstractIterableInts second)
	// {
	// return new AbstractEnhancedIterable<IntWith<E>>() {
	// @Override
	// public AbstractEnhancedIterator<IntWith<E>> iter()
	// {
	// return first.iter().zipWith(second.iter());
	// }
	// };
	// }
}
