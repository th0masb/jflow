package com.github.maumay.jflow.iterators.impl.skip;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractEnhancedIteratorSkipTest extends IteratorExampleProviders implements IteratorTest
{
	@Test
	void test()
	{
		List<List<String>> expectedOutcomesForDifferentIndexArguments = asList(
				asList("0", "1", "2", "3", "4"), asList("1", "2", "3", "4"), asList("2", "3", "4"),
				asList("3", "4"), asList("4"), asList());

		int nArgs = expectedOutcomesForDifferentIndexArguments.size();

		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();

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

	private <T> AbstractRichIterable<T> createSkipIteratorProviderFrom(
			AbstractRichIterable<T> src, int skipCount)
	{
		return new AbstractRichIterable<T>() {
			@Override
			public AbstractRichIterator<T> iter()
			{
				return src.iter().skip(skipCount);
			}
		};
	}
}
