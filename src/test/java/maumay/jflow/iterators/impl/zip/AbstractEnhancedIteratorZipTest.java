package maumay.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.iterators.misc.DoubleWith;
import maumay.jflow.iterators.misc.IntWith;
import maumay.jflow.iterators.misc.LongWith;
import maumay.jflow.iterators.misc.Pair;
import maumay.jflow.testutilities.AbstractEnhancedIterable;
import maumay.jflow.testutilities.AbstractIterableDoubles;
import maumay.jflow.testutilities.AbstractIterableInts;
import maumay.jflow.testutilities.AbstractIterableLongs;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithObject()
	{
		final AbstractEnhancedIterable<String> small = getSmallObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> mid = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> large = getLargeObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(Pair.of("0", "10"), Pair.of("1", "11")),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(Pair.of("0", "0"), Pair.of("1", "1"),
				Pair.of("2", "2"), Pair.of("3", "3"), Pair.of("4", "4")),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(Pair.of("0", "10"), Pair.of("1", "11"), Pair.of("2", "12"),
						Pair.of("3", "13"), Pair.of("4", "14")),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private <E1, E2> AbstractEnhancedIterable<Pair<E1, E2>> createZipIteratorProviderFrom(
			final AbstractEnhancedIterable<E1> first, final AbstractEnhancedIterable<E2> second)
	{
		return new AbstractEnhancedIterable<Pair<E1, E2>>() {
			@Override
			public AbstractEnhancedIterator<Pair<E1, E2>> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	@Test
	void testZipWithDouble()
	{
		final AbstractEnhancedIterable<String> populatedObjects = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		final AbstractIterableDoubles smallDoubles = getSmallDoubleTestIteratorProvider();
		final AbstractIterableDoubles midDoubles = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles largeDoubles = getLargeDoubleTestIteratorProvider();
		final AbstractIterableDoubles emptyDoubles = getSmallDoubleTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(DoubleWith.of(10, "0"), DoubleWith.of(11, "1")),
				createZipIteratorProviderFrom(populatedObjects, smallDoubles));

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(0, "0"), DoubleWith.of(1, "1"), DoubleWith.of(2, "2"),
						DoubleWith.of(3, "3"), DoubleWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedObjects, midDoubles));

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(10, "0"), DoubleWith.of(11, "1"), DoubleWith.of(12, "2"),
						DoubleWith.of(13, "3"), DoubleWith.of(14, "4")),
				createZipIteratorProviderFrom(populatedObjects, largeDoubles));

		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyObjects, emptyDoubles));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyObjects, smallDoubles));
	}

	private <E> AbstractEnhancedIterable<DoubleWith<E>> createZipIteratorProviderFrom(
			final AbstractEnhancedIterable<E> first, final AbstractIterableDoubles second)
	{
		return new AbstractEnhancedIterable<DoubleWith<E>>() {
			@Override
			public AbstractEnhancedIterator<DoubleWith<E>> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	@Test
	void testZipWithLong()
	{
		final AbstractEnhancedIterable<String> populatedObjects = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		final AbstractIterableLongs smallLongs = getSmallLongTestIteratorProvider();
		final AbstractIterableLongs midLongs = getLongTestIteratorProvider();
		final AbstractIterableLongs largeLongs = getLargeLongTestIteratorProvider();
		final AbstractIterableLongs emptyLongs = getSmallLongTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(LongWith.of(10, "0"), LongWith.of(11, "1")),
				createZipIteratorProviderFrom(populatedObjects, smallLongs));

		assertObjectIteratorAsExpected(
				asList(LongWith.of(0, "0"), LongWith.of(1, "1"), LongWith.of(2, "2"),
						LongWith.of(3, "3"), LongWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedObjects, midLongs));

		assertObjectIteratorAsExpected(
				asList(LongWith.of(10, "0"), LongWith.of(11, "1"), LongWith.of(12, "2"),
						LongWith.of(13, "3"), LongWith.of(14, "4")),
				createZipIteratorProviderFrom(populatedObjects, largeLongs));

		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyObjects, emptyLongs));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyObjects, smallLongs));
	}

	private <E> AbstractEnhancedIterable<LongWith<E>> createZipIteratorProviderFrom(
			final AbstractEnhancedIterable<E> first, final AbstractIterableLongs second)
	{
		return new AbstractEnhancedIterable<LongWith<E>>() {
			@Override
			public AbstractEnhancedIterator<LongWith<E>> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	@Test
	void testZipWithInt()
	{
		final AbstractEnhancedIterable<String> populatedObjects = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		final AbstractIterableInts smallInts = getSmallIntTestIteratorProvider();
		final AbstractIterableInts midInts = getIntTestIteratorProvider();
		final AbstractIterableInts largeInts = getLargeIntTestIteratorProvider();
		final AbstractIterableInts emptyInts = getSmallIntTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(IntWith.of(10, "0"), IntWith.of(11, "1")),
				createZipIteratorProviderFrom(populatedObjects, smallInts));

		assertObjectIteratorAsExpected(
				asList(IntWith.of(0, "0"), IntWith.of(1, "1"), IntWith.of(2, "2"),
						IntWith.of(3, "3"), IntWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedObjects, midInts));

		assertObjectIteratorAsExpected(
				asList(IntWith.of(10, "0"), IntWith.of(11, "1"), IntWith.of(12, "2"),
						IntWith.of(13, "3"), IntWith.of(14, "4")),
				createZipIteratorProviderFrom(populatedObjects, largeInts));

		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyObjects, emptyInts));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyObjects, smallInts));
	}

	private <E> AbstractEnhancedIterable<IntWith<E>> createZipIteratorProviderFrom(
			final AbstractEnhancedIterable<E> first, final AbstractIterableInts second)
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
