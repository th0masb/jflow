/**
 *
 */
package com.github.maumay.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.gihub.maumay.jflow.iterators.misc.DoubleTup;
import com.gihub.maumay.jflow.iterators.misc.DoubleWith;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractDoubleIteratorZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithDouble()
	{
		final AbstractIterableDoubles small = getSmallDoubleTestIteratorProvider();
		final AbstractIterableDoubles mid = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles large = getLargeDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(DoubleTup.of(0, 0), DoubleTup.of(1, 1), DoubleTup.of(2, 2),
						DoubleTup.of(3, 3), DoubleTup.of(4, 4)),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(DoubleTup.of(0, 10), DoubleTup.of(1, 11), DoubleTup.of(2, 12),
						DoubleTup.of(3, 13), DoubleTup.of(4, 14)),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(DoubleTup.of(0, 10), DoubleTup.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(), createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractEnhancedIterable<DoubleTup> createZipIteratorProviderFrom(
			final AbstractIterableDoubles first, final AbstractIterableDoubles second)
	{
		return new AbstractEnhancedIterable<DoubleTup>() {
			@Override
			public AbstractEnhancedIterator<DoubleTup> iter()
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
