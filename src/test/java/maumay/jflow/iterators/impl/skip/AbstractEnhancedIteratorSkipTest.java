package maumay.jflow.iterators.impl.skip;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.testutilities.AbstractEnhancedIterable;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractEnhancedIteratorSkipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final List<List<String>> expectedOutcomesForDifferentIndexArguments = asList(
				asList("0", "1", "2", "3", "4"), asList("1", "2", "3", "4"), asList("2", "3", "4"),
				asList("3", "4"), asList("4"), asList());

		final int nArgs = expectedOutcomesForDifferentIndexArguments.size();

		final AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i -> {
			assertObjectIteratorAsExpected(expectedOutcomesForDifferentIndexArguments.get(i),
					createSkipIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createSkipIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> {
			assertThrows(IllegalArgumentException.class, () -> populated.iter().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().take(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> {
			assertObjectIteratorAsExpected(asList(), createSkipIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createSkipIteratorProviderFrom(empty, i));
		});

	}

	private <T> AbstractEnhancedIterable<T> createSkipIteratorProviderFrom(
			AbstractEnhancedIterable<T> src, int skipCount)
	{
		return new AbstractEnhancedIterable<T>() {
			@Override
			public AbstractEnhancedIterator<T> iter()
			{
				return src.iter().drop(skipCount);
			}
		};
	}
}
