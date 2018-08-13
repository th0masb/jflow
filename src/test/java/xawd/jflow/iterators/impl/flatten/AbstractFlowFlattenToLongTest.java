/**
 *
 */
package xawd.jflow.iterators.impl.flatten;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractFlowFlattenToLongTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final Function<String, AbstractLongFlow> flattenMapping1 = string -> repeat(Long.parseLong(string), 2*(Integer.parseInt(string)%2));
		assertLongIteratorAsExpected(new long[] {1, 1, 3, 3}, createFlattenToLongsIteratorProviderFrom(populated, flattenMapping1));
		assertLongIteratorAsExpected(new long[0], createFlattenToLongsIteratorProviderFrom(empty, flattenMapping1));

		final Function<String, AbstractLongFlow> flattenMapping2 = string -> repeat(Long.parseLong(string), 2*((Integer.parseInt(string) + 1)%2));
		assertLongIteratorAsExpected(new long[] {0, 0, 2, 2, 4, 4}, createFlattenToLongsIteratorProviderFrom(populated, flattenMapping2));
		assertLongIteratorAsExpected(new long[0], createFlattenToLongsIteratorProviderFrom(empty, flattenMapping2));
	}

	private AbstractLongFlow repeat(final long element, final int nTimes)
	{
		return new AbstractLongFlow(OptionalInt.of(nTimes)) {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < nTimes;
			}
			@Override
			public long nextLong() {
				if (count++ >= nTimes) {
					throw new NoSuchElementException();
				}
				return element;
			}
			@Override
			public void skip() {
				nextLong();
			}
		};
	}

	private <E> AbstractIterableLongs createFlattenToLongsIteratorProviderFrom(final AbstractFlowIterable<E> source, final Function<? super E, ? extends LongFlow> flattenMapping)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iterator() {
				return source.iterator().flattenToLongs(flattenMapping);
			}
		};
	}
}
