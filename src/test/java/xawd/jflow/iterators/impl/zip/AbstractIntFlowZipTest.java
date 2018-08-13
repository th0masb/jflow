/**
 *
 */
package xawd.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.misc.IntPair;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.IntWithDouble;
import xawd.jflow.iterators.misc.IntWithLong;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntFlowZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithInt()
	{
		final AbstractIterableInts small = getSmallIntTestIteratorProvider();
		final AbstractIterableInts mid = getIntTestIteratorProvider();
		final AbstractIterableInts large = getLargeIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(IntPair.of(0, 0), IntPair.of(1, 1), IntPair.of(2, 2), IntPair.of(3, 3), IntPair.of(4, 4)),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(IntPair.of(0, 10), IntPair.of(1, 11), IntPair.of(2, 12), IntPair.of(3, 13), IntPair.of(4, 14)),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(
				asList(IntPair.of(0, 10), IntPair.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractFlowIterable<IntPair> createZipIteratorProviderFrom(final AbstractIterableInts first, final AbstractIterableInts second)
	{
		return new AbstractFlowIterable<IntPair>() {
			@Override
			public AbstractFlow<IntPair> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithObject()
	{
		final AbstractIterableInts populatedInts = getIntTestIteratorProvider();
		final AbstractIterableInts emptyInts = getEmptyIntTestIteratorProvider();

		final AbstractFlowIterable<String> smallObjects = getSmallObjectTestIteratorProvider();
		final AbstractFlowIterable<String> midObjects = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> largeObjects = getLargeObjectTestIteratorProvider();
		final AbstractFlowIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(IntWith.of(0, "10"), IntWith.of(1, "11")),
				createZipIteratorProviderFrom(populatedInts, smallObjects));

		assertObjectIteratorAsExpected(
				asList(IntWith.of(0, "0"), IntWith.of(1, "1"), IntWith.of(2, "2"), IntWith.of(3, "3"), IntWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedInts, midObjects));

		assertObjectIteratorAsExpected(
				asList(IntWith.of(0, "10"), IntWith.of(1, "11"), IntWith.of(2, "12"), IntWith.of(3, "13"), IntWith.of(4, "14")),
				createZipIteratorProviderFrom(populatedInts, largeObjects));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyInts, emptyObjects));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyInts, smallObjects));
	}

	private <E> AbstractFlowIterable<IntWith<E>> createZipIteratorProviderFrom(final AbstractIterableInts first, final AbstractFlowIterable<E> second)
	{
		return new AbstractFlowIterable<IntWith<E>>() {
			@Override
			public AbstractFlow<IntWith<E>> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithLong()
	{
		final AbstractIterableInts populatedInts = getIntTestIteratorProvider();
		final AbstractIterableInts emptyInts = getEmptyIntTestIteratorProvider();

		final AbstractIterableLongs smallLongs = getSmallLongTestIteratorProvider();
		final AbstractIterableLongs midLongs = getLongTestIteratorProvider();
		final AbstractIterableLongs largeLongs = getLargeLongTestIteratorProvider();
		final AbstractIterableLongs emptyLongs = getEmptyLongTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(IntWithLong.of(0, 10), IntWithLong.of(1, 11)),
				createZipIteratorProviderFrom(populatedInts, smallLongs));

		assertObjectIteratorAsExpected(
				asList(IntWithLong.of(0, 0), IntWithLong.of(1, 1), IntWithLong.of(2, 2), IntWithLong.of(3, 3), IntWithLong.of(4, 4)),
				createZipIteratorProviderFrom(populatedInts, midLongs));

		assertObjectIteratorAsExpected(
				asList(IntWithLong.of(0, 10), IntWithLong.of(1, 11), IntWithLong.of(2, 12), IntWithLong.of(3, 13), IntWithLong.of(4, 14)),
				createZipIteratorProviderFrom(populatedInts, largeLongs));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyInts, emptyLongs));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyInts, smallLongs));
	}

	private AbstractFlowIterable<IntWithLong> createZipIteratorProviderFrom(final AbstractIterableInts first, final AbstractIterableLongs second)
	{
		return new AbstractFlowIterable<IntWithLong>() {
			@Override
			public AbstractFlow<IntWithLong> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}

	@Test
	void testZipWithDouble()
	{
		final AbstractIterableInts populatedInts = getIntTestIteratorProvider();
		final AbstractIterableInts emptyInts = getEmptyIntTestIteratorProvider();

		final AbstractIterableDoubles smallDoubles = getSmallDoubleTestIteratorProvider();
		final AbstractIterableDoubles midDoubles = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles largeDoubles = getLargeDoubleTestIteratorProvider();
		final AbstractIterableDoubles emptyDoubles = getEmptyDoubleTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(IntWithDouble.of(0, 10), IntWithDouble.of(1, 11)),
				createZipIteratorProviderFrom(populatedInts, smallDoubles));

		assertObjectIteratorAsExpected(
				asList(IntWithDouble.of(0, 0), IntWithDouble.of(1, 1), IntWithDouble.of(2, 2), IntWithDouble.of(3, 3), IntWithDouble.of(4, 4)),
				createZipIteratorProviderFrom(populatedInts, midDoubles));

		assertObjectIteratorAsExpected(
				asList(IntWithDouble.of(0, 10), IntWithDouble.of(1, 11), IntWithDouble.of(2, 12), IntWithDouble.of(3, 13), IntWithDouble.of(4, 14)),
				createZipIteratorProviderFrom(populatedInts, largeDoubles));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyInts, emptyDoubles));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(emptyInts, smallDoubles));
	}

	private AbstractFlowIterable<IntWithDouble> createZipIteratorProviderFrom(final AbstractIterableInts first, final AbstractIterableDoubles second)
	{
		return new AbstractFlowIterable<IntWithDouble>() {
			@Override
			public AbstractFlow<IntWithDouble> iterator() {
				return first.iterator().zipWith(second.iterator());
			}
		};
	}
}
