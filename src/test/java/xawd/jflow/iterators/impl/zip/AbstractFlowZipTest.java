package xawd.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.LongWith;
import xawd.jflow.iterators.misc.Pair;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractFlowZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithObject()
	{
		final AbstractFlowIterable<String> small = getSmallObjectTestIteratorProvider();
		final AbstractFlowIterable<String> mid = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> large = getLargeObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(Pair.of("0", "10"), Pair.of("1", "11")),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(
				asList(Pair.of("0", "0"), Pair.of("1", "1"), Pair.of("2", "2"), Pair.of("3", "3"), Pair.of("4", "4")),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(Pair.of("0", "10"), Pair.of("1", "11"), Pair.of("2", "12"), Pair.of("3", "13"), Pair.of("4", "14")),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private <E1, E2> AbstractFlowIterable<Pair<E1, E2>> createZipIteratorProviderFrom(final AbstractFlowIterable<E1> first, final AbstractFlowIterable<E2> second)
	{
		return new AbstractFlowIterable<Pair<E1, E2>>() {
			@Override
			public AbstractFlow<Pair<E1, E2>> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithDouble()
	{
		final AbstractFlowIterable<String> populatedObjects = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		final AbstractIterableDoubles smallDoubles = getSmallDoubleTestIteratorProvider();
		final AbstractIterableDoubles midDoubles = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles largeDoubles = getLargeDoubleTestIteratorProvider();
		final AbstractIterableDoubles emptyDoubles = getSmallDoubleTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(10, "0"), DoubleWith.of(11, "1")),
				createZipIteratorProviderFrom(populatedObjects, smallDoubles));

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(0, "0"), DoubleWith.of(1, "1"), DoubleWith.of(2, "2"), DoubleWith.of(3, "3"), DoubleWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedObjects, midDoubles));

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(10, "0"), DoubleWith.of(11, "1"), DoubleWith.of(12, "2"), DoubleWith.of(13, "3"), DoubleWith.of(14, "4")),
				createZipIteratorProviderFrom(populatedObjects, largeDoubles));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyObjects, emptyDoubles));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyObjects, smallDoubles));
	}

	private <E> AbstractFlowIterable<DoubleWith<E>> createZipIteratorProviderFrom(final AbstractFlowIterable<E> first, final AbstractIterableDoubles second)
	{
		return new AbstractFlowIterable<DoubleWith<E>>() {
			@Override
			public AbstractFlow<DoubleWith<E>> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithLong()
	{
		final AbstractFlowIterable<String> populatedObjects = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		final AbstractIterableLongs smallLongs = getSmallLongTestIteratorProvider();
		final AbstractIterableLongs midLongs = getLongTestIteratorProvider();
		final AbstractIterableLongs largeLongs = getLargeLongTestIteratorProvider();
		final AbstractIterableLongs emptyLongs = getSmallLongTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(LongWith.of(10, "0"), LongWith.of(11, "1")),
				createZipIteratorProviderFrom(populatedObjects, smallLongs));

		assertObjectIteratorAsExpected(
				asList(LongWith.of(0, "0"), LongWith.of(1, "1"), LongWith.of(2, "2"), LongWith.of(3, "3"), LongWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedObjects, midLongs));

		assertObjectIteratorAsExpected(
				asList(LongWith.of(10, "0"), LongWith.of(11, "1"), LongWith.of(12, "2"), LongWith.of(13, "3"), LongWith.of(14, "4")),
				createZipIteratorProviderFrom(populatedObjects, largeLongs));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyObjects, emptyLongs));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyObjects, smallLongs));
	}

	private <E> AbstractFlowIterable<LongWith<E>> createZipIteratorProviderFrom(final AbstractFlowIterable<E> first, final AbstractIterableLongs second)
	{
		return new AbstractFlowIterable<LongWith<E>>() {
			@Override
			public AbstractFlow<LongWith<E>> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithInt()
	{
		final AbstractFlowIterable<String> populatedObjects = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		final AbstractIterableInts smallInts = getSmallIntTestIteratorProvider();
		final AbstractIterableInts midInts = getIntTestIteratorProvider();
		final AbstractIterableInts largeInts = getLargeIntTestIteratorProvider();
		final AbstractIterableInts emptyInts = getSmallIntTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(IntWith.of(10, "0"), IntWith.of(11, "1")),
				createZipIteratorProviderFrom(populatedObjects, smallInts));

		assertObjectIteratorAsExpected(
				asList(IntWith.of(0, "0"), IntWith.of(1, "1"), IntWith.of(2, "2"), IntWith.of(3, "3"), IntWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedObjects, midInts));

		assertObjectIteratorAsExpected(
				asList(IntWith.of(10, "0"), IntWith.of(11, "1"), IntWith.of(12, "2"), IntWith.of(13, "3"), IntWith.of(14, "4")),
				createZipIteratorProviderFrom(populatedObjects, largeInts));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyObjects, emptyInts));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyObjects, smallInts));
	}

	private <E> AbstractFlowIterable<IntWith<E>> createZipIteratorProviderFrom(final AbstractFlowIterable<E> first, final AbstractIterableInts second)
	{
		return new AbstractFlowIterable<IntWith<E>>() {
			@Override
			public AbstractFlow<IntWith<E>> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}
}
