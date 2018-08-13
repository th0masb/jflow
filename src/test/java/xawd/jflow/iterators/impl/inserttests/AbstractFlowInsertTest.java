package xawd.jflow.iterators.impl.inserttests;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractFlowInsertTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		final AbstractFlowIterable<String> small = getSmallObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("10", "11", "0", "1", "2", "3", "4"), createinsertIteratorProviderFrom(populated, small));
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"), createinsertIteratorProviderFrom(populated, empty));

		assertObjectIteratorAsExpected(asList("10", "11"), createinsertIteratorProviderFrom(empty, small));
		assertObjectIteratorAsExpected(asList(), createinsertIteratorProviderFrom(empty, empty));
	}

	private <E> AbstractFlowIterable<E> createinsertIteratorProviderFrom(final AbstractFlowIterable<E> source, final AbstractFlowIterable<E> toInsert)
	{
		return new AbstractFlowIterable<E>() {
			@Override
			public AbstractFlow<E> iterator() {
				return source.iterator().insert(toInsert.iterator());
			}
		};
	}
}
