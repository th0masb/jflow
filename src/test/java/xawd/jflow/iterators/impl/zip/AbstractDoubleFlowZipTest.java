/**
 *
 */
package xawd.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.misc.DoublePair;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithDouble;
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
class AbstractDoubleFlowZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithDouble()
	{
		final AbstractIterableDoubles small = getSmallDoubleTestIteratorProvider();
		final AbstractIterableDoubles mid = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles large = getLargeDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(DoublePair.of(0, 0), DoublePair.of(1, 1), DoublePair.of(2, 2), DoublePair.of(3, 3), DoublePair.of(4, 4)),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(DoublePair.of(0, 10), DoublePair.of(1, 11), DoublePair.of(2, 12), DoublePair.of(3, 13), DoublePair.of(4, 14)),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(
				asList(DoublePair.of(0, 10), DoublePair.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractFlowIterable<DoublePair> createZipIteratorProviderFrom(final AbstractIterableDoubles first, final AbstractIterableDoubles second)
	{
		return new AbstractFlowIterable<DoublePair>() {
			@Override
			public AbstractFlow<DoublePair> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithObject()
	{
		final AbstractIterableDoubles populatedDoubles = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles emptyDoubles = getEmptyDoubleTestIteratorProvider();

		final AbstractFlowIterable<String> smallObjects = getSmallObjectTestIteratorProvider();
		final AbstractFlowIterable<String> midObjects = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> largeObjects = getLargeObjectTestIteratorProvider();
		final AbstractFlowIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(0, "10"), DoubleWith.of(1, "11")),
				createZipIteratorProviderFrom(populatedDoubles, smallObjects));

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(0, "0"), DoubleWith.of(1, "1"), DoubleWith.of(2, "2"), DoubleWith.of(3, "3"), DoubleWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedDoubles, midObjects));

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(0, "10"), DoubleWith.of(1, "11"), DoubleWith.of(2, "12"), DoubleWith.of(3, "13"), DoubleWith.of(4, "14")),
				createZipIteratorProviderFrom(populatedDoubles, largeObjects));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyDoubles, emptyObjects));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyDoubles, smallObjects));
	}

	private <E> AbstractFlowIterable<DoubleWith<E>> createZipIteratorProviderFrom(final AbstractIterableDoubles first, final AbstractFlowIterable<E> second)
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
		final AbstractIterableDoubles populatedDoubles = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles emptyDoubles = getEmptyDoubleTestIteratorProvider();

		final AbstractIterableLongs smallLongs = getSmallLongTestIteratorProvider();
		final AbstractIterableLongs midLongs = getLongTestIteratorProvider();
		final AbstractIterableLongs largeLongs = getLargeLongTestIteratorProvider();
		final AbstractIterableLongs emptyLongs = getEmptyLongTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(DoubleWithLong.of(0, 10), DoubleWithLong.of(1, 11)),
				createZipIteratorProviderFrom(populatedDoubles, smallLongs));

		assertObjectIteratorAsExpected(
				asList(DoubleWithLong.of(0, 0), DoubleWithLong.of(1, 1), DoubleWithLong.of(2, 2), DoubleWithLong.of(3, 3), DoubleWithLong.of(4, 4)),
				createZipIteratorProviderFrom(populatedDoubles, midLongs));

		assertObjectIteratorAsExpected(
				asList(DoubleWithLong.of(0, 10), DoubleWithLong.of(1, 11), DoubleWithLong.of(2, 12), DoubleWithLong.of(3, 13), DoubleWithLong.of(4, 14)),
				createZipIteratorProviderFrom(populatedDoubles, largeLongs));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyDoubles, emptyLongs));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyDoubles, smallLongs));
	}

	private AbstractFlowIterable<DoubleWithLong> createZipIteratorProviderFrom(final AbstractIterableDoubles first, final AbstractIterableLongs second)
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
		final AbstractIterableDoubles populatedDoubles = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles emptyDoubles = getEmptyDoubleTestIteratorProvider();

		final AbstractIterableInts smallInts = getSmallIntTestIteratorProvider();
		final AbstractIterableInts midInts = getIntTestIteratorProvider();
		final AbstractIterableInts largeInts = getLargeIntTestIteratorProvider();
		final AbstractIterableInts emptyInts = getEmptyIntTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(IntWithDouble.of(10, 0), IntWithDouble.of(11, 1)),
				createZipIteratorProviderFrom(populatedDoubles, smallInts));

		assertObjectIteratorAsExpected(
				asList(IntWithDouble.of(0, 0), IntWithDouble.of(1, 1), IntWithDouble.of(2, 2), IntWithDouble.of(3, 3), IntWithDouble.of(4, 4)),
				createZipIteratorProviderFrom(populatedDoubles, midInts));

		assertObjectIteratorAsExpected(
				asList(IntWithDouble.of(10, 0), IntWithDouble.of(11, 1), IntWithDouble.of(12, 2), IntWithDouble.of(13, 3), IntWithDouble.of(14, 4)),
				createZipIteratorProviderFrom(populatedDoubles, largeInts));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyDoubles, emptyInts));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyDoubles, smallInts));
	}

	private AbstractFlowIterable<IntWithDouble> createZipIteratorProviderFrom(final AbstractIterableDoubles first, final AbstractIterableInts second)
	{
		return new AbstractFlowIterable<IntWithDouble>() {
			@Override
			public AbstractFlow<IntWithDouble> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}
}
