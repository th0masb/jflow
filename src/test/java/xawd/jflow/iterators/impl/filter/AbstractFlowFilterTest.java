package xawd.jflow.iterators.impl.filter;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;


/**
 * @author ThomasB
 */
class AbstractFlowFilterTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final Predicate<String> allFilteredPredicate = string -> parseInt(string) < 0;
		assertObjectIteratorAsExpected(asList(), createFilterIteratorProviderFrom(populated, allFilteredPredicate));
		assertObjectIteratorAsExpected(asList(), createFilterIteratorProviderFrom(empty, allFilteredPredicate));

		final Predicate<String> someFilteredPredicate = string -> parseInt(string) < 3;
		assertObjectIteratorAsExpected(asList("0", "1", "2"), createFilterIteratorProviderFrom(populated, someFilteredPredicate));
		assertObjectIteratorAsExpected(asList(), createFilterIteratorProviderFrom(empty, someFilteredPredicate));

		final Predicate<String> noneFilteredPredicate = string -> parseInt(string) < 5;
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"), createFilterIteratorProviderFrom(populated, noneFilteredPredicate));
		assertObjectIteratorAsExpected(asList(), createFilterIteratorProviderFrom(empty, noneFilteredPredicate));
	}

	private <E> AbstractFlowIterable<E> createFilterIteratorProviderFrom(final AbstractFlowIterable<E> source, final Predicate<? super E> predicate)
	{
		return new AbstractFlowIterable<E>() {
			@Override
			public AbstractFlow<E> iterator() {
				return source.iterator().filter(predicate);
			}
		};
	}
}
