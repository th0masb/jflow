/**
 *
 */
package com.github.maumay.jflow.iterators.impl.flatten;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.impl.AbstractLongIterator;
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
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		Function<String, AbstractLongIterator> flattenMapping1 = string -> repeat(
				Long.parseLong(string), 2 * (Integer.parseInt(string) % 2));
		assertLongIteratorAsExpected(new long[] { 1, 1, 3, 3 },
				createFlattenToLongsIteratorProviderFrom(populated, flattenMapping1));
		assertLongIteratorAsExpected(new long[0],
				createFlattenToLongsIteratorProviderFrom(empty, flattenMapping1));

		Function<String, AbstractLongIterator> flattenMapping2 = string -> repeat(
				Long.parseLong(string), 2 * ((Integer.parseInt(string) + 1) % 2));
		assertLongIteratorAsExpected(new long[] { 0, 0, 2, 2, 4, 4 },
				createFlattenToLongsIteratorProviderFrom(populated, flattenMapping2));
		assertLongIteratorAsExpected(new long[0],
				createFlattenToLongsIteratorProviderFrom(empty, flattenMapping2));
	}

	private AbstractLongIterator repeat(long element, int nTimes)
	{
		return new AbstractLongIterator(OptionalInt.of(nTimes)) {
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
			AbstractEnhancedIterable<E> source,
			Function<? super E, ? extends LongIterator> flattenMapping)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return source.iterator().flatMapToLong(flattenMapping);
			}
		};
	}
}
