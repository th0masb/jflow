/**
 *
 */
package xawd.jflow.iterators.impl.appendtests;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractFlowAppendTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		final AbstractFlowIterable<String> small = getSmallObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4", "10", "11"), createAppendIteratorProviderFrom(populated, small));
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"), createAppendIteratorProviderFrom(populated, empty));

		assertObjectIteratorAsExpected(asList("10", "11"), createAppendIteratorProviderFrom(empty, small));
		assertObjectIteratorAsExpected(asList(), createAppendIteratorProviderFrom(empty, empty));
	}

	private <E> AbstractFlowIterable<E> createAppendIteratorProviderFrom(final AbstractFlowIterable<E> source, final AbstractFlowIterable<E> toAppend)
	{
		return new AbstractFlowIterable<E>() {
			@Override
			public AbstractFlow<E> iterator() {
				return source.iterator().append(toAppend.iterator());
			}
		};
	}
}
