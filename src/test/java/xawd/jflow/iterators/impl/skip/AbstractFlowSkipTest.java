package xawd.jflow.iterators.impl.skip;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractFlowSkipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final List<List<String>> expectedOutcomesForDifferentIndexArguments = asList(
				asList("0", "1", "2", "3", "4"),
				asList("1", "2", "3", "4"),
				asList("2", "3", "4"),
				asList("3", "4"),
				asList("4"),
				asList()
				);

		final int nArgs = expectedOutcomesForDifferentIndexArguments.size();

		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i ->
		{
			assertObjectIteratorAsExpected(expectedOutcomesForDifferentIndexArguments.get(i), createSkipIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createSkipIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i ->
		{
			assertThrows(IllegalArgumentException.class, () -> populated.iterator().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iterator().take(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i ->
		{
			assertObjectIteratorAsExpected(asList(), createSkipIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createSkipIteratorProviderFrom(empty, i));
		});

	}

	private <T> AbstractFlowIterable<T> createSkipIteratorProviderFrom(AbstractFlowIterable<T> src, int skipCount)
	{
		return new AbstractFlowIterable<T>() {
			@Override
			public AbstractFlow<T> iterator() {
				return src.iterator().drop(skipCount);
			}
		};
	}
}
