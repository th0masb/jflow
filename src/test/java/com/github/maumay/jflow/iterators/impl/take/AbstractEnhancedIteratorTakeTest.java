package com.github.maumay.jflow.iterators.impl.take;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * @author ThomasB
 */
public class AbstractEnhancedIteratorTakeTest extends IteratorExampleProviders
		implements FiniteIteratorTest
{
	@Test
	void test()
	{
		List<List<String>> expectedOutcomesForDifferentIndexArguments = asList(asList(),
				asList("0"), asList("0", "1"), asList("0", "1", "2"), asList("0", "1", "2", "3"),
				asList("0", "1", "2", "3", "4"));

		int nArgs = expectedOutcomesForDifferentIndexArguments.size();

		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();

		IntStream.range(0, nArgs).forEach(i -> {
			assertIteratorAsExpected(expectedOutcomesForDifferentIndexArguments.get(i),
					createTakeIteratorProviderFrom(populated, i));
			assertIteratorAsExpected(asList(), createTakeIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> {
			assertThrows(IllegalArgumentException.class, () -> populated.iter().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().take(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> {
			assertIteratorAsExpected(
					expectedOutcomesForDifferentIndexArguments.get(nArgs - 1),
					createTakeIteratorProviderFrom(populated, i));
			assertIteratorAsExpected(asList(), createTakeIteratorProviderFrom(empty, i));
		});
	}

	private <T> AbstractRichIterable<T> createTakeIteratorProviderFrom(
			AbstractRichIterable<T> src, int takeCount)
	{
		return new AbstractRichIterable<T>() {
			@Override
			public AbstractRichIterator<T> iter()
			{
				return src.iter().take(takeCount);
			}
		};
	}
}
