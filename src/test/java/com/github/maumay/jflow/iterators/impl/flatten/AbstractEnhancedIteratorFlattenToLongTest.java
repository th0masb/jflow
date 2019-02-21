/**
 *
 */
package com.github.maumay.jflow.iterators.impl.flatten;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedLongIterator;
import com.github.maumay.jflow.iterators.EnhancedLongIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorFlattenToLongTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final Function<String, AbstractEnhancedLongIterator> flattenMapping1 = string -> repeat(
				Long.parseLong(string), 2 * (Integer.parseInt(string) % 2));
		assertLongIteratorAsExpected(new long[] { 1, 1, 3, 3 },
				createFlattenToLongsIteratorProviderFrom(populated, flattenMapping1));
		assertLongIteratorAsExpected(new long[0],
				createFlattenToLongsIteratorProviderFrom(empty, flattenMapping1));

		final Function<String, AbstractEnhancedLongIterator> flattenMapping2 = string -> repeat(
				Long.parseLong(string), 2 * ((Integer.parseInt(string) + 1) % 2));
		assertLongIteratorAsExpected(new long[] { 0, 0, 2, 2, 4, 4 },
				createFlattenToLongsIteratorProviderFrom(populated, flattenMapping2));
		assertLongIteratorAsExpected(new long[0],
				createFlattenToLongsIteratorProviderFrom(empty, flattenMapping2));
	}

	private AbstractEnhancedLongIterator repeat(final long element, final int nTimes)
	{
		return new AbstractEnhancedLongIterator(OptionalInt.of(nTimes)) {
			int count = 0;

			@Override
			public boolean hasNext()
			{
				return count < nTimes;
			}

			@Override
			public long nextLong()
			{
				if (count++ >= nTimes) {
					throw new NoSuchElementException();
				}
				return element;
			}

			@Override
			public void skip()
			{
				nextLong();
			}
		};
	}

	private <E> AbstractIterableLongs createFlattenToLongsIteratorProviderFrom(
			final AbstractEnhancedIterable<E> source,
			final Function<? super E, ? extends EnhancedLongIterator> flattenMapping)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractEnhancedLongIterator iter()
			{
				return source.iterator().flatMapToLong(flattenMapping);
			}
		};
	}
}
