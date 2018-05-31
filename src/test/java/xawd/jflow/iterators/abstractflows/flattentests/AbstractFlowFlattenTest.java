/**
 *
 */
package xawd.jflow.iterators.abstractflows.flattentests;

import static java.util.Arrays.asList;

import java.util.NoSuchElementException;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.abstractiterables.AbstractFlowIterable;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractFlowFlattenTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final Function<String, AbstractFlow<String>> flattenMapping1 = string -> repeat(string, 2*(Integer.parseInt(string)%2));
		assertObjectIteratorAsExpected(asList("1", "1", "3", "3"), createFlattenIteratorProviderFrom(populated, flattenMapping1));
		assertObjectIteratorAsExpected(asList(), createFlattenIteratorProviderFrom(empty, flattenMapping1));

		final Function<String, AbstractFlow<String>> flattenMapping2 = string -> repeat(string, 2*((Integer.parseInt(string) + 1)%2));
		assertObjectIteratorAsExpected(asList("0", "0", "2", "2", "4", "4"), createFlattenIteratorProviderFrom(populated, flattenMapping2));
		assertObjectIteratorAsExpected(asList(), createFlattenIteratorProviderFrom(empty, flattenMapping2));
	}

	private <E> AbstractFlow<E> repeat(final E element, final int nTimes)
	{
		return new AbstractFlow<E>() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < nTimes;
			}
			@Override
			public E next() {
				if (count++ >= nTimes) {
					throw new NoSuchElementException();
				}
				return element;
			}
			@Override
			public void skip() {
				next();
			}
		};
	}

	private <E, R> AbstractFlowIterable<R> createFlattenIteratorProviderFrom(final AbstractFlowIterable<E> source, final Function<? super E, ? extends Flow<R>> flattenMapping)
	{
		return new AbstractFlowIterable<R>() {
			@Override
			public AbstractFlow<R> iterator() {
				return source.iterator().flatten(flattenMapping);
			}
		};
	}
}
