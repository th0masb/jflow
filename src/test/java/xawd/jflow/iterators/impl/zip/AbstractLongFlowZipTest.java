/**
 *
 */
package xawd.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithLong;
import xawd.jflow.iterators.misc.LongPair;
import xawd.jflow.iterators.misc.LongWith;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractLongFlowZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithLong()
	{
		final AbstractIterableLongs small = getSmallLongTestIteratorProvider();
		final AbstractIterableLongs mid = getLongTestIteratorProvider();
		final AbstractIterableLongs large = getLargeLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(LongPair.of(0, 0), LongPair.of(1, 1), LongPair.of(2, 2), LongPair.of(3, 3), LongPair.of(4, 4)),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(LongPair.of(0, 10), LongPair.of(1, 11), LongPair.of(2, 12), LongPair.of(3, 13), LongPair.of(4, 14)),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(
				asList(LongPair.of(0, 10), LongPair.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractFlowIterable<LongPair> createZipIteratorProviderFrom(final AbstractIterableLongs first, final AbstractIterableLongs second)
	{
		return new AbstractFlowIterable<LongPair>() {
			@Override
			public AbstractFlow<LongPair> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithObject()
	{
		final AbstractIterableLongs populatedLongs = getLongTestIteratorProvider();
		final AbstractIterableLongs emptyLongs = getEmptyLongTestIteratorProvider();

		final AbstractFlowIterable<String> smallObjects = getSmallObjectTestIteratorProvider();
		final AbstractFlowIterable<String> midObjects = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> largeObjects = getLargeObjectTestIteratorProvider();
		final AbstractFlowIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(LongWith.of(0, "10"), LongWith.of(1, "11")),
				createZipIteratorProviderFrom(populatedLongs, smallObjects));

		assertObjectIteratorAsExpected(
				asList(LongWith.of(0, "0"), LongWith.of(1, "1"), LongWith.of(2, "2"), LongWith.of(3, "3"), LongWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedLongs, midObjects));

		assertObjectIteratorAsExpected(
				asList(LongWith.of(0, "10"), LongWith.of(1, "11"), LongWith.of(2, "12"), LongWith.of(3, "13"), LongWith.of(4, "14")),
				createZipIteratorProviderFrom(populatedLongs, largeObjects));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyLongs, emptyObjects));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyLongs, smallObjects));
	}

	private <E> AbstractFlowIterable<LongWith<E>> createZipIteratorProviderFrom(final AbstractIterableLongs first, final AbstractFlowIterable<E> second)
	{
		return new AbstractFlowIterable<LongWith<E>>() {
			@Override
			public AbstractFlow<LongWith<E>> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithDouble()
	{
		final AbstractIterableLongs populatedLongs = getLongTestIteratorProvider();
		final AbstractIterableLongs emptyLongs = getEmptyLongTestIteratorProvider();

		final AbstractIterableDoubles smallDoubles = getSmallDoubleTestIteratorProvider();
		final AbstractIterableDoubles midDoubles = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles largeDoubles = getLargeDoubleTestIteratorProvider();
		final AbstractIterableDoubles emptyDoubles = getEmptyDoubleTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(DoubleWithLong.of(10, 0), DoubleWithLong.of(11, 1)),
				createZipIteratorProviderFrom(populatedLongs, smallDoubles));

		assertObjectIteratorAsExpected(
				asList(DoubleWithLong.of(0, 0), DoubleWithLong.of(1, 1), DoubleWithLong.of(2, 2), DoubleWithLong.of(3, 3), DoubleWithLong.of(4, 4)),
				createZipIteratorProviderFrom(populatedLongs, midDoubles));

		assertObjectIteratorAsExpected(
				asList(DoubleWithLong.of(10, 0), DoubleWithLong.of(11, 1), DoubleWithLong.of(12, 2), DoubleWithLong.of(13, 3), DoubleWithLong.of(14, 4)),
				createZipIteratorProviderFrom(populatedLongs, largeDoubles));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyLongs, emptyDoubles));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyLongs, smallDoubles));
	}

	private AbstractFlowIterable<DoubleWithLong> createZipIteratorProviderFrom(final AbstractIterableLongs first, final AbstractIterableDoubles second)
	{
		return new AbstractFlowIterable<DoubleWithLong>() {
			@Override
			public AbstractFlow<DoubleWithLong> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithInt()
	{
		final AbstractIterableLongs populatedLongs = getLongTestIteratorProvider();
		final AbstractIterableLongs emptyLongs = getEmptyLongTestIteratorProvider();

		final AbstractIterableInts smallInts = getSmallIntTestIteratorProvider();
		final AbstractIterableInts midInts = getIntTestIteratorProvider();
		final AbstractIterableInts largeInts = getLargeIntTestIteratorProvider();
		final AbstractIterableInts emptyInts = getEmptyIntTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(IntWithLong.of(10, 0), IntWithLong.of(11, 1)),
				createZipIteratorProviderFrom(populatedLongs, smallInts));

		assertObjectIteratorAsExpected(
				asList(IntWithLong.of(0, 0), IntWithLong.of(1, 1), IntWithLong.of(2, 2), IntWithLong.of(3, 3), IntWithLong.of(4, 4)),
				createZipIteratorProviderFrom(populatedLongs, midInts));

		assertObjectIteratorAsExpected(
				asList(IntWithLong.of(10, 0), IntWithLong.of(11, 1), IntWithLong.of(12, 2), IntWithLong.of(13, 3), IntWithLong.of(14, 4)),
				createZipIteratorProviderFrom(populatedLongs, largeInts));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyLongs, emptyInts));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyLongs, smallInts));
	}

	private AbstractFlowIterable<IntWithLong> createZipIteratorProviderFrom(final AbstractIterableLongs first, final AbstractIterableInts second)
	{
		return new AbstractFlowIterable<IntWithLong>() {
			@Override
			public AbstractFlow<IntWithLong> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}
}
