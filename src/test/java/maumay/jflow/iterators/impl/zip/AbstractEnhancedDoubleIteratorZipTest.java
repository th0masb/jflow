/**
 *
 */
package maumay.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.iterators.misc.DoublePair;
import maumay.jflow.iterators.misc.DoubleWith;
import maumay.jflow.testutilities.AbstractEnhancedIterable;
import maumay.jflow.testutilities.AbstractIterableDoubles;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedDoubleIteratorZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithDouble()
	{
		final AbstractIterableDoubles small = getSmallDoubleTestIteratorProvider();
		final AbstractIterableDoubles mid = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles large = getLargeDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(DoublePair.of(0, 0), DoublePair.of(1, 1), DoublePair.of(2, 2),
						DoublePair.of(3, 3), DoublePair.of(4, 4)),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(DoublePair.of(0, 10), DoublePair.of(1, 11), DoublePair.of(2, 12),
						DoublePair.of(3, 13), DoublePair.of(4, 14)),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(DoublePair.of(0, 10), DoublePair.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractEnhancedIterable<DoublePair> createZipIteratorProviderFrom(
			final AbstractIterableDoubles first, final AbstractIterableDoubles second)
	{
		return new AbstractEnhancedIterable<DoublePair>() {
			@Override
			public AbstractEnhancedIterator<DoublePair> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	@Test
	void testZipWithObject()
	{
		final AbstractIterableDoubles populatedDoubles = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles emptyDoubles = getEmptyDoubleTestIteratorProvider();

		final AbstractEnhancedIterable<String> smallObjects = getSmallObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> midObjects = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> largeObjects = getLargeObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> emptyObjects = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList(DoubleWith.of(0, "10"), DoubleWith.of(1, "11")),
				createZipIteratorProviderFrom(populatedDoubles, smallObjects));

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(0, "0"), DoubleWith.of(1, "1"), DoubleWith.of(2, "2"),
						DoubleWith.of(3, "3"), DoubleWith.of(4, "4")),
				createZipIteratorProviderFrom(populatedDoubles, midObjects));

		assertObjectIteratorAsExpected(
				asList(DoubleWith.of(0, "10"), DoubleWith.of(1, "11"), DoubleWith.of(2, "12"),
						DoubleWith.of(3, "13"), DoubleWith.of(4, "14")),
				createZipIteratorProviderFrom(populatedDoubles, largeObjects));

		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyDoubles, emptyObjects));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(emptyDoubles, smallObjects));
	}

	private <E> AbstractEnhancedIterable<DoubleWith<E>> createZipIteratorProviderFrom(
			final AbstractIterableDoubles first, final AbstractEnhancedIterable<E> second)
	{
		return new AbstractEnhancedIterable<DoubleWith<E>>() {
			@Override
			public AbstractEnhancedIterator<DoubleWith<E>> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}
}
